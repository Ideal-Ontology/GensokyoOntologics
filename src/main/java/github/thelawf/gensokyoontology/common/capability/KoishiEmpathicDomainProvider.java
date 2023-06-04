package github.thelawf.gensokyoontology.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KoishiEmpathicDomainProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {

    private IIdeologyCapability domainCapability;

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapability.DOMAIN_CAPABILITY ? LazyOptional.of(this::getOrCreateCapability).cast() :
                LazyOptional.empty();
    }

    IIdeologyCapability getOrCreateCapability() {
        if (domainCapability == null) {
            this.domainCapability = new DomainCapability(
                    new AxisAlignedBB(0,0,0,50,50,50));
        }
        return this.domainCapability;
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
