package github.thelawf.gensokyoontology.common.libs.danmakulib;

import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpellData extends ForgeRegistryEntry<SpellData> {

    public static final SpellData INSTANCE = new SpellData(new HashMap<>(), DanmakuType.LARGE_SHOT, DanmakuColor.PINK, false, false);

    public HashMap<Integer, TransformFunction> keyTransforms = new HashMap<>();
    public List<VectorOperations> vectorOperations;

    public DanmakuType danmakuType;

    public DanmakuColor danmakuColor;

    public boolean canHurtPlayer;

    public boolean hasTeamHurt;

    public SpellData(HashMap<Integer, TransformFunction> keyTransforms) {
        this.keyTransforms = keyTransforms;
    }

    public SpellData(@Nullable HashMap<Integer, TransformFunction> keyTransforms, DanmakuType danmakuType,
                     DanmakuColor danmakuColor, boolean canHurtPlayer, boolean hasTeamHurt) {
       this.keyTransforms = keyTransforms;
        this.danmakuType = danmakuType;
        this.danmakuColor = danmakuColor;
        this.canHurtPlayer = canHurtPlayer;
        this.hasTeamHurt = hasTeamHurt;
    }
}
