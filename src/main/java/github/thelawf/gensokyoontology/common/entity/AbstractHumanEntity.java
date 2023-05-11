package github.thelawf.gensokyoontology.common.entity;

import com.mojang.serialization.Dynamic;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.INPC;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class AbstractHumanEntity extends AgeableEntity implements INPC, IMerchant {
    protected AbstractHumanEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.3D));
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
    }

    @Override
    protected Brain<?> createBrain(Dynamic<?> dynamicIn) {
        return super.createBrain(dynamicIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void setCustomer(@Nullable PlayerEntity player) {
    }

    @Nullable
    @Override
    public PlayerEntity getCustomer() {
        return null;
    }

    @Override
    public MerchantOffers getOffers() {
        return null;
    }

    @Override
    public void setClientSideOffers(@Nullable MerchantOffers offers) {

    }

    @Override
    public void onTrade(MerchantOffer offer) {

    }

    @Override
    public void verifySellingItem(ItemStack stack) {

    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public int getXp() {
        return 0;
    }

    @Override
    public void setXP(int xpIn) {

    }

    @Override
    public boolean hasXPBar() {
        return false;
    }

    @Override
    public SoundEvent getYesSound() {
        return null;
    }
}
