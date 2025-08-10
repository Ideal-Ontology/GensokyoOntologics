package github.thelawf.gensokyoontology.common.entity.combat;

import github.thelawf.gensokyoontology.api.entity.EntityBehavior;
import github.thelawf.gensokyoontology.common.entity.projectile.RiceShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;

public class SpellBehavior {
    public static final EntityBehavior.SkillAction<SpellCardEntity> CHERRY_BLOOSM_SPELL = (world, spellCard) -> {
        LivingEntity living = (LivingEntity) spellCard.getOwner();
        if(living == null) return;

        List<Vector3d> roseLinePos = DanmakuUtil.getRoseLinePos(1.2, 3, 2, 0.05);
        if (living.ticksExisted % 20 == 0) {
            for (Vector3d vector3d : roseLinePos) {
                RiceShotEntity riceShot = new RiceShotEntity(living, world, DanmakuType.RICE_SHOT, DanmakuColor.PURPLE);
                Vector3d shootVec = new Vector3d(vector3d.x, vector3d.y, vector3d.z);
                shootVec = DanmakuUtil.rotateRandomAngle(shootVec, (float) Math.PI * 2, (float) Math.PI * 2);
                vector3d = vector3d.add(DanmakuUtil.getRandomPosWithin(3.5f, DanmakuUtil.Plane.XYZ));
                vector3d = vector3d.add(spellCard.getPositionVec());

                DanmakuUtil.initDanmaku(riceShot, vector3d, new Vector2f((float) vector3d.x, (float) vector3d.y), true);
                riceShot.shoot(shootVec.x, shootVec.y, shootVec.z, 0.3f, 0f);
                world.addEntity(riceShot);
            }
        }
    };
}
