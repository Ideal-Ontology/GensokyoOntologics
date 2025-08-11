package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ScaleShot extends DanmakuItem {
    public ScaleShot(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        final String typeName = String.valueOf(this.getItem().getRegistryName());
        final String modid = GensokyoOntology.MODID + ":";
        DanmakuColor danmakuColor;

        switch (typeName) {
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
            case modid + "scale_shot_red":
            default:
                danmakuColor = DanmakuColor.RED;
                break;
        }

        ScaleShotEntity scaleShot = new ScaleShotEntity(playerIn, worldIn, DanmakuType.SCALE_SHOT, danmakuColor);
        DanmakuUtil.shootDanmaku(worldIn, playerIn, scaleShot, 0.6f, 0f);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
