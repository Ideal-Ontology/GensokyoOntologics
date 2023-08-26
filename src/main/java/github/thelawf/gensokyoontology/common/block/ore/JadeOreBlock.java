package github.thelawf.gensokyoontology.common.block.ore;

import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class JadeOreBlock extends OreBlock {
    public JadeOreBlock() {
        super(Properties.from(Blocks.NETHERITE_BLOCK).harvestLevel(5));
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
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
}
