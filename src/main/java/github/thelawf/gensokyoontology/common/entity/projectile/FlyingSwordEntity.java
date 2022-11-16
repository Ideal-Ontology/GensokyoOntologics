package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.logoslib.math.MathCalculator;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class FlyingSwordEntity extends Entity implements IRendersAsItem {

    private UUID master;
    private int masterId;
    private static int life = 200;

    public static final EntityType<FlyingSwordEntity> FLY_SWORD_TYPE = EntityType.Builder.<FlyingSwordEntity>create(
                    FlyingSwordEntity::new, EntityClassification.MISC).size(1f,0.4f).trackingRange(4)
            .updateInterval(5).build("flying_sword_entity");

    public FlyingSwordEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public void setMaster(@Nullable Entity entityIn) {
        if (entityIn != null) {
            this.master = entityIn.getUniqueID();
            this.masterId = entityIn.getEntityId();
        }
    }

    @Nullable
    public Entity getMaster() {
        if (this.master != null && this.world instanceof ServerWorld) {
            return ((ServerWorld)this.world).getEntityByUuid(this.master);
        }
        else {
            return this.masterId != 0 ? this.world.getEntityByID(this.masterId) : null;
        }
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        if (compound.hasUniqueId("Master")) {
            this.master = compound.getUniqueId("Master");
        }
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        if (this.master != null) {
            compound.putUniqueId("Master", this.master);
        }
    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(ItemRegistry.METAPHYSICS_SWORD.get());
    }

    @Override
    public void tick() {
        if (this.getMaster() instanceof PlayerEntity && life >= 0) {
            // 飞剑实体的位置
            double x = this.getMaster().getLookVec().x;
            double y = this.getMaster().getLookVec().y;
            double z = this.getMaster().getLookVec().z;

            // 玩家实体的位置
            double playerX = this.getMaster().getPosX();
            double playerY = this.getMaster().getPosY();
            double playerZ = this.getMaster().getPosZ();

            // 飞剑与玩家的距离
            double radius = MathCalculator.distanceBetweenPoints(x, z, playerX, playerZ);
            double deltaX = radius / Math.sin(720.d / life);
            float each = life;
            this.setPositionAndRotation(x, y, z, 720.f / each,0);
            --life;

            if (life == 0) {
                this.remove();
            }
        }

    }
}
