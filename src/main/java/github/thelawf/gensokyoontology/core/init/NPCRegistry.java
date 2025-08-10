package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.entity.VillagerOrder;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;

public class NPCRegistry {
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS,
            GensokyoOntology.MODID);
   public static final RegistryKey<Registry<VillagerOrder>> ORDER_KEY = RegistryKey.getOrCreateRootKey(
           GensokyoOntology.withRL("villager_order"));

}
