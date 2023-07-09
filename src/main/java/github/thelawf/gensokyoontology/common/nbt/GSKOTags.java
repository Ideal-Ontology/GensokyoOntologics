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


    public static class GSKOItemTag {
        public static final Tags.IOptionalNamedTag<Item> INYO_JADE = makeForgeTag("gems/inyo_jade");
        public static final Tags.IOptionalNamedTag<Item> DANMAKU = makeModTag("danmaku");

        public static final Tags.IOptionalNamedTag<Item> LARGE_SHOTS = makeModTag("danmaku/large_shots");
        public static final Tags.IOptionalNamedTag<Item> HEART_SHOTS = makeModTag("danmaku/heart_shots");
        public static final Tags.IOptionalNamedTag<Item> STAR_SHOTS = makeModTag("danmaku/star_shots");

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
