package github.thelawf.gensokyoontology.common.util.math;


import net.minecraft.util.math.vector.Vector2f;

public class Complex {
    public final double real;
    public final double imaginary;

    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex UNIT_COMPLEX = new Complex(1, 1);

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // 复数加法
    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    // 复数减法
    public Complex subtract(Complex other) {
        return new Complex(this.real - other.real, this.imaginary - other.imaginary);
    }

    // 复数乘法
    public Complex multiply(Complex other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new Complex(newReal, newImaginary);
    }

    public Complex conjugate() {
        return new Complex(this.real, -this.imaginary);
    }

    /**
     * @return 复数的模
     */
    public double getMagnitude() {
        return Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
    }

    /**
     * @return 复数的幅角（相位）
     */
    public double getPhase() {
        return Math.atan2(this.imaginary, this.real);
    }

    /**
     * @return 复数的幅角（相位）
     */
    public double getArg() {
        return getPhase();
    }

    public Complex scale(double factor) {
        return new Complex(this.real * factor, this.imaginary * factor);
    }

    public Vector2f toVector2f() {
        return new Vector2f((float) this.real, (float) this.imaginary);
    }
}
