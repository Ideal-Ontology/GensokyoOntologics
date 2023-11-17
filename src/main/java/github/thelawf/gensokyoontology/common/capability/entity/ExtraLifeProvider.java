package github.thelawf.gensokyoontology.common.capability.entity;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.world.BloodyMistCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtraLifeProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {

    private ExtraLifeCapability capability;
    private int extraLifeCount;

    public ExtraLifeProvider(ExtraLifeCapability capability, int extraLifeCount) {
        this.capability = capability;
        this.extraLifeCount = extraLifeCount;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.EXTRA_LIFE ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    private ExtraLifeCapability getOrCreateCapability() {
        if (this.capability == null) {
            this.capability = new ExtraLifeCapability();
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
