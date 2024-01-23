package github.thelawf.gensokyoontology.common.util.math.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class PolynomialParser {
    public static PolynomialFunc parsePolynomial(String polynomialPattern) {
        List<Monomial> monomials = new ArrayList<>();
        String s1 = polynomialPattern.replace(" ", "");
        String[] strings = s1.split("\\+");

        for (String str : strings) {
            if (str.contains("-")) {
                char[] chars = str.toCharArray();
                ArrayList<Character> characters = toListChar(chars);
                monomials.addAll(tryParseMinus(characters));
            }
            else monomials.add(parseMonomial(str));
        }
        return new PolynomialFunc(monomials);
    }

    public static Monomial parseMonomial(String pattern, boolean isNegative) {
        if (!isNegative) return parseMonomial(pattern);

        // 既有系数，又有次数的情况；
        if (pattern.contains("^")) {
            String[] s2 = pattern.split("\\^");
            return Monomial.of(Float.parseFloat(s2[0].replaceAll("[^\\-0-9.]", "").trim()),
                    Float.parseFloat(s2[1].replaceAll("[^\\-0-9.]", "").trim()));
        }

        // 只有系数，没有次数的情况
        else if (Pattern.compile("[\\^+-.0-9]").matcher(pattern).find() &&
        Pattern.compile("[^\\-+.0-9]").matcher(pattern).find()) {
            return Monomial.of(Float.parseFloat(pattern.replaceAll("[^\\-0-9.]", "").trim()),0);
        }

        else if (Pattern.compile("[+\\-.0-9]").matcher(pattern).find()) {
            Monomial monomial = Monomial.of(0, 0);
            monomial.withConst(Float.parseFloat(pattern));
            return monomial;
        }
        return new Monomial(0,0);
    }

    public static Monomial parseMonomial(String pattern) {

        String[] s2 = pattern.split("\\^");

        // 如果 s2[0] 在正则表达式替换之后得到空字符串，则表示该单项式的系数为1
        if (s2.length == 2) {
            return Monomial.of(Float.parseFloat(s2[0].replaceAll("[^\\-0-9.]", "").trim().equals("") ? "1.0" : s2[0].replaceAll("[^\\-0-9.]", "").trim()),
                    Float.parseFloat(s2[1].replaceAll("[^\\-0-9.]", "").trim()));
        }
        else {
            return Monomial.of(Float.parseFloat(s2[0].replaceAll("[^\\-0-9.]", "").trim().equals("") ? "1.0" : s2[0].replaceAll("[^\\-0-9.]", "").trim()), 0);
        }
    }
    private static ArrayList<Character> toListChar(char[] chars) {
        Character[] result = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            result[i] = chars[i];
        }
        return new ArrayList<>(Arrays.asList(result));
    }

    private static List<Monomial> tryParseMinus(ArrayList<Character> characters) {
        ArrayList<Monomial> ms = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        characters.forEach(builder::append);
        String[] strings = builder.toString().split("-");

        for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                ms.add(parseMonomial(strings[i]));
            }
            else {
                String s = "-" + strings[i];
                ms.add(parseMonomial(s, true));
            }
        }

        return ms;
    }
}
