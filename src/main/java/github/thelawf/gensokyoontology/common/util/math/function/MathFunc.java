package github.thelawf.gensokyoontology.common.util.math.function;

public interface MathFunc {
    double computeResult(double x);
    default MathExpression<MathFunc> functor(){
        return (func, x) -> this.computeResult(x);
    }
}
