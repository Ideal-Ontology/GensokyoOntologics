package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.common.block.GapBlock;
import github.thelawf.gensokyoontology.common.tileentity.GapTileEntity;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GapItem extends Item {
    public GapItem(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResultType onItemUse(ItemUseContext context) {
        World worldIn = context.getWorld();
        BlockPos pos = context.getPos().up();

        CompoundNBT itemNBT = new CompoundNBT();
        itemNBT.putBoolean("is_first_placement", true);
        itemNBT.putLong("first_pos", pos.toLong());

        if (!worldIn.isRemote && context.getPlayer() != null) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            RegistryKey<World> departureWorld = serverWorld.getDimensionKey();
            itemNBT.putString("departure_world", departureWorld.getLocation().toString());

            PlayerEntity player = context.getPlayer();
            ItemStack itemStack = player.getHeldItemMainhand();
            itemStack.setTag(itemNBT);

            if (itemNBT.getBoolean("is_first_placement")) {
                itemStack.setCount(1);

                itemNBT.remove("is_first_placement");
                itemNBT.putBoolean("is_first_placement", false);
                itemStack.setTag(itemNBT);
                setBlockTileFirst(worldIn, pos);
            }
            else {
                itemStack.setTag(null);
                setBlockTileSecond(worldIn, pos, itemNBT, serverWorld, departureWorld);
            }
        }
        return super.onItemUse(context);
    }

    private void setBlockTileFirst(World worldIn, BlockPos pos) {
        worldIn.setBlockState(pos, BlockRegistry.GAP_BLOCK.get().getDefaultState());
    }

    private void setBlockTileSecond(World worldIn, BlockPos pos, CompoundNBT itemNBT, ServerWorld serverWorld, RegistryKey<World> departureWorld) {
        if (worldIn.getTileEntity(pos) instanceof GapTileEntity) {
            CompoundNBT tileNBT = new CompoundNBT();
            GapTileEntity secondPlacedSukima = (GapTileEntity) worldIn.getTileEntity(pos);
            GapTileEntity firstPlacedSukima = (GapTileEntity) worldIn.getTileEntity(
                    BlockPos.fromLong(itemNBT.getLong("departure_world")));

            RegistryKey<World> arrivalWorld = serverWorld.getDimensionKey();
            tileNBT.putString("ArrivalWorld", arrivalWorld.getLocation().toString());

            if (secondPlacedSukima != null) {
                secondPlacedSukima.setDestinationPos(pos);
                secondPlacedSukima.setDestinationWorld(departureWorld);

            }
            if (firstPlacedSukima != null) {
                firstPlacedSukima.setDestinationPos(BlockPos.fromLong(itemNBT.getLong("departure_world")));
                firstPlacedSukima.setDestinationWorld(arrivalWorld);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
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
