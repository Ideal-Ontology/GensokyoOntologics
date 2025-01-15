package github.thelawf.gensokyoontology.common.capability.world;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// 2202532849087618747
public class EternalSummerCapProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private EternalSummerCapability capability;
    private boolean isTriggered;
    public EternalSummerCapProvider(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.ETERNAL_SUMMER ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    private EternalSummerCapability getOrCreateCapability() {
        if (this.capability == null) {
            this.capability = new EternalSummerCapability(this.isTriggered);
        }
        return this.capability;
    }
    @Override
    public CompoundNBT serializeNBT() {
        return getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getOrCreateCapability().deserializeNBT(nbt);
    }
}
