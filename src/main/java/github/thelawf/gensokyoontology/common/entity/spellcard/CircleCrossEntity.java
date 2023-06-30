package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.HeartShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.SpellData;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

public class CircleCrossEntity extends SpellCardEntity {

    private final int lifeSpan = 100;
    public static final EntityType<CircleCrossEntity> CIRCLE_CROSS_ENTITY =
            EntityType.Builder.<CircleCrossEntity>create(CircleCrossEntity::new,
                            EntityClassification.MISC).size(1F,1F).trackingRange(4)
                    .updateInterval(2).build("circle_cross");

    public CircleCrossEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn, PlayerEntity player) {
        super(entityTypeIn, worldIn, player);
    }

    public CircleCrossEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if (ticksExisted >= lifeSpan)
            this.remove();

        PlayerEntity player = (PlayerEntity) this.getOwner();
        Vector3d center = new Vector3d(Vector3f.XP);

        Vector3d local = center.add(4,0,0).rotateYaw(((float) (Math.PI / 60 * ticksExisted)));
        Vector3d global = local.add(this.getPositionVec());

        HashMap<Integer, TransformFunction> map = new HashMap<>();
        SpellData spellData = new SpellData(map, DanmakuType.LARGE_SHOT, DanmakuColor.PINK, false, false);

        if (ticksExisted % 3 == 0) {
            for (int i = 0; i < 20; i++) {
                Vector3d nextShootAngle = center.rotateYaw((float) (Math.PI / 20) * i);
                LargeShotEntity largeShot = new LargeShotEntity(player, world, spellData);
                largeShot.setLocationAndAngles(global.x, global.y, global.z,
                        (float) center.y, (float) center.z);
                largeShot.setNoGravity(true);

                largeShot.shoot(nextShootAngle.x, 0F, nextShootAngle.z, 0.5F, 0F);
                world.addEntity(largeShot);
            }
            for (int i = 0; i < 20; i++) {
                Vector3d nextShootAngle = center.rotateYaw((float) -(Math.PI / 20) * i);
                LargeShotEntity largeShot = new LargeShotEntity(player, world, spellData);
                largeShot.setLocationAndAngles(global.x, global.y, global.z,
                        (float) center.y, (float) center.z);
                largeShot.setNoGravity(true);

                largeShot.shoot(nextShootAngle.x, 0F, nextShootAngle.z, 0.5F, 0F);
                world.addEntity(largeShot);
            }
        }
    }

    private void shootDanmakuPattern () {
        PlayerEntity player = world.getPlayers().get(0);
        Vector3d muzzle = new Vector3d(Vector3f.XP);

        if (ticksExisted % 10 == 0) {
            for (int i = 0; i < 50; i++) {
                LargeShotEntity largeShot = new LargeShotEntity(player, world, new SpellData(new HashMap<>()));
                Vector3d nextShootAngle = muzzle.rotateYaw((float) (Math.PI / 50) * i);

                initDanmaku(largeShot, muzzle);
                largeShot.shoot(nextShootAngle.x, 0F, nextShootAngle.z, 0.5F, 0F);
                world.addEntity(largeShot);
            }
            for (int i = 0; i < 50; i++) {
                LargeShotEntity largeShot = new LargeShotEntity(player, world, new SpellData(new HashMap<>()));
                Vector3d nextShootAngle = muzzle.rotateYaw((float) -(Math.PI / 50) * i);

                initDanmaku(largeShot, muzzle);
                largeShot.shoot(nextShootAngle.x, 0F, nextShootAngle.z, 0.5F, 0F);
                world.addEntity(largeShot);
            }
        }
        else if (ticksExisted % 10 == 5){
            for (int i = 0; i < 50; i++) {
                LargeShotEntity largeShot = new LargeShotEntity(player, world, new SpellData(new HashMap<>()));
                Vector3d nextShootAngle = muzzle.rotateYaw((float) (Math.PI / 50) * i).rotateYaw((float) (Math.PI / 100));

                initDanmaku(largeShot, muzzle);
                largeShot.shoot(nextShootAngle.x, 0F, nextShootAngle.z, 0.5F, 0F);
                world.addEntity(largeShot);
            }
            for (int i = 0; i < 50; i++) {
                LargeShotEntity largeShot = new LargeShotEntity(player, world, new SpellData(new HashMap<>()));
                Vector3d nextShootAngle = muzzle.rotateYaw((float) -(Math.PI / 50) * i).rotateYaw((float) (Math.PI / 100));

                initDanmaku(largeShot, muzzle);
                largeShot.shoot(nextShootAngle.x, 0F, nextShootAngle.z, 0.5F, 0F);
                world.addEntity(largeShot);
            }
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }
}
