package github.thelawf.gensokyoontology.common.named;

import net.minecraft.block.Block;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;

import java.util.List;

public class GSKOTag<T> implements ITag<T> {

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public List<T> getAllElements() {
        return null;
    }

    public static Tag<Block> blockTag;

}
