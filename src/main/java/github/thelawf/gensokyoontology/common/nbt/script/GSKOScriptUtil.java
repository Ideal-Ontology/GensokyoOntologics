package github.thelawf.gensokyoontology.common.nbt.script;

import github.thelawf.gensokyoontology.common.nbt.GSKONBTUtil;
import net.minecraft.nbt.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Predicate;

public class GSKOScriptUtil {

    /**
     * 传入一个顶层的表达式，并从顶层表达式的 value 字段中获取具体的表达式操作，在这里就是通过反射获取 CompoundNBT 的 tagMap，
     * 获取之后便返回该 tagMap，以便其它方法对 tagMap 进行递归遍历。（注意：顶层表达式在这里指 CompoundNBT 结构树的根节点，
     * 即该 CompoundNBT 之上没有任何带有 "type" 字段的父标签或者父节点）
     * <p> operationNBT 示例：
     * <pre>
     * {@code
     * {
     *  "type": "binary_operation",
     *  "name": "plus",
     *  "value": {
     *     "operation": "multiple"
     *     "left":{
     *        "type": "int",
     *        "value": 3,
     *     }
     *     "right":{
     *        "type": "int",
     *        "value": 5,
     *        }
     *     }
     *  }
     * }
     * </pre>
     * 更多示例请参考tick.json。
     * @param operationNBT 顶层表达式 (Top Binary Operation)
     * @return 顶层表达式所包含的标签映射表 (All the Mappings in the Top Binary Operation)
     */
    @SuppressWarnings("unchecked")
    public static Map<String, INBT> getOptValueTagMap(CompoundNBT operationNBT) throws InvocationTargetException, IllegalAccessException {
        INBT value = getScriptValue(operationNBT);
        Method method = ObfuscationReflectionHelper.findMethod(CompoundNBT.class, "getTagMap");
        Object mapOBJ = method.invoke(value);
        if (mapOBJ instanceof Map) return (Map<String, INBT>) mapOBJ;
        return new HashMap<>();
    }

    /**
     * <p>递归遍历顶层表达式的每个节点，由于：</p>
     * <p>operationNBT 必然包含 "type" 和 "value" 字段，可能包含 "condition", "branch" 字段， 则：</p>
     * <p>1. 获取 "type" 字段，即获取该表达式的类型，如果该表达式为：</p>
     * <p>&emsp(1) 二元运算式</p>
     * <p>那么，获取 "value" 字段，再获取value字段下的左值和右值，如果左值或右值中存在着同样的二元表达式，则
     * 在此条件分支中再次调用本函数，并将左值或者右值的NBT数据再次传给本函数。</p>
     */
    public static Stack<CompoundNBT> traverseOpt(CompoundNBT parent, BinaryOperation operation, Stack<CompoundNBT> stack) throws InvocationTargetException, IllegalAccessException {
        // 这个 tagMapOfOpt 是顶层表达式的 value 字段下的所有标签表。
        // 而 valueNBT 则是顶层表达式的 value 字段下的NBT存储类。
        Map<String, INBT> tagMapOfOpt = getOptValueTagMap(parent);
        for (Map.Entry<String, INBT> entry : tagMapOfOpt.entrySet()) {

            Predicate<Map.Entry<String, INBT>> isAnOperation = e ->
                    Arrays.stream(BinaryOperation.values()).map(bo -> bo.key)
                            .anyMatch(s -> e.getValue().toString().equals(s));

            if (entry.getKey().equals("left") || entry.getKey().equals("right") && entry.getValue() instanceof CompoundNBT) {
                CompoundNBT nbt = (CompoundNBT) entry.getValue();
                traverseOpt(nbt, BinaryOperation.valueOf(tagMapOfOpt.get("operation").getString()), stack);
            }
            if (entry.getKey().equals("ref") || !isAnOperation.test(entry)) {
                CompoundNBT nbt = new CompoundNBT();
                nbt.putString("operation", operation.key);

                stack.push(parent);
                stack.push(nbt);
            }
        }
        return stack;
    }
    
    public static CompoundNBT tryCalculateIterate(Stack<CompoundNBT> stack) {

        CompoundNBT left = stack.pop();
        CompoundNBT opt = stack.pop();
        CompoundNBT right = stack.pop();

        CompoundNBT result = new CompoundNBT();
        result.putString("type", left.getString("type"));

        stack.push(putIf(result, GSKONBTUtil.isNumberType(left) && GSKONBTUtil.isNumberType(right) ?
                calculateNumber(left, BinaryOperation.valueOf(opt.getString("operation")), right) : 0));
        if (stack.size() % 3 == 1) {
            tryCalculateIterate(stack);
        }
        return stack.pop();
    }

    public static CompoundNBT get(Stack<CompoundNBT> stack) {
        return tryCalculateIterate(stack);
    }

    public static Number tryCalculate(CompoundNBT operationNBT) {
        try {
            Stack<CompoundNBT> result = new Stack<>();
            Stack<CompoundNBT> stack = traverseOpt(operationNBT, BinaryOperation.NONE, result);
            CompoundNBT left = stack.pop();
            CompoundNBT opt = stack.pop();
            CompoundNBT right = stack.pop();

            return GSKONBTUtil.isNumberType(left) && GSKONBTUtil.isNumberType(right) ?
                    calculateNumber(left, BinaryOperation.valueOf(opt.getString("operation")), right) : 0;

        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Number calculateNumber(CompoundNBT left, BinaryOperation operation, CompoundNBT right) {
        switch (operation) {
            case NONE:
            default:
                return 0;
            case PLUS:
                return plusIf(left, right);
            case SUBTRACT:
                return subtractIf(left, right);
            case MULTIPLE:
                return mulIf(left, right);
            case DIVIDE:
                return divIf(left, right);
            case MODULUS:
                return modIf(left, right);

        }
    }

    public static CompoundNBT putIf(CompoundNBT nbt, Number number) {
        CompoundNBT compound = new CompoundNBT();
        if (isType(nbt, "int")) compound.putInt("value", (int) number);
        if (isType(nbt, "long")) compound.putLong("value", (int) number);
        if (isType(nbt, "float")) compound.putFloat("value", (int) number);
        if (isType(nbt, "double")) compound.putDouble("value", (int) number);
        return compound;
    }


    public static Number plusIf(CompoundNBT left, CompoundNBT right) {
        if (left.getString("type").equals("int") && left.getString("type").equals("int")) {
            return getAsInt(left) + getAsInt(right);
        }
        else if (left.getString("type").equals("float") || left.getString("type").equals("float")) {
            return getAsFloat(left) + getAsFloat(right);
        }
        else if (left.getString("type").equals("double") && left.getString("type").equals("double")) {
            return getAsDouble(left) + getAsDouble(right);
        }
        else if (left.getString("type").equals("long") || left.getString("type").equals("long")) {
            return getAsLong(left) + getAsLong(right);
        }
        return 0;
    }

    public static Number subtractIf(CompoundNBT left, CompoundNBT right) {
        if (left.getString("type").equals("int") && left.getString("type").equals("int")) {
            return getAsInt(left) - getAsInt(right);
        }
        else if (left.getString("type").equals("float") || left.getString("type").equals("float")) {
            return getAsFloat(left) - getAsFloat(right);
        }
        else if (left.getString("type").equals("double") && left.getString("type").equals("double")) {
            return getAsDouble(left) - getAsDouble(right);
        }
        else if (left.getString("type").equals("long") || left.getString("type").equals("long")) {
            return getAsLong(left) - getAsLong(right);
        }
        return 0;
    }

    public static Number mulIf(CompoundNBT left, CompoundNBT right) {
        if (left.getString("type").equals("int") && left.getString("type").equals("int")) {
            return getAsInt(left) * getAsInt(right);
        }
        else if (left.getString("type").equals("float") || left.getString("type").equals("float")) {
            return getAsFloat(left) * getAsFloat(right);
        }
        else if (left.getString("type").equals("double") && left.getString("type").equals("double")) {
            return getAsDouble(left) * getAsDouble(right);
        }
        else if (left.getString("type").equals("long") || left.getString("type").equals("long")) {
            return getAsLong(left) * getAsLong(right);
        }
        return 0;
    }

    public static Number divIf(CompoundNBT left, CompoundNBT right) {
        if (left.getString("type").equals("int") && left.getString("type").equals("int")) {
            return getAsInt(left) / getAsInt(right);
        }
        else if (left.getString("type").equals("float") || left.getString("type").equals("float")) {
            return getAsFloat(left) / getAsFloat(right);
        }
        else if (left.getString("type").equals("double") && left.getString("type").equals("double")) {
            return getAsDouble(left) / getAsDouble(right);
        }
        else if (left.getString("type").equals("long") || left.getString("type").equals("long")) {
            return getAsLong(left) / getAsLong(right);
        }
        return 0;
    }

    public static Number modIf(CompoundNBT left, CompoundNBT right) {
        if (left.getString("type").equals("int") && left.getString("type").equals("int")) {
            return getAsInt(left) % getAsInt(right);
        }
        else if (left.getString("type").equals("float") || left.getString("type").equals("float")) {
            return getAsFloat(left) % getAsFloat(right);
        }
        else if (left.getString("type").equals("double") && left.getString("type").equals("double")) {
            return getAsDouble(left) % getAsDouble(right);
        }
        else if (left.getString("type").equals("long") || left.getString("type").equals("long")) {
            return getAsLong(left) % getAsLong(right);
        }
        return 0;
    }

    public static float getAsFloat(CompoundNBT nbt) {
        if (nbt.get("value") instanceof IntNBT) {
            return (float) nbt.getInt("value");
        }
        else if (nbt.get("value") instanceof LongNBT) {
            return (float) nbt.getLong("value");
        }
        else if (nbt.get("value") instanceof FloatNBT) {
            return nbt.getFloat("value");
        }
        else if (nbt.get("value") instanceof DoubleNBT) {
            return (float) nbt.getDouble("value");
        }
        return 0f;
    }

    public static int getAsInt(CompoundNBT nbt) {
        if (nbt.get("value") instanceof IntNBT) {
            return nbt.getInt("value");
        }
        else if (nbt.get("value") instanceof LongNBT) {
            return (int) nbt.getLong("value");
        }
        else if (nbt.get("value") instanceof FloatNBT) {
            return (int) nbt.getFloat("value");
        }
        else if (nbt.get("value") instanceof DoubleNBT) {
            return (int) nbt.getDouble("value");
        }
        return 0;
    }

    public static long getAsLong(CompoundNBT nbt) {
        if (nbt.get("value") instanceof IntNBT) {
            return nbt.getInt("value");
        }
        else if (nbt.get("value") instanceof LongNBT) {
            return nbt.getLong("value");
        }
        else if (nbt.get("value") instanceof FloatNBT) {
            return (long) nbt.getFloat("value");
        }
        else if (nbt.get("value") instanceof DoubleNBT) {
            return (long) nbt.getDouble("value");
        }
        return 0L;
    }

    private static boolean isType(CompoundNBT nbt, String typeStr) {
        return nbt.contains("type") && nbt.getString("type").equals(typeStr);
    }

    public static double getAsDouble(CompoundNBT nbt) {
        if (nbt.get("value") instanceof IntNBT) {
            return nbt.getInt("value");
        }
        else if (nbt.get("value") instanceof LongNBT) {
            return (double) nbt.getLong("value");
        }
        else if (nbt.get("value") instanceof FloatNBT) {
            return nbt.getFloat("value");
        }
        else if (nbt.get("value") instanceof DoubleNBT) {
            return nbt.getDouble("value");
        }
        return 0D;
    }

    public static Vector3d calculateV3d(CompoundNBT left, BinaryOperation operation, CompoundNBT right) {
        return new Vector3d(0,0,0);
    }

    public static CompoundNBT getScriptValue(CompoundNBT operationNBT) {
        INBT inbt = GSKONBTUtil.getFromKey(operationNBT, "value");
        if (inbt instanceof CompoundNBT)
            return  (CompoundNBT) inbt;
        return new CompoundNBT();
    }

    public static CompoundNBT getOptLeft(CompoundNBT operationNBT) {
        if (getScriptValue(operationNBT).contains("left")) {
            INBT left = GSKONBTUtil.getFromKey(getScriptValue(operationNBT), "left");
            if (left instanceof CompoundNBT)
                return  (CompoundNBT) left;
        }
        return new CompoundNBT();
    }

    public static CompoundNBT getOptRight(CompoundNBT operationNBT) {
        if (getScriptValue(operationNBT).contains("right")) {
            INBT left = GSKONBTUtil.getFromKey(getScriptValue(operationNBT), "right");
            if (left instanceof CompoundNBT)
                return  (CompoundNBT) left;
        }
        return new CompoundNBT();
    }
}
