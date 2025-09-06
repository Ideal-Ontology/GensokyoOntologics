package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class OnbashiraTileEntity extends TileEntity implements ITickableTileEntity {
    private ItemStack material;

    public OnbashiraTileEntity() {
        super(TileEntityRegistry.ONBASHIRA_TILE_ENTITY.get());
    }

    @Override
    public void tick() {

    }

    @Override
    public void read(BlockState blockState, CompoundNBT nbt) {
        super.read(blockState, nbt);
        this.material = ItemStack.read(nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        this.material.write(nbt);
        return super.write(nbt);
    }

    public void setMaterial(ItemStack material) {
        this.material = material;
    }

    public ItemStack getMaterial() {
        return this.material;
    }
}
