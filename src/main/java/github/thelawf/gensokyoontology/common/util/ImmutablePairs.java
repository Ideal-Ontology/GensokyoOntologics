package github.thelawf.gensokyoontology.common.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ImmutablePairs<A,B> {
    private List<Pair<A, B>> pairs = new ArrayList<>();

    public static <A,B> ImmutablePairs<A,B> of(Pair<A, B>... pairsIn) {
        Set<Pair<A,B>> set = Arrays.stream(pairsIn).sequential().collect(Collectors.toSet());
        ImmutablePairs<A,B> immutablePairs = new ImmutablePairs<>();
        immutablePairs.pairs.addAll(set);
        return immutablePairs;
    }
}
