package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.core.init.TileEntityTypeRegistry;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class SpaceFissureTileEntity extends TileEntity implements ITickableTileEntity {

    public final int lifeSpan = 200;
    protected long elapsedTime = 0L;

    public SpaceFissureTileEntity() {
        super(TileEntityTypeRegistry.SPACE_FISSURE_TILE_ENTITY.get());
    }

    @Override
    public void tick() {
        if (this.world != null) {
            this.elapsedTime++;
            if (this.elapsedTime >= lifeSpan) {
                this.remove();
            }
        }
    }
}
