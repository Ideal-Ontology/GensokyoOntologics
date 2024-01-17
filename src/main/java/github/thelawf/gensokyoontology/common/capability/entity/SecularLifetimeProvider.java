package github.thelawf.gensokyoontology.common.capability.entity;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SecularLifetimeProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private long lifetime;
    private SecularLifeCapability capability;

    public SecularLifetimeProvider(long lifetime) {
        this.lifetime = lifetime;
        this.capability = GSKOCapabilities.SECULAR_LIFE.getDefaultInstance();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.SECULAR_LIFE ? LazyOptional.of(() -> this.capability).cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong("lifetime", this.lifetime);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (nbt.contains("lifetime")) {
            this.lifetime = nbt.getLong("lifetime");
        }
    }
}
