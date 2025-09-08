package github.thelawf.gensokyoontology.common.capability.entity;

import github.thelawf.gensokyoontology.common.nbt.GSKONBTUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class VillagerOrder implements IVillagerOrder {
    private ItemStack appetizer;
    private ItemStack entrees;
    private ItemStack dessert;
    private ItemStack drinks;
    private final List<Tags.IOptionalNamedTag<Item>> favouriteTags;


    public VillagerOrder() {
        this.appetizer = ItemStack.EMPTY;
        this.entrees = ItemStack.EMPTY;
        this.dessert = ItemStack.EMPTY;
        this.drinks = ItemStack.EMPTY;
        this.favouriteTags = new ArrayList<>();
    }

    @Override
    public void setAppetizer(ItemStack appetizer) {
        this.appetizer = appetizer;
    }

    @Override
    public void setEntrees(ItemStack entrees) {
        this.entrees = entrees;
    }

    @Override
    public void setDessert(ItemStack dessert) {
        this.dessert = dessert;
    }

    @Override
    public void setDrinks(ItemStack drinks) {
        this.drinks = drinks;
    }

    @Override
    public final void addFavouriteTag(Tags.IOptionalNamedTag<Item> tag) {
        this.favouriteTags.add(tag);
    }

    @Override
    public final void addFavouriteTags(List<Tags.IOptionalNamedTag<Item>> tags) {
        this.favouriteTags.addAll(tags);
    }

    @Override
    public ItemStack getAppetizer() {
        return this.appetizer;
    }

    @Override
    public ItemStack getEntrees() {
        return this.entrees;
    }

    @Override
    public ItemStack getDessert() {
        return this.dessert;
    }

    @Override
    public ItemStack getDrinks() {
        return this.drinks;
    }

    @Override
    public List<Tags.IOptionalNamedTag<Item>> getFavouriteTags() {
        return this.favouriteTags;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        if (this.getDrinks() != ItemStack.EMPTY) nbt.put("appetizer", this.appetizer.serializeNBT());
        if (this.getDrinks() != ItemStack.EMPTY) nbt.put("entrees", this.entrees.serializeNBT());
        if (this.getDrinks() != ItemStack.EMPTY) nbt.put("dessert", this.dessert.serializeNBT());
        if (this.getDrinks() != ItemStack.EMPTY) nbt.put("drinks", this.drinks.serializeNBT());

        ListNBT listNBT = new ListNBT();
        this.favouriteTags.stream().map(tag -> tag.getName().toString()).collect(Collectors.toList()).forEach(
                s -> listNBT.add(StringNBT.valueOf(s)));
        nbt.put("favouriteTags", listNBT);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (nbt.contains("appetizer")) this.appetizer = ItemStack.read(nbt.getCompound("appetizer"));
        if (nbt.contains("entrees")) this.entrees = ItemStack.read(nbt.getCompound("entrees"));
        if (nbt.contains("dessert")) this.dessert = ItemStack.read(nbt.getCompound("dessert"));
        if (nbt.contains("drinks")) this.drinks = ItemStack.read(nbt.getCompound("drinks"));

        ListNBT listNBT = GSKONBTUtil.getListFrom("favouriteTags", nbt, GSKONBTUtil.NBTType.STRING);

        for (int i = 0; i < listNBT.size(); i++) {
            int index = i;
            AtomicReference<Tags.IOptionalNamedTag<Item>> tagRef = new AtomicReference<>();
            this.favouriteTags.replaceAll(tag -> {
                if (index == this.favouriteTags.indexOf(tag)) tagRef.set(this.favouriteTags.get(index));
                return tagRef.get();
            });
        }

        // this.favouriteTags.replaceAll(tag -> listNBT.forEachAct(nbt -> ItemStack.read(nbt)));
    }

    public static VillagerOrder deserialize(CompoundNBT nbt){
        VillagerOrder order = new VillagerOrder();
        order.deserializeNBT(nbt);
        return order;
    }
}
