package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.HeartShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.RiceShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.nbt.BehaviorFunctions;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class SuperEgoSpellEntity extends SpellCardEntity {

    public SuperEgoSpellEntity(World worldIn, PlayerEntity player) {
        super(EntityRegistry.SUPER_EGO_SPELL_ENTITY.get(), worldIn, player);
    }

    public SuperEgoSpellEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        //PlayerEntity player = this.world.getPlayerByUuid(this.dataManager.get(DATA_OWNER_UUID).get());
        Vector3d center = new Vector3d(Vector3f.XP);

        if (ticksExisted % 5 == 0) return;
        for (int i = 0; i < 6; i++) {
            Vector3d clockwise = center.add(120, 0, 0).rotateYaw((float) Math.PI * 2 / 6 * i);
            Vector3d counterClockwise = center.add(120, 0, 0).rotateYaw((float) -Math.PI * 2 / 6 * i);

            clockwise = clockwise.rotateYaw((float) (Math.PI / 180 * ticksExisted));
            counterClockwise = counterClockwise.rotateYaw((float) -Math.PI / 180 * ticksExisted);
            Vector3d clockShoot = clockwise.normalize().inverse();
            Vector3d counterClockShoot = counterClockwise.normalize().inverse();

            CompoundNBT nbt = initColor(DanmakuColor.AQUA);
            CompoundNBT nbtCounter = initColor(DanmakuColor.AQUA);
            applyFunc(clockwise, nbt);
            applyFunc(counterClockwise, nbtCounter);

            HeartShotEntity heartShot = new HeartShotEntity((LivingEntity) this.getOwner(), world, nbt);
            HeartShotEntity heartCounter = new HeartShotEntity((LivingEntity) this.getOwner(), world, nbtCounter);
            setDanmakuInit(heartShot, clockwise.add(this.getPositionVec()), new Vector2f((float) clockShoot.x, (float) clockShoot.z));
            setDanmakuInit(heartCounter, counterClockwise.add(this.getPositionVec()), new Vector2f((float) counterClockShoot.x, (float) counterClockShoot.z));

            heartShot.shoot(clockShoot.x, clockShoot.y, clockShoot.z, 0.6f, 0f);
            heartCounter.shoot(counterClockShoot.x, counterClockShoot.y, counterClockShoot.z, 0.6f, 0f);
            world.addEntity(heartShot);
            world.addEntity(heartCounter);
        }
    }

    private void applyFunc(Vector3d motion, CompoundNBT script) {
        ListNBT list = new ListNBT();
        CompoundNBT behavior = new CompoundNBT();
        motion = motion.rotateYaw((float) -Math.PI / 200 * 199).scale(0.1);

        ListNBT params = newDoubleNBTList(motion.x, motion.y, motion.z);
        behavior.put(BehaviorFunctions.ADD_MOTION, params);
        list.add(behavior);

        script.putString("type", "keyTickBehavior");
        script.put("behaviors", list);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }
}
