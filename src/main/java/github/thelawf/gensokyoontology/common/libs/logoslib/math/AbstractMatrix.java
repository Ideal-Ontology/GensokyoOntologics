package github.thelawf.gensokyoontology.common.libs.logoslib.math;

import github.thelawf.gensokyoontology.api.IMatrix;

import java.util.List;

public abstract class AbstractMatrix<E> implements IMatrix<E> {
    int dimension;
    List<Integer> coordinate;
}
