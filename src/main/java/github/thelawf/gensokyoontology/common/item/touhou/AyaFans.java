package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.common.util.logos.math.GSKOMathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class AyaFans extends Item {
    public AyaFans(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this) && !playerIn.isCreative())
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        AxisAlignedBB aabb = new AxisAlignedBB(playerIn.getPositionVec().subtract(new Vector3d(5,1,5)),
                playerIn.getPositionVec().add(new Vector3d(5,10,5)));

        Vector3d lookVec = playerIn.getLookVec();
        List<LivingEntity> entities = worldIn.getEntitiesWithinAABB(LivingEntity.class, aabb);

        entities.forEach(entity -> {
            if (playerIn.getPositionVec().distanceTo(entity.getPositionVec()) <= 5 &&
                    entity instanceof MonsterEntity) {
                entity.applyKnockback(5.0f, -lookVec.x, -lookVec.z);
            }
        });

        for (int i = 0; i < GSKOMathUtil.randomRange(10, 30); i++) {
            worldIn.addParticle(ParticleTypes.CLOUD, false, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(),
                    lookVec.x + random.nextDouble(), lookVec.y + random.nextDouble(), lookVec.z + random.nextDouble());
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
