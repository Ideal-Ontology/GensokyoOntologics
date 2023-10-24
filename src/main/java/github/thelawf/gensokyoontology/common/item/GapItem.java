package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.common.tileentity.GapTileEntity;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class GapItem extends Item {
    public GapItem(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResultType onItemUse(ItemUseContext context) {
        World worldIn = context.getWorld();
        BlockPos pos = context.getPos().up();

        CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean("is_first_placement", true);
        nbt.putLong("FirstPos", pos.toLong());

        if (!worldIn.isRemote && context.getPlayer() != null) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            RegistryKey<World> departureWorld = serverWorld.getDimensionKey();
            nbt.putString("DepartureWorld", departureWorld.getLocation().toString());

            PlayerEntity player = context.getPlayer();
            ItemStack itemStack = player.getActiveItemStack();
            itemStack.setTag(nbt);

            if (nbt.getBoolean("is_first_placement")) {
                itemStack.setCount(1);
                worldIn.setBlockState(pos, BlockRegistry.GAP_BLOCK.get().getDefaultState());

                nbt.remove("is_first_placement");
                nbt.putBoolean("is_first_placement", false);
                itemStack.setTag(nbt);
            }
            else {
                if (worldIn.getTileEntity(pos) instanceof GapTileEntity) {
                    GapTileEntity secondPlacedSukima = (GapTileEntity) worldIn.getTileEntity(pos);
                    GapTileEntity firstPlacedSukima = (GapTileEntity) worldIn.getTileEntity(
                            BlockPos.fromLong(nbt.getLong("FirstPos")));

                    RegistryKey<World> arrivalWorld = serverWorld.getDimensionKey();
                    nbt.putString("ArrivalWorld", arrivalWorld.getLocation().toString());

                    if (secondPlacedSukima != null) {
                        secondPlacedSukima.setDestinationPos(pos);
                        secondPlacedSukima.setDestinationWorld(departureWorld);

                    }
                    if (firstPlacedSukima != null) {
                        firstPlacedSukima.setDestinationPos(BlockPos.fromLong(nbt.getLong("FirstPos")));
                        firstPlacedSukima.setDestinationWorld(arrivalWorld);
                    }
                }
            }
        }
        return super.onItemUse(context);
    }
}
