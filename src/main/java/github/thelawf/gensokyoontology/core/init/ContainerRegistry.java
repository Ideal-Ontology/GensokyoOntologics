package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.*;
import github.thelawf.gensokyoontology.common.container.script.*;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ContainerRegistry {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(
            ForgeRegistries.CONTAINERS, GensokyoOntology.MODID);

    public static final RegistryObject<ContainerType<DanmakuCraftingContainer>> DANMAKU_CRAFTING_CONTAINER =
            CONTAINERS.register("danmaku_crafting_container", () -> IForgeContainerType.create(
                    (windowId, inv, data) -> new DanmakuCraftingContainer(windowId, inv, data.readBlockPos())));

    public static final RegistryObject<ContainerType<KogasaSmithingContainer>> KOGASA_SMITHING_CONTAINER =
            CONTAINERS.register("kogasa_smithing_container", () -> IForgeContainerType.create(
                    (windowId, inv, data) -> new KogasaSmithingContainer(inv, windowId)));

    public static final RegistryObject<ContainerType<SorceryExtractorContainer>> SORCERY_EXTRACTOR_CONTAINER =
            CONTAINERS.register("sorcery_extractor_container", () -> IForgeContainerType.create(
                    ((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        World world = inv.player.world;
                        return new SorceryExtractorContainer(windowId, world, pos, inv);
                    })));
    public static final RegistryObject<ContainerType<AltarContainer>> ALTAR_CONTAINER =
            CONTAINERS.register("altar_container", () -> IForgeContainerType.create(
                    (windowId, inv, data) -> new AltarContainer(windowId, inv, data.readBlockPos())));

    public static final RegistryObject<ContainerType<BinaryOperationContainer>> BINARY_OPERATION_CONTAINER =
            CONTAINERS.register("binary_operation_container", () -> IForgeContainerType.create(
                    ((windowId, inv, data) -> new BinaryOperationContainer(windowId, inv))));
    public static final RegistryObject<ContainerType<V3dInvokerContainer>> V3D_INVOKER_CONTAINER =
            CONTAINERS.register("v3d_invoker_container", () -> IForgeContainerType.create(
                    ((windowId, inv, data) -> new V3dInvokerContainer(windowId, inv))));
    public static final RegistryObject<ContainerType<SpellCardConsoleContainer>> SPELL_CONSOLE_CONTAINER =
            CONTAINERS.register("spell_console_container", () -> IForgeContainerType.create(
                    ((windowId, inv, data) -> new SpellCardConsoleContainer(windowId, inv.player, inv.player.world, data.readBlockPos()))));
    public static final RegistryObject<ContainerType<StaticInvokerContainer>> STATIC_INVOKER_CONTAINER =
            CONTAINERS.register("static_invoker_container", () -> IForgeContainerType.create(
                    ((windowId, inv, data) -> new StaticInvokerContainer(windowId, inv))));

    public static final RegistryObject<ContainerType<CBContainer>> CB_CONTAINER =
            CONTAINERS.register("cb_container", () -> IForgeContainerType.create(
                    ((windowId, inv, data) -> new CBContainer(windowId, inv))));
    public static final RegistryObject<ContainerType<V3DBContainer>> V3DB_CONTAINER =
            CONTAINERS.register("v3db_container", () -> IForgeContainerType.create(
                    ((windowId, inv, data) -> new V3DBContainer(windowId, inv))));
    public static final RegistryObject<ContainerType<DanmakuBuilderContainer>> DB_CONTAINER =
            CONTAINERS.register("db_container", () -> IForgeContainerType.create(
                    ((windowId, inv, data) -> new DanmakuBuilderContainer(windowId, inv))));

}
