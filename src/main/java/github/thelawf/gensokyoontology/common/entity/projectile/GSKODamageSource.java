package github.thelawf.gensokyoontology.common.entity.projectile;

import net.minecraft.util.DamageSource;

public class GSKODamageSource extends DamageSource{
    public static final DamageSource DANMAKU = (new DamageSource("danmaku")).setDamageBypassesArmor();

    public static final DamageSource PSYCHOLOGY = (new DamageSource("psychology")).setDamageBypassesArmor();

    public static final DamageSource LASER = new DamageSource("laser");
    public GSKODamageSource(String damageTypeIn) {
        super(damageTypeIn);
    }
}
