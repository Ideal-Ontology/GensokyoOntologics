package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class LargeShot extends DanmakuItem {

    DanmakuType type;

    public LargeShot(DanmakuType type) {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
        this.type = type;
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        final String typeName = String.valueOf(this.getItem().getRegistryName());
        final String modid = GensokyoOntology.MODID + ":";
        DanmakuColor danmakuColor = DanmakuColor.NONE;

        switch (typeName) {
            case modid + "large_shot_red":
                danmakuColor = DanmakuColor.RED;
                break;
            case modid + "large_shot_yellow":
                danmakuColor = DanmakuColor.YELLOW;
                break;
            case modid + "large_shot_blue":
                danmakuColor = DanmakuColor.BLUE;
                break;
            case modid + "large_shot_green":
                danmakuColor = DanmakuColor.GREEN;
                break;
            case modid + "large_shot_purple":
                danmakuColor = DanmakuColor.PURPLE;
                break;
            default:
                break;
        }

        LargeShotEntity largeShot = new LargeShotEntity(playerIn, worldIn, DanmakuType.HEART_SHOT, danmakuColor);
        DanmakuUtil.shootDanmaku(worldIn, playerIn, largeShot, 0.6f, 0f);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
