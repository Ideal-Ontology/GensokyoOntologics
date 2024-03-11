package github.thelawf.gensokyoontology.common.entity.spellcard.boss;

import github.thelawf.gensokyoontology.api.entity.ISpellCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BossSpell {
    public final List<Runnable> spellCards;

    public BossSpell(List<Runnable> spellCards) {
        this.spellCards = spellCards;
    }

    public static BossSpell of(Runnable... spellCards) {
        List<Runnable> spellCardList = new ArrayList<>(Arrays.asList(spellCards));
        return new BossSpell(spellCardList);
    }

    public void callByIndex(int index){
        spellCards.get(index).run();
    }
}
