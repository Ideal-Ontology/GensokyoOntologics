package github.thelawf.gensokyoontology.common.nbt;

import github.thelawf.gensokyoontology.common.item.script.ScriptBuilderItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GSKONBTUtil {

    public static boolean hasItemStack(PlayerEntity player, Item item) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.isItemEqual(new ItemStack(item))) {
                return true;
            }
        }
        return false;
    }

    public static CompoundNBT wrap(Supplier<CompoundNBT> supplier) {
        return supplier.get();
    }

    public static int operateAndGet(BiFunction<IntNBT, IntNBT, IntNBT> action, IntNBT left, IntNBT right) {
        return action.apply(left, right).getInt();
    }


    public static CompoundNBT getNonNullTag(ItemStack stack, String key) {
        if (stack.getTag() == null) return new CompoundNBT();
        return stack.getTag();
    }

    public static CompoundNBT getNonNullTags(ItemStack stack, String... keys) {
        if (!hasAndContainsTags(stack, keys)) return new CompoundNBT();
        return stack.getTag();
    }

    public static boolean hasAndContainsTag(ItemStack stack, String key) {
        if (stack.getTag() == null) return false;
        return stack.getTag().contains(key);
    }

    public static boolean hasAndContainsTags(ItemStack stack, String... keys) {
        CompoundNBT nbt = stack.getTag();
        if (nbt == null) return false;
        return Arrays.stream(keys).allMatch(nbt::contains);
    }

    public static int getFirstItemIndex(PlayerEntity player, Item item) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.isItemEqual(new ItemStack(item))) {
                return i;
            }
        }
        return -1;
    }

    @Nonnull
    public static BlockPos readPosFromStack(ItemStack stack, String nbtKey) {
        if (stack.getTag() != null && stack.getTag().contains(nbtKey)) {
            int[] pos = stack.getTag().getIntArray(nbtKey);
            BlockPos blockPos = new BlockPos(pos[0], pos[1], pos[2]);
            Vector3d posVec = blockPosToVec(blockPos);
            return blockPos;
        }
        return BlockPos.ZERO;
    }

    public static CompoundNBT removeAllChildNBT(CompoundNBT nbt) {
        if (nbt != null) {
            for (String key : nbt.keySet()) {
                nbt.remove(key);
            }
        }
        return nbt;
    }

    public static CompoundNBT removeAllChildNBT(ItemStack stack, CompoundNBT nbt) {
        if (stack.getTag() == nbt && nbt != null) {
            for (String key : nbt.keySet()) {
                nbt.remove(key);
                stack.setTag(nbt);
            }
            return nbt;
        }
        return new CompoundNBT();
    }

    public static CompoundNBT putStory() {
        String key = "story";
        String storyText = "";
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(key, storyText);
        return nbt;
    }

    public static CompoundNBT putRandomStory(List<String> stories) {
        String key = "story";
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(key, stories.get(new Random().nextInt(stories.size())));
        return nbt;
    }

    public static <T> CompoundNBT putStoryIf(Predicate<T> predicate, T t) {
        if (predicate.test(t)) {
            String key = "story";
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString(key, predicate.toString());
            return nbt;
        }
        return new CompoundNBT();
    }

    public static ItemStack removeAllChildNBTFromStack(ItemStack stack, CompoundNBT nbt) {
        if (stack.getTag() == nbt && nbt != null) {
            for (String key : nbt.keySet()) {
                nbt.remove(key);
                stack.setTag(nbt);
            }
            return stack;
        }
        return new ItemStack(stack.getItem());
    }

    public static Vector3d blockPosToVec(BlockPos blockPos) {
        return new Vector3d(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static BlockPos vecToBlockPos(Vector3d vector3d) {
        return new BlockPos(new Vector3i(vector3d.x, vector3d.y, vector3d.z));
    }

    public static boolean containsPrimitiveType(CompoundNBT nbt) {
        if (!nbt.contains("type")) return false;
        switch (nbt.getString("type")) {
            case "int":
            case "long":
            case "float":
            case "double":
            case "string":
            case "boolean":
                return true;
            default:
                return false;
        }
    }

    public static INBT getFromValue(CompoundNBT nbt) {
        if (nbt.get("value") instanceof NumberNBT) return getAsNumber(nbt);
        else if (nbt.get("value") instanceof StringNBT) return nbt.get("value");
        else if (nbt.get("value") instanceof ByteNBT) {
            ByteNBT byteNBT = (ByteNBT) nbt.get("value");
            if (byteNBT != null) {
                return byteNBT.getInt() == 0 ? StringNBT.valueOf("false") : StringNBT.valueOf("true");
            }
        }
        else if (nbt.get("value") instanceof CompoundNBT) {
            return nbt.get("value");
        }
        return new CompoundNBT();
    }

    public static NumberNBT getAsNumber(CompoundNBT nbt) {
        if (nbt.get("value") instanceof IntNBT) {
            return (IntNBT) nbt.get("value");
        }
        else if (nbt.get("value") instanceof LongNBT) {
            return (LongNBT) nbt.get("value");
        }
        else if (nbt.get("value") instanceof FloatNBT) {
            return (FloatNBT) nbt.get("value");
        }
        else if (nbt.get("value") instanceof DoubleNBT) {
            return (DoubleNBT) nbt.get("value");
        }
        return IntNBT.valueOf(0);
    }

    public static boolean isNumberType(CompoundNBT nbt) {
        return nbt.getString("type").equals("int") ||
                nbt.getString("type").equals("long") ||
                nbt.getString("type").equals("float") ||
                nbt.getString("type").equals("double");
    }

    public static NumberNBT getNumberFromKey(CompoundNBT nbt, String key) {
        if (nbt.get(key) instanceof IntNBT) {
            return (IntNBT) nbt.get(key);
        }
        else if (nbt.get(key) instanceof LongNBT) {
            return (LongNBT) nbt.get(key);
        }
        else if (nbt.get(key) instanceof FloatNBT) {
            return (FloatNBT) nbt.get(key);
        }
        else if (nbt.get(key) instanceof DoubleNBT) {
            return (DoubleNBT) nbt.get(key);
        }
        return IntNBT.valueOf(0);
    }

    public static INBT getFromKey(CompoundNBT nbt, String key) {
        if (nbt.get(key) instanceof NumberNBT) {
            return getNumberFromKey(nbt, key);
        }
        else if (nbt.get(key) instanceof StringNBT) {
            return nbt.get(key);
        }
        else if (nbt.get(key) instanceof CompoundNBT) {
            return nbt.get(key);
        }
        return new CompoundNBT();
    }

    public static CompoundNBT castToCompound(INBT inbt) {
        return inbt instanceof CompoundNBT ? (CompoundNBT) inbt : new CompoundNBT();
    }

    public static String getType(CompoundNBT nbt) {
        if (containsPrimitiveType(nbt)) return nbt.getString("type");
        return "undefined";
    }

    public static boolean containsAllowedType(CompoundNBT nbt) {
        if (!nbt.contains("type")) return false;
        if (containsPrimitiveType(nbt)) return false;
        switch (nbt.getString("type")) {
            case "vector3d":
            case "world":
            case "danmaku":
            case "vector3d_list":
            case "danmaku_list":
                return true;
            default:
                return false;
        }
    }

    public static List<String> getMemberValues(CompoundNBT nbt) {
        List<String> values = new ArrayList<>();
        if (containsAllowedType(nbt)) {
            switch (nbt.getString("type")) {
                case "vector3d":
                    return getVector3dValues(nbt);
                case "world":
                case "danmaku":
                    CompoundNBT danmakuNBT = getCompoundValue(nbt);
                    values.add(String.valueOf(danmakuNBT.getInt("danmakuType")));
                    values.add(String.valueOf(danmakuNBT.getInt("danmakuColor")));
                default:
                    return values;
            }
        }
        return values;
    }

    public static List<String> getVector3dValues(CompoundNBT nbt) {
        List<String> values = new ArrayList<>();
        CompoundNBT vectorNBT = getCompoundValue(nbt);
        values.add(ScriptBuilderItem.NAME_HIGHLIGHT + "X: " + ScriptBuilderItem.RESET_HIGHLIGHT +
                ScriptBuilderItem.VALUE_HIGHLIGHT + getNumberFromKey(vectorNBT, "x").getDouble());
        values.add(ScriptBuilderItem.NAME_HIGHLIGHT + "Y: " + ScriptBuilderItem.RESET_HIGHLIGHT +
                ScriptBuilderItem.VALUE_HIGHLIGHT + getNumberFromKey(vectorNBT, "y").getDouble());
        values.add(ScriptBuilderItem.NAME_HIGHLIGHT + "Z: " + ScriptBuilderItem.RESET_HIGHLIGHT +
                ScriptBuilderItem.VALUE_HIGHLIGHT + getNumberFromKey(vectorNBT, "z").getDouble());
        // DataCommand
        return values;
    }

    public static CompoundNBT getCompoundValue(CompoundNBT nbt) {
        if (!nbt.contains("value")) return new CompoundNBT();
        if (containsAllowedType(nbt) && nbt.get("value") instanceof CompoundNBT) {
            return (CompoundNBT) nbt.get("value");
        }
        else return new CompoundNBT();
    }

    public static String getMemberValueAsString(CompoundNBT objectNBT, String memberKey) {
        if (!objectNBT.contains("value")) return new CompoundNBT().toString();
        if (containsAllowedType(objectNBT) && objectNBT.get("value") instanceof CompoundNBT) {
            CompoundNBT compound = (CompoundNBT) objectNBT.get("value");
            if (compound != null) {
                return getFromKey(compound, memberKey).toString();
            }
        }
        else return new CompoundNBT().toString();
        return new CompoundNBT().toString();
    }

    public static String getMemberValueWithKey(CompoundNBT objectNBT, String memberKey, String keyText) {
        if (!objectNBT.contains("value")) return new CompoundNBT().toString();
        if (containsAllowedType(objectNBT) && objectNBT.get("value") instanceof CompoundNBT) {
            CompoundNBT compound = (CompoundNBT) objectNBT.get("value");
            if (compound != null) {
                return keyText + getFromKey(compound, memberKey).toString();
            }
        }
        else return new CompoundNBT().toString();
        return new CompoundNBT().toString();
    }

    public static String getMemberValueWithFormat(CompoundNBT nbt, String memberKey, String keyText) {
        if (!nbt.contains("value")) return new CompoundNBT().toString();
        if (containsAllowedType(nbt) && nbt.get("value") instanceof CompoundNBT) {
            CompoundNBT compound = (CompoundNBT) nbt.get("value");
            if (compound != null) {
                return ScriptBuilderItem.NAME_HIGHLIGHT + keyText + ScriptBuilderItem.RESET_HIGHLIGHT +
                        ScriptBuilderItem.VALUE_HIGHLIGHT + getFromKey(compound, memberKey).toString();
            }
        }
        else return new CompoundNBT().toString();
        return new CompoundNBT().toString();
    }

    public static ListNBT getListValue(CompoundNBT nbt) {
        if (nbt.contains("value")) return new ListNBT();
        if (containsAllowedType(nbt) && nbt.get("value") instanceof ListNBT) {
            return (ListNBT) nbt.get("value");
        }
        else return new ListNBT();
    }

    public static List<CompoundNBT> getListCompound(ListNBT listNBT) {
        List<CompoundNBT> nbtList = new ArrayList<>();
        if (listNBT.isEmpty()) return nbtList;
        listNBT.forEach(inbt -> {
            if (inbt instanceof CompoundNBT) {
                nbtList.add((CompoundNBT) inbt);
            }
        });
        return nbtList;
    }
}
