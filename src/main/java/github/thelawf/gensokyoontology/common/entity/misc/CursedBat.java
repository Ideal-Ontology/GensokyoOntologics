package github.thelawf.gensokyoontology.common.entity.misc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CursedBat extends BatEntity {
    private static final DataParameter<BlockPos> MANSION_POS = EntityDataManager.createKey(CursedBat.class,
            DataSerializers.BLOCK_POS);
    private BlockPos mansionPos;
    static {
        new BlockPos(0, 0, 0);
    }

    public CursedBat(EntityType<? extends BatEntity> type, World worldIn, BlockPos mansionPos) {
        super(type, worldIn);
        this.mansionPos = mansionPos;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(MANSION_POS, this.mansionPos);
    }

    public BlockPos getMansionPos() {
        return this.dataManager.get(MANSION_POS);
    }

    public void setMansionPos(BlockPos mansionPos) {
        this.mansionPos = mansionPos;
        this.dataManager.set(MANSION_POS, this.mansionPos);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putLong("mansion_pos", this.getMansionPos().toLong());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("mansion_pos")) {
            this.setMansionPos(BlockPos.fromLong(compound.getLong("mansion_pos")));
        }
    }
}
