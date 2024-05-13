package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.common.extensions.IForgeFluid;
import net.minecraftforge.fluids.FluidAttributes;

import java.util.Random;
import java.util.function.Supplier;

public class HotSpringBlock extends LiquidBlock implements IForgeFluid {

    public HotSpringBlock(Supplier<? extends FlowingFluid> supplier, BlockBehaviour.Properties properties) {
        super(supplier, properties);
    }


    @Override
    public FluidAttributes getAttributes() {
        return null;
    }

    @Override
    public void animateTick(BlockState pState, Level worldIn, BlockPos pos, Random rand) {
        super.animateTick(pState, worldIn, pos, rand);
        // Direction direction
        double dx = (double) pos.getX() + 0.5D;
        double dy = (double) pos.getY() + 1.0D;
        double dz = (double) pos.getZ() + 0.5D;

        double speedX = .0D;
        double speedY = .05D;
        double speedZ = .0D;

        final boolean IGNORE_RANGE_CHECK = false; // if true, always render particle regardless of how far away the player is
        final double SPAWN_PROBABILITY = 10.D;  // only spawn Lava particles occasionally (visually distracting if too many)

        if (rand.nextDouble() < SPAWN_PROBABILITY / 25.0D) {
            for (int i = 0; i < 2; i++) {
                worldIn.addParticle(ParticleTypes.CLOUD, false,
                        dx, dy, dz, speedX + rand.nextDouble() / 18, speedY, speedZ + rand.nextDouble() / 18);
            }

        }

    }

}
