package github.thelawf.gensokyoontology.client.particle;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector4i;

public class PowerParticle extends SpriteTexturedParticle {
    protected PowerParticle(ClientWorld world, double x, double y, double z, Vector3d speed, Vector4i color) {
        super(world, x, y, z, speed.x, speed.y, speed.z);
        this.maxAge = 100;
        this.motionX = speed.x;
        this.motionY = speed.y;
        this.motionZ = speed.z;

        this.setColor(color.x / 255F, color.y / 255F, color.z / 255F);
        this.setAlphaF(color.w / 255F);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Type extends ParticleType<PowerParticleData> {

        public Type() {
            super(true, PowerParticleData.DESERIALIZER);
        }

        @Override
        public Codec<PowerParticleData> func_230522_e_() {
            return Codec.unit(new PowerParticleData(new Vector3d(0,0,0), new Vector4i()));
        }
    }

    public static class Factory implements IParticleFactory<PowerParticleData> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        private Factory() {
            throw new UnsupportedOperationException("Use the SpaceFissureParticle.Factory(IAnimatedSprite sprite) constructor");
        }

        @Nullable
        @Override
        public Particle makeParticle(PowerParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            PowerParticle particle = new PowerParticle(worldIn, x, y, z, typeIn.getSpeed(), typeIn.getColor());
            particle.selectSpriteWithAge(spriteSet);
            return particle;
        }
    }
}
