package github.thelawf.gensokyoontology.common.container;

import com.google.common.collect.Maps;
import github.thelawf.gensokyoontology.api.Actions;
import github.thelawf.gensokyoontology.api.Functions;
import github.thelawf.gensokyoontology.api.client.AbstractContainer;
import github.thelawf.gensokyoontology.api.util.INBTReader;
import github.thelawf.gensokyoontology.api.util.IntRange;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.EnchantRegistry;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import github.thelawf.gensokyoontology.data.recipe.IKogasaSmithingRecipe;
import github.thelawf.gensokyoontology.data.recipe.RecastEntry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
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
            CompoundNBT mergedNBT = new CompoundNBT();

            this.getTagListFromAllMaterials(serverWorld.getRecipeManager()).forEach(entry -> {
                CompoundNBT materialNBT = RECAST_LOGICS.getOrDefault(entry.getKey(),
                        (container, defaultEntry) ->
                                defaultEntry.getValue()).apply(this, entry);
                mergedNBT.merge(materialNBT);
            });
            ItemStack resultStack = this.resultInv.getStackInSlot(0).copy();
            resultStack.setTag(mergedNBT);

            this.resultInv.setInventorySlotContents(0, resultStack);
        }
    }

    public int getDuplicateMaterialCount(IKogasaSmithingRecipe recipe) {
        return Math.toIntExact(this.craftingItems().stream().filter(stack ->
                stack.getItem() == recipe.getMaterial().getItem()).count());
    }


    private List<Item> craftingItems() {
        return GSKOUtil.toItemList(this.inventory).subList(1, 4).stream().map(ItemStack::getItem).collect(Collectors.toList());
    }

    private List<RecastEntry> getTagListFromAllMaterials(RecipeManager manager) {
        List<RecastEntry> entries = new ArrayList<>();
        manager.getRecipesForType(RecipeRegistry.KOGASA_SMITHING).forEach(recipe -> {
            if (this.craftingItems().contains(recipe.getMaterial().getItem())) {
                entries.add(recipe.getRecastEntry());
            }
        });
        return entries;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public IntRange getContainerSlotRange() {
        return new IntRange(0, 5);
    }

    public static final Map<ResourceLocation, BiFunction<KogasaSmitingContainer, RecastEntry, CompoundNBT>> RECAST_LOGICS =
            Util.make(Maps.newHashMap(), map -> {
                map.put(new ResourceLocation("minecraft:enchantment"),
                        (container, entry) -> {
                            CompoundNBT nbt = new CompoundNBT();
                            int count = container.getDuplicateMaterialCount((IKogasaSmithingRecipe)
                                    container.world.getRecipeManager());
                            if (count > 0) {
                                ListNBT listnbt = new ListNBT();

                                entry.getValue().getList("Enchantments", Type.COMPOUND.id).forEach(enchantment -> {
                                    if (enchantment instanceof CompoundNBT) {
                                        CompoundNBT tag = (CompoundNBT)enchantment;
                                        CompoundNBT newTag = new CompoundNBT();

                                        listnbt.add(tag);
                                        newTag.putString("id", tag.getString("id"));
                                        newTag.putInt("lvl", count);
                                    }
                                });

                                nbt.put("Enchantments", listnbt);
                                return nbt;
                            }
                            return nbt;
        });
    });
}
