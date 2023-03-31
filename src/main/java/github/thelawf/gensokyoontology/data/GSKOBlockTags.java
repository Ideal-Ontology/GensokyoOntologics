package github.thelawf.gensokyoontology.data;

import github.thelawf.gensokyoontology.common.nbt.ForgeTags;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class GSKOBlockTags extends BlockTagsProvider {

    public GSKOBlockTags(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        //getOrCreateBuilder(ForgeTags.CROPS).addTag(ForgeTags.CROPS);
        //getOrCreateBuilder(ForgeTags.ONION).addTag(ForgeTags.ONION).add(BlockRegistry.ONION_CROP_BLOCK.get());
    }
}
