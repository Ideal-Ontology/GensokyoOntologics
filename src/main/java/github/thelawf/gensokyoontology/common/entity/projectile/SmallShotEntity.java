package github.thelawf.gensokyoontology.common.entity.projectile;


import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.SpellData;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;

public class SmallShotEntity extends AbstractDanmakuEntity{


    public static final EntityType<SmallShotEntity> SMALL_SHOT = EntityType.Builder.<SmallShotEntity>create(
                    SmallShotEntity::new, EntityClassification.MISC).size(0.3F,0.3F).trackingRange(4)
            .updateInterval(2).build("small_shot");


    protected SmallShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(SMALL_SHOT, worldIn);
    }

    public SmallShotEntity(LivingEntity throwerIn, World world, SpellData spellData) {
        super(SMALL_SHOT, throwerIn, world, spellData);
    }

    public SmallShotEntity(LivingEntity throwerIn, World worldIn, DanmakuType danmakuTypeIn, DanmakuColor danmakuColorIn) {
        super(SMALL_SHOT, throwerIn, worldIn, danmakuTypeIn, danmakuColorIn);
    }

    @Override
    public ItemStack getItem() {
        IItemProvider item = null;
        
        switch (getDanmakuColor()) {
            case RED:
                item = ItemRegistry.SMALL_SHOT_RED.get();
                break;
            case AQUA:
                item = ItemRegistry.SMALL_SHOT_AQUA.get();
                break;
            case PINK:
            case NONE:
                item = ItemRegistry.SMALL_SHOT_RED.get();
                break;
            case ORANGE:
                item = ItemRegistry.SMALL_SHOT_ORANGE.get();
                break;
            case YELLOW:
                item = ItemRegistry.SMALL_SHOT_YELLOW.get();
                break;
            case GREEN:
                item = ItemRegistry.SMALL_SHOT_GREEN.get();
                break;
            case BLUE:
                item = ItemRegistry.SMALL_SHOT_BLUE.get();
                break;
            case PURPLE:
                item = ItemRegistry.SMALL_SHOT_PURPLE.get();
                break;
            case MAGENTA:
                item = ItemRegistry.SMALL_SHOT_MAGENTA.get();
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
