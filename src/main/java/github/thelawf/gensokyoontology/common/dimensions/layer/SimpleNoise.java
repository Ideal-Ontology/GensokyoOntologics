package github.thelawf.gensokyoontology.common.dimensions.layer;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Random;

public class SimpleNoise {

    public static int getNoiseHeight(long seed, int globalX, int globalZ, int diff, int loud) {
        Random random = new Random(seed);
        for (int i = 0; i < diff; i++) {
            for (int j = 0; j < diff; j++) {
                int y = random.nextInt(loud);
            }
        }

        return 0;
    }

    public static int getSmoothHeight(BlockPos posIn) {
        return 0;
    }
}
