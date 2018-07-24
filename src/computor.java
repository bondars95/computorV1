
public class computor {
    public static void main(String[] args) {
        Polynom pol;
        PolynomPow2 pol2;

        if (args.length != 1) {
            System.out.println("Wrong number of arguments :( ");
            return;
        }

        try {
            pol = new Polynom(args[0]);
        } catch (Exception e) {
            System.out.println("Parsing error has occurred.\nPlease check format. Error details : " + e.toString() + ".");
            System.out.println("computor-V1 has exited unexpectly.");
            return;
        }
        pol.sort();
        System.out.println("Reduced form: " + pol + " = 0");
        System.out.println("Polynomial degree: " + Integer.toString(pol.getPower()));
        if (pol.getPower() > 2) {
            System.out.println("The polynomial degree is stricly greater than 2, I can't solve.");
        } else if (pol.getPower() == 2) {
            new PolynomPow2(pol).printSolution();
        } else if (pol.getPower() == 1) {
            System.out.println("The polynomial degree is equal to 1, here is the only solution :");
            System.out.println((-1 * pol.getPow(0).getCof()) / pol.getPow(1).getCof());
        } else if (pol.getPower() == 0) {
            System.out.println("The equation you entered corresponds to: " + pol + " = 0");
            System.out.println(
                    pol.getPow(0).getCof() == 0
                            ? "Every real number is a solution of this equation."
                            : "There is no solution to this equation."
            );
        }
    }
}
