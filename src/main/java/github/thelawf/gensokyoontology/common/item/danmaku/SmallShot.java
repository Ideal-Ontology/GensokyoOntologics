package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SmallShot extends Item {

    public DanmakuType type;
    public SmallShot(DanmakuType type) {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
        this.type = type;
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        final String typeName = String.valueOf(this.getItem().getRegistryName());
        final String modid = GensokyoOntology.MODID + ":";
        DanmakuColor danmakuColor = DanmakuColor.NONE;

        switch (typeName) {
            case modid + "small_shot_red":
                danmakuColor = DanmakuColor.RED;
                break;
            case modid + "small_shot_yellow":
                danmakuColor = DanmakuColor.YELLOW;
                break;
            case modid + "small_shot_blue":
                danmakuColor = DanmakuColor.BLUE;
                break;
            case modid + "small_shot_green":
                danmakuColor = DanmakuColor.GREEN;
                break;
            case modid + "small_shot_purple":
                danmakuColor = DanmakuColor.PURPLE;
                break;
            default:
                break;
        }

        SmallShotEntity smallShot = new SmallShotEntity(playerIn, worldIn,
                DanmakuType.HEART_SHOT, danmakuColor);
        DanmakuUtil.shootDanmaku(worldIn, playerIn, smallShot, 0.6f, 0f);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
