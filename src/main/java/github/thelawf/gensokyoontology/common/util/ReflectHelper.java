package github.thelawf.gensokyoontology.common.util;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.*;

public class ReflectHelper {


    public static Object getFieldValue(Object obj, String fieldName) throws IllegalAccessException {
        Field field = ObfuscationReflectionHelper.findField(obj.getClass(), fieldName);
        return field.get(obj);
    }

    @SuppressWarnings("unchecked")
    public static <O> O getFieldValue(Object obj, String fieldName, Class<O> tarClass, Class<Object> genericClass) throws IllegalAccessException {
        Field field = ObfuscationReflectionHelper.findField(obj.getClass(), "blocks");
        return isTypeEqual(field, tarClass, genericClass) ? (O) field.get(obj) : (O) obj;
    }

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

}
