package github.thelawf.gensokyoontology.api.util;

public class IntRange {
    private final int min;
    private final int max;

    public IntRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static IntRange of(int min, int max) {
        return new IntRange(min, max);
    }

    public boolean contains(int value) {
        return value >= min && value <= max;
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public int size() {
        return max - min + 1;
    }
}
