package github.thelawf.gensokyoontology.common.util.math.function;

import onlyfortest.tree.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PolynomialFunc extends RationalFunc{
    public List<Pair<Float, Float>> values = new ArrayList<>();
    public List<Monomial> monomials = new ArrayList<>();

    public PolynomialFunc(List<Monomial> monomials) {
        this.monomials = monomials;
        for (Monomial monomial : this.monomials) {
            this.values.add(Pair.of(monomial.coefficient, monomial.time));
        }
    }

    public PolynomialFunc(List<Float> coefficients, List<Float> times){
        if (coefficients.size() == 0 || times.size() == 0) {
            throw new IllegalStateException("List is empty!");
        }

        if (coefficients.size() != times.size()) {
            throw new IllegalStateException("The size of the two list is not the same!");
        }

        for (int i = 0; i < coefficients.size(); i++) {
            Pair<Float, Float> pair = Pair.of(coefficients.get(i), times.get(i));
            this.monomials.add(Monomial.of(coefficients.get(i), times.get(i)));
            this.values.add(pair);
        }
    }

    public static PolynomialFunc of(Monomial... monomials) {
        return new PolynomialFunc(new ArrayList<>(Arrays.asList(monomials)));
    }

    @Override
    public double computeResult(double x) {
        AtomicReference<Double> res = new AtomicReference<>((double) 0);
        monomials.forEach(monomial -> res.updateAndGet(v -> v + monomial.computeResult(x)));
        return Math.round(res.get() * 100) * 0.01;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("f(x)=");
        for (Monomial m : this.monomials) {

            builder.append(m.toString());
            if(this.monomials.indexOf(m) == 0) {
                char[] chars = builder.toString().toCharArray();
                if (chars[5] == '+') {
                    builder.delete(5,6);
                }
            }
        }
        return builder.toString();
    }
}
