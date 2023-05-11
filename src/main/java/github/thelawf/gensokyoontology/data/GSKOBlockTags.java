package github.thelawf.gensokyoontology.data;

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
        //getOrCreateBuilder(GSKOTags.CROPS).addTag(GSKOTags.CROPS);
        //getOrCreateBuilder(GSKOTags.ONION).addTag(GSKOTags.ONION).add(BlockRegistry.ONION_CROP_BLOCK.get());
    }
}
