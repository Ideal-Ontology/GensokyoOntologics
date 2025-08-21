package github.thelawf.gensokyoontology.common.entity.combat;

import github.thelawf.gensokyoontology.api.entity.YoukaiCombat;
import github.thelawf.gensokyoontology.common.entity.misc.Laser;
import github.thelawf.gensokyoontology.common.entity.monster.RemiliaScarletEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.Rot2f;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;

public class RemiliaBattle {
    public static final YoukaiCombat.TargetAction<RemiliaScarletEntity> CROSS_SHOTS = (world, remilia, target) -> {
        if (target == null) return;
        if (remilia.ticksExisted % 10 != 0) return;
        DanmakuUtil.oddCurveVec(remilia, target, 13, 30).forEach(shootVec -> {
            Danmaku.create(world, remilia,  ItemRegistry.RICE_SHOT_RED.get())
                    .pos(remilia.getPositionVec().add(-1, 0, 0))
                    .rot(Rot2f.from(shootVec))
                    .shoot(shootVec, 0.4F);
            Danmaku.create(world, remilia,  ItemRegistry.RICE_SHOT_RED.get())
                    .pos(remilia.getPositionVec().add(1, 0, 0))
                    .rot(Rot2f.from(shootVec))
                    .shoot(shootVec, 0.4F);
        });
    };
    public static final YoukaiCombat.SkillAction<RemiliaScarletEntity> THOUSAND_KNIVES = (world, remilia) -> {
        if (remilia.getAttackTarget() == null) return;
        if (remilia.ticksExisted % 20 != 0) return;
        DanmakuUtil.ellipticPos(Vector2f.ZERO, 1.2F, 15).forEach(initPos -> {
            Vector3d shootA = initPos.rotateYaw(Danmaku.rad(5)).normalize();
            Vector3d shootB = initPos.rotateYaw(Danmaku.rad(-5)).normalize();

            for (int i = -1; i < 4; i++) {
                Danmaku.create(world, remilia, ItemRegistry.KNIFE_RED.get())
                        .pos(remilia.getPositionVec().add(initPos).add(0, i, 0))
                        .rot(Rot2f.from(shootA))
                        .shoot(shootA, 0.45F);
                Danmaku.create(world, remilia, ItemRegistry.KNIFE_RED.get())
                        .pos(remilia.getPositionVec().add(initPos).add(0, i, 0))
                        .rot(Rot2f.from(shootB))
                        .shoot(shootB, 0.45F);
            }
        });
    };
    public static final YoukaiCombat.SkillAction<RemiliaScarletEntity> PETTY_DEVIL_LORD = (world, remilia) -> {
        if (remilia.getAttackTarget() == null) return;
        if (remilia.ticksExisted % 400 == 0) {
            DanmakuUtil.spheroidPos(1F, 5).forEach(laserVec -> {
                Laser.create(world, remilia).rot(Rot2f.from(laserVec));
                Laser.create(world, remilia).rot(Rot2f.from(laserVec.scale(2F).rotateYaw(Danmaku.rad(30))));
                Laser.create(world, remilia).rot(Rot2f.from(laserVec.scale(2F).rotateYaw(Danmaku.rad(-30))));
            });
        }

        if (remilia.ticksExisted % 60 == 30) {
            DanmakuUtil.spheroidPos(1F, 12).forEach(laserVec -> {
                Danmaku.create(world, remilia, ItemRegistry.LARGE_SHOT_PURPLE.get())
                        .shoot(laserVec, 0.5F);
            });
        }
        if (remilia.ticksExisted % 60 == 0) {
            DanmakuUtil.spheroidPos(1F, 12).forEach(laserVec -> {
                Danmaku.create(world, remilia, ItemRegistry.LARGE_SHOT_AQUA.get())
                        .shoot(laserVec.rotateYaw(Danmaku.rad(15)), 0.5F);
            });
        }
    };
}
