package github.thelawf.gensokyoontology.common.entity.combat.spell;

import github.thelawf.gensokyoontology.common.entity.combat.AbstractSpellCardEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.SpellData;
import github.thelawf.gensokyoontology.common.util.danmaku.TransformFunction;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class MountainOfFaithEntity extends AbstractSpellCardEntity {

    public MountainOfFaithEntity(World worldIn, PlayerEntity player) {
        super(EntityRegistry.MOUNTAIN_OF_FAITH_ENTITY.get(), worldIn, player);
        this.setOwner(player.getEntity());
    }

    public MountainOfFaithEntity(EntityType<? extends AbstractSpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        Vector3d origin = new Vector3d(Vector3f.XP);

        HashMap<Integer, TransformFunction> map = new HashMap<>();
        SpellData spellData = new SpellData(map, DanmakuType.LARGE_SHOT, DanmakuColor.PINK, false, false);

        // level: 麻将山花瓣层数
        // track: 每层的花瓣数
        // count: 每朵花瓣上的弹幕数量
        final int level = 3;
        final int track = 5;
        final int count = 15;

        Logger LOGGER = LogManager.getLogger();

        // for (int j = 0; j < track; j++) {
        //     if (ticksExisted % count > 3) {
        //         // 两个origin 的含义不同
        //         // centerLocal 表示在三个不同的半径上将会生成三个不同半径的花瓣形曲线
        //         // muzzleLocal 表示每个花瓣形曲线上的某一个圆弧花瓣线的中心位置
        //         Vector3d centerLocal;
        //         if (i % 2 == 0) {
        //             centerLocal = origin.add(i * 2, 0, i * 2).rotateYaw((float) (Math.PI / track * j));
        //         }
        //         else {
        //             centerLocal = origin.add(i * 2, 0, i * 2).rotateYaw((float) (Math.PI / track * j))
        //                     .rotateYaw((float) (Math.PI / track * j));
        //         }
        //         Vector3d muzzleLocal = origin.add(1, 0, 1);
        //         Vector3d muzzleNext = muzzleLocal.rotateYaw((float) (Math.PI / 50 * ticksExisted));
        //         Vector3d muzzleGlobal = muzzleNext.add(this.getPositionVec()).add(centerLocal);
        //
        //         DanmakuPool<SmallShotEntity> pool = new DanmakuPool<>(SmallShotEntity.SMALL_SHOT,
        //                 (LivingEntity) this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.RED);
        //         SmallShotEntity smallShot = pool.acquireProjectile(new SmallShotEntity((LivingEntity) this.getOwner(),
        //                 world, DanmakuType.LARGE_SHOT, DanmakuColor.RED), muzzleGlobal, Vector2f.ZERO);
        //         setDanmakuInit(smallShot, muzzleGlobal);
        //         world.addEntity(smallShot);
        //         pool.releaseProjectile(smallShot);
        //         float speed = 0.5F + (ticksExisted - 50) * 0.02F;
        //         if (smallShot.ticksExisted > 50) {
        //             smallShot.shoot(
        //                     -muzzleNext.normalize().x,
        //                     muzzleNext.normalize().y,
        //                     -muzzleNext.normalize().z, 0.4f, 0f
        //             );
        //         }
        //     }
        // }

        for (int i = 1; i < level; i++) {
            for (int j = 0; j < track; j++) {
                if (ticksExisted % count > 3) {
                    // 两个本地坐标的含义不同
                    // centerLocal 表示在三个不同的半径上将会生成三个不同半径的花瓣形曲线
                    // muzzleLocal 表示每个花瓣形曲线上的某一个圆弧花瓣线的中心位置

                    Vector3d centerLocal;
                    if (i % 2 == 0) {
                        centerLocal = origin.add(i * 2, 0, 0).rotateYaw((float) (Math.PI / track * j * 2));
                    } else {
                        centerLocal = origin.add(i * 2, 0, 0).rotateYaw((float) (Math.PI / track * j * 2))
                                .rotateYaw((float) (Math.PI / track * j));
                    }

                    Vector3d muzzleLocal = origin.add(i * 2, 0, i * 2);
                    Vector3d muzzleNext = muzzleLocal.rotateYaw((float) (Math.PI / 50 * ticksExisted));
                    Vector3d muzzleGlobal = muzzleNext.add(this.getPositionVec()).add(centerLocal);

                    // LargeShotEntity smallShot = new LargeShotEntity(player, world, DanmakuType.LARGE_SHOT, DanmakuColor.GREEN);
                    // setDanmakuInit(smallShot, muzzleGlobal);
                    // smallShot.shoot(muzzleNext.x, muzzleNext.y, muzzleNext.z, 0.1F, 0F);
                    // DanmakuPool<SmallShotEntity> pool = new DanmakuPool<>(EntityRegistry.SMALL_SHOT_ENTITY.get(),
                    //         (LivingEntity) this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.RED);
                    // SmallShotEntity smallShot = pool.acquireProjectile(new SmallShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.RED),
                    //         muzzleGlobal, Vector2f.ZERO);
                    // setDanmakuInit(smallShot, muzzleGlobal);
                    // world.addEntity(smallShot);
                    // pool.releaseProjectile(smallShot);

                    // float speed = 0.5F + (ticksExisted - 50) * 0.02F;
                    // if (smallShot.ticksExisted > 50) {
                    //     smallShot.shoot(
                    //             -muzzleNext.x,
                    //             muzzleNext.y,
                    //             -muzzleNext.z, 0.4f, 0f
                    //     );
                    //     LOGGER.info("Shoot!!");
                    // }
                }
            }
        }

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    @NotNull
    public ItemStack getItem() {
        return ItemStack.EMPTY;
        // return new ItemStack(ItemRegistry.SC_MOUNTAIN_OF_FAITH.get());
    }
}
