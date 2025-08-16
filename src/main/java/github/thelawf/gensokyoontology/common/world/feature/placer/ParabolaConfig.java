package github.thelawf.gensokyoontology.common.world.feature.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.gen.feature.IFeatureConfig;
import org.apache.logging.log4j.core.jackson.ContextDataDeserializer;

public class ParabolaConfig implements IFeatureConfig {
    public final int layerLevel;
    public final int parabolaCount;
    public final float speed;
    public final float gravity;
    public final float pitchDeg;
    public final float maxTime;

    public static final Codec<ParabolaConfig> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.fieldOf("layerLevel").forGetter(config -> config.layerLevel),
            Codec.INT.fieldOf("parabolaCount").forGetter(config -> config.parabolaCount),

            Codec.FLOAT.fieldOf("speed").forGetter(config -> config.speed),
            Codec.FLOAT.fieldOf("gravity").forGetter(config -> config.gravity),
            Codec.floatRange(-1F, 1F).fieldOf("pitchDeg").forGetter(config -> config.pitchDeg),
            Codec.floatRange(0, 1F).fieldOf("maxTime").forGetter(config -> config.maxTime)
    ).apply(inst, ParabolaConfig::new));

    public ParabolaConfig(int layerLevel, int parabolaCount, float speed, float gravity, float pitchDeg, float maxTime) {
        this.layerLevel = layerLevel;
        this.parabolaCount = parabolaCount;
        this.speed = speed;
        this.gravity = gravity;
        this.pitchDeg = pitchDeg;
        this.maxTime = maxTime;
    }

    public Vector3d getShootVec(int index){
        return Vector3d.fromPitchYaw(this.pitchDeg, 360F / this.parabolaCount * index);
    }

    public BlockPos getFoliagePos(int index, float deltaTime){
        int foliageX = 0;
        int foliageY = 0;
        int foliageZ = 0;
        for (float i = 0; i < this.maxTime; i += deltaTime) {

        }
        return null;
    }
}
