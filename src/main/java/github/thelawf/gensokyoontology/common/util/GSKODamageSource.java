package github.thelawf.gensokyoontology.common.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public class GSKODamageSource extends DamageSource {
    public static final DamageSource DANMAKU = (new DamageSource("danmaku")).setDamageBypassesArmor();

    public static final DamageSource PSYCHOLOGY = (new DamageSource("psychology")).setDamageBypassesArmor();

    public static final DamageSource IMPERISHABLE_NIGHT = (new DamageSource("imperishable_night"));
    public static final DamageSource LASER = new DamageSource("laser");
    public static final DamageSource HAKUREI_POWER = new DamageSource("hakurei_power");

    public GSKODamageSource(String damageTypeIn) {
        super(damageTypeIn);
    }
}
