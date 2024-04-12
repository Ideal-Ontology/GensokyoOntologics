package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;

public class GSKODamageSource extends DamageSource {
    public static final DamageSource DANMAKU = (new DamageSource("danmaku")).setDamageBypassesArmor();

    public static final DamageSource PSYCHOLOGY = (new DamageSource("psychology")).setDamageBypassesArmor();

    public static final DamageSource IMPERISHABLE_NIGHT = (new DamageSource("imperishable_night"));
    public static final DamageSource LASER = new DamageSource("laser");
    public static DamageSource causeIndirectLaser(LaserSourceEntity laser, Entity laserOwnerIn) {
        return new IndirectEntityDamageSource("indirect_laser", laser, laserOwnerIn);
    }
    public static DamageSource causeIndirectDanmaku(AbstractDanmakuEntity danmaku, Entity laserOwnerIn) {
        return (new IndirectEntityDamageSource("indirect_danmaku", danmaku, laserOwnerIn)).setDamageBypassesArmor();
    }
    public static final DamageSource HAKUREI_POWER = new DamageSource("hakurei_power");

    public GSKODamageSource(String damageTypeIn) {
        super(damageTypeIn);
    }
}
