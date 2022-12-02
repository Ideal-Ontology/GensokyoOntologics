package github.thelawf.gensokyoontology.common.data;

import github.thelawf.gensokyoontology.common.nbt.ForgeTags;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class GSKOItemTags extends ItemTagsProvider {

    public GSKOItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, modId, existingFileHelper);
    }


    @Override
    protected void registerTags() {
        // addItemTags(ForgeTags.ONION, ItemRegistry.ONION.get());
        this.getOrCreateBuilder(ForgeTags.CROPS).addTag(ForgeTags.CROPS);
        this.getOrCreateBuilder(ForgeTags.ONION).addItemEntry(ItemRegistry.ONION.get());
    }

    protected void addItemTags(ITag.INamedTag<Item> tag, IItemProvider... items) {
        getOrCreateBuilder(tag).add(Arrays.stream(items).map(IItemProvider::asItem).toArray(Item[]::new));
    }
}
