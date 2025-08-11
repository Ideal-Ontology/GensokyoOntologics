package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.SpellData;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class InYoJadeDanmakuEntity extends AbstractDanmakuEntity {

    public InYoJadeDanmakuEntity(World worldIn, Entity shooter) {
        super(EntityRegistry.INYO_JADE_DANMAKU.get(), worldIn);
        this.setShooter(shooter);
    }

    public InYoJadeDanmakuEntity(EntityType<InYoJadeDanmakuEntity> type, World world) {
        super(type, world);
    }


    @Override
    protected void registerData() {

    }

    @Override
    protected void onEntityHit(@NotNull EntityRayTraceResult result) {
        if (result.getEntity() instanceof YoukaiEntity) {
            YoukaiEntity youkai = (YoukaiEntity) result.getEntity();
            youkai.attackEntityFrom(DamageSource.WITHER, 5F);
        }
        super.onEntityHit(result);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemStack.EMPTY.getItem();
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.INYO_JADE_RED.get());
    }
}
