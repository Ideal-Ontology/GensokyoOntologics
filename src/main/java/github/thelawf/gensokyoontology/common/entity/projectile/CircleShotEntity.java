package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CircleShotEntity extends ScriptedDanmakuEntity{
    public CircleShotEntity(LivingEntity throwerIn, World worldIn, CompoundNBT scriptIn) {
        super(EntityRegistry.CIRCLE_SHOT_ENTITY.get(), throwerIn, worldIn, DanmakuType.RICE_SHOT, scriptIn);
    }

    public CircleShotEntity(EntityType<CircleShotEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void onScriptTick() {
        // ListNBT list1 = wrapAsList(this.scriptsNBT.get("scripts"));
        // list1.forEach(inbt1 -> {
        ListNBT list2 = getBehaviors(wrapAsCompound(this.scriptsNBT));
        list2.forEach(inbt2 -> {
            CompoundNBT behavior = wrapAsCompound(inbt2);
            if (behavior.contains("shoot") && behavior.get("shoot") instanceof ListNBT) {
                List<Double> paramList = wrapAsDoubleFromList((ListNBT) behavior.get("shoot"));
                int keyTick = behavior.getInt("keyTick");
                if (paramList.size() != 4) return;
                if (this.ticksExisted == keyTick) this.shoot(paramList.get(0), paramList.get(1), paramList.get(2), paramList.get(3).floatValue(), 0f);
            }
            if (behavior.contains("setMotion") && behavior.get("setMotion") instanceof ListNBT) {
                List<Double> paramList = wrapAsDoubleFromList((ListNBT) behavior.get("shoot"));
                int keyTick = behavior.getInt("keyTick");
                if (paramList.size() != 3) return;
                Vector3d motion = new Vector3d(paramList.get(0), paramList.get(1), paramList.get(2));
                if (this.ticksExisted == keyTick) this.setMotion(motion.normalize());
            }
        });
        // });
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    @NotNull
    public ItemStack getItem() {
        IItemProvider item;
        switch (this.getDanmakuColor()) {
            case RED:
            case PINK:
            case NONE:
            case YELLOW:
            case AQUA:
            case GREEN:
            default:
                item = ItemRegistry.CIRCLE_SHOT_GREEN.get();
                break;
            case BLUE:
                item = ItemRegistry.CIRCLE_SHOT_BLUE.get();
                break;
            case MAGENTA:
                item = ItemRegistry.CIRCLE_SHOT_MAGENTA.get();
                break;
        }

        return new ItemStack(item);
    }
}
