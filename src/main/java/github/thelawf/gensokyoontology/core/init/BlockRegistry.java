package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.block.FriedPane;
import github.thelawf.gensokyoontology.common.block.HotSpringBlock;
import github.thelawf.gensokyoontology.common.block.LycorisRadiata;
import github.thelawf.gensokyoontology.common.block.OnionCropBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GensokyoOntology.MODID);

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

}
