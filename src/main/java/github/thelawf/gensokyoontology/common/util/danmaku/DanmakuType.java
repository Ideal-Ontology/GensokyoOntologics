package github.thelawf.gensokyoontology.common.util.danmaku;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.potion.Effect;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public enum DanmakuType {
    DANMAKU_SHOT("danmakuShot", 0.5f, 0.1f),
    LARGE_SHOT("largeShot", 2.5f, 5.5f),
    SMALL_SHOT("smallShot", 0.5f, 2f),
    STAR_SHOT_LARGE("largeStarShot", 1.5f, 3.0f),
    STAR_SHOT_SMALL("smallStarShot", 0.5f, 0.5f),
    RICE_SHOT("riceShot", 0.5f, 1f),
    SCALE_SHOT("scaleShot", 0.5f, 2f),
    HEART_SHOT("heartShot", 1.5f, 3.5f),
    TALISMAN_SHOT("talismanShot", 1.0f, 2.5f),
    INYO_JADE("inyoJade", 2.0f, 3f),
    FAKE_LUNAR("fakeLunar", 5.0f, 12.0f)
    // ,BUTTERFLY_SHOT("butterflyShot", 1.5f, 3f),
    // KUNAI_SHOT("kunaiShot", 0.5f, 2.5f),
    // CRYSTAL_SHOT("crystalShot", 0.5f, 2f, Effects.SLOWNESS),
    // ARROW_SHOT("arrowShot", 1.0f, 2f),
    // OVAL_SHOT("ovalShot", 1.0f, 3f),
    // KNIFE_SHOT("knifeShot", 1.0f, 2.5f);
    ;

    public final String name;
    public final float damage;
    public final float size;
    public final Effect[] effect;

    // charm, amulet, medallion, talisman, vignettes
    DanmakuType(String name, float size, float damage, @Nullable Effect... effect) {
        this.name = name;
        this.damage = damage;
        this.size = size;
        this.effect = effect;
    }

    public ITextComponent toTextComponent() {
        return GensokyoOntology.withTranslation("gui.", ".danmaku_type." + this.name);
    }
}
