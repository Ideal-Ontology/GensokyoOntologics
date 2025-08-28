package github.thelawf.gensokyoontology.common.container;

import github.thelawf.gensokyoontology.api.Functions;
import github.thelawf.gensokyoontology.api.client.AbstractContainer;
import github.thelawf.gensokyoontology.api.util.INBTReader;
import github.thelawf.gensokyoontology.api.util.IntRange;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.EnchantRegistry;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import github.thelawf.gensokyoontology.data.recipe.KogasaSmithingRecipe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
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
                    KogasaSmitingContainer.this.trySmithing();
                }

                @Override
                public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                    KogasaSmitingContainer.this.trySmithing();
                    return super.onTake(thePlayer, stack);
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
        this.resultInv.setInventorySlotContents(0, smithingItem);
        List<ItemStack> materials = this.getNBTList(smithingItem, "materials", Type.COMPOUND).stream()
                .map(inbt -> ItemStack.read(Type.COMPOUND.cast(inbt))).collect(Collectors.toList());
        materials.forEach(stack ->
                this.inventory.setInventorySlotContents(materials.indexOf(stack) + 1, stack));
    }

    private void removeMaterialWhenTaken() {
        ItemStack smithingItem = this.inventory.getStackInSlot(0);
        this.resultInv.setInventorySlotContents(0, ItemStack.EMPTY);
        List<ItemStack> materials = this.getNBTList(smithingItem, "materials", Type.COMPOUND).stream()
                .map(inbt -> ItemStack.read(Type.COMPOUND.cast(inbt))).collect(Collectors.toList());
        materials.forEach(stack ->
                this.inventory.setInventorySlotContents(materials.indexOf(stack) + 1, ItemStack.EMPTY));
    }

    public void trySmithing(){
        this.detectAndSendChanges();
        if (!this.world.isRemote) {
            ServerWorld serverWorld = (ServerWorld)this.world;
            Optional<KogasaSmithingRecipe> optional = serverWorld.getRecipeManager().getRecipe(
                    RecipeRegistry.KOGASA_SMITHING, this.inventory, serverWorld);
            if (!optional.isPresent()) return;
            KogasaSmithingRecipe recipe = optional.get();
            CompoundNBT tag = recipe.getTagEntry();
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

    public static final Map<Item, Functions.Func3<Item, Enchantment, IInventory, CompoundNBT>> MATERIAL_ENCHANTS = Util.make(() -> {
        Map<Item, Functions.Func3<Item, Enchantment, IInventory, CompoundNBT>> map = new HashMap<>();
        map.put(ItemRegistry.SAKUYA_WATCH.get(), KogasaSmitingContainer::withEnchantTag);
        return map;
    });

    public final Map<Item, Function<IInventory, CompoundNBT>> RECAST_TAG_ENTRY = Util.make(() -> {
        Map<Item, Function<IInventory, CompoundNBT>> map = new HashMap<>();
        map.put(ItemRegistry.SAKUYA_WATCH.get(), inv -> MATERIAL_ENCHANTS.get(ItemRegistry.SAKUYA_WATCH.get())
                .apply(ItemRegistry.SAKUYA_WATCH.get(), EnchantRegistry.COOLDOWN_HASTE.get(), inv));
        return map;
    });

    private static CompoundNBT withEnchantTag(Item material ,Enchantment enchantment, IInventory inventory) {
        CompoundNBT tag = new CompoundNBT();
        if (!tag.contains("Enchantments", 9)) {
            tag.put("Enchantments", new ListNBT());
        }

        int itemCount = 0;
        for (int i = 0; i < inventory.getSizeInventory() - 1; i++) {
            if (inventory.getStackInSlot(i).getItem() == material) itemCount++;
        }

        ListNBT listnbt = tag.getList("Enchantments", 10);
        CompoundNBT compoundnbt = new CompoundNBT();
        compoundnbt.putString("id", String.valueOf(ForgeRegistries.ENCHANTMENTS.getKey(enchantment)));
        compoundnbt.putShort("lvl", (byte)itemCount);
        listnbt.add(compoundnbt);

        return tag;
    }
}
