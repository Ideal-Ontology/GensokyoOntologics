package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.common.entity.projectile.HeartShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.StarShotEntity;
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

public class StarShot extends Item {

    public final DanmakuType type;

    public StarShot(DanmakuType type) {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
        this.type = type;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        SpellData spellData = new SpellData(null, DanmakuType.STAR_SHOT_LARGE,
                DanmakuColor.NONE, false, false);
        DanmakuUtil.shootDanmaku(worldIn, playerIn, new StarShotEntity(playerIn, worldIn, spellData),
                0.6f, 0f);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
