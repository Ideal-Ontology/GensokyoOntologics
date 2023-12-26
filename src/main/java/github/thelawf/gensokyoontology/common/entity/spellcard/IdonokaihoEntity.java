package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.HeartShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 恋恋的符卡：本我的解放
 */
public class IdonokaihoEntity extends SpellCardEntity {

    private final int lifespan = 600;
    private final Map<Integer, HeartShotEntity> map;
    private final List<Map<Integer, HeartShotEntity>> pool = new ArrayList<>();
    private final List<List<HeartShotEntity>> list = new ArrayList<>();

    public IdonokaihoEntity(World worldIn, PlayerEntity player) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(EntityRegistry.IDO_NO_KAIHO_ENTITY.get(), worldIn, player);
        map = newDanmakuMap(new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                HeartShotEntity.class, 100);
        for (int i = 0; i < 6; i++) {
            pool.add(newDanmakuMap(new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                    HeartShotEntity.class, 100));
            list.add(newDanmakuList(() -> new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                    HeartShotEntity.class, 100));
        }
    }

    public IdonokaihoEntity(EntityType<IdonokaihoEntity> entityTypeIn, World worldIn) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(entityTypeIn, worldIn);
        map = newDanmakuMap(new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                HeartShotEntity.class, 100);
        for (int i = 0; i < 6; i++) {
            pool.add(newDanmakuMap(new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                    HeartShotEntity.class, 100));
            list.add(newDanmakuList(() -> new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                    HeartShotEntity.class, 100));
        }
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SC_IDO_NO_KAIHO.get());
    }

    @Override
    @SuppressWarnings("deprecation")
    public void tick() {

        super.tick();

        float a = 3;
        double e = Math.E;
        double angle = Math.PI * 2 / 360 * ticksExisted;

        // TODO: 1. 生成弹幕对象池，并将其添加至世界；2. 获取弹幕UUID，添加至UUID列表；3. 遍历UUID列表，动态修改里面的弹幕Motion
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < pool.get(i).size(); j++) {
                // angle += Math.PI / 3 * i;
                HeartShotEntity heartShot;
                try {
                    heartShot = newDanmaku((HeartShotEntity) Arrays.stream(this.pool.get(i).values().toArray())
                            .collect(Collectors.toList()).get(j), HeartShotEntity.class);
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                Vector3d pos = new Vector3d(this.getPosX() + angle * MathHelper.cos((float) angle), this.getPosY(),
                        this.getPosZ() + angle * MathHelper.sin((float) angle));
                setDanmakuInit(heartShot, this.getPositionVec(), new Vector2f(this.rotationYaw, this.rotationPitch));
                heartShot.setPosition(pos.x, pos.y, pos.z);
            }

            pool.add(map);
            HeartShotEntity heartShot = (HeartShotEntity) Arrays.stream(this.pool.get(i).values().toArray())
                    .collect(Collectors.toList()).get(GSKOMathUtil.clampPeriod(ticksExisted, 0, map.size() -1));
            HeartShotEntity shot = (HeartShotEntity) Arrays.stream(this.pool.get(i).values().toArray())
                    .collect(Collectors.toList()).get(GSKOMathUtil.clampPeriod(ticksExisted + 1, 0, map.size() -1));
            world.addEntity(heartShot.removed ? heartShot : shot);
        }
    }

    private void withSpirals(float a, float angle, float nextAngle, float speed, HeartShotEntity heartShot) {

    }
}

