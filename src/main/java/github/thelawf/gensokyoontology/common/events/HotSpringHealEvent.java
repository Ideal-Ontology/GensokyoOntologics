package github.thelawf.gensokyoontology.common.events;


import github.thelawf.gensokyoontology.common.block.nature.HotSpringBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.monster.IMob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Deprecated
@Mod.EventBusSubscriber(modid = "gensokyoontology",bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
public class HotSpringHealEvent{
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


