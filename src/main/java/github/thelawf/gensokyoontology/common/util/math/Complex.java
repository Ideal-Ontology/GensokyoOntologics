package github.thelawf.gensokyoontology.common.util.math;


public class Complex {
    public final double real;
    public final double imaginary;

    public static final Complex ZERO = new Complex(0,0);
    public static final Complex UNIT_COMPLEX = new Complex(1,1);

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex conjugate(Complex other) {
        return other;
    }

    public Complex scale(double factor) {
        return new Complex(this.real * factor, this.imaginary * factor);
    }
}
