package github.thelawf.gensokyoontology.common.entity;

import github.thelawf.gensokyoontology.api.entity.ISpellCardUser;
import github.thelawf.gensokyoontology.api.dialog.DialogTreeNode;
import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class KoishiEntity extends ConversationalEntity implements ISpellCardUser {

    private int angerTime;
    private UUID angerTarget;
    private static final DataParameter<Integer> DATA_FAVORABILITY = EntityDataManager.createKey(KoishiEntity.class, DataSerializers.VARINT);
    public static final String KEY_FAVORABILITY = "favourability";

    public static final EntityType<KoishiEntity> KOISHI = EntityType.Builder.create(
                    KoishiEntity::new, EntityClassification.CREATURE).updateInterval(2)
            .size(0.6f, 1.5f).trackingRange(10).build("koishi");

    protected KoishiEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_FAVORABILITY,0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4f));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 0.8f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    public void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt(KEY_FAVORABILITY, getFavorability());
    }

    @Override
    public void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    public int getAngerTime() {
        return this.angerTime;
    }

    @Override
    public void setAngerTime(int time) {
        this.angerTime = time;
    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return this.angerTarget;
    }

    @Override
    public void setAngerTarget(@Nullable UUID target) {
        this.angerTarget = target;
    }

    @Override
    public void func_230258_H__() {

    }

    private int getFavorability() {
        return this.dataManager.get(DATA_FAVORABILITY);
    }

    @Override
    @NotNull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public DialogTreeNode getNextDialog(int optionIndex) {
        return optionIndex == 0 ? new DialogTreeNode("root").accessBranch(optionIndex) :
                new DialogTreeNode("root");
    }

    @Override
    public void spellCardAttack(SpellCardEntity spellCard, int ticksIn) {

    }
}
