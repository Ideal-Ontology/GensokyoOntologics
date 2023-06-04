package github.thelawf.gensokyoontology.common.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class GSKOCapability {

    @CapabilityInject(IIdeologyCapability.class)
    public static Capability<IIdeologyCapability> DOMAIN_CAPABILITY;
}
