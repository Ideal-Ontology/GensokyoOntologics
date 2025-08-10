package github.thelawf.gensokyoontology.common.entity.combat.boss;

import com.google.common.collect.ImmutableList;
import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.entity.monster.RemiliaScarletEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;

public class RemiliaSpellAttack {

    public static List<Runnable> toSpells(World world, RemiliaScarletEntity remilia) {
        return ImmutableList.of(() -> sphere(world, remilia));
    }

    public static void sphere(World world, RemiliaScarletEntity remilia) {
        List<Vector3d> shootVec = DanmakuUtil.spheroidPos(1, 20);
        shootVec.forEach(vector3d -> {
            Vector3d vec = GSKOMathUtil.randomVec(-3, 3);
            LargeShotEntity largeShot = new LargeShotEntity(world);
            DanmakuUtil.initDanmaku(largeShot, remilia.getPositionVec().add(vector3d.x, 1.2, vector3d.z)
                    .add(vec.x, 0, vec.z), true);
            largeShot.shoot(vector3d.x, vector3d.y, vector3d.z, 0.7f, 0f);
            largeShot.setShooter(remilia);
            world.addEntity(largeShot);
        });
    }

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
