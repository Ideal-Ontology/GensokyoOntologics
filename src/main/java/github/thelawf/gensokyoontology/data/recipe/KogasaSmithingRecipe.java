package github.thelawf.gensokyoontology.data.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import net.minecraft.inventory.IInventory;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KogasaSmithingRecipe implements IKogasaSmithingRecipe{

    private final float powerConsumption;
    private final ResourceLocation id;
    private final NonNullList<ItemStack> materials;
    private final CompoundNBT tagEntry;

    public KogasaSmithingRecipe(ResourceLocation id, NonNullList<ItemStack> materials, CompoundNBT tagEntry, float power) {
        this.id = id;
        this.powerConsumption = power;
        this.materials = materials;
        this.tagEntry = tagEntry;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
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
    public ItemStack getMaterial(int slotIndex) {
        return this.materials.get(slotIndex);
    }

    @Override
    public int getMaterialCount(ItemStack stack) {
        return Math.toIntExact(this.materials.stream().filter(itemStack -> itemStack.getItem() == stack.getItem()).count());
    }

    @Override
    public CompoundNBT getTagEntry(ItemStack stack) {
        return this.tagEntry;
    }

    @Override
    public float getPowerConsumption() {
        return this.powerConsumption;
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

            NonNullList<ItemStack> materials = NonNullList.create();

            for (JsonElement jsonElement : JSONUtils.getJsonArray(json, "materials")) {
                materials.add(deserializeItem(jsonElement.getAsJsonObject()));
            }
            return new KogasaSmithingRecipe(recipeId, materials, tagEntry, powerConsumption);
        }

        @Override
        public @Nullable KogasaSmithingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            float power = buffer.readFloat();
            CompoundNBT recastTag = buffer.readCompoundTag();

            NonNullList<ItemStack> materials = NonNullList.create();
            for (int i = 0; i < buffer.readVarInt(); i++) {
                materials.add(buffer.readItemStack());
            }
            return new KogasaSmithingRecipe(recipeId, materials, recastTag, power);
        }

        @Override
        public void write(PacketBuffer buffer, KogasaSmithingRecipe recipe) {
            buffer.writeFloat(recipe.powerConsumption);
            buffer.writeCompoundTag(recipe.tagEntry);
            buffer.writeVarInt(recipe.materials.size());
            for (ItemStack stack : recipe.materials) {
                buffer.writeItemStack(stack);
            }
        }
    }
}
