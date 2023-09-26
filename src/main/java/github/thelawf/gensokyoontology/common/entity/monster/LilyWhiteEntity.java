package github.thelawf.gensokyoontology.common.entity.monster;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.ISpellCardUser;
import github.thelawf.gensokyoontology.common.entity.ai.goal.BossBattleGoal;
import github.thelawf.gensokyoontology.common.entity.ai.goal.LilyWhiteBossBattleGoal;
import github.thelawf.gensokyoontology.common.entity.projectile.RiceShotEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.FullCherryBlossomEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import github.thelawf.gensokyoontology.common.util.SpellCards;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class LilyWhiteEntity extends TameableEntity implements IAngerable, ISpellCardUser {
    public static final EntityType<LilyWhiteEntity> LILY_WHITE = EntityType.Builder.create(
                    LilyWhiteEntity::new, EntityClassification.CREATURE).updateInterval(2)
            .size(0.6f, 1.5f).trackingRange(10).build("lily_white");

    public LilyWhiteEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        List<BossBattleGoal.Stage> stages = new ArrayList<>();
        stages.add(new BossBattleGoal.Stage(BossBattleGoal.Type.NON_SPELL, new FullCherryBlossomEntity(world, this), 500, true));
        // stages.put(BossBattleGoal.Type.SPELL_CARD_BREAKABLE, Pair.of(50f, 2000));

        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LilyWhiteBossBattleGoal(this, stages, 0.4f));
        this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void spellCardAttack(SpellCardEntity spellCard, int ticksIn) {
        if (spellCard == null) {
            return;
        }

        if (ticksIn % 20 == 0) {
            // spellCard.onTick(ticksIn);

            List<Vector3d> roseLinePos = DanmakuUtil.getRoseLinePos(1.2, 3, 2, 0.05);

            for (Vector3d vector3d : roseLinePos) {
                RiceShotEntity riceShot = new RiceShotEntity(this, world, DanmakuType.RICE_SHOT, DanmakuColor.PURPLE);
                Vector3d shootVec = new Vector3d(vector3d.x, vector3d.y, vector3d.z);
                shootVec = DanmakuUtil.rotateRandomAngle(shootVec, (float) Math.PI * 2, (float) Math.PI * 2);
                vector3d = vector3d.add(DanmakuUtil.getRandomPosWithin(3.5f, DanmakuUtil.Plane.XYZ));
                vector3d = vector3d.add(this.getPositionVec());

                DanmakuUtil.initDanmaku(riceShot, vector3d, new Vector2f((float) vector3d.x, (float) vector3d.y), true);
                riceShot.shoot(shootVec.x, shootVec.y, shootVec.z, 0.3f, 0f);
                world.addEntity(riceShot);
            }
        }

    }

    @Nullable
    @Override
    public AgeableEntity createChild(@NotNull ServerWorld world, @NotNull AgeableEntity mate) {
        return null;
    }

    @Override
    @NotNull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public int getAngerTime() {
        return 0;
    }

    @Override
    public void setAngerTime(int time) {

    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return null;
    }

    @Override
    public void setAngerTarget(@Nullable UUID target) {

    }

    @Override
    public void func_230258_H__() {

    }
}
