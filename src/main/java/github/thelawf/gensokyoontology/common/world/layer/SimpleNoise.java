package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Random;

@Deprecated
public class SimpleNoise {

    public static int getNoiseHeight(long seed, BlockPos blockPos, int diff, int loud) {
        Random random = new Random(seed);
        Vector2f pos00;
        Vector2f pos01;
        Vector2f pos10;

        // 判断 pos00, pos01, pos10 在哪个区块
        if (blockPos.getX() % 16 == 0 && blockPos.getZ() % 16 == 0) {
            pos00 = new Vector2f(blockPos.getX(), blockPos.getZ());
            pos01 = new Vector2f(blockPos.getX(), blockPos.getZ() + 16);
            pos10 = new Vector2f(blockPos.getX() + 16, blockPos.getZ());
        } else if (blockPos.getX() % 16 == 0) {
            int z = blockPos.getZ() % 16;
            pos00 = new Vector2f(blockPos.getX(), blockPos.getZ() - z);
            pos01 = new Vector2f(blockPos.getX(), blockPos.getZ() + 16 - z);
            pos10 = new Vector2f(blockPos.getX() + 16, blockPos.getZ() - z);
        } else if (blockPos.getZ() % 16 == 0) {
            int x = blockPos.getX() % 16;
            pos00 = new Vector2f(blockPos.getX() - x, blockPos.getZ());
            pos01 = new Vector2f(blockPos.getX() - x, blockPos.getZ() + 16);
            pos10 = new Vector2f(blockPos.getX() + 16 - x, blockPos.getZ());
        } else {
            int x = blockPos.getX() % 16;
            int z = blockPos.getZ() % 16;
            pos00 = new Vector2f(blockPos.getX() - x, blockPos.getZ() - z);
            pos01 = new Vector2f(blockPos.getX() - x, blockPos.getZ() + 16 - z);
            pos10 = new Vector2f(blockPos.getX() + 16 - x, blockPos.getZ() - z);
        }

        // 先判断是否是区块角点（即变量pos00, pos01, pos10, pos11），
        // 再计算角点的方块位置，其高度值被随机数确定，并将其作为该区块的全局变量
        // 然后将下列四个方块坐标传入getSmoothY()方法。
        // 表示区块内所有坐标的高度值由这四个点的y坐标高度决定
        return getSmoothY(new Vector3d(pos00.x, random.nextInt(loud), pos00.y),
                new Vector3d(pos01.x, random.nextInt(loud), pos01.y),
                new Vector3d(pos10.x, random.nextInt(loud), pos10.y),
                blockPos, diff);
    }

    /**
     * 这个方法的思路如下：<br>
     * 1. 求得区块的边界长宽，再将这个长宽乘以地形延展广度（即参数diff），以此决定某个地形会被水平延展多少
     * 倍率;<br>
     * 2. 在该方法体外用for循环在16×16的范围内传入区块坐标（即参数chunkPos）求出该坐标在
     * 本地变量length和width上所占的百分比;<br>
     * 3. 获取pos01和pos10这两个点的高度值（这两个点的高度值通过随机数获得），并求出它们与
     * pos00这个坐标的高度的绝对平均值;<br>
     * 4. 最后将两个绝对平均值与其对应的比率相乘，并将其平均值返回为整形的高度值。
     *
     * @param pos00 区块左下角的全局坐标
     * @param pos01 区块右下角的全局坐标
     * @param pos10 区块左上角的全局坐标
     * @param chunkPos 区块内的方块坐标点
     * @param diff 地形延展广度，用以控制该地形有多大
     * @return 根据区块角点的高度插值得来的区块内各点的高度值
     */
    public static int getSmoothY(Vector3d pos00, Vector3d pos01, Vector3d pos10,
                                 BlockPos chunkPos, int diff) {
        int length = (int) (pos01.x - pos00.x) + diff;
        int width = (int) (pos10.z - pos00.z) + diff;
        float rateL = (float) Math.abs(chunkPos.getX() - pos00.x) / length;
        float rateW = (float) Math.abs(chunkPos.getZ() - pos00.z) / width;

        double heightOnLength = (pos01.x + pos00.x) / 2;
        double heightOnWidth = (pos10.z + pos00.z) / 2;
        double height = (rateL * heightOnLength + rateW * heightOnWidth) / 2;
        return (int) height;
    }

}
