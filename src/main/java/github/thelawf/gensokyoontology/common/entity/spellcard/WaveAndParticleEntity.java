package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.*;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


/** 八云紫的经典符卡：【境符】波与粒的境界 */
public class WaveAndParticleEntity extends SpellCardEntity{

    public static final EntityType<WaveAndParticleEntity> WAVE_AND_PARTICLE =
            EntityType.Builder.<WaveAndParticleEntity>create(WaveAndParticleEntity::new,
                    EntityClassification.MISC).size(1F,1F).trackingRange(4)
            .updateInterval(2).build("wave_and_particle");

    public WaveAndParticleEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(WAVE_AND_PARTICLE, worldIn);
    }

    public WaveAndParticleEntity(World worldIn, PlayerEntity player) {
        super(WAVE_AND_PARTICLE, worldIn);
        this.setOwner(player);
    }

    @Override
    public void tick() {
        super.tick();
        // 首先获取玩家和玩家视角，判断卡牌的所有者是否是玩家（但是不知道命令召唤出来的符卡会不会有这个属性）
        //
        // if (!(this.getOwner() instanceof PlayerEntity))
        //     return;

        // PlayerEntity player = (PlayerEntity) this.getOwner();
        Vector3d playerPos = this.getPositionVec();
        Vector3d lookVec = this.getLookVec();

        SpellData spellData = new SpellData(new HashMap<>());

        // 然后是设定弹幕发射口的变换函数和需要发射的弹幕
        TransformFunction muzzle = new TransformFunction()
                .setLifeSpan(100)
                .setInitLocation(playerPos)
                .setShootVector(lookVec)
                .setLifeSpan(200)
                .setIncrement(Math.PI / 180);

        // 在具体的符卡类中，这里初始化的弹幕实体类应该写成泛型强制类型转换：
        // if (muzzle.getDanmaku() instanceof LargeshotEntity) {
        //    LargeShotEntity danmaku = (LargeShotEntity) muzzle.getDanmaku();
        // }
        // 先判断muzzle里面弹幕的类型是否和符卡需要的类型一致，再初始化弹幕
        LargeShotEntity danmaku1 = new LargeShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.PURPLE);
        LargeShotEntity danmaku2 = new LargeShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.PURPLE);
        LargeShotEntity danmaku3 = new LargeShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.PURPLE);

        // this.shootAngle = muzzle.getFunc().getShootVector();
        Vector3d muzzle1 = this.shootAngle.rotateYaw((float) (muzzle.increment *
                ticksExisted * ((float) ticksExisted / 5)));

        Vector3d muzzle2 = this.shootAngle.rotateYaw((float) Math.PI / 3 * 2);
        Vector3d muzzle3 = this.shootAngle.rotateYaw((float) Math.PI / 3 * 4);

        muzzle2 = muzzle2.rotateYaw((float) (muzzle.increment * ticksExisted *
                ((float) ticksExisted / 5)));
        muzzle3 = muzzle3.rotateYaw((float) (muzzle.increment * ticksExisted *
                ((float) ticksExisted / 5)));

        initDanmaku(danmaku1, muzzle1);
        initDanmaku(danmaku2, muzzle2);
        initDanmaku(danmaku3, muzzle3);

        danmaku1.shoot(muzzle1.getX(), 0, muzzle1.getZ(), 0.5f, 0f);
        danmaku2.shoot(muzzle2.getX(), 0, muzzle2.getZ(), 0.5f, 0f);
        danmaku3.shoot(muzzle3.getX(), 0, muzzle3.getZ(), 0.5f, 0f);
        this.world.addEntity(danmaku1);
        this.world.addEntity(danmaku2);
        this.world.addEntity(danmaku3);
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(ItemRegistry.SC_WAVE_AND_PARTICLE.get());
    }
}
