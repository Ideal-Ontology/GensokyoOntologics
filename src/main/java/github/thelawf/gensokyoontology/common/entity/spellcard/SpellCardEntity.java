package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuMuzzle;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpellCardEntity extends Entity implements IRendersAsItem {

    private int lifeSpan = 100;
    public List<DanmakuMuzzle<? extends AbstractDanmakuEntity>> muzzles = new ArrayList<>();
    private UUID owner;

    public static final DataParameter<Integer> DATA_LIFESPAN = EntityDataManager.createKey(
            SpellCardEntity.class, DataSerializers.VARINT);

    public static final EntityType<SpellCardEntity> SPELL_CARD_ENTITY = EntityType.Builder.<SpellCardEntity>create(
                    SpellCardEntity::new, EntityClassification.MISC).size(1F,1F).trackingRange(4)
            .updateInterval(2).build("spell_card_entity");

    public SpellCardEntity(World worldIn, List<DanmakuMuzzle<? extends AbstractDanmakuEntity>> muzzles, Entity owner) {
        super(SPELL_CARD_ENTITY, worldIn);
        this.muzzles = muzzles;
        this.setOwner(owner);
    }

    public SpellCardEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(SPELL_CARD_ENTITY, worldIn);
    }


    @Override
    protected void registerData() {
        this.dataManager.register(DATA_LIFESPAN, this.lifeSpan);
    }

    @Override
    public void readAdditional(@NotNull CompoundNBT compound) {
        if (compound.contains("lifespan")) {
            this.lifeSpan = compound.getInt("lifespan");
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        compound.putInt("lifespan", this.lifeSpan);
    }

    public void setOwner(Entity entityIn) {
        if (entityIn != null) {
            this.owner = entityIn.getUniqueID();
        }
    }

    @Nullable
    public Entity getOwner() {
        if (this.owner != null && this.world instanceof ServerWorld) {
            return ((ServerWorld) this.world).getEntityByUuid(this.owner);
        }
        else {
            return null;
        }
    }

    /**
     * 使用tick()方法让弹幕在每个游戏刻执行不同的操作，将实体类中的 tickExisted 参数作为变换函数 increment 增加值的迭代单位，
     * 因为这个参数的存在，实体类的tick方法比单纯继承了 ITickable 接口的类更加便捷好用。
     */
    @Override
    public void tick() {
        super.tick();

        if (ticksExisted >= lifeSpan) {
            this.remove();
        }

        // 首先获取玩家和玩家视角，这里使用的方法有点蠢，之后会通过直接初始化的方式获取
        PlayerEntity player = world.getPlayers().get(0);
        Vector3d playerPos = player.getPositionVec();
        Vector3d lookVec = player.getLookVec();

        // 然后是设定弹幕发射口的变换函数和需要发射的弹幕
        DanmakuMuzzle<LargeShotEntity> muzzle = new DanmakuMuzzle<>(new TransformFunction()
                .setPlayer(player).setLifeSpan(100)
                .setInitLocation(playerPos)
                .setShootVector(lookVec)
                .setLifeSpan(200)
                .setIncrement(Math.PI / 180),
                new LargeShotEntity(player, world)
        );

        // 在具体的符卡类中，这里初始化的弹幕实体类应该写成泛型强制类型转换：
        // if (muzzle.getDanmaku() instanceof LargeshotEntity) {
        //    LargeShotEntity danmaku = (LargeShotEntity) muzzle.getDanmaku();
        // }
        // 先判断muzzle里面弹幕的类型是否和符卡需要的类型一致，再初始化弹幕
        LargeShotEntity danmaku = new LargeShotEntity(player, world);

        Vector3d prevAngle = muzzle.getFunc().getShootVector();

        if (muzzle.getFunc().increment != 0D) {
            Vector3d newAngle = prevAngle.rotateYaw((float) (muzzle.getFunc().increment +
                    (Math.PI / 3) * ticksExisted));

            danmaku.setNoGravity(true);
            danmaku.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(),
                    (float) newAngle.y, (float) newAngle.z);
        }
        else {
            danmaku.setNoGravity(true);
            danmaku.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(),
                    (float) prevAngle.y, (float) prevAngle.z);
        }
        danmaku.shoot(this.getPosX(), prevAngle.y, this.getPosZ(), 0.3f, 0f);
        this.world.addEntity(danmaku);

        this.muzzles.forEach(m -> {});

    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AttributeModifierMap.createMutableAttribute().createMutableAttribute(Attributes.MAX_HEALTH).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE).createMutableAttribute(Attributes.MOVEMENT_SPEED).createMutableAttribute(Attributes.ARMOR).createMutableAttribute(Attributes.ARMOR_TOUGHNESS).createMutableAttribute(net.minecraftforge.common.ForgeMod.SWIM_SPEED.get()).createMutableAttribute(net.minecraftforge.common.ForgeMod.NAMETAG_DISTANCE.get()).createMutableAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
    }

    @Override
    @NotNull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SC_WAVE_AND_PARTICLE.get());
    }
}
