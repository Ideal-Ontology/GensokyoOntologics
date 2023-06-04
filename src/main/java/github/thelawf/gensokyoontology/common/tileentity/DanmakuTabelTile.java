package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.core.init.TileEntityTypeRegistry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class DanmakuTabelTile extends TileEntity {
    public DanmakuTabelTile() {
        super(TileEntityTypeRegistry.DANMAKU_TABLE_TILE.get());
    }
}
