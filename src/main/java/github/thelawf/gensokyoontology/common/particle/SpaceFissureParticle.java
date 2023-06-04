package github.thelawf.gensokyoontology.common.particle;


import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.vector.Vector3d;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class SpaceFissureParticle extends SpriteTexturedParticle {
    private final IAnimatedSprite spriteWithAge;
    protected SpaceFissureParticle(ClientWorld world, double x, double y, double z, Vector3d speed, Color color, float diameter, IAnimatedSprite spriteWithAge) {
        super(world, x, y, z);
        this.spriteWithAge = spriteWithAge;
        this.particleScale = 1.0F - (float)diameter * 0.5F;
    }

    public int getBrightnessForRender(float partialTick) {
        return 15728880;
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.selectSpriteWithAge(this.spriteWithAge);
        }
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_LIT;
    }

    public static class Factory implements IParticleFactory<SpaceFissureParticleData> {
        private final IAnimatedSprite spriteSet;
        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }
        private Factory() {
            throw new UnsupportedOperationException("Use the SpaceFissureParticle.Factory(IAnimatedSprite sprite) constructor");
        }
        @Nullable
        @Override
        public Particle makeParticle(SpaceFissureParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SpaceFissureParticle sfParticle = new SpaceFissureParticle(worldIn,x,y,z,typeIn.getSpeed(),typeIn.getColor(),typeIn.getDiameter(),spriteSet);
            sfParticle.selectSpriteWithAge(spriteSet);
            return sfParticle;
        }
    }
}
