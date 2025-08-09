package github.thelawf.gensokyoontology.client.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector4i;

import java.util.Locale;

public class PowerParticleData implements IParticleData {
    private final Vector3d speed;
    private final Vector4i color;
    public static final IDeserializer<PowerParticleData> DESERIALIZER = new IDeserializer<PowerParticleData>() {

        @Override
        public PowerParticleData deserialize(ParticleType<PowerParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException, CommandSyntaxException {
            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            reader.expect(' ');
            double speedX = reader.readDouble();
            reader.expect(' ');
            double speedY = reader.readDouble();
            reader.expect(' ');
            double speedZ = reader.readDouble();
            reader.expect(' ');
            int red = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            reader.expect(' ');
            int green = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            reader.expect(' ');
            int blue = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            reader.expect(' ');
            int alpha = MathHelper.clamp(reader.readInt(), 1, MAX_COLOUR);
            return new PowerParticleData(new Vector3d(speedX, speedY, speedZ), new Vector4i(red, green, blue, alpha));
        }

        @Override
        public PowerParticleData read(ParticleType<PowerParticleData> particleTypeIn, PacketBuffer buffer) {
            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            double speedX = buffer.readDouble();
            double speedY = buffer.readDouble();
            double speedZ = buffer.readDouble();
            int red = MathHelper.clamp(buffer.readInt(), MIN_COLOUR, MAX_COLOUR);
            int green = MathHelper.clamp(buffer.readInt(), MIN_COLOUR, MAX_COLOUR);
            int blue = MathHelper.clamp(buffer.readInt(), MIN_COLOUR, MAX_COLOUR);
            int alpha = MathHelper.clamp(buffer.readInt(), 1, MAX_COLOUR);
            return new PowerParticleData(new Vector3d(speedX, speedY, speedZ), new Vector4i(red, green, blue, alpha));
        }
    };

    public PowerParticleData(Vector3d speed, Vector4i color) {
        this.speed = speed;
        this.color = color;
    }

    @Override
    public @NotNull ParticleType<?> getType() {
        return GSKOParticleRegistry.POWER_PARTICLE.get();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeDouble(this.speed.x);
        buffer.writeDouble(this.speed.y);
        buffer.writeDouble(this.speed.z);
        buffer.writeInt(this.color.x);
        buffer.writeInt(this.color.y);
        buffer.writeInt(this.color.z);
        buffer.writeInt(this.color.w);
    }

    @Override
    public @NotNull String getParameters() {
        return String.format(Locale.ROOT, "%s %i %i %i %i %.2d %.2d %.2d",
                this.getType().getRegistryName(), color.x, color.y, color.z, color.w, speed.getX(), speed.getY(), speed.getZ());
    }

    public Vector3d getSpeed() {
        return this.speed;
    }

    public Vector4i getColor() {
        return this.color;
    }
}
