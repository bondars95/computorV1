

public class Exponent implements Comparable<Exponent> {
    private int power;
    private double cof;

    Exponent(int power, double cof) {
        this.power = power;
        if (cof == -0.0)
            cof = 0.0;
        this.cof = cof;
    }

    int getPower() {
        return power;
    }

    double getCof() {
        return cof;
    }

    void add(Exponent toAdd) {
        this.cof = cof + toAdd.getCof();
    }

    @Override
    public int compareTo(Exponent o) {
        return o != null ? Integer.compare(o.getPower(), power) : 0;
    }

    public String toString() {
        if (power != 0) {
            return ((cof % 1 == 0 ? Math.rint(cof) : cof) + " * X^" + power);
        } else {
            return (cof % 1 == 0 ? Integer.toString((int) Math.rint(cof)) : Double.toString(cof));
        }
    }
}
