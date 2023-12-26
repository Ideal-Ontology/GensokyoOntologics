package github.thelawf.gensokyoontology.api.util.expression;

import com.google.gson.JsonObject;
import github.thelawf.gensokyoontology.api.util.tree.ITreeNode;
import net.minecraft.nbt.CompoundNBT;

public abstract class SerializableExpression<T> implements IExpression, ITreeNode<T> {

    public abstract String toJsonString();
    public abstract JsonObject toJson();
    public abstract IExpression fromJson(JsonObject jsonObject);
    public abstract IExpression fromJsonString(String json);

    public abstract CompoundNBT serialize();
    public abstract IExpression deserialize(CompoundNBT nbt);
}
