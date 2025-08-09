package github.thelawf.gensokyoontology.client.particle;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GSKOParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(
            ForgeRegistries.PARTICLE_TYPES, GensokyoOntology.MODID);
    public static final RegistryObject<ParticleType<SpaceFissureParticleData>> SPACE_FISSURE = PARTICLE_TYPES.register(
            "space_fissure", SpaceFissureParticleType::new);
    public static final RegistryObject<ParticleType<PowerParticleData>> POWER_PARTICLE = PARTICLE_TYPES.register(
            "power_particle", PowerParticle.Type::new);
}
