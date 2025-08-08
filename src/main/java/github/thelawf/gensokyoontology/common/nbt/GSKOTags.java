package github.thelawf.gensokyoontology.common.nbt;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class GSKOTags {

    public static class GSKOItemTag {
        public static final Tags.IOptionalNamedTag<Item> INYO_JADE = makeForgeTag("gems/inyo_jade");
        public static final Tags.IOptionalNamedTag<Item> DANMAKU = makeModTag("danmaku");

        public static final Tags.IOptionalNamedTag<Item> LARGE_SHOTS = makeModTag("danmaku/large_shots");
        public static final Tags.IOptionalNamedTag<Item> SMALL_SHOTS = makeModTag("danmaku/small_shots");
        public static final Tags.IOptionalNamedTag<Item> HEART_SHOTS = makeModTag("danmaku/heart_shots");
        public static final Tags.IOptionalNamedTag<Item> SMALL_STAR_SHOTS = makeModTag("danmaku/small_star_shots");

        public static final Tags.IOptionalNamedTag<Item> LITERATE = makeModTag("food/literate");
        public static final Tags.IOptionalNamedTag<Item> PHOTOGENIC = makeModTag("food/photogenic");
        public static final Tags.IOptionalNamedTag<Item> PHANTASMAGORICAL = makeModTag("food/phantasmagorical");

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
