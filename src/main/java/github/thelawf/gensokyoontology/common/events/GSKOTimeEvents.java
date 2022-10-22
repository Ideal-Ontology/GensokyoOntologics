package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.libs.IdealismLib;
import github.thelawf.gensokyoontology.core.init.ItemInit;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*
@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GSKOTimeEvents {

    // 玩家登录Minecraft时读取本地时间，与上次登录进行比较

    @SubscribeEvent
    public static void onPlayerOnLine(PlayerEvent.PlayerLoggedInEvent event) {
        IdealismLib idealLib = new IdealismLib();
        final int[][] dateAndYear = idealLib.loadTimeData();
        int[] lastLogInDateAndYear = dateAndYear[0];
        int[] recentLogInDateAndYear = dateAndYear[1];
        if (recentLogInDateAndYear[1] - lastLogInDateAndYear[1] >= 1) {
            recentLogInDateAndYear[0] += 366;
            if (recentLogInDateAndYear[0] - lastLogInDateAndYear[0] >= 365) {
                event.getPlayer().addItemStackToInventory(new ItemStack(ItemInit.SPIRIT_CREATIVE.get(),2));
            }
        }
    }

    // 玩家退出Minecraft时记录一次本地时间
    @SubscribeEvent
    public static void onPlayerOffLine(PlayerEvent.PlayerLoggedOutEvent event) {
        IdealismLib idealismLib = new IdealismLib();
        idealismLib.saveTimeData();
    }
}
 */
