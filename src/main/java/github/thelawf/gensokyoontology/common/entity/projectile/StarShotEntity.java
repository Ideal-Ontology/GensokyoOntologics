package github.thelawf.gensokyoontology.common.entity.projectile;


import github.thelawf.gensokyoontology.common.libs.danmakulib.SpellData;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class StarShotEntity extends AbstractDanmakuEntity {

    public static final EntityType<StarShotEntity> STAR_SHOT_SMALL =
            EntityType.Builder.<StarShotEntity>create(StarShotEntity::new,
                            EntityClassification.MISC).size(0.5F,0.5F)
                    .trackingRange(4).updateInterval(2).build("star_shot_small");

    public static final EntityType<StarShotEntity> STAR_SHOT_LARGE =
            EntityType.Builder.<StarShotEntity>create(StarShotEntity::new,
                            EntityClassification.MISC).size(2.8F,2.8F)
                    .trackingRange(4).updateInterval(2).build("star_shot_large");


    protected StarShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(STAR_SHOT_SMALL, worldIn);
    }

    public StarShotEntity(LivingEntity throwerIn, World world, SpellData spellData) {
        super(STAR_SHOT_SMALL, throwerIn, world, spellData);
    }

    @Override
    public ItemStack getItem() {
        return null;
    }
}
