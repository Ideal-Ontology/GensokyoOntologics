package github.thelawf.gensokyoontology.common.nbt;

import github.thelawf.gensokyoontology.common.item.script.ScriptBuilderItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GSKOTagUtil {

    public static boolean hasItemStack(PlayerEntity player, Item item) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.isItemEqual(new ItemStack(item))) {
                return true;
            }
        }
        return false;
    }

    public static CompoundTag wrap(Supplier<CompoundTag> supplier) {
        return supplier.get();
    }

    public static int operateAndGet(BiFunction<IntTag, IntTag, IntTag> action, IntTag left, IntTag right) {
        return action.apply(left, right).getAsInt();
    }


    public static CompoundTag getNonNullTag(ItemStack stack, String key) {
        if (stack.getTag() == null) return new CompoundTag();
        return stack.getTag();
    }

    public static CompoundTag getNonNullTags(ItemStack stack, String... keys) {
        if (!hasAndContainsTags(stack, keys)) return new CompoundTag();
        return stack.getTag();
    }

    public static boolean hasAndContainsTag(ItemStack stack, String key) {
        if (stack.getTag() == null) return false;
        return stack.getTag().contains(key);
    }

    public static boolean hasAndContainsTags(ItemStack stack, String... keys) {
        CompoundTag nbt = stack.getTag();
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

    public static CompoundTag removeAllChildTag(CompoundTag nbt) {
        if (nbt != null) {
            for (String key : nbt.keySet()) {
                nbt.remove(key);
            }
        }
        return nbt;
    }

    public static CompoundTag removeAllChildTag(ItemStack stack, CompoundTag nbt) {
        if (stack.getTag() == nbt && nbt != null) {
            for (String key : nbt.keySet()) {
                nbt.remove(key);
                stack.setTag(nbt);
            }
            return nbt;
        }
        return new CompoundTag();
    }

    public static CompoundTag putStory() {
        String key = "story";
        String storyText = "";
        CompoundTag nbt = new CompoundTag();
        nbt.putString(key, storyText);
        return nbt;
    }

    public static CompoundTag putRandomStory(List<String> stories) {
        String key = "story";
        CompoundTag nbt = new CompoundTag();
        nbt.putString(key, stories.get(new Random().nextInt(stories.size())));
        return nbt;
    }

    public static <T> CompoundTag putStoryIf(Predicate<T> predicate, T t) {
        if (predicate.test(t)) {
            String key = "story";
            CompoundTag nbt = new CompoundTag();
            nbt.putString(key, predicate.toString());
            return nbt;
        }
        return new CompoundTag();
    }

    public static ItemStack removeAllChildTagFromStack(ItemStack stack, CompoundTag nbt) {
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

    public static boolean containsPrimitiveType(CompoundTag nbt) {
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

    public static Tag getFromValue(CompoundTag nbt) {
        if (nbt.get("value") instanceof NumericTag) return getAsNumber(nbt);
        else if (nbt.get("value") instanceof StringTag) return nbt.get("value");
        else if (nbt.get("value") instanceof ByteTag) {
            ByteTag byteTag = (ByteTag) nbt.get("value");
            if (byteTag != null) {
                return byteTag.getInt() == 0 ? StringTag.valueOf("false") : StringTag.valueOf("true");
            }
        }
        else if (nbt.get("value") instanceof CompoundTag) {
            return nbt.get("value");
        }
        return new CompoundTag();
    }

    public static NumericTag getAsNumber(CompoundTag nbt) {
        if (nbt.get("value") instanceof IntTag) {
            return (IntTag) nbt.get("value");
        }
        else if (nbt.get("value") instanceof LongTag) {
            return (LongTag) nbt.get("value");
        }
        else if (nbt.get("value") instanceof FloatTag) {
            return (FloatTag) nbt.get("value");
        }
        else if (nbt.get("value") instanceof DoubleTag) {
            return (DoubleTag) nbt.get("value");
        }
        return IntTag.valueOf(0);
    }

    public static boolean isNumberType(CompoundTag nbt) {
        return nbt.getString("type").equals("int") ||
                nbt.getString("type").equals("long") ||
                nbt.getString("type").equals("float") ||
                nbt.getString("type").equals("double");
    }

    public static NumericTag getNumberFromKey(CompoundTag nbt, String key) {
        if (nbt.get(key) instanceof IntTag) {
            return (IntTag) nbt.get(key);
        }
        else if (nbt.get(key) instanceof LongTag) {
            return (LongTag) nbt.get(key);
        }
        else if (nbt.get(key) instanceof FloatTag) {
            return (FloatTag) nbt.get(key);
        }
        else if (nbt.get(key) instanceof DoubleTag) {
            return (DoubleTag) nbt.get(key);
        }
        return IntTag.valueOf(0);
    }

    public static Tag getFromKey(CompoundTag nbt, String key) {
        if (nbt.get(key) instanceof NumericTag) {
            return getNumberFromKey(nbt, key);
        }
        else if (nbt.get(key) instanceof StringTag) {
            return nbt.get(key);
        }
        else if (nbt.get(key) instanceof CompoundTag) {
            return nbt.get(key);
        }
        return new CompoundTag();
    }

    public static CompoundTag castToCompound(Tag inbt) {
        return inbt instanceof CompoundTag ? (CompoundTag) inbt : new CompoundTag();
    }

    public static String getType(CompoundTag nbt) {
        if (containsPrimitiveType(nbt)) return nbt.getString("type");
        return "undefined";
    }

    public static boolean containsAllowedType(CompoundTag nbt) {
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

    public static List<String> getMemberValues(CompoundTag nbt) {
        List<String> values = new ArrayList<>();
        if (containsAllowedType(nbt)) {
            switch (nbt.getString("type")) {
                case "vector3d":
                    return getVector3dValues(nbt);
                case "world":
                case "danmaku":
                    CompoundTag danmakuTag = getCompoundValue(nbt);
                    values.add(String.valueOf(danmakuTag.getInt("danmakuType")));
                    values.add(String.valueOf(danmakuTag.getInt("danmakuColor")));
                default:
                    return values;
            }
        }
        return values;
    }

    public static List<String> getVector3dValues(CompoundTag nbt) {
        List<String> values = new ArrayList<>();
        CompoundTag vectorTag = getCompoundValue(nbt);
        values.add(ScriptBuilderItem.NAME_HIGHLIGHT + "X: " + ScriptBuilderItem.RESET_HIGHLIGHT +
                ScriptBuilderItem.VALUE_HIGHLIGHT + getNumberFromKey(vectorTag, "x").getDouble());
        values.add(ScriptBuilderItem.NAME_HIGHLIGHT + "Y: " + ScriptBuilderItem.RESET_HIGHLIGHT +
                ScriptBuilderItem.VALUE_HIGHLIGHT + getNumberFromKey(vectorTag, "y").getDouble());
        values.add(ScriptBuilderItem.NAME_HIGHLIGHT + "Z: " + ScriptBuilderItem.RESET_HIGHLIGHT +
                ScriptBuilderItem.VALUE_HIGHLIGHT + getNumberFromKey(vectorTag, "z").getDouble());
        // DataCommand
        return values;
    }

    public static CompoundTag getCompoundValue(CompoundTag nbt) {
        if (!nbt.contains("value")) return new CompoundTag();
        if (containsAllowedType(nbt) && nbt.get("value") instanceof CompoundTag) {
            return (CompoundTag) nbt.get("value");
        }
        else return new CompoundTag();
    }

    public static String getMemberValueAsString(CompoundTag objectTag, String memberKey) {
        if (!objectTag.contains("value")) return new CompoundTag().toString();
        if (containsAllowedType(objectTag) && objectTag.get("value") instanceof CompoundTag) {
            CompoundTag compound = (CompoundTag) objectTag.get("value");
            if (compound != null) {
                return getFromKey(compound, memberKey).toString();
            }
        }
        else return new CompoundTag().toString();
        return new CompoundTag().toString();
    }

    public static String getMemberValueWithKey(CompoundTag objectTag, String memberKey, String keyText) {
        if (!objectTag.contains("value")) return new CompoundTag().toString();
        if (containsAllowedType(objectTag) && objectTag.get("value") instanceof CompoundTag) {
            CompoundTag compound = (CompoundTag) objectTag.get("value");
            if (compound != null) {
                return keyText + getFromKey(compound, memberKey).toString();
            }
        }
        else return new CompoundTag().toString();
        return new CompoundTag().toString();
    }

    public static String getMemberValueWithFormat(CompoundTag nbt, String memberKey, String keyText) {
        if (!nbt.contains("value")) return new CompoundTag().toString();
        if (containsAllowedType(nbt) && nbt.get("value") instanceof CompoundTag) {
            CompoundTag compound = (CompoundTag) nbt.get("value");
            if (compound != null) {
                return ScriptBuilderItem.NAME_HIGHLIGHT + keyText + ScriptBuilderItem.RESET_HIGHLIGHT +
                        ScriptBuilderItem.VALUE_HIGHLIGHT + getFromKey(compound, memberKey).toString();
            }
        }
        else return new CompoundTag().toString();
        return new CompoundTag().toString();
    }

    public static ListTag getListValue(CompoundTag nbt) {
        if (nbt.contains("value")) return new ListTag();
        if (containsAllowedType(nbt) && nbt.get("value") instanceof ListTag) {
            return (ListTag) nbt.get("value");
        }
        else return new ListTag();
    }

    public static List<CompoundTag> getListCompound(ListTag listTag) {
        List<CompoundTag> nbtList = new ArrayList<>();
        if (listTag.isEmpty()) return nbtList;
        listTag.forEach(inbt -> {
            if (inbt instanceof CompoundTag) {
                nbtList.add((CompoundTag) inbt);
            }
        });
        return nbtList;
    }

}
