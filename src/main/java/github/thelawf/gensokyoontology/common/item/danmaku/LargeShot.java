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

public class LargeShot extends DanmakuItem {

    DanmakuType type;

    public LargeShot(DanmakuType type) {
        super();
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
