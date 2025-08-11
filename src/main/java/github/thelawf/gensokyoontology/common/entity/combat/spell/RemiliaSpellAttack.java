package github.thelawf.gensokyoontology.common.entity.combat.spell;

import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.entity.monster.RemiliaScarletEntity;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;

public class RemiliaSpellAttack {


    public static void tickLaserSpiral(RemiliaScarletEntity remilia) {
        int count = 25;
        Vector3d initRot = new Vector3d(1, 0, 0);
        Vector3d position = remilia.getPositionVec();

        for (int i = remilia.ticksExisted; i < remilia.ticksExisted + count; i++) {
            int unit = i - remilia.ticksExisted;
            // initRot = initRot.rotatePitch((float) Math.PI / 25 * unit);
            initRot = initRot.rotateYaw((float) Math.PI / count);
            position = position.add(new Vector3d(0, 0.5, 0));

            Vector2f direction = GSKOMathUtil.toYawPitch(initRot);
            LaserSourceEntity laser = new LaserSourceEntity(remilia.world, remilia);
            laser.setLocationAndAngles(position.x, position.y, position.z, direction.x, direction.y);
            laser.init(500, 30, 100);
            remilia.world.addEntity(laser);
        }
    }
}
