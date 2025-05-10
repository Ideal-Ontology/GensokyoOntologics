package github.thelawf.gensokyoontology.common.capability.entity;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.IdentityType;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class IdentityCapabilityProvider implements ICapabilitySerializable<ListNBT> {
    private IdentityCapability capability;
    private final Map<ResourceLocation, Float> map = IdentityType.createIdentityMap();

    public IdentityCapabilityProvider() {
        this.capability = GSKOCapabilities.IDENTITY.getDefaultInstance();
    }

    public IdentityCapability getOrCreate() {
        if (this.capability == null) {
            this.capability = new IdentityCapability(map);
        }
        return this.capability;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.IDENTITY ? LazyOptional.of(this::getOrCreate).cast() : LazyOptional.empty();
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
