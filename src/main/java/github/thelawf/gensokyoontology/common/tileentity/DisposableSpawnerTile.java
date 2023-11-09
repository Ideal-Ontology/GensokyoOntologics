package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Predicate;

/** 一次性刷怪笼
 *
 */
public class DisposableSpawnerTile extends TileEntity implements ITickableTileEntity, IRayTraceReader {

    private EntityType<?> entityType;
    private boolean canContinueSpawn;

    public DisposableSpawnerTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.canContinueSpawn = true;
        this.entityType = FlandreScarletEntity.FLANDRE_SCARLET;
    }

    @Override
    public void tick() {
        if (this.world != null) {
            AxisAlignedBB aabb = new AxisAlignedBB(0,0,0,10,10,10);
            Predicate<DisposableSpawnerTile> predicate = tileEntity ->
                    tileEntity.getSpawnEntity() != null && tileEntity.world != null &&
                    getEntityWithinSphere(this.world, PlayerEntity.class, aabb.offset(this.pos), 10).size() > 0;
            spawn(predicate);
            markDirty();
        }
    }

    private void spawn(Predicate<DisposableSpawnerTile> predicate){
        if (predicate.test(this)) {
            this.canContinueSpawn = false;
        }
    }


    public EntityType<?> getSpawnEntity() {
        return this.entityType;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
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
