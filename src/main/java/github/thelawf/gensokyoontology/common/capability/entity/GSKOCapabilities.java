package github.thelawf.gensokyoontology.common.capability.entity;

import github.thelawf.gensokyoontology.common.capability.world.BloodyMistCapability;
import github.thelawf.gensokyoontology.common.capability.world.ImperishableNightCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

public class GSKOCapabilities {

    @CapabilityInject(BloodyMistCapability.class)
    public static Capability<BloodyMistCapability> BLOODY_MIST;

    @CapabilityInject(ImperishableNightCapability.class)
    public static Capability<ImperishableNightCapability> IMPERISHABLE_NIGHT;

    public static void registerCapabilities() {
        register(BloodyMistCapability.class);
        register(ImperishableNightCapability.class);

        // CapabilityManager.INSTANCE.register(BloodyMistCapability.class, new Capability.IStorage<BloodyMistCapability>() {
        //     @Nullable
        //     @Override
        //     public INBT writeNBT(Capability<BloodyMistCapability> capability, BloodyMistCapability instance, Direction side) {
        //         return instance.serializeNBT();
        //     }
//
        //     @Override
        //     public void readNBT(Capability<BloodyMistCapability> capability, BloodyMistCapability instance, Direction side, INBT nbt) {
        //         instance.deserializeNBT((CompoundNBT) nbt);
        //     }
        // }, () -> null);
//
        // CapabilityManager.INSTANCE.register(ImperishableNightCapability.class, new Capability.IStorage<ImperishableNightCapability>() {
        //     @Nullable
        //     @Override
        //     public INBT writeNBT(Capability<ImperishableNightCapability> capability, ImperishableNightCapability instance, Direction side) {
        //         return instance.serializeNBT();
        //     }
//
        //     @Override
        //     public void readNBT(Capability<ImperishableNightCapability> capability, ImperishableNightCapability instance, Direction side, INBT nbt) {
        //         instance.deserializeNBT((CompoundNBT) nbt);
        //     }
        // }, () -> null);
    }


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
}
