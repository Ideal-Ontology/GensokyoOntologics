package github.thelawf.gensokyoontology.common.container;

import github.thelawf.gensokyoontology.api.client.AbstractContainer;
import github.thelawf.gensokyoontology.api.util.IntRange;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AltarContainer extends AbstractContainer {
    protected final IInventory inventory = new Inventory(9);
    protected final World world;

    public AltarContainer(@Nullable ContainerType<?> type, PlayerInventory playerInv, int id) {
        super(type, id);
        this.world = playerInv.player.world;
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
