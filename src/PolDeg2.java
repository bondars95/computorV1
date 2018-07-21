

public class PolDeg2 extends Polynome{ // a * x2 + bx + c
	private double a;
	private double b;
	private double c;
	
	PolDeg2(double pa, double pb, double pc) {
		a = pa;
		b = pb;
		c = pc;
	}
	PolDeg2(Polynome pol) {
		a = (pol.get_monome_deg(2)).getCoef();
		if (pol.get_monome_deg(1) != null)
			b = (pol.get_monome_deg(1)).getCoef();
		else
			b = 0;
		if (pol.get_monome_deg(0) != null)
			c = (pol.get_monome_deg(0)).getCoef();
		else
			c = 0;
	}
	
	public double getDelta() {
		return (b * b - 4 * a * c);
	}
	
	public void solve_and_print() {
		if (this.getDelta() > 0)
		{
			System.out.println("Discriminant is strictly positive, the two solutions are:");
			System.out.println(Double.toString((-1 * b - Math.sqrt(this.getDelta())) / (2 * a)));
			System.out.println(Double.toString((-1 * b + Math.sqrt(this.getDelta())) / (2 * a)));
		}
		else if (this.getDelta() == 0) {
			System.out.println("Discriminant is equal to 0, the only solution is:");
			if ((-1 * b) / (2 * a) == 0)
				System.out.println("0");
			else
				System.out.println(String.valueOf((-1 * b) / (2 * a)));
		}
		else
		{
			System.out.println("Discriminant is strictly negative, the two (imaginary) solutions are:");
			Complex z1 = new Complex((-1 * b) / (2 * a), (-1 * Math.sqrt(-1 * this.getDelta()) / (2 * a)));
			Complex z2 = new Complex((-1 * b) / (2 * a), (Math.sqrt(-1 * this.getDelta()) / (2 * a)));
			System.out.println(z1 + System.lineSeparator() + z2);
		}
	}
}
