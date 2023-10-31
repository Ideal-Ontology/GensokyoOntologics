package github.thelawf.gensokyoontology.common.capability;

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

    public static void registerIncidents() {
        CapabilityManager.INSTANCE.register(BloodyMistCapability.class, new Capability.IStorage<BloodyMistCapability>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<BloodyMistCapability> capability, BloodyMistCapability instance, Direction side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<BloodyMistCapability> capability, BloodyMistCapability instance, Direction side, INBT nbt) {
                instance.deserializeNBT((CompoundNBT) nbt);
            }
        }, () -> null);

        CapabilityManager.INSTANCE.register(ImperishableNightCapability.class, new Capability.IStorage<ImperishableNightCapability>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<ImperishableNightCapability> capability, ImperishableNightCapability instance, Direction side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<ImperishableNightCapability> capability, ImperishableNightCapability instance, Direction side, INBT nbt) {
                instance.deserializeNBT((CompoundNBT) nbt);
            }
        }, () -> null);
    }

}
