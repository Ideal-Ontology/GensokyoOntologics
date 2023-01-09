package github.thelawf.gensokyoontology.common.dimensions.layer;

import com.google.common.base.MoreObjects;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class SimpleRandomGenerator {
    ArrayList<Integer> location = new ArrayList<>();
    ArrayList<ArrayList<Integer>> locations = new ArrayList<>();
    BlockPos.Mutable blockPos;
    // 世界边界大小
    int worldSize;
    final long seed;
    Random random = new Random();
    // 权重
    short weight;

    public SimpleRandomGenerator(int worldSize, long seed) {
        this.worldSize = worldSize;
        this.seed = seed;
        this.random.setSeed(seed);

        for (int i = 0; i < 256; i++) {
            if (this.random.nextBoolean()) {
                location.add(0);
            }
            else {
                location.add(1);
            }
            locations.add(location);
        }

    }

    public SimpleRandomGenerator(long seed) {
        this.seed = seed;
        this.random.setSeed(seed);

        for (int i = 0; i < 256; i++) {
            if (this.random.nextBoolean()) {
                location.add(0);
            }
            else {
                location.add(1);
            }
            locations.add(location);
        }
    }

    /**
     * 如果某个位置的值是1，且周围的八个位置中有四个位置的值等于0，则该位置的值变为0，
     * 如果某个位置的值是0，且周围的八个位置中有五个位置的值等于1，则该位置变为1
     * @param indexX
     * @param indexY
     * @param posIn
     * @return
     */
    public boolean getNeighbors(ArrayList<ArrayList<Integer>> posIn, int indexX, int indexY) {
        ArrayList<Integer> neighbors = new ArrayList<>();

        neighbors.add(posIn.get(indexX-1).get(indexY));
        neighbors.add(posIn.get(indexX).get(indexY-1));
        neighbors.add(posIn.get(indexX+1).get(indexY));
        neighbors.add(posIn.get(indexX).get(indexY+1));

        neighbors.add(posIn.get(indexX-1).get(indexY-1));
        neighbors.add(posIn.get(indexX+1).get(indexY+1));
        neighbors.add(posIn.get(indexX-1).get(indexY+1));
        neighbors.add(posIn.get(indexX+1).get(indexY-1));

        int count = 0;

        if (posIn.get(indexX).get(indexY) == 0) {
            for (int value : neighbors) {
                if (value == 0) {
                    count++;
                }
            }
            if (count == 4) {
                posIn.get(indexX).set(indexY, 0);
            }
        }
        else if (posIn.get(indexX).get(indexY) == 1) {
            for (int value : neighbors) {
                if (value == 1) {
                    count++;
                }
            }
            if (count == 5) {
                posIn.get(indexX).set(indexY, 0);
            }
        }

        return count == 4;
    }


    public static void setBlockPosWeight(BlockPos posIn) {

    }
}
