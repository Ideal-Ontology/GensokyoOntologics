package github.thelawf.gensokyoontology.common.nbt;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class GSKOTags {

    public static class Items extends GSKOTags {
        static ITag.INamedTag<Item> tag (String modid, String tagName) {
            return tag(ItemTags::makeWrapperTag, modid, tagName);
        }
        static ITag.INamedTag<Item> forgeTag(String tagName){
            return tag("forge", tagName);
        }
        public static final ITag.INamedTag<Item> ONION_TAG = forgeTag("crops/onion");
    }

    static <T extends ITag.INamedTag<?>> T tag(Function<String, T> creator, String modid, String tagName) {
        return creator.apply(new ResourceLocation(modid, tagName).toString());
    }

    static <T extends ITag.INamedTag<?>> T forgeTag(Function<String, T> creator, String modid, String tagName) {
        return tag(creator, "forge", tagName);
    }

}
