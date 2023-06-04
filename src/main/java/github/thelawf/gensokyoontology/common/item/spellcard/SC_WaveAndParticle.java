package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuMuzzle;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SC_WaveAndParticle extends SpellCardItem {

    public static final int LIFE_SPAN = 200;

    public SC_WaveAndParticle(Properties properties, String id, String description, int duration) {
        super(properties, id, description, duration);
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {

        // 获取玩家位置
        Vector3d playerPos = player.getPositionVec();
        Vector3d lookVec = player.getLookVec();

        List<DanmakuMuzzle<?>> muzzles = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Vector3d nextVec = lookVec.rotateYaw((float) (i * Math.PI / 3));

            muzzles.add(new DanmakuMuzzle<>(new TransformFunction()
                    .setPlayer(player).setLifeSpan(100)
                    .setInitLocation(playerPos)
                    .setShootVector(nextVec)
                    .setLifeSpan(200)
                    .setIncrement(Math.PI / 180),
                    new LargeShotEntity(player, world)
            ));
        }

        SpellCardEntity spellEntity = new SpellCardEntity(world, muzzles, player);
        world.addEntity(spellEntity);

        return super.onItemRightClick(world, player, handIn);

    }
}
