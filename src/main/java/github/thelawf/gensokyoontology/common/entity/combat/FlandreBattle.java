package github.thelawf.gensokyoontology.common.entity.combat;

import github.thelawf.gensokyoontology.api.entity.YoukaiCombat;
import github.thelawf.gensokyoontology.common.entity.misc.DestructiveEyeEntity;
import github.thelawf.gensokyoontology.common.entity.misc.Laser;
import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import github.thelawf.gensokyoontology.common.entity.monster.RemiliaScarletEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

public class FlandreBattle {
    public static final YoukaiCombat.SkillAction<FlandreScarletEntity> FLANDRE_LASER = (world, flandre) -> {
        if (flandre.getAttackTarget() == null) return;
        if (flandre.ticksExisted % 50 == 0) {
            Laser laser = new Laser(world, flandre);
            laser.setLocationAndAngles(flandre.getPosX(), flandre.getPosY(), flandre.getPosZ(),
                    flandre.getAimedYaw(), flandre.getAimedPitch());
            laser.setOwnerId(flandre.getUniqueID());
            world.addEntity(laser);
        }
    };
    public static final YoukaiCombat.TargetAction<FlandreScarletEntity> SUMMON_EYE = (world, flandre, target) -> {
        flandre.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = flandre.getDistanceSq(target);

        if (!flandre.getEntitySenses().canSee(target) || !(distance < BossBattle.MAX_DISTANCE)) return;
        if (world.isRemote) return;

        ServerWorld serverWorld = (ServerWorld) world;
        boolean canSummon = serverWorld.getEntities().filter(e -> e.getType() == EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get()).count() <= 20;
        if (!canSummon) return;

        for (int i = 0; i < 3; i++) {
            DestructiveEyeEntity eye = new DestructiveEyeEntity(world);
            Vector3d vector3d = DanmakuUtil.getRandomPosWithin(BossBattle.MAX_DISTANCE, DanmakuUtil.Plane.XYZ).add(target.getPositionVec());
            eye.setLocationAndAngles(vector3d.x, vector3d.y, vector3d.z, 0F, 0F);
            world.addEntity(eye);
        }
    };

    public static final YoukaiCombat.SkillAction<RemiliaScarletEntity> FLANDRE_SPHERE = (world, flandre) -> {
        float raidus = GSKOMathUtil.triangularPeriod(flandre.ticksExisted, 0, 2);
        if (flandre.ticksExisted % 7 != 0) return;
        float angleDeg = flandre.ticksExisted / 7F * 3;
        DanmakuUtil.spheroidPos(raidus, 7).forEach(pos -> {
            Vector3d shoot = pos.inverse().rotateYaw(angleDeg);
            Danmaku.create(world, flandre, ItemRegistry.RICE_SHOT_RED.get())
                    .pos(flandre.getPositionVec().add(pos))
                    .shoot(shoot, 0.3F);
        });
    };
}
