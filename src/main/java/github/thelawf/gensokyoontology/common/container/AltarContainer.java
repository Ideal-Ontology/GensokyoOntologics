package github.thelawf.gensokyoontology.common.container;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.api.client.AbstractContainer;
import github.thelawf.gensokyoontology.api.util.IntRange;
import github.thelawf.gensokyoontology.common.tileentity.AltarTileEntity;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.List;

public class AltarContainer extends AbstractContainer {
    protected final IInventory inventory = new Inventory(1);
    protected final PlayerInventory playerInv;
    protected final TileEntity altarTile;
    protected final World world;

    public AltarContainer(int id, PlayerInventory playerInv, BlockPos pos) {
        super(ContainerRegistry.ALTAR_CONTAINER.get(), id);
        this.playerInv = playerInv;
        this.world = playerInv.player.world;
        this.altarTile = this.world.getTileEntity(pos);
        this.addPlayerInventorySlots(this.playerInv, 8, 110, 168);

        if (this.altarTile != null) {
            this.altarTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
                this.inventory.setInventorySlotContents(0, itemHandler.getStackInSlot(0));
                this.addSlot(new SlotItemHandler(itemHandler, 0, 125, 44));
            });
        }
    }

    public List<Pair<Pair<Integer, Integer>, ItemStack>> getMaterials() {
        return AltarTileEntity.getMaterialsOfPos(this.world, this.altarTile.getPos());
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public IntRange getContainerSlotRange() {
        return IntRange.of(0,7);
    }
}
