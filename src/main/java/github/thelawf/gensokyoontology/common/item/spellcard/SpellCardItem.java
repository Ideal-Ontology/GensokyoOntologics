package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.item.danmaku.DanmakuItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public abstract class SpellCardItem extends Item {

    private int duration;

    public SpellCardItem(Properties properties) {
        super(properties);
    }

    public SpellCardItem(Properties properties, int duration) {
        super(properties);
        this.duration = duration;
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn,@NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this) && !playerIn.isCreative())
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        if (playerIn.getHeldItem(handIn).getItem() instanceof DanmakuItem) {
            return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
        }
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
