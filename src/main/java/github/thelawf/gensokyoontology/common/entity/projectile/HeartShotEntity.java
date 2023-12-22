package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.SpellData;
import github.thelawf.gensokyoontology.common.util.danmaku.TransformFunction;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class HeartShotEntity extends AbstractDanmakuEntity implements IRendersAsItem {

    public HeartShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HeartShotEntity(LivingEntity throwerIn, World world, SpellData spellData) {
        super(EntityRegistry.HEART_SHOT_ENTITY.get(), throwerIn, world, spellData);
    }

    public HeartShotEntity(LivingEntity throwerIn, World worldIn, DanmakuType danmakuTypeIn, DanmakuColor danmakuColorIn) {
        super(EntityRegistry.HEART_SHOT_ENTITY.get(), throwerIn, worldIn, danmakuTypeIn, danmakuColorIn);
    }


    private void applyTransform(HashMap<Integer, TransformFunction> keyTransforms) {
        for (Map.Entry<Integer, TransformFunction> entry : keyTransforms.entrySet()) {
            Integer keyTick = entry.getKey();
            TransformFunction function = entry.getValue();
            if (function.transformOrders == null) return;

        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    @NotNull
    public ItemStack getItem() {
        IItemProvider item = null;
        switch (getDanmakuColor()) {
            case RED:
            case YELLOW:
            case GREEN:
            case PURPLE:
            case NONE:
                item = ItemRegistry.HEART_SHOT_RED.get();
                break;
            case AQUA:
                item = ItemRegistry.HEART_SHOT_AQUA.get();
                break;
            case BLUE:
                item = ItemRegistry.HEART_SHOT_BLUE.get();
                break;
            case PINK:
                item = ItemRegistry.HEART_SHOT_PINK.get();
                break;
        }

        if (item == null) {
            return ItemStack.EMPTY;
        } else {
            return new ItemStack(item);
        }
    }
}
