package github.thelawf.gensokyoontology.data.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;


public class KogasaSmithingRecipe implements IKogasaSmithingRecipe{

    private final float powerConsumption;
    private final ResourceLocation id;
    private final ItemStack material;
    private final CompoundNBT tagEntry;

    public KogasaSmithingRecipe(ResourceLocation id, ItemStack material, CompoundNBT tagEntry, float power) {
        this.id = id;
        this.powerConsumption = power;
        this.material = material;
        this.tagEntry = tagEntry;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        List<Item> itemList = GSKOUtil.toItemList(inv).stream().map(ItemStack::getItem).collect(Collectors.toList());
        return true;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeRegistry.KOGASA_SMITHING_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RecipeRegistry.KOGASA_SMITHING;
    }

    @Override
    public ItemStack getMaterial() {
        return this.material;
    }

    @Override
    public CompoundNBT getTagEntry() {
        return this.tagEntry;
    }

    @Override
    public float getPowerConsumption() {
        return this.powerConsumption;
    }

    @Override
    public int getDuplicateMaterialCount(IInventory inventory) {
        return Math.toIntExact(GSKOUtil.toItemList(inventory).subList(1, 4).stream().filter(stack ->
                stack.getItem() == this.getMaterial().getItem()).count());
    }

    public static class Type implements IRecipeType<KogasaSmithingRecipe> {
        @Override
        public String toString() {
            return RECIPE_ID.toString();
        }
    }

    public static ItemStack deserializeItem(JsonObject json) {
        String itemRegistryName = JSONUtils.getString(json, "item");
        int count = JSONUtils.getInt(json, "count");
        return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemRegistryName)), count);
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<KogasaSmithingRecipe> {

        @Override
        public KogasaSmithingRecipe read(ResourceLocation recipeId, JsonObject json) {
            float powerConsumption = JSONUtils.getFloat(json, "power");

            CompoundNBT tagEntry = CompoundNBT.CODEC.decode(JsonOps.INSTANCE,
                    JSONUtils.getJsonObject(json, "tag_entry")).result()
                    .orElse(Pair.of(new CompoundNBT(), new JsonObject())).getFirst();

            ItemStack material = deserializeItem(JSONUtils.getJsonObject(json, "material"));
            return new KogasaSmithingRecipe(recipeId, material, tagEntry, powerConsumption);
        }

        @Override
        public @Nullable KogasaSmithingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            float power = buffer.readFloat();
            CompoundNBT tagEntry = buffer.readCompoundTag();
            ItemStack material = buffer.readItemStack();
            return new KogasaSmithingRecipe(recipeId, material, tagEntry, power);
        }

        @Override
        public void write(PacketBuffer buffer, KogasaSmithingRecipe recipe) {
            buffer.writeFloat(recipe.powerConsumption);
            buffer.writeCompoundTag(recipe.tagEntry);
            buffer.writeItemStack(recipe.material);
        }
    }
}
