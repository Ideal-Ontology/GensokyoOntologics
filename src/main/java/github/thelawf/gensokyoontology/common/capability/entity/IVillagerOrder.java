package github.thelawf.gensokyoontology.common.capability.entity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

public interface IVillagerOrder extends INBTSerializable<CompoundNBT> {

    void setAppetizer(ItemStack appetizer);
    void setEntrees(ItemStack entrees);
    void setDessert(ItemStack dessert);
    void setDrinks(ItemStack drinks);
    void addFavouriteTag(Tags.IOptionalNamedTag<Item> tag);
    void addFavouriteTags(List<Tags.IOptionalNamedTag<Item>> tags);
    ItemStack getAppetizer();
    ItemStack getEntrees();
    ItemStack getDessert();
    ItemStack getDrinks();
    List<Tags.IOptionalNamedTag<Item>> getFavouriteTags();
}
