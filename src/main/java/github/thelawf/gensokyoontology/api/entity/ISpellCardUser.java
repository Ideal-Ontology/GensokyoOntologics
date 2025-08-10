package github.thelawf.gensokyoontology.api.entity;

import github.thelawf.gensokyoontology.common.entity.combat.AbstractSpellCardEntity;

public interface ISpellCardUser {

    void spellCardAttack(AbstractSpellCardEntity spellCard, int ticksIn);
    // void spellCardAttack(AbstractSpellCardEntity spellCard);
}
