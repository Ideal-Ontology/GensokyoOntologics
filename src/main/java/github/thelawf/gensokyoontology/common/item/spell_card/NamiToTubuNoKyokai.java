package github.thelawf.gensokyoontology.common.item.spell_card;

import github.thelawf.gensokyoontology.common.entity.DanmakuEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class NamiToTubuNoKyokai extends Item {
    // 传统艺能：境符「波与粒的境界」

    public NamiToTubuNoKyokai(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (!worldIn.isRemote) {
            /* 开花弹示例：
            TransformFunction tf = new TransformFunction(new Vector3d(0,0,0),playerIn.getPositionVec(),5,100,2,0,0,null,0.5);
            for (int i = 0; i < tf.lifeSpan / tf.shootInterval * tf.executeTimes; i++) {
                DanmakuEntity danmaku = new DanmakuEntity();
                danmaku.setLocationAndAngles(playerIn.getPosX(),playerIn.getPosY(),playerIn.getPosZ(), (float) (i * 7.2),0);
                worldIn.addEntity(danmaku);
            }
            // 或者使用 danmaku.moveForced(tf.angleToVector(incrementYawIn, incrementPitchIn));
             */

        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
