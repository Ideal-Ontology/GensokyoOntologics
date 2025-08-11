package github.thelawf.gensokyoontology.common.entity.combat;

import github.thelawf.gensokyoontology.api.entity.YoukaiCombat;
import github.thelawf.gensokyoontology.common.entity.misc.DestructiveEyeEntity;
import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import github.thelawf.gensokyoontology.common.entity.monster.RumiaEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.RiceShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class BossBattle {
    public static final int MAX_DISTANCE = 100;
    public static final YoukaiCombat.SkillAction<FlandreScarletEntity> FLANDRE_LASER = (world, flandre) -> {
        if (flandre.getAttackTarget() == null) return;
        if (flandre.ticksExisted % 50 == 0) {
            LaserSourceEntity laser = new LaserSourceEntity(world, flandre);
            laser.setLocationAndAngles(flandre.getPosX(), flandre.getPosY(), flandre.getPosZ(),
                    flandre.getAimedYaw(), flandre.getAimedPitch());
            laser.setOwnerId(flandre.getUniqueID());
            world.addEntity(laser);
        }
    };


    public static final YoukaiCombat.TargetAction<FlandreScarletEntity> SUMMON_EYE = (world, flandre, target) -> {
        flandre.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = flandre.getDistanceSq(target);

        if (!flandre.getEntitySenses().canSee(target) || !(distance < MAX_DISTANCE)) return;
        if (world.isRemote) return;

        ServerWorld serverWorld = (ServerWorld) world;
        boolean canSummon = serverWorld.getEntities().filter(e -> e.getType() == EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get()).count() <= 20;
        if (!canSummon) return;

        for (int i = 0; i < 3; i++) {
            DestructiveEyeEntity eye = new DestructiveEyeEntity(world);
            Vector3d vector3d = DanmakuUtil.getRandomPosWithin(MAX_DISTANCE, DanmakuUtil.Plane.XYZ).add(target.getPositionVec());
            eye.setLocationAndAngles(vector3d.x, vector3d.y, vector3d.z, 0F, 0F);
            world.addEntity(eye);
        }
    };

    public static final YoukaiCombat.SkillAction<FlandreScarletEntity> FLANDRE_SPHERE = (world, flandre) -> {
        List<Vector3d> shootVec = DanmakuUtil.spheroidPos(1, 10);
        shootVec.forEach(vector3d -> {
            Vector3d vec = GSKOMathUtil.randomVec(-3, 3);
            LargeShotEntity largeShot = new LargeShotEntity(world);
            DanmakuUtil.initDanmaku(largeShot, flandre.getPositionVec().add(vector3d.x, 1.2, vector3d.z)
                    .add(vec.x, 0, vec.z), true);
            largeShot.shoot(vector3d.x, vector3d.y, vector3d.z, 0.7f, 0f);
            largeShot.setShooter(flandre);
            world.addEntity(largeShot);
        });
    };

    public static final YoukaiCombat.TargetAction<RumiaEntity> DARK_SPHERE = (world, youkai, target) -> {

    };

    public static final YoukaiCombat.TargetAction<RumiaEntity> AIMED_SHOOT = (world, youkai, target) -> {

    };

    public static final YoukaiCombat.SkillAction<RumiaEntity> DARK_BORDER_LINE = (world, rumiaEntity) -> {
        List<Vector3d> shootVec = DanmakuUtil.spheroidPos(1, 15);
        shootVec.forEach(vector3d -> {
            RiceShotEntity riceShot = new RiceShotEntity(rumiaEntity, world, DanmakuType.RICE_SHOT, DanmakuColor.PINK);

        });
    };
}
