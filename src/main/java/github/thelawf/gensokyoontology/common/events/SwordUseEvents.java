package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.common.item.tools.RealismSword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = "gensokyoontology",bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
public class SwordUseEvents {
    @SubscribeEvent
    public static void onSwordRightClick(PlayerInteractEvent.RightClickItem event){
        if (event.getEntityLiving().getHeldItemMainhand().getItem() instanceof RealismSword) {
           // event.getEntityLiving().getEntityWorld().addEntity();
            List<Entity> entityList = event.getWorld().getEntitiesWithinAABB(Entity.class,
                    new AxisAlignedBB(event.getPlayer().getPosition(),event.getPlayer().getPosition().add(160,32,160)));

            for (Entity e : entityList) {
                if (event.getWorld().isRemote && e instanceof MonsterEntity) {
                    AxisAlignedBB aabb = e.getBoundingBox();
                    LightningBoltEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(e.world);
                    Vector3d vector3d = aabb.getCenter();
                    Objects.requireNonNull(lightningBolt).moveForced(vector3d);
                    e.world.addEntity(lightningBolt);
                }
            }
        }
    }
}
