package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;

public class HyperboloidLaser extends SpellCardEntity{
    public HyperboloidLaser(World worldIn, LivingEntity living) {
        super(EntityRegistry.HYPERBOLOID_LASER_ENTITY.get(), worldIn, living);
    }

    public HyperboloidLaser(EntityType<HyperboloidLaser> type, World world) {
        super(EntityRegistry.HYPERBOLOID_LASER_ENTITY.get(), world);
    }

    @Override
    public void tick() {
        super.tick();
        Vector3d center = new Vector3d(Vector3f.XP);
        Vector3d nextPos = center.scale(3).rotateYaw((float) Math.PI * 2 / 20 * ticksExisted);
        nextPos = this.getPositionVec().add(nextPos);
        float yaw = -360 + (float) 360 / 20 * ticksExisted;
        Vector2f emitVec = new Vector2f(GSKOMathUtil.clamp(yaw, -360, 360), -75);

        if (ticksExisted <= 20) {
            LaserSourceEntity laser = new LaserSourceEntity(world, this.getOwner());
            laser.init(450, 30, 40);
            laser.setLocationAndAngles(nextPos.x, nextPos.y, nextPos.z, emitVec.x, emitVec.y);
            world.addEntity(laser);
        }

    }

    @Override
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
