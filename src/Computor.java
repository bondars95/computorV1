
 public class Computor {
	public static void main(String[] args) {
		Polynome pol;
		Pol_deg_2 pol2;
		
		if (args.length != 1) {
			System.out.println("Wrong number of arguments :( ");
			return ;
		}
		
		try {
			pol = new Polynome(args[0]);
		} catch (Exception e) {
			System.out.println("Parsing error has occurred." + System.lineSeparator() + "Please check format. "
					+ "Error details : " + e.toString() + ".");
			System.out.println("Computor-V1 has exited unexpectly.");
			return ;
		}
		pol.sort();
		System.out.println("Reduced form: " + pol + " = 0");
		System.out.println("Polynomial degree: " + Integer.toString(pol.getDegre()));
		if (pol.getDegre() > 2)
			System.out.println("The polynomial degree is stricly greater than 2, I can't solve.");
		else if (pol.getDegre() == 2) {
			pol2 = new Pol_deg_2(pol);
			pol2.solve_and_print();
		}
		else if (pol.getDegre() == 1) {
			System.out.println("The polynomial degree is equal to 1, here is the only solution :");
			System.out.println((-1 * pol.get_monome_deg(0).getCoef()) / pol.get_monome_deg(1).getCoef());
		}
		else if (pol.getDegre() == 0) {
			System.out.println("The equation you entered corresponds to: " + pol + " = 0");
			if (pol.get_monome_deg(0).getCoef() == 0)
				System.out.println("Every real number is a solution of this equation.");
			else
				System.out.println("There is no solution to this equation.");
		}
	}
}
