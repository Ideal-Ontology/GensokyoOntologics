package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.SpellData;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class FakeLunarEntity extends AbstractDanmakuEntity {

    public static final EntityType<FakeLunarEntity> FAKE_LUNAR =
            EntityType.Builder.<FakeLunarEntity>create(FakeLunarEntity::new,
                            EntityClassification.MISC).size(2.8F,2.8F)
                    .trackingRange(4).updateInterval(2).build("fake_lunar");

    public FakeLunarEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(FAKE_LUNAR, worldIn);
    }

    public FakeLunarEntity(LivingEntity throwerIn, World world, SpellData spellData) {
        super(FAKE_LUNAR, throwerIn, world, spellData);
    }

    public FakeLunarEntity(LivingEntity throwerIn, World worldIn, DanmakuType danmakuTypeIn, DanmakuColor danmakuColorIn) {
        super(FAKE_LUNAR, throwerIn, worldIn, danmakuTypeIn, danmakuColorIn);
    }

    @Override
    protected void onEntityHit(@NotNull EntityRayTraceResult result) {
        Entity shooter = getShooter();

        if (result.getEntity() instanceof AbstractDanmakuEntity){
            AbstractDanmakuEntity danmaku = (AbstractDanmakuEntity) result.getEntity();
            danmaku.remove();
        }
        else if (!(result.getEntity() instanceof PlayerEntity)) {
            // entityHit.addPotionEffect(new EffectInstance(EffectRegistry.LOVE_EFFECT.get(), 5 * 40));
            result.getEntity().attackEntityFrom(GSKODamageSource.DANMAKU,this.dataManager.get(DATA_DAMAGE));
            this.remove();
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.FAKE_LUNAR_ITEM.get());
    }
}
