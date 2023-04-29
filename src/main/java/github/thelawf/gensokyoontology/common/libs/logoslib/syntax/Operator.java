package github.thelawf.gensokyoontology.common.libs.logoslib.syntax;

import com.google.gson.JsonObject;
import github.thelawf.gensokyoontology.api.IOperatable;

public abstract class Operator<O> implements IOperatable {
    JsonObject json;
}
