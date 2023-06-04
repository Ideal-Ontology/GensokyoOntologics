package github.thelawf.gensokyoontology.api;

import java.util.List;

public interface IMatrix<E> extends Iterable<E>{

    E get(List<Integer> coordinate);

    void add(E e);

    void insertAt(E e, List<Integer> coordinate);

    void removeAt(List<Integer> coordinate);

}
