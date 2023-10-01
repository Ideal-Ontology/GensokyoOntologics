package github.thelawf.gensokyoontology.core.init;


import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class FluidRegistry {
    public static final ResourceLocation STILL_HOTSPRING_TEX = new ResourceLocation(
            GensokyoOntology.MODID, "tileentity/water_still");
    public static final ResourceLocation FLOW_HOTSPRING_TEX = new ResourceLocation(
            GensokyoOntology.MODID, "tileentity/water_flow");

    public static final ResourceLocation SAKE_WINE_STILL_TEX = STILL_HOTSPRING_TEX;
    public static final ResourceLocation SAKE_WINE_FLOW_TEX = FLOW_HOTSPRING_TEX;

    public static final ResourceLocation PAPER_PULP_STILL_TEX = STILL_HOTSPRING_TEX;
    public static final ResourceLocation PAPER_PULP_FLOW_TEX = FLOW_HOTSPRING_TEX;

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(
            ForgeRegistries.FLUIDS, GensokyoOntology.MODID);

    public static final RegistryObject<FlowingFluid> HOT_SPRING_SOURCE = FLUIDS.register(
            "hot_spring_fluid",
            () -> new ForgeFlowingFluid.Source(FluidRegistry.HOT_SPRING_PROPERTIES));
    public static final RegistryObject<FlowingFluid> HOT_SPRING_FLOWING = FLUIDS.register(
            "hot_spring_fluid_flowing",
            () -> new ForgeFlowingFluid.Flowing(FluidRegistry.HOT_SPRING_PROPERTIES));

    public static final RegistryObject<FlowingFluid> PAPER_PULP_SOURCE = FLUIDS.register(
            "paper_pulp_fluid",
            () -> new ForgeFlowingFluid.Source(FluidRegistry.PAPER_PULP_PROPERTIES));
    public static final RegistryObject<FlowingFluid> PAPER_PULP_FLOWING = FLUIDS.register(
            "paper_pulp_fluid_flowing",
            () -> new ForgeFlowingFluid.Flowing(FluidRegistry.PAPER_PULP_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SAKE_WINE_SOURCE = FLUIDS.register(
            "sake_wine_fluid", () -> new ForgeFlowingFluid.Source(FluidRegistry.SAKE_WINE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> SAKE_WINE_FLOWING = FLUIDS.register(
            "sake_wine_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(FluidRegistry.SAKE_WINE_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HOT_SPRING_PROPERTIES = new ForgeFlowingFluid.Properties(
            HOT_SPRING_SOURCE, HOT_SPRING_FLOWING, FluidAttributes.builder(STILL_HOTSPRING_TEX,FLOW_HOTSPRING_TEX)
            .color(0xFF00FFFF).density(5000).viscosity(4000))
            .bucket(ItemRegistry.HOTSPRING_BUCKET).block(BlockRegistry.HOT_SPRING_BLOCK)
            .slopeFindDistance(3).explosionResistance(100F);

    public static final ForgeFlowingFluid.Properties SAKE_WINE_PROPERTIES = new ForgeFlowingFluid.Properties(
            SAKE_WINE_SOURCE, SAKE_WINE_FLOWING, FluidAttributes.builder(SAKE_WINE_STILL_TEX, SAKE_WINE_FLOW_TEX)
            .color(0xFF8888BB)
            .density(3000)
            .viscosity(3800))
            .bucket(ItemRegistry.SAKE_BUCKET)
            .block(BlockRegistry.SAKE_WINE_BLOCK)
            .slopeFindDistance(3).explosionResistance(100F);

    public static final ForgeFlowingFluid.Properties PAPER_PULP_PROPERTIES = new ForgeFlowingFluid.Properties(
            PAPER_PULP_SOURCE, PAPER_PULP_FLOWING, FluidAttributes.builder(PAPER_PULP_STILL_TEX, PAPER_PULP_FLOW_TEX)
            .color(0xDDE1C699)
            .density(6000)
            .viscosity(2800))
            .bucket(ItemRegistry.PAPER_PULP_BUCKET)
            .block(BlockRegistry.PAPER_PULP_BLOCK)
            .slopeFindDistance(3).explosionResistance(100F);
}
