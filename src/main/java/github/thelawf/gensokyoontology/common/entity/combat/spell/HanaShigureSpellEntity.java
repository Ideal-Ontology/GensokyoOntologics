package github.thelawf.gensokyoontology.common.entity.combat.spell;

import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.entity.combat.AbstractSpellCardEntity;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.Rot2f;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * 春符「花时雨」
 */
public class HanaShigureSpellEntity extends AbstractSpellCardEntity {
    private Random random = new Random();
    public HanaShigureSpellEntity(World worldIn, LivingEntity player) {
        super(EntityRegistry.HANA_SHIGURE_ENTITY.get(), worldIn);
        this.setOwner(player);
        this.setLocationAndAngles(player.getPosX(),player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
        this.lifeSpan = 1200;
    }

    public HanaShigureSpellEntity(EntityType<? extends AbstractSpellCardEntity> entityTypeIn, World worldIn) {
        super(EntityRegistry.HANA_SHIGURE_ENTITY.get(), worldIn);
        this.lifeSpan = 1200;
    }

    @Override
    public void tick() {

        Vector3d start = new Vector3d(GSKOMathUtil.randomRange(-1D, 1D),
                GSKOMathUtil.randomRange(-1D,1D), GSKOMathUtil.randomRange(-1D, 1D)).scale(8);
        Vector3d end = start.inverse();
        float f = (float) 1 / 75 * (ticksExisted % 200);
        Vector3d pos = GSKOMathUtil.lerp(MathHelper.clamp(f, 0f, 1f), start, end);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                Vector3d initPos = pos.add(0, 0.05 * i, 0);
                Vector3d shootVec = pos.rotateYaw((float) Math.PI * 2 / 60 * j);
                Danmaku riceShot =Danmaku.create(this.world, (LivingEntity) this.getOwner(), ItemRegistry.RICE_SHOT_PURPLE.get())
                        .pos(this.getPositionVec().add(initPos))
                        .rot(Rot2f.from(shootVec));
                riceShot.shoot(shootVec.x, shootVec.y, shootVec.z, 0.5f, 0f);
                LogManager.getLogger().info(riceShot.hasNoGravity());
                world.addEntity(riceShot);
            }
        }

        super.tick();
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }
}
