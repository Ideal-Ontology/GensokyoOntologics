package github.thelawf.gensokyoontology.common.item.script;

import github.thelawf.gensokyoontology.common.nbt.GSKONBTUtil;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ScriptReadOnlyItem extends Item {
    public static final String TYPE_HIGHLIGHT = "§6";    /// 金色 ///
    public static final String NAME_HIGHLIGHT = "§d";    /// 粉色 ///
    public static final String VALUE_HIGHLIGHT = "§a";   /// 浅绿 ///
    public static final String STRING_HIGHLIGHT = "§b";  /// 天蓝色 ///
    public static final String EXCEPTION_HIGHLIGHT = "§c";  /// 红色 ///
    public static final String RESET_HIGHLIGHT = "§r";    /// 重置 ///
    public static final ITextComponent FILED_TYPE_TIP = GSKOUtil.translate("tooltip.",".script_builder.field_type");
    public static final ITextComponent FILED_NAME_TIP = GSKOUtil.translate("tooltip.",".script_builder.field_name");
    public static final ITextComponent FILED_VALUE_TIP = GSKOUtil.translate("tooltip.",".script_builder.field_value");
    public ScriptReadOnlyItem() {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        addReadOnlyData(worldIn, playerIn, playerIn.getHeldItem(handIn));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public abstract void addReadOnlyData(World world, PlayerEntity player, ItemStack stack);

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.getTag() != null) {
            CompoundNBT nbt = stack.getTag();
            tooltip.add(FILED_TYPE_TIP);
            tooltip.add(new StringTextComponent(TYPE_HIGHLIGHT + nbt.getString("type")));
            tooltip.add(FILED_NAME_TIP);
            tooltip.add(new StringTextComponent(NAME_HIGHLIGHT + nbt.getString("name")));
            tooltip.add(FILED_VALUE_TIP);
            if (GSKONBTUtil.containsPrimitiveType(nbt)) {
                tooltip.add(new StringTextComponent(VALUE_HIGHLIGHT + GSKONBTUtil.getFromValue(nbt).getString()));
            }
            else if (GSKONBTUtil.containsAllowedType(nbt)) {
                GSKONBTUtil.getMemberValues(nbt).forEach(s -> tooltip.add(new StringTextComponent(s)));
            }
        }
    }
}
