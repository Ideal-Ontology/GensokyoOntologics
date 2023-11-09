package github.thelawf.gensokyoontology.common.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public abstract class YoukaiEntity extends RetreatableEntity {

    /** 是否被退治 */
    protected boolean isRetreated = false;
    /** 好感度 */
    protected int favorability = 0;
    public static final DataParameter<Boolean> DATA_RETREATED = EntityDataManager.createKey(
            YoukaiEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> DATA_FAVORABILITY = EntityDataManager.createKey(YoukaiEntity.class, DataSerializers.VARINT);

    protected YoukaiEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_RETREATED, this.isRetreated);
        this.dataManager.register(DATA_FAVORABILITY, this.favorability);
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

    public int getFavorability() {
        return this.dataManager.get(DATA_FAVORABILITY);
    }

    public void setFavorability(int favorabilityIn) {
        this.dataManager.set(DATA_FAVORABILITY, favorabilityIn);
    }

    public void setRetreated(boolean isRetreated) {
        this.dataManager.set(DATA_RETREATED, isRetreated);
    }

    public boolean isRetreated () {
        return this.dataManager.get(DATA_RETREATED);
    }

}
