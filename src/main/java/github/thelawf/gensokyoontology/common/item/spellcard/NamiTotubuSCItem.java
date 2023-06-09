package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class NamiTotubuSCItem extends Item {
    // 传统艺能：境符「波与粒的境界」
    String spellName;

    public NamiTotubuSCItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (!worldIn.isRemote) {
            // 开花弹示例：
            TransformFunction tf = TransformFunction.Builder.create()
                    .setPlayer(playerIn).setInitLocation(playerIn.getPositionVec())
                    .setLifeSpan(120).setExecuteTimes(5)
                    .setExecuteInterval(10).setResultantSpeed(0.75)
                    .setWorld(worldIn);

            for (int i = 0; i < tf.executeTimes; i++) {
                // Vector3d rotationVec = new Vector3d(10,0,0);
                // Vector3d pivot = func.translate(func.x, func.y, func.z);
                // func.rotate(func.initLocation, pivot, new Vector3d(0d, Math.PI / 12, 0d));

                tf.setInitLocation(new Vector3d(tf.initLocation.x + 0.9 * i,
                        tf.initLocation.y, tf.initLocation.z));

            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
