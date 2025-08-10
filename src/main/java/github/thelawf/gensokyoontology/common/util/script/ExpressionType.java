package github.thelawf.gensokyoontology.common.util.script;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import github.thelawf.gensokyoontology.api.Tree;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Util;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class ExpressionType<E extends IExpressionType> implements IExpressionType {

    // public static final Codec<IExpressionType> CODEC = Expressions.REGISTRY.byNameCodec().dispatch(
    //         "expression", IExpressionType::getExp, IExpressionType::type);
//
    // public static final RegistryKeyCodec<RegistryFriendlyByteBuf, IExpressionType> STREAM_CODEC = ByteBufCodecs.registry(
    //         Expressions.KEY).dispatch(IExpressionType::getExp, type -> type.getExp().streamCodec());

    private ExpressionType<? extends IExpressionType> expressionType;
    private E e;
    // public static final Map<String, ?> METHODS = Util.make(() -> {
    //     // var map = new HashMap<String, Constructor<?>>();
    //     // map.put(AccessibleMethod.DANMAKU_INIT.methodName, findConstructor(Danmaku.class, Level.class, Item.class, ClosureExpression.class));
    //     // map.put(AccessibleMethod.VEC3_INIT.methodName, findConstructor(Vec3.class, double.class, double.class, double.class));
    //     // return map;
    // });


    public Tree<ExpressionType<E>> expTree = new Tree<>();

    private static Constructor<?> findConstructor(Class<?> c, Class<?>... params) {
        return ObfuscationReflectionHelper.findConstructor(c, params);
    }

    private static Method findMethod(Class<?> c, String name, Class<?>... params) {
        return ObfuscationReflectionHelper.findMethod(c, name, params);
    }

    public abstract ExpressionType<E> get();


    // public JsonElement toJson() {
    //     DataResult<JsonElement> dataResult = CODEC.encodeStart(JsonOps.INSTANCE, this);
    //     return dataResult.result().get();
    // }

}
