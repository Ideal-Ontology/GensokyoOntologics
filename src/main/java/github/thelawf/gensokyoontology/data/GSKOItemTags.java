package github.thelawf.gensokyoontology.data;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class GSKOItemTags {

    public static final Tags.IOptionalNamedTag<Item> INYO_JADE = makeForgeTag("gems/inyo_jade");
    public static final Tags.IOptionalNamedTag<Item> DANMAKU = makeModTag("danmaku");

    public static final Tags.IOptionalNamedTag<Item> LARGE_SHOTS = makeModTag("danmaku/large_shots");
    public static final Tags.IOptionalNamedTag<Item> SMALL_SHOTS = makeModTag("danmaku/small_shots");
    public static final Tags.IOptionalNamedTag<Item> HEART_SHOTS = makeModTag("danmaku/heart_shots");
    public static final Tags.IOptionalNamedTag<Item> SMALL_STAR_SHOTS = makeModTag("danmaku/small_star_shots");

    public static final Tags.IOptionalNamedTag<Item> LITERATE = makeModTag("food/literate");
    public static final Tags.IOptionalNamedTag<Item> PHOTOGENIC = makeModTag("food/photogenic");
    public static final Tags.IOptionalNamedTag<Item> PHANTASMAGORICAL = makeModTag("food/phantasmagorical");

    public static final Tags.IOptionalNamedTag<Item> ORDERABLE = makeModTag("food/orderable");
    public static final Tags.IOptionalNamedTag<Item> APPETIZER = makeModTag("food/appetizer");
    public static final Tags.IOptionalNamedTag<Item> DRINKS = makeModTag("food/drinks");
    public static final Tags.IOptionalNamedTag<Item> ENTREES = makeModTag("food/entrees");
    public static final Tags.IOptionalNamedTag<Item> DESSERT = makeModTag("food/dessert");

    private static Tags.IOptionalNamedTag<Item> makeForgeTag(String name) {
        return ItemTags.createOptional(new ResourceLocation("forge", name));
    }

    private static Tags.IOptionalNamedTag<Item> makeMcTag(String name) {
        return ItemTags.createOptional(new ResourceLocation("minecraft", name));
    }

    private static Tags.IOptionalNamedTag<Item> makeModTag(String name) {
        return ItemTags.createOptional(new ResourceLocation(GensokyoOntology.MODID, name));
    }
}
