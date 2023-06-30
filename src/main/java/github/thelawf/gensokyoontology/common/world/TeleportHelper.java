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
        BlockState blockState = destination.getBlockState(pos);
        BlockState standBlock = destination.getBlockState(lowerPos);

        if (eyeBlock.equals(Blocks.AIR.getDefaultState()) && blockState.equals(Blocks.AIR.getDefaultState())) {
            if (standBlock.getBlock().equals(Blocks.AIR)) {
                destination.setBlockState(lowerPos, BlockRegistry.SAKURA_PLANKS.get().getDefaultState());
                return true;
            }
            else if (standBlock.getBlock() instanceof IFluidBlock) {
                destination.setBlockState(lowerPos, BlockRegistry.SAKURA_PLANKS.get().getDefaultState());
                return true;
            }
            else {
                return true;
            }
        }
        return false;
    }
}
