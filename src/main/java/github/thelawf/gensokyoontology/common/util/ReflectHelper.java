package github.thelawf.gensokyoontology.common.util;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.*;
import java.util.Optional;
import java.util.stream.Stream;

public class ReflectHelper {

    public static <D extends GenericDeclaration> boolean areTypeParametersEqual(Class<?> objClass, Class<?>... typeClasses) {
        int match = 0;
        for (TypeVariable<? extends Class<?>> variable : objClass.getTypeParameters()) {
            for (Class<?> typeClass : typeClasses) {
                match += variable.getClass().getName().equals(typeClass.getName()) ? 1 : 0;
            }
        }
        return match == objClass.getTypeParameters().length;
    }

    public static <O,G> boolean isTypeEqual(Field field, Class<O> objClass, Class<G> genericClass) {
        if (field.getType() == objClass) {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                Type[] typeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
                if (typeArguments.length == 1 && typeArguments[0] == genericClass) {
                    return true;
                }
            }
        }
        return false;
    }

    // @SuppressWarnings("unchecked")
    public static <T> Object getPublicStatic(T t, String name) throws IllegalAccessException {
        Optional<Field> optional = Stream.of(t.getClass().getDeclaredFields()).filter(field -> {
            int modifiers = field.getModifiers();
            // 判断该属性是否是public final static 类型的
            // 如果想过去其它的,具体可以参考 Modifier 这个类里面的修饰符解码
            boolean flag = (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers));
            return flag && field.getName().equals(name);
        }).findAny();
        if (optional.isPresent()) {
            return optional.get().get(t);
        }
        return null;
    }
}
