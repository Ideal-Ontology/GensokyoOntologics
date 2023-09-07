package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.SpellData;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class LargeShotEntity extends AbstractDanmakuEntity implements IRendersAsItem {

    public static final EntityType<LargeShotEntity> LARGE_SHOT = EntityType.Builder.<LargeShotEntity>create(
                    LargeShotEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("large_shot");

    public LargeShotEntity(World worldIn) {
        super(LARGE_SHOT, worldIn);
    }

    public LargeShotEntity(LivingEntity throwerIn, World world, SpellData spellData) {
        super(LARGE_SHOT, throwerIn, world, spellData);
    }

    public LargeShotEntity(EntityType<LargeShotEntity> entityType, World worldIn) {
        super(LARGE_SHOT, worldIn);
    }

    public LargeShotEntity(LivingEntity throwerIn, World worldIn, DanmakuType danmakuTypeIn, DanmakuColor danmakuColorIn) {
        super(LARGE_SHOT, throwerIn, worldIn, danmakuTypeIn, danmakuColorIn);
    }

    public LargeShotEntity(EntityType<LargeShotEntity> entityType, LivingEntity throwerIn, World worldIn, DanmakuType danmakuTypeIn, DanmakuColor danmakuColorIn) {
        super(entityType, throwerIn, worldIn, danmakuTypeIn, danmakuColorIn);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        IItemProvider item = null;
        switch (getDanmakuColor()) {
            case RED:
                item = ItemRegistry.LARGE_SHOT_RED.get();
                break;
            case ORANGE:
                item = ItemRegistry.LARGE_SHOT_ORANGE.get();
                break;
            case NONE:
            case PINK:
            case AQUA:
                item = ItemRegistry.LARGE_SHOT_AQUA.get();
                break;
            case YELLOW:
                item = ItemRegistry.LARGE_SHOT_YELLOW.get();
                break;
            case GREEN:
                item = ItemRegistry.LARGE_SHOT_GREEN.get();
                break;
            case BLUE:
                item = ItemRegistry.LARGE_SHOT_BLUE.get();
                break;
            case PURPLE:
                item = ItemRegistry.LARGE_SHOT_PURPLE.get();
                break;
            case MAGENTA:
                item = ItemRegistry.LARGE_SHOT_MAGENTA.get();
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
