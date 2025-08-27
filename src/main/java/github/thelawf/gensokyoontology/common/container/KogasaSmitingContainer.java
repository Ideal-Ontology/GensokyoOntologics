package github.thelawf.gensokyoontology.common.container;

import github.thelawf.gensokyoontology.api.util.INBTReader;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.data.recipe.KogasaSmithingRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
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

public class KogasaSmitingContainer extends Container implements INBTReader {
    private Inventory inventory = new Inventory(5);
    private Inventory resultInv = new Inventory(0);
    private World world;
    private PlayerEntity player;
    private PlayerInventory playerInv;

    protected KogasaSmitingContainer(@Nullable ContainerType<?> type, PlayerInventory playerInv, int id) {
        super(type, id);
        this.playerInv = playerInv;
        this.player = playerInv.player;
        this.world = playerInv.player.world;

        this.addSlot(new Slot(this.inventory, 0, 56, 35){
            @Override
            public void onSlotChanged() {
                super.onSlotChanged();
                KogasaSmitingContainer.this.onSmithingAdded();
                KogasaSmitingContainer.this.trySmithing();
            }
        });

        for (int i = 1; i <= 4; i++) {
            this.addSlot(new Slot(this.inventory, i, 56, 35){
                @Override
                public void onSlotChanged() {
                    super.onSlotChanged();
                    KogasaSmitingContainer.this.onCraftMatrixChanged(this.inventory);
                }
            });
        }

        this.addSlot(new Slot(this.resultInv, 0, 56, 35){
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

    public void onSmithingAdded(){
        this.detectAndSendChanges();
        this.setMaterialsWhenHasSpecialTag();
    }

    public void setMaterialsWhenHasSpecialTag() {
        ItemStack smithingItem = this.inventory.getStackInSlot(0);

        List<ItemStack> materials = this.getNBTList(smithingItem, "materials", Type.COMPOUND).stream()
                .map(inbt -> ItemStack.read(Type.COMPOUND.cast(inbt))).collect(Collectors.toList());

        materials.forEach(stack ->
                this.inventory.setInventorySlotContents(materials.indexOf(stack) + 1, stack));
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
}
