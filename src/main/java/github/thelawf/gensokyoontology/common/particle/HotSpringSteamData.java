package github.thelawf.gensokyoontology.common.particle;

/*
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Locale;

public class HotSpringSteamData implements IParticleData {

    private final Vector3d speed;
    private final Color color;
    private final float diameter;

    public static final HotSpringSteamData HOT_SPRING_STEAM =
            new HotSpringSteamData(new Vector3d(0.1D,.0D,0.1F),
                    Color.WHITE,0.8F);

    public HotSpringSteamData(Vector3d speed, Color color, float diameter) {
        this.speed = speed;
        this.color = color;
        this.diameter = diameter;
    }


    @SuppressWarnings("deprecation")
    public static final IDeserializer<HotSpringSteamData> DESERIALIZER = new IDeserializer<HotSpringSteamData>() {
        @Override
        @Nonnull
        public HotSpringSteamData deserialize(@Nonnull ParticleType<HotSpringSteamData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
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
            reader.expect(' ');
            float diameter = reader.readFloat();
            return new HotSpringSteamData(new Vector3d(speedX, speedY, speedZ), new Color(red, green, blue, alpha), diameter);

        }

        @Override
        @Nonnull
        public HotSpringSteamData read(@Nonnull ParticleType<HotSpringSteamData> particleTypeIn, PacketBuffer buffer) {
            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            double speedX = buffer.readDouble();
            double speedY = buffer.readDouble();
            double speedZ = buffer.readDouble();
            int red = MathHelper.clamp(buffer.readInt(), MIN_COLOUR, MAX_COLOUR);
            int green = MathHelper.clamp(buffer.readInt(), MIN_COLOUR, MAX_COLOUR);
            int blue = MathHelper.clamp(buffer.readInt(), MIN_COLOUR, MAX_COLOUR);
            int alpha = MathHelper.clamp(buffer.readInt(), 1, MAX_COLOUR);
            float diameter = buffer.readFloat();
            return new HotSpringSteamData(new Vector3d(speedX, speedY, speedZ), new Color(red, green, blue, alpha), diameter);
        }
    };


    @Override
    public HotSpringSteamData getType() {
        return this;
    }

    @Override
    public ParticleType<?> getType() {
        return null;
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeDouble(this.speed.x);
        buffer.writeDouble(this.speed.y);
        buffer.writeDouble(this.speed.z);
        buffer.writeInt(this.color.getRed());
        buffer.writeInt(this.color.getGreen());
        buffer.writeInt(this.color.getBlue());
        buffer.writeInt(this.color.getAlpha());
        buffer.writeFloat(this.diameter);
    }

    @Override
    @Nonnull
    public String getParameters() {
        return String.format(Locale.ROOT, "%s %.2f %d %d %d %d %.2g %.2g %.2g",
                this.getType().getRegistryName(), diameter, color.getRed(), color.getGreen(),
                color.getBlue(), color.getAlpha(), speed.getX(), speed.getY(), speed.getZ());
    }

    public Vector3d getSpeed(){
        return speed;
    }

    public Color getColor() {
        return color;
    }

    public float getDiameter() {
        return diameter;
    }

}
*/