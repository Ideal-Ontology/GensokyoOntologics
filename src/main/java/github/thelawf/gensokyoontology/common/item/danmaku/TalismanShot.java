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

public class TalismanShot extends DanmakuItem {
    public TalismanShot(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        final String typeName = String.valueOf(this.getItem().getRegistryName());
        final String modid = GensokyoOntology.MODID + ":";
        DanmakuColor danmakuColor = DanmakuColor.NONE;

        switch (typeName) {
            case modid + "talisman_shot_red":
                danmakuColor = DanmakuColor.RED;
                break;
            case modid + "talisman_shot_yellow":
                danmakuColor = DanmakuColor.YELLOW;
                break;
            case modid + "talisman_shot_green":
                danmakuColor = DanmakuColor.GREEN;
                break;
            case modid + "talisman_shot_blue":
                danmakuColor = DanmakuColor.BLUE;
                break;
            case modid + "talisman_shot_aqua":
                danmakuColor = DanmakuColor.AQUA;
                break;
            case modid + "talisman_shot_purple":
                danmakuColor = DanmakuColor.PURPLE;
                break;
            default:
                break;
        }

        TalismanShotEntity talisman = new TalismanShotEntity(playerIn, worldIn, DanmakuType.TALISMAN_SHOT, danmakuColor);
        DanmakuUtil.shootDanmaku(worldIn, playerIn, talisman, 0.5f, 0f);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
