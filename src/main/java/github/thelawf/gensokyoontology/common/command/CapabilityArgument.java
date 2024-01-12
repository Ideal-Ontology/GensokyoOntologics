package github.thelawf.gensokyoontology.common.command;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.ReflectHelper;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.impl.data.DataCommand;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.SelectorTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CapabilityArgument implements ArgumentType<LazyOptional<Capability<?>>> {
    public static CapabilityArgument capability() {
        return new CapabilityArgument();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {

        IdentityHashMap<String, Capability<?>> map = ObfuscationReflectionHelper.getPrivateValue(CapabilityManager.INSTANCE.getDeclaringClass(), CapabilityManager.INSTANCE, "providers");
        if (map != null) {
            return ISuggestionProvider.suggest(new ArrayList<>(map.keySet()), builder);
        }
        return Suggestions.empty();
    }

    @Override
    public LazyOptional<Capability<?>> parse(StringReader reader) {
        IdentityHashMap<String, Capability<?>> map = ObfuscationReflectionHelper.getPrivateValue(CapabilityManager.INSTANCE.getDeclaringClass(), CapabilityManager.INSTANCE, "providers");
        if (map != null) {
            return LazyOptional.of(() -> {
                try {
                    return map.get(reader.readString());
                } catch (CommandSyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return null;
    }

    public static LazyOptional<Capability<?>> getCapability(String name) {
        IdentityHashMap<String, Capability<?>> map = ObfuscationReflectionHelper.getPrivateValue(CapabilityManager.INSTANCE.getDeclaringClass(), CapabilityManager.INSTANCE, "providers");
        if (map != null) {
            return LazyOptional.of(() -> map.get(name));
        }
        return LazyOptional.empty();
    }

}
