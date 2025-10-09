package github.thelawf.gensokyoontology.common.entity.passive;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import github.thelawf.gensokyoontology.common.capability.entity.VillagerOrder;
import github.thelawf.gensokyoontology.common.entity.ai.brain.BrainUtils;
import github.thelawf.gensokyoontology.common.entity.trade.GSKOTrades;
import github.thelawf.gensokyoontology.data.GSKOSerializers;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.schedule.Schedule;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * StayNearPointTask.field_220549_b -> walkingSpeed （行走速度）<br>
 * StayNearPointTask.field_220550_c -> distance （目标行走距离）<br>
 * StayNearPointTask.field_220551_d -> maxDistance （最大巡航距离）
 */
public class HumanResidentEntity extends VillagerEntity {
    public static final DataParameter<Integer> DATA_GENDER = EntityDataManager.createKey(HumanResidentEntity.class,
            DataSerializers.VARINT);
    public static final DataParameter<VillagerData> DATA_VILLAGER = EntityDataManager.createKey(HumanResidentEntity.class,
            DataSerializers.VILLAGER_DATA);
    public static final DataParameter<VillagerOrder> DATA_ORDER = EntityDataManager.createKey(HumanResidentEntity.class,
            GSKOSerializers.VILLAGER_ORDER);

    private VillagerOrder order = new VillagerOrder();
    private Gender gender;

    public HumanResidentEntity(EntityType<HumanResidentEntity> type, World worldIn) {
        super(type, worldIn);
        this.gender = randomGender();
        ((GroundPathNavigator)this.getNavigator()).setBreakDoors(true);
        this.getNavigator().setCanSwim(true);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }


    public void resetBrain(ServerWorld serverWorldIn) {
        Brain<VillagerEntity> brain = this.getBrain();
        brain.stopAllTasks(serverWorldIn, this);
        this.brain = brain.copy();
        this.initBrain(brain);
    }

    public void initBrain(Brain<VillagerEntity> brain) {
        brain.setSchedule(Schedule.VILLAGER_DEFAULT);
        brain.registerActivity(Activity.CORE, BrainUtils.CORE);
        brain.registerActivity(Activity.IDLE, BrainUtils.idle());
        brain.registerActivity(Activity.REST, BrainUtils.rest());

        brain.setPersistentActivities(ImmutableSet.of(Activity.IDLE));
        brain.setFallbackActivity(Activity.IDLE);
        brain.switchTo(Activity.IDLE);
        brain.updateActivity(this.world.getDayTime(), this.world.getGameTime());
    }

    @Override
    protected void updateAITasks() {
        this.world.getProfiler().startSection("humanBrain");
        this.getBrain().tick((ServerWorld)this.world, this);
        this.world.getProfiler().endSection();
        super.updateAITasks();
    }


    public static Gender randomGender(){
        Random random = new Random();
        return random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
    }

    public VillagerOrder getOrder() {
        return this.dataManager.get(DATA_ORDER);
    }

    public void setOrder(VillagerOrder order) {
        this.order = order;
        this.dataManager.set(DATA_ORDER, order);
    }

    public Gender getGender() {
        return Gender.values()[this.dataManager.get(DATA_GENDER)];
    }

    private int getGenderOrdinal(){
        return this.dataManager.get(DATA_GENDER);
    }

    public void setGender(Gender gender) {
        this.dataManager.set(DATA_GENDER, gender.ordinal());
        this.gender = gender;
    }

    private void setGenderOrdinal(int genderOrdinal){
        this.dataManager.set(DATA_GENDER, genderOrdinal);
        this.gender = Gender.values()[genderOrdinal];
    }

    public void setVillagerData(VillagerData data) {
        VillagerData villagerdata = this.getVillagerData();
        if (villagerdata.getProfession() != data.getProfession()) {
            this.offers = null;
        }

        this.dataManager.set(DATA_VILLAGER, data);
    }

    public VillagerData getVillagerData() {
        return this.dataManager.get(DATA_VILLAGER);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_GENDER, randomGender().ordinal());
        this.dataManager.register(DATA_VILLAGER, new VillagerData(VillagerType.PLAINS, randomProf(), 1));
        this.dataManager.register(DATA_ORDER, new VillagerOrder());
    }

    public static VillagerProfession randomProf(){
        Random random = new Random();
        switch (random.nextInt(3)){
            case 0:
                return VillagerProfession.MASON;
            case 1:
            default:
                return VillagerProfession.FARMER;
        }
    }

    @Override
    public void readAdditional(CompoundNBT compound) {

        if (compound.contains("Order")) this.setOrder(VillagerOrder.deserialize(compound.getCompound("Order")));
        if (compound.contains("gender")) this.setGenderOrdinal(compound.getInt("gender"));
        if (compound.contains("Offers", 10)) this.offers = new MerchantOffers(compound.getCompound("Offers"));

        if (this.world instanceof ServerWorld) {
            this.resetBrain((ServerWorld) this.world);
        }
        super.readAdditional(compound);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        compound.putInt("gender", this.getGenderOrdinal());
        MerchantOffers offers = this.getOffers();
        if (!offers.isEmpty()) compound.put("Offers", offers.write());

        compound.put("Order", this.getOrder().serializeNBT());
        super.writeAdditional(compound);
    }

    @Override
    public ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        if (!this.isAlive() || this.hasCustomer() || this.isSleeping()) return super.getEntityInteractionResult(playerIn, hand);
        if (this.getOffers().isEmpty()) return ActionResultType.func_233537_a_(this.world.isRemote);

        if (!this.world.isRemote) {
            this.setCustomer(playerIn);
            this.openMerchantContainer(playerIn, this.getDisplayName(), 1);
            return ActionResultType.func_233537_a_(this.world.isRemote);
        }
        return super.getEntityInteractionResult(playerIn, hand);
    }

    private VillagerProfession getProf(){
        return this.getVillagerData().getProfession();
    }

    @Override
    protected void onVillagerTrade(MerchantOffer offer) {
    }

    @Override
    protected void populateTradeData() {
        if (this.getProf() == VillagerProfession.NONE) return;
        this.addTrades(this.getOffers(), GSKOTrades.TRADE_MAP.get(this.getProf()), GSKOTrades.TRADE_MAP.get(this.getProf()).length);
    }

    public enum Gender{
        MALE,
        FEMALE
    }


}
