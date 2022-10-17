package github.thelawf.gensokyoontology.common.dimensions;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID)
public class GensokyoDimensions {
    public static final Logger LOGGER = LogManager.getLogger(GensokyoDimensions.class);
    public static final ResourceLocation GENSOKYO_ID = new ResourceLocation(GensokyoOntology.MODID,"dimension");
    public static final RegistryKey<World> WORLD = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,GENSOKYO_ID);
    public static final RegistryKey<Dimension> DIMENSION = RegistryKey.getOrCreateKey(Registry.DIMENSION_KEY,GENSOKYO_ID);
    public static final RegistryKey<DimensionType> DIMENSION_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY,GENSOKYO_ID);
    public static final RegistryKey<DimensionSettings> DIMENSION_SETTINGS = RegistryKey.getOrCreateKey(Registry.NOISE_SETTINGS_KEY,GENSOKYO_ID);
}
