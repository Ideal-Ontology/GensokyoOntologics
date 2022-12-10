package github.thelawf.gensokyoontology.common.events;

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
                event.getPlayer().addItemStackToInventory(new ItemStack(ItemRegistry.SPIRIT_CREATIVE.get(),2));
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
