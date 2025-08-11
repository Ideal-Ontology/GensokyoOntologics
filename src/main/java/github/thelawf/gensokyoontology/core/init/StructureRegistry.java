package github.thelawf.gensokyoontology.core.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.structure.*;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

/**
 * 种子：3761463330874600349
 */
public final class StructureRegistry {
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(
            ForgeRegistries.STRUCTURE_FEATURES, GensokyoOntology.MODID);

    public static final RegistryObject<Structure<NoFeatureConfig>> ALICE_HOUSE = STRUCTURES.register(
            "alice_house", () -> new AliceHouse(NoFeatureConfig.CODEC));
    public static final RegistryObject<Structure<NoFeatureConfig>> SCARLET_DEVIL_MANSION = STRUCTURES.register(
            "scarlet_devil_mansion", () -> new ScarletDevilMansion(NoFeatureConfig.CODEC));
    public static final RegistryObject<Structure<NoFeatureConfig>> MYSTIA_IZAKAYA = STRUCTURES.register(
            "mystia_izakaya", () -> new MystiaIzakayaStructure(NoFeatureConfig.CODEC));
    public static final RegistryObject<Structure<NoFeatureConfig>> HAKUREI_SHRINE = STRUCTURES.register(
            "hakurei_shrine", () -> new HakureiShrineStructure(NoFeatureConfig.CODEC));
    public static final RegistryObject<Structure<NoFeatureConfig>> CHIREIDEN = STRUCTURES.register(
            "chireiden", () -> new ChireidenStructure(NoFeatureConfig.CODEC));
    public static final RegistryObject<Structure<NoFeatureConfig>> BEAST_PATHWAY = STRUCTURES.register(
            "beast_pathway", () -> new BeastPathStructure(NoFeatureConfig.CODEC));
    public static final RegistryObject<Structure<NoFeatureConfig>> CIRNO_ICE_HOUSE = STRUCTURES.register(
            "cirno_ice_house", () -> new CirnoIceHouseStructure(NoFeatureConfig.CODEC));
    public static final RegistryObject<Structure<NoFeatureConfig>> HUMAN_VILLAGE = STRUCTURES.register(
            "human_village", () -> new HumanVillageStructure(NoFeatureConfig.CODEC));

    // public static final RegistryObject<Structure<NoFeatureConfig>> WATERFALL_NINE_HEAVEN = STRUCTURES.register(
    //         "water_of_nine_heaven", () -> new WaterfallNineHeavenStructure(NoFeatureConfig.CODEC));

    // StructureSeparationSettings 里面的几个参数为：距离，分布和随机值
    public static void setupStructures() {
        setupMapSpacingAndLand(ALICE_HOUSE.get(), new StructureSeparationSettings(30, 15, 19298474), true);
        setupMapSpacingAndLand(SCARLET_DEVIL_MANSION.get(), new StructureSeparationSettings(80, 50, 745264516), true);
        setupMapSpacingAndLand(MYSTIA_IZAKAYA.get(), new StructureSeparationSettings(20, 10, 1023567897), true);
        setupMapSpacingAndLand(HAKUREI_SHRINE.get(), new StructureSeparationSettings(30, 20, 51392147), true);
        setupMapSpacingAndLand(CHIREIDEN.get(), new StructureSeparationSettings(80, 50, 413054656), true);
        setupMapSpacingAndLand(CIRNO_ICE_HOUSE.get(), new StructureSeparationSettings(25, 15, 64916420), true);
        setupMapSpacingAndLand(BEAST_PATHWAY.get(), new StructureSeparationSettings(8, 3, 994251466), true);
        setupMapSpacingAndLand(HUMAN_VILLAGE.get(), new StructureSeparationSettings(20, 10, 854694869), true);
        // setupMapSpacingAndLand(WATERFALL_NINE_HEAVEN.get(), new StructureSeparationSettings(40, 30, 95323460), true);
    }

    /**
     * 本方法中的反混淆映射名如下：
     * <br><br>
     * srg名：Structure.field_236384_t<br>
     * 反混淆名：structureList<br>
     * 类型：List< Structure< ?>><br>
     * 作用：存放所有建筑结构的列表<br>
     * <br>
     * srg名：DimensionStructuresSettings.field_236191_b_<br>
     * 反混淆名：structureSettings<br>
     * 类型：ImmutableMap< Structure< ?>, StructureSeparationSettings><br>
     * 作用：存放建筑结构和建筑生成设置的不可变映射Map<br>
     * <br>
     * srg名：DimensionStructuresSettings.field_236193_d_<br>
     * 反混淆名：structures<br>
     * 类型：Map< Structure< ?>, StructureSeparationSettings><br>
     * 作用：存放建筑结构和建筑生成设置的映射Map
     *
     * @param structure                   建筑结构
     * @param structureSeparationSettings 建筑结构分布设置
     * @param transformSurroundingLand    是否转变建筑周围的地形
     * @param <F>                         建筑结构类型形参
     */
    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure,
                                                                       StructureSeparationSettings structureSeparationSettings,
                                                                       boolean transformSurroundingLand) {
        /*
         * We need to add our structures into the map in Structure class
         * alongside vanilla structures or else it will cause errors.
         *
         * If the registration is set up properly for the structure,
         * getRegistryName() should never return null.
         */
        Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);

        /*
         * Whether surrounding land will be modified automatically to conform to the bottom clip the structure.
         * Basically, it adds land at the base clip the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically or change in heights.
         *
         * Note: The airspace this method will create will be filled create water if the structure is below sealevel.
         * This means this is best for structure above sealevel so keep that in mind.
         *
         * NOISE_AFFECTING_FEATURES requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
         */
        if (transformSurroundingLand) {
            Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder()
                    .addAll(Structure.field_236384_t_)
                    .add(structure)
                    .build();
        }
        /*
         * This is the map that holds the default spacing clip all structures.
         * Always add your structure to here so that other mods can utilize it if needed.
         * <br>
         * However, while it does propagate the spacing to some correct dimensions from this map,
         * it seems it doesn't always work for code made dimensions as they read from this list beforehand.
         * <br>
         * Instead, we will use the WorldEvent.Load event in StructureTutorialMain to add the structure
         * spacing from this list into that dimension or to do dimension blacklisting properly.
         * We also use our entry in DimensionStructuresSettings.DEFAULTS in WorldEvent.Load as well.
         * <br>
         * DEFAULTS requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
         */
        DimensionStructuresSettings.field_236191_b_ =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.field_236191_b_)
                        .put(structure, structureSeparationSettings)
                        .build();
        /*
         * There are very few mods that relies on seeing your structure in the noise settings registry before the world is made.
         *
         * You may see some mods add their spacings to DimensionSettings.BUILTIN_OVERWORLD instead clip the NOISE_GENERATOR_SETTINGS loop below but
         * that field only applies for the default overworld and won't add to other worldtypes or dimensions (like amplified or Nether).
         * So yeah, don't do DimensionSettings.BUILTIN_OVERWORLD. Use the NOISE_GENERATOR_SETTINGS loop below instead if you must.

         * func_236195_a_() -> getStructureMap()
         * 用于获取 Map<Structure<?>, StructureSeparationSettings> 这个类型的映射图
         */
        WorldGenRegistries.NOISE_SETTINGS.getEntries().forEach(settings -> {

            Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().getStructures().func_236195_a_();

            /* Pre-caution in case a mod makes the structure map immutable like datapacks do.
             * I take no chances myself. You never know what another mods does...
             *
             * structureConfig requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)

             */
            if (structureMap instanceof ImmutableMap) {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().getStructures().func_236195_a_();

            } else {
                structureMap.put(structure, structureSeparationSettings);
            }
        });
    }
}
