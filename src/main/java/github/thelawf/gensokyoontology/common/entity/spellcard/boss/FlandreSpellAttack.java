package github.thelawf.gensokyoontology.common.entity.spellcard.boss;

import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;

public class FlandreSpellAttack {
    public final BossSpell bossSpell;
    public World world;
    public FlandreScarletEntity flandre;
    public FlandreSpellAttack(World world, FlandreScarletEntity flandre) {
        this.world = world;
        this.flandre = flandre;
        this.bossSpell = BossSpell.of(this::sphereDanmaku, this::laserAttack);
    }

    private void sphereDanmaku() {
        sphereShot(10);
    }

    private void laserAttack() {
        if (this.flandre.getAttackTarget() == null) return;
        if (this.flandre.ticksExisted % 50 == 0) {
            LaserSourceEntity laser = new LaserSourceEntity(this.world, this.flandre);
            laser.setLocationAndAngles(this.flandre.getPosX(), this.flandre.getPosY(), this.flandre.getPosZ(),
                    flandre.getAimedYaw(), flandre.getAimedPitch());
            laser.setOwnerId(this.flandre.getUniqueID());
            this.world.addEntity(laser);
        }
    }

    private void sphereShot(int count) {
        List<Vector3d> shootVec = DanmakuUtil.spheroidPos(1, count);
        shootVec.forEach(vector3d -> {
            Vector3d vec = GSKOMathUtil.randomVec(-3, 3);
            LargeShotEntity largeShot = new LargeShotEntity(world);
            DanmakuUtil.initDanmaku(largeShot, flandre.getPositionVec().add(vector3d.x, 1.2, vector3d.z)
                    .add(vec.x, 0, vec.z), true);
            largeShot.shoot(vector3d.x, vector3d.y, vector3d.z, 0.7f, 0f);
            largeShot.setShooter(this.flandre);
            world.addEntity(largeShot);
        });
    }

    public static void laser(FlandreScarletEntity flandre) {
        if (flandre.getAttackTarget() == null) return;
        if (flandre.ticksExisted % 50 == 0) {
            LaserSourceEntity laser = new LaserSourceEntity(flandre.world, flandre);
            laser.setLocationAndAngles(flandre.getPosX(), flandre.getPosY(), flandre.getPosZ(),
                    flandre.getAimedYaw(), flandre.getAimedPitch());
            laser.setOwnerId(flandre.getUniqueID());
            flandre.world.addEntity(laser);
        }
    }

    public static void sphere(FlandreScarletEntity flandre) {
        List<Vector3d> shootVec = DanmakuUtil.spheroidPos(1, 10);
        World world = flandre.world;
        shootVec.forEach(vector3d -> {
            Vector3d vec = GSKOMathUtil.randomVec(-3, 3);
            LargeShotEntity largeShot = new LargeShotEntity(world);
            DanmakuUtil.initDanmaku(largeShot, flandre.getPositionVec().add(vector3d.x, 1.2, vector3d.z)
                    .add(vec.x, 0, vec.z), true);
            largeShot.shoot(vector3d.x, vector3d.y, vector3d.z, 0.7f, 0f);
            largeShot.setShooter(flandre);
            world.addEntity(largeShot);
        });
    }
}
