package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.danmaku.SpellData;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class DanmakuShotEntity extends AbstractDanmakuEntity {

    public static final int MAX_LIVING_TICK = 125;

    public static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(DanmakuShotEntity.class, DataSerializers.FLOAT);

    public DanmakuShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public DanmakuShotEntity(LivingEntity throwerIn, World world, SpellData spellData) {
        super(EntityRegistry.DANMAKU_ENTITY.get(), throwerIn, world, spellData);
        // this.setSpellData(spellData);
    }

    @Override
    public void tick() {

    }

    @Override
    protected void registerData() {

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.DANMAKU_SHOT.get());
    }

}
