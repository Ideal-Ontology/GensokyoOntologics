package github.thelawf.gensokyoontology;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GSKOConfigs {
    public static boolean CAN_HAKKEIRO_DESTROY_BLOCK;
    public static int RAIL_RESOLUTION;
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.BooleanValue CAN_HAKKEIRO_BREAK_BLOCK = BUILDER
            .comment("Determines whether Marisa Hakkeiro can destroy blocks.")
            .define("can_hakkeiro_destroy_block", true);
    private static final ForgeConfigSpec.IntValue _RESOLUTION = BUILDER
            .comment("Determines whether Marisa Hakkeiro can destroy blocks.")
            .defineInRange("rail_resolution", 8, 1, 16);

    @SubscribeEvent
    public static void onLoad(final ModConfig.ModConfigEvent event) {
        CAN_HAKKEIRO_DESTROY_BLOCK = CAN_HAKKEIRO_BREAK_BLOCK.get();
        RAIL_RESOLUTION = _RESOLUTION.get();
    }
}
