package github.thelawf.gensokyoontology.common.entity.projectile;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class GSKODamageSource extends DamageSource {
    public GSKODamageSource(String damageTypeIn) {
        super(damageTypeIn);
    }

    public static final DamageSource DANMAKU = (new DamageSource(
            "danmaku"));
    public static final DamageSource PSYCHOLOGY = (new DamageSource(
            "psychology")).setDamageBypassesArmor();
}
