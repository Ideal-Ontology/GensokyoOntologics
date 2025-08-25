package github.thelawf.gensokyoontology.client.particle;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(
            ForgeRegistries.PARTICLE_TYPES, GensokyoOntology.MODID);
    public static final RegistryObject<ParticleType<SpaceFissureParticleData>> SPACE_FISSURE = PARTICLE_TYPES.register(
            "space_fissure", SpaceFissureParticleType::new);
    public static final RegistryObject<PowerParticle.Type> POWER_PARTICLE = PARTICLE_TYPES.register(
            "power_particle", PowerParticle.Type::new);

}
