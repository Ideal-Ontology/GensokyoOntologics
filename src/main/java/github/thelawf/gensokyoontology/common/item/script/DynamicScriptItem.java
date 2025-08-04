package github.thelawf.gensokyoontology.common.item.script;

import com.mojang.serialization.Dynamic;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public abstract class DynamicScriptItem extends Item {
    public static final String TYPE_HIGHLIGHT = "§b";    /// 浅蓝 ///
    public static final String EXCEPTION_HIGHLIGHT = "§c";  /// 红色 ///
    public static final ITextComponent OPERATION_TYPE_TIP = GensokyoOntology.translate("tooltip.",".binary_builder.operation_type");
    public static final ITextComponent LEFT_TYPE_TIP = GensokyoOntology.translate("tooltip.",".binary_builder.left_type");
    public static final ITextComponent RIGHT_TYPE_TIP = GensokyoOntology.translate("tooltip.",".binary_builder.right_type");
    public DynamicScriptItem() {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        // this.addDynamicData(worldIn, playerIn, playerIn.getHeldItem(handIn), Dynamic.);
        openScriptEditGUI(worldIn, playerIn, playerIn.getHeldItem(handIn));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public abstract void addDynamicData(World world, PlayerEntity player, ItemStack stack, Dynamic<INBT> dynamic);

    public abstract void openScriptEditGUI(World world, PlayerEntity player, ItemStack stack);

}
