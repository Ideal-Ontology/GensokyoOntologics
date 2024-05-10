package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
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
        super.onScriptTick();
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
