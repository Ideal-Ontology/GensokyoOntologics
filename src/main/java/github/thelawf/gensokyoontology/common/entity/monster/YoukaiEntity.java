package github.thelawf.gensokyoontology.common.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Predicate;

public abstract class YoukaiEntity extends RetreatableEntity {

    /**
     * 是否被退治
     */
    protected boolean isRetreated = false;
    /**
     * 好感度
     */
    protected int favorability = 0;
    protected boolean duringSpellCard = false;
    private String battlePhase = "1.1";

    // @OnlyIn(Dist.CLIENT)
    // private Animation animation = Animation.IDLE;
    public static final DataParameter<String> DATA_PHASE = EntityDataManager.createKey(YoukaiEntity.class,
            DataSerializers.STRING);
    public static final DataParameter<Boolean> DATA_RETREATED = EntityDataManager.createKey(
            YoukaiEntity.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> DATA_FAVORABILITY = EntityDataManager.createKey(YoukaiEntity.class, DataSerializers.VARINT);

    protected YoukaiEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        this.setBattlePhase("1.1");
    }


    /**
     * 怪不得继承自YoukaiEntity的实体死不了呢（）原来是我之前在这里判断如果战胜了妖怪则将她驯服了啊（
     *
     */
    @Override
    public void onDeath(@NotNull DamageSource cause) {
        super.onDeath(cause);
    }

    public int getFavorability() {
        return this.dataManager.get(DATA_FAVORABILITY);
    }

    public void setFavorability(int favorabilityIn) {
        this.dataManager.set(DATA_FAVORABILITY, favorabilityIn);
    }

    public void setRetreated(boolean isRetreated) {
        this.dataManager.set(DATA_RETREATED, isRetreated);
    }

    public boolean isRetreated() {
        return this.dataManager.get(DATA_RETREATED);
    }

    public boolean isDuringSpellCardAttack(boolean isDuringSpellCardAttack){
        this.duringSpellCard = isDuringSpellCardAttack;
        return duringSpellCard;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_RETREATED, this.isRetreated);
        this.dataManager.register(DATA_FAVORABILITY, this.favorability);
        this.dataManager.register(DATA_PHASE, "1.1");
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setBattlePhase(compound.getString("phase"));
    }


    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("phase", this.battlePhase);
    }

    public String getBattlePhase() {
        return this.dataManager.get(DATA_PHASE);
    }

    public void setBattlePhase(int mainPhase, int subPhase) {
        this.battlePhase = mainPhase + "." + subPhase;
        this.dataManager.set(DATA_PHASE, mainPhase + "." + subPhase);
    }

    private void setBattlePhase(String battlePhase) {
        this.battlePhase = battlePhase;
        this.dataManager.set(DATA_PHASE, battlePhase);
    }

    public void nextPhase(){
        int mainPhase = this.getMainPhase();
        int subPhase = this.getSubPhase();
        if ((subPhase + 1) > this.getMaxPhases()[mainPhase - 1].length) {
            if ((mainPhase + 1) > this.getMaxPhases().length) return;
            this.setBattlePhase(++mainPhase, + 1);
        }
        else {
            this.setBattlePhase(mainPhase, ++subPhase);
        }
    }

    public int getMainPhase(){
        String phase = this.getBattlePhase();
        if (phase.equals("")) return 1;
        return Integer.parseInt(phase.split("\\.")[0]);
    }

    public int getSubPhase() {
        String phase = this.getBattlePhase();
        if (phase.equals("")) return 1;
        return Integer.parseInt(phase.split("\\.")[1]);
    }

    public int[][] getMaxPhases(){
        return new int[0][];
    }
    public boolean isPhaseMatches(String phase){
        return this.getBattlePhase().equals(phase);
    }

    public boolean isPhaseMatches(int main, int sub){
        return this.getMainPhase() == main & this.getSubPhase() == sub;
    }

    @Override
    public void onKillCommand() {
        this.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
    }

    public abstract void danmakuAttack(LivingEntity target);

}
