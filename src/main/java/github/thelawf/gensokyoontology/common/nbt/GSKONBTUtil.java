package github.thelawf.gensokyoontology.common.nbt;

import com.google.common.collect.ImmutableList;
import github.thelawf.gensokyoontology.common.item.script.ScriptBuilderItem;
import github.thelawf.gensokyoontology.data.recipe.RecastEntry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GSKONBTUtil {

    public static final List<String> PRIMITIVE_TYPES = ImmutableList.of(
            "int",
            "float",
            "double",
            "long",
            "string",
            "boolean"
    );

    public static final List<String> ALLOWED_TYPES = ImmutableList.of(
            "vector3d",
            "danmaku",
            "world",
            "vector3d_list",
            "danmaku_list"
    );

    public static void mergeEnchantment(Map<RecastEntry, Integer> map, CraftResultInventory resultInv) {
        ListNBT mergedList = new ListNBT();
        CompoundNBT enchantsMapping = new CompoundNBT();
        map.forEach((entry, count) -> mergedList.add(entry.remapEnchantLevel(count)));

        mergedList.replaceAll(it -> {
            CompoundNBT nbt = new CompoundNBT();
            CompoundNBT merged = castToCompound(it);
            Enchantment enchant = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(merged.getString("id")));
            if (enchant != null && enchant.getMaxLevel() < merged.getInt("lvl")) {
                nbt.putInt("lvl", enchant.getMaxLevel());
                return nbt;
            }
            return it;
        });

        enchantsMapping.put("Enchantments", mergedList);
        ItemStack stack = resultInv.getStackInSlot(0).copy();
        stack.setTag(enchantsMapping);
        resultInv.setInventorySlotContents(0, stack);
    }

    public static void mergeSpell(Map<RecastEntry, Integer> map, CraftResultInventory resultInv) {}

    public static void mergeEnchantment(CraftResultInventory resultInv, RecastEntry entry, int level) {
        if (level <= 0) return;

        ItemStack stack = resultInv.getStackInSlot(0).copy();
        // entry.replaceEnchantLvl(level);

        ListNBT listInStack = resultInv.getStackInSlot(0).getEnchantmentTagList();
        ListNBT listInRecast = entry.getValue().getList("enchantments", 10);

        // 创建合并后的列表
        ListNBT mergedList = new ListNBT();
        CompoundNBT enchantsMapping = new CompoundNBT();

        // 创建映射表存储最高等级附魔
        Map<String, Integer> enchantmentMap = new HashMap<>();
        processEnchantmentList(listInStack, enchantmentMap);
        processEnchantmentList(listInRecast, enchantmentMap);

        // 将映射表转换回 ListNBT
        for (Map.Entry<String, Integer> mapEntry : enchantmentMap.entrySet()) {
            CompoundNBT enchantment = new CompoundNBT();
            enchantment.putString("id", mapEntry.getKey());
            enchantment.putInt("lvl", level);
            mergedList.add(enchantment);
        }


        enchantsMapping.put("Enchantments", mergedList);
        stack.setTag(enchantsMapping);

        resultInv.setInventorySlotContents(0, stack);
    }

    private static void processEnchantmentList(ListNBT list, Map<String, Integer> map) {
        for (INBT nbt : list) {
            if (nbt instanceof CompoundNBT) {
                CompoundNBT enchantment = (CompoundNBT) nbt;
                String id = enchantment.getString("id");
                int level = enchantment.getInt("lvl");

                // 如果已有相同附魔，取等级更高的
                if (map.containsKey(id)) {
                    int currentLevel = map.get(id);
                    if (level > currentLevel) {
                        map.put(id, level);
                    }
                } else {
                    map.put(id, level);
                }
            }
        }
    }

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

    public static ListNBT getListFrom(String key, CompoundNBT parentNBT, NBTType type){
        if (!parentNBT.contains(key)) return new ListNBT();
        return parentNBT.getList(key, type.typeByte);
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
    
    public static boolean containsPrimitiveType(CompoundNBT nbt) {
        if (!nbt.contains("type")) return false;
        return PRIMITIVE_TYPES.contains(nbt.getString("type"));
    }
    
    public static String getType(CompoundNBT nbt) {
        if (containsPrimitiveType(nbt)) return nbt.getString("type");
        return "undefined";
    }

    public static boolean containsAllowedType(CompoundNBT nbt) {
        if (!nbt.contains("type")) return false;
        if (containsPrimitiveType(nbt)) return false;
        return ALLOWED_TYPES.contains(nbt.getString("type"));
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

    public enum NBTType{
        END(0),
        BYTE(1),
        SHORT(2),
        INT(3),
        LONG(4),
        FLOAT(5),
        DOUBLE(6),
        BYTE_ARRAY(7),
        STRING(8),
        LIST(9),
        COMPOUND(10),
        INT_ARRAY(11);
        public final int typeByte;

        NBTType(int typeByte) {
            this.typeByte = typeByte;
        }
    }
}
