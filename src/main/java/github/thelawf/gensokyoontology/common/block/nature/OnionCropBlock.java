package github.thelawf.gensokyoontology.common.block.nature;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class OnionCropBlock extends CropBlock {

    public OnionCropBlock(BlockBehaviour.Properties builder) {
        super(builder);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ItemRegistry.ONION.get();
    }
}
