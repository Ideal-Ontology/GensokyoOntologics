package github.thelawf.gensokyoontology.api.util.expression;

import com.google.gson.JsonObject;
import github.thelawf.gensokyoontology.api.util.tree.ITreeNode;
import github.thelawf.gensokyoontology.api.util.tree.TreeNode;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class Expression<T> extends SerializableExpression<T> {
    @Override
    public String toJsonString() {
        return null;
    }

    @Override
    public JsonObject toJson() {
        return null;
    }

    @Override
    public IExpression fromJson(JsonObject jsonObject) {
        return null;
    }

    @Override
    public IExpression fromJsonString(String json) {
        return null;
    }

    @Override
    public CompoundNBT serialize() {
        return null;
    }

    @Override
    public IExpression deserialize(CompoundNBT nbt) {
        return null;
    }

    @Override
    public T get() {
        return null;
    }

    @Override
    public void addChild(TreeNode<T> child) {

    }

    @Override
    public boolean contains(String name) {
        return false;
    }

    @Override
    public boolean hasName(String name) {
        return false;
    }

    @Override
    public boolean hasChild() {
        return false;
    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public boolean hasAncestors() {
        return false;
    }

    @Override
    public ITreeNode<T> getNode(String name) {
        return null;
    }
}
