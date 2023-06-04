package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.core.init.TileEntityTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SukimaTileEntity extends TileEntity implements ITickableTileEntity {

    private BlockPos prevPos;
    private BlockPos nextPos;

    public SukimaTileEntity() {
        super(TileEntityTypeRegistry.SUKIMA_TILE_ENTITY.get());
    }

    @Override
    public void tick() {

    }

    @Override
    public CompoundNBT getUpdateTag() {
        return super.getUpdateTag();
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return super.getUpdatePacket();
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {

        super.read(state, nbt);
    }

    @Override
    @Nonnull
    public CompoundNBT write(CompoundNBT compound) {
        int[] previous = {prevPos.getX(), prevPos.getY(), prevPos.getZ()};
        int[] next = {nextPos.getX(), nextPos.getY(), nextPos.getZ()};
        compound.putIntArray("prevPos", Arrays.stream(previous).toArray());
        compound.putIntArray("nextPos", Arrays.stream(next).toArray());
        markDirty();
        return super.write(compound);
    }
}
