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

        // 定义弹幕路径
        List<Vector3d> path = new ArrayList<>();
        for (double i = 0; i < 2 * Math.PI; i += Math.PI / 180) {
            double x = 5 * (Math.cos(i) + Math.sin(i) * Math.sin(i) * Math.cos(2 * i));
            double y = 5 * (Math.sin(i) - Math.sin(i) * Math.cos(i) * Math.cos(2 * i));
            double z = i * 20;

            Vector3d point = new Vector3d(x, y, z);
            path.add(point);
        }

        // 创建弹幕实体并设置属性
        DanmakuEntity danmaku = new DanmakuEntity(player, world, DanmakuType.HEART_SHOT, path);
        world.addEntity(danmaku);
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
