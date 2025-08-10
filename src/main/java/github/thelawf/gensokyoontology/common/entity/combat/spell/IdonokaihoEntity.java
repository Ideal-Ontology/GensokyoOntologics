package github.thelawf.gensokyoontology.common.entity.combat.spell;

import github.thelawf.gensokyoontology.common.entity.combat.AbstractSpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.HeartShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.nbt.BehaviorFuncKeys;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * 恋恋的符卡：本我的解放
 */
public class IdonokaihoEntity extends AbstractSpellCardEntity {

    public IdonokaihoEntity(World worldIn, LivingEntity player){
        super(EntityRegistry.IDO_NO_KAIHO_ENTITY.get(), worldIn, player);
    }

    public IdonokaihoEntity(EntityType<IdonokaihoEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SC_IDO_NO_KAIHO.get());
    }

    @Override
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
            clockwise = clockwise.rotateYaw((float) Math.PI / 180 * ticksExisted);
            counterClockwise = counterClockwise.rotateYaw((float) -Math.PI / 180 * ticksExisted);

            applyFunc(new Vector3d(-0.2, 0, 0), nbtClockWise);
            applyFunc(new Vector3d(0.2, 0, 0), nbtCounterClockWise);

            HeartShotEntity heartClockwise = new HeartShotEntity((LivingEntity) this.getOwner(), world, nbtClockWise);
            HeartShotEntity heartCounterClockwise = new HeartShotEntity((LivingEntity) this.getOwner(), world, nbtCounterClockWise);
            setDanmakuInit(heartClockwise, this.getPositionVec(), new Vector2f(this.rotationYaw, this.rotationPitch));
            setDanmakuInit(heartCounterClockwise, this.getPositionVec(), new Vector2f(this.rotationYaw, this.rotationPitch));

            heartClockwise.shoot(clockwise.x, clockwise.y, clockwise.z, 0.4f, 0f);
            heartCounterClockwise.shoot(counterClockwise.x, counterClockwise.y, counterClockwise.z, 0.4f, 0f);
            world.addEntity(heartClockwise);
            world.addEntity(heartCounterClockwise);
        }
    }

    private void applyFunc(Vector3d motion, CompoundNBT script) {
        ListNBT list = new ListNBT();
        CompoundNBT behavior = new CompoundNBT();
        motion = motion.rotateYaw((float) Math.PI / 2).scale(0.1);

        ListNBT params = newDoubleNBTList(motion.x, motion.y, motion.z);
        behavior.put(BehaviorFuncKeys.ADD_MOTION, params);
        list.add(behavior);

        script.putString("type", "keyTickBehavior");
        script.put("behaviors", list);
    }
}

