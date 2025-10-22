package github.thelawf.gensokyoontology.data.tag;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class GSKOTags {

    public static class GSKOBlockTag {
        public static final Tags.IOptionalNamedTag<Block> ONION_CROP = makeForgeTag("crops/onion");

        private static Tags.IOptionalNamedTag<Block> makeForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

        private static Tags.IOptionalNamedTag<Item> makeMcTag(String id) {
            return ItemTags.createOptional(new ResourceLocation("minecraft", id));
        }

        private static Tags.IOptionalNamedTag<Block> makeModTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(GensokyoOntology.MODID, name));
        }
    }
}
