package github.thelawf.gensokyoontology.common.entity.spawn;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.world.GSKOWorldUtil;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

/** Reference From {@link net.minecraft.world.spawner.WanderingTraderSpawner WanderingTraderSpawner} in which the field is <br>
 * field_221249_d -> wanderingTraderSpawnDelay; <br>
 * field_221249_e -> wanderingTraderSpawnChance;
 */
public class LilyWhiteSpawner {

    public static void spawn(ServerWorld serverWorld, PlayerEntity player, BlockPos pos, int ticks, float chance) {
        Random random = new Random();
        if (player.ticksExisted % 10000 != 0)return;
        if (player.getEntityWorld().getDimensionKey() != GSKODimensions.GENSOKYO) return;
        EntityRegistry.LILY_WHITE.get().spawn(serverWorld, null, player, pos, SpawnReason.NATURAL, false, false);

        // if (validateCondition(serverWorld, ticks, random, chance) && validateSpawnPos(player, serverWorld)) {
        //     EntityRegistry.LILY_WHITE.get().spawn(serverWorld, null, null, pos, SpawnReason.NATURAL, false, false);
        // }
    }

    public static boolean validateCondition(ServerWorld serverWorld, int ticks, Random random, float chance) {
        return GSKOMathUtil.isBetween(ticks % 20000, 0, 10) && random.nextFloat() <= chance &&
                serverWorld.getEntities().noneMatch(entity -> entity.getType() == EntityRegistry.LILY_WHITE.get());
    }

    public static boolean validateSpawnPos(PlayerEntity player, ServerWorld world) {
        Random random = new Random();

        Vector3d lowerPos = DanmakuUtil.getRandomPos(player.getPositionVec(), new Vector3f(3f, 3f, 3f));

        boolean flag = false;

        for (int i = 0; i < 256; i++) {

            BlockPos pos = new BlockPos(lowerPos.getX(), i, lowerPos.getZ());
            BlockPos upperPos = new BlockPos(lowerPos.getX(), i+2, lowerPos.getZ());
            BlockPos middlePos = new BlockPos(lowerPos.getX(), i+1, lowerPos.getZ());

            BlockState eyeBlock = world.getBlockState(upperPos);
            BlockState middleBlock = world.getBlockState(middlePos);
            BlockState standBlock = world.getBlockState(pos);

            if (eyeBlock.equals(Blocks.AIR.getDefaultState()) && middleBlock.equals(Blocks.AIR.getDefaultState())) {
                if (!standBlock.getBlock().equals(Blocks.AIR)) {
                    flag = true;
                    break;
                }
            }
        }
        //player.sendMessage(new StringTextComponent("Flag is " + flag), player.getUniqueID());

        return flag && GSKOWorldUtil.isEntityInBiome(player, GSKOBiomes.HAKUREI_SHRINE_PRECINCTS_KEY);
    }
}
