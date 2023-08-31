package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MobiusRingEntity extends SpellCardEntity{

    public static final EntityType<MobiusRingEntity> MOBIUS_RING_ENTITY =
            EntityType.Builder.<MobiusRingEntity>create(MobiusRingEntity::new,
                            EntityClassification.MISC).size(1F,1F).trackingRange(4)
                    .updateInterval(2).build("mobius_ring_world");

    public MobiusRingEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }
    public MobiusRingEntity(World worldIn, PlayerEntity player) {
        super(MOBIUS_RING_ENTITY, worldIn, player);
    }

    @Override
    public void tick() {
        super.tick();

        // 创建圆环的水平面：
        // 定义一个位于 X-Z 平面的 MN 向量，以M为圆心形成单位圆
        Vector3d horizonVec = new Vector3d(Vector3f.XP);
        horizonVec = horizonVec.scale(1.2);

        // 创建竖圆：
        // 定义一个位于 X-Y 平面的 NA 向量， 以N为圆心
        Vector3d verticalVec = new Vector3d(Vector3f.XP);
        verticalVec = verticalVec.scale(0.8);

        // 迭代 MN 和 NA 的旋转角度
        horizonVec = horizonVec.rotateYaw((float) Math.PI / 60 * ticksExisted);
        verticalVec = verticalVec.rotatePitch((float) Math.PI / 50 * 2 * ticksExisted);

        List<DanmakuColor> colors = getRainbowColoredDanmaku();

        for (int j = 0; j < colors.size(); j++) {
            SmallShotEntity smallShot = new SmallShotEntity((LivingEntity) this.getOwner(), this.world,
                    DanmakuType.SMALL_SHOT, colors.get(j));
            verticalVec = verticalVec.rotateYaw((float) Math.PI / 60 * ticksExisted).rotatePitch(
                    (float) Math.PI / j * 2 * ticksExisted);

            setDanmakuInit(smallShot, verticalVec.add(horizonVec).add(this.getPositionVec()));
            smallShot.shoot((float) verticalVec.getX(), (float) verticalVec.getY(), (float) verticalVec.getZ(), 0.35f, 0f);
            world.addEntity(smallShot);
        }
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }

    private List<DanmakuColor> getRainbowColoredDanmaku() {
        List<DanmakuColor> colors = new ArrayList<>();

        colors.add(DanmakuColor.RED);
        colors.add(DanmakuColor.ORANGE);
        colors.add(DanmakuColor.YELLOW);
        colors.add(DanmakuColor.GREEN);
        colors.add(DanmakuColor.AQUA);
        colors.add(DanmakuColor.BLUE);
        colors.add(DanmakuColor.PURPLE);
        colors.add(DanmakuColor.MAGENTA);

        return colors;
    }
}
