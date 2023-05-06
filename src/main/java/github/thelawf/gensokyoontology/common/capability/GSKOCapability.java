package github.thelawf.gensokyoontology.common.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class GSKOCapability {

    @CapabilityInject(IDomainCapability.class)
    public static Capability<IDomainCapability> DOMAIN_CAPABILITY;
}
