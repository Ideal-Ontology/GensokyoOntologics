package github.thelawf.gensokyoontology.common.tileentity;

import com.google.common.collect.ImmutableList;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntArrayNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.vector.Vector3i;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdobeTileEntity extends TileEntity {
    private List<Vector3i> positionCarved;
    public AdobeTileEntity() {
        super(TileEntityRegistry.ADOBE_TILE_ENTITY.get());
    }

    @Override
    public void read(@NotNull BlockState state, CompoundNBT nbt) {
        if (nbt.get("PositionCarved") != null) {
            INBT iNBT = nbt.get("PositionCarved");
            if (iNBT instanceof ListNBT) {
                ListNBT listNBT = ((ListNBT) iNBT);
                tryReadListNBT(listNBT);
            }
        }
        super.read(state, nbt);
    }

    @NotNull
    @Override
    public CompoundNBT write(@NotNull CompoundNBT compound) {
        super.write(compound);
        ListNBT listNBT = new ListNBT();
        this.positionCarved.forEach(vector3i -> listNBT.add(new IntArrayNBT(ImmutableList.of(vector3i.getX(), vector3i.getY(), vector3i.getZ()))));
        compound.put("PositionCarved", listNBT);
        return super.write(compound);
    }

    private void tryReadListNBT(ListNBT nbt) {
        for (INBT inbt : nbt) {
            if (inbt instanceof IntArrayNBT) {
                IntArrayNBT arr = ((IntArrayNBT) inbt);
                if (arr.size() != 3) return;
                Vector3i vector3i = new Vector3i(arr.get(0).getInt(), arr.get(1).getInt(), arr.get(2).getInt());
                this.positionCarved.clear();
                this.positionCarved.add(vector3i);
            }

        }
    }
}
