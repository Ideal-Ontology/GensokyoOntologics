package github.thelawf.gensokyoontology.common.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*
public class HotSpringSteamFactory implements IParticleFactory<HotSpringSteamData> {

    private final IAnimatedSprite sprite;

    public HotSpringSteamFactory(IAnimatedSprite sprite) {
        this.sprite = sprite;
    }

    @Nullable
    @Override
    public Particle makeParticle(@Nonnull HotSpringSteamData typeIn, @Nonnull ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        HotSpringSteamParticle particle = new HotSpringSteamParticle(worldIn,x,y,z,typeIn.getSpeed(),
                typeIn.getColor(), typeIn.getDiameter());
        particle.selectSpriteRandomly(sprite);
        return particle;
    }
}

 */
