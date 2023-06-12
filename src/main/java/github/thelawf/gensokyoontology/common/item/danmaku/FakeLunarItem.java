package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.common.entity.projectile.FakeLunarEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.HashMap;

public class FakeLunarItem extends Item {
    public FakeLunarItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        HashMap<Integer, TransformFunction> map = new HashMap<>();
        SpellData spellData = new SpellData(map, DanmakuType.FAKE_LUNAR, DanmakuColor.PINK,
                false, false);

        DanmakuUtil.shootDanmaku(worldIn, playerIn, new FakeLunarEntity(playerIn, worldIn, spellData),
                0.6f, 0f);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
