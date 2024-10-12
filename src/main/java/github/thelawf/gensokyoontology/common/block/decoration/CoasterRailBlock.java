package github.thelawf.gensokyoontology.common.block.decoration;

import com.github.tartaricacid.touhoulittlemaid.mclib.math.functions.limit.Min;
import github.thelawf.gensokyoontology.client.gui.screen.RailDashboardScreen;
import github.thelawf.gensokyoontology.common.container.RailAdjustGUI;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CAdjustRailPacket;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CoasterRailBlock extends Block {
    public static final HashMap<Vector2f, Float> DIRECTION_MAPPING = initMapping();
    public static final VoxelShape SHAPE = makeCuboidShape(0,0,0, 16, 6, 16);
    public static HashMap<Vector2f, Float> initMapping() {
        HashMap<Vector2f, Float> map = new HashMap<>();
        map.put(new Vector2f(-22.5F, 22.5F), 270F);
        map.put(new Vector2f(22.5F, 45F + 22.5F), 45F);
        map.put(new Vector2f(45F + 22.5F, 90F + 22.5F), 0F);
        map.put(new Vector2f(90F + 22.5F, 135F + 22.5F), 135F);
        map.put(new Vector2f(135F + 22.5F, 180F), 90F);
        map.put(new Vector2f(-180F, -180F + 22.5F), 90F);
        map.put(new Vector2f(-180F + 22.5F, -135F + 22.5F), 225F);
        map.put(new Vector2f(-135F + 22.5F, -90F + 22.5F), 180F);
        map.put(new Vector2f(-90F + 22.5F, -45F + 22.5F), 315F);
        return map;
    }
    public CoasterRailBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.or(SHAPE);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new RailTileEntity();
    }

    @Override
    public void onBlockPlacedBy(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!(worldIn.getTileEntity(pos) instanceof RailTileEntity)) return;
        RailTileEntity railTile = (RailTileEntity) worldIn.getTileEntity(pos);
        if (railTile == null) return;
        if (placer == null) return;

        Vector2f rotation = GSKOMathUtil.toYawPitch(placer.getLookVec());
        float yaw = 0F;
        for (Map.Entry<Vector2f, Float> entry : DIRECTION_MAPPING.entrySet()) {
            if (rotation.x > entry.getKey().x && rotation.x <= entry.getKey().y) {
                yaw = entry.getValue();
                break;
            }
        }
        railTile.setYaw(yaw);
        GSKOUtil.log(this.getClass(), rotation.x + " -> " + railTile.getYaw());
    }

    @Override
    @NotNull
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos,
                                             @NotNull PlayerEntity player, @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
        if (!(worldIn.getTileEntity(pos) instanceof RailTileEntity)) return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        RailTileEntity railTile = (RailTileEntity) worldIn.getTileEntity(pos);
        if (railTile == null) return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);

        CAdjustRailPacket packet = new CAdjustRailPacket(railTile.write(new CompoundNBT()));
        GSKONetworking.sendToClientPlayer(packet, player);
        GSKONetworking.CHANNEL.sendToServer(packet);

        if (worldIn.isRemote && player.getHeldItem(handIn).getItem() == ItemRegistry.RAIL_WRENCH.get()) {
            new RailDashboardScreen(railTile.getRoll(), railTile.getYaw(), railTile.getPitch()).open();
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

}
