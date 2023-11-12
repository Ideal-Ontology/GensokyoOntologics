package github.thelawf.gensokyoontology.common.particle;

import com.mojang.serialization.Codec;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nonnull;
import java.awt.*;

public class SpaceFissureParticleType extends ParticleType<SpaceFissureParticleData> {

    private static final boolean ALWAYS_SHOW = true;

    @SuppressWarnings("deprecation")
    public SpaceFissureParticleType() {
        super(ALWAYS_SHOW, SpaceFissureParticleData.DESERIALIZER);
    }

    @Override
    @Nonnull
    public Codec<SpaceFissureParticleData> func_230522_e_() {
        return Codec.unit(new SpaceFissureParticleData(new Vector3d(0, 0, 0),
                new Color(0), 0));
    }
}
