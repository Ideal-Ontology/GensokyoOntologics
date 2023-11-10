package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Predicate;

/** 一次性刷怪笼
 *
 */
public class DisposableSpawnerTile extends TileEntity implements ITickableTileEntity, IRayTraceReader {

    private EntityType<?> entityType;
    private boolean canContinueSpawn;

    public DisposableSpawnerTile() {
        super(TileEntityTypeRegistry.DISPOSABLE_SPAWNER_TILE_ENTITY.get());
        this.canContinueSpawn = true;
        this.entityType = FlandreScarletEntity.FLANDRE_SCARLET;
    }

    @Override
    public void tick() {
        if (this.world != null && this.world.isRemote) {
            AxisAlignedBB aabb = new AxisAlignedBB(0, 0, 0, 10, 10, 10);
            PlayerEntity player = this.world.getClosestPlayer(this.pos.getX(), this.pos.getY(), this.pos.getZ(), 10, false);
            if (player == null) return;

            Predicate<DisposableSpawnerTile> predicate = tileEntity ->
                    tileEntity.getSpawnEntity() != null && tileEntity.canContinueSpawn && !player.isCreative() &&
                            getEntityWithinSphere(this.world, PlayerEntity.class, aabb.offset(this.pos), 10).size() > 0;
            spawn(predicate);
            markDirty();
        }
    }

    private void spawn(Predicate<DisposableSpawnerTile> predicate){
        if (predicate.test(this) && this.world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) this.world;
            CompoundNBT compound = this.write(new CompoundNBT());
            BlockPos.Mutable blockPos = this.pos.toMutable().move(GSKOMathUtil.randomRange(-3,3), 1, GSKOMathUtil.randomRange(-3,3));
            Optional<EntityType<?>> optionalEntity = EntityType.readEntityType(compound);
            optionalEntity.ifPresent(type -> type.spawn(serverWorld, null, null, blockPos.toImmutable(), SpawnReason.SPAWNER, false, false));
            this.canContinueSpawn = false;
            markDirty();
            this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
        }
    }


    public EntityType<?> getSpawnEntity() {
        return this.entityType;
    }

    @Override
    public void read(@NotNull BlockState state, @NotNull CompoundNBT nbt) {
        super.read(state, nbt);
        Optional<EntityType<?>> entityOptional = EntityType.readEntityType(nbt);
        entityOptional.ifPresent(type -> this.entityType = entityOptional.get());
    }

    @Override
    @NotNull
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString("id", GensokyoOntology.withRL("flandre_scarlet").toString());
        compound.putBoolean("can_continue_spawn", this.canContinueSpawn);
        return compound;
    }
}
