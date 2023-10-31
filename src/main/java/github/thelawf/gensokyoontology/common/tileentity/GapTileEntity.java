package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.common.util.world.GSKOWorldUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class GapTileEntity extends TileEntity implements ITickableTileEntity {

    private BlockPos destinationPos;
    private RegistryKey<World> destinationWorld;

    public GapTileEntity() {
        super(TileEntityTypeRegistry.GAP_TILE_ENTITY.get());
    }

    @Override
    public void tick() {

    }

    @Override
    @NotNull
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
        if (nbt.contains("DestinationPos")) {
            this.destinationPos = BlockPos.fromLong(nbt.getLong("DestinationPos"));
        }
        if (nbt.contains("DestinationWorld")) {
            this.destinationWorld = GSKOWorldUtil.getWorldDimension(new ResourceLocation(
                    nbt.getString("DestinationWorld")));
        }
    }

    @Override
    @Nonnull
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString("DestinationWorld", this.destinationWorld.getLocation().toString());
        compound.putLong("DestinationPos", this.destinationPos.toLong());
        markDirty();
        return compound;
    }

    public void setDestinationPos(BlockPos destinationPos) {
        this.destinationPos = destinationPos;
    }

    public void setDestinationWorld(RegistryKey<World> destinationWorld) {
        this.destinationWorld = destinationWorld;
    }

    public BlockPos getDestinationPos() {
        return destinationPos;
    }

    public RegistryKey<World> getDestinationWorld() {
        return destinationWorld;
    }

}
