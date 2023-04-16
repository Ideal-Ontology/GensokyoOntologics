package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuEntity;
import github.thelawf.gensokyoontology.common.item.tools.SpellCardItem;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SC_WaveAndParticle extends SpellCardItem {

    public SC_WaveAndParticle(Properties properties, String id, String description, int duration) {
        super(properties, id, description, duration);
    }

    @Override
    public void start(PlayerEntity player) {
        World world = player.world;

        // 获取玩家位置
        Vector3d playerPos = player.getPositionVec();

        // 创建弹幕实体并设置属性
        // DanmakuEntity danmaku = new DanmakuEntity(player, world, DanmakuType.HEART_SHOT, path);
        // world.addEntity(danmaku);
    }

    @Override
    public void update(PlayerEntity player, int tick) {

    }

    @Override
    public void end(PlayerEntity player) {

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        for (int i = 0; i < 100; i++) {
            start(playerIn);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
