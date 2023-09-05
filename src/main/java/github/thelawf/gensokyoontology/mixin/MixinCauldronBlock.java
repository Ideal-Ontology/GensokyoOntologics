package github.thelawf.gensokyoontology.mixin;

import github.thelawf.gensokyoontology.common.util.CauldronFluid;
import github.thelawf.gensokyoontology.common.util.GSKOBlockProperties;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CauldronBlock.class)
public class MixinCauldronBlock extends Block{
    private static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_0_3;

    @Unique
    private static final EnumProperty<CauldronFluid> FLUID = GSKOBlockProperties.CAULDRON_FLUID;

    public MixinCauldronBlock(Properties properties) {
        super(properties);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void cauldronBlock$Init(Properties properties, CallbackInfo ci) {
        this.thelawf$setFluidState(CauldronFluid.EMPTY);
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos pos = context.getPos();
        if (pos.getY() < 255 && context.getWorld().getBlockState(pos.up()).isReplaceable(context)) {
            return this.getDefaultState().with(FLUID, CauldronFluid.EMPTY).with(LEVEL, 0);
        } else {
            return null;
        }
    }

    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true, remap = true)
    public void onEntityEnter(BlockState state, World worldIn, BlockPos pos, Entity entityIn, CallbackInfo ci) {
        int i = state.get(LEVEL);
        float f = (float)pos.getY() + (6.0F + (float)(3 * i)) / 16.0F;

        if (!worldIn.isRemote && entityIn.isBurning() && i > 0 && entityIn.getPosY() <= (double)f) {
            if (FLUID.getAllowedValues().contains(CauldronFluid.WATER) || FLUID.getAllowedValues().contains(CauldronFluid.HOT_SPRING)) {
                entityIn.extinguish();
                if (FLUID.getAllowedValues().contains(CauldronFluid.WATER)) {
                    this.thelawf$setCauldronState(worldIn, pos, CauldronFluid.WATER, i - 1);
                }
                else if (FLUID.getAllowedValues().contains(CauldronFluid.WATER)) {
                    this.thelawf$setCauldronState(worldIn, pos, CauldronFluid.HOT_SPRING, i - 1);
                }
            }
            else if (FLUID.getAllowedValues().contains(CauldronFluid.HOT_SPRING) &&
            entityIn instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entityIn;
                living.heal(1.2F);
            }
            else if (FLUID.getAllowedValues().contains(CauldronFluid.LAVA) &&
                    entityIn instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entityIn;
                living.setFire(3);
            }
        }
        ci.cancel();
    }

    // @Redirect(method = "onBlockActivated", at = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/CauldronBlock;onBlockActivated(Lnet/minecraft/tileentity/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/util/math/Direction;FFF)Z"))
    // private ActionResultType redirectToOnBlockClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
    //                                     BlockRayTraceResult hit) {
    //     return onBlockRightClick(state, worldIn, pos, player, handIn, hit);
    // }

    /**
     * @author TheLawF
     * @reason 重写这个方法的原因是因为1.16.5版本居然不支持把岩浆加到炼药锅里面，所以自定义的液体也无法被添加至炼药锅
     */
    @Overwrite
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
                                             BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        if (itemstack.isEmpty()) {
            return ActionResultType.PASS;
        } else {
            int i = state.get(LEVEL);
            Item item = itemstack.getItem();
            if (item == Items.WATER_BUCKET) {
                if (i < 3 && !worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                    }

                    player.addStat(Stats.FILL_CAULDRON);
                    this.setWaterLevel(worldIn, pos, state, 3);
                    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return ActionResultType.func_233537_a_(worldIn.isRemote);
            } else if (item == Items.BUCKET) {
                if (i == 3 && !worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.setHeldItem(handIn, new ItemStack(Items.WATER_BUCKET));
                        } else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))) {
                            player.dropItem(new ItemStack(Items.WATER_BUCKET), false);
                        }
                    }

                    player.addStat(Stats.USE_CAULDRON);
                    this.setWaterLevel(worldIn, pos, state, 0);
                    worldIn.setBlockState(pos, this.getDefaultState().with(FLUID, CauldronFluid.EMPTY));
                    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return ActionResultType.func_233537_a_(worldIn.isRemote);
            } else if (item == Items.GLASS_BOTTLE) {
                if (i > 0 && !worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        ItemStack itemstack4 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.WATER);
                        player.addStat(Stats.USE_CAULDRON);
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.setHeldItem(handIn, itemstack4);
                        } else if (!player.inventory.addItemStackToInventory(itemstack4)) {
                            player.dropItem(itemstack4, false);
                        } else if (player instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity)player).sendContainerToPlayer(player.container);
                        }
                    }

                    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    this.setWaterLevel(worldIn, pos, state, i - 1);
                }

                return ActionResultType.func_233537_a_(worldIn.isRemote);
            } else if (item == Items.POTION && PotionUtils.getPotionFromItem(itemstack) == Potions.WATER) {
                if (!FLUID.getAllowedValues().contains(CauldronFluid.WATER))
                    return ActionResultType.FAIL;

                if (i < 3 && !worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
                        player.addStat(Stats.USE_CAULDRON);
                        player.setHeldItem(handIn, itemstack3);
                        if (player instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity)player).sendContainerToPlayer(player.container);
                        }
                    }

                    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    this.thelawf$setCauldronState(worldIn, pos, CauldronFluid.WATER, i + 1);
                }

                return ActionResultType.func_233537_a_(worldIn.isRemote);
            }
            else if (item == ItemRegistry.HOTSPRING_BUCKET.get()) {
                if (i == 0 && !worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                    }

                    player.addStat(Stats.FILL_CAULDRON);
                    this.thelawf$setCauldronState(worldIn, pos, CauldronFluid.HOT_SPRING, 3);
                    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return ActionResultType.func_233537_a_(worldIn.isRemote);
            }
            else if (item == Items.LAVA_BUCKET) {
                if (i == 0 && !worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                    }

                    player.addStat(Stats.FILL_CAULDRON);
                    this.thelawf$setCauldronState(worldIn, pos , CauldronFluid.LAVA, 3);
                    worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return ActionResultType.func_233537_a_(worldIn.isRemote);
            }
            else {
                if (i > 0 && item instanceof IDyeableArmorItem) {
                    IDyeableArmorItem idyeablearmoritem = (IDyeableArmorItem)item;
                    if (idyeablearmoritem.hasColor(itemstack) && !worldIn.isRemote) {
                        idyeablearmoritem.removeColor(itemstack);
                        this.setWaterLevel(worldIn, pos, state, i - 1);
                        player.addStat(Stats.CLEAN_ARMOR);
                        return ActionResultType.SUCCESS;
                    }
                }

                if (i > 0 && item instanceof BannerItem) {
                    if (BannerTileEntity.getPatterns(itemstack) > 0 && !worldIn.isRemote) {
                        ItemStack itemstack2 = itemstack.copy();
                        itemstack2.setCount(1);
                        BannerTileEntity.removeBannerData(itemstack2);
                        player.addStat(Stats.CLEAN_BANNER);
                        if (!player.abilities.isCreativeMode) {
                            itemstack.shrink(1);
                            this.setWaterLevel(worldIn, pos, state, i - 1);
                        }

                        if (itemstack.isEmpty()) {
                            player.setHeldItem(handIn, itemstack2);
                        } else if (!player.inventory.addItemStackToInventory(itemstack2)) {
                            player.dropItem(itemstack2, false);
                        } else if (player instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity)player).sendContainerToPlayer(player.container);
                        }
                    }

                    return ActionResultType.func_233537_a_(worldIn.isRemote);
                } else if (i > 0 && item instanceof BlockItem) {
                    Block block = ((BlockItem)item).getBlock();
                    if (block instanceof ShulkerBoxBlock && !worldIn.isRemote()) {
                        ItemStack itemstack1 = new ItemStack(Blocks.SHULKER_BOX, 1);
                        if (itemstack.hasTag()) {
                            itemstack1.setTag(itemstack.getTag().copy());
                        }

                        player.setHeldItem(handIn, itemstack1);
                        this.setWaterLevel(worldIn, pos, state, i - 1);
                        player.addStat(Stats.CLEAN_SHULKER_BOX);
                        return ActionResultType.SUCCESS;
                    } else {
                        return ActionResultType.CONSUME;
                    }
                } else {
                    return ActionResultType.PASS;
                }
            }
        }
    }

    @Shadow
    public void setWaterLevel(World worldIn, BlockPos pos, BlockState state, int level) {
        worldIn.setBlockState(pos, state.with(LEVEL, MathHelper.clamp(level, 0, 3)), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }

    @Inject(method = "fillStateContainer", at = @At("TAIL"))
    protected void $addToStateContainer(StateContainer.Builder<Block, BlockState> builder, CallbackInfo info) {
        builder.add(FLUID);
    }

    /**
     * @author TheLawF
     * @param fluidIn 需要处理的液体类型
     */
    @Unique
    private void thelawf$setCauldronState(World world, BlockPos pos, CauldronFluid fluidIn, int levelIn) {
        world.setBlockState(pos, this.getDefaultState().with(FLUID, fluidIn).with(LEVEL, levelIn));
    }

    @Unique
    private void thelawf$setFluidState(CauldronFluid fluid) {
        this.stateContainer.getBaseState().with(FLUID, fluid);
    }

}
