package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.tileentity.SukimaTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SukimaBlock extends Block {
    public SukimaBlock() {
        super(Properties.create(Material.PORTAL).hardnessAndResistance(11400.f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SukimaTileEntity();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
    }
}
