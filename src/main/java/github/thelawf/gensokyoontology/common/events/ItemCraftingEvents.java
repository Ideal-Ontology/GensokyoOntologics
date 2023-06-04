package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.common.item.food.*;
import github.thelawf.gensokyoontology.common.nbt.GensokyoOntologyNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Deprecated
@Mod.EventBusSubscriber(modid = "gensokyoontology",bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
public class ItemCraftingEvents {

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
            event.getEntityLiving().getHeldItemMainhand().setTag(GensokyoOntologyNBT.nbtYattsume);
        }
        else if (event.getInventory() instanceof KoishiHatMousse) {
            event.getEntityLiving().getHeldItemMainhand().setTag(GensokyoOntologyNBT.nbtKoishiMousse);
        }
        /*
        else if (event.getInventory() instanceof CakeScarletDemon) {

        }
         */
    }

    public static void onHarvest(PlayerEvent event) {

    }
}
