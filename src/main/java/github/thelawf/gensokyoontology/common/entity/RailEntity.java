package github.thelawf.gensokyoontology.common.entity;

import github.thelawf.gensokyoontology.client.gui.screen.RailDashboardScreen;
import github.thelawf.gensokyoontology.common.item.tool.RailWrench;
import github.thelawf.gensokyoontology.common.util.math.RotMatrix;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import github.thelawf.gensokyoontology.data.GSKOSerializers;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RailEntity extends Entity {
    public static final float NAN = Float.NaN;
    public static final DataParameter<Integer> DATA_TARGET_ID = EntityDataManager.createKey(
            RailEntity.class, DataSerializers.VARINT);
    public static final DataParameter<Quaternion> DATA_ROT = EntityDataManager.createKey(
            RailEntity.class, GSKOSerializers.QUATERNION);
    public static final DataParameter<BlockPos> DATA_TARGET = EntityDataManager.createKey(
            RailEntity.class, DataSerializers.BLOCK_POS);

    public RailEntity(EntityType<RailEntity> entityType, World worldIn) {
        super(entityType, worldIn);
        this.setNoGravity(true);
    }

    public RailEntity(World worldIn) {
        this(EntityRegistry.RAIL_ENTITY.get(), worldIn);
    }

    public static RailEntity place(World world, BlockPos pos) {
        RailEntity railEntity = new RailEntity(world);
        railEntity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        return railEntity;
    }

    @Override
    @NotNull
    public ActionResultType processInitialInteract(@NotNull PlayerEntity player, @NotNull Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (stack.hasTag()) {
            return this.onClickNextRail(player, stack);
        }
        else {
            return this.onClickFirstRail(player, stack, this.getPosition());
        }
    }

    public ActionResultType onClickFirstRail(PlayerEntity player, ItemStack stack, BlockPos startPos) {
        if (stack.getItem() != ItemRegistry.RAIL_WRENCH.get()) return ActionResultType.PASS;
        if (Screen.hasShiftDown()){
            new RailDashboardScreen(this.getPosition(), this.getRotation(), this.getEntityId()).open();
            return ActionResultType.SUCCESS;
        }
        ItemStack connector = new ItemStack(ItemRegistry.RAIL_CONNECTOR.get());
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong("startPos", startPos.toLong());
        connector.setTag(nbt);

        stack.shrink(1);
        player.addItemStackToInventory(connector);
        return ActionResultType.CONSUME;
    }

    public ActionResultType onClickNextRail(PlayerEntity player, ItemStack stack) {
        if (this.world.getEntityByID(this.getTargetId()) == null) return ActionResultType.FAIL;
        if (stack.getItem() != ItemRegistry.RAIL_CONNECTOR.get()) return ActionResultType.PASS;
        if (stack.getTag() == null) return ActionResultType.PASS;

        BlockPos targetPos = BlockPos.fromLong(stack.getTag().getLong("startPos"));
        this.setTargetPos(targetPos);
        stack.shrink(1);
        player.addItemStackToInventory(new ItemStack(ItemRegistry.RAIL_WRENCH.get()));

        return ActionResultType.CONSUME;
    }

    @Override
    protected void registerData() {
        this.dataManager.register(DATA_TARGET_ID, 0);
        this.dataManager.register(DATA_ROT, new Quaternion(0f, 0f, 0f, 1f));
        this.dataManager.register(DATA_TARGET, new BlockPos(NAN, NAN, NAN));
    }

    @Override
    public @NotNull IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void readAdditional(@NotNull CompoundNBT nbt) {
        float qx = nbt.getFloat("qx");
        float qy = nbt.getFloat("qy");
        float qz = nbt.getFloat("qz");
        float qw = nbt.getFloat("qw");

        this.setRotation(new Quaternion(qx, qy, qz, qw));
        this.setTargetId(nbt.getInt("targetId"));

        if (nbt.contains("targetX") && nbt.contains("targetY") && nbt.contains("targetZ")){
            this.dataManager.set(DATA_TARGET, new BlockPos(nbt.getInt("targetX"), nbt.getInt("targetY"), nbt.getInt("targetZ")));
        }
        super.read(nbt);
    }

    @Override
    public void writeAdditional(@NotNull CompoundNBT compound) {
        compound.putFloat("qx", this.getRotation().getX());
        compound.putFloat("qy", this.getRotation().getY());
        compound.putFloat("qz", this.getRotation().getZ());
        compound.putFloat("qw", this.getRotation().getW());

        compound.putInt("targetId", this.getTargetId());
        compound.putInt("targetX", this.getTargetPos().getX());
        compound.putInt("targetY", this.getTargetPos().getY());
        compound.putInt("targetZ", this.getTargetPos().getZ());
    }

    public int getTargetId() {
        return this.dataManager.get(DATA_TARGET_ID);
    }
    public void setTargetId(int id) {
        this.dataManager.set(DATA_TARGET_ID, id);
    }

    public void setRotation(float qx, float qy, float qz, float qw) {
        this.dataManager.set(DATA_ROT, new Quaternion(qx, qy, qz, qw));
    }

    public void setRotation(Quaternion rotation) {
        this.dataManager.set(DATA_ROT, rotation);
    }

    public Quaternion getRotation() {
        return this.dataManager.get(DATA_ROT);
    }

    public BlockPos getTargetPos() {
        return this.dataManager.get(DATA_TARGET);
    }

    public void setTargetPos(BlockPos targetRailPos) {
        this.dataManager.set(DATA_TARGET, targetRailPos);
    }

    public Vector3f getFacing() {
        return new RotMatrix(this.getRotation()).tangent();
    }

    public Optional<RailEntity> getTargetRail() {
        return Optional.ofNullable((RailEntity) this.world.getEntityByID(this.dataManager.get(DATA_TARGET_ID)));
    }
}
