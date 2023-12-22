package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.HeartShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 恋恋的符卡：本我的解放
 */
public class IdonokaihoEntity extends SpellCardEntity {

    private final int lifespan = 600;
    private final List<HeartShotEntity> prevPool;
    private final List<HeartShotEntity> pool = new ArrayList<>();

    public IdonokaihoEntity(World worldIn, PlayerEntity player) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(EntityRegistry.IDO_NO_KAIHO_ENTITY.get(), worldIn, player);
        prevPool = newDanmakuList(() -> new HeartShotEntity((LivingEntity) this.getOwner(), worldIn, DanmakuType.HEART_SHOT, DanmakuColor.PINK),
                HeartShotEntity.class, 100);
    }

    public IdonokaihoEntity(EntityType<IdonokaihoEntity> entityTypeIn, World worldIn) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(entityTypeIn, worldIn);
        prevPool = newDanmakuList(() -> new HeartShotEntity((LivingEntity) this.getOwner(), worldIn, DanmakuType.HEART_SHOT, DanmakuColor.PINK),
                HeartShotEntity.class, 100);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SC_IDO_NO_KAIHO.get());
    }

    @Override
    public void tick() {
        super.tick();

        float a = 3;
        float speed = 3F;
        float angle = (float) world.getGameTime() * 0.05F;
        float nextAngle = (float) (world.getGameTime() * 0.05) + 0.1F;  // 略微增加角度以计算下一时刻速度

        angle += (float) Math.PI / 60 * ticksExisted;
        nextAngle += (float) Math.PI / 60 * ticksExisted;

        spirals: for (int i = 0; i < 6; i++) {
            angle += Math.PI / 6 * i;
            nextAngle += Math.PI / 6 * i;

            // OK, This might be the worst line I have ever written before.
            // 有一种《用最小的文件大小做游戏编程大赛》的美（）
            // 总之就是判断索引变量j是否在整百区间。
            for (int j = 0; j < prevPool.size(); j++) {
                HeartShotEntity heartShot = prevPool.get(j);
                HeartShotEntity newHeartShot = new HeartShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.HEART_SHOT, DanmakuColor.PINK);
                setDanmakuInit(newHeartShot, this.getPositionVec());

                Vector3d pos = new Vector3d(this.getPosX() + a * MathHelper.cos(angle), this.getPosY(), this.getPosZ() + a * MathHelper.sin(angle));
                Vector3d nextPos = new Vector3d(this.getPosX() + a * MathHelper.cos(nextAngle), this.getPosY(), this.getPosZ() + a * MathHelper.sin(nextAngle));
                Vector3d motion = nextPos.subtract(pos);

                heartShot.setPosition(pos.x, pos.y, pos.z);
                heartShot.setMotion(motion.scale(speed));
                if (heartShot.ticksExisted == heartShot.getLifespan()) {
                    prevPool.remove(heartShot);
                }
                prevPool.set(j, heartShot);
                prevPool.add(newHeartShot);
            };

            // for ( HeartShotEntity entity : prevPool) {
            //     withSpirals(a, angle, nextAngle, speed, entity);
            // }
            //     if (entity.ticksExisted >= entity.getLifespan()) prevPool.remove(entity);
            //     if (prevPool.indexOf(entity) >= (i * 100) && prevPool.indexOf(entity) < ((i + 1) * 100)) {
            //         withSpirals(a, angle, nextAngle, speed, entity);
            //     }
            //     continue spirals;
            // }
            HeartShotEntity heartShot = prevPool.get(GSKOMathUtil.clampPeriod(ticksExisted, 0, prevPool.size() - 1));
            world.addEntity(heartShot);
        }

    }

    private void withSpirals(float a, float angle, float nextAngle, float speed, HeartShotEntity heartShot) {

    }
}

