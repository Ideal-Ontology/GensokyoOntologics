package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;

public class FairyEntity extends MonsterEntity {

    public static final EntityType<FairyEntity> FAIRY_ENTITY = EntityType.Builder.create(
            FairyEntity::new, EntityClassification.CREATURE)
            .size(0.6f, 1.5f).trackingRange(10).build("fairy");

    public FairyEntity(EntityType<FairyEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 0.8f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerData() {
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return null;
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
