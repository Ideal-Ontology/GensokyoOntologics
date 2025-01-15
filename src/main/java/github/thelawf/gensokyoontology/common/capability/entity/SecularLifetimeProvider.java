package github.thelawf.gensokyoontology.common.capability.entity;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SecularLifetimeProvider implements ICapabilityProvider, INBTSerializable<LongNBT> {
    private long lifetime;
    private SecularLifeCapability capability;

    public SecularLifetimeProvider(long lifetime) {
        this.lifetime = lifetime;
        this.capability = GSKOCapabilities.SECULAR_LIFE.getDefaultInstance();
    }

    public SecularLifeCapability getOrCreate() {
        if (this.capability == null) {
            this.capability = new SecularLifeCapability(this.lifetime);
        }
        return this.capability;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.SECULAR_LIFE && this.capability != null ? LazyOptional.of(() -> this.capability).cast() : LazyOptional.empty();
    }

    @Override
    public LongNBT serializeNBT() {
        return (LongNBT) GSKOCapabilities.SECULAR_LIFE.writeNBT(getOrCreate(), null);
    }

    @Override
    public void deserializeNBT(LongNBT nbt) {
        GSKOCapabilities.SECULAR_LIFE.readNBT(getOrCreate(), null, nbt);
    }
}
