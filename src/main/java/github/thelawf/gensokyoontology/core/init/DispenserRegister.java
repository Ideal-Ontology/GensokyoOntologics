package github.thelawf.gensokyoontology.core.init;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DispenserRegister {
    @SubscribeEvent
    public static void onDispenseRegister(FMLCommonSetupEvent event){
        DispenserBlock.registerDispenseBehavior(ItemRegistry.HOTSPRING_BUCKET.get(),
                new DefaultDispenseItemBehavior(){
                    private final DefaultDispenseItemBehavior behavior =
                            new DefaultDispenseItemBehavior();

                    /**
                     * Dispense the specified stack, play the dispensed sound and spawn particles.
                     *
                     */
                    @Override
                    public ItemStack dispenseStack(IBlockSource source, ItemStack stack){
                        BucketItem bucketItem = (BucketItem) stack.getItem();
                        BlockPos blockPos = source.getBlockPos().offset(source.getBlockState()
                                .get(DispenserBlock.FACING));
                        World world = source.getWorld();
                        if (bucketItem.tryPlaceContainedLiquid(null,world,
                                blockPos,null)) {
                            bucketItem.onLiquidPlaced(world,stack,blockPos);
                            return new ItemStack(Items.BUCKET);
                        }
                        else {
                            return this.behavior.dispense(source,stack);
                        }
                    }
                });

    }
}
