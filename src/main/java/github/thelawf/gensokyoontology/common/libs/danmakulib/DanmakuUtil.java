package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class DanmakuUtil {

    public static <T extends AbstractDanmakuEntity> void shootDanmaku(@NotNull World worldIn, PlayerEntity playerIn,
                                                                      T danmakuEntityType, float velocity, float inaccuracy) {
        Vector3d lookVec = playerIn.getLookVec();
        Vector3d pos = Vector3d.copyCentered(playerIn.getPosition());

        danmakuEntityType.setNoGravity(true);
        danmakuEntityType.setLocationAndAngles(pos.getX(), playerIn.getPosY() + 0.8, pos.getZ(),
                (float) lookVec.y, (float) lookVec.z);
        danmakuEntityType.shoot(lookVec.x, lookVec.y, lookVec.z, velocity, inaccuracy);
        worldIn.addEntity(danmakuEntityType);

        playerIn.getHeldItemMainhand().shrink(1);

    }

    public static <T extends AbstractDanmakuEntity> void shootWaveAndParticle (@NotNull World worldIn, PlayerEntity playerIn,
                                                                               T danmakuEntityType, float velocity, float inaccuracy) {

    }
}
