package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.common.block.HotSpringBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.monster.IMob;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "gensokyoontology",bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GSKOUpdatingEvent {

    @SubscribeEvent
    public static void onTickDelayed (TickEvent.WorldTickEvent event) {

    }

    @SubscribeEvent
    public static void onHotSpringIn(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() != null && event.getEntityLiving().isInWater()) {
            BlockState blockState = event.getEntityLiving().getBlockState();
            if (blockState.getBlockState().getBlock() instanceof HotSpringBlock &&
                    !(event.getEntityLiving() instanceof IMob)) {
                event.getEntityLiving().heal(1.2F);
                // event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.BLINDNESS, 5 * 20));
                // event.getEntityLiving().attackEntityFrom(DamageSource.OUT_OF_WORLD, 100.f);
            }

        }
    }

}
