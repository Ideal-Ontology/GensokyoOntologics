package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.common.item.food.CakeScarletDemon;
import github.thelawf.gensokyoontology.common.item.food.KoishiHatMousse;
import github.thelawf.gensokyoontology.common.item.food.MilkBottle;
import github.thelawf.gensokyoontology.common.item.food.YattsumeUnaYaki;
import github.thelawf.gensokyoontology.common.named.GensokyoOntologyNBTs;
import github.thelawf.gensokyoontology.core.init.ItemInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "gensokyoontology",bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
public class FoodCookingEvents {

    @SubscribeEvent
    public static void onRightClickCooker(PlayerInteractEvent.RightClickItem event){
        int volume;
        if (event.getEntityLiving().getHeldItemMainhand().getItem() instanceof MilkBottle) {
            System.out.println("l");
        }
    }

    @SubscribeEvent
    public static void onFoodCrafted(PlayerEvent.ItemCraftedEvent event){
        if (event.getInventory() instanceof YattsumeUnaYaki) {
            event.getEntityLiving().getHeldItemMainhand().setTag(GensokyoOntologyNBTs.nbtYattsume);
        }
        else if (event.getInventory() instanceof KoishiHatMousse) {
            event.getEntityLiving().getHeldItemMainhand().setTag(GensokyoOntologyNBTs.nbtKoishiMousse);
        }
        /*
        else if (event.getInventory() instanceof CakeScarletDemon) {

        }
         */
    }
}
