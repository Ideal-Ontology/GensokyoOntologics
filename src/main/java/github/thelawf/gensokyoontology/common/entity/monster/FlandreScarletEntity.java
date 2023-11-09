package github.thelawf.gensokyoontology.common.entity.monster;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FlandreScarletEntity extends TameableEntity implements IAngerable {

    private boolean isRetreated = false;
    public static final DataParameter<Boolean> DATA_IS_RETREATED = EntityDataManager.createKey(
            FlandreScarletEntity.class, DataSerializers.BOOLEAN);

    public static final EntityType<FlandreScarletEntity> FLANDRE_SCARLET = EntityType.Builder.create(
                    FlandreScarletEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
            .size(0.6f, 1.5f).trackingRange(10).build("flandre_scarlet");

    protected FlandreScarletEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_IS_RETREATED, this.isRetreated);
    }

    @Override
    protected void onDeathUpdate() {
        super.onDeathUpdate();
    }

    @Override
    public void onDeath(@NotNull DamageSource cause) {
        if (!this.isRetreated) {
            this.setHealth(this.getMaxHealth());
            this.setOwnerId(cause.getTrueSource() instanceof PlayerEntity && cause.getTrueSource() == null ?
                    cause.getTrueSource().getUniqueID() : null);
            if (this.getOwnerId() != null) this.setRetreated(true);
            return;
        }
        super.onDeath(cause);
    }

    public void setRetreated(boolean isRetreated) {
        this.dataManager.set(DATA_IS_RETREATED, isRetreated);
    }

    public boolean isRetreated () {
        return this.isRetreated;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SitGoal(this));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.4f));

        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 0.8f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, CreatureEntity.class)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, TsumiBukuroEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(5, new ResetAngerGoal<>(this, true));
    }

    @Override
    @NotNull
    public ActionResultType getEntityInteractionResult(@NotNull PlayerEntity playerIn, @NotNull Hand hand) {
        return super.getEntityInteractionResult(playerIn, hand);
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
