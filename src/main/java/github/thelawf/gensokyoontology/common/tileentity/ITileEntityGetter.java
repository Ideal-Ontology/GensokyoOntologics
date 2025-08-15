package github.thelawf.gensokyoontology.common.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public interface ITileEntityGetter<T extends TileEntity> {

    @SuppressWarnings("unchecked")
    default Optional<T> getTileEntity(World world, BlockPos pos) {

        TileEntity tile = world.getTileEntity(pos);
        if (tile == null) return Optional.empty();

        T te = (T) tile;
        return Optional.of(te);
    }
}
