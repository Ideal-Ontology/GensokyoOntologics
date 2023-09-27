package github.thelawf.gensokyoontology.common.block.ore;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class JadeOreBlock extends OreBlock {
    public JadeOreBlock() {
        super(Properties.from(Blocks.NETHERITE_BLOCK).harvestLevel(5));
    }

    public static ItemStack getItemToDrop(World worldIn, ItemStack prevStack) {
        if (!worldIn.isRemote) {
            int probability = new Random().nextInt(10000);
            if (worldIn.getDimensionKey().equals(GSKODimensions.GENSOKYO)) {
                if (probability <= 50) {
                    return new ItemStack(ItemRegistry.JADE_LEVEL_SSS.get());
                }
                else if (probability > 51 && probability <= 300) {
                    return new ItemStack(ItemRegistry.JADE_LEVEL_SS.get());
                }
                else if (probability > 301 && probability <= 1050) {
                    return new ItemStack(ItemRegistry.JADE_LEVEL_S.get());
                }
                else if (probability > 1051 && probability <= 3000) {
                    return new ItemStack(ItemRegistry.JADE_LEVEL_A.get());
                }
                else {
                    return new ItemStack(ItemRegistry.JADE_LEVEL_B.get());
                }
            }
        }
        return prevStack;
    }

    public static ItemStack getItemToDrop(World worldIn,ItemStack prevStack, int sss, int ss, int s, int a){
        if (!worldIn.isRemote) {
            int probability = new Random().nextInt(10000);
            if (worldIn.getDimensionKey().equals(GSKODimensions.GENSOKYO)) {
                if (probability <= sss) {
                    return new ItemStack(ItemRegistry.JADE_LEVEL_SSS.get());
                }
                else if (probability > sss + 1 && probability <= ss) {
                    return new ItemStack(ItemRegistry.JADE_LEVEL_SS.get());
                }
                else if (probability > ss + 1 && probability <= s) {
                    return new ItemStack(ItemRegistry.JADE_LEVEL_S.get());
                }
                else if (probability > s + 1 && probability <= a) {
                    return new ItemStack(ItemRegistry.JADE_LEVEL_A.get());
                }
                else {
                    return new ItemStack(ItemRegistry.JADE_LEVEL_B.get());
                }
            }
        }
        return prevStack;
    }

    public static void spawnDropByWeight(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote) {
            int probability = new Random().nextInt(100);
            if (worldIn.getDimensionKey().equals(GSKODimensions.GENSOKYO)) {
                if (probability <= 5) {
                    spawnDrops(state, worldIn, pos, null, player, new ItemStack(ItemRegistry.JADE_LEVEL_SSS.get()));
                }
                else if (probability > 6 && probability <= 20) {
                    spawnDrops(state, worldIn, pos, null, player, new ItemStack(ItemRegistry.JADE_LEVEL_SS.get()));
                }
                else if (probability > 20 && probability <= 45) {
                    spawnDrops(state, worldIn, pos, null, player, new ItemStack(ItemRegistry.JADE_LEVEL_S.get()));
                }
                else if (probability > 45 && probability <= 80) {
                    spawnDrops(state, worldIn, pos, null, player, new ItemStack(ItemRegistry.JADE_LEVEL_A.get()));
                }
                else {
                    spawnDrops(state, worldIn, pos, null, player, new ItemStack(ItemRegistry.JADE_LEVEL_B.get()));
                }
                player.addStat(Stats.BLOCK_MINED.get(BlockRegistry.JADE_ORE.get()), 1);
            }
        }
    }

    // @Override
    // public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
    //     super.onBlockHarvested(worldIn, pos, state, player);
    //     spawnDropByWeight(worldIn, pos, state, player);
    // }
}
