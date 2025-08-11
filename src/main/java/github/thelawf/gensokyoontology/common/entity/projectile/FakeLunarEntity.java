package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.entity.Danmaku;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.SpellData;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class FakeLunarEntity extends Danmaku {

    public FakeLunarEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }


    public FakeLunarEntity(World worldIn, LivingEntity throwerIn, Item item) {
        super(worldIn, item, throwerIn);
    }

    @Override
    protected void onEntityHit(@NotNull EntityRayTraceResult result) {
        Entity shooter = getShooter();

        if (result.getEntity() instanceof AbstractDanmakuEntity) {
            AbstractDanmakuEntity danmaku = (AbstractDanmakuEntity) result.getEntity();
            danmaku.remove();
        } else if (!(result.getEntity() instanceof PlayerEntity)) {
            // entityHit.addPotionEffect(new EffectInstance(EffectRegistry.LOVE_EFFECT.get(), 5 * 40));
            result.getEntity().attackEntityFrom(GSKODamageSource.DANMAKU, 12F);
            this.remove();
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.FAKE_LUNAR_ITEM.get());
    }
}
