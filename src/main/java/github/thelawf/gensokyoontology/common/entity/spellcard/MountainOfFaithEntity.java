package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.*;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.UUID;

public class MountainOfFaithEntity extends SpellCardEntity{

    public static final EntityType<MountainOfFaithEntity> MOUTAIN_OF_FAITH_ENTITY =
            EntityType.Builder.<MountainOfFaithEntity>create(MountainOfFaithEntity::new,
                            EntityClassification.MISC).size(1F,1F).trackingRange(4)
                    .updateInterval(2).build("mountain_of_faith");

    public MountainOfFaithEntity(EntityType<?> entityTypeIn, World worldIn, UUID owner) {
        super(entityTypeIn, worldIn, owner);
    }

    public MountainOfFaithEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        Vector3d origin = new Vector3d(Vector3f.XP);
        PlayerEntity player = world.getPlayers().get(0);

        HashMap<Integer, TransformFunction> map = new HashMap<>();
        SpellData spellData = new SpellData(map, DanmakuType.LARGE_SHOT, DanmakuColor.PINK, false, false);

        final int level = 4;
        final int track = 5;
        final int count = 15;

        Logger LOGGER = LogManager.getLogger();


        for (int i = 1; i < level; i++) {
            for (int j = 0; j < track; j++) {
                if (ticksExisted % count > 3) {
                    // 两个origin 的含义不同
                    // centerLocal 表示在三个不同的半径上将会生成三个不同半径的花瓣形曲线
                    // muzzleLocal 表示每个花瓣形曲线上的某一个圆弧花瓣线的中心位置

                    Vector3d centerLocal;
                    if (i % 2 == 0) {
                        centerLocal = origin.add(i * 2, 0, i * 2).rotateYaw((float) (Math.PI / track * j * 2));
                    }
                    else {
                        centerLocal = origin.add(i * 2, 0, i * 2).rotateYaw((float) (Math.PI / track * j * 2))
                                .rotateYaw((float) (Math.PI / track * j));
                    }

                    Vector3d muzzleLocal = origin.add(i * 2, 0, i * 2);
                    Vector3d muzzleNext = muzzleLocal.rotateYaw((float) (Math.PI / 50 * ticksExisted));
                    Vector3d muzzleGlobal = muzzleNext.add(this.getPositionVec()).add(centerLocal);

                    LargeShotEntity largeShot = new LargeShotEntity(player, world, spellData);
                    setDanmakuInit(largeShot, muzzleGlobal);
                    // largeShot.shoot(muzzleNext.x, muzzleNext.y, muzzleNext.z, 0.1F, 0F);
                    world.addEntity(largeShot);

                    float speed = 0.5F + (ticksExisted - 50) * 0.02F;
                    if (largeShot.ticksExisted > 50) {
                        largeShot.setMotion(muzzleNext.normalize());
                    }
                }
            }
        }

    }

    @Override
    public ItemStack getItem() {
        // return new ItemStack(ItemRegistry.SC_MOUNTAIN_OF_FAITH.get());
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }
}
