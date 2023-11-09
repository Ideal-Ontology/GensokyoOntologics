package github.thelawf.gensokyoontology.common.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public abstract class YoukaiEntity extends RetreatableEntity {

    private boolean isRetreated = false;
    public static final DataParameter<Boolean> DATA_IS_RETREATED = EntityDataManager.createKey(
            YoukaiEntity.class, DataSerializers.BOOLEAN);

    protected YoukaiEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_IS_RETREATED, this.isRetreated);
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

}
