package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.TransformFunction;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class HeartShotEntity extends ScriptedDanmakuEntity implements IRendersAsItem {

    public HeartShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HeartShotEntity(LivingEntity throwerIn, World worldIn, CompoundNBT scriptIn) {
        super(EntityRegistry.HEART_SHOT_ENTITY.get(), throwerIn, worldIn, DanmakuType.HEART_SHOT, scriptIn);
    }

    private void applyTransform(HashMap<Integer, TransformFunction> keyTransforms) {
        for (Map.Entry<Integer, TransformFunction> entry : keyTransforms.entrySet()) {
            Integer keyTick = entry.getKey();
            TransformFunction function = entry.getValue();
            if (function.transformOrders == null) return;

        }
    }

    /**
     * 心弹中允许玩家在scriptIn.behaviours的每一个元素内再额外传入一个名为 "addMotion" 的含有三个浮点参数的列表：<br>
     * <code  type=json>
     *     顶层：scriptIn<br>
     *     {<br>
     *         &emsp;"type" = "keyTickBehaviour"<br>
     *         &emsp;"behaviours" = [<br>
     *             &emsp;&emsp;{<br>
     *                 &emsp;&emsp;&emsp;"keyTick": 1,<br>
     *                 &emsp;&emsp;&emsp;"shoot": [0,0,0],<br>
     *                 &emsp;&emsp;&emsp;"setMotion": [0,0,0],<br>
     *                 &emsp;&emsp;&emsp;"addMotion": [0,0,0] //仅在心弹中生效<br>
     *             &emsp;&emsp;}<br>
     *         &emsp;]<br>
     *     }<br>
     * </code>
     */
    @Override
    public void onScriptTick() {
        super.onScriptTick();
        ListNBT inbts = getBehaviors(this.scriptsNBT);
        for (INBT inbt : inbts) {
            CompoundNBT behavior = wrapAsCompound(inbt);
            if (behavior.contains("addMotion") && behavior.get("addMotion") instanceof ListNBT) {
                List<Double> paramList = wrapAsDoubleList((ListNBT) behavior.get("addMotion"));
                if (paramList.size() != 3) return;
                this.setMotion(this.getMotion().add(paramList.get(0), paramList.get(1), paramList.get(2)).normalize());
            }
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
