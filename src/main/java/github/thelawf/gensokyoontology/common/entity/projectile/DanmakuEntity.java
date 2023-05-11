package github.thelawf.gensokyoontology.common.entity.projectile;

import com.google.gson.Gson;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuStyle;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.core.init.EffectRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class DanmakuEntity extends ThrowableEntity implements IRendersAsItem{

    public static final EntityType<DanmakuEntity> DANMAKU = EntityType.Builder.<DanmakuEntity>create(
                    DanmakuEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("danmaku_entity");

    public static final int MAX_LIVING_TICK = 125;

    public static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(DanmakuEntity.class,DataSerializers.FLOAT);
    private int pathIndex = 0;
    private int pathTick = 0;
    private DanmakuType type;

    public DanmakuEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public DanmakuEntity(LivingEntity throwerIn, World worldIn) {
        super(DANMAKU,throwerIn, worldIn);
    }

    public DanmakuEntity(LivingEntity throwerIn, World world,  DanmakuType type) {
        super(DANMAKU, throwerIn, world);
        this.type = type;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.world.isRemote) {
            return;
        }

        if (this.ticksExisted >= MAX_LIVING_TICK) {
            this.remove();
        }

    }

    @Override
    protected void registerData() {

    }

    public void getStyleFromJson() {
        ResourceLocation location = new ResourceLocation(GensokyoOntology.MODID,
                "danmaku/vortex_style.json");
        Gson gson = new Gson();
        DanmakuStyle style = gson.fromJson(location.toString(), DanmakuStyle.class);
    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return super.serializeNBT();
    }

    @Override
    public void setShooter(@Nullable Entity entityIn) {
        super.setShooter(entityIn);
    }

    @Nullable
    @Override
    public Entity getShooter() {
        return super.getShooter();
    }

    @Override
    public void setNoGravity(boolean noGravity) {
        super.setNoGravity(true);
    }

    @Override
    public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
        super.setLocationAndAngles(x, y, z, yaw, pitch);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity shooter = getShooter();
        LivingEntity entityHit = (LivingEntity) result.getEntity();
        if (shooter instanceof PlayerEntity && !(entityHit instanceof PlayerEntity)) {
            entityHit.addPotionEffect(new EffectInstance(EffectRegistry.LOVE_EFFECT.get(), 5 * 40));
            entityHit.attackEntityFrom(GSKODamageSource.DANMAKU, DanmakuType.HEART_SHOT.damage);
            this.remove();
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.DANMAKU_TEST_ITEM.get());
    }

    public void setGravity(int gravity) {
        if (gravity == 0) {
            this.setNoGravity(true);
        }
    }

    public DanmakuEntity setDanmakuType(DanmakuType type) {
        this.type = type;
        return this;
    }
}
