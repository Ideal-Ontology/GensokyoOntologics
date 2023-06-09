package github.thelawf.gensokyoontology.common.entity.projectile;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class GSKODamageSource {
    public static final DamageSource DANMAKU = (new DamageSource(
            "danmaku"));

    public static final DamageSource PSYCHOLOGY = (new DamageSource(
            "psychology")).setDamageBypassesArmor();
}
