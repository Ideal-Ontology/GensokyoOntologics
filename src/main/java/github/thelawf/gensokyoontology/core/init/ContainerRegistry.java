package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.client.gui.container.SorceryExtractorContainer;
import net.minecraft.inventory.container.ContainerType;
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
                    ((windowId, inv, data) -> new SorceryExtractorContainer(windowId, inv, inv.player))));

}
