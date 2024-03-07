package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.common.entity.HaniwaEntity;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.server.ServerWorld;

public class HaniwaTileEntity extends TileEntity implements ITickableTileEntity {
    private int faithCount = 0;
    private int cooldown = 10000;
    public static final int MAX_COUNT = 20;
    public static final int MAX_COOLDOWN = 10000;
    public HaniwaTileEntity() {
        super(TileEntityRegistry.HANIWA_TILE_ENTITY.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        if (nbt.contains("faith_count")) {
            this.faithCount = nbt.getInt("faith_count");
        }
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("faith_count", this.faithCount);
        compound.putInt("cooldown", this.cooldown);
        return super.write(compound);
    }

    public void addFaith(int faithCount) {
        this.faithCount += faithCount;
        markDirty();
    }

    @Override
    public void tick() {
        if (this.world != null && this.faithCount >= MAX_COUNT && !this.world.isRemote) {
            HaniwaEntity haniwa = new HaniwaEntity(EntityRegistry.HANIWA.get(), this.world);
            haniwa.setPosition(this.pos.getX(), this.pos.getY(), this.pos.getZ());
            this.world.addEntity(haniwa);
            this.remove();
        }
    }
}
