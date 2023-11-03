package github.thelawf.gensokyoontology.common.world;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.tileentity.GapTileEntity;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.impl.TeleportCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.WoodlandMansionPieces;
import net.minecraft.world.gen.feature.structure.WoodlandMansionStructure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fluids.IFluidBlock;
import org.apache.logging.log4j.LogManager;

import java.util.function.Function;

public class TeleportHelper {
    public static void teleport(ServerPlayerEntity player, ServerWorld destination, BlockPos pos) {

        if (canTeleport(destination, pos)) {
            player.changeDimension(destination, new ITeleporter() {
                @Override
                public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld,
                                          float yaw, Function<Boolean, Entity> repositionEntity) {
                    BlockPos validPos = findValidPos(destination, pos).getSecond();

                    entity = repositionEntity.apply(false);
                    entity.setPosition(validPos.getX(), validPos.getY(), validPos.getZ());
                    return entity;
                }
            });
        }
    }

    public static void applyGapTeleport(ServerPlayerEntity player, ServerWorld destination, GapTileEntity gapTile) {
        BlockPos pos = gapTile.getDestinationPos();

        if (isInSameDimension(player.world.getDimensionKey(), destination.getDimensionKey())) {
            player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
            gapTile.setCooldown(400);
            return;
        }

        player.changeDimension(destination, new ITeleporter() {
            @Override
            public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                player.sendMessage(new StringTextComponent("Different Dimension Destination Pos: " + pos.getCoordinatesAsString()), player.getUniqueID());
                // player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
                entity = repositionEntity.apply(false);
                entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
                player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
                gapTile.setCooldown(400);
                return player;
            }

            @Override
            public boolean playTeleportSound(ServerPlayerEntity player, ServerWorld sourceWorld, ServerWorld destWorld) {
                return false;
            }
        });
    }

    public static void applyStructureTeleport(ServerWorld destination) {

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
                return isValidPos(destination, pos);
            }
        }
        return false;
    }

    public static boolean isValidPos (ServerWorld destination, BlockPos pos) {
        LogManager.getLogger().info("Other Condition is: " + findValidPos(destination, pos).getFirst());
        return findValidPos(destination, pos).getFirst();
    }

    public static Pair<Boolean, BlockPos> findValidPos(ServerWorld destination, BlockPos pos) {
        for (int i = 0; i < 255; i++) {
            BlockPos playerPos = new BlockPos(pos.getX(), i, pos.getZ());
            BlockPos standPos = new BlockPos(pos.down().getX(), i, pos.down().getZ());
            BlockPos eyePos = new BlockPos(pos.up().getX(), i, pos.up().getZ());
            if (!destination.getBlockState(standPos).getBlock().equals(Blocks.AIR) &&
                    destination.getBlockState(playerPos).getBlock().equals(Blocks.AIR) &&
                    destination.getBlockState(eyePos).getBlock().equals(Blocks.AIR)) {
                return Pair.of(true, playerPos);
            }
        }
        return Pair.of(false, pos);
    }

    private static boolean isInSameDimension(RegistryKey<World> departureWorld, RegistryKey<World> destination) {
        return departureWorld == destination;
    }
}
