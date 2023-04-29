package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public class StoryCard extends Item {
    private CompoundNBT storyNBT;

    public StoryCard(final CompoundNBT storyNBT) {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB).maxStackSize(1));
        this.storyNBT = storyNBT;

    }

    public CompoundNBT getStoryNBT() {
        return storyNBT;
    }

    public void setStoryNBT(CompoundNBT storyNBT) {
        this.storyNBT = storyNBT;
    }
}
