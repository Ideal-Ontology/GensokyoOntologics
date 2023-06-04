package github.thelawf.gensokyoontology.common.util;

public class TriMap<F,S,T> implements ITriMap<F,S,T>{
    private F f;
    private S s;
    private T t;
    @Override
    public F getFirst() {
        return f;
    }

    @Override
    public S getSecond() {
        return s;
    }

    @Override
    public T getThird() {
        return t;
    }

    public void put(F f, S s, T t) {
        this.f = f;
        this.s = s;
        this.t = t;
    }
}
