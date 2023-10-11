package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
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

        // 创建圆环的水平面：
        // 定义一个位于 X-Z 平面的 PQ 向量，以P为圆心形成圆P
        Vector3d horizonVec = new Vector3d(Vector3f.ZP);
        horizonVec = horizonVec.scale(6);

        // 创建竖圆：
        /*
        定义一个位于 X-Y 平面的 QA 向量， 以Q为球心，做一个以QA为半径向量的球
        在球Q上，N点和S点分别代表北极点和南极点，连接NS，经过Q点。
        现在，让点A绕球Q的0度经线旋转30度，
         */
        Vector3d verticalVec = new Vector3d(Vector3f.ZP);
        verticalVec = verticalVec.scale(3);

        float velocity = 0.2f;
        float rotation = (float) (Math.PI / 80 * 2 * ticksExisted);

        // rotation = ticksExisted % 180 > 90 ? (float) -(Math.PI / 180 * ticksExisted) : rotation;
        // velocity = ticksExisted % 200 > 100 ? this.getSpeedFactor() - acceleration * (ticksExisted % 100):
        //         this.getSpeedFactor() + acceleration * (ticksExisted % 100);

        horizonVec = horizonVec.rotateYaw(rotation);

        List<DanmakuColor> colors = DanmakuUtil.getRainbowColoredDanmaku();

        for (int i = 0; i < colors.size(); i++) {
            SmallShotEntity smallShot = new SmallShotEntity((LivingEntity) this.getOwner(), this.world,
                    DanmakuType.SMALL_SHOT, colors.get(i));

            verticalVec = verticalVec.rotatePitch((float) Math.PI / colors.size() * i);
            verticalVec = verticalVec.rotateYaw((float) Math.PI / 80 * 2 * ticksExisted);

            setDanmakuInit(smallShot, horizonVec.add(this.getPositionVec()));
            smallShot.shoot((float) verticalVec.getX(), (float) verticalVec.getY(), (float) verticalVec.getZ(), velocity, 0f);

            world.addEntity(smallShot);
        }

        int lifeSpan = 1000;
        if (ticksExisted >= lifeSpan) {
            this.remove();
        }
        super.tick();
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }

}
