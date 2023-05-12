package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.screen.container.DanmakuCraftingContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ContainerRegistry {

    public static final  DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(
            ForgeRegistries.CONTAINERS, GensokyoOntology.MODID);

    public static final RegistryObject<ContainerType<DanmakuCraftingContainer>> DANMAKU_CRAFTING_CONTAINER
            = CONTAINERS.register("danmaku_crafting_container",
            () -> IForgeContainerType.create((windowId, inv, data) -> {
                World world = inv.player.getEntityWorld();
                int id = data.readInt();
                return new DanmakuCraftingContainer(null, windowId,
                        world, id, inv, inv.player);
            }));
}
