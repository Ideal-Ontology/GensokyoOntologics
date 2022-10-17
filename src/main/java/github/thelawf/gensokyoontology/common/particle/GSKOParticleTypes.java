package github.thelawf.gensokyoontology.common.particle;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.command.impl.ParticleCommand;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("deprecation")
public class GSKOParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(
            ForgeRegistries.PARTICLE_TYPES,GensokyoOntology.MODID);

    public static final GSKOBasicParticleType SPACE_FISSURE = register("space_fissure",true);
    public static final GSKOBasicParticleType SENKO_HANABI_SPARK = register("senko_hanabi_spark",false);

    /*
    public static final RegistryObject<ParticleType<?>> SPACE_FISSURE = PARTICLE_TYPES.register(
            "space_fissure",() -> new GSKOBasicParticleType(true));
     */
    private static GSKOBasicParticleType register(String key, boolean alwaysShow) {
        return Registry.register(Registry.PARTICLE_TYPE, key, new GSKOBasicParticleType(alwaysShow));
    }
}

