package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.common.block.SpaceFissureBlock;
import github.thelawf.gensokyoontology.common.item.tools.IdealismSword;
import github.thelawf.gensokyoontology.common.item.tools.RealismSword;
import github.thelawf.gensokyoontology.common.particle.SpaceFissureParticleData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = "gensokyoontology",bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
public class ItemUseEvents {
    @SubscribeEvent
    public static void onSwordRightClick(PlayerInteractEvent.RightClickItem event){
        // 持有实在论之剑右键单击会给范围内的敌对生物劈下闪电
        if (event.getEntityLiving().getHeldItemMainhand().getItem() instanceof RealismSword) {
            List<Entity> entityList = event.getWorld().getEntitiesWithinAABB(Entity.class,
                    new AxisAlignedBB(event.getPlayer().getPosition(),event.getPlayer().getPosition().add(160,80,160)));

            for (Entity e : entityList) {
                if (e instanceof MonsterEntity) {
                    AxisAlignedBB aabb = e.getBoundingBox();
                    LightningBoltEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(e.world);
                    Vector3d vector3d = aabb.getCenter();
                    Objects.requireNonNull(lightningBolt).moveForced(vector3d);
                    e.world.addEntity(lightningBolt);
                }
            }
        }
    }

    // 持有观念论之剑左键单击会生成撕裂空间的粒子效果
    @SubscribeEvent
    public static void onSwordLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getEntityLiving().getHeldItemMainhand().getItem() instanceof IdealismSword) {
            double d0 = -MathHelper.sin(event.getEntityLiving().rotationYaw * ((float)Math.PI / 180F));
            double d1 = MathHelper.cos(event.getEntityLiving().rotationYaw * ((float)Math.PI / 180F));
            if (event.getWorld() instanceof ServerWorld) {
                SpaceFissureParticleData sfpData = new SpaceFissureParticleData(new Vector3d(0,0,0),new Color(0),0.8F);
                ((ServerWorld)event.getWorld()).spawnParticle(sfpData,
                        event.getEntityLiving().getPosX() + d0, event.getEntityLiving().getPosYHeight(0.5D),
                        event.getEntityLiving().getPosZ() + d1, 0, d0, 0.0D, d1, 0.0D);
            }
        }
    }

    @SubscribeEvent
    public static void onSwordRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getPlayer().getHeldItemMainhand().getItem() instanceof IdealismSword) {
            event.getPlayer().getCooldownTracker().setCooldown(event.getItemStack().getItem(), 200);
            if (event.getPlayer().getCooldownTracker().getCooldown(event.getItemStack().getItem(), 200) == 0) {
                SpaceFissureBlock sfb = new SpaceFissureBlock();
                event.getWorld().updateBlock(event.getPlayer().getPosition(),sfb);
            }
        }
    }

}
