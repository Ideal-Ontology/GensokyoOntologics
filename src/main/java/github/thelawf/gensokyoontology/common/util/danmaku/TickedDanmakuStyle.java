package github.thelawf.gensokyoontology.common.util.danmaku;

import net.minecraft.client.renderer.texture.ITickable;

public abstract class TickedDanmakuStyle implements IDanmakuStyle, ITickable {
    public int ticksExisted;
    @Override
    public void tick() {
        ticksExisted++;
    }
}
