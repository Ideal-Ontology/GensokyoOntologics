package github.thelawf.gensokyoontology.common.world;

import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fluids.IFluidBlock;
import org.apache.logging.log4j.LogManager;

import java.util.function.Function;

public class TeleportHelper {
    public static void teleport(ServerPlayerEntity entity, ServerWorld destination, BlockPos pos) {

        if (canTeleport(destination, pos)) {
            entity.changeDimension(destination, new ITeleporter() {
                @Override
                public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld,
                                          float yaw, Function<Boolean, Entity> repositionEntity) {
                    entity = repositionEntity.apply(false);
                    entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
                    return entity;
                }
            });
        }
    }

    /** 获取目的地世界对应位置的方块状态，如果玩家位置是空气且玩家脚下的方块是固态方块则进行传送 */
    public static boolean canTeleport(ServerWorld destination, BlockPos pos) {
        if (destination == null)
            return false;

        BlockPos lowerPos = new BlockPos(pos.getX(), pos.getY()-1, pos.getZ());
        BlockPos upperPos = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
        BlockState eyeBlock = destination.getBlockState(upperPos);
        BlockState playerBlock = destination.getBlockState(pos);
        BlockState standBlock = destination.getBlockState(lowerPos);

        if (eyeBlock.equals(Blocks.AIR.getDefaultState()) && playerBlock.equals(Blocks.AIR.getDefaultState())) {
            if (standBlock.getBlock().equals(Blocks.AIR)) {
                destination.setBlockState(lowerPos, BlockRegistry.SAKURA_PLANKS.get().getDefaultState());
                LogManager.getLogger().info("Position Valid");
                return true;
            }
            else if (standBlock.getBlock() instanceof IFluidBlock) {
                destination.setBlockState(lowerPos, BlockRegistry.SAKURA_PLANKS.get().getDefaultState());
                LogManager.getLogger().info("Stand Position Has Fluids");
                return true;
            }
            else {
                return isValidPos(destination, pos, lowerPos, upperPos);
            }
        }
        return false;
    }

    public static boolean isValidPos (ServerWorld destination, BlockPos pos, BlockPos lowerPos, BlockPos upperPos) {
        return findValidPos(destination, pos, lowerPos, upperPos) == null;
    }

    public static BlockPos findValidPos(ServerWorld destination, BlockPos pos, BlockPos lowerPos, BlockPos upperPos) {
        for (int i = 0; i < 255; i++) {
            BlockPos playerPos = new BlockPos(pos.getX(), i, pos.getZ());
            BlockPos standPos = new BlockPos(lowerPos.getX(), i, lowerPos.getZ());
            BlockPos eyePos = new BlockPos(upperPos.getX(), i, lowerPos.getZ());
            if (!destination.getBlockState(standPos).getBlock().equals(Blocks.AIR) &&
                destination.getBlockState(playerPos).getBlock().equals(Blocks.AIR) &&
                    destination.getBlockState(eyePos).getBlock().equals(Blocks.AIR)){
                return playerPos;
            }
        }
        return null;
    }
}
