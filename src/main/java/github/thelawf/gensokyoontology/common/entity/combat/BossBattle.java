package github.thelawf.gensokyoontology.common.entity.combat;

import github.thelawf.gensokyoontology.api.entity.YoukaiCombat;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.entity.monster.*;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.Rot2f;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class BossBattle {
    public static final int MAX_DISTANCE = 100;

    public static final Consumer<EntityRayTraceResult> ON_CRYSTAL_HIT = ray -> {
        if (ray.getType() != EntityRayTraceResult.Type.ENTITY) return;
        if (ray.getEntity() instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) ray.getEntity();
            living.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 5, 1));
        }
    };

    /** 在Goal中添加该空白阶段以便让玩家拥有反击BOSS的喘息时间，降低玩家BOSS战生存难度。 */
    public static final YoukaiCombat.SkillAction<YoukaiEntity> BLANK_PHASE = (world, youkaiEntity) -> {};

    public static final YoukaiCombat.TargetAction<RumiaEntity> DARK_SPHERE = (world, rumia, target) -> {
        if (target == null) return;
        if (rumia.ticksExisted % 10 != 0) return;
        Random random = new Random();
        boolean greenOrBlue = random.nextBoolean();
        Vector3d randPos = GSKOMathUtil.randomVec(-2, 2);

        DanmakuUtil.ellipticPos(Vector2f.ZERO, 1.5F, 12).forEach(vector3d ->
                Danmaku.create(world, rumia, greenOrBlue ? ItemRegistry.SMALL_SHOT_BLUE.get() : ItemRegistry.SMALL_SHOT_GREEN.get())
                        .pos(rumia.getPositionVec().add(randPos).add(vector3d))
                        .shootTo(target, 0.5F));

        if (rumia.ticksExisted % 35 != 0) return;
        DanmakuUtil.oddCurveVec(rumia, target, 5, 30).forEach(vector3d ->
                Danmaku.create(world, rumia, ItemRegistry.DESTRUCTIVE_EYE.get())
                        .damage(5F)
                        .shoot(vector3d, 0.2F));
    };

    public static final YoukaiCombat.TargetAction<RumiaEntity> WALL_SHOOT_RUMIA = (world, rumia, target) -> {
        if (target == null) return;
        if (rumia.ticksExisted % 20 != 0) return;
        for (int i = -4; i <= 4; i++) {
            for (int j = -4; j < 4; j++) {
                Vector3d shootVec = DanmakuUtil.getAimingAt(rumia, target)
                        .rotateYaw(Danmaku.rad(j * 5))
                        .add(0, i * 0.1, 0)
                        .normalize();
                Danmaku.create(world, rumia, ItemRegistry.SMALL_SHOT_BLUE.get())
                        .damage(3F)
                        .shoot(shootVec, 0.6F);

                // DanmakuUtil.wallShoot(danmaku, DanmakuUtil.getAimedVec(rumia, target), 5F, i, j, 0.65F);
            }
        }
    };

    public static final YoukaiCombat.TimerAction<RumiaEntity> DARK_BORDER_LINE = (world, rumiaEntity, target, currentTimer) -> {
        if (target == null) return;
        if (rumiaEntity.ticksExisted % 7 != 0) return;
        int unit = currentTimer.get() % 10;
        int increment = currentTimer.get() % 10 > 5 ? 3 * unit : -3 * unit;
        Vector3d vector3d = DanmakuUtil.getAimingAt(rumiaEntity, target).rotateYaw(Danmaku.rad(increment) * rumiaEntity.ticksExisted);

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j < 2; j++) {
                Vector3d shootVec = vector3d
                        .rotateYaw(Danmaku.rad(j * 5))
                        .add(0, i * 0.1, 0).normalize();
                Danmaku.create(world, rumiaEntity, ItemRegistry.SMALL_SHOT_GREEN.get())
                        .damage(3F)
                        .shoot(shootVec, 0.55F);
            }
        }
        currentTimer.set(currentTimer.get() + 1);
    };

    public static final YoukaiCombat.TargetAction<CirnoEntity> PERFECT_FREEZE = (world, cirnoEntity, target) -> {

    };

    public static final YoukaiCombat.TargetAction<CirnoEntity> ICY_STORM = (world, youkai, target) -> {
        if (youkai.ticksExisted % 11 != 0) return;

        Vector3d randPos = GSKOMathUtil.randomVec(-3, 3);
        DanmakuUtil.spheroidPos(1, 8).forEach(shoot -> Danmaku.create(world, youkai, ItemRegistry.CRYSTAL_AQUA.get())
                .rot(Rot2f.from(shoot))
                .pos(randPos)
                .onHit(ON_CRYSTAL_HIT)
                .shoot(shoot, 0.4F));
    };

    /** 案例：周期往复运动 */
    public static final YoukaiCombat.TimerAction<CirnoEntity> CRYSTAL_TUNNEL = (world, cirno, target, timer) -> {
        if (target == null) return;
        if (cirno.ticksExisted % 2 != 0) return;
        boolean shouldTurnBack = cirno.ticksExisted % 140 >= 70;
        int prev = timer.get();
        timer.set(shouldTurnBack ? --prev : ++prev);
        int increment = 5 * timer.get();

        Vector3d initPos = new Vector3d(Vector3f.ZP).scale(2F);
        List<Vector3d> list = DanmakuUtil.ellipticVeritcal(3F, 8);
        list.forEach( vector3d -> {
            Vector3d shoot = vector3d.rotateYaw(Danmaku.rad(increment * timer.get()));
            initPos.add(0, 0, increment);
            Danmaku.create(world, cirno, ItemRegistry.CRYSTAL_AQUA.get())
                    .pos(cirno.getPositionVec().add(initPos))
                    .onHit(ON_CRYSTAL_HIT)
                    .shoot(shoot, 0.45F);
        });
    };

    public static final YoukaiCombat.TimerAction<CirnoEntity> FROST_COLUMNS = (world, cirno, target, timer) -> {
        if (target == null) return;
        if (cirno.ticksExisted % 5 != 0) return;
        final float scale = 0.8F;
        int lifespan = timer.get();
        boolean direction = RandomUtils.nextBoolean();

        Vector3d randomStart = GSKOMathUtil.randomVec(-3, 3);
        Vector3d vector3d = DanmakuUtil.getAimingAt(cirno, target);
        if (direction) vector3d = vector3d.rotateYaw(Danmaku.rad(90));
        Vector3d curentPos = randomStart.add(vector3d.scale(scale));

        if (lifespan > 30) timer.set(0);
        if (lifespan <= 30) {
            DanmakuUtil.ellipticVeritcal(1.5F, 10).forEach(shoot -> {
                Danmaku.create(world, cirno, ItemRegistry.CRYSTAL_BLUE.get())
                        .pos(cirno.getPositionVec().add(curentPos))
                        .onHit(ON_CRYSTAL_HIT)
                        .shoot(shoot, 0.4F);
            });
            timer.set(++lifespan);
        }
    };
}
