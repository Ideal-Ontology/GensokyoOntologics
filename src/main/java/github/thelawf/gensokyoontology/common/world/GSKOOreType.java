package github.thelawf.gensokyoontology.common.world;

import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum GSKOOreType {
    CRIMSON_METAL(Lazy.of(BlockRegistry.CRIMSON_METAL_ORE), 2, 3, 10),
    DRAGON_SPHERE(Lazy.of(BlockRegistry.DRAGON_SPHERE_ORE), 6, 15, 30),
    JADE_GENSOKYO(Lazy.of(BlockRegistry.JADE_ORE), 8, 10, 20),
    JADE_FORMER_HELL(Lazy.of(BlockRegistry.JADE_ORE), 8, 7, 12);

    private final Lazy<Block> lazyBlock;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;

    GSKOOreType(Lazy<Block> lazyBlock, int maxVeinSize, int minHeight, int maxHeight) {
        this.lazyBlock = lazyBlock;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public Lazy<Block> getLazyBlock() {
        return lazyBlock;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public static GSKOOreType getInstance(Block block) {
        for (GSKOOreType oreType : values()) {
            if (block == oreType.lazyBlock) {
                return oreType;
            }
        }
        return null;
    }
}
