package github.thelawf.gensokyoontology.data.recipe;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;


public interface IJigsawRecipe extends IRecipe<IInventory> {
    int getUnitCount();
    float getPowerConsumption();
    NonNullList<Block> getJigsawPattern();

    /**
     * @param center 拼图图案中心方块的世界坐标
     */
    default boolean matchesIncludePos(ServerWorld world, IInventory inv, BlockPos center){
        BlockPos.Mutable pos = new BlockPos.Mutable(center.getX() -2, center.getY(), center.getZ()-2);
        int matches = 0;
        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {
                pos.add(x, 0, z);
                if (this.getJigsawPattern().get(x * 5 + z).equals(world.getBlockState(pos.add(x, 0, z)).getBlock())) matches++;
            }
        }
        return matches == 25;
    }
}
