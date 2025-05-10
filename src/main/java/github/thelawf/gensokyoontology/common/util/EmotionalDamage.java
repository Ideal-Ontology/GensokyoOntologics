package github.thelawf.gensokyoontology.common.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.IndirectEntityDamageSource;
import org.jetbrains.annotations.Nullable;

public class EmotionalDamage extends IndirectEntityDamageSource {
    public EmotionalDamage(String damageTypeIn, Entity source, @Nullable Entity indirectEntityIn) {
        super(damageTypeIn, source, indirectEntityIn);
    }
}
