package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.core.init.TileEntityTypeRegistry;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class SukimaTileEntity extends TileEntity implements ITickableTileEntity {
    public SukimaTileEntity() {
        super(TileEntityTypeRegistry.SUKIMA_TILE_ENTITY.get());
    }

    @Override
    public void tick() {

    }
}
