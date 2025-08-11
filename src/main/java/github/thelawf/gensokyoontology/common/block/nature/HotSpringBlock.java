package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeFluid;
import net.minecraftforge.fluids.FluidAttributes;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Supplier;

public class HotSpringBlock extends FlowingFluidBlock implements IForgeFluid {

    public HotSpringBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    @Override
    public boolean isEntityInside(FluidState state, IWorldReader world, BlockPos pos, Entity entity, double yToTest, Tag<Fluid> tag, boolean testingHead) {
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entity;
            living.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 2 * 10, 1));
        }
        return true;
    }

    @Override
    public FluidAttributes getAttributes() {
        return null;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(@Nonnull BlockState stateIn, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Random rand) {
        // Direction direction
        double dx = (double) pos.getX() + 0.5D;
        double dy = (double) pos.getY() + 1.0D;
        double dz = (double) pos.getZ() + 0.5D;

        double speedX = .0D;
        double speedY = .05D;
        double speedZ = .0D;

        final boolean IGNORE_RANGE_CHECK = false; // if true, always render particle regardless of3D how far away the player is
        final double SPAWN_PROBABILITY = 10.D;  // only spawn Lava particles occasionally (visually distracting if too many)

        if (rand.nextDouble() < SPAWN_PROBABILITY / 25.0D) {
            for (int i = 0; i < 2; i++) {
                worldIn.addParticle(ParticleTypes.CLOUD, false,
                        dx, dy, dz, speedX + rand.nextDouble() / 18, speedY, speedZ + rand.nextDouble() / 18);
            }

        }

    }

}
