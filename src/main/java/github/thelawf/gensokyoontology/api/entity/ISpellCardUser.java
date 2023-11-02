package github.thelawf.gensokyoontology.api.entity;

import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import net.minecraft.entity.Entity;

import java.util.function.Predicate;

public interface ISpellCardUser {

    void spellCardAttack(SpellCardEntity spellCard, int ticksIn);
    // void spellCardAttack(SpellCardEntity spellCard);
}
