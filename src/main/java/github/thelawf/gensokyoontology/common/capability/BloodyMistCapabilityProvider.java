package github.thelawf.gensokyoontology.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BloodyMistCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private BloodyMistCapability capability;
    private final List<String> biomeRegistryNames;
    private boolean isTriggered;

    public BloodyMistCapabilityProvider(List<String> biomeRegistryNames, boolean isTriggered) {
        this.biomeRegistryNames = biomeRegistryNames;
        this.isTriggered = isTriggered;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.BLOODY_MIST ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    @NotNull
    BloodyMistCapability getOrCreateCapability () {
        if (this.capability == null) {
            this.capability = new BloodyMistCapability(this.biomeRegistryNames, this.isTriggered);
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
