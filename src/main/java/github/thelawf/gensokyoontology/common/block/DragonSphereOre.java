package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class DragonSphereOre extends OreBlock {
    public DragonSphereOre() {
        super(Properties.from(Blocks.DIAMOND_ORE).setRequiresTool()
                .hardnessAndResistance(3.0f,3.0f)
                .sound(SoundType.STONE));
    }

    @Override
    protected int getExperience(@NotNull Random rand) {
        return MathHelper.nextInt(rand, 5,9);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}
