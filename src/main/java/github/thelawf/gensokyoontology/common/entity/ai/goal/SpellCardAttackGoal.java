package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.List;

public abstract class SpellCardAttackGoal extends Goal {


    /** Map的索引值是符卡战斗的总共次数，Map的key表示符卡战斗的类型。<br>
     * Pair的第一个Float代表为了击破该符卡需要对BOSS造成的总伤害，
     * 第二个Integer表示该符卡提供给玩家的击破时间，单位为tick。
     */
    protected final List<Stage> stages;

    /**
     * @param stages 该形参是一个映射表类型，其索引值是符卡战斗的总共次数，其key表示符卡战斗的类型。Pair的第一个Float代表为了击破该符卡需要对BOSS造成的总伤害，第二个Integer表示该符卡提供给玩家的击破时间，单位为tick。
     */
    public SpellCardAttackGoal(List<Stage> stages) {
        this.stages = stages;
    }

    public enum Type{
        /** 非符 */
        NON_SPELL,

        /** 可击破符卡 */
        SPELL_CARD_BREAKABLE,

        /** 时符 */
        TEMPORAL_SPELL;

    }

    public static class Stage {
        public final Type type;
        public final SpellCardEntity spellCard;
        public final int duration;
        public final boolean isBreakable;

        public Stage(Type type, SpellCardEntity spellCard, int duration, boolean isBreakable) {
            this.type = type;
            this.spellCard = spellCard;
            this.duration = duration;
            this.isBreakable = isBreakable;
        }
    }
}
