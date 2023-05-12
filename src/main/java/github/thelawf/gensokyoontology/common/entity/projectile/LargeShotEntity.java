package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class LargeShotEntity extends AbstractDanmakuEntity implements IRendersAsItem {

    public static final EntityType<LargeShotEntity> LARGE_SHOT = EntityType.Builder.<LargeShotEntity>create(
                    LargeShotEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("large_shot_entity");

    public LargeShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public LargeShotEntity(LivingEntity throwerIn, World world) {
        super(LARGE_SHOT, throwerIn, world, DanmakuType.LARGE_SHOT);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.LARGE_SHOT_ITEM.get());
    }
}
