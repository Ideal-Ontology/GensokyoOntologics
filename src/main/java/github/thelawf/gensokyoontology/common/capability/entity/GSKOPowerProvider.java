package github.thelawf.gensokyoontology.common.capability.entity;

import com.github.tartaricacid.touhoulittlemaid.TouhouLittleMaid;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GSKOPowerProvider implements ICapabilitySerializable<CompoundNBT> {

    private float count;
    private GSKOPowerCapability capability;
    public GSKOPowerProvider(float count) {
        this.count = count;
        this.capability = GSKOCapabilities.POWER.getDefaultInstance();
    }

    public GSKOPowerCapability getOrCreate() {
        if (this.capability == null) {
            this.capability = new GSKOPowerCapability(this.count);
        }
        return this.capability;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GSKOCapabilities.POWER && this.capability != null ? LazyOptional.of(() -> this.capability).cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) GSKOCapabilities.POWER.writeNBT(getOrCreate(), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        GSKOCapabilities.POWER.readNBT(getOrCreate(), null, nbt);
    }
}
