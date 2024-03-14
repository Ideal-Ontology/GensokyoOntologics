package github.thelawf.gensokyoontology.api.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IInputParser {
    default int parseInt(String input) {
        String pattern = "-?\\d+";
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) sb.append(matcher.group());
        return sb.toString().isEmpty() ? 0 : Integer.parseInt(sb.toString());
    }

    default float parseFloat(String input) {
        String pattern = "-?\\d*\\.?\\d+(?:[eE][-+]?\\d+)?";
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) sb.append(matcher.group());
        return sb.toString().isEmpty() ? 0F : Float.parseFloat(sb.toString());

    }

    default double parseDouble(String input) {
        String pattern = "-?\\d*\\.?\\d+(?:[eE][-+]?\\d+)?";
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) sb.append(matcher.group());
        return sb.toString().isEmpty() ? 0D : Double.parseDouble(sb.toString());

    }

    default long parseLong(String input) {
        String pattern = "-?\\d+";
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) sb.append(matcher.group());
        return sb.toString().isEmpty() ? 0L : Long.parseLong(sb.toString());

    }

    default boolean isInValidIntRange(String input) {
        if (input.matches("^-?\\d+$")){
            String parsedStr = Pattern.compile("[^0-9]").matcher(input).replaceAll("");
            return parsedStr.length() > 10 + hasPosOrNegSymbol(parsedStr);
        }
        return false;
    }

    default int hasPosOrNegSymbol(String parsedStr) {
        if (parsedStr.contains("+") || parsedStr.contains("-")) return 1;
        else return 0;
    }
}
