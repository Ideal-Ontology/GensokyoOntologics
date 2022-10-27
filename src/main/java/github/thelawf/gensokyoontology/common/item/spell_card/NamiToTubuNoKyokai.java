package github.thelawf.gensokyoontology.common.item.spell_card;

import github.thelawf.gensokyoontology.common.entity.DanmakuEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class NamiToTubuNoKyokai extends Item {
    // 境符「波与粒的境界」

    public NamiToTubuNoKyokai(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            // muzzle->(danmaku_type, danmaku_count(each[x,y,z] <- function))
            
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
