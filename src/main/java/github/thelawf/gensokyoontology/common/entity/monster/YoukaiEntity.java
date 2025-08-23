package github.thelawf.gensokyoontology.common.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import org.jetbrains.annotations.NotNull;

/**
 * 妖怪实体内置Boss战斗阶段
 */
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
    public String battlePhase = "1.1";

    protected final ServerBossInfo bossBar;
    // @OnlyIn(Dist.CLIENT)
    // private Animation animation = Animation.IDLE;
    public static final DataParameter<String> DATA_PHASE = EntityDataManager.createKey(YoukaiEntity.class,
            DataSerializers.STRING);
    public static final DataParameter<Boolean> DATA_RETREATED = EntityDataManager.createKey(
            YoukaiEntity.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> DATA_FAVORABILITY = EntityDataManager.createKey(YoukaiEntity.class, DataSerializers.VARINT);

    protected YoukaiEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        this.bossBar = new ServerBossInfo(type.getName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS);
    }

    @Override
    public void tick() {
        super.tick();

        // 更新血条位置和血量
        if (!this.world.isRemote) {
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
            this.world.getEntitiesWithinAABB(ServerPlayerEntity.class, this.getBoundingBox().grow(50))
                    .forEach(player -> {
                        if (player instanceof ServerPlayerEntity) this.bossBar.addPlayer(player);
                    });
        }
    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        this.bossBar.removeAllPlayers();
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
        if (compound.contains("phase")) {
            this.dataManager.set(DATA_PHASE, compound.getString("phase"));
        } else {
            this.dataManager.set(DATA_PHASE, "1.1");
        }
    }


    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        // 直接使用DataManager中的值
        compound.putString("phase", this.getBattlePhase());
    }

    public String getBattlePhase() {
        return this.dataManager.get(DATA_PHASE);
    }

    public void setBattlePhase(int mainPhase, int subPhase) {
        this.dataManager.set(DATA_PHASE, mainPhase + "." + subPhase);
    }

    private void setBattlePhase(String battlePhase) {
        this.dataManager.set(DATA_PHASE, battlePhase);
    }

    /**
     * 该方法是{@link github.thelawf.gensokyoontology.common.entity.ai.goal.YoukaiBattlePhaseGoal YoukaiBattlePhaseGoal.java}中默认调用的方法，重写该方法以自定义你想实现的切换战斗阶段的逻辑。<br>
     * 核心思路：<br>
     * 1. 判断小阶段的值是否大于当前小阶段的数量<br>
     * 2. 如果大于最大值且允许进入下一个大阶段则进入下一个大阶段<br>
     * 3. 如果小于最大值调用该实体实现的如何进入下一个小阶段的函数<br>
     */
    public void nextPhase(){
        String currentPhase = this.getBattlePhase();
        String[] parts = currentPhase.split("\\.");

        if (parts.length != 2) {
            this.setBattlePhase(1, 1);
            return;
        }

        try {
            int main = Integer.parseInt(parts[0]);
            int sub = Integer.parseInt(parts[1]);
            int maxMain = this.getMaxPhases().length;

            if (maxMain == 0) return;
            if ((sub + 1) > this.getMaxPhases()[main - 1]) {
                if ((main + 1) > maxMain) {
                    this.setBattlePhase(main, 1);
                    return;
                }
                this.setBattlePhase(++main, 1);
            }
            else this.nextSubPhase();

        } catch (NumberFormatException e) {
            this.setBattlePhase(1, 1);
        }
    }

    public abstract void nextSubPhase();

    /**
     * 如果希望BOSS的下一个小阶段是从可选阶段中随机选择一个的话，可以使用该方法覆盖{@link YoukaiEntity#nextPhase() this.nextPhase}
     */
    public void nextRandomPhase(){
        String currentPhase = this.getBattlePhase();
        String[] parts = currentPhase.split("\\.");

        if (parts.length != 2) {
            this.setBattlePhase(1, 1);
            return;
        }

        try {
            final int main = Integer.parseInt(parts[0]);
            final int currentSubPhaseCount = this.getMaxPhases()[main - 1];
            this.setBattlePhase(main, this.rand.nextInt(currentSubPhaseCount) + 1);
        } catch (NumberFormatException e) {
            this.setBattlePhase(1, 1);
        }
    }

    public void loopSubPhase(){

    }

    /**
     * 在各个具体类中实现这个方法，以此来判断BOSS此时是否应该进入下一个主要阶段
     */
    public abstract boolean shouldEnterNextMainPhase();

    public int getMainPhase(){
        String phase = this.getBattlePhase();
        if (phase.isEmpty()) return 1;
        return Integer.parseInt(phase.split("\\.")[0]);
    }

    public int getSubPhase() {
        String phase = this.getBattlePhase();
        if (phase.isEmpty()) return 1;
        return Integer.parseInt(phase.split("\\.")[1]);
    }

    /**
     * @return 返回一个以数组长度为主阶段数量，数组元素为每个主阶段内小阶段数量的整数数组
     */
    public int[] getMaxPhases(){
        return new int[]{3};
    }

    public int getMaxMainPhase(){
        return this.getMaxPhases().length - 1;
    }

    public int getMaxSubPhase(){
        return this.getMaxPhases()[this.getMainPhase()];
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
