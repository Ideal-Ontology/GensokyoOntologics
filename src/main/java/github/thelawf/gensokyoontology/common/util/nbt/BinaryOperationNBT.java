package github.thelawf.gensokyoontology.common.util.nbt;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.StringNBT;

public class BinaryOperationNBT extends CompoundNBT {
    private final StringNBT operation;
    private final CompoundNBT left = new CompoundNBT();
    private final CompoundNBT right = new CompoundNBT();

    public BinaryOperationNBT(String operation, CompoundNBT leftEntry, CompoundNBT rightEntry) {
        this.operation = StringNBT.valueOf(operation);
        this.left.put("left", leftEntry);
        this.right.put("right", rightEntry);
    }

    public StringNBT getOperation() {
        return this.operation;
    }

    public CompoundNBT getLeft() {
        return this.left;
    }

    public CompoundNBT getRight() {
        return this.right;
    }
}
