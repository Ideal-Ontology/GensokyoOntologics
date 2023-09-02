package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.core.SpellCardRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.util.IntIdentityHashBiMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static <T extends AbstractDanmakuEntity> void shootDanmaku(@NotNull World worldIn, PlayerEntity playerIn,
                                                                      T danmakuEntityType, float velocity, float inaccuracy) {
        Vector3d lookVec = playerIn.getLookVec();

        danmakuEntityType.setNoGravity(true);
        danmakuEntityType.setLocationAndAngles(playerIn.getPosX(), playerIn.getPosY() + playerIn.getEyeHeight(), playerIn.getPosZ(),
                (float) lookVec.y, (float) lookVec.z);
        danmakuEntityType.shoot(lookVec.x, lookVec.y, lookVec.z, velocity, inaccuracy);
        worldIn.addEntity(danmakuEntityType);

    }

    public static <T extends AbstractDanmakuEntity> void shootWaveAndParticle(@NotNull World worldIn, PlayerEntity playerIn,
                                                                              float velocity, float inaccuracy,
                                                                              int ticksExisted) {
        SpellData spellData = new SpellData(new HashMap<>());
        // 在具体的符卡类中，这里初始化的弹幕实体类应该写成泛型强制类型转换：
        // if (muzzle.getDanmaku() instanceof LargeshotEntity) {
        //    LargeShotEntity danmaku = (LargeShotEntity) muzzle.getDanmaku();
        // }
        // 先判断muzzle里面弹幕的类型是否和符卡需要的类型一致，再初始化弹幕

    }

    public static <D extends AbstractDanmakuEntity> void initDanmaku(D danmaku, Vector3d muzzle) {
        danmaku.setNoGravity(true);
        danmaku.setLocationAndAngles(muzzle.getX(), muzzle.getY(), muzzle.getZ(),
                (float) muzzle.y, (float) muzzle.z);
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
        final List<Item> danmakuItems = new ArrayList<>();
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
}
