package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.api.util.INBTRunnable;
import github.thelawf.gensokyoontology.api.util.INBTWriter;
import github.thelawf.gensokyoontology.common.block.GapBlock;
import github.thelawf.gensokyoontology.common.tileentity.GapTileEntity;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class GapItem extends BlockItem implements INBTWriter, INBTRunnable {
    public GapItem(Properties properties) {
        super(BlockRegistry.GAP_BLOCK.get(), properties);
    }

    @Override
    protected boolean onBlockPlaced(@NotNull BlockPos pos, @NotNull World worldIn, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {

        if (player == null) return super.onBlockPlaced(pos, worldIn, player, stack, state);

        if (player.getHeldItemMainhand().getItem() == this) {

            if (stack.hasTag()) {
                CompoundNBT nbt = stack.getTag();
                setBlockTileSecond(player, worldIn, pos, nbt);
                stack.setTag(new CompoundNBT());
                stack.shrink(1);
                return super.onBlockPlaced(pos, worldIn, player, stack, state);

            }
        }

        setBlockTileFirst(worldIn, player, pos);
        return super.onBlockPlaced(pos, worldIn, player, stack, state);
    }

    private void setBlockTileFirst(World worldIn, PlayerEntity player, BlockPos pos) {
        CompoundNBT itemNBT = new CompoundNBT();
        itemNBT.putBoolean("is_first_placement", true);
        itemNBT.putLong("first_pos", pos.toLong());

        if (!worldIn.isRemote) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            RegistryKey<World> departureWorld = serverWorld.getDimensionKey();
            itemNBT.putString("departure_world", departureWorld.getLocation().toString());

            ItemStack itemStack = player.getHeldItemMainhand();
            itemStack.setTag(itemNBT);

            itemStack.setCount(1);
            mergeBoolean(itemStack, "is_first_placement", false);
        }

        worldIn.setBlockState(pos, BlockRegistry.GAP_BLOCK.get().getDefaultState());
        if (worldIn.getTileEntity(pos) instanceof GapTileEntity) {
            GapTileEntity gapTile = (GapTileEntity) worldIn.getTileEntity(pos);
            if (gapTile != null) {
                gapTile.markDirty();
            }
        }
    }

    private void setBlockTileSecond(PlayerEntity player, World worldIn, BlockPos pos, CompoundNBT itemNBT) {
        worldIn.setBlockState(pos, BlockRegistry.GAP_BLOCK.get().getDefaultState());

        if (!worldIn.isRemote && worldIn.getTileEntity(pos) instanceof GapTileEntity) {

            RegistryKey<World> departureKey = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(itemNBT.getString("departure_world")));
            if (worldIn.getServer() == null) return;
            ServerWorld depatureWorld = worldIn.getServer().getWorld(departureKey);
            if (depatureWorld == null) return;

            ServerWorld arrivalWorld = (ServerWorld) worldIn;
            RegistryKey<World> arrivalKey = arrivalWorld.getDimensionKey();

            GapTileEntity secondPlacedSukima = (GapTileEntity) arrivalWorld.getTileEntity(pos);
            GapTileEntity firstPlacedSukima = (GapTileEntity) depatureWorld.getTileEntity(
                    BlockPos.fromLong(itemNBT.getLong("first_pos")));

            if (secondPlacedSukima != null) {
                player.sendMessage(new StringTextComponent("2nd Gap is Present"), player.getUniqueID());
                secondPlacedSukima.setDestinationPos(pos);
                secondPlacedSukima.setDestinationWorld(departureKey);
                secondPlacedSukima.markDirty();

            }
            if (firstPlacedSukima != null) {
                player.sendMessage(new StringTextComponent("1st Gap is Present"), player.getUniqueID());
                firstPlacedSukima.setDestinationPos(BlockPos.fromLong(itemNBT.getLong("first_pos")));
                firstPlacedSukima.setDestinationWorld(arrivalKey);
                firstPlacedSukima.markDirty();
            }
        }
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.getTag() != null && stack.getTag().contains("first_pos")) {
            CompoundNBT nbt = stack.getTag();
            if (nbt.contains("first_pos")) {
                BlockPos pos = BlockPos.fromLong(nbt.getLong("first_pos"));
                tooltip.add(new StringTextComponent("第一处隙间设置为: "+ pos.getX() +", "+ pos.getY() + ", " + pos.getZ()));
            }
            if (nbt.contains("departure_world")) {
                tooltip.add(new TranslationTextComponent(nbt.getString("departure_world")));
            }
            if (nbt.contains("is_first_placement")) {
                tooltip.add(new StringTextComponent("是否是第一次点击：" + nbt.getBoolean("is_first_placement")));
            }
        }
    }
}
