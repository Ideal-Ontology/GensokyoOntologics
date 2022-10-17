package github.thelawf.gensokyoontology.common.events;


import github.thelawf.gensokyoontology.common.block.HotSpringBlock;
import github.thelawf.gensokyoontology.common.named.GSKOBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.data.TagsProvider;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "gensokyoontology",bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
public class HotSpringHealEvent{
    @SubscribeEvent
    public static void onHotSpringIn(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() != null && event.getEntityLiving().isInWater()) {
            BlockState blockState = event.getEntityLiving().getBlockState();
            if (blockState.getBlockState().getBlock() instanceof HotSpringBlock &&
            !(event.getEntityLiving() instanceof MonsterEntity)) {
                event.getEntityLiving().heal(1.2F);
            }

        }
    }
}


