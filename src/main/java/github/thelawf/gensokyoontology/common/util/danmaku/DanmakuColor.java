package github.thelawf.gensokyoontology.common.util.danmaku;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.text.ITextComponent;

public enum DanmakuColor {
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    AQUA,
    BLUE,
    PURPLE,
    MAGENTA,
    PINK,
    NONE;

    public ITextComponent toTextComponent() {
        return GensokyoOntology.withTranslation("gui.", ".danmaku_color." + this.name().toLowerCase());
    }
}
