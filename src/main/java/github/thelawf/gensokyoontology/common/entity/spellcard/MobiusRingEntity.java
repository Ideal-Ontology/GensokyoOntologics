package github.thelawf.gensokyoontology.common.entity.spellcard;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.logoslib.math.GSKOMathUtil;
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
        // 定义一个位于 X-Z 平面的 PQ 向量，以P为圆心形成圆P
        Vector3d horizonVec = new Vector3d(Vector3f.ZP);
        horizonVec = horizonVec.scale(4);

        // 创建竖圆：
        /*
        定义一个位于 X-Y 平面的 QA 向量， 以Q为球心，做一个以QA为半径向量的球
        在球Q上，N点和S点分别代表北极点和南极点，连接NS，经过Q点。
        现在，让点A绕球Q的0度经线旋转30度，
         */
        Vector3d verticalVec = new Vector3d(Vector3f.ZP);
        verticalVec = verticalVec.scale(1.5);

        // 迭代 MN 和 NA 的旋转角度
        horizonVec = horizonVec.rotateYaw((float) Math.PI / 80 * 2 * ticksExisted);

        List<DanmakuColor> colors = getRainbowColoredDanmaku();

        for (int i = 0; i < colors.size(); i++) {
            SmallShotEntity smallShot = new SmallShotEntity((LivingEntity) this.getOwner(), this.world,
                    DanmakuType.SMALL_SHOT, colors.get(i));

            verticalVec = verticalVec.rotatePitch((float) Math.PI / colors.size() * i);
            verticalVec = verticalVec.rotateYaw((float) Math.PI / 80 * 2 * ticksExisted);
            //verticalVec = GSKOMathUtil.rotateCircleDot(verticalVec,
            //        0, Math.PI / 80 * 2 * ticksExisted);

            // setDanmakuInit(smallShot, horizonVec.add(this.getPositionVec()));
            setDanmakuInit(smallShot, horizonVec.add(this.getPositionVec()));
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
