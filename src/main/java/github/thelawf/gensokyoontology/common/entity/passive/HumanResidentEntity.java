package github.thelawf.gensokyoontology.common.entity.passive;

import github.thelawf.gensokyoontology.common.capability.entity.VillagerOrder;
import github.thelawf.gensokyoontology.common.entity.trade.GSKOTrades;
import net.minecraft.command.impl.data.EntityDataAccessor;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.MerchantOffer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class HumanResidentEntity extends AbstractVillagerEntity {
    public static final DataParameter<Integer> DATA_GENDER = EntityDataManager.createKey(HumanResidentEntity.class,
            DataSerializers.VARINT);
    public static final DataParameter<CompoundNBT> DATA_ORDER = EntityDataManager.createKey(HumanResidentEntity.class,
            DataSerializers.COMPOUND_NBT);

    public VillagerOrder order = new VillagerOrder();
    public Gender gender = HumanResidentEntity.randomGender();

    public HumanResidentEntity(EntityType<HumanResidentEntity> type, World worldIn) {
        super(type, worldIn);
        this.gender = randomGender();
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return super.createNavigator(worldIn);
    }


    @Override
    protected void onVillagerTrade(MerchantOffer offer) {
    }

    public static Gender randomGender(){
        Random random = new Random();
        return random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
    }

    public VillagerOrder getOrder() {
        return VillagerOrder.decode(this.dataManager.get(DATA_ORDER));
    }

    public void setOrder(VillagerOrder order) {
        this.order = order;
        this.dataManager.set(DATA_ORDER, order.serializeNBT());
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

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_GENDER, randomGender().ordinal());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("gender")) this.setGenderOrdinal(compound.getInt("gender"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("gender", this.getGenderOrdinal());
    }

    @Override
    protected ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
        if (!this.isAlive() || this.hasCustomer() || this.isSleeping()) return super.getEntityInteractionResult(playerIn, hand);
        if (this.getOffers().isEmpty()) return ActionResultType.func_233537_a_(this.world.isRemote);

        if (!this.world.isRemote) {
            this.setCustomer(playerIn);
            this.openMerchantContainer(playerIn, this.getDisplayName(), 1);
            return ActionResultType.func_233537_a_(this.world.isRemote);
        }
        return super.getEntityInteractionResult(playerIn, hand);
    }

    @Override
    protected void populateTradeData() {
        this.addTrades(this.getOffers(), GSKOTrades.HUMAN_FARMER_TRADE, 2);
    }

    public enum Gender{
        MALE,
        FEMALE
    }
}
