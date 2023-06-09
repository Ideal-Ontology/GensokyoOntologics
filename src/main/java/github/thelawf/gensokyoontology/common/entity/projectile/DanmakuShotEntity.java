package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.danmakulib.SpellData;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class DanmakuShotEntity extends AbstractDanmakuEntity {

    public static final EntityType<DanmakuShotEntity> DANMAKU = EntityType.Builder.<DanmakuShotEntity>create(
                    DanmakuShotEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("danmaku_entity");

    public static final int MAX_LIVING_TICK = 125;

    public static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(DanmakuShotEntity.class,DataSerializers.FLOAT);

    public DanmakuShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }


    public DanmakuShotEntity(LivingEntity throwerIn, World world, SpellData spellData) {
        super(DANMAKU, throwerIn, world, spellData);
        this.setSpellData(spellData);
    }

    @Override
    public void tick() {

    }

    @Override
    protected void registerData() {

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.DANMAKU_SHOT.get());
    }

}
