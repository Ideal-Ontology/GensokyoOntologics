package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.common.util.world.GSKOWorldUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
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

    private static final int MAX_COOLDOWN_TICK = 400;
    private boolean allowTeleport = false;
    private BlockPos destinationPos;
    private RegistryKey<World> destinationWorld;
    private int cooldown = 0;

    public GapTileEntity(RegistryKey<World> destinationWorld, BlockPos destinationPos) {
        super(TileEntityRegistry.GAP_TILE_ENTITY.get());
        this.setDestinationWorld(destinationWorld);
        this.setDestinationPos(destinationPos);
    }

    public GapTileEntity() {
        super(TileEntityRegistry.GAP_TILE_ENTITY.get());
        this.setDestinationWorld(World.OVERWORLD);
        this.setDestinationPos(BlockPos.ZERO);
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
    public void read(@NotNull BlockState state, @NotNull CompoundNBT nbt) {
        if (nbt.contains("DestinationX") && nbt.contains("DestinationY") && nbt.contains("DestinationZ")) {
            this.destinationPos = new BlockPos(nbt.getInt("DestinationX"), nbt.getInt("DestinationY"), nbt.getInt("DestinationZ"));
        }
        if (nbt.contains("DestinationWorld")) {
            this.destinationWorld = GSKOWorldUtil.getWorldDimension(new ResourceLocation(
                    nbt.getString("DestinationWorld")));
        }
        if (nbt.contains("AllowTeleport")) {
            this.allowTeleport = nbt.getBoolean("AllowTeleport");
        }
        if (nbt.contains("Cooldown")) {
            setCooldown(nbt.getInt("Cooldown"));
        }
        super.read(state, nbt);
    }

    @Override
    @Nonnull
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putString("DestinationWorld", this.destinationWorld.getLocation().toString());
        compound.putBoolean("AllowTeleport", this.allowTeleport);
        compound.putInt("Cooldown", this.cooldown);
        compound.putInt("DestinationX", this.destinationPos.getX());
        compound.putInt("DestinationY", this.destinationPos.getY());
        compound.putInt("DestinationZ", this.destinationPos.getZ());

        return compound;
    }

    public void setDestinationPos(BlockPos destinationPos) {
        this.destinationPos = destinationPos;
    }

    public void setDestinationWorld(RegistryKey<World> destinationWorld) {
        this.destinationWorld = destinationWorld;
        this.allowTeleport = true;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public BlockPos getDestinationPos() {
        return this.destinationPos;
    }

    public RegistryKey<World> getDestinationWorld() {
        return this.destinationWorld;
    }

    public boolean isAllowTeleport() {
        return this.allowTeleport;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public void setAllowTeleport(boolean isAllowTeleport) {
        this.allowTeleport = isAllowTeleport;
    }

    @Override
    public void tick() {
        if (this.world != null && !this.world.isRemote) {
            if (this.cooldown > 0) {
                this.cooldown--;
            } else {
                this.cooldown++;
            }
        }

        markDirty();
    }
}
