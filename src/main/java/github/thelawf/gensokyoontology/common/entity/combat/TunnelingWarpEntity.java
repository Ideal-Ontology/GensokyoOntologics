package github.thelawf.gensokyoontology.common.entity.combat;

import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallStarShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 跃迁「超时空隧道折跃」
 */
public class TunnelingWarpEntity extends AbstractSpellCardEntity {
    public TunnelingWarpEntity(EntityType<? extends AbstractSpellCardEntity> entityTypeIn, World worldIn, PlayerEntity player) {
        super(entityTypeIn, worldIn, player);
    }

    public TunnelingWarpEntity(EntityType<? extends AbstractSpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        LivingEntity shooter = (LivingEntity) this.getOwner();
        if (shooter == null) return;

        Vector3d starInitPos = new Vector3d(Vector3f.XP).scale(1.8);
        Vector3d starRadialShootVec = new Vector3d(Vector3f.ZP).scale(3);

        Vector3d tunnelPos = new Vector3d(Vector3f.XP).scale(3.4);

        List<DanmakuColor> colors = DanmakuUtil.getRainbowColoredDanmaku();

        if (ticksExisted % 3 == 0) {
            for (int i = 0; i < colors.size(); i++) {
                SmallShotEntity smallShot = new SmallShotEntity(shooter, world, DanmakuType.SMALL_SHOT, colors.get(i));
                tunnelPos = tunnelPos.rotateYaw((float) Math.PI / colors.size() * i);

                setDanmakuInit(smallShot, tunnelPos.add(this.getPositionVec()),
                        (float) tunnelPos.x, (float) tunnelPos.z, false);

                world.addEntity(smallShot);
            }
        }
        for (int i = 0; i < 3; i++) {
            SmallStarShotEntity smallStar = new SmallStarShotEntity(shooter, world, DanmakuType.STAR_SHOT_SMALL, DanmakuColor.BLUE);

            setDanmakuInit(smallStar, starInitPos.add(new Vector3d(0, shooter.getPosY() + 15, 0)),
                    (float) starInitPos.x, (float) starInitPos.z, true);
            smallStar.shoot(starRadialShootVec.getX(), starRadialShootVec.getY(), starRadialShootVec.getZ(),
                    5.2f, 0f);
            world.addEntity(smallStar);
        }
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }

}
