package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class HeartShotEntity extends AbstractDanmakuEntity implements IRendersAsItem {

    public static final EntityType<HeartShotEntity> HEART_SHOT = EntityType.Builder.<HeartShotEntity>create(
                    HeartShotEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("heart_shot_entity");

    public HeartShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HeartShotEntity(LivingEntity throwerIn, World world) {
        super(HEART_SHOT, throwerIn, world, DanmakuType.HEART_SHOT);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.HEART_SHOT_ITEM.get());
    }
}
