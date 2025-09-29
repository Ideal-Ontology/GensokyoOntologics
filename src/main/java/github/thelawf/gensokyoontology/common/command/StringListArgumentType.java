package github.thelawf.gensokyoontology.common.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.arguments.ItemArgument;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StringListArgumentType implements ArgumentType<String> {
    private final List<String> suggestions;
    private final boolean caseSensitive;

    private StringListArgumentType(List<String> suggestions, boolean caseSensitive) {
        this.suggestions = suggestions;
        this.caseSensitive = caseSensitive;
    }


    public static StringListArgumentType stringList(List<String> suggestions) {
        return new StringListArgumentType(suggestions, false);
    }

    public static StringListArgumentType stringListCaseSensitive(List<String> suggestions) {
        return new StringListArgumentType(suggestions, true);
    }

    public static String getString(final CommandContext<?> context, final String name) {
        return context.getArgument(name, String.class);
    }
    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        return reader.readUnquotedString();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        String remaining = builder.getRemaining().toLowerCase();

        for (String suggestion : suggestions) {
            String compareSuggestion = caseSensitive ? suggestion : suggestion.toLowerCase();
            if (compareSuggestion.startsWith(remaining)) {
                builder.suggest(suggestion);
            }
        }

        return builder.buildFuture();
    }
}
