package github.thelawf.gensokyoontology.common.util.world;


import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.math.BezierUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConnectionUtil {
    public static final Logger LOGGER = LogManager.getLogger();

    public static boolean tryConnect(BlockPos startPos, BlockPos endPos) {
        if (startPos.getY() == endPos.getY()) {
            return true;
        } else if (GSKOMathUtil.distanceOf3D(
                startPos.getX(), startPos.getY(), startPos.getZ(),
                endPos.getX(), endPos.getY(), endPos.getZ()) == 0) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * 获取组成贝塞尔曲线的离散直线部分的数量
     *
     * @param startPosIn 第一次点击的方块位置
     * @param endPosIn   第二次点击的方块位置
     * @return 组成曲线的直线部分的数量
     */
    public static int getSegCount(BlockPos startPosIn, BlockPos endPosIn) {
        if (startPosIn == null || endPosIn == null) {
            LOGGER.warn("Invalid Block Positions: {} , {}", startPosIn, endPosIn);
            return 0;
        } else {
            return (int) (GSKOMathUtil.distanceOf3D(
                    startPosIn.getX(), startPosIn.getY(), startPosIn.getZ(),
                    endPosIn.getX(), endPosIn.getY(), endPosIn.getZ()) / 3);
        }
    }

    // TODO: 获取轨道的平滑旋转插值
    public static List<Vector3f> getSegRotations(Vector3d start, Vector3d end, float time, List<Vector3f> rotations) {
        for (float i = 0; i < 1; i += time) {
            if (rotations.size() > 1F / time) break;
            Vector3d v = GSKOMathUtil.lerp(time, start, end);
            rotations.add(new Vector3f((float) v.x, (float) v.y, (float) v.z));
        }
        return rotations;
    }

    public static Vector3d getCtrlDotFromRotation(Vector3d startPos, Vector2f startRotation, Vector2f endRotation){
        Vector3d startDir = Vector3d.fromPitchYaw(startRotation);
        Vector3d endDir = Vector3d.fromPitchYaw(endRotation);
        Vector3d intersection = getIntersection(startDir, endDir);
        return intersection.add(startPos);
    }

    public static Vector3d getIntersection(BlockPos start, Vector3d startFacing, BlockPos end, Vector3d endFacing) {
        return GSKOMathUtil.getIntersectionFromRot(Vector3d.copyCentered(start), startFacing, Vector3d.copyCentered(end), endFacing);
    }

    public static Vector3d getIntersection(Vector3d startFacing, Vector3d endFacing) {
        return GSKOMathUtil.getIntersection(startFacing, startFacing.scale(2), endFacing, endFacing.scale(2));
    }


    /**
     * 获取直线的位置以及旋转
     * 用法：在外部迭代调用该方法，迭代传入起始点和终点，迭代次数为上文segment的个数。
     *
     * @return 方块位置(x, y, z)和三维向量(roll, yaw, pitch)的一对儿
     */
    public static Pair<Vector3d, Vector3d> getPosAndRot(Vector3d startPos, Vector3d intersection, Vector3d endPos, float time) {
        Vector3d segPos = GSKOMathUtil.bezier2(startPos, intersection, endPos, time);
        return new Pair<>(segPos, new Vector3d(1, 1, 1));
    }

    /**
     *
     * @return 返回一个以 key 为起点，value 为终点的 HashMap。
     */
    public static List<Pair<Vector3d, Vector3d>> toConnectionVec(List<Vector3d> bezierPositions) {
        List<Pair<Vector3d, Vector3d>> pairs = new ArrayList<>();
        Vector3d start = null, end = null;
        for (int i = 0; i < bezierPositions.size(); i++) {
            if (i % 2 == 0 && start == null) {
                start = bezierPositions.get(i);
            }
            else end = bezierPositions.get(i);
            if (start != null && end != null) {
                pairs.add(Pair.of(start, end));
                start = end;
                end = null;
            }
        }
        return pairs;
    }

    public static List<Pair<Vector3d, Vector3f>> toRotationMap(List<Vector3d> bezierPositions, List<Vector3f> rotations) {
        List<Pair<Vector3d, Vector3f>> list = new ArrayList<>();
        bezierPositions.forEach(vector3d -> list.add(Pair.of(vector3d, rotations.get(
                bezierPositions.indexOf(vector3d)))));
        return list;
    }

    public static void connectRail(Vector3d startPos, Vector3d intersection, Vector3d endPos, float time) {
        Pair<Vector3d, Vector3d> pair = getPosAndRot(startPos, intersection, endPos, time);
    }
}
