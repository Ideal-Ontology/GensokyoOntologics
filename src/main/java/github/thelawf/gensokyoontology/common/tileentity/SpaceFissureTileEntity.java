package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.core.init.TileEntityTypeRegistry;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class SpaceFissureTileEntity extends TileEntity implements ITickableTileEntity {
    public SpaceFissureTileEntity() {
        super(TileEntityTypeRegistry.SPACE_FISSURE_TILE_ENTITY.get());
    }

    @Override
    public void tick() {

    }
}
