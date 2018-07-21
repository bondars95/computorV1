

public class Complex {
	private double i;
	private double r;
	
	Complex(double pr, double pi) {
		r = pr;
		i = pi;
	}
	
	public String toString() {
		return (r + " + i * " + i);
	}
}
