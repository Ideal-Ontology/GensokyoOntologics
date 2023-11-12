package github.thelawf.gensokyoontology.common.entity.projectile;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;

public class MasterSparkEntity extends ThrowableEntity {
    public final int LIFE_SPAN = 200;
    public static final EntityType<MasterSparkEntity> MASTER_SPARK = EntityType.Builder.<MasterSparkEntity>create(
                    MasterSparkEntity::new, EntityClassification.MISC).size(2F, 25F).trackingRange(4)
            .updateInterval(2).build("master_spark_entity");


    protected MasterSparkEntity(EntityType<? extends ThrowableEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    public MasterSparkEntity(EntityType<? extends ThrowableEntity> type, World world) {
        super(MASTER_SPARK, world);

    }

    @Override
    public void tick() {
        super.tick();
        if (ticksExisted >= this.LIFE_SPAN) {
            this.remove();
        }

    }

    @Override
    protected void registerData() {

    }
}
