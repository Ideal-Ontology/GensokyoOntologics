package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class RiceShotEntity extends AbstractDanmakuEntity{
    public static final EntityType<RiceShotEntity> RICE_SHOT =
            EntityType.Builder.<RiceShotEntity>create(RiceShotEntity::new, EntityClassification.MISC)
                    .size(0.5F,0.5F).trackingRange(4).updateInterval(2).build("rice_shot");

    protected RiceShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(RICE_SHOT, worldIn);
    }

    public RiceShotEntity(PlayerEntity playerIn, World worldIn, DanmakuType danmakuType, DanmakuColor danmakuColor) {
        super(RICE_SHOT, playerIn, worldIn, danmakuType, danmakuColor);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        IItemProvider item = null;
        switch (getDanmakuColor()) {
            case RED:
            case PINK:
            case NONE:
            case YELLOW:
            case AQUA:
            case GREEN:
                item = ItemRegistry.RICE_SHOT_RED.get();
                break;
            case BLUE:
                item = ItemRegistry.RICE_SHOT_BLUE.get();
                break;
            case PURPLE:
                item = ItemRegistry.RICE_SHOT_PURPLE.get();
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