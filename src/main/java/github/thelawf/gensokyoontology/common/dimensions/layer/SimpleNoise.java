package github.thelawf.gensokyoontology.common.dimensions.layer;

import github.thelawf.gensokyoontology.common.libs.logoslib.math.MathUtil;

import java.util.Random;

public class SimpleNoise {

    public static int getNoiseHeight(long seed, int globalX, int globalZ, int diff, int loud) {
        Random random = new Random(seed);
        int y = 0;
        if (globalX % diff == 0 && globalZ % diff == 0) {
            for (int i = 0; i < diff; i++) {
                for (int j = 0; j < diff; j++) {
                    int height = random.nextInt(loud);
                    y = getSmoothY(i, j, height, diff);
                }
            }
        }

        return y;
    }

    public static int getSmoothY(int x, int z, int height, int diff) {
        return (int) Math.sqrt(
                MathUtil.pow2((double) x / (x + diff)) +
                MathUtil.pow2((double)z / (z + diff))) * height;
    }
}
