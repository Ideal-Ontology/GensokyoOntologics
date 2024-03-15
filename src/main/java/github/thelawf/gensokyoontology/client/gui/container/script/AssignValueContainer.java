package github.thelawf.gensokyoontology.client.gui.container.script;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public class AssignValueContainer extends ScriptBuilderContainer {

    private final IItemHandler playerInventory;
    private final IWorldPosCallable POS_CALLABLE = IWorldPosCallable.DUMMY;
    private final IInventory assignedValue = new Inventory(1);

    protected AssignValueContainer(@Nullable ContainerType<?> type, int id, PlayerEntity player) {
        super(type, id);
        this.playerInventory = new InvWrapper(player.inventory);
        // addSlot(new SlotItemHandler(playerInventory))
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
