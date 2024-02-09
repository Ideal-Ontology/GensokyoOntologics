package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.common.entity.ai.goal.DamakuAttackGoal;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class SpectreEntity extends RetreatableEntity implements IRendersAsItem, IFlyingAnimal {

    public SpectreEntity(EntityType<? extends RetreatableEntity> type, World worldIn) {
        super(type, worldIn);
        this.setNoGravity(true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new DamakuAttackGoal(this, 30, 0.6f));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 0.8f));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        super.registerGoals();
    }

    @Override
    public void tick() {
        super.tick();
        Vector3d pos = this.getPositionVec().add(new Vector3d(0.2,0.2,0.2)).add(new Vector3d(
                GSKOMathUtil.randomRange(-0.2,0.2), GSKOMathUtil.randomRange(-0.2,0.2), GSKOMathUtil.randomRange(-0.2,0.2)));
        this.world.addParticle(ParticleTypes.CLOUD, pos.x, pos.y, pos.z, GSKOMathUtil.randomRange(-0.1,0.1), GSKOMathUtil.randomRange(-0.1,0.1), GSKOMathUtil.randomRange(-0.1,0.1));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.WANDERING_SOUL.get());
    }

    @Override
    public void setNoGravity(boolean noGravity) {
        super.setNoGravity(true);
    }

    @Override
    @NotNull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static boolean canMonsterSpawnInLight(EntityType<SpectreEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && isValidLightLevel(worldIn, pos, randomIn) && canSpawnOn(type, worldIn, reason, pos, randomIn);
    }
    public static boolean isValidLightLevel(IServerWorld worldIn, BlockPos pos, Random randomIn) {
        if (worldIn.getLightFor(LightType.SKY, pos) > randomIn.nextInt(32)) {
            return false;
        } else {
            int i = worldIn.getWorld().isThundering() ? worldIn.getNeighborAwareLightSubtracted(pos, 10) : worldIn.getLight(pos);
            return i <= randomIn.nextInt(8);
        }
    }

    @Override
    public void danmakuAttack(LivingEntity target) {
        if (ticksExisted % 10 == 0) {
            Vector3d direction = new Vector3d(target.getPosX() - this.getPosX(), target.getPosY() - this.getPosY(), target.getPosZ() - this.getPosZ());
            SmallShotEntity danmaku = new SmallShotEntity(this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.BLUE);
            DanmakuUtil.initDanmaku(danmaku, this.getPositionVec(), true);
            danmaku.shoot(direction.x, direction.y, direction.z, 0.78f, 0f);
            this.world.addEntity(danmaku);
        }
    }

    private <D extends AbstractDanmakuEntity> void aimedShot(LivingEntity target) {
    }
}
