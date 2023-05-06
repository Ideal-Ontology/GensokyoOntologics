package github.thelawf.gensokyoontology.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.ArrayList;
import java.util.List;

public class DomainCapability implements IDomainCapability{

    public AxisAlignedBB rangeBox;

    public DomainCapability(AxisAlignedBB rangeBox) {
        this.rangeBox = rangeBox;
    }

    @Override
    public AxisAlignedBB getRange() {
        return rangeBox;
    }

    @Override
    public void setRange(AxisAlignedBB aabb) {
        this.rangeBox = aabb;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putDouble("range_min_x", this.rangeBox.minX);
        nbt.putDouble("range_max_x", this.rangeBox.maxX);
        nbt.putDouble("range_min_y", this.rangeBox.minY);
        nbt.putDouble("range_max_y", this.rangeBox.maxY);
        nbt.putDouble("range_min_z", this.rangeBox.minZ);
        nbt.putDouble("range_max_z", this.rangeBox.maxZ);
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.rangeBox = new AxisAlignedBB(
                nbt.getDouble("range_min_x"),
                nbt.getDouble("range_max_x"),
                nbt.getDouble("range_min_y"),
                nbt.getDouble("range_max_y"),
                nbt.getDouble("range_min_z"),
                nbt.getDouble("range_max_z"));
    }
}
