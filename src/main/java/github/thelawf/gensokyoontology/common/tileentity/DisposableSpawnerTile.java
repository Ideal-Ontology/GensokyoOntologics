package github.thelawf.gensokyoontology.common.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class DisposableSpawnerTile extends TileEntity {

    private Entity entity;
    public DisposableSpawnerTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
}
