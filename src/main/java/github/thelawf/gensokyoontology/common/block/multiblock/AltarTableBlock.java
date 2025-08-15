package github.thelawf.gensokyoontology.common.block.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.LeverBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class AltarTableBlock extends Block {
    public AltarTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        ServerWorld serverWorld = (ServerWorld) worldIn;
        // serverWorld.getRecipeManager().getRecipe()
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
