package github.thelawf.gensokyoontology.common.entity.passive;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import github.thelawf.gensokyoontology.common.capability.entity.VillagerOrder;
import github.thelawf.gensokyoontology.common.entity.trade.GSKOTrades;
import net.minecraft.command.impl.data.EntityDataAccessor;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTDynamicOps;
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

/**
 * StayNearPointTask.field_220549_b -> walkingSpeed （行走速度）<br>
 * StayNearPointTask.field_220550_c -> distance （目标行走距离）<br>
 * StayNearPointTask.field_220551_d -> maxDistance （最大巡航距离）
 * */
public class HumanResidentEntity extends AbstractVillagerEntity {
    public static final DataParameter<Integer> DATA_GENDER = EntityDataManager.createKey(HumanResidentEntity.class,
            DataSerializers.VARINT);
    public static final DataParameter<VillagerData> DATA_VILLAGER = EntityDataManager.createKey(HumanResidentEntity.class,
            DataSerializers.VILLAGER_DATA);
    public static final DataParameter<CompoundNBT> DATA_ORDER = EntityDataManager.createKey(HumanResidentEntity.class,
            DataSerializers.COMPOUND_NBT);

    private VillagerOrder order = new VillagerOrder();
    private Gender gender;

    public HumanResidentEntity(EntityType<HumanResidentEntity> type, World worldIn) {
        super(type, worldIn);
        this.gender = randomGender();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
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
    }

    public static VillagerProfession randomProf(){
        Random random = new Random();
        switch (random.nextInt(3)){
            case 0:
                return VillagerProfession.MASON;
            default:
            case 1:
                return VillagerProfession.FARMER;
        }
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("VillagerData", 10)) {
            DataResult<VillagerData> dataresult = VillagerData.CODEC.parse(new Dynamic<>(NBTDynamicOps.INSTANCE, compound.get("VillagerData")));
            dataresult.resultOrPartial(LOGGER::error).ifPresent(this::setVillagerData);
        }

        if (compound.contains("gender")) this.setGenderOrdinal(compound.getInt("gender"));
        if (compound.contains("Offers", 10)) this.offers = new MerchantOffers(compound.getCompound("Offers"));

    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("gender", this.getGenderOrdinal());
        MerchantOffers offers = this.getOffers();
        if (!offers.isEmpty()) compound.put("Offers", offers.write());

        VillagerData.CODEC.encodeStart(NBTDynamicOps.INSTANCE, this.getVillagerData()).resultOrPartial(LOGGER::error).ifPresent((data) -> {
            compound.put("VillagerData", data);
        });
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

    private VillagerProfession getProf(){
        return this.getVillagerData().getProfession();
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
