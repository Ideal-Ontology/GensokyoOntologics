package github.thelawf.gensokyoontology.common.nbt;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class GSKOTags {

    public static final Tags.IOptionalNamedTag<Item> CROPS = GSKOItemTag.makeForgeTag("crops");

    public static final Tags.IOptionalNamedTag<Item> INYO_JADE = GSKOItemTag.makeForgeTag("inyo_jade");
    public static final Tags.IOptionalNamedTag<Item> ONION = GSKOItemTag.makeForgeTag("crops/onion");

    public static final Tags.IOptionalNamedTag<Block> ONION_CROP = GSKOBlockTag.makeForgeTag("crops/onion");

    static class GSKOItemTag {
        private static Tags.IOptionalNamedTag<Item> makeForgeTag(String id) {
            return ItemTags.createOptional(new ResourceLocation("forge", id));
        }

        private static Tags.IOptionalNamedTag<Item> makeMcTag(String id) {
            return ItemTags.createOptional(new ResourceLocation("minecraft", id));
        }

        private static Tags.IOptionalNamedTag<Item> makeModTag(String id) {
            return ItemTags.createOptional(new ResourceLocation(GensokyoOntology.MODID, id));
        }
    }

    static class GSKOBlockTag {
        private static Tags.IOptionalNamedTag<Block> makeForgeTag(String id) {
            return BlockTags.createOptional(new ResourceLocation("forge", id));
        }
    }
}
