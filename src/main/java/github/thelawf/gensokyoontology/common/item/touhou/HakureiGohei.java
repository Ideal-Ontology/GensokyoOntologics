package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.misc.DreamSealEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.InYoJadeDanmakuEntity;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * 博丽灵梦的御币
 */
public class HakureiGohei extends Item {
    public static final ITextComponent TITLE = GensokyoOntology.withTranslation("gui.", ".hakurei_gohei.title");
    public HakureiGohei(Properties properties) {
        super(properties);
    }

    private void setMode(CompoundNBT nbt, Mode mode) {
        nbt.putInt("mode", mode.ordinal());
    }

    public static Mode getMode(CompoundNBT nbt) {
        return Mode.values()[nbt.getInt("mode")];
    }

    private Mode switchMode(CompoundNBT nbt) {
        return EnumUtil.switchEnum(Mode.class, getMode(nbt));
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this) && !playerIn.isCreative())
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        ItemStack stack = playerIn.getHeldItem(handIn);
        if (stack.getTag() != null) {
            switch(getMode(stack.getTag())) {
                default:
                case DANMAKU:
                    InYoJadeDanmakuEntity inYoJade = new InYoJadeDanmakuEntity(worldIn, playerIn);
                    DanmakuUtil.shootDanmaku(worldIn, playerIn, inYoJade, 1F, 0F);
                    break;
                case DREAM_SEAL:
                    fireDreamSeal(worldIn, playerIn);
                    break;
                case SPELL_CARD:
                    break;
            }
        }

        if (playerIn.isCreative()) return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        playerIn.getCooldownTracker().setCooldown(this, 10);
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    public void fireDreamSeal(World worldIn, PlayerEntity playerIn) {
        for (int i = 0; i < 8; i++) {
            int i1 = i % 3;
            DanmakuColor color;
            switch (i1) {
                default:
                case 0:
                    color = DanmakuColor.RED;
                    break;
                case 1:
                    color = DanmakuColor.BLUE;
                    break;
                case 2:
                    color = DanmakuColor.GREEN;
                    break;
            }
            Vector3d vector3d = new Vector3d(1, 2.5, 0);
            Vector3d shootVec = playerIn.getLookVec();
            Vector3d initPos = vector3d.rotatePitch((float) Math.PI * 2 / i).add(playerIn.getPositionVec());

            DreamSealEntity dreamSeal = new DreamSealEntity(worldIn, playerIn, color);
            dreamSeal.setNoGravity(true);
            dreamSeal.shoot(shootVec.x, shootVec.y, shootVec.z, 0.8f, 0f);
            dreamSeal.setLocationAndAngles(initPos.x, initPos.y, initPos.z, playerIn.rotationYaw, playerIn.rotationPitch);
            worldIn.addEntity(dreamSeal);
        }
    }

    @Override
    public void fillItemGroup(@NotNull ItemGroup group, @NotNull NonNullList<ItemStack> items) {
        if (group == GSKOItemTab.GSKO_ITEM_TAB) {
            ItemStack itemStack = new ItemStack(this);
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("mode", Mode.DANMAKU.ordinal());
            itemStack.setTag(tag);
            items.add(itemStack);
        }
    }

    @Override
    @NotNull
    public UseAction getUseAction(@NotNull ItemStack stack) {
        return UseAction.BLOCK;
    }

    public enum Mode {
        DANMAKU,
        SPELL_CARD,
        DREAM_SEAL
    }
}
