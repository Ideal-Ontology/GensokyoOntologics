package github.thelawf.gensokyoontology.common.particle;


import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;

public class SpaceFissureParticle extends SpriteTexturedParticle {
    private final IAnimatedSprite spriteWithAge;
    protected SpaceFissureParticle(ClientWorld world, double x, double y, double z, double scale, IAnimatedSprite spriteWithAge) {
        super(world, x, y, z);
        this.spriteWithAge = spriteWithAge;
        this.particleScale = 1.0F - (float)scale * 0.5F;
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

    public static class Factory implements IParticleFactory<GSKOBasicParticleType> {
        private final IAnimatedSprite spriteSet;
        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle makeParticle(GSKOBasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SpaceFissureParticle(worldIn,x,y,z,xSpeed,this.spriteSet);
        }
    }
}
