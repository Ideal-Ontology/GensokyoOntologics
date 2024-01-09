package github.thelawf.gensokyoontology.common.util.block;

import com.google.common.collect.Lists;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class BlockPositions implements INBTSerializable<ListNBT>, Iterable<BlockPos> {
    private final List<BlockPos> list = Lists.newArrayList();

    @Override
    public ListNBT serializeNBT() {
        ListNBT nbt = new ListNBT();
        for (BlockPos pos : this.list) {
            nbt.add(NBTUtil.writeBlockPos(pos));
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(ListNBT nbt) {
        this.list.clear();
        for (int i = 0; i < nbt.size(); i++) {
            this.list.add(NBTUtil.readBlockPos(nbt.getCompound(i)));
        }
    }

    public List<BlockPos> asList() {
        return this.list;
    }
    public void add(BlockPos pos) {
        this.list.add(pos);
    }
    public void remove(BlockPos pos) {
        this.list.remove(pos);
    }
    public void remove(int index) {
        this.list.remove(index);
    }
    public BlockPos get(int index) {
        return this.list.get(index);
    }
    public void set(int index, BlockPos value) {
        this.list.set(index, value);
    }
    public int indexOf(BlockPos value) {
        return this.list.indexOf(value);
    }

    @NotNull
    @Override
    public Iterator<BlockPos> iterator() {
        return this.list.iterator();
    }

}
