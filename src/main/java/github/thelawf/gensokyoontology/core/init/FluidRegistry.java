package github.thelawf.gensokyoontology.core.init;


import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class FluidRegistry {
    public static final ResourceLocation STILL_HOTSPRING_TEX =
            new ResourceLocation("block/water_still");
    public static final ResourceLocation FLOW_HOTSPRING_TEX =
            new ResourceLocation("block/water_flow");

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, GensokyoOntology.MODID);

    public static RegistryObject<FlowingFluid> HOT_SPRING_SOURCE =
            FLUIDS.register("hot_spring_fluid", () ->
                    new ForgeFlowingFluid.Source(FluidRegistry.HOT_SPRING_PROPERTIES));
    public static RegistryObject<FlowingFluid> HOT_SPRING_FLOWING =
            FLUIDS.register("hot_spring_fluid_flowing", () ->
                    new ForgeFlowingFluid.Flowing(FluidRegistry.HOT_SPRING_PROPERTIES));

    public static ForgeFlowingFluid.Properties HOT_SPRING_PROPERTIES = new ForgeFlowingFluid.Properties(
            HOT_SPRING_SOURCE, HOT_SPRING_FLOWING, FluidAttributes.builder(
                    STILL_HOTSPRING_TEX,FLOW_HOTSPRING_TEX).color(0xFF00FFFF).density(5000).viscosity(4000))
            .bucket(ItemRegistry.HOTSPRING_BUCKET).block(BlockRegistry.HOT_SPRING_BLOCK)
            .slopeFindDistance(3).explosionResistance(100F);

    /*
    public static RegistryObject<ForgeFlowingFluid> HOT_SPRING_SOURCE = FLUIDS.register("hot_spring_fluid",
            () -> new HotSpringWater.Source(FluidRegistry.HOT_SPRING_PROPERTIES));

    public static RegistryObject<HotSpringWater.Flowing> HOT_SPRING_FLOWING = FLUIDS.register("hot_spring_fluid_flowing",
            () -> new HotSpringWater.Flowing(FluidRegistry.HOT_SPRING_PROPERTIES));

    public static ForgeFlowingFluid.Properties HOT_SPRING_PROPERTIES = new ForgeFlowingFluid.Properties(
            HOT_SPRING_SOURCE,HOT_SPRING_FLOWING, FluidAttributes.builder(
            STILL_HOTSPRING_TEX,FLOW_HOTSPRING_TEX).color(0xFFF0FFAA).density(5000).viscosity(4000))
            .bucket(ItemRegistry.HOTSPRING_BUCKET).block(BlockRegistry.HOT_SPRING_BLOCK)
            .slopeFindDistance(3).explosionResistance(100F);

     */

}
