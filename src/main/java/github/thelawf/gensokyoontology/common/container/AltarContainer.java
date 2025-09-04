package github.thelawf.gensokyoontology.common.container;

import github.thelawf.gensokyoontology.api.client.AbstractContainer;
import github.thelawf.gensokyoontology.api.util.IntRange;
import github.thelawf.gensokyoontology.data.recipe.RecastEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AltarContainer extends AbstractContainer {
    protected final IInventory inventory = new Inventory(9);
    protected final World world;

    public AltarContainer(@Nullable ContainerType<?> type, PlayerInventory playerInv, int id) {
        super(type, id);
        this.world = playerInv.player.world;
    }

    public void tryCraft(){
        this.detectAndSendChanges();
        if (this.world.isRemote) return;

        ServerWorld serverWorld = (ServerWorld)this.world;
        serverWorld.getRecipeManager().getRecipesForType()
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
