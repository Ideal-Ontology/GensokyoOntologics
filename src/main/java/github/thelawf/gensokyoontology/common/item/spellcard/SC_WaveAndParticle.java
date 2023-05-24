package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.SpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.Muzzle;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

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

        List<Muzzle<?>> muzzles = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            muzzles.add(new Muzzle<>(new TransformFunction()
                    .setPlayer(player).setLifeSpan(100)
                    .setInitLocation(playerPos)
                    .setShootVector(lookVec)
                    .setLifeSpan(200)
                    .setIncrement(Math.PI / 180),
                    new DanmakuShotEntity(player, world, DanmakuType.LARGE_SHOT)
            ));
        }

        SpellCardEntity spellEntity = new SpellCardEntity(
                SpellCardEntity.SPELL_CARD_ENTITY, world, muzzles);
        world.addEntity(spellEntity);

        muzzles.forEach(muzzle -> {
            LargeShotEntity danmaku = new LargeShotEntity(muzzle.getPlayer(), muzzle.getPlayer().getEntityWorld());
            Vector3d prevAngle = muzzle.getFunc().getShootVector();

            if (muzzle.getFunc().increment != 0D) {
                Vector3d newAngle = prevAngle.rotateYaw((float) muzzle.getFunc().increment * 0.1f);
                danmaku.setNoGravity(true);
                danmaku.setLocationAndAngles(muzzle.getX(), muzzle.getY(), muzzle.getZ(),
                        (float) newAngle.y, (float) newAngle.z);
            }
            else {
                danmaku.setNoGravity(true);
                danmaku.setLocationAndAngles(muzzle.getX(), muzzle.getY(), muzzle.getZ(),
                        (float) prevAngle.y, (float) prevAngle.z);
            }
            danmaku.shoot(prevAngle.x, prevAngle.y, prevAngle.z, 0.3f, 0f);
            world.addEntity(danmaku);
        });

        spellEntity.tick();

        GensokyoOntology.LOGGER.info("波粒符卡已点击释放了呀");

        return super.onItemRightClick(world, player, handIn);
    }
}
