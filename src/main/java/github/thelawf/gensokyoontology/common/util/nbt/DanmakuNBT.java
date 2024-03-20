package github.thelawf.gensokyoontology.common.util.nbt;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import net.minecraft.nbt.StringNBT;

public class DanmakuNBT {
    public static final String TYPE = "danmaku";
    private final StringNBT danmakuType;
    private final StringNBT danmakuColor;

    public DanmakuNBT(StringNBT danmakuType, StringNBT danmakuColor) {
        this.danmakuType = danmakuType;
        this.danmakuColor = danmakuColor;
    }
    public DanmakuColor getDanmakuType() {
        return DanmakuColor.valueOf(this.danmakuType.getString().toUpperCase());
    }

    public DanmakuColor getDanmakuColor() {
        return DanmakuColor.valueOf(this.danmakuColor.getString().toUpperCase());
    }
}
