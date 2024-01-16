package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.block.*;
import github.thelawf.gensokyoontology.common.block.decoration.ChireitenColoredGlassBlock;
import github.thelawf.gensokyoontology.common.block.decoration.ClayAdobeBlock;
import github.thelawf.gensokyoontology.common.block.decoration.HaniwaBlock;
import github.thelawf.gensokyoontology.common.block.decoration.SaisenBoxBlock;
import github.thelawf.gensokyoontology.common.block.nature.*;
import github.thelawf.gensokyoontology.common.block.ore.*;
import github.thelawf.gensokyoontology.common.world.feature.tree.MapleTree;
import github.thelawf.gensokyoontology.common.world.feature.tree.SakuraTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GensokyoOntology.MODID);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////                     装饰类方块                          /////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //====================================   泥土石头类方块   ====================================//
    public static final RegistryObject<Block> KAOLIN = BLOCKS.register("kaolin", KaolinBlock::new);
    public static final RegistryObject<Block> KAOLINITE = BLOCKS.register("kaolinite", KaoliniteBlock::new);

    ////////////////////////////////////////// 魔法树木 //////////////////////////////////////////
    public static final RegistryObject<Block> MAGIC_LEAVES = BLOCKS.register("magic_leaves", MagicLeaves::new);

    public static final RegistryObject<Block> MAGIC_LOG = BLOCKS.register("magic_log", MagicLog::new);

    // public static final RegistryObject<Block> MAGIC_SAPLING =BLOCKS.register("magic_sapling",
    // () -> new SaplingBlock());
    public static final RegistryObject<Block> MAGIC_BUTTON = BLOCKS.register("magic_button", MagicButton::new);
    public static final RegistryObject<Block> MAGIC_PLANKS = BLOCKS.register("magic_planks", MagicPlanks::new);
    public static final RegistryObject<Block> MAGIC_SLAB = BLOCKS.register("magic_slab", MagicSlab::new);
    public static final RegistryObject<Block> MAGIC_STAIRS = BLOCKS.register("magic_stairs", MagicStairs::new);
    public static final RegistryObject<Block> MAGIC_DOOR = BLOCKS.register("magic_door", MagicDoor::new);
    public static final RegistryObject<Block> MAGIC_FENCE = BLOCKS.register("magic_fence", MagicFence::new);
    public static final RegistryObject<Block> MAGIC_FENCE_GATE = BLOCKS.register("magic_fence_gate", MagicFenceGate::new);
    public static final RegistryObject<Block> MAGIC_TRAPDOOR = BLOCKS.register("magic_trapdoor", MagicTrapdoor::new);
    public static final RegistryObject<StandingSignBlock> MAGIC_SIGN_BLOCK = BLOCKS.register("magic_sign",
            () -> new StandingSignBlock(AbstractBlock.Properties.from(Blocks.ACACIA_SIGN), WoodType.create("magic")));

    ////////////////////////////////////////// 樱花树木 //////////////////////////////////////////
    public static final RegistryObject<Block> SAKURA_SAPLING = BLOCKS.register(
            "sakura_sapling", () -> new SaplingBlock(new SakuraTree(),
                    AbstractBlock.Properties.from(Blocks.ACACIA_SAPLING)));
    public static final RegistryObject<Block> SAKURA_LEAVES = BLOCKS.register("sakura_leaves", SakuraLeaves::new);
    public static final RegistryObject<Block> SAKURA_LOG = BLOCKS.register("sakura_log", SakuraLog::new);
    public static final RegistryObject<Block> SAKURA_PLANKS = BLOCKS.register("sakura_planks", SakuraPlanks::new);
    public static final RegistryObject<Block> SAKURA_BUTTON = BLOCKS.register("sakura_button", SakuraButton::new);
    public static final RegistryObject<Block> SAKURA_STAIRS = BLOCKS.register("sakura_stairs", SakuraStairs::new);
    public static final RegistryObject<Block> SAKURA_SLAB = BLOCKS.register("sakura_slab", SakuraSlab::new);
    public static final RegistryObject<Block> SAKURA_DOOR = BLOCKS.register("sakura_door", SakuraDoor::new);
    public static final RegistryObject<Block> SAKURA_FENCE = BLOCKS.register("sakura_fence", SakuraFence::new);
    public static final RegistryObject<Block> SAKURA_FENCE_GATE = BLOCKS.register("sakura_fence_gate", SakuraFenceGate::new);
    public static final RegistryObject<Block> SAKURA_TRAPDOOR = BLOCKS.register("sakura_trapdoor", SakuraTrapDoor::new);

    public static final RegistryObject<Block> SAKURA_PRESSRUE_PLATE = BLOCKS.register(
            "sakura_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.from(Blocks.ACACIA_PRESSURE_PLATE)));

    public static final RegistryObject<StandingSignBlock> SAKURA_SIGN_BLOCK = BLOCKS.register("sakura_sign",
            () -> new StandingSignBlock(AbstractBlock.Properties.from(Blocks.ACACIA_SIGN), WoodType.create("sakura")));

    ////////////////////////////////////////// 榉树木 //////////////////////////////////////////

    // public static final RegistryObject<Block> ZELKOVA_SAPLING = BLOCKS.register(
    //         "zelkova_sapling", () -> new SaplingBlock(new MapleTree(),
    //                 AbstractBlock.Properties.from(Blocks.ACACIA_SAPLING)));
    public static final RegistryObject<Block> ZELKOVA_LEAVES = BLOCKS.register("zelkova_leaves", ZelkovaLeaves::new);
    public static final RegistryObject<Block> ZELKOVA_LOG = BLOCKS.register("zelkova_log", ZelkovaLog::new);
    public static final RegistryObject<Block> ZELKOVA_PLANKS = BLOCKS.register("zelkova_planks", ZelkovaPlanks::new);
    public static final RegistryObject<Block> ZELKOVA_BUTTON = BLOCKS.register("zelkova_button", ZelkovaButton::new);
    public static final RegistryObject<Block> ZELKOVA_STAIRS = BLOCKS.register("zelkova_stairs", ZelkovaStairs::new);
    public static final RegistryObject<Block> ZELKOVA_SLAB = BLOCKS.register("zelkova_slab", ZelkovaSlab::new);
    public static final RegistryObject<Block> ZELKOVA_DOOR = BLOCKS.register("zelkova_door", ZelkovaDoor::new);
    public static final RegistryObject<Block> ZELKOVA_FENCE = BLOCKS.register("zelkova_fence", ZelkovaFence::new);
    public static final RegistryObject<Block> ZELKOVA_FENCE_GATE = BLOCKS.register("zelkova_fence_gate", ZelkovaFenceGate::new);
    public static final RegistryObject<Block> ZELKOVA_TRAPDOOR = BLOCKS.register("zelkova_trapdoor", ZelkovaTrapdoor::new);
    public static final RegistryObject<Block> ZELKOVA_PRESSRUE_PLATE = BLOCKS.register(
            "zelkova_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.from(Blocks.ACACIA_PRESSURE_PLATE)));
    public static final RegistryObject<StandingSignBlock> ZELKOVA_SIGN_BLOCK = BLOCKS.register("zelkova_sign",
            () -> new StandingSignBlock(AbstractBlock.Properties.from(Blocks.ACACIA_SIGN), WoodType.create("zelkova")));

    ////////////////////////////////////////// 枫木 //////////////////////////////////////////
    public static final RegistryObject<Block> MAPLE_SAPLING = BLOCKS.register(
            "maple_sapling", () -> new SaplingBlock(new MapleTree(),
                    AbstractBlock.Properties.from(Blocks.ACACIA_SAPLING)));
    public static final RegistryObject<Block> MAPLE_LEAVES = BLOCKS.register("maple_leaves", MapleLeaves::new);
    public static final RegistryObject<Block> MAPLE_LOG = BLOCKS.register("maple_log", MapleLog::new);
    public static final RegistryObject<Block> MAPLE_PLANKS = BLOCKS.register("maple_planks", MaplePlanks::new);
    public static final RegistryObject<Block> MAPLE_BUTTON = BLOCKS.register("maple_button", MapleButton::new);
    public static final RegistryObject<Block> MAPLE_STAIRS = BLOCKS.register("maple_stairs", MapleStairs::new);
    public static final RegistryObject<Block> MAPLE_SLAB = BLOCKS.register("maple_slab", MapleSlab::new);
    public static final RegistryObject<Block> MAPLE_DOOR = BLOCKS.register("maple_door", MapleDoor::new);
    public static final RegistryObject<Block> MAPLE_FENCE = BLOCKS.register("maple_fence", MapleFence::new);
    public static final RegistryObject<Block> MAPLE_FENCE_GATE = BLOCKS.register("maple_fence_gate", MapleFenceGate::new);
    public static final RegistryObject<Block> MAPLE_TRAPDOOR = BLOCKS.register("maple_trapdoor", MapleTrapdoor::new);

    ////////////////////////////////////////// 银杏木 //////////////////////////////////////////
    public static final RegistryObject<Block> GINKGO_LEAVES = BLOCKS.register("ginkgo_leaves", GinkgoLeaves::new);
    public static final RegistryObject<Block> GINKGO_LOG = BLOCKS.register("ginkgo_log", GinkgoLog::new);

    ////////////////////////////////////////// 红杉木 //////////////////////////////////////////
    public static final RegistryObject<Block> REDWOOD_LEAVES = BLOCKS.register("redwood_leaves", RedwoodLeaves::new);

    public static final RegistryObject<Block> MAPLE_PRESSURE_PLATE = BLOCKS.register(
            "maple_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.from(Blocks.ACACIA_PRESSURE_PLATE)));
    public static final RegistryObject<StandingSignBlock> MAPLE_SIGN_BLOCK = BLOCKS.register("maple_sign",
            () -> new StandingSignBlock(AbstractBlock.Properties.from(Blocks.ACACIA_SIGN), WoodType.create("maple")));

    /////////////////////////////     蘑菇方块      ////////////////////////////////
    public static final RegistryObject<Block> BLUE_MUSHROOM_BLOCK = BLOCKS.register(
            "blue_mushroom_block", BlueMushroomBlock::new);
    public static final RegistryObject<Block> PURPLE_MUSHROOM_BLOCK = BLOCKS.register(
            "purple_mushroom_block", PurpleMushroomBlock::new);

    /////////////////////////////     草本植物     ////////////////////////////////
    public static final RegistryObject<Block> BLUE_ROSE_BUSH = BLOCKS.register(
            "blue_rose_bush", BlueRoseBush::new);
    public static final RegistryObject<Block> LYCORIS_RADIATA = BLOCKS.register(
            "lycoris_radiata", LycorisRadiata::new);
    public static final RegistryObject<Block> WASABI_BLOCK = BLOCKS.register(
            "wasabi", WasabiBlock::new);

    /////////////////////////////     工艺装饰类方块     ////////////////////////////////
    public static final RegistryObject<GlassBlock> CHIREIDEN_COLORED_GLASS = BLOCKS.register(
            "chireiden_colored_glass", ChireitenColoredGlassBlock::new);
    public static final RegistryObject<Block> CLAY_ADOBE_BLOCK = BLOCKS.register("clay_adobe", ClayAdobeBlock::new);
    public static final RegistryObject<Block> HANIWA_BLOCK = BLOCKS.register("haniwa", HaniwaBlock::new);

    ///////////////////////////////////////////////////////////////////////////////////
    /////////////                     实用类方块                          ///////////////
    ///////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Block> ISHI_ZAKURA = BLOCKS.register("ishi_zakura", IshiZakuraBlock::new);

    // ============================== 矿石类方块 ================================== //
    public static final RegistryObject<Block> IZANO_OBJECT_ORE = BLOCKS.register(
            "izano_object_ore", IzanoObjectOre::new);
    public static final RegistryObject<Block> DRAGON_SPHERE_ORE = BLOCKS.register(
            "dragon_sphere_ore", DragonSphereOre::new);
    public static final RegistryObject<Block> CRIMSON_ALLOY_BLOCK = BLOCKS.register(
            "crimson_alloy_block", CrimsonAlloyBlock::new);
    public static final RegistryObject<Block> CRIMSON_METAL_BLOCK = BLOCKS.register(
            "crimson_metal_block", CrimsonMetalBlock::new);
    public static final RegistryObject<Block> IMMEMORIAL_ALLOY_BLOCK = BLOCKS.register(
            "immemorial_alloy_block", ImmemorialAlloyBlock::new);

    public static final RegistryObject<Block> JADE_ORE = BLOCKS.register(
            "jade_ore", JadeOreBlock::new);
    public static final RegistryObject<Block> JADE_BLOCK = BLOCKS.register(
            "jade_block", JadeBlock::new);

    // ==============================流体方块 ================================== //
    public static final RegistryObject<HotSpringBlock> HOT_SPRING_BLOCK = BLOCKS.register("hot_spring_block",
            () -> new HotSpringBlock(FluidRegistry.HOT_SPRING_SOURCE,
                    AbstractBlock.Properties.from(Blocks.WATER)));
    public static final RegistryObject<FlowingFluidBlock> SAKE_WINE_BLOCK = BLOCKS.register("sake_wine_block",
            () -> new FlowingFluidBlock(FluidRegistry.SAKE_WINE_SOURCE,
                    AbstractBlock.Properties.from(Blocks.WATER)));
    public static final RegistryObject<FlowingFluidBlock> PAPER_PULP_BLOCK = BLOCKS.register("paper_pulp_block",
            () -> new FlowingFluidBlock(FluidRegistry.PAPER_PULP_SOURCE,
                    AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<Block> ONION_CROP_BLOCK = BLOCKS.register(
            "onion_crop", () -> new OnionCropBlock(AbstractBlock.Properties.from(Blocks.CARROTS)));

    /// 方块实体
    public static final RegistryObject<Block> DANMAKU_TABLE = BLOCKS.register("danmaku_table", DanmakuTableBlock::new);
    public static final RegistryObject<Block> SORCERY_EXTRACTOR = BLOCKS.register("sorcery_extractor", SorceryExtractorBlock::new);
    public static final RegistryObject<Block> DISPOSABLE_SPAWNER = BLOCKS.register("disposable_spawner", DisposableSpawnerBlock::new);
    public static final RegistryObject<Block> SPACE_FISSURE_BLOCK = BLOCKS.register("space_fissure_block", SpaceFissureBlock::new);
    public static final RegistryObject<Block> GAP_BLOCK = BLOCKS.register("gap", GapBlock::new);
    public static final RegistryObject<Block> SAISEN_BOX = BLOCKS.register("saisen_box", SaisenBoxBlock::new);
    public static final RegistryObject<Block> SPELL_CARD_CONSOLE = BLOCKS.register("spell_card_console", SpellCardConsoleBlock::new);

}
