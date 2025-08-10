package github.thelawf.gensokyoontology.common.entity.combat;

import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MobiusRingEntity extends AbstractSpellCardEntity {

    public MobiusRingEntity(EntityType<? extends AbstractSpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public MobiusRingEntity(World worldIn, PlayerEntity player) {
        super(EntityRegistry.MOBIUS_RING_WORLD_ENTITY.get(), worldIn, player);
    }

    @Override
    public void tick() {

        // 创建圆环的水平面：
        // 定义一个位于 X-Z 平面的 PQ 向量，以P为圆心形成圆P
        Vector3d horizonVec = new Vector3d(Vector3f.ZP);
        horizonVec = horizonVec.scale(6);

        // 创建竖圆：
        Vector3d verticalVec = new Vector3d(Vector3f.ZP);
        verticalVec = verticalVec.scale(3);

        float velocity = 0.6f;
        float rotation = (float) (Math.PI / 80 * 2 * ticksExisted);

        horizonVec = horizonVec.rotateYaw(rotation);
        List<DanmakuColor> colors = DanmakuUtil.getRainbowColoredDanmaku();

        createMobius(horizonVec, verticalVec, colors, velocity);
        Vector3d horizonVecNew = horizonVec.rotateYaw((float) Math.PI);
        createMobius(horizonVecNew, verticalVec, colors, velocity);

        // for (int i = 0; i < colors.size(); i++) {
        //     SmallShotEntity smallShot = new SmallShotEntity((LivingEntity) this.getOwner(), this.world,
        //             DanmakuType.SMALL_SHOT, colors.get(i));
//
        //     /*
        //      * 这里的操作是不是意味着：
        //      * 设verticalVec 为 V，俯仰角为θ，偏航角为φ
        //      * 俯仰角旋转操作等同于 V(x, y * cos(θ) + z * sin(θ), z * cos(θ) - y * sin(θ))
        //      * 偏航角旋转操作等同于 V(x * cos(φ) + z * sin(φ), y, z1 * cos(φ) - x * sin(φ))
        //      * 整体的旋转操作等同于 V(x * cos(φ) + z * sin(φ), y, (z * cos(θ) - y * sin(θ) * cos(φ)) - x * sin(φ))
        //      */
        //     verticalVec = verticalVec.rotatePitch((float) Math.PI / colors.size() * i);
        //     verticalVec = verticalVec.rotateYaw((float) Math.PI / 80 * 2 * ticksExisted);
//
        //     setDanmakuInit(smallShot, horizonVec.add(this.getPositionVec()));
        //     smallShot.shoot((float) verticalVec.getX(), (float) verticalVec.getY(), (float) verticalVec.getZ(), velocity, 0f);
//
        //     world.addEntity(smallShot);
        // }

        int lifeSpan = 1000;
        if (ticksExisted >= lifeSpan) {
            this.remove();
        }
        super.tick();
    }

    private void createMobius(Vector3d horizonVec, Vector3d verticalVec, List<DanmakuColor> colors, float velocity) {
        for (int i = 0; i < colors.size(); i++) {
            SmallShotEntity smallShot = new SmallShotEntity((LivingEntity) this.getOwner(), this.world,
                    DanmakuType.SMALL_SHOT, colors.get(i));

            /*
             * 这里的操作是不是意味着：
             * 设verticalVec 为 V，俯仰角为θ，偏航角为φ
             * 俯仰角旋转操作等同于 V(x, y * cos(θ) + z * sin(θ), z * cos(θ) - y * sin(θ))
             * 偏航角旋转操作等同于 V(x * cos(φ) + z * sin(φ), y, z1 * cos(φ) - x * sin(φ))
             * 整体的旋转操作等同于 V(x * cos(φ) + z * sin(φ), y, (z * cos(θ) - y * sin(θ) * cos(φ)) - x * sin(φ))
             */
            verticalVec = verticalVec.rotatePitch((float) Math.PI / colors.size() * i);
            verticalVec = verticalVec.rotateYaw((float) Math.PI / 80 * 2 * ticksExisted);

            setDanmakuInit(smallShot, horizonVec.add(this.getPositionVec()));
            smallShot.shoot((float) verticalVec.getX(), (float) verticalVec.getY(), (float) verticalVec.getZ(), velocity, 0f);

            world.addEntity(smallShot);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SC_MOBIUS_RING_WORLD.get());
    }

}
