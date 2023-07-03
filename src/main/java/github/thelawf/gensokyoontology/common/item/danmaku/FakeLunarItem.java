package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.common.entity.projectile.FakeLunarEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.*;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class FakeLunarItem extends Item {
    public DanmakuType type;
    public FakeLunarItem(DanmakuType type) {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
        this.type = type;
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        FakeLunarEntity fakeLunar = new FakeLunarEntity(playerIn, worldIn, DanmakuType.FAKE_LUNAR, DanmakuColor.NONE);
        DanmakuUtil.shootDanmaku(worldIn, playerIn, fakeLunar, 0.6f, 0f);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
