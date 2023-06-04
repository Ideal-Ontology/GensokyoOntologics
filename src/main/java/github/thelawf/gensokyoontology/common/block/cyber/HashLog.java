package github.thelawf.gensokyoontology.common.block.cyber;

import github.thelawf.gensokyoontology.common.nbt.GensokyoOntologyNBT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class HashLog extends RotatedPillarBlock {
    public HashLog() {
        super(Properties.from(Blocks.OAK_LOG));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (context.getItem().getStack().getTag() != GensokyoOntologyNBT.nbtCanPlaceIn) {
            super.getStateForPlacement(new BlockItemUseContext(
                    Objects.requireNonNull(context.getPlayer()), context.getHand(),
                    context.getItem().getStack(), BlockRayTraceResult.createMiss(context.getHitVec(),
                    context.getFace(),context.getPos())));
            return null;
        }
        return this.getDefaultState();
    }
}
