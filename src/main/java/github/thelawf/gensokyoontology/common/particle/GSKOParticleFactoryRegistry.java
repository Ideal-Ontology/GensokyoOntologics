package github.thelawf.gensokyoontology.common.particle;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;
import java.util.Random;
/*
@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GSKOParticleFactoryRegistry extends ParticleManager{
    public final Map<ResourceLocation, IParticleFactory<?>> factories = new java.util.HashMap<>();
    private final Map<ResourceLocation, GSKOParticleFactoryRegistry.AnimatedSpriteImpl> sprites = Maps.newHashMap();

    public GSKOParticleFactoryRegistry(ClientWorld world, TextureManager textureManager) {
        super(world, textureManager);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)


    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(ParticleRegistry.obsidianParticle.get(), ObsidianParticleFactory::new);
    }
}
    public <T extends IParticleData> void registerFactory(ParticleType<T> particleTypeIn, IParticleFactory<T> particleFactoryIn) {
        this.factories.put(Registry.PARTICLE_TYPE.getKey(particleTypeIn), particleFactoryIn);
    }

    public <T extends IParticleData> void registerFactory(ParticleType<T> particleTypeIn, ParticleManager.IParticleMetaFactory<T> particleMetaFactoryIn) {
        GSKOParticleFactoryRegistry.AnimatedSpriteImpl particlemanager$animatedspriteimpl = new GSKOParticleFactoryRegistry.AnimatedSpriteImpl();
        this.sprites.put(Registry.PARTICLE_TYPE.getKey(particleTypeIn), particlemanager$animatedspriteimpl);
        this.factories.put(Registry.PARTICLE_TYPE.getKey(particleTypeIn), particleMetaFactoryIn.create(particlemanager$animatedspriteimpl));
    }

    @OnlyIn(Dist.CLIENT)
    static
    class AnimatedSpriteImpl implements IAnimatedSprite {
        private List<TextureAtlasSprite> sprites;

        private AnimatedSpriteImpl() {
        }

        public TextureAtlasSprite get(int particleAge, int particleMaxAge) {
            return this.sprites.get(particleAge * (this.sprites.size() - 1) / particleMaxAge);
        }

        public TextureAtlasSprite get(Random rand) {
            return this.sprites.get(rand.nextInt(this.sprites.size()));
        }

        public void setSprites(List<TextureAtlasSprite> sprites) {
            this.sprites = ImmutableList.copyOf(sprites);
        }
    }
}
 */
