package github.thelawf.gensokyoontology.common.entity.spellcard.boss;

import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.entity.monster.RemiliaScarletEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;

public class RemiliaSpellAttack {
    public static void tickLaserSpiral(RemiliaScarletEntity remilia) {
        Vector3d initRot = new Vector3d(0, -1, 0);
        Vector3d position = remilia.getPositionVec().subtract(0, -5, 0);

        for (int i = remilia.ticksExisted; i < remilia.ticksExisted + 25; i++) {
            // if (remilia.ticksExisted != i) break;
            initRot = initRot.rotatePitch((float) Math.PI / (i - remilia.ticksExisted));
            initRot = initRot.rotateYaw((float) Math.PI / (i - remilia.ticksExisted));
            position = position.add(new Vector3d(0, (double) (10 / 25) * (i - remilia.ticksExisted), 0));

            Vector2f direction = GSKOMathUtil.toYawPitch(initRot);
            GSKOUtil.log(RemiliaSpellAttack.class, direction.x + ", " + direction.y);

            LaserSourceEntity laser = new LaserSourceEntity(remilia.world, remilia);
            laser.setLocationAndAngles(remilia.getPosX(), remilia.getPosY(), remilia.getPosZ(),
                    direction.x, direction.y);
            laser.init(500, 30, 100);
            remilia.world.addEntity(laser);
        }
    }
}
