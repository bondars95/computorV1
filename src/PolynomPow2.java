

class PolynomPow2 { // a * x2 + bx + c
    private double a;
    private double b;
    private double c;

    PolynomPow2(Polynom pol) {
        a = (pol.getPow(2)).getCof();
        b = pol.getPow(1) != null ? pol.getPow(1).getCof() : 0;
        c = pol.getPow(0) != null ? pol.getPow(0).getCof() : 0;
    }

    private double getDiscriminant() {
        return (b * b - 4 * a * c);
    }

    void printSolution() {
        if (this.getDiscriminant() > 0) {
            System.out.println("Discriminant is strictly positive, the two solutions are:");
            System.out.println(Double.toString((-1 * b - Math.sqrt(this.getDiscriminant())) / (2 * a)));
            System.out.println(Double.toString((-1 * b + Math.sqrt(this.getDiscriminant())) / (2 * a)));
        } else if (this.getDiscriminant() == 0) {
            System.out.println("Discriminant is equal to 0, the only solution is:");
            System.out.println((-1 * b) / (2 * a) == 0 ? "0" : String.valueOf((-1 * b) / (2 * a)));
        } else {
            System.out.println("Discriminant is strictly negative, the two (imaginary) solutions are:");
            System.out.println(
                    String.format(
                            "%f  + i * %f\n%f  + i * %f",
                            (-1 * b) / (2 * a),
                            (-1 * Math.sqrt(-1 * this.getDiscriminant()) / (2 * a)),
                            (-1 * b) / (2 * a),
                            (Math.sqrt(-1 * this.getDiscriminant()) / (2 * a))
                    )
			);
        }
    }
}
