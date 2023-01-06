package github.thelawf.gensokyoontology.common.dimensions;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraftforge.common.world.ForgeWorldType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class GSKODimensions {
    public static final Logger LOGGER = LogManager.getLogger(GSKODimensions.class);
    public static final ResourceLocation GENSOKYO_ID = new ResourceLocation(GensokyoOntology.MODID,"gensokyo");
    public static final ResourceLocation FORMER_HELL_ID = new ResourceLocation(GensokyoOntology.MODID, "former_hell");
    public static final ResourceLocation MEKKAI_ID = new ResourceLocation(GensokyoOntology.MODID, "mekkai");

    public static final RegistryKey<World> GSKO_WORLD = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, GENSOKYO_ID);
    public static final RegistryKey<Dimension> GENSOKYO_DIM = RegistryKey.getOrCreateKey(Registry.DIMENSION_KEY, GENSOKYO_ID);
    public static final RegistryKey<DimensionType> GENSOKYO_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, GENSOKYO_ID);

    public static void register() {
        LOGGER.debug("Registry Dimension of" + GensokyoOntology.MODID);
    }

}
