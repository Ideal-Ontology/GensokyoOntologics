package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.NonNullConsumer;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static github.thelawf.gensokyoontology.GensokyoOntology.LOGGER;
import static github.thelawf.gensokyoontology.GensokyoOntology.withAffix;

public class GSKOUtil {
    public static <N extends INBT, T extends INBTSerializable<N>> void syncWorldCapability(ClientWorld clientWorld, ServerWorld serverWorld, Capability<T> capability){
        if (serverWorld == null) return;
        if (clientWorld == null) return;
        clientWorld.getCapability(capability).ifPresent(clientCap -> serverWorld.getCapability(capability).ifPresent(
                serverCap -> clientCap.deserializeNBT(serverCap.serializeNBT())));
        serverWorld.getCapability(capability).ifPresent(serverCap -> clientWorld.getCapability(capability).ifPresent(
                clientCap -> serverCap.deserializeNBT(clientCap.serializeNBT())));
    }

    public static void showChatMsg(PlayerEntity receiver, String text, int frequency) {
        if (receiver.ticksExisted % frequency == 0) {
            receiver.sendMessage(new StringTextComponent(text), receiver.getUniqueID());
        }
    }
    public static void showChatMsg(PlayerEntity player, boolean b, int frequency) {
        if (player.ticksExisted % frequency == 0) {
            player.sendMessage(new StringTextComponent(String.valueOf(b)), player.getUniqueID());
        }
    }
    public static void showChatMsg(PlayerEntity player, int i, int frequency) {
        if (player.ticksExisted % frequency == 0) {
            player.sendMessage(new StringTextComponent(String.valueOf(i)), player.getUniqueID());
        }
    }
    public static void showChatMsg(PlayerEntity player, float f, int frequency) {
        if (player.ticksExisted % frequency == 0) {
            player.sendMessage(new StringTextComponent(String.valueOf(f)), player.getUniqueID());
        }
    }

    public static void showChatMsg(PlayerEntity player, long l, int frequency) {
        if (player.ticksExisted % frequency == 0) {
            player.sendMessage(new StringTextComponent(String.valueOf(l)), player.getUniqueID());
        }
    }

    public static void showChatMsg(PlayerEntity player, Object obj, int frequency) {
        if (player.ticksExisted % frequency == 0) {
            player.sendMessage(new StringTextComponent(obj.toString()), player.getUniqueID());
        }
    }

    public static void log(Class<?> clazz, String str) {
        LogManager.getLogger().info(clazz.getName() + ": {}", str);
    }
    public static void log(Class<?> clazz, boolean b) {
        LogManager.getLogger().info(clazz.getName() + ": {}", b);
    }
    public static void log(Class<?> clazz, int i) {
        LogManager.getLogger().info(clazz.getName() + ": {}", i);
    }
    public static void log(Class<?> clazz, float f) {
        LogManager.getLogger().info(clazz.getName() + ": {}", f);
    }
    public static void log(Class<?> clazz, Object obj) {
        LogManager.getLogger().info(clazz.getName() + ": {}", obj.toString());
    }

    public static void log(String str) {
        LogManager.getLogger().info(str);
    }

    public static void log(Object o) {
        LogManager.getLogger().info(o.toString());
    }

    public static void formatLog(Class<?> clazz, Object... objects) {
        LogManager.getLogger().info("{}", clazz.getName());
    }

    public static TranslationTextComponent fromLocaleKey(String prefix, String suffix) {
        return new TranslationTextComponent(withAffix(prefix, suffix));
    }
    public static TranslationTextComponent fromLocaleFormat(String prefix, String suffix, Object... formats) {
        return new TranslationTextComponent(withAffix(prefix, suffix), formats);
    }

    public static Advancement getAdvancement(ServerPlayerEntity serverPlayer, ResourceLocation advancementName) {
        PlayerAdvancements advancements = serverPlayer.getAdvancements();
        if (serverPlayer.world.getServer() == null) {
            LOGGER.info("Advancement: {} Not Present", advancementName);
            return null;
        }
        AdvancementManager manager = serverPlayer.world.getServer().getAdvancementManager();
        if (manager.getAdvancement(advancementName) == null){
            LOGGER.info("Advancement: {} Not Present", advancementName);
            return null;
        }
        return manager.getAdvancement(advancementName);
    }

    public static <K, V> void mapPrintLine(HashMap<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            log(map.getClass(), "key: {" + entry.getKey().toString() + "} -> Value: {" + entry.getValue().toString() + "}");
        }
    }

    public static <T> void listPrintLine(List<T> list) {
        for (T t : list) {
            log(list.getClass(), "key: {" + t.toString() + "}");
        }
    }

    public static ItemStack findItem(PlayerEntity player, Item item) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            if (player.inventory.getStackInSlot(i).getItem() == item) return player.inventory.getStackInSlot(i);
        }
        return ItemStack.EMPTY;
    }

    public static boolean firstMatch(PlayerEntity player, Item item) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            if (player.inventory.getStackInSlot(i).getItem() == item) return true;
        }
        return false;
    }

    public static RegistryKey<World> getDimension(PlayerEntity player) {
        return player.getEntityWorld().getDimensionKey();
    }

    public static <T> void runIfCapPresent(Entity entity, Capability<T> capability, NonNullConsumer<T> consumer) {
        entity.getCapability(capability).ifPresent(consumer);
    }

    public static <T> T rollFrom(List<T> pool) {
        return pool.get(new Random().nextInt(pool.size() - 1));
    }

    @SafeVarargs
    public static <K, V> void putDefaultValue(Map<K, V> map, V defaultValue, K... keys) {
        for (K k : keys) map.put(k, defaultValue);
    }
}
