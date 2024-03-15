package github.thelawf.gensokyoontology.client.gui.container.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

// player inventory: 20, 96
// left slot: 20, 20
// right slot: 110, 20
// out put slot: 164, 54
// operate button: 56, 20
// left text: 20, 46
// right text: 20, 69
// left input: 60, 46
// right input: 60, 69
public class BinaryOperationContainer extends ScriptBuilderContainer{

    private final IInventory operationSlots = new Inventory(2);
    protected BinaryOperationContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
        // addSlot(new Slot(operationSlots))
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
