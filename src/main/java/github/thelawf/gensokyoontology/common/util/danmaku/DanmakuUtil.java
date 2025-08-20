package github.thelawf.gensokyoontology.common.util.danmaku;

import com.google.common.collect.Lists;
import github.thelawf.gensokyoontology.common.entity.Danmaku;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.item.DanmakuItem;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.Rot2f;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.command.impl.WeatherCommand;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DanmakuUtil {
    public static <D extends AbstractDanmakuEntity> void shootDanmaku(@NotNull World worldIn, PlayerEntity playerIn,
                                                                      D danmakuEntityType, float velocity, float inaccuracy) {
        Vector3d lookVec = playerIn.getLookVec();

        danmakuEntityType.setNoGravity(true);
        danmakuEntityType.setLocationAndAngles(playerIn.getPosX(), playerIn.getPosY() + playerIn.getEyeHeight(), playerIn.getPosZ(),
                (float) lookVec.y, (float) lookVec.z);
        danmakuEntityType.shoot(lookVec.x, lookVec.y, lookVec.z, velocity, inaccuracy);
        worldIn.addEntity(danmakuEntityType);
    }

    public static void createAndShoot(@NotNull World worldIn, LivingEntity living, DanmakuItem danmakuItem,
                                      float velocity, float inaccuracy) {
        Vector3d lookVec = living.getLookVec();
        Danmaku danmaku = Danmaku.create(worldIn, living, danmakuItem);
        danmaku.setNoGravity(true);
        danmaku.setLocationAndAngles(living.getPosX(), living.getPosY() + living.getEyeHeight(), living.getPosZ(),
                (float) lookVec.y, (float) lookVec.z);
        danmaku.shoot(lookVec.x, lookVec.y, lookVec.z, velocity, inaccuracy);
    }

    public static <D extends AbstractDanmakuEntity> void initDanmaku(D danmaku, Vector3d globalPos, Vector2f rotation, boolean noGravity) {
        danmaku.setNoGravity(noGravity);
        danmaku.setLocationAndAngles(globalPos.getX(), globalPos.getY(), globalPos.getZ(),
                rotation.x, rotation.y);
    }
    public static <D extends AbstractDanmakuEntity> void initDanmaku(D danmaku, Vector3d globalPos, boolean noGravity) {
        danmaku.setNoGravity(noGravity);
        danmaku.setLocationAndAngles(globalPos.getX(), globalPos.getY(), globalPos.getZ(),
                Vector2f.ZERO.x, Vector2f.ZERO.y);
    }

    public static void init(Danmaku danmaku, Vector3d globalPos, Rot2f rotation, boolean noGravity) {
        danmaku.disableGravity().pos(globalPos).rot(rotation);
    }

    public static void applyOperation(ArrayList<VectorOperations> operations, TransformFunction function, Vector3d prevVec) {
        // operations.forEachAct(operation -> getTransform(operation, function, prevVec));
    }

    public static Vector3d getTransform(VectorOperations operation, TransformFunction function, Vector3d prevVec) {
        if (function.scaling > 0F) {
            if (operation == VectorOperations.ROTATE_YAW) {
                return prevVec.rotateYaw(function.scaling);
            } else if (operation == VectorOperations.ROTATE_ROLL) {
                return prevVec.rotateRoll(function.scaling);
            } else if (operation == VectorOperations.ROTATE_PITCH) {
                return prevVec.rotatePitch(function.scaling);
            } else if (operation == VectorOperations.VECTOR_SCALE) {
                return prevVec.scale(function.scaling);
            }
        } else if (function.acceleration != null) {
            if (operation == VectorOperations.VECTOR_ADD) {
                return prevVec.add(function.acceleration);
            } else if (operation == VectorOperations.VECTOR_SUBTRACT) {
                return prevVec.subtract(function.acceleration);
            }
        } else if (operation == VectorOperations.ARCHIMEDE_SPIRAL) {
            return getArchimedeSpiral(prevVec, 1.0f, Math.PI);
        }

        return prevVec;
    }

    public static Vector3d getArchimedeSpiral(Vector3d prevVec, double radius, double angle) {
        return new Vector3d(prevVec.x * radius * Math.cos(angle),
                prevVec.y, prevVec.z * radius * Math.signum(angle));
    }

    public static List<Item> getAllDanmakuItem() {
        List<Item> danmakuItems = new ArrayList<>();
        danmakuItems.add(ItemRegistry.LARGE_SHOT_RED.get());
        danmakuItems.add(ItemRegistry.LARGE_SHOT_PURPLE.get());
        danmakuItems.add(ItemRegistry.LARGE_SHOT_BLUE.get());
        danmakuItems.add(ItemRegistry.LARGE_SHOT_GREEN.get());
        danmakuItems.add(ItemRegistry.LARGE_SHOT_YELLOW.get());

        danmakuItems.add(ItemRegistry.SMALL_SHOT_RED.get());
        danmakuItems.add(ItemRegistry.SMALL_SHOT_BLUE.get());
        danmakuItems.add(ItemRegistry.SMALL_SHOT_GREEN.get());
        danmakuItems.add(ItemRegistry.SMALL_SHOT_PURPLE.get());
        danmakuItems.add(ItemRegistry.SMALL_SHOT_YELLOW.get());

        danmakuItems.add(ItemRegistry.HEART_SHOT_RED.get());
        danmakuItems.add(ItemRegistry.HEART_SHOT_PINK.get());
        danmakuItems.add(ItemRegistry.HEART_SHOT_AQUA.get());

        return danmakuItems;
    }

    public static Vector3d rotateRandomAngle(Vector3d preVec, float yawBounds, float pitchBounds) {
        Vector3d nextVec = preVec.rotateYaw(GSKOMathUtil.randomRange(0f, yawBounds));
        nextVec = nextVec.rotatePitch(GSKOMathUtil.randomRange(0f, pitchBounds));
        return nextVec;
    }

    public static Vector3d getRandomPos(Vector3d center, Vector3f radius) {
        double x = GSKOMathUtil.randomRange(-radius.getX(), radius.getX());
        double y = GSKOMathUtil.randomRange(-radius.getY(), radius.getY());
        double z = GSKOMathUtil.randomRange(-radius.getZ(), radius.getZ());

        return new Vector3d(center.x + x, center.y + y, center.z + z);
    }

    public static Vector3d getRandomPosWithin(float radius, Plane planeIn) {
        return getRandomPosWithin(new Vector3f(radius, radius, radius), planeIn);
    }

    public static Vector3d getRandomPosWithin(Vector3f radius, Plane planeIn) {
        double x = GSKOMathUtil.randomRange(-radius.getX(), radius.getX());
        double y = GSKOMathUtil.randomRange(-radius.getY(), radius.getY());
        double z = GSKOMathUtil.randomRange(-radius.getZ(), radius.getZ());

        Vector3d vector3d = Vector3d.ZERO;

        switch (planeIn) {
            case XY:
                vector3d = new Vector3d(x, y, 0);
                break;
            case XZ:
                vector3d = new Vector3d(x, 0, z);
                break;
            case YZ:
                vector3d = new Vector3d(0, y, z);
                break;
            case XYZ:
                vector3d = new Vector3d(x, y, z);
                break;
        }
        return vector3d;
    }

    public static Vector3d getAimingAt(LivingEntity thrower, LivingEntity target) {
        float offset = (float) (0.3f / target.getYOffset());
        return new Vector3d(target.getPosX() - thrower.getPosX(), target.getPosY() - thrower.getPosY() - offset, target.getPosZ() - thrower.getPosZ())
                .normalize();
    }

    public static List<Vector3d> oddCurveVec(LivingEntity shooter, LivingEntity target, float count, int angleDeg) {
        List<Vector3d> vectors = new ArrayList<>();
        vectors.add(getAimedVec(shooter, target));
        for (int i = 1; i < (count - 1) / 2 + 1; i++) {
            Vector3d rightVec = getAimingAt(shooter, target).rotateYaw(Danmaku.rad(angleDeg) * i);
            Vector3d leftVec = getAimingAt(shooter, target).rotateYaw(-Danmaku.rad(angleDeg) * i);
            vectors.add(leftVec);
            vectors.add(rightVec);
        }

        return vectors;
    }


    public static <D extends AbstractDanmakuEntity> void shootWithRoseLine(D danmaku, Plane planeIn, Vector3d offsetRotation,
                                                                           double radius, double count, double size, int density) {
        // List<Vector3d> roseLinePos = getRoseLinePos(radius, count, size, density);
        // List<Vector2f> shootVectors = new ArrayList<>();
        // roseLinePos.forEachAct(vector3d -> shootVectors.add(GSKOMathUtil.getEulerAngle(new Vector3d(Vector3f.ZP), vector3d)));
    }

    /**
     * 按照玫瑰线来初始化弹幕的位置和旋转
     *
     * @param count 玫瑰线花瓣/叶片的数量
     * @param size  玫瑰线花瓣的大小
     * @param delta 决定着玫瑰线上的弹幕之间的间隔
     */
    public static List<Vector3d> getRoseLinePos(double radius, double count, double size, double delta) {
        double x, y;
        List<Vector3d> positions = new ArrayList<>();

        count = count / size;

        for (double i = 0; i < 4 * Math.PI; i += delta) {

            double r = Math.sin(count * i);
            x = r * Math.cos(i) * radius;
            y = r * Math.sin(i) * radius;

            positions.add(new Vector3d((float) x, (float) y, 0));

        }
        return positions;
    }

    public static List<Vector3d> getHeartLinePos(float radius, double delta) {
        double t = 0;
        double maxT = 2 * Math.PI;
        List<Vector3d> positions = new ArrayList<>();

        for (int i = 0; i < Math.ceil(maxT / delta); i++) {
            float x = (float) (16 * GSKOMathUtil.pow3(Math.sin(t)));
            float y = (float) (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t));
            t += delta;
            positions.add(new Vector3d(x * radius, y * radius, 0));
        }
        return positions;
    }

    public static List<Vector3d> getStarLinePos(float radius, double t, Plane planeIn) {
        List<Vector3d> positions = new ArrayList<>();

        for (int i = 0; i < Math.PI * 2; i += t) {
            double x = radius * GSKOMathUtil.pow3(Math.cos(t));
            double y = radius * GSKOMathUtil.pow3(Math.sin(t));

            switch (planeIn) {
                case XY:
                    positions.add(new Vector3d(x, y, 0));
                    break;
                default:
                case XZ:
                    positions.add(new Vector3d(x, 0, y));
                    break;
                case YZ:
                    positions.add(new Vector3d(0, y, x));
                    break;
            }

        }
        return positions;
    }

    /**
     * 这里的旋转角度是弧度制
     *
     * @param prevPositions 之前的弹幕图案的坐标列表
     * @param yaw           对每一个坐标执行的 yaw 旋转角度
     * @param pitch         对每一个坐标执行的 pitch 旋转角度
     */
    public static List<Vector3d> getRotatedPos(List<Vector3d> prevPositions, float yaw, float pitch) {
        List<Vector3d> newPos = new ArrayList<>();
        for (Vector3d prevPos : prevPositions) {
            newPos.add(prevPos.rotatePitch(pitch).rotateYaw(yaw));
        }
        return newPos;
    }

    public static List<Vector3d> getParaboloidPos(Vector2f range, double a, double b, double delta) {
        List<Vector3d> positions = new ArrayList<>();
        for (int i = 0; i < range.x; i += delta) {
            for (int j = 0; j < range.y; j += delta) {
                double z = GSKOMathUtil.pow2(i) / GSKOMathUtil.pow2(a) -
                        GSKOMathUtil.pow2(j) / GSKOMathUtil.pow2(b);
                positions.add(new Vector3d(i, j, z));
            }
        }
        return positions;
    }

    public static List<Vector3d> getEllipticParaboloidPos(Vector2f start, Vector2f end, double a, double b, double delta) {
        List<Vector3d> positions = new ArrayList<>();
        return positions;
    }

    public static List<Vector3d> spheroidPos(double radius, int count) {
        List<Vector3d> coordinates = new ArrayList<>();
        List<Vector3d> pos1 = ellipticPos(new Vector2f(0,0), radius, count);
        for (int i = 0; i < pos1.size(); i++) {
            for (int j = 0; j < pos1.size(); j++) {
                Vector3d vector3d = pos1.get(j).rotatePitch((float) Math.PI * 2 / pos1.size() * j);
                pos1.set(j, vector3d);
            }
            coordinates.addAll(pos1);
        }
        return coordinates;
    }

    public static List<Vector3d> ellipticPos(Vector2f center, double radius, int count) {
        ArrayList<Vector3d> coordinates = new ArrayList<>();
        // 定义生成坐标的数量
        // 计算每个点的角度间隔
        double angleInterval = 2 * Math.PI / count;
        // 生成坐标
        for (int i = 0; i < count; i++) {
            double angle = i * angleInterval;
            double x = center.x + radius * Math.cos(angle);
            double y = center.y + radius * Math.sin(angle);
            coordinates.add(new Vector3d(x, 0, y));
        }
        return coordinates;
    }

    public static Vector3d getAimedVec(LivingEntity shooter, LivingEntity target) {
        return target.getPositionVec().subtract(shooter.getPositionVec()).scale(-1);
        // return new Vector3d(target.getPosX() - shooter.getPosX(), target.getPosY() - shooter.getPosY(), target.getPosZ() - shooter.getPosZ());
    }

    public static void curveShoot(Danmaku danmaku, Vector3d aimedVec, float angleDeg, int index, float speed){
        if (index % 2 == 0){
            danmaku.shoot(aimedVec.rotateYaw(Danmaku.rad(angleDeg) / 2), speed);
            danmaku.shoot(aimedVec.rotateYaw(-Danmaku.rad(angleDeg) / 2), speed);

            int i = (index - 2) / 2;
            Vector3d rightVec = aimedVec.rotateYaw(Danmaku.rad(angleDeg) * i + (angleDeg / 2));
            Vector3d leftVec = aimedVec.rotateYaw(-Danmaku.rad(angleDeg) * i - (angleDeg / 2));
            danmaku.shoot(rightVec, speed);
            danmaku.shoot(leftVec, speed);
            danmaku.world.addEntity(danmaku);
        }
        else {
            danmaku.shoot(aimedVec, speed);
            int i = (index - 1) / 2;
            Vector3d rightVec = aimedVec.rotateYaw(Danmaku.rad(angleDeg) * i);
            Vector3d leftVec = aimedVec.rotateYaw(-Danmaku.rad(angleDeg) * i);

            danmaku.shoot(rightVec, speed);
            danmaku.shoot(leftVec, speed);
            danmaku.world.addEntity(danmaku);

        }
    }

    public static void wallShoot(Danmaku danmaku, Vector3d aimedVec, float angleDeg, int widthIndex, int heightIndex, float speed){
        if (heightIndex % 2 == 0){
            int i = (heightIndex - 2) / 2;
            curveShoot(danmaku, aimedVec.rotatePitch(angleDeg / 2), angleDeg, widthIndex, speed);
            curveShoot(danmaku, aimedVec.rotatePitch(-angleDeg / 2), angleDeg, widthIndex, speed);
            applyLR(danmaku, aimedVec, angleDeg, widthIndex, speed, i);
        }
        else {
            int i = (heightIndex - 1) / 2;
            curveShoot(danmaku, aimedVec, angleDeg, widthIndex, speed);
            applyLR(danmaku, aimedVec, angleDeg, widthIndex, speed, i);
        }
    }

    private static void applyLR(Danmaku danmaku, Vector3d aimedVec, float angleDeg, int widthIndex, float speed, int i) {
        Vector3d rightVec = aimedVec.rotatePitch(Danmaku.rad(angleDeg) * i + (angleDeg / 2));
        Vector3d leftVec = aimedVec.rotatePitch(-Danmaku.rad(angleDeg) * i - (angleDeg / 2));
        curveShoot(danmaku, rightVec,angleDeg, widthIndex, speed);
        curveShoot(danmaku, leftVec, angleDeg, widthIndex, speed);
    }


    public static List<Item> getRainbowColoredDanmaku() {

        return Lists.newArrayList(
                ItemRegistry.SMALL_SHOT_RED.get(),
                ItemRegistry.SMALL_SHOT_ORANGE.get(),
                ItemRegistry.SMALL_SHOT_YELLOW.get(),
                ItemRegistry.SMALL_SHOT_GREEN.get(),
                ItemRegistry.SMALL_SHOT_AQUA.get(),
                ItemRegistry.SMALL_SHOT_BLUE.get(),
                ItemRegistry.SMALL_SHOT_PURPLE.get(),
                ItemRegistry.SMALL_SHOT_MAGENTA.get());
    }


    public enum Plane {
        XZ,
        XY,
        YZ,
        XYZ;
    }

}
