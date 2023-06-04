package github.thelawf.gensokyoontology.common.util;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.INBTType;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ObjectNBT implements INBT {

    public static final INBTType<ObjectNBT> TYPE = new INBTType<ObjectNBT>() {
        @Override
        public ObjectNBT readNBT(DataInput input, int depth, NBTSizeTracker accounter) throws IOException {
            accounter.read(Long.MAX_VALUE);
            return new ObjectNBT();
        }

        @Override
        @NotNull
        public String getName() {
            return "OBJECT";
        }

        @Override
        @NotNull
        public String getTagName() {
            return "TAG_object";
        }
    };

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
        return new ObjectNBT();
    }

    @Override
    public ITextComponent toFormattedComponent(String indentation, int indentDepth) {
        return null;
    }
}
