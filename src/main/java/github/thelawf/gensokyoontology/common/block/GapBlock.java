package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.api.util.INBTRunnable;
import github.thelawf.gensokyoontology.api.util.INBTWriter;
import github.thelawf.gensokyoontology.common.tileentity.GapTileEntity;
import github.thelawf.gensokyoontology.common.world.TeleportHelper;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GapBlock extends Block implements INBTWriter, INBTRunnable {

    private BlockPos tilePos;

    // public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape SUKIMA_PLANE_X = Block.makeCuboidShape(-4.0D, 0.0D, 4.0D, 20.0D, 16.0D, 4.0D);
    protected static final VoxelShape SUKIMA_PLANE_Z = Block.makeCuboidShape(4.0D, 0.0D, -4.0D, 4.0D, 16.0D, 20.0D);
    @Override
    @NotNull
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(@NotNull BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
        return SUKIMA_PLANE_X;
    }

    public GapBlock() {
        super(Properties.from(Blocks.NETHER_PORTAL));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GapTileEntity();
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);
        // if (worldIn.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && random.nextInt(2000) < worldIn.getDifficulty().getId()) {
        //
        // }
    }

    @Override
    public void onBlockPlacedBy(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (!(placer instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) placer;

        if (stack.getTag() != null) {
            CompoundNBT nbt = stack.getTag();
            setBlockTileSecond(player, worldIn, pos, nbt);
            stack.setTag(new CompoundNBT());
            stack.shrink(1);
            return;
        }

        setBlockTileFirst(worldIn, player, pos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);

        if (!worldIn.isRemote && entityIn instanceof ServerPlayerEntity) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entityIn;

            if (checkCanTeleport(serverWorld, pos)){
                GapTileEntity departureGap = getGapTile(serverWorld, pos);
                ServerWorld destinationWorld = worldIn.getServer().getWorld(departureGap.getDestinationWorld());
                if (destinationWorld != null) {
                    // tryTeleport(serverWorld, destinationWorld, serverPlayer, departureGap);
                    GapTileEntity arrivalGap = getGapTile(destinationWorld, departureGap.getDestinationPos());
                    arrivalGap.setCooldown(400);
                    TeleportHelper.applyGapTeleport(serverPlayer, destinationWorld, departureGap);
                }
            }

        }
    }

    private void setBlockTileFirst(World worldIn, PlayerEntity player, BlockPos firstPos) {
        CompoundNBT itemNBT = new CompoundNBT();
        itemNBT.putBoolean("is_first_placement", true);
        itemNBT.putLong("first_pos", firstPos.toLong());

        if (!worldIn.isRemote) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            RegistryKey<World> departureWorld = serverWorld.getDimensionKey();
            itemNBT.putString("departure_world", departureWorld.getLocation().toString());
            itemNBT.putInt("pos_x", firstPos.getX());
            itemNBT.putInt("pos_y", firstPos.getY());
            itemNBT.putInt("pos_z", firstPos.getZ());

            ItemStack itemStack = player.getHeldItemMainhand();
            itemStack.setTag(itemNBT);
            itemStack.grow(1);
        }

        worldIn.setBlockState(firstPos, BlockRegistry.GAP_BLOCK.get().getDefaultState());
        if (worldIn.getTileEntity(firstPos) instanceof GapTileEntity) {
            GapTileEntity gapTile = (GapTileEntity) worldIn.getTileEntity(firstPos);
            if (gapTile != null) {
                gapTile.markDirty();
            }
        }
    }

    private void setBlockTileSecond(PlayerEntity player, World worldIn, BlockPos secondPos, @NotNull CompoundNBT itemNBT) {
        worldIn.setBlockState(secondPos, BlockRegistry.GAP_BLOCK.get().getDefaultState());

        if (!worldIn.isRemote && worldIn.getTileEntity(secondPos) instanceof GapTileEntity) {

            RegistryKey<World> departureKey = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(itemNBT.getString("departure_world")));
            if (worldIn.getServer() == null) return;
            ServerWorld depatureWorld = worldIn.getServer().getWorld(departureKey);
            if (depatureWorld == null) return;

            ServerWorld arrivalWorld = (ServerWorld) worldIn;
            RegistryKey<World> arrivalKey = arrivalWorld.getDimensionKey();
            BlockPos firstPos = new BlockPos(getNBTInt(itemNBT, "pos_x"), getNBTInt(itemNBT, "pos_y"), getNBTInt(itemNBT, "pos_z"));

            GapTileEntity secondPlacedSukima = (GapTileEntity) arrivalWorld.getTileEntity(secondPos);
            GapTileEntity firstPlacedSukima = (GapTileEntity) depatureWorld.getTileEntity(firstPos);

            if (secondPlacedSukima != null) {
                // player.sendMessage(new StringTextComponent("2nd Gap is Present"), player.getUniqueID());
                secondPlacedSukima.setDestinationPos(firstPos);
                secondPlacedSukima.setDestinationWorld(departureKey);
                secondPlacedSukima.markDirty();

            }
            if (firstPlacedSukima != null) {
                // player.sendMessage(new StringTextComponent("1st Gap is Present"), player.getUniqueID());
                firstPlacedSukima.setDestinationPos(secondPos);
                firstPlacedSukima.setDestinationWorld(arrivalKey);
                firstPlacedSukima.markDirty();
            }
        }

    }


    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.getTag() != null && stack.getTag().contains("first_pos")) {
            CompoundNBT nbt = stack.getTag();
            if (nbt.contains("first_pos")) {
                BlockPos pos = BlockPos.fromLong(nbt.getLong("first_pos"));
                tooltip.add(new StringTextComponent("第一处隙间设置为: "+ pos.getCoordinatesAsString()));
            }
            if (nbt.contains("departure_world")) {
                tooltip.add(new TranslationTextComponent(nbt.getString("departure_world")));
            }
            if (nbt.contains("is_first_placement")) {
                tooltip.add(new StringTextComponent("是否是第一次点击：" + nbt.getBoolean("is_first_placement")));
            }
        }
    }

    public boolean checkCanTeleport(ServerWorld serverWorld, BlockPos depaturePos) {
        if (serverWorld.getTileEntity(depaturePos) instanceof GapTileEntity) {
            GapTileEntity departureGap = (GapTileEntity) serverWorld.getTileEntity(depaturePos);
            return departureGap != null && departureGap.getCooldown() == 0;
        }
        return false;
    }

    public GapTileEntity getGapTile(ServerWorld serverWorld, BlockPos pos) {
        return (GapTileEntity) serverWorld.getTileEntity(pos);
    }

    private static boolean isInSameDimension(RegistryKey<World> departureWorld, RegistryKey<World> destination) {
        return departureWorld == destination;
    }

    public GapTileEntity getDestinationSukimaTile(ServerWorld serverWorld, BlockPos pos, RegistryKey<World> destination) {
        ServerWorld destinationWorld = serverWorld.getServer().getWorld(destination);
        if (destinationWorld != null && destinationWorld.getTileEntity(pos) instanceof GapTileEntity) {
            return (GapTileEntity) destinationWorld.getTileEntity(pos);
        }
        return null;
    }

    private void tryTeleport(ServerWorld departureWorld, ServerWorld destinationWorld, ServerPlayerEntity serverPlayer, GapTileEntity departureGap) {

    }
}
