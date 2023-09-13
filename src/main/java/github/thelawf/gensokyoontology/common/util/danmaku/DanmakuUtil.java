package github.thelawf.gensokyoontology.common.util.danmaku;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.util.logos.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.SpellCardRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.util.IntIdentityHashBiMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DanmakuUtil {

    public static final IntIdentityHashBiMap<IDataSerializer<SpellData>> REGISTRY = new IntIdentityHashBiMap<>(16);

    public static final IDataSerializer<SpellData> SPELL_DATA = new IDataSerializer<SpellData>() {
        @Override
        public void write(PacketBuffer buf, @NotNull SpellData value) {
            buf.writeString(SpellCardRegistry.IDO_NO_KAIHO_DATA.getId().toString());
        }

        @Override
        public SpellData read(PacketBuffer buf) {
            return SpellCardRegistry.SPELL_CARD_REGISTRY.getValue(new ResourceLocation(
                    GensokyoOntology.MODID, buf.readString()));
        }

        @Override
        @NotNull
        public SpellData copyValue(@NotNull SpellData value) {
            return value;
        }

    };

    public static void registerSerializer(IDataSerializer<SpellData> serializer) {
        if (REGISTRY.add(serializer) >= 256) throw new RuntimeException("Vanilla DataSerializer ID limit exceeded");
    }

    static {
        registerSerializer(SPELL_DATA);
    }

    public static <D extends AbstractDanmakuEntity> void shootDanmaku(@NotNull World worldIn, PlayerEntity playerIn,
                                                                      D danmakuEntityType, float velocity, float inaccuracy) {
        Vector3d lookVec = playerIn.getLookVec();

        danmakuEntityType.setNoGravity(true);
        danmakuEntityType.setLocationAndAngles(playerIn.getPosX(), playerIn.getPosY() + playerIn.getEyeHeight(), playerIn.getPosZ(),
                (float) lookVec.y, (float) lookVec.z);
        danmakuEntityType.shoot(lookVec.x, lookVec.y, lookVec.z, velocity, inaccuracy);
        worldIn.addEntity(danmakuEntityType);

    }

    public static <D extends AbstractDanmakuEntity> void shootDanmaku(@NotNull World worldIn, Entity entityIn,
                                                                      D danmakuEntityType, Vector3d shootVec,
                                                                      float velocity, float inaccuracy) {
        Vector3d lookVec = entityIn.getLookVec();

        danmakuEntityType.setNoGravity(true);
        danmakuEntityType.setLocationAndAngles(entityIn.getPosX(), entityIn.getPosY() + entityIn.getEyeHeight(), entityIn.getPosZ(),
                (float) lookVec.y, (float) lookVec.z);
        danmakuEntityType.shoot(shootVec.x, shootVec.y, shootVec.z, velocity, inaccuracy);
        worldIn.addEntity(danmakuEntityType);

    }

    public static <D extends AbstractDanmakuEntity> void initDanmaku(D danmaku, Vector3d globalPos, Vector2f rotation, boolean noGravity) {
        danmaku.setNoGravity(noGravity);
        danmaku.setLocationAndAngles(globalPos.getX(), globalPos.getY(), globalPos.getZ(),
                rotation.x, rotation.y);
    }


    public static void applyOperation(ArrayList<VectorOperations> operations, TransformFunction function, Vector3d prevVec) {
        // operations.forEach(operation -> getTransform(operation, function, prevVec));
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

    public static <D extends AbstractDanmakuEntity> void shootSpherical(Class<D> danmaku, double radius,
                                                                        int latitudeCount, int longitudeCount,
                                                                        int i, int j) {

        Vector3d vector3d = new Vector3d(Vector3f.ZP).scale(radius);
        vector3d = vector3d.rotatePitch((float) Math.PI / latitudeCount * i);
        vector3d = vector3d.rotateYaw((float) Math.PI / longitudeCount * j);

        // initDanmaku(data);
        // danmaku.shoot(vector3d.getX(), vector3d.getY(), vector3d.getZ(), data.speed, 0f);
        // data.world.addEntity(danmaku);

    }

    public static void shootSpiral() {

    }

    public static void shootRadial() {

    }

    public static Vector3d getAimingShootVec(LivingEntity thrower, LivingEntity target) {
        float offset = (float) (0.3f / target.getYOffset());
        return new Vector3d(target.getPosX() - thrower.getPosX(), target.getPosY() - thrower.getPosY() - offset, target.getPosZ() - thrower.getPosZ());
    }

    public static <D extends AbstractDanmakuEntity> void shootWithRoseLine(D danmaku, Plane planeIn, Vector3d offsetRotation,
                                                                           double radius, double count, double size, int density) {
        // List<Vector3d> roseLinePos = getRoseLinePos(radius, count, size, density);
        // List<Vector2f> shootVectors = new ArrayList<>();
        // roseLinePos.forEach(vector3d -> shootVectors.add(GSKOMathUtil.getEulerAngle(new Vector3d(Vector3f.ZP), vector3d)));
    }

    /**
     * 按照玫瑰线来初始化弹幕的位置和旋转
     * @param count 玫瑰线花瓣/叶片的数量
     * @param size 玫瑰线花瓣的大小
     * @param delta 决定着玫瑰线上的弹幕之间的间隔
     */
    public static List<Vector3d> getRoseLinePos(double radius, double count, double size, double delta) {
        double x,y;
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
                    positions.add(new Vector3d(x,y,0));
                    break;
                default:
                case XZ:
                    positions.add(new Vector3d(x,0,y));
                    break;
                case YZ:
                    positions.add(new Vector3d(0,y,x));
                    break;
            }

        }
        return positions;
    }

    /**
     * 这里的旋转角度是弧度制
     * @param prevPositions 之前的弹幕图案的坐标列表
     * @param yaw 对每一个坐标执行的 yaw 旋转角度
     * @param pitch 对每一个坐标执行的 pitch 旋转角度
     * */
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

    public static List<DanmakuColor> getRainbowColoredDanmaku() {
        List<DanmakuColor> colors = new ArrayList<>();

        colors.add(DanmakuColor.RED);
        colors.add(DanmakuColor.ORANGE);
        colors.add(DanmakuColor.YELLOW);
        colors.add(DanmakuColor.GREEN);
        colors.add(DanmakuColor.AQUA);
        colors.add(DanmakuColor.BLUE);
        colors.add(DanmakuColor.PURPLE);
        colors.add(DanmakuColor.MAGENTA);

        return colors;
    }

    public enum Plane {
        XZ,
        XY,
        YZ,
        XYZ;
    }

}
