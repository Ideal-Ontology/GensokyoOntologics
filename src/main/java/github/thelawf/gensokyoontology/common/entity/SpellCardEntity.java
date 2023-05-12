package github.thelawf.gensokyoontology.common.entity;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.Muzzle;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpellCardEntity extends Entity implements IRendersAsItem {

    public static int lifeSpan;
    public List<Muzzle<? extends AbstractDanmakuEntity>> muzzles = new ArrayList<>();

    public static final DataParameter<Integer> DATA_LIFESPAN = EntityDataManager.createKey(
            SpellCardEntity.class, DataSerializers.VARINT);

    public static final DataParameter<CompoundNBT> DATA_MUZZLES = EntityDataManager.createKey(
            SpellCardEntity.class, DataSerializers.COMPOUND_NBT);

    public static final EntityType<SpellCardEntity> SPELL_CARD_ENTITY = EntityType.Builder.<SpellCardEntity>create(
                    SpellCardEntity::new, EntityClassification.MISC).size(1F,1F).trackingRange(4)
            .updateInterval(2).build("spell_card_entity");

    public SpellCardEntity(EntityType<?> entityTypeIn, World worldIn, List<Muzzle<? extends AbstractDanmakuEntity>> muzzles) {
        super(entityTypeIn, worldIn);
        this.muzzles = muzzles;
        muzzles.forEach(muzzle -> lifeSpan = muzzle.getFunc().lifeSpan);
    }

    public SpellCardEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void registerData() {
        this.dataManager.register(DATA_LIFESPAN, lifeSpan);
    }


    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.read(compound);
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        compound.putInt("lifespan", lifeSpan);
    }


    @Override
    public void tick() {
        super.tick();
        if (ticksExisted >= lifeSpan) {
            this.remove();
        }
        // 在这里调用变换函数，使用 tickExisted 作为变换函数中increment增加值的迭代单位
        this.muzzles.forEach(muzzle -> {
            LargeShotEntity danmaku = (LargeShotEntity) muzzle.getDanmaku();
            Vector3d prevAngle = muzzle.getFunc().getShootVector();

            if (muzzle.getFunc().increment != 0D) {
                Vector3d newAngle = prevAngle.rotateYaw((float) muzzle.getFunc().increment * ticksExisted);
                danmaku.setNoGravity(true);
                danmaku.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(),
                        (float) newAngle.y, (float) newAngle.z);
                danmaku.shoot(prevAngle.x, prevAngle.y, prevAngle.z, 0.3f, 0f);
                world.addEntity(danmaku);
            }
            else {
                danmaku.setNoGravity(true);
                danmaku.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(),
                        (float) prevAngle.y, (float) prevAngle.z);
                danmaku.shoot(prevAngle.x, prevAngle.y, prevAngle.z, 0.3f, 0f);
                world.addEntity(danmaku);
            }

        });
    }

    @Override
    @NotNull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }
}
