package github.thelawf.gensokyoontology.common.capability.entity;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BeliefCapabilityProvider implements ICapabilitySerializable<ListNBT> {
    private BeliefCapability capability;

    public BeliefCapabilityProvider() {
        this.capability = GSKOCapabilities.BELIEF.getDefaultInstance();
    }

    public BeliefCapability getOrCreate() {
        if (this.capability == null) {
            this.capability = new BeliefCapability();
        }
        return this.capability;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.BELIEF ? LazyOptional.of(this::getOrCreate).cast() : LazyOptional.empty();
    }

    @Override
    public ListNBT serializeNBT() {
        return getOrCreate().serializeNBT();
    }

    @Override
    public void deserializeNBT(ListNBT nbt) {
        getOrCreate().deserializeNBT(nbt);
    }
}
