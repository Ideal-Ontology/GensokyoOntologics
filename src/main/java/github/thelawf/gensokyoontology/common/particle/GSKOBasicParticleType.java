package github.thelawf.gensokyoontology.common.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nonnull;
import java.util.Objects;

public class GSKOBasicParticleType extends ParticleType<GSKOBasicParticleType> implements IParticleData {

    public GSKOBasicParticleType(boolean alwaysShow) {
        super(false, DESERIALIZER);
    }

    @SuppressWarnings("deprecation")
    private static final IDeserializer<GSKOBasicParticleType> DESERIALIZER = new IDeserializer<GSKOBasicParticleType>() {
        public GSKOBasicParticleType deserialize(ParticleType<GSKOBasicParticleType> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            return (GSKOBasicParticleType) particleTypeIn;
        }

        @Override
        public GSKOBasicParticleType read(ParticleType<GSKOBasicParticleType> particleTypeIn, PacketBuffer buffer) {
            return (GSKOBasicParticleType) particleTypeIn;
        }
    };

    private final Codec<GSKOBasicParticleType> codec = Codec.unit(this::getType);

    @Override
    public GSKOBasicParticleType getType() {
        return this;
    }

    @Override
    public void write(@Nonnull PacketBuffer buffer) {
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public String getParameters() {
        return Objects.requireNonNull(Registry.PARTICLE_TYPE.getKey(this)).toString();
    }

    @Override
    public Codec<GSKOBasicParticleType> func_230522_e_() {
        return codec;
    }
}
