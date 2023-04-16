package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.block.*;
import github.thelawf.gensokyoontology.common.block.cyber.ComputerBlock;
import github.thelawf.gensokyoontology.common.block.cyber.FractalLeaves;
import github.thelawf.gensokyoontology.common.block.cyber.HashLeaves;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GensokyoOntology.MODID);

    public static final RegistryObject<Block> MAGIC_LEAVES = BLOCKS.register("magic_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.create(
                    Material.LEAVES).tickRandomly().sound(SoundType.PLANT)));

    public static final RegistryObject<Block> MAGIC_LOG = BLOCKS.register(
            "magic_log", MagicLog::new);

    // public static final RegistryObject<Block> MAGIC_SAPLING =BLOCKS.register("magic_sapling",
    //         () -> new SaplingBlock());

    public static final RegistryObject<Block> SAKURA_LEAVES = BLOCKS.register("sakura_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.create
                    (Material.LEAVES).tickRandomly().sound(SoundType.PLANT)));

    public static final RegistryObject<Block> SAKURA_LOG = BLOCKS.register(
            "sakura_log", SakuraLog::new);

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

    public static RegistryObject<HotSpringBlock> HOT_SPRING_BLOCK = BLOCKS.register("hot_spring_block",
            () -> new HotSpringBlock(() -> FluidRegistry.HOT_SPRING_SOURCE.get(),
            Block.Properties.create(Material.WATER).doesNotBlockMovement()
                                .hardnessAndResistance(100.0f).noDrops()));
    public static final RegistryObject<Block> LYCORIS_RADIATA =
            BLOCKS.register("lycoris_radiata", LycorisRadiata::new);

    public static final RegistryObject<FriedPane> FRIED_PANE = BLOCKS.register(
            "fried_pane",FriedPane::new
    );

    public static final RegistryObject<Block> ONION_CROP_BLOCK = BLOCKS.register(
            "onion_crop", () -> new OnionCropBlock(AbstractBlock.Properties.from(Blocks.CARROTS)));

    public static final RegistryObject<Block> SPACE_FISSURE_BLOCK = BLOCKS.register(
            "space_fissure_block", SpaceFissureBlock::new);

    public static final RegistryObject<Block> SUKIMA_BLOCK = BLOCKS.register("sukima",
            SukimaBlock::new);

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
