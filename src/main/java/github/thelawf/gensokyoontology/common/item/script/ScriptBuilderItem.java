package github.thelawf.gensokyoontology.common.item.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.GSKONBTUtil;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ScriptBuilderItem extends Item {
    public static final String TYPE_HIGHLIGHT = "§6";    /// 金色 ///
    public static final String NAME_HIGHLIGHT = "§d";    /// 粉色 ///
    public static final String VALUE_HIGHLIGHT = "§a";   /// 浅绿 ///
    public static final String STRING_HIGHLIGHT = "§b";  /// 天蓝色 ///
    public static final String EXCEPTION_HIGHLIGHT = "§c";  /// 红色 ///
    public static final String RESET_HIGHLIGHT = "§r";    /// 重置 ///
    public static final ITextComponent FILED_TYPE_TIP = GensokyoOntology.withTranslation("tooltip.",".script_builder.field_type");
    public static final ITextComponent FILED_NAME_TIP = GensokyoOntology.withTranslation("tooltip.",".script_builder.field_name");
    public static final ITextComponent FILED_VALUE_TIP = GensokyoOntology.withTranslation("tooltip.",".script_builder.field_value");
    public ScriptBuilderItem() {
        super(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (!worldIn.isRemote) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
            this.openScriptEditGUI(serverWorld, serverPlayer, playerIn.getHeldItem(handIn));
        }
        this.openScriptEditGUI(worldIn, playerIn, playerIn.getHeldItem(handIn));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

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
                tooltip.add(new StringTextComponent(VALUE_HIGHLIGHT + GSKONBTUtil.getAs(nbt).getString()));
            }
            else if (GSKONBTUtil.containsAllowedType(nbt)) {
                GSKONBTUtil.getMemberValues(nbt).forEach(s -> tooltip.add(new StringTextComponent(s)));
            }
        }
    }

    public abstract void openScriptEditGUI(World serverWorld, PlayerEntity serverPlayer, ItemStack stack);
}
