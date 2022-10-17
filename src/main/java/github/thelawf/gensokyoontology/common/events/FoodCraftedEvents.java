package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.common.item.food.KoishiHatMousse;
import github.thelawf.gensokyoontology.common.item.food.YattsumeUnaYaki;
import github.thelawf.gensokyoontology.common.named.GensokyoOntologyNBTs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "gensokyoontology",bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
public class FoodCraftedEvents {

    @SubscribeEvent
    public static void onFoodCrafted(PlayerEvent.ItemCraftedEvent event){
        if (event.getInventory() instanceof YattsumeUnaYaki) {
            event.getEntityLiving().getHeldItemMainhand().setTag(GensokyoOntologyNBTs.nbtYattsume);
        }
        else if (event.getInventory() instanceof KoishiHatMousse) {
            event.getEntityLiving().getHeldItemMainhand().setTag(GensokyoOntologyNBTs.nbtKoishiMousse);
        }
    }
}
