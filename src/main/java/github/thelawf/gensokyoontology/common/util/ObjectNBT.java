package github.thelawf.gensokyoontology.common.util;

import com.google.common.collect.Maps;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.INBTType;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;

public class ObjectNBT implements INBT {

    private final Map<String, INBT> tagMap;

    public static final INBTType<ObjectNBT> TYPE = new INBTType<ObjectNBT>() {
        @Override
        public ObjectNBT readNBT(DataInput input, int depth, NBTSizeTracker accounter) throws IOException {
            accounter.read(Long.MAX_VALUE);
            Map<String, INBT> map = Maps.newHashMap();
            return new ObjectNBT(map);
        }

        @Override
        @NotNull
        public String getName() {
            return "OBJECT";
        }

        @Override
        @NotNull
        public String getTagName() {
            return "TAG_Object";
        }
    };

    public ObjectNBT(Map<String, INBT> tagMap) {
        this.tagMap = tagMap;
    }

    @Override
    public void write(DataOutput output) throws IOException {

    }

    @Override
    public byte getId() {
        return 0;
    }

    @Override
    @NotNull
    public INBTType<?> getType() {
        return TYPE;
    }

    @Override
    @NotNull
    public INBT copy() {
        return new ObjectNBT(tagMap);
    }

    @Override
    public ITextComponent toFormattedComponent(String indentation, int indentDepth) {
        return null;
    }

    public <K,V> void putMap (String key, Map<K,V> map) {
        this.tagMap.put(key, (INBT) map);
    }

    public <O> void putTreeNode (String key, TreeNode<O> node) {
        this.tagMap.put(key, (INBT) node);
    }

    public void putRunnable (String key, Runnable runnable) {
        this.tagMap.put(key, (INBT) runnable);
    }
}
