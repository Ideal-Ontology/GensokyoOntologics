package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

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
        Vector3d top = new Vector3d(1, 10, 0).scale(2);
        Vector3d bottom = new Vector3d(Vector3f.XP).scale(2);
        // top = top.rotateYaw((float) Math.PI / 20 * ticksExisted);
        // bottom = bottom.rotateYaw((float) Math.PI / 20 * (ticksExisted + 1));
        Vector3d emitVec = top.subtract(bottom);

        LaserSourceEntity laser = new LaserSourceEntity(world, this.getOwner());
        laser.init(100, 30, 40);
        laser.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(),
                (float) emitVec.y, (float) emitVec.z);
        world.addEntity(laser);
    }

    @Override
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
