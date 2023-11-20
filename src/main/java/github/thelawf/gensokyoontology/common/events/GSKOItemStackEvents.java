package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.client.settings.GSKOKeyboardManager;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = "gensokyoontology", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class GSKOItemStackEvents {
    public static final Logger LOGGER = LogManager.getLogger();
    private static int pauseTicks;

    public static void onSakuyaWatchClick(PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().getItem() == ItemRegistry.SAKUYA_WATCH.get()) {
            ItemStack stack = event.getItemStack();
            LivingEntity entity = event.getEntityLiving();
            if (stack.getTag() != null && stack.getTag().contains("pause_ticks")) {
                pauseTicks = stack.getTag().getInt("pause_ticks") + entity.ticksExisted;
            }
        }
    }

    public static void onSakuyaStopWatchTick(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            World world = player.world;
            AxisAlignedBB aabb = new AxisAlignedBB(player.getPositionVec().subtract(10,10,10), player.getPositionVec().add(10,10,10));
            ItemStack stack = player.getHeldItemMainhand();
            if (player.ticksExisted <= pauseTicks && stack.getTag() != null && stack.getTag().contains("pause_ticks")) {
                world.getEntitiesWithinAABB(LivingEntity.class, aabb).stream()
                        .filter(living -> aabb.getCenter().distanceTo(living.getPositionVec()) <= 10 && !(living instanceof PlayerEntity))
                        .forEach(living -> living.canUpdate(false));
            }
            if (player.ticksExisted > pauseTicks) {
                world.getEntitiesWithinAABB(LivingEntity.class, aabb).stream()
                        .filter(living -> aabb.getCenter().distanceTo(living.getPositionVec()) <= 10 && !(living instanceof PlayerEntity))
                        .forEach(living -> living.canUpdate(true));
            }
        }
    }

    @SubscribeEvent
    public static void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        ItemStack stack = event.getCrafting();
        // if (stack.getItem().equals(ItemRegistry.AYA_FANS.get())) {
        //     CompoundNBT nbt = new CompoundNBT();
        //     nbt.putLong("posA", event.getEntityLiving().getPosition().toLong());
        //     stack.setTag(nbt);
        // }
    }

    @SubscribeEvent
    public static void onItemRightClickField(PlayerInteractEvent.EntityInteract event) {

    }

}
