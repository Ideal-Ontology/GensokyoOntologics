package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.GSKONBTUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class SetBlockWand extends Item {

    BlockState blockState = Blocks.STONE.getDefaultState();
    Mode mode = Mode.CIRCLE;
    CompoundNBT nbt = new CompoundNBT();

    public SetBlockWand(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (Screen.hasShiftDown()) {
            blockState = context.getWorld().getBlockState(context.getPos());
        }

        ItemStack itemStack = Objects.requireNonNull(context.getPlayer()).getHeldItemMainhand();

        if (!context.getWorld().isRemote) {
            BlockPos pos = context.getPos(); // 获取玩家所在的坐标

            if (this.mode == Mode.CIRCLE && itemStack.getTag() != null &&
                    itemStack.getTag().contains("centerX") &&
                    itemStack.getTag().contains("centerY") &&
                    itemStack.getTag().contains("centerZ")) { // 如果物品没有标签，就设置圆心

                nbt.putInt("centerX", pos.getX());
                nbt.putInt("centerY", pos.getY());
                nbt.putInt("centerZ", pos.getZ());
                itemStack.setTag(nbt);
                context.getPlayer().sendMessage(new TranslationTextComponent("set_block_item.message.set_center",
                        pos.getX(), pos.getY(), pos.getZ()),context.getPlayer().getUniqueID());
            }
            else { // 如果物品已经有标签，就设置半径
                CompoundNBT nbt = itemStack.getTag();
                int centerX, centerY, centerZ, radius;

                if (nbt != null && nbt.contains("centerX") &&
                        nbt.contains("centerY") && nbt.contains("centerZ")) {
                    centerX = nbt.getInt("centerX");
                    centerY = nbt.getInt("centerY");
                    centerZ = nbt.getInt("centerZ");
                    radius = (int) Math.sqrt(Math.pow(pos.getX() - centerX, 2) +
                            Math.pow(pos.getY() - centerY, 2) + Math.pow(pos.getZ() - centerZ, 2));
                    nbt.putInt("radius", radius);

                    itemStack.setTag(nbt);
                    context.getPlayer().sendMessage(new TranslationTextComponent("item.message.set_radius", radius),context.getPlayer().getUniqueID());
                    placeCircleBlock(new BlockPos(centerX, centerY, centerZ), radius);

                    nbt.remove("centerX");
                    nbt.remove("centerY");
                    nbt.remove("centerZ");
                    nbt.remove("radius");
                    itemStack.setTag(nbt);
                }

            }

            if (this.mode == Mode.BEZIER_CURVE && itemStack.getTag() == null) {

                nbt.putInt("startX", pos.getX());
            }
        }
        return super.onItemUse(context);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (Screen.hasAltDown()) {
           switch (mode){
               case CIRCLE:
                   mode = Mode.ECLIPSE;
                   this.nbt = GSKONBTUtil.removeAllChildNBT(nbt);
                   break;
               case ECLIPSE:
                   mode = Mode.RECTANGLE;
                   this.nbt = GSKONBTUtil.removeAllChildNBT(nbt);
                   break;
               case RECTANGLE:
                   mode = Mode.BEZIER_CURVE;
                   this.nbt = GSKONBTUtil.removeAllChildNBT(nbt);
                   break;
               case BEZIER_CURVE:
                   mode = Mode.CIRCLE;
                   this.nbt = GSKONBTUtil.removeAllChildNBT(nbt);
                   break;
           }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip." +
                GensokyoOntology.MODID + "set_block_wand.mode"));
        tooltip.add(new TranslationTextComponent("tooltip." +
                GensokyoOntology.MODID + "set_block_wand.key_hint"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public enum Mode{
        CIRCLE("circle_mode"),
        ECLIPSE("eclipse_mode"),
        RECTANGLE("rectangle_mode"),
        BEZIER_CURVE("bezier_mode");

        public final String id;
        Mode(String id) {
            this.id = id;
        }
    }

    public void placeCircleBlock(BlockPos posIn, int radius) {

    }

    public void placeBezierCurveBlock() {

    }
}
