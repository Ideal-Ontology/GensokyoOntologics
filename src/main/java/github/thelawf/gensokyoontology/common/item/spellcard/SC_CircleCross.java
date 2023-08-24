package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.CircleCrossEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SC_CircleCross extends SpellCardItem{
    public SC_CircleCross(Properties properties, int duration) {
        super(properties, duration);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) worldIn;

            CircleCrossEntity circleCross = new CircleCrossEntity(worldIn, playerIn);
            worldIn.addEntity(circleCross);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
