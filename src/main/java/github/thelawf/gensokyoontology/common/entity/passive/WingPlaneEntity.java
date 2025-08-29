package github.thelawf.gensokyoontology.common.entity.passive;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerCapability;
import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 僚机属性：数量、浮游位置、是否围绕玩家旋转、旋转速度
 */
public class WingPlaneEntity extends AffiliatedEntity {

    public static final DataParameter<Integer> DATA_INDEX = EntityDataManager.
            createKey(WingPlaneEntity.class, DataSerializers.VARINT);

    public WingPlaneEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.setInvulnerable(true);
        this.setNoGravity(true);
    }

    public WingPlaneEntity(Entity owner, World worldIn) {
        super(EntityRegistry.WING_PLANE.get(), owner, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.getDataManager().register(DATA_INDEX, 0);
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
        this.getDataManager().set(DATA_INDEX, compound.getInt("index"));
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("index", this.getDataManager().get(DATA_INDEX));
    }

    public int getIndex() {
        return this.getDataManager().get(DATA_INDEX);
    }

    public void setIndex(int index) {
        this.getDataManager().set(DATA_INDEX, index);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getOwnerId().isPresent()) return;
        UUID ownerId = this.getOwnerId().get();
        PlayerEntity player = this.world.getPlayerByUuid(ownerId);

        if (player == null) return;
        if (this.ticksExisted % 3 == 0)
            this.move(MoverType.SELF, this.getCurrentFloatingPos(player));
    }

    public Vector3d getCurrentFloatingPos(PlayerEntity player) {
        AtomicReference<Vector3d> atomicVector3d = new AtomicReference<>(Vector3d.ZERO);
        player.getCapability(GSKOCapabilities.POWER).ifPresent(cap -> {
            int wingPlaneCount = (int) (cap.getCount() + 1);
            Vector3d xp = new Vector3d(1, 0, 0);
            Vector3d initPos = xp.rotateYaw(Danmaku.rad(360F / wingPlaneCount * this.getIndex()));
            Vector3d rotatedPos = initPos.rotateYaw(Danmaku.rad(this.ticksExisted));
            Vector3d finalPos = player.getPositionVec().add(rotatedPos);
            atomicVector3d.set(rotatedPos);
        });
        return atomicVector3d.get();
    }
}
