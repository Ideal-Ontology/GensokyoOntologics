package github.thelawf.gensokyoontology.common.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class TileEntityHelper {
    public static <T extends TileEntity> Optional<TileEntity> getTileIf(World world, BlockPos pos, TileEntityType<T> type) {

        TileEntity tile = world.getTileEntity(pos);
        if (tile == null) return Optional.empty();
        if (tile.getType() != type) return Optional.empty();

        T te = (T) tile;
        return Optional.of(te);
    }
}
