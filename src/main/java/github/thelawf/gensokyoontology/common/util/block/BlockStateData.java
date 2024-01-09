package github.thelawf.gensokyoontology.common.util.block;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class BlockStateData implements INBTSerializable<ListNBT>, Iterable<BlockState> {
    private List<BlockState> list;

    @Override
    public ListNBT serializeNBT() {
        ListNBT nbt = new ListNBT();
        for (BlockState state : this.list) {
            nbt.add(NBTUtil.writeBlockState(state));
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(ListNBT nbt) {
        this.list.clear();
        for (int i = 0; i < nbt.size(); i++) {
            this.list.add(NBTUtil.readBlockState(nbt.getCompound(i)));
        }
    }

    public List<BlockState> asList() {
        return this.list;
    }
    public void add(BlockState pos) {
        this.list.add(pos);
    }
    public void remove(BlockState pos) {
        this.list.remove(pos);
    }
    public void remove(int index) {
        this.list.remove(index);
    }
    public BlockState get(int index) {
        return this.list.get(index);
    }
    public void set(int index, BlockState value) {
        this.list.set(index, value);
    }
    public int indexOf(BlockState value) {
        return this.list.indexOf(value);
    }

    @NotNull
    @Override
    public Iterator<BlockState> iterator() {
        return this.list.iterator();
    }
}
