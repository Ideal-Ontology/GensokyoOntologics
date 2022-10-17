package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.common.item.food.MilkBottle;
import github.thelawf.gensokyoontology.core.init.ItemInit;
import net.minecraftforge.api.distmarker.Dist;
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
}
