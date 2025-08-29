package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.Actions;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.item.DanmakuItem;
import github.thelawf.gensokyoontology.common.item.touhou.SakuyaStopWatch;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.EnchantRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GSKOItemStackEvents {
    public static final Logger LOGGER = LogManager.getLogger();
    private static Vector3d motion = new Vector3d(0,0,0);
    private static float speed = 0.F;

    @SubscribeEvent
    public static void onShootDanmaku(PlayerInteractEvent.RightClickItem event) {

    }

    @Deprecated
    public static void onSakuyaStopWatchTick(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            World world = player.world;
            AxisAlignedBB aabb = new AxisAlignedBB(player.getPositionVec().subtract(10,10,10), player.getPositionVec().add(10,10,10));
            ItemStack stack = player.getHeldItemMainhand();

            if (player.ticksExisted < SakuyaStopWatch.totalTicks && stack.getTag() != null) {
                freezeEntities(world, null, aabb, 15F);
            }
            if (player.ticksExisted >= SakuyaStopWatch.totalTicks && stack.getItem() == ItemRegistry.SAKUYA_WATCH.get()) {
                stack.setTag(new CompoundNBT());
                unfreezeEntities(world, null, aabb, 15F);
            }
        }
    }

    public static void freezeEntities(World world, @Nullable Predicate<Entity> predicate, AxisAlignedBB aabb, float radius) {
        if (predicate == null) {
            world.getEntitiesWithinAABB(Entity.class, aabb).stream()
                    .filter(entity -> aabb.getCenter().distanceTo(entity.getPositionVec()) <= radius && !(entity instanceof PlayerEntity))
                    .forEach(GSKOItemStackEvents::forEntitiesOnTimeFreeze);
            return;
        }
        world.getEntitiesWithinAABB(LivingEntity.class, aabb).stream()
                .filter(living -> aabb.getCenter().distanceTo(living.getPositionVec()) <= radius && predicate.test(living))
                .forEach(GSKOItemStackEvents::forEntitiesOnTimeFreeze);
    }

    public static void unfreezeEntities(World world, @Nullable Predicate<Entity> predicate, AxisAlignedBB aabb, float radius) {
        if (predicate == null) {
            world.getEntitiesWithinAABB(Entity.class, aabb).stream()
                    .filter(entity -> aabb.getCenter().distanceTo(entity.getPositionVec()) <= radius && !(entity instanceof PlayerEntity))
                    .forEach(GSKOItemStackEvents::forEntitiesOnTimeUnfreeze);
            return;
        }
        world.getEntitiesWithinAABB(Entity.class, aabb).stream()
                .filter(entity -> aabb.getCenter().distanceTo(entity.getPositionVec()) <= radius && predicate.test(entity))
                .forEach(GSKOItemStackEvents::forEntitiesOnTimeUnfreeze);
    }

    private static void forEntitiesOnTimeFreeze(Entity entity) {
        if (entity instanceof ProjectileEntity) {
            entity.setNoGravity(true);
            motion = entity.getMotion();
            entity.setMotion(0, 0, 0);
            entity.velocityChanged = true;
        } else if (entity instanceof LivingEntity && !(entity instanceof PlayerEntity)) {
            LivingEntity living = (LivingEntity) entity;
            speed = living.getAIMoveSpeed();
            living.setAIMoveSpeed(0);
        }
        entity.canUpdate(false);
    }

    private static void forEntitiesOnTimeUnfreeze(Entity entity) {
        if (entity instanceof ProjectileEntity) {
            entity.setMotion(motion);
            entity.velocityChanged = true;
        } else if (entity instanceof LivingEntity && !(entity instanceof PlayerEntity)) {
            LivingEntity living = (LivingEntity) entity;
            living.setAIMoveSpeed(speed);
        }
        entity.canUpdate(true);
    }

    public static Map<Enchantment, Actions.DanmakuEnchant> registerEnchantActions() {
        HashMap<Enchantment, Actions.DanmakuEnchant> actions = new HashMap<>();
        actions.put(EnchantRegistry.CURVED_SHAPE.get(),  (enchantment, stack, world, player, sizeIn) -> {
            int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
            if (level == 0) return level;
            DanmakuUtil.oddCurveVec(player, level, 180 / level).forEach(shoot ->
                    Danmaku.create(world, player, stack)
                            .size(sizeIn)
                            .shoot(shoot, 0.55F));
            return level;
        });

        actions.put(EnchantRegistry.CIRCLE_SHAPE.get(), (enchantment, stack, world, player, sizeIn) -> {
            int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
            if (level == 0) return level;
            DanmakuUtil.ellipticPos(1F, level).forEach(shoot ->
                    Danmaku.create(world, player, stack)
                            .size(sizeIn)
                            .shoot(shoot, 0.55F));
            return level;
        });

        actions.put(EnchantRegistry.SPHERE_SHAPE.get(),(enchantment, stack, world, player, sizeIn) -> {
            int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
            if (level == 0) return level;
            DanmakuUtil.spheroidPos(1F, level).forEach(shoot ->
                    Danmaku.create(world, player, stack)
                            .size(sizeIn)
                            .shoot(shoot, 0.55F));
            return level;
        });

        actions.put(EnchantRegistry.LINEAR_SHAPE.get(), ((enchantment, stack, worldIn, playerIn, size) -> {
            int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
            if (level == 0) return level;
            for (float speed = level * 0.1F + 0.2F; speed >= 0.2F; speed -= 0.1F) {
                float eye = playerIn.getEyeHeight();
                Danmaku.create(worldIn, playerIn, stack)
                        .size(size)
                        .shoot(playerIn.getLookVec(), speed);
            }
            return level;
        }));

        actions.put(EnchantRegistry.INFINITE_DANMAKU.get(),(enchantment, stack, world, player, sizeIn) ->
                EnchantmentHelper.getEnchantmentLevel(enchantment, stack));

        return actions;
    }
}
