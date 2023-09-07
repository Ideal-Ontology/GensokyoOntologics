package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;

public class DanmakuShotItem extends DanmakuItem {
    public final DanmakuType danmaku;

    public DanmakuShotItem(DanmakuType danmaku) {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
        this.danmaku = danmaku;
    }

    // @Override
    // @NotNull
    // public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
    //     SpellData = new SpellData(null, DanmakuType.DANMAKU_SHOT,
    //             DanmakuColor.NONE, false, false);
    //     DanmakuUtil.shootDanmaku(worldIn, playerIn, new DanmakuShotEntity(playerIn, worldIn, spellData),
    //             0.5f, 0.6f);
    //     return super.onItemRightClick(worldIn, playerIn, handIn);
    // }

}
