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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class DanmakuEntity extends ThrowableEntity implements IRendersAsItem{

    public static final EntityType<DanmakuEntity> DANMAKU_ENTITY = EntityType.Builder.<DanmakuEntity>create(
                    DanmakuEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("danmaku_entity");

    public static final int MAX_LIVING_TICK = 125;

    public static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(DanmakuEntity.class,DataSerializers.FLOAT);
    private List<Vector3d> path;
    private int pathIndex = 0;
    private int pathTick = 0;
    private DanmakuType type;

    public DanmakuEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public DanmakuEntity(LivingEntity throwerIn, World worldIn) {
        super(DANMAKU_ENTITY,throwerIn, worldIn);
    }

    public DanmakuEntity(LivingEntity throwerIn, World world,  DanmakuType type, List<Vector3d> path) {
        super(DANMAKU_ENTITY, throwerIn, world);
        this.type = type;
        this.path = path;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.world.isRemote) {
            return;
        }

        if (this.ticksExisted >= MAX_LIVING_TICK) {
            this.remove();
            return;
        }

        Vector3d currentPos = this.path.get(this.pathIndex);
        Vector3d nextPos = this.path.get((this.pathIndex + 1) % this.path.size());

        double deltaX = nextPos.x - currentPos.x;
        double deltaY = nextPos.y - currentPos.y;
        double deltaZ = nextPos.z - currentPos.z;

        double speed = 0.5;

        double motionX = deltaX / this.pathTick * speed;
        double motionY = deltaY / this.pathTick * speed;
        double motionZ = deltaZ / this.pathTick * speed;

        this.setMotion(motionX, motionY, motionZ);

        if (this.pathTick >= this.pathIndex) {
            this.pathIndex = (this.pathIndex + 1) % this.path.size();
            this.pathTick = 0;
        } else {
            this.pathTick++;
        }

        this.move(MoverType.SELF, this.getMotion());
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
            entityHit.attackEntityFrom(DamageSource.GENERIC, DanmakuType.HEART_SHOT.damage);
            this.remove();
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.DANMAKU_TEST_ITEM.get());
    }


    public DanmakuEntity setPath(List<Vector3d> path) {
        this.path = path;
        return this;
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
