package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class FairyEntity extends MonsterEntity {

    private static final int MAX_LIVING_TICK = 3000;

    public static final EntityType<FairyEntity> FAIRY = EntityType.Builder.<FairyEntity>create(
            FairyEntity::new, EntityClassification.MONSTER).updateInterval(2)
            .size(0.6f, 1.5f).trackingRange(10).build("fairy");

    public FairyEntity(EntityType<? extends MonsterEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.getAttributeManager().createInstanceIfAbsent(Attributes.MAX_HEALTH);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 0.8f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    public void onKillEntity(ServerWorld world, LivingEntity killedEntity) {
        super.onKillEntity(world, killedEntity);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.ticksExisted >= MAX_LIVING_TICK) {
            this.remove();
        }
    }

    public static class FlyingCasuallyGoal extends Goal {

        private final FairyEntity fairy;

        public FlyingCasuallyGoal(FairyEntity fairy) {
            this.fairy = fairy;
        }

        @Override
        public boolean shouldExecute() {
            return false;
        }
    }

    public static class FlyToPlayerGoal extends Goal {
        public FlyToPlayerGoal() {
            super();
        }

        @Override
        public boolean shouldExecute() {
            return false;
        }
    }

}
