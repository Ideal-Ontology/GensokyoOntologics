package github.thelawf.gensokyoontology.common.nbt;

import net.minecraft.block.Block;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class ForgeTags {

    public static final ITag.INamedTag<Item> CROPS = makeItemTag("crops");

    public static final ITag.INamedTag<Item> INYO_JADE = makeItemTag("inyo_jade");
    public static final ITag.INamedTag<Item> ONION = makeItemTag("crops/onion");

    public static final ITag.INamedTag<Block> ONION_CROP = makeBlockTag("crops/onion");

    private static ITag.INamedTag<Item> makeItemTag(String id) {
        return ItemTags.makeWrapperTag(new ResourceLocation("forge", id).toString());
    }

    private static ITag.INamedTag<Block> makeBlockTag(String id) {
        return BlockTags.makeWrapperTag(new ResourceLocation("forge", id).toString());
    }
}
