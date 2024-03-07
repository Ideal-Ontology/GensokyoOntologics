package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.entity.HaniwaEntity;
import github.thelawf.gensokyoontology.common.util.BeliefType;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.impl.TimeCommand;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.server.ServerWorld;

import java.util.UUID;

public class HaniwaTileEntity extends TileEntity implements ITickableTileEntity {
    private int faithCount = 0;
    private boolean canAddCount;
    private UUID ownerId;
    public static final int MAX_COUNT = 20;
    public HaniwaTileEntity() {
        super(TileEntityRegistry.HANIWA_TILE_ENTITY.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        if (nbt.contains("faith_count")) {
            this.faithCount = nbt.getInt("faith_count");
        }
        if (nbt.contains("can_add_count")) {
            this.canAddCount = nbt.getBoolean("can_add_count");
        }
        if (nbt.contains("owner")) {
            this.ownerId = nbt.getUniqueId("owner");
        }
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("faith_count", this.faithCount);
        compound.putBoolean("can_add_count", this.canAddCount);
        compound.putUniqueId("owner", this.ownerId);
        return super.write(compound);
    }

    @Override
    public void tick() {
        if (this.world != null && !this.world.isRemote) {
            if (this.world.getDayTime() % 24000L == 1) {
                this.canAddCount = true;
            }

            if (this.faithCount >= MAX_COUNT && !this.world.isRemote) {
                HaniwaEntity haniwa = new HaniwaEntity(EntityRegistry.HANIWA.get(), this.world);
                haniwa.setPosition(this.pos.getX(), this.pos.getY(), this.pos.getZ());
                haniwa.setOwnerId(this.getOwnerId());
                this.world.addEntity(haniwa);
                this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
                this.remove();
            }
        }

    }

    public void addFaith(int addedCount) {
        this.setFaith(this.canAddCount ? this.faithCount + addedCount : this.faithCount);
        // this.setFaith(this.faithCount + addedCount);
        markDirty();
    }

    public void setFaith(int faithCount) {
        this.faithCount = faithCount;
        markDirty();
    }
    public void setOwnerId(UUID uuid) {
        this.ownerId = uuid;
        markDirty();
    }
    public void setCanAddCount(boolean canAddCount) {
        this.canAddCount = canAddCount;
        markDirty();
    }

    public int getFaithCount() {
        return this.faithCount;
    }

    public UUID getOwnerId() {
        return this.ownerId;
    }

    public boolean canAddCount() {
        return this.canAddCount;
    }
}
