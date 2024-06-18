package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.block.decoration.AdobeTileBlock;
import github.thelawf.gensokyoontology.common.block.decoration.ClayAdobeBlock;
import github.thelawf.gensokyoontology.common.block.nature.*;
import github.thelawf.gensokyoontology.common.block.ore.*;
import github.thelawf.gensokyoontology.common.world.feature.tree.SakuraTree;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GensokyoOntology.MODID);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////                     装饰类方块                          /////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //====================================   泥土石头类方块   ====================================//

    public static final RegistryObject<Block> DEFOLIATION_DIRT = BLOCKS.register("defoliation_dirt",() -> new Block(Block.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> KAOLIN = BLOCKS.register("kaolin", KaolinBlock::new);
    public static final RegistryObject<Block> KAOLINITE = BLOCKS.register("kaolinite", KaoliniteBlock::new);

    ////////////////////////////////////////// 落叶堆 ///////////////////////////////////////////
    public static final RegistryObject<Block> GINKGO_LEAVES_PILE = BLOCKS.register("ginkgo_leaves_pile", LeavesPileBlock::new);
    public static final RegistryObject<Block> MAPLE_LEAVES_PILE = BLOCKS.register("maple_leaves_pile", LeavesPileBlock::new);
    public static final RegistryObject<Block> SAKURA_LEAVES_PILE = BLOCKS.register("sakura_leaves_pile", LeavesPileBlock::new);
    public static final RegistryObject<Block> ZELKOVA_LEAVES_PILE = BLOCKS.register("zelkova_leaves_pile", LeavesPileBlock::new);

    ////////////////////////////////////////// 魔法树木 //////////////////////////////////////////
    public static final RegistryObject<Block> MAGIC_LEAVES = BLOCKS.register("magic_leaves", GSKOLeaves::new);

    public static final RegistryObject<Block> MAGIC_LOG = BLOCKS.register("magic_log", GSKOLog::new);
    // public static final RegistryObject<Block> MAGIC_SAPLING =BLOCKS.register("magic_sapling", () -> new SaplingBlock(
    //         new MagicTree(), Block.Properties.copy(Blocks.ACACIA_SAPLING)));
    public static final RegistryObject<Block> MAGIC_BUTTON = BLOCKS.register("magic_button", GSKOWoodButton::new);
    public static final RegistryObject<Block> MAGIC_PLANKS = BLOCKS.register("magic_planks", GSKOPlanks::new);
    public static final RegistryObject<Block> MAGIC_SLAB = BLOCKS.register("magic_slab", GSKOSlab::new);
    public static final RegistryObject<Block> MAGIC_STAIRS = BLOCKS.register("magic_stairs", GSKOStairs::new);
    public static final RegistryObject<Block> MAGIC_DOOR = BLOCKS.register("magic_door", GSKODoor::new);
    public static final RegistryObject<Block> MAGIC_FENCE = BLOCKS.register("magic_fence", GSKOFence::new);
    public static final RegistryObject<Block> MAGIC_FENCE_GATE = BLOCKS.register("magic_fence_gate", GSKOFenceGate::new);
    public static final RegistryObject<Block> MAGIC_TRAPDOOR = BLOCKS.register("magic_trapdoor", GSKOTrapdoor::new);
    public static final RegistryObject<StandingSignBlock> MAGIC_SIGN_BLOCK = BLOCKS.register("magic_sign",
            () -> new StandingSignBlock(Block.Properties.copy(Blocks.ACACIA_SIGN), WoodType.create("magic")));


    //////////////////////////////////////// 樱花树木 //////////////////////////////////////////
     public static final RegistryObject<Block> SAKURA_SAPLING = BLOCKS.register(
             "sakura_sapling", () -> new SaplingBlock(new SakuraTree(),
                     Block.Properties.copy(Blocks.ACACIA_SAPLING)));
    
    public static final RegistryObject<Block> SAKURA_LEAVES = BLOCKS.register("sakura_leaves", GSKOLeaves::new);
    public static final RegistryObject<Block> SAKURA_LOG = BLOCKS.register("sakura_log", GSKOLog::new);
    public static final RegistryObject<Block> SAKURA_PLANKS = BLOCKS.register("sakura_planks", GSKOPlanks::new);
    public static final RegistryObject<Block> SAKURA_BUTTON = BLOCKS.register("sakura_button", GSKOWoodButton::new);
    public static final RegistryObject<Block> SAKURA_STAIRS = BLOCKS.register("sakura_stairs", GSKOStairs::new);
    public static final RegistryObject<Block> SAKURA_SLAB = BLOCKS.register("sakura_slab", GSKOSlab::new);
    public static final RegistryObject<Block> SAKURA_DOOR = BLOCKS.register("sakura_door", GSKODoor::new);
    public static final RegistryObject<Block> SAKURA_FENCE = BLOCKS.register("sakura_fence", GSKOFence::new);
    public static final RegistryObject<Block> SAKURA_FENCE_GATE = BLOCKS.register("sakura_fence_gate", GSKOFenceGate::new);
    public static final RegistryObject<Block> SAKURA_TRAPDOOR = BLOCKS.register("sakura_trapdoor", GSKODoor::new);
    public static final RegistryObject<Block> SAKURA_PRESSRUE_PLATE = BLOCKS.register(
            "sakura_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    Block.Properties.copy(Blocks.ACACIA_PRESSURE_PLATE)));
    // public static final RegistryObject<Block> SAKURA_LEAVES_PILE = BLOCKS.register("sakura_leaves_pile", LeavesPileBlock::new);
    public static final RegistryObject<StandingSignBlock> SAKURA_SIGN_BLOCK = BLOCKS.register("sakura_sign",
            () -> new StandingSignBlock(Block.Properties.copy(Blocks.ACACIA_SIGN), WoodType.create("sakura")));


    ////////////////////////////////////////// 榉树木 //////////////////////////////////////////

    // public static final RegistryObject<Block> ZELKOVA_SAPLING = BLOCKS.register(
    //         "zelkova_sapling", () -> new SaplingBlock(new MapleTree(),
    //                 Block.Properties.copy(Blocks.ACACIA_SAPLING)));
    public static final RegistryObject<Block> ZELKOVA_LEAVES = BLOCKS.register("zelkova_leaves", GSKOLeaves::new);
    public static final RegistryObject<Block> ZELKOVA_LOG = BLOCKS.register("zelkova_log", GSKOLog::new);
    public static final RegistryObject<Block> ZELKOVA_PLANKS = BLOCKS.register("zelkova_planks", GSKOPlanks::new);
    public static final RegistryObject<Block> ZELKOVA_BUTTON = BLOCKS.register("zelkova_button", GSKOWoodButton::new);
    public static final RegistryObject<Block> ZELKOVA_STAIRS = BLOCKS.register("zelkova_stairs", GSKOStairs::new);
    public static final RegistryObject<Block> ZELKOVA_SLAB = BLOCKS.register("zelkova_slab", GSKOSlab::new);
    public static final RegistryObject<Block> ZELKOVA_DOOR = BLOCKS.register("zelkova_door", GSKODoor::new);
    public static final RegistryObject<Block> ZELKOVA_FENCE = BLOCKS.register("zelkova_fence", GSKOFence::new);
    public static final RegistryObject<Block> ZELKOVA_FENCE_GATE = BLOCKS.register("zelkova_fence_gate", GSKOFenceGate::new);
    public static final RegistryObject<Block> ZELKOVA_TRAPDOOR = BLOCKS.register("zelkova_trapdoor", GSKOTrapdoor::new);
    public static final RegistryObject<Block> ZELKOVA_PRESSRUE_PLATE = BLOCKS.register(
            "zelkova_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    Block.Properties.copy(Blocks.ACACIA_PRESSURE_PLATE)));
    public static final RegistryObject<StandingSignBlock> ZELKOVA_SIGN_BLOCK = BLOCKS.register("zelkova_sign",
            () -> new StandingSignBlock(Block.Properties.copy(Blocks.ACACIA_SIGN), WoodType.create("zelkova")));

    ////////////////////////////////////////// 枫木 //////////////////////////////////////////
    // public static final RegistryObject<Block> MAPLE_SAPLING = BLOCKS.register("maple_sapling", () -> new SaplingBlock(new MapleTree(), Block.Properties.copy(Blocks.ACACIA_SAPLING)));
    public static final RegistryObject<Block> MAPLE_LEAVES = BLOCKS.register("maple_leaves", GSKOLeaves::new);
    public static final RegistryObject<Block> MAPLE_LOG = BLOCKS.register("maple_log", GSKOLog::new);
    public static final RegistryObject<Block> MAPLE_PLANKS = BLOCKS.register("maple_planks", GSKOPlanks::new);
    public static final RegistryObject<Block> MAPLE_BUTTON = BLOCKS.register("maple_button", GSKOWoodButton::new);
    public static final RegistryObject<Block> MAPLE_STAIRS = BLOCKS.register("maple_stairs", GSKOStairs::new);
    public static final RegistryObject<Block> MAPLE_SLAB = BLOCKS.register("maple_slab", GSKOSlab::new);
    public static final RegistryObject<Block> MAPLE_DOOR = BLOCKS.register("maple_door", GSKODoor::new);
    public static final RegistryObject<Block> MAPLE_FENCE = BLOCKS.register("maple_fence", GSKOFence::new);
    public static final RegistryObject<Block> MAPLE_FENCE_GATE = BLOCKS.register("maple_fence_gate", GSKOFenceGate::new);
    public static final RegistryObject<Block> MAPLE_TRAPDOOR = BLOCKS.register("maple_trapdoor", GSKOTrapdoor::new);

    ////////////////////////////////////////// 银杏木 //////////////////////////////////////////
    public static final RegistryObject<Block> GINKGO_LEAVES = BLOCKS.register("ginkgo_leaves", GSKOLeaves::new);
    public static final RegistryObject<Block> GINKGO_LOG = BLOCKS.register("ginkgo_log", GSKOLog::new);

    ////////////////////////////////////////// 红杉木 //////////////////////////////////////////
    public static final RegistryObject<Block> REDWOOD_LEAVES = BLOCKS.register("redwood_leaves", GSKOLeaves::new);

    public static final RegistryObject<Block> MAPLE_PRESSURE_PLATE = BLOCKS.register(
            "maple_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    Block.Properties.copy(Blocks.ACACIA_PRESSURE_PLATE)));
    public static final RegistryObject<StandingSignBlock> MAPLE_SIGN_BLOCK = BLOCKS.register("maple_sign",
            () -> new StandingSignBlock(Block.Properties.copy(Blocks.ACACIA_SIGN), WoodType.create("maple")));

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
    // public static final RegistryObject<GlassBlock> CHIREIDEN_COLORED_GLASS = BLOCKS.register(
    //         "chireiden_colored_glass", ChireitenColoredGlassBlock::new);
    public static final RegistryObject<Block> CLAY_ADOBE_BLOCK = BLOCKS.register("clay_adobe", ClayAdobeBlock::new);
    public static final RegistryObject<Block> ADOBE_TILE_BLOCK = BLOCKS.register("adobe_tile", AdobeTileBlock::new);
    // public static final RegistryObject<Block> HANIWA_BLOCK = BLOCKS.register("haniwa", HaniwaBlock::new);

    ///////////////////////////////////////////////////////////////////////////////////
    /////////////                     实用类方块                          ///////////////
    ///////////////////////////////////////////////////////////////////////////////////


    // public static final RegistryObject<Block> ISHI_ZAKURA = BLOCKS.register("ishi_zakura", IshiZakuraBlock::new);

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
                    Block.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<LiquidBlock> SAKE_WINE_BLOCK = BLOCKS.register("sake_wine_block",
            () -> new LiquidBlock(FluidRegistry.SAKE_WINE_SOURCE,
                    Block.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<LiquidBlock> PAPER_PULP_BLOCK = BLOCKS.register("paper_pulp_block",
            () -> new LiquidBlock(FluidRegistry.PAPER_PULP_SOURCE,
                    Block.Properties.copy(Blocks.WATER)));

    /*
    public static final RegistryObject<Block> ONION_CROP_BLOCK = BLOCKS.register(
            "onion_crop", () -> new OnionCropBlock(BlockBehaviour.Properties.copy(Blocks.CARROTS)));

    /// 方块实体
    public static final RegistryObject<Block> DANMAKU_TABLE = BLOCKS.register("danmaku_table", DanmakuTableBlock::new);
    public static final RegistryObject<Block> SORCERY_EXTRACTOR = BLOCKS.register("sorcery_extractor", SorceryExtractorBlock::new);
    public static final RegistryObject<Block> DISPOSABLE_SPAWNER = BLOCKS.register("disposable_spawner", DisposableSpawnerBlock::new);
    // public static final RegistryObject<Block> SPACE_FISSURE_BLOCK = BLOCKS.register("space_fissure_block", SpaceFissureBlock::new);
    public static final RegistryObject<Block> GAP_BLOCK = BLOCKS.register("gap", GapBlock::new);
    public static final RegistryObject<Block> SAISEN_BOX = BLOCKS.register("saisen_box", SaisenBoxBlock::new);
    public static final RegistryObject<Block> SPELL_CARD_CONSOLE = BLOCKS.register("spell_card_console", SpellCardConsoleBlock::new);

     */
}
