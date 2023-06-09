package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SpellCardBlank extends SpellCardItem {
    public SpellCardBlank(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {

        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }

    @Override
    public void start(PlayerEntity player) {

    }

    @Override
    public Supplier<TransformFunction> update(PlayerEntity player, int tick) {
        return () -> new TransformFunction().setPlayer(player);
    }

    @Override
    public void end(PlayerEntity player) {

    }
}
