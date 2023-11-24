package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class SaisenBoxTileEntity extends TileEntity implements ITickableTileEntity, IRayTraceReader {
    private int count;
    public SaisenBoxTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(@NotNull BlockState state, @NotNull CompoundNBT nbt) {
        if (nbt.contains("count")) {
            this.count = nbt.getInt("count");
        }
        super.read(state, nbt);
    }

    @Override
    @NotNull
    public CompoundNBT write(@NotNull CompoundNBT compound) {
        super.write(compound);
        compound.putInt("count", this.count);
        return compound;
    }

    @Override
    public void tick() {
        AxisAlignedBB aabb = new AxisAlignedBB(getPos().up());
        if (world != null) {
            List<ItemEntity> itemEntities = world.getEntitiesWithinAABB(ItemEntity.class, aabb, EntityPredicates.IS_ALIVE).stream()
                    .filter(itemEntity -> itemEntity.getItem().equals(new ItemStack(ItemRegistry.VILLAGE_COIN.get())))
                    .collect(Collectors.toList());
            this.addCoinCount(itemEntities.size());
            markDirty();
        }
    }

    public void addCoinCount(int count){
        this.count += count;
    }
}
