package github.thelawf.gensokyoontology.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ImperishableNightProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private int time;
    private boolean isTriggered;
    private ImperishableNightCapability capability;

    public ImperishableNightProvider(int time, boolean isTriggered) {
        this.time = time;
        this.isTriggered = isTriggered;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.IMPERISHABLE_NIGHT ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    @NotNull
    ImperishableNightCapability getOrCreateCapability() {
        if (this.capability == null) {
            this.capability = new ImperishableNightCapability(this.time, this.isTriggered);
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
