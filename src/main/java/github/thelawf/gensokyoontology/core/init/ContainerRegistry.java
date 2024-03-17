package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.client.gui.container.SorceryExtractorContainer;
import github.thelawf.gensokyoontology.client.gui.container.script.BinaryOperationContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ContainerRegistry {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(
            ForgeRegistries.CONTAINERS, GensokyoOntology.MODID);

    public static final RegistryObject<ContainerType<DanmakuCraftingContainer>> DANMAKU_CRAFTING_CONTAINER =
            CONTAINERS.register("danmaku_crafting_container", () -> IForgeContainerType.create(
                    (windowId, inv, data) -> new DanmakuCraftingContainer(windowId, inv, inv.player)));
    public static final RegistryObject<ContainerType<SorceryExtractorContainer>> SORCERY_EXTRACTOR_CONTAINER =
            CONTAINERS.register("sorcery_extractor_container", () -> IForgeContainerType.create(
                    ((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        World world = inv.player.world;
                        return new SorceryExtractorContainer(windowId, world, pos, inv);
                    })));

    public static final RegistryObject<ContainerType<BinaryOperationContainer>> BINARY_OPERATION_CONTAINER =
            CONTAINERS.register("binary_operation_container", () -> IForgeContainerType.create(
                    ((windowId, inv, data) -> new BinaryOperationContainer(windowId, inv))));

}
