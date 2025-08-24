package github.thelawf.gensokyoontology.common.entity.combat;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.api.entity.YoukaiCombat;
import github.thelawf.gensokyoontology.common.entity.misc.DestructiveEyeEntity;
import github.thelawf.gensokyoontology.common.entity.misc.Laser;
import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import github.thelawf.gensokyoontology.common.entity.monster.RemiliaScarletEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.Rot2f;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class FlandreBattle {
    public static final BiFunction<World, LivingEntity, List<Danmaku>> WAVE_PACKET_DANMAKU = (world, youkai) -> {
        List<Danmaku> danmakuList = new ArrayList<>();
        List<Vector3d> horizontalPos = DanmakuUtil.ellipticPos(8F, 60);
        List<Pair<Vector3d, Vector3d>> posAndShootRot = new ArrayList<>();

        for (int i = 0; i < horizontalPos.size(); i++) {
            float yawDeg = 360F / horizontalPos.size() * i;
            float vertRadius = GSKOMathUtil.sineSmoothPeriod(i, 10,0F, 3F);
            int vertCount = 12;
            for (int j = 0; j < vertCount; j++) {
                float pitchDeg = 360F / vertCount * j;
                Vector3d vertPos = new Vector3d(Vector3f.XP).scale(vertRadius)
                        .rotateYaw(Danmaku.rad(yawDeg))
                        .rotatePitch(Danmaku.rad(pitchDeg));
                posAndShootRot.add(Pair.of(horizontalPos.get(i), vertPos));
            }
        }

        posAndShootRot.forEach(pair -> {
            Danmaku danmaku = new Danmaku(world, ItemRegistry.CIRCLE_SHOT_BLUE.get(), youkai){
                @Override
                public void tick() {
                    super.tick();
                    if (this.ticksExisted > 50) {
                        Vector3d shoot = pair.getSecond().inverse();
                        this.shoot(shoot.x, shoot.y, shoot.z, 0.4F, 0);
                    }
                }
            }.pos(youkai.getPositionVec().add(pair.getFirst().add(pair.getSecond())));
            danmakuList.add(danmaku);
        });

        return danmakuList;
    };

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

    public static final YoukaiCombat.SkillAction<FlandreScarletEntity> LINEAR_SPIRAL = (world, flandre) -> {
        if (flandre.ticksExisted % 11 != 0) return;
        Vector3d start = GSKOMathUtil.randomVec(-6, 0);
        Vector3d end = GSKOMathUtil.randomVec(3, 6);

        for (int i = 0; i < 18; i++) {
            float progress = i * (1F / 18);
            float angleDeg = 360 * progress;
            Vector3d currentPos = GSKOMathUtil.lerp(progress , start, end);
            Vector3d shoot = end.subtract(start).rotateYaw(Danmaku.rad(angleDeg));

            Danmaku.create(world, flandre, ItemRegistry.RICE_SHOT_PURPLE.get())
                    .pos(flandre.getPositionVec().add(currentPos))
                    .rot(Rot2f.from(shoot))
                    .shoot(shoot, 0.4F);
        }
    };

    public static final YoukaiCombat.SkillAction<FlandreScarletEntity> WAVE_PACKET = (world, flandre) -> {
        if (flandre.ticksExisted % 400 > 200) {
            List<Danmaku> danmakuList = WAVE_PACKET_DANMAKU.apply(world, flandre);
            danmakuList.get(GSKOMathUtil.clampPeriod(flandre.ticksExisted, 0, danmakuList.size() - 1))
                    .addToWorld(world);
        }
    };
}
