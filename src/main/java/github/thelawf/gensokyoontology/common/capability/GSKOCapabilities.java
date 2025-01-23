package github.thelawf.gensokyoontology.common.capability;

import github.thelawf.gensokyoontology.common.capability.entity.IdentityCapability;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerCapability;
import github.thelawf.gensokyoontology.common.capability.entity.SecularLifeCapability;
import github.thelawf.gensokyoontology.common.capability.world.BloodyMistCapability;
import github.thelawf.gensokyoontology.common.capability.world.EternalSummerCapability;
import github.thelawf.gensokyoontology.common.capability.world.ImperishableNightCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class GSKOCapabilities {

    @CapabilityInject(BloodyMistCapability.class)
    public static Capability<BloodyMistCapability> BLOODY_MIST;
    @CapabilityInject(ImperishableNightCapability.class)
    public static Capability<ImperishableNightCapability> IMPERISHABLE_NIGHT;
    @CapabilityInject(EternalSummerCapability.class)
    public static Capability<EternalSummerCapability> ETERNAL_SUMMER;
    @CapabilityInject(GSKOPowerCapability.class)
    public static Capability<GSKOPowerCapability> POWER;
    @CapabilityInject(SecularLifeCapability.class)
    public static Capability<SecularLifeCapability> SECULAR_LIFE;
    @CapabilityInject(IdentityCapability.class)
    public static Capability<IdentityCapability> IDENTITY;

    public static void registerCapabilities() {
        register(IdentityCapability.class);
        register(GSKOPowerCapability.class);
        register(SecularLifeCapability.class);

        register(BloodyMistCapability.class);
        register(ImperishableNightCapability.class);
        register(EternalSummerCapability.class);
    }

    private static <T extends INBTSerializable<CompoundNBT>> void register(Class<T> capClass, Capability.IStorage<T> storage) {
        CapabilityManager.INSTANCE.register(capClass, storage, capClass::newInstance);
    }

    public static boolean hasCapability(World world, Capability<?> capability) {
        if (world.isRemote) return false;
        ServerWorld serverWorld = (ServerWorld) world;
        return serverWorld.getCapability(capability).isPresent();
    }

    public static Method getCapMethod(PlayerEntity player, Capability<?> capability, String methodName) {
        AtomicReference<Method> methodRef = new AtomicReference<>();
        player.getCapability(capability).ifPresent(cap -> {
            try {
                methodRef.set(cap.getClass().getDeclaredMethod(methodName));
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
        return methodRef.get();
    }

    public static INBTSerializable<CompoundNBT> getCapInstance(PlayerEntity player, Capability<? extends INBTSerializable<CompoundNBT>> capability) {
        AtomicReference<INBTSerializable<CompoundNBT>> capRef = new AtomicReference<>();
        player.getCapability(capability).ifPresent(capRef::set);
        return capRef.get();
    }

    public static <T> T getCapIns(PlayerEntity player, Capability<T> capability) {
        AtomicReference<T> capRef = new AtomicReference<>();
        player.getCapability(capability).ifPresent(cap -> capRef.set(capability.getDefaultInstance()));
        return capRef.get();
    }

    public static Object getAndInvoke(PlayerEntity player, Capability<? extends INBTSerializable<CompoundNBT>> capability, String methodName, Object... objects) {
        if (getCapMethod(player, capability, methodName) == null) {
            throw new RuntimeException("Cap not present");
        }
        try {
            return getCapMethod(player, capability, methodName).invoke(getCapInstance(player, capability), objects);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Function<Object, Object> getCapFunctor(PlayerEntity player, Capability<T> capability, String methodName) {
        return (parameter) -> {
            try {
                return getCapMethod(player, capability, methodName).invoke(getCapIns(player, capability));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> Object getFunctorResult(PlayerEntity player, Capability<T> capability, String methodName, @Nullable Object... objects) {
        return getCapFunctor(player, capability, methodName).apply(objects);
    }

    /*
     private static <T extends INBTSerializable<CompoundNBT>> void register(Class<T> capClass) {
         CapabilityManager.INSTANCE.register(capClass, new Capability.IStorage<T>() {
             @Nullable
             @Override
             public INBT writeNBT(Capability<T> capability, T instance, Direction side) {
                 return instance.serializeNBT();
             }
             @Override
             public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {
                 instance.deserializeNBT((CompoundNBT) nbt);
             }
         }, () -> null);
     }
     */

    private static <T extends INBTSerializable<N>, N extends INBT> void register(Class<T> capClass) {
        CapabilityManager.INSTANCE.register(capClass, new Capability.IStorage<T>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<T> capability, T instance, Direction side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {
                instance.deserializeNBT((N) nbt);
            }
        }, () -> null);
    }
}
