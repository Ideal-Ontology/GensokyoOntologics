package github.thelawf.gensokyoontology.common.entity.monster;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FlandreScarletEntity extends TameableEntity implements IAngerable {
    public static final EntityType<FlandreScarletEntity> FLANDRE_SCARLET = EntityType.Builder.create(
                    FlandreScarletEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
            .size(0.6f, 1.5f).trackingRange(10).build("flandre_scarlet");

    protected FlandreScarletEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.4f));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 0.8f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
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
