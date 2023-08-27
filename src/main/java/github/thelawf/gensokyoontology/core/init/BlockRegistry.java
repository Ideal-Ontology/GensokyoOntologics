package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.block.*;
import github.thelawf.gensokyoontology.common.block.cyber.ComputerBlock;
import github.thelawf.gensokyoontology.common.block.cyber.HashLeaves;
import github.thelawf.gensokyoontology.common.block.decoration.ChireitenColoredGlassBlock;
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

    ///////////////////////////////////////////////////////////////////////////////////
    /////////////                     装饰类方块                          ///////////////
    ///////////////////////////////////////////////////////////////////////////////////
    public static final RegistryObject<Block> MAGIC_LEAVES = BLOCKS.register(
            "magic_leaves", MagicLeaves::new);

    public static final RegistryObject<Block> MAGIC_LOG = BLOCKS.register(
            "magic_log", MagicLog::new);

    // public static final RegistryObject<Block> MAGIC_SAPLING =BLOCKS.register("magic_sapling",
    //         () -> new SaplingBlock());

    public static final RegistryObject<Block> SAKURA_SAPLING = BLOCKS.register(
            "sakura_sapling", () -> new SaplingBlock(new SakuraTree(),
                    AbstractBlock.Properties.from(Blocks.ACACIA_SAPLING)));
    public static final RegistryObject<Block> SAKURA_LEAVES = BLOCKS.register(
            "sakura_leaves", SakuraLeaves::new);

    public static final RegistryObject<Block> SAKURA_LOG = BLOCKS.register(
            "sakura_log", SakuraLog::new);

    public static final RegistryObject<Block> SAKURA_PLANKS = BLOCKS.register(
            "sakura_planks", SakuraPlanks::new);

    public static final RegistryObject<Block> SAKURA_BUTTON = BLOCKS.register(
            "sakura_button", SakuraButton::new);

    public static final RegistryObject<Block> SAKURA_STAIRS = BLOCKS.register(
            "sakura_stairs", SakuraStairs::new);

    public static final RegistryObject<Block> SAKURA_SLAB = BLOCKS.register(
            "sakura_slab", SakuraSlab::new);

    public static final RegistryObject<Block> SAKURA_DOOR = BLOCKS.register(
            "sakura_door", SakuraDoor::new);

    public static final RegistryObject<Block> SAKURA_FENCE = BLOCKS.register(
            "sakura_fence", SakuraFence::new);

    public static final RegistryObject<Block> SAKURA_FENCE_GATE = BLOCKS.register(
            "sakura_fence_gate", SakuraFenceGate::new);

    public static final RegistryObject<Block> SAKURA_TRAPDOOR = BLOCKS.register(
            "sakura_trapdoor", SakuraTrapDoor::new);

    public static final RegistryObject<Block> SAKURA_PRESSRUE_PLATE = BLOCKS.register(
            "sakura_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.from(Blocks.ACACIA_PRESSURE_PLATE)));

    //////////////////////////////// 榉树木 ////////////////////////////////
    // public static final RegistryObject<Block> FAGUS_SAPLING = BLOCKS.register(
    //         "fagus_sapling", () -> new SaplingBlock(new MapleTree(),
    //                 AbstractBlock.Properties.from(Blocks.ACACIA_SAPLING)));
    public static final RegistryObject<Block> FAGUS_LEAVES = BLOCKS.register(
            "fagus_leaves", FagusLeaves::new);

    public static final RegistryObject<Block> FAGUS_LOG = BLOCKS.register(
            "fagus_log", FagusLog::new);

    public static final RegistryObject<Block> FAGUS_PLANKS = BLOCKS.register(
            "fagus_planks", FagusPlanks::new);

    public static final RegistryObject<Block> FAGUS_BUTTON = BLOCKS.register(
            "fagus_button", FagusButton::new);

    public static final RegistryObject<Block> FAGUS_STAIRS = BLOCKS.register(
            "fagus_stairs", FagusStairs::new);

    public static final RegistryObject<Block> FAGUS_SLAB = BLOCKS.register(
            "fagus_slab", FagusSlab::new);

    public static final RegistryObject<Block> FAGUS_DOOR = BLOCKS.register(
            "fagus_door", FagusDoor::new);

    public static final RegistryObject<Block> FAGUS_FENCE = BLOCKS.register(
            "fagus_fence", FagusFence::new);

    public static final RegistryObject<Block> FAGUS_FENCE_GATE = BLOCKS.register(
            "fagus_fence_gate", FagusFenceGate::new);

    public static final RegistryObject<Block> FAGUS_TRAPDOOR = BLOCKS.register(
            "fagus_trapdoor", FagusTrapdoor::new);

    public static final RegistryObject<Block> FAGUS_PRESSRUE_PLATE = BLOCKS.register(
            "fagus_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.from(Blocks.ACACIA_PRESSURE_PLATE)));

    //////////////////////////////// 枫木 ////////////////////////////////
    public static final RegistryObject<Block> MAPLE_SAPLING = BLOCKS.register(
            "maple_sapling", () -> new SaplingBlock(new MapleTree(),
                    AbstractBlock.Properties.from(Blocks.ACACIA_SAPLING)));
    public static final RegistryObject<Block> MAPLE_LEAVES = BLOCKS.register(
            "maple_leaves", MapleLeaves::new);

    public static final RegistryObject<Block> MAPLE_LOG = BLOCKS.register(
            "maple_log", MapleLog::new);

    public static final RegistryObject<Block> MAPLE_PLANKS = BLOCKS.register(
            "maple_planks", MaplePlanks::new);

    public static final RegistryObject<Block> MAPLE_BUTTON = BLOCKS.register(
            "maple_button", MapleButton::new);

    public static final RegistryObject<Block> MAPLE_STAIRS = BLOCKS.register(
            "maple_stairs", MapleStairs::new);

    public static final RegistryObject<Block> MAPLE_SLAB = BLOCKS.register(
            "maple_slab", MapleSlab::new);

    public static final RegistryObject<Block> MAPLE_DOOR = BLOCKS.register(
            "maple_door", MapleDoor::new);

    public static final RegistryObject<Block> MAPLE_FENCE = BLOCKS.register(
            "maple_fence", MapleFence::new);

    public static final RegistryObject<Block> MAPLE_FENCE_GATE = BLOCKS.register(
            "maple_fence_gate", MapleFenceGate::new);

    public static final RegistryObject<Block> MAPLE_TRAPDOOR = BLOCKS.register(
            "maple_trapdoor", MapleTrapdoor::new);

    public static final RegistryObject<Block> MAPLE_PRESSURE_PLATE = BLOCKS.register(
            "maple_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    AbstractBlock.Properties.from(Blocks.ACACIA_PRESSURE_PLATE)));

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

    ///////////////////////////////////////////////////////////////////////////////////
    /////////////                     实用类方块                          ///////////////
    ///////////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Block> ISHI_ZAKURA = BLOCKS.register("ishi_zakura", IshiZakuraBlock::new);

    // ============================== 矿石类方块 ================================== //
    public static final RegistryObject<Block> DRAGON_SPHERE_ORE = BLOCKS.register(
            "dragon_sphere_ore", DragonSphereOre::new);
    public static final RegistryObject<Block> CRIMSON_METAL_ORE = BLOCKS.register(
            "diamond_ore", CrimsonOreBlock::new);

    public static final RegistryObject<Block> CRIMSON_ALLOY_BLOCK = BLOCKS.register(
            "crimson_alloy_block", CrimsonAlloyBlock::new);
    public static final RegistryObject<Block> CRIMSON_METAL_BLOCK = BLOCKS.register(
            "crimson_metal_block", CrimsonMetalBlock::new);
    public static final RegistryObject<Block> CRIMSON_ORE_BLOCK = BLOCKS.register(
            "crimson_ore_block", CrimsonOreBlock::new);

    public static final RegistryObject<Block> JADE_ORE = BLOCKS.register(
            "jade_ore", JadeOreBlock::new);
    public static final RegistryObject<Block> JADE_BLOCK = BLOCKS.register(
            "jade_block", JadeBlock::new);

    // ==============================流体方块 ================================== //
    public static final RegistryObject<HotSpringBlock> HOT_SPRING_BLOCK = BLOCKS.register("hot_spring_block",
            () -> new HotSpringBlock(FluidRegistry.HOT_SPRING_SOURCE,
            Block.Properties.create(Material.WATER).doesNotBlockMovement()
                                .hardnessAndResistance(100.0f).noDrops()));

    public static final RegistryObject<FlowingFluidBlock> SAKE_WINE_BLOCK = BLOCKS.register("sake_wine_block",
            () -> new FlowingFluidBlock(FluidRegistry.SAKE_WINE_SOURCE,
                    AbstractBlock.Properties.from(Blocks.WATER)));

    public static final RegistryObject<Block> ONION_CROP_BLOCK = BLOCKS.register(
            "onion_crop", () -> new OnionCropBlock(AbstractBlock.Properties.from(Blocks.CARROTS)));

    /// 方块实体
    public static final RegistryObject<Block> DANMAKU_TABLE = BLOCKS.register(
            "danmaku_table", DanmakuTableBlock::new);
    public static final RegistryObject<Block> SORCERY_EXTRACTOR = BLOCKS.register(
            "sorcery_extractor", SorceryExtractorBlock::new);

    public static final RegistryObject<Block> SPACE_FISSURE_BLOCK = BLOCKS.register(
            "space_fissure_block", SpaceFissureBlock::new);

    public static final RegistryObject<Block> GAP_BLOCK = BLOCKS.register("gap",
            GapBlock::new);

    //======================= ↓ The Mod Cyber Statistics ↓ ==================//
    public static final RegistryObject<Block> HASH_LOG = BLOCKS.register("hash_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> HASH_LEAVES = BLOCKS.register(
            "hash_leaves", HashLeaves::new);

    public static final RegistryObject<Block> FRACTAL_LOG = BLOCKS.register("fractal_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));

    // public static final RegistryObject<Block> FRACTAL_LEAVES = BLOCKS.register("magic_leaves",
    //         FractalLeaves::new);

    public static final RegistryObject<Block> IC_BOARD_LOG = BLOCKS.register("ic_board_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> IC_LEAVES = BLOCKS.register("ic_leaves", () ->
            new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).tickRandomly().sound(SoundType.PLANT)));

    //======================= ↑ The Mod Cyber Statistics ↑ ====================//
    public static final RegistryObject<Block> COMPUTER_BLOCK = BLOCKS.register("computer",
            ComputerBlock::new);

    public static final RegistryObject<Block> RAIL_TRACK_BLOCK = BLOCKS.register("rail_track",
            RailTrackBlock::new);

    public static final RegistryObject<Block> RAIL_NODE_BLOCK = BLOCKS.register("rail_node",
            RailNodeBlock::new);

    public static final RegistryObject<Block> DAKIMAKURA = BLOCKS.register("dakimakura",
            Dakimakura::new);

    public static final RegistryObject<Block> ROTATE_FRAME = BLOCKS.register("rotate_frame",
            RotateFrameBlock::new);

}
