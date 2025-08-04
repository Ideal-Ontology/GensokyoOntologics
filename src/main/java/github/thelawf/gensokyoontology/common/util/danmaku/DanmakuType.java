package github.thelawf.gensokyoontology.common.util.danmaku;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.text.ITextComponent;

import java.util.Objects;

public enum DanmakuType {
    DANMAKU_SHOT("danmaku_shot", 0.5f, 0.1f),
    LARGE_SHOT("large_shot", 2.5f, 5.5f),
    SMALL_SHOT("small_shot", 0.5f, 2f),
    STAR_SHOT_LARGE("large_star_shot", 1.5f, 3.0f),
    STAR_SHOT_SMALL("small_star_shot", 0.5f, 2f),
    RICE_SHOT("rice_shot", 0.5f, 1f),
    SCALE_SHOT("scale_shot", 0.5f, 2f),
    HEART_SHOT("heart_shot", 1.5f, 3.5f),
    TALISMAN_SHOT("talisman_shot", 1.0f, 2.5f),
    INYO_JADE("inyo_jade", 2.0f, 7.5f),
    FAKE_LUNAR("fake_lunar", 5.0f, 12.0f),
    CIRCLE_SHOT("circle_shot", 1.0f, 2f)
    // ,BUTTERFLY_SHOT("butterfly_shot", 1.5f, 3f),
    // KUNAI_SHOT("kunai_shot", 0.5f, 2.5f),
    // CRYSTAL_SHOT("crystal_shot", 0.5f, 2f, Effects.SLOWNESS),
    // ARROW_SHOT("arrow_shot", 1.0f, 2f),
    // OVAL_SHOT("oval_shot", 1.0f, 3f),
    // KNIFE_SHOT("knife_shot", 1.0f, 2.5f)
    ;

    public final String name;
    public final float damage;
    public final float size;

    // charm, amulet, medallion, talisman, vignettes
    DanmakuType(String name, float size, float damage) {
        this.name = name;
        this.damage = damage;
        this.size = size;
    }

    public ITextComponent toTextComponent() {
        return GensokyoOntology.translate("gui.", ".danmaku_type." + this.name);
    }

    public DanmakuType getIfMatches(String name) {
        return Objects.equals(name, this.name) ? this : DanmakuType.LARGE_SHOT;
    }
}
