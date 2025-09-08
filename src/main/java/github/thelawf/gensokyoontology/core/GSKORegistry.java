package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GSKORegistry {

    public static final DeferredRegister<DataSerializerEntry> SPELL_DATA_SERIALIZER = DeferredRegister.create(
            ForgeRegistries.DATA_SERIALIZERS, GensokyoOntology.MODID);
    public static final DeferredRegister<Activity> ACTIVITIES = DeferredRegister.create(
            ForgeRegistries.ACTIVITIES, GensokyoOntology.MODID);

    public static final RegistryObject<Activity> ORDERING = ACTIVITIES.register("ordering", () -> new Activity("ordering"));
}
