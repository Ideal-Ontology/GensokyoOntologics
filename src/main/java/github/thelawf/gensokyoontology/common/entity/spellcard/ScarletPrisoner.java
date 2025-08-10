package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.LargeShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ScarletPrisoner extends AbstractSpellCardEntity {
    public ScarletPrisoner(World worldIn, LivingEntity living) {
        super(EntityRegistry.SCARLET_PRISONER_ENTITY.get(), worldIn, living);
    }

    public ScarletPrisoner(EntityType<ScarletPrisoner> type, World world) {
        super(EntityRegistry.SCARLET_PRISONER_ENTITY.get(), world);
    }

    @Override
    public void tick() {
        super.tick();
        Vector3d center = new Vector3d(Vector3f.XP);
        Vector3d nextPos = center.scale(3).rotateYaw((float) Math.PI * 2 / 20 * ticksExisted);
        nextPos = this.getPositionVec().add(nextPos);
        float yaw = -360 + (float) 360 / 20 * ticksExisted;
        Vector2f emitVec = new Vector2f(GSKOMathUtil.clampPeriod(yaw, -360, 360), -75);

        if (ticksExisted <= 20 && !world.isRemote()) {
            ServerWorld serverWorld = (ServerWorld) world;
            LaserSourceEntity laser = new LaserSourceEntity(world, this.getOwner());
            laser.init(450, 30, 40);
            laser.setLocationAndAngles(nextPos.x, nextPos.y, nextPos.z, emitVec.x, emitVec.y);
            boolean flag = serverWorld.getEntities()
                    .filter(entity -> entity.getType() == EntityRegistry.LASER_SOURCE_ENTITY.get())
                    .count() <= 21;
            if (flag) world.addEntity(laser);
        }

        if (ticksExisted % 20 == 0) {

            // List<Vector3d> pos1 = DanmakuUtil.ellipticPos(new Vector2f(0,0), 1, 20);
            List<Vector3d> coordinates = DanmakuUtil.spheroidPos(1, 20);

            // for (int i = 0; i < pos1.size(); i++) {
            //     for (int j = 0; j < pos1.size(); j++) {
            //         Vector3d vector3d = pos1.get(j).rotatePitch((float) Math.PI * 2 / pos1.size() * j);
            //         pos1.set(j, vector3d);
            //     }
            //     coordinates.addAll(pos1);
            // }

            coordinates.forEach(vector3d -> {
                LargeShotEntity largeShot = new LargeShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.RED);
                setDanmakuInit(largeShot, this.getPositionVec().add(vector3d.x, 15, vector3d.y));
                largeShot.shoot(vector3d.x, vector3d.y, vector3d.z, 0.6f, 0f);
                world.addEntity(largeShot);
            });
        }

    }

    @Override
    public @NotNull ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
