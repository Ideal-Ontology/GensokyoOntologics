package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuEntity;
import net.minecraft.entity.EntityType;

public class Muzzle {
    public final TransformFunction func;

    public final EntityType<? extends DanmakuEntity> danmaku;
    public Muzzle(TransformFunction func, EntityType<DanmakuEntity> danmaku) {
        this.func = func;
        this.danmaku = danmaku;
    }
}
