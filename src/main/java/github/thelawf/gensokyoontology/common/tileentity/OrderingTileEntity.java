package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.common.capability.entity.VillagerOrder;
import github.thelawf.gensokyoontology.common.nbt.GSKONBTUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderingTileEntity extends TileEntity implements ITickableTileEntity {

    private List<VillagerOrder> orders = new ArrayList<>();

    public OrderingTileEntity() {
        super(TileEntityRegistry.ORDERING_TILE_ENTITY.get());
    }

    @Override
    public void tick() {

    }

    public void addOrder(VillagerOrder order) {
        this.orders.add(order);
        this.write(new CompoundNBT());
    }

    public void removeOrder(VillagerOrder order) {
        if (this.world == null) return;

        BlockState state = this.world.getBlockState(this.pos);
        this.read(state, new CompoundNBT());

        this.orders.remove(order);
        this.write(new CompoundNBT());
    }

    public List<VillagerOrder> getOrders() {
        return this.orders;
    }

    public VillagerOrder getOrder(int index) {
        return this.orders.get(index);
    }

    public int getOrderIndex(VillagerOrder order) {
        return this.orders.indexOf(order);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        CompoundNBT tag = super.write(nbt);
        ListNBT list = new ListNBT();
        this.orders.forEach(order -> list.add(order.serializeNBT()));
        tag.put("Orders", list);
        return tag;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        ListNBT list = nbt.getList("Orders", 10);
        List<VillagerOrder> villagerOrders = new ArrayList<>();
        list.forEach(inbt -> {
            CompoundNBT compound = GSKONBTUtil.castToCompound(inbt);
            villagerOrders.add(VillagerOrder.deserialize(compound));
        });
        this.orders = villagerOrders;
    }
}
