package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.item.danmaku.DanmakuItem;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Supplier;

public abstract class SpellCardItem extends Item {

    private String id;
    private String description;
    private int duration;

    public SpellCardItem(Properties properties) {
        super(properties);
    }

    public SpellCardItem(Properties properties, String id, String description, int duration) {
        super(properties);
        this.id = id;
        this.description = description;
        this.duration = duration;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getHeldItem(handIn).getItem() instanceof DanmakuItem) {
            return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
        }
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public abstract void start(PlayerEntity player);
    public abstract Supplier<TransformFunction> update(PlayerEntity player, int tick);
    public abstract void end(PlayerEntity player);
}
