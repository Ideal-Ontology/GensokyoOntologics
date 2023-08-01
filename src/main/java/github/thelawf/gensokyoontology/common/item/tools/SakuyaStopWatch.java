package github.thelawf.gensokyoontology.common.item.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 咲夜的怀表物品
 */
public class SakuyaStopWatch extends Item {
    public SakuyaStopWatch(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItemMainhand();

        if (!worldIn.isRemote() && stack.getOrCreateTag().getLong("cooldown") < worldIn.getGameTime()) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            BlockPos playerPos = playerIn.getPosition();
            List<Entity> entities = serverWorld.getEntitiesWithinAABBExcludingEntity(playerIn,
                    new AxisAlignedBB(playerPos).grow(32));

            AtomicReference<Vector3d> vector3d = new AtomicReference<>(new Vector3d(0, 0, 0));
            AtomicReference<Float> speed = new AtomicReference<>();
            entities.forEach(entity -> {
                if (entity instanceof ProjectileEntity) {
                    entity.setNoGravity(true);
                    vector3d.set(entity.getMotion());
                    entity.setMotion(0,0,0);
                    entity.velocityChanged = true;
                }
                else if (entity instanceof LivingEntity && !(entity instanceof PlayerEntity)) {
                    LivingEntity living = (LivingEntity) entity;
                    speed.set(living.getAIMoveSpeed());
                    living.setAIMoveSpeed(0);

                }
            });

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    entities.forEach(entity -> {
                        if (entity instanceof ProjectileEntity) {
                            entity.setNoGravity(false);
                            entity.setMotion(
                                    vector3d.get().x,
                                    vector3d.get().y,
                                    vector3d.get().z);

                            entity.velocityChanged = true;
                        }
                        else if (entity instanceof LivingEntity && !(entity instanceof PlayerEntity)) {
                            LivingEntity living = (LivingEntity) entity;
                            living.setAIMoveSpeed(speed.get());
                        }
                    });
                }
            }, 5000);

            stack.getOrCreateTag().putLong("cooldown", worldIn.getGameTime() + 6000);
            playerIn.getCooldownTracker().setCooldown(stack.getItem(), 6000);
            return ActionResult.resultSuccess(stack);
        }
        return ActionResult.resultFail(stack);
    }
}
