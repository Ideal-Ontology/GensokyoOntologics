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

import java.util.List;
import java.util.Set;

public class BeliefCapabilityProvider implements ICapabilitySerializable<CompoundNBT> {
    private List<Pair<BeliefType, Integer>> pairSet;
    private BeliefCapability capability;

    public BeliefCapabilityProvider(List<Pair<BeliefType, Integer>> pairSet) {
        this.pairSet = pairSet;
        this.capability = GSKOCapabilities.BELIEF.getDefaultInstance();
    }

    public BeliefCapability getOrCreate() {
        if (this.capability == null) {
            this.capability = new BeliefCapability(this.pairSet);
        }
        return this.capability;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.BELIEF ? LazyOptional.of(this::getOrCreate).cast() : LazyOptional.empty();
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
