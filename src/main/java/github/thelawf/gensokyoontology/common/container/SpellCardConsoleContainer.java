package github.thelawf.gensokyoontology.common.container;

import github.thelawf.gensokyoontology.common.container.script.ScriptBuilderContainer;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public class SpellCardConsoleContainer extends WrapPlayerContainer {
    private final TileEntity tileEntity;
    public SpellCardConsoleContainer(int id, PlayerEntity player, World world, BlockPos blockPos) {
        super(ContainerRegistry.SPELL_CONSOLE_CONTAINER.get(), player.inventory, id);
        this.tileEntity = world.getTileEntity(blockPos);
        
        this.addPlayerInventorySlots(0,0);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
