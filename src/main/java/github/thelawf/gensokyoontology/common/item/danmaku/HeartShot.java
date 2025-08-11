package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class HeartShot extends DanmakuItem {

    DanmakuType type;

    public HeartShot(DanmakuType type) {
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
            case modid + "heart_shot_red":
                danmakuColor = DanmakuColor.RED;
                break;
            case modid + "heart_shot_pink":
                danmakuColor = DanmakuColor.PINK;
                break;
            case modid + "heart_shot_aqua":
                danmakuColor = DanmakuColor.AQUA;
                break;
            case modid + "heart_shot_blue":
                danmakuColor = DanmakuColor.BLUE;
                break;
            default:
                break;
        }

        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("color", danmakuColor.ordinal());

        HeartShotEntity heartShot = new HeartShotEntity(playerIn, worldIn, nbt);
        DanmakuUtil.shootDanmaku(worldIn, playerIn, heartShot, 0.6f, 0f);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
