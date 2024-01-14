package github.thelawf.gensokyoontology.common.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class HaniwaTileEntity extends TileEntity implements ITickableTileEntity {
    private int faithCount = 0;
    public static final int MAX_COUNT = 20;
    public HaniwaTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        if (nbt.contains("faith_count")) {
            this.faithCount = nbt.getInt("faith_count");
        }
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("faith_count", this.faithCount);
        return super.write(compound);
    }

    public void addFaith(int faithCount) {
        this.faithCount += faithCount;
    }

    @Override
    public void tick() {
        if (this.faithCount >= MAX_COUNT) this.remove();
    }
}
