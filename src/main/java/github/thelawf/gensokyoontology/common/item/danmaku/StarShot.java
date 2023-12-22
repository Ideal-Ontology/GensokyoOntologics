package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallStarShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class StarShot extends DanmakuItem {

    public final DanmakuType type;

    public StarShot(DanmakuType type) {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
        this.type = type;
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        final String typeName = String.valueOf(this.getItem().getRegistryName());
        final String modid = GensokyoOntology.MODID + ":";
        DanmakuColor danmakuColor;

        switch (typeName) {
            case modid + "small_star_shot_yellow":
            case modid + "large_star_shot_yellow":
                danmakuColor = DanmakuColor.YELLOW;
                break;
            case modid + "small_star_shot_green":
            case modid + "large_star_shot_green":
                danmakuColor = DanmakuColor.GREEN;
                break;
            case modid + "small_star_shot_blue":
            case modid + "large_star_shot_blue":
                danmakuColor = DanmakuColor.BLUE;
                break;
            case modid + "small_star_shot_aqua":
            case modid + "large_star_shot_aqua":
                danmakuColor = DanmakuColor.AQUA;
                break;
            case modid + "small_star_shot_purple":
            case modid + "large_star_shot_purple":
                danmakuColor = DanmakuColor.PURPLE;
                break;
            case modid + "small_star_shot_red":
            case modid + "large_star_shot_red":
            default:
                danmakuColor = DanmakuColor.RED;
                break;
        }

        SmallStarShotEntity starShot = new SmallStarShotEntity(playerIn, worldIn, DanmakuType.LARGE_SHOT, danmakuColor);
        DanmakuUtil.shootDanmaku(worldIn, playerIn, starShot, 0.6f, 0f);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
