package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.FullCherryBlossomEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.HellEclipseEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Supplier;

public class SC_FullCherryBlossom extends SpellCardItem {
    public SC_FullCherryBlossom(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        // try {
        //     List<SmallShotEntity> suppliers = DanmakuUtil.newDanmakuPool(() -> new SmallShotEntity(
        //             playerIn, worldIn, DanmakuType.SMALL_SHOT, DanmakuColor.RED), SmallShotEntity.class, 500);
        // } catch (ReflectiveOperationException e) {
        //     throw new RuntimeException(e);
        // }

        if (worldIn instanceof ServerWorld) {

            FullCherryBlossomEntity fullCherryBlossom = new FullCherryBlossomEntity(worldIn, playerIn);
            worldIn.addEntity(fullCherryBlossom);
            playerIn.getCooldownTracker().setCooldown(this, 1200);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
