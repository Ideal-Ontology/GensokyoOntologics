package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuUtil;
import github.thelawf.gensokyoontology.common.libs.danmakulib.SpellData;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class DanmakuShotItem extends Item {
    public final DanmakuType type;

    public DanmakuShotItem(DanmakuType type) {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
        this.type = type;
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        SpellData spellData = new SpellData(null, DanmakuType.DANMAKU_SHOT,
                DanmakuColor.NONE, false, false);
        DanmakuUtil.shootDanmaku(worldIn, playerIn, new DanmakuShotEntity(playerIn, worldIn, spellData),
                0.5f, 0.6f);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
