package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.SmallStarShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GalacticArmSpellEntity extends SpellCardEntity{
    private final List<SmallStarShotEntity> smallStars;

    public GalacticArmSpellEntity(World worldIn, LivingEntity living) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(EntityRegistry.GALACTIC_ARM_SPELL_ENTITY.get(), worldIn, living);
        smallStars = newDanmakuPool(() -> new SmallStarShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                SmallStarShotEntity.class, 500);
        smallStars.forEach(entity -> entity.setLifespan(this.lifeSpan));
    }

    public GalacticArmSpellEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(entityTypeIn, worldIn);
        smallStars = newDanmakuPool(() -> new SmallStarShotEntity((LivingEntity) this.getOwner(), this.world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE),
                SmallStarShotEntity.class, 500);
        smallStars.forEach(entity -> entity.setLifespan(this.lifeSpan));
    }

    @Override
    public void tick() {
        super.tick();
        float a = 7;
        float b = 4;
        float speed = 0.06F;
        Vector3d startPos = this.getPositionVec().add(1, 0, 0);

        for (int i = 0; i < smallStars.size(); i++) {
            SmallStarShotEntity smallStar = smallStars.get(i);
            double angle = ((world.getGameTime() + i) * 0.1) % (Math.PI * 2);

            Vector3d nextPos = new Vector3d(startPos.x + a * MathHelper.cos((float) angle), startPos.y,
                    startPos.z + b * MathHelper.sin((float) angle));
            double motionX = -a * MathHelper.sin((float) angle) * speed;
            double motionZ = b * MathHelper.cos((float) angle) * speed;

            setDanmakuInit(smallStar, startPos, new Vector2f(this.rotationYaw, this.rotationPitch));
            smallStar.setPosition(nextPos.x, nextPos.y, nextPos.z);
            smallStar.setMotion(motionX, 0, motionZ);
            smallStars.set(i, smallStar);
        }

        SmallStarShotEntity smallStar = smallStars.get((int) GSKOMathUtil.clamp(ticksExisted, 0, smallStars.size()-1));
        world.addEntity(smallStar);
    }

    @Override
    public @NotNull ItemStack getItem() {
        return new ItemStack(ItemRegistry.SC_GALACTIC_SPIRAL_ARMS.get());
    }
}
