package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.RorschachDanmakuEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class SC_RorshachDanmaku extends SpellCardItem{
    // @Override
    // public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
    //     if (playerIn.getCooldownTracker().hasCooldown(this))
    //         return ActionResult.resultPass(playerIn.getHeldItem(handIn));
//
    //     if (worldIn instanceof ServerWorld) {
    //         RorschachDanmakuEntity rorschach = new RorschachDanmakuEntity(worldIn, playerIn);
    //         worldIn.addEntity(rorschach);
//
    //         playerIn.getCooldownTracker().setCooldown(this, 1200);
    //     }
//
    //     return super.onItemRightClick(worldIn, playerIn, handIn);
    // }


    @Override
    protected void applySpell(World worldIn, PlayerEntity playerIn) {
        RorschachDanmakuEntity rorschach = new RorschachDanmakuEntity(worldIn, playerIn);
        worldIn.addEntity(rorschach);
    }
}
