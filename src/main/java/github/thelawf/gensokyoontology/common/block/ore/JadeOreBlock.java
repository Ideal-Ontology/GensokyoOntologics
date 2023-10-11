package github.thelawf.gensokyoontology.common.block.ore;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

import java.util.Random;

public class JadeOreBlock extends OreBlock {
    public JadeOreBlock() {
        super(Properties.from(Blocks.NETHERITE_BLOCK).harvestLevel(5));
    }

    public static ItemStack getItemToDrop(World worldIn, PlayerEntity playerIn, RegistryKey<World> dimension) {
        if (!worldIn.isRemote) {
            int probability = new Random().nextInt(10000);
            if (dimension.equals(GSKODimensions.GENSOKYO)) {
                return getItemToDrop(worldIn, 50, 180, 470, 3000);
            }
            else {
                playerIn.sendMessage(new TranslationTextComponent("msg."+ GensokyoOntology.MODID +"item_use.jade_cut_failed"),
                        playerIn.getUniqueID());
                return new ItemStack(Items.COBBLESTONE);
            }
        }
        return ItemStack.EMPTY;
    }

    /**
     * 玉石矿在不同的世界会存在着不同的掉落概率
     */
    public static ItemStack getItemToDrop(World worldIn, int sss, int ss, int s, int a){
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
        return ItemStack.EMPTY;
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
