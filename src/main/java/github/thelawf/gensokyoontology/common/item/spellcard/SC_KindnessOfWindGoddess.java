package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Supplier;

public class SC_KindnessOfWindGoddess extends SpellCardItem{
    public SC_KindnessOfWindGoddess(Properties properties, String id, String description, int duration) {
        super(properties, id, description, duration);
    }

    @Override
    public void start(PlayerEntity player) {

    }

    @Override
    public Supplier<TransformFunction> update(PlayerEntity player, int tick) {
        return null;
    }

    @Override
    public void end(PlayerEntity player) {

    }
}
