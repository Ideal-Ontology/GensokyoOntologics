package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.HeartShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.nbt.BehaviorFunctions;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
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
    // private final Map<Integer, HeartShotEntity> map;
    private final List<Map<Integer, HeartShotEntity>> pool = new ArrayList<>();
    private final List<List<HeartShotEntity>> list = new ArrayList<>();

    public IdonokaihoEntity(World worldIn, PlayerEntity player) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(EntityRegistry.IDO_NO_KAIHO_ENTITY.get(), worldIn, player);
        // map = newDanmakuMap(new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
        //         HeartShotEntity.class, 100);
        // for (int i = 0; i < 6; i++) {
        //     pool.add(newDanmakuMap(new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
        //             HeartShotEntity.class, 100));
        //     list.add(newDanmakuList(() -> new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
        //             HeartShotEntity.class, 100));
        // }
    }

    public IdonokaihoEntity(EntityType<IdonokaihoEntity> entityTypeIn, World worldIn) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(entityTypeIn, worldIn);
        // map = newDanmakuMap(new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
        //         HeartShotEntity.class, 100);
        // for (int i = 0; i < 6; i++) {
        //     pool.add(newDanmakuMap(new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
        //             HeartShotEntity.class, 100));
        //     list.add(newDanmakuList(() -> new HeartShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
        //             HeartShotEntity.class, 100));
        // }
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
        Vector3d start = new Vector3d(1,0,0);

        // TODO: 现在可以通过继承脚本弹幕的方式设置其运动
        if (ticksExisted % 3 != 0) return;
        for (int i = 0; i < 6; i++) {
            CompoundNBT nbtClockWise = new CompoundNBT();
            CompoundNBT nbtCounterClockWise = new CompoundNBT();
            nbtClockWise.putInt("color", DanmakuColor.PINK.ordinal());
            nbtCounterClockWise.putInt("color", DanmakuColor.PINK.ordinal());

            Vector3d clockwise = start.rotateYaw((float) Math.PI * 2 / 6 * i);
            Vector3d counterClockwise = start.rotateYaw((float) -Math.PI * 2/ 6 * i);
            clockwise = clockwise.rotateYaw((float) Math.PI / 80 * ticksExisted);
            counterClockwise = counterClockwise.rotateYaw((float) -Math.PI / 80 * ticksExisted);

            applyFunc(clockwise, nbtClockWise);
            applyFunc(counterClockwise, nbtCounterClockWise);

            HeartShotEntity heartClockwise = new HeartShotEntity((LivingEntity) this.getOwner(), world, nbtClockWise);
            HeartShotEntity heartCounterClockwise = new HeartShotEntity((LivingEntity) this.getOwner(), world, nbtCounterClockWise);
            setDanmakuInit(heartClockwise, this.getPositionVec(), new Vector2f(this.rotationYaw, this.rotationPitch));
            setDanmakuInit(heartCounterClockwise, this.getPositionVec(), new Vector2f(this.rotationYaw, this.rotationPitch));

            heartClockwise.shoot(clockwise.x, clockwise.y, clockwise.z, 0.6f, 0f);
            heartCounterClockwise.shoot(counterClockwise.x, counterClockwise.y, counterClockwise.z, 0.6f, 0f);
            world.addEntity(heartClockwise);
            world.addEntity(heartCounterClockwise);
        }
    }

    private void applyFunc(Vector3d motion, CompoundNBT script) {
        ListNBT list = new ListNBT();
        CompoundNBT behavior = new CompoundNBT();
        motion = motion.rotateYaw((float) Math.PI / 2);

        ListNBT params = newDoubleNBTList(motion.x, motion.y, motion.z);
        behavior.put(BehaviorFunctions.ADD_MOTION, params);
        list.add(behavior);

        script.putString("type", "keyTickBehavior");
        script.put("behaviors", list);
    }
}

