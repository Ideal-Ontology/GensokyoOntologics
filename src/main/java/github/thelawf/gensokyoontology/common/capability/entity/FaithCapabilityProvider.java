package github.thelawf.gensokyoontology.common.capability.entity;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.BeliefType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class FaithCapabilityProvider implements ICapabilitySerializable<CompoundNBT> {
    private Set<Pair<BeliefType, Integer>> pairSet;
    private FaithCapability capability;

    public FaithCapabilityProvider(Set<Pair<BeliefType, Integer>> pairSet, FaithCapability capability) {
        this.pairSet = pairSet;
        this.capability = capability;
    }

    public FaithCapability getOrCreate() {
        if (this.capability == null) {
            this.capability = new FaithCapability(this.pairSet);
        }
        return this.capability;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.FAITH ? LazyOptional.of(this::getOrCreate).cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return getOrCreate().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getOrCreate().deserializeNBT(nbt);
    }
}
