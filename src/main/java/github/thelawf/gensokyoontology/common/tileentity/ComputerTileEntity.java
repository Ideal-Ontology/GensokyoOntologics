package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.core.init.TileEntityTypeRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;

public class ComputerTileEntity extends TileEntity implements ITickableTileEntity {

    public ComputerTileEntity() {
        super(TileEntityTypeRegistry.COMPUTER_TILE_ENTITY.get());
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return super.getCapability(cap);
    }

    @Override
    public void tick() {

    }
}
