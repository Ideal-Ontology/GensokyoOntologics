package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.projectile.RiceShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.ScaleShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ScaleShot extends Item {
    public ScaleShot() {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        final String typeName = String.valueOf(this.getItem().getRegistryName());
        final String modid = GensokyoOntology.MODID + ":";
        DanmakuColor danmakuColor = DanmakuColor.NONE;

        switch (typeName) {
            case modid + "scale_shot_red":
                danmakuColor = DanmakuColor.RED;
                break;
            case modid + "scale_shot_yellow":
                danmakuColor = DanmakuColor.YELLOW;
                break;
            case modid + "scale_shot_green":
                danmakuColor = DanmakuColor.GREEN;
                break;
            case modid + "scale_shot_blue":
                danmakuColor = DanmakuColor.BLUE;
                break;
            case modid + "scale_shot_purple":
                danmakuColor = DanmakuColor.PURPLE;
                break;
            default:
                danmakuColor = DanmakuColor.RED;
                break;
        }

        ScaleShotEntity scaleShot = new ScaleShotEntity(playerIn, worldIn, DanmakuType.SCALE_SHOT, danmakuColor);
        DanmakuUtil.shootDanmaku(worldIn, playerIn, scaleShot, 0.6f, 0f);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
