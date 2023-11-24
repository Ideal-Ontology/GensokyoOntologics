package github.thelawf.gensokyoontology.common.util.danmaku;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class SpellBehavior {
    @Nullable
    public Entity target;
    public Vector3d pos;
    public Vector3d motion;
    public Vector2f rotation;
    public int keyTick;

    public SpellBehavior(@Nullable Entity target, Vector3d pos, Vector3d motion, Vector2f rotation, int keyTick) {
        this.target = target;
        this.pos = pos;
        this.rotation = rotation;
        this.motion = motion;
        this.keyTick = keyTick;
    }
}
