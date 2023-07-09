package github.thelawf.gensokyoontology.data;

import github.thelawf.gensokyoontology.common.nbt.GSKOTags;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class GSKOItemTags extends ItemTagsProvider {

    public GSKOItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        // addItemTags(GSKOTags.ONION, ItemRegistry.ONION.get());
        // this.getOrCreateBuilder(GSKOTags.INYO_JADE).addItemEntry()
    }

    protected void addItemTags(ITag.INamedTag<Item> tag, IItemProvider... items) {
        getOrCreateBuilder(tag).add(Arrays.stream(items).map(IItemProvider::asItem).toArray(Item[]::new));
    }
}
