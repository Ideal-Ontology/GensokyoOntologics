package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class IzanoObjectOre extends OreBlock {
    public IzanoObjectOre() {
        super(Properties.from(Blocks.LAPIS_ORE).setRequiresTool()
                .hardnessAndResistance(3.0f,3.0f)
                .sound(SoundType.STONE));
    }

    @Override
    protected int getExperience(@NotNull Random rand) {
        return MathHelper.nextInt(rand, 3, 7);
    }

}