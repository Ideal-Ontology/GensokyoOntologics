package github.thelawf.gensokyoontology.client.particle;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(
            ForgeRegistries.PARTICLE_TYPES, GensokyoOntology.MODID);
    public static final RegistryObject<ParticleType<SpaceFissureParticleData>> SPACE_FISSURE = PARTICLE_TYPES.register(
            "space_fissure", SpaceFissureParticleType::new);
    public static final RegistryObject<ParticleType<PowerParticleData>> POWER_PARTICLE = PARTICLE_TYPES.register(
            "power_particle", PowerParticle.Type::new);

    public static void shootParticle(World world, ParticleType<?> particle, Vector3d pos, Vector3d speed){
        world.addParticle((IParticleData) particle, pos.x, pos.y, pos.z, speed.x, speed.y, speed.z);
    }
}
