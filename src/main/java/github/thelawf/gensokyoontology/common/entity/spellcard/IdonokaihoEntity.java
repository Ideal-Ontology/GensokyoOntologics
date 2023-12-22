package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.HeartShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallStarShotEntity;
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
import net.minecraft.util.math.vector.Vector2f;
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
    private final List<HeartShotEntity> list;
    private final List<List<HeartShotEntity>> pool = new ArrayList<>();

    public IdonokaihoEntity(World worldIn, PlayerEntity player) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(EntityRegistry.IDO_NO_KAIHO_ENTITY.get(), worldIn, player);
        list = newDanmakuList(() -> new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                HeartShotEntity.class, 100);
        for (int i = 0; i < 6; i++) {
            pool.add(newDanmakuList(() -> new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                    HeartShotEntity.class, 100));
        }
    }

    public IdonokaihoEntity(EntityType<IdonokaihoEntity> entityTypeIn, World worldIn) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(entityTypeIn, worldIn);
        list = newDanmakuList(() -> new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                HeartShotEntity.class, 100);
        for (int i = 0; i < 6; i++) {
            pool.add(newDanmakuList(() -> new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                    HeartShotEntity.class, 100));
        }
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
        double e = Math.E;
        double angle = Math.PI * 2 / 360 * ticksExisted;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < pool.get(i).size(); j++) {
                // angle += Math.PI / 3 * i;
                HeartShotEntity heartShot = this.pool.get(i).get(j);
                Vector3d pos = new Vector3d(this.getPosX() + angle * MathHelper.cos((float) angle), this.getPosY(),
                        this.getPosZ() + angle * MathHelper.sin((float) angle));
                setDanmakuInit(heartShot, this.getPositionVec(), new Vector2f(this.rotationYaw, this.rotationPitch));

                heartShot.setPosition(pos.x, pos.y, pos.z);
                list.set(j, heartShot);
            }

            pool.add(list);
            HeartShotEntity heartShot = pool.get(i).get(GSKOMathUtil.clampPeriod(ticksExisted, 0, 99)).isAlive()  ?
                    pool.get(i).get(GSKOMathUtil.clampPeriod(ticksExisted, 0, 99)) :
                    pool.get(i).get(GSKOMathUtil.clampPeriod(ticksExisted + 1, 0, 99));
            world.addEntity(heartShot);
        }
    }

    private void withSpirals(float a, float angle, float nextAngle, float speed, HeartShotEntity heartShot) {

    }
}

