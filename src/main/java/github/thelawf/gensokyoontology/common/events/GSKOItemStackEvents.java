package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.item.touhou.SakuyaStopWatch;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
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

import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = "gensokyoontology", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class GSKOItemStackEvents {
    public static final Logger LOGGER = LogManager.getLogger();
    private static Vector3d motion = new Vector3d(0,0,0);
    private static float speed = 0.F;
    @SubscribeEvent
    public static void onSakuyaStopWatchTick(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            World world = player.world;
            AxisAlignedBB aabb = new AxisAlignedBB(player.getPositionVec().subtract(10,10,10), player.getPositionVec().add(10,10,10));
            ItemStack stack = player.getHeldItemMainhand();

            if (player.ticksExisted < SakuyaStopWatch.totalTicks && stack.getTag() != null) {
                // player.sendStatusMessage(GensokyoOntology.withTranslation("msg.",".sakuya_stop_watch.freeze"), true);
                freezeEntities(world, null, aabb, 15F);
            }
            if (player.ticksExisted >= SakuyaStopWatch.totalTicks && stack.getItem() == ItemRegistry.SAKUYA_WATCH.get()) {
                // player.sendStatusMessage(GensokyoOntology.withTranslation("msg.",".sakuya_stop_watch.unfreeze"), true);
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

}
