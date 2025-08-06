package github.thelawf.gensokyoontology.common.world;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.tileentity.GapTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.impl.LocateBiomeCommand;
import net.minecraft.command.impl.TeleportCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.structure.WoodlandMansionPieces;
import net.minecraft.world.gen.feature.structure.WoodlandMansionStructure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fluids.IFluidBlock;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class TeleportHelper {
    public static void teleport(ServerPlayerEntity player, ServerWorld destination, BlockPos pos) {
        // Pair<Boolean, BlockPos> pair = findHumanVillage(destination, pos);
        // if (!pair.getFirst()) return;

        if (canTeleport(player, destination, pos)) {
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
        // if (isInSameDimension(player.world.getDimensionKey(), destination.getDimensionKey())) {
        //     player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        //     // player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
        //     gapTile.setCooldown(400);
        //     return;
        // }

        player.changeDimension(destination, new ITeleporter() {
            @Override
            public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                entity = repositionEntity.apply(false);
                entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
                player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
                gapTile.setCooldown(400);
                return player;
            }
        });
    }

    public static void applyStructureTeleport(ServerWorld destination) {

    }

    /**
     * 获取目的地世界对应位置的方块状态，如果玩家位置是空气且玩家脚下的方块是固态方块则进行传送
     */
    public static boolean canTeleport(ServerPlayerEntity player, ServerWorld destination, BlockPos pos) {
        if (destination == null)
            return false;

        BlockPos standPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        BlockPos eyePos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        BlockState eyeBlock = destination.getBlockState(eyePos);
        BlockState legBlock = destination.getBlockState(pos);
        BlockState standBlock = destination.getBlockState(standPos);

        if (eyeBlock.equals(Blocks.AIR.getDefaultState()) && legBlock.equals(Blocks.AIR.getDefaultState())) {
            if (standBlock.getBlock().equals(Blocks.AIR)) {
                // GSKOUtil.showChatMsg(player, "Pos Valid", 1);
                // destination.setBlockState(standPos, BlockRegistry.SAKURA_PLANKS.get().getDefaultState());
                return setBlocks(destination, standPos);
            } else if (standBlock.getBlock() instanceof IFluidBlock) {
                // destination.setBlockState(standPos, BlockRegistry.SAKURA_PLANKS.get().getDefaultState());
                // GSKOUtil.showChatMsg(player, "Stand Pos is Fluid", 1);
                return setBlocks(destination, standPos);
            }
            return true;
        } else {
            if (standBlock.getBlock().equals(Blocks.AIR)) {
                return clearAndSetBlocks(destination, standPos, pos);
            }
            return clearBlocks(destination, pos);
        }
    }

    private static boolean clearAndSetBlocks(ServerWorld destination, BlockPos standPos, BlockPos legPos) {
        clearBlocks(destination, legPos);
        setBlocks(destination, standPos);
        return true;
    }

    private static boolean clearBlocks(ServerWorld destination, BlockPos pos) {
        final BlockState air = Blocks.AIR.getDefaultState();
        destination.setBlockState(pos, air);

        for (int x = -1; x < 1; x++) {
            for (int z = -1; z < 1; z++) {
                for (int y = 0; y < 3; y++) {
                    destination.setBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z), air);
                }
            }
        }

        destination.setBlockState(pos, Blocks.TORCH.getDefaultState());
        return true;
    }

    private static boolean setBlocks(ServerWorld destination, BlockPos pos){
        final BlockState sakuraPlanks = BlockRegistry.SAKURA_PLANKS.get().getDefaultState();
        for (int x = -1; x < 1; x++) {
            for (int z = -1; z < 1; z++) {
                destination.setBlockState(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z), sakuraPlanks);
            }
        }
        return true;
    }

    private static boolean isValidPos(ServerWorld destination, BlockPos pos) {
        LogManager.getLogger().info("Other Condition is: " + findValidPos(destination, pos).getFirst());
        return findValidPos(destination, pos).getFirst();
    }

    private static Pair<Boolean, BlockPos> findValidPos(ServerWorld destination, BlockPos pos) {
        for (int i = 0; i < 255; i++) {
            BlockPos legPos = new BlockPos(pos.getX(), i, pos.getZ());
            BlockPos standPos = new BlockPos(pos.down().getX(), i, pos.down().getZ());
            BlockPos eyePos = new BlockPos(pos.up().getX(), i, pos.up().getZ());
            if (!destination.getBlockState(standPos).getBlock().equals(Blocks.AIR) &&
                    destination.getBlockState(legPos).getBlock().equals(Blocks.AIR) &&
                    destination.getBlockState(eyePos).getBlock().equals(Blocks.AIR)) {
                return Pair.of(true, legPos);
            }
        }
        return Pair.of(false, pos);
    }

    private static boolean isInSameDimension(RegistryKey<World> departureWorld, RegistryKey<World> destination) {
        return departureWorld == destination;
    }

    private static Pair<Boolean, BlockPos> findHumanVillage(ServerWorld serverWorld, BlockPos pos){
        AtomicReference<Pair<Boolean, BlockPos>> pairRef = new AtomicReference<>();
        pairRef.set(Pair.of(false, null));

        if (serverWorld.getDimensionKey() != GSKODimensions.GENSOKYO) return pairRef.get();
        serverWorld.getServer().getDynamicRegistries().getRegistry(Registry.BIOME_KEY)
                .getOptionalValue(GSKOBiomes.HUMAN_VILLAGE_KEY).ifPresent(biome -> {
                    pairRef.set(Pair.of(true, serverWorld.getBiomeLocation(biome, pos, 6400, 8)));
                });
        return pairRef.get();
    }

}
