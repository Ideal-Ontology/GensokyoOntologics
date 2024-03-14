package github.thelawf.gensokyoontology.common.entity.spellcard.boss;

import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.ArrayList;
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
        LargeShotEntity largeShot = new LargeShotEntity(world);
        for (int i = 0; i < GSKOMathUtil.randomRange(2, 5); i++) {
            sphereShot(largeShot, 30);
        }
    }

    private void laserAttack() {
        if (this.flandre.getAttackTarget() == null) return;
        for (int i = 0; i < 10; i++) {
            if (this.flandre.ticksExisted % 400 == (i * 20)) {
                LivingEntity target = this.flandre.getAttackTarget();
                LaserSourceEntity laser = new LaserSourceEntity(this.world, this.flandre);
                laser.setLocationAndAngles(this.flandre.getPosX(), this.flandre.getPosY(), this.flandre.getPosZ(),
                        (float) flandre.getAimedVec(target).x, (float) flandre.getAimedVec(target).z);
                this.world.addEntity(laser);
            }
        }


    }

    private void sphereShot(AbstractDanmakuEntity danmaku, int count) {

        List<Vector3d> pos1 = DanmakuUtil.ellipticPos(Vector2f.ZERO, 1, count);
        List<Vector3d> pos2 = new ArrayList<>();

        for (int i = 0; i < pos1.size(); i++) {
            for (int j = 0; j < pos1.size(); j++) {
                Vector3d vector3d = pos1.get(j).rotatePitch((float) Math.PI * 2 / pos1.size() * j);
                pos1.set(j, vector3d);
            }
            pos2.addAll(pos1);
        }

        pos2.forEach(vector3d -> {
            Vector3d vec = GSKOMathUtil.randomVec(-3, 3);
            DanmakuUtil.initDanmaku(danmaku, flandre.getPositionVec().add(vector3d.x, 1.2, vector3d.z)
                    .add(vec.x, 0, vec.z), true);
            danmaku.shoot(vector3d.x, vector3d.y, vector3d.z, 0.7f, 0f);
            world.addEntity(danmaku);
        });
    }
}
