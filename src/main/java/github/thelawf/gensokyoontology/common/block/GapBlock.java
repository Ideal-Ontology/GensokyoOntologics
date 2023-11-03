package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.INBTReader;
import github.thelawf.gensokyoontology.api.util.INBTRunnable;
import github.thelawf.gensokyoontology.api.util.INBTWriter;
import github.thelawf.gensokyoontology.common.tileentity.GapTileEntity;
import github.thelawf.gensokyoontology.common.world.TeleportHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

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
    public void onBlockPlacedBy(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        final String isFirstPlacement = "is_first_placement";
        final String depatureWorld = "depature_world";
        final String depaturePos = "depature_pos";

        final String arrivalWorld = "arrival_world";
        final String arrivalPos = "arrival_pos";

        // 第一次放置时需要判断物品内部是否含有tag，如果没有tag则添加对应的tag
        writeBooleanIf(itemStack -> containsKey(stack, isFirstPlacement), stack, isFirstPlacement, true);
        writeStringIf(itemStack -> containsKey(stack, depatureWorld), stack, depatureWorld, worldIn.getDimensionKey().getLocation().toString());
        writeBlockPosIf(itemStack -> containsKey(stack, depaturePos), stack, depaturePos, pos.up(1));

        if (!(placer instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) placer;

        /* 第一次放置时 is_first_placement 为 true，所以调用这里的runIf(), 增加一个物品，同时将is_first_placement
         * 设置为 false，nbt内部的合并方法会将旧值替换为新值
         */
        if (getNBTBoolean(stack, isFirstPlacement) && !player.isCreative()) {
            stack.grow(1);
            mergeBoolean(stack, isFirstPlacement, false);
            return;
        }

        // runIf(itemStack -> getNBTBoolean(stack, isFirstPlacement) && player.isCreative(), stack, () -> mergeBoolean(stack, isFirstPlacement, false));

        /* 第二次放置时 is_first_placement 为 false，所以调用这里的runIf()，在坐标
         */
        runIf(itemStack -> !getNBTBoolean(stack, isFirstPlacement), stack, () -> {
            TileEntity firstTile = worldIn.getTileEntity(getNBTBlockPos(stack, depaturePos));
            if (firstTile instanceof GapTileEntity && getNBTBlockPos(stack, depaturePos) != BlockPos.ZERO &&
                    !Objects.equals(getNBTString(stack, depatureWorld), "NULL")) {
                GapTileEntity depatureGap = (GapTileEntity) firstTile;
                player.sendMessage(new StringTextComponent(depatureGap.getPos().getCoordinatesAsString()), player.getUniqueID());
                depatureGap.setDestinationPos(getNBTBlockPos(stack, depaturePos));
                depatureGap.setDestinationWorld(RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
                        new ResourceLocation(getNBTString(stack, depatureWorld))));
            }

            mergeString(stack, arrivalWorld, worldIn.getDimensionKey().getLocation().toString());
            mergeBlockPos(stack, arrivalPos, pos);

            TileEntity secondTile = worldIn.getTileEntity(pos);
            if (secondTile instanceof GapTileEntity && getNBTBlockPos(stack, depaturePos) != BlockPos.ZERO &&
                    !Objects.equals(getNBTString(stack, depatureWorld), "NULL")) {
                GapTileEntity arrivalGap = (GapTileEntity) secondTile;
                arrivalGap.setDestinationPos(pos);
                arrivalGap.setDestinationWorld(worldIn.getDimensionKey());
            }
        });

        // 将物品NBT设置为空，以开始下一次的循环
        stack.setTag(new CompoundNBT());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (!worldIn.isRemote && entityIn instanceof ServerPlayerEntity) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entityIn;
            if (isDestinationValid(serverWorld, pos) && !serverPlayer.hasPortalCooldown()){
                GapTileEntity departureGap = getGapTile(serverWorld, pos);
                ServerWorld destinationWorld = worldIn.getServer().getWorld(departureGap.getDestinationWorld());
                TeleportHelper.applyGapTeleport(serverPlayer, destinationWorld, departureGap.getDestinationPos());
                serverPlayer.setPortalCooldown();
            }

        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        final String t = "tooltip.";
        final String gap = ".gap_block";

        final String isFirstPlacement = gap + ".is_first_placement";
        final String depatureWorld = gap + ".depature_world";
        final String depaturePos = gap + ".depature_pos";

        final String arrivalWorld = gap + ".arrival_world";
        final String arrivalPos = gap + ".arrival_pos";

        runIf(itemStack -> containsKey(stack, isFirstPlacement), stack, () ->
                tooltip.add(GensokyoOntology.withTranslation(t, isFirstPlacement)));

        runIf(itemStack -> containsKey(stack, depatureWorld), stack, () ->
                tooltip.add(GensokyoOntology.withTranslation(t, depatureWorld)));

        runIf(itemStack -> containsKey(stack, depaturePos), stack, () -> {
            BlockPos pos = getNBTBlockPos(stack, depaturePos);
            tooltip.add(GensokyoOntology.withTranslation(t, depaturePos));
            tooltip.add(new StringTextComponent(pos.getX() +", "+ pos.getY() + ", " + pos.getZ()));
        });

        runIf(itemStack -> containsKey(stack, arrivalWorld), stack, () ->
                tooltip.add(GensokyoOntology.withTranslation(t, arrivalWorld)));

        runIf(itemStack -> containsKey(stack, arrivalPos), stack, () -> {
            BlockPos pos = getNBTBlockPos(stack, arrivalPos);
            tooltip.add(GensokyoOntology.withTranslation(t, arrivalPos));
            tooltip.add(new StringTextComponent(pos.getX() +", "+ pos.getY() + ", " + pos.getZ()));
        });
    }

    public boolean isDestinationValid(ServerWorld serverWorld, BlockPos depaturePos) {
        if (serverWorld.getTileEntity(depaturePos) instanceof GapTileEntity) {
            GapTileEntity departureGap = (GapTileEntity) serverWorld.getTileEntity(depaturePos);
            return departureGap != null && departureGap.isAllowTeleport();
        }
        return false;
    }

    public GapTileEntity getGapTile(ServerWorld serverWorld, BlockPos pos) {
        return (GapTileEntity) serverWorld.getTileEntity(pos);
    }

    public GapTileEntity getDestinationSukimaTile(ServerWorld serverWorld, BlockPos pos, RegistryKey<World> destination) {
        ServerWorld destinationWorld = serverWorld.getServer().getWorld(destination);
        if (destinationWorld != null && destinationWorld.getTileEntity(pos) instanceof GapTileEntity) {
            return (GapTileEntity) destinationWorld.getTileEntity(pos);
        }
        return null;
    }

}
