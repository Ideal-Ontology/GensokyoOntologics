package github.thelawf.gensokyoontology.common.container;

import github.thelawf.gensokyoontology.api.client.AbstractContainer;
import github.thelawf.gensokyoontology.api.util.INBTReader;
import github.thelawf.gensokyoontology.api.util.IntRange;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.data.recipe.KogasaSmithingRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class KogasaSmitingContainer extends AbstractContainer implements INBTReader {
    private final Inventory inventory = new Inventory(5);
    private final CraftResultInventory resultInv = new CraftResultInventory();
    private final World world;
    private final PlayerInventory playerInv;

    public KogasaSmitingContainer(PlayerInventory playerInv, int id) {
        super(ContainerRegistry.KOGASA_SMITHING_CONTAINER.get(), id);
        this.playerInv = playerInv;
        this.world = playerInv.player.world;
        this.addPlayerInventorySlots(this.playerInv, 7, 109, 167);

        this.addSlot(new Slot(this.inventory, 0, 51, 75){
            @Override
            public void onSlotChanged() {
                super.onSlotChanged();
                KogasaSmitingContainer.this.setMaterialsWhenHasSpecialTag();
                KogasaSmitingContainer.this.trySmithing();
            }

            @Override
            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                KogasaSmitingContainer.this.removeMaterialWhenTaken();
                return super.onTake(thePlayer, stack);
            }
        });

        for (int i = 1; i <= 4; i++) {
            this.addSlot(new Slot(this.inventory, i, 51 + 18 * i, 21 + 18 * i) {
                @Override
                public void onSlotChanged() {
                    super.onSlotChanged();
                    KogasaSmitingContainer.this.onCraftMatrixChanged(this.inventory);
                }
            });
        }

        this.addSlot(new Slot(this.resultInv, 0, 105, 75){
            @Override
            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                this.inventory.clear();
                return super.onTake(thePlayer, stack);
            }
        });
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);
        this.trySmithing();
    }


    private void setMaterialsWhenHasSpecialTag() {
        this.detectAndSendChanges();
        ItemStack smithingItem = this.inventory.getStackInSlot(0);
        List<ItemStack> materials = this.getNBTList(smithingItem, "materials", Type.COMPOUND).stream()
                .map(inbt -> ItemStack.read(Type.COMPOUND.cast(inbt))).collect(Collectors.toList());
        materials.forEach(stack ->
                this.inventory.setInventorySlotContents(materials.indexOf(stack) + 1, stack));
    }

    private void removeMaterialWhenTaken() {
        ItemStack smithingItem = this.inventory.getStackInSlot(0);
        List<ItemStack> materials = this.getNBTList(smithingItem, "materials", Type.COMPOUND).stream()
                .map(inbt -> ItemStack.read(Type.COMPOUND.cast(inbt))).collect(Collectors.toList());
        materials.forEach(stack ->
                this.inventory.setInventorySlotContents(materials.indexOf(stack) + 1, ItemStack.EMPTY));
    }

    public void trySmithing(){
        if (!this.world.isRemote) {
            ServerWorld serverWorld = (ServerWorld)this.world;
            Optional<KogasaSmithingRecipe> optional = serverWorld.getRecipeManager().getRecipe(
                    RecipeRegistry.KOGASA_SMITHING, this.inventory, serverWorld);
            if (!optional.isPresent()) return;
            KogasaSmithingRecipe recipe = optional.get();
            this.resultInv.setInventorySlotContents(0, recipe.getRecipeOutput());
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public IntRange getContainerSlotRange() {
        return new IntRange(0, 5);
    }
}
