package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ScaleShotEntity extends AbstractDanmakuEntity{
    public static final EntityType<ScaleShotEntity> SCALE_SHOT =
            EntityType.Builder.<ScaleShotEntity>create(ScaleShotEntity::new, EntityClassification.MISC)
                    .size(0.5F,0.5F).trackingRange(4).updateInterval(2).build("scale_shot");

    protected ScaleShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(SCALE_SHOT, worldIn);
    }

    public ScaleShotEntity(LivingEntity throwerIn, World worldIn, DanmakuType danmakuTypeIn, DanmakuColor danmakuColorIn) {
        super(SCALE_SHOT, throwerIn, worldIn, danmakuTypeIn, danmakuColorIn);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        IItemProvider item;
        switch (getDanmakuColor()) {
            case RED:
            case PINK:
            case NONE:
            case ORANGE:
            default:
                item = ItemRegistry.SCALE_SHOT_RED.get();
                break;
            case YELLOW:
                item = ItemRegistry.SCALE_SHOT_YELLOW.get();
                break;
            case GREEN:
                item = ItemRegistry.SCALE_SHOT_GREEN.get();
                break;
            case AQUA:
            case BLUE:
                item = ItemRegistry.SCALE_SHOT_BLUE.get();
                break;
            case MAGENTA:
            case PURPLE:
                item = ItemRegistry.SCALE_SHOT_PURPLE.get();
                break;
        }

        if (item == null) {
            return ItemStack.EMPTY;
        }
        else {
            return new ItemStack(item);
        }
    }
}
