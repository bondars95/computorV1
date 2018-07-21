

public class Monome implements Comparable<Monome> {
	private int puissance;
	private double coef;
	
	Monome(int pdegre, double pnumber) {
		puissance = pdegre;
		if (pnumber == -0.0)
			pnumber = 0.0;
		coef = pnumber;
	}

	public int getPuissance() {
		return puissance;
	}

	public double getCoef() {
		return coef;
	}
	
	public void add(Monome to_add) {
		this.coef =  coef + to_add.getCoef();
	}

	@Override
	public int compareTo(Monome o) {
		Monome m;
		if (o instanceof Monome) {
			m = (Monome) o;
			if (m.getPuissance() > puissance)
				return (1);
			else if (m.getPuissance() < puissance)
				return (-1);
			else
				return (0);
		}
		else
			return 0;
	}
	
	public String toString() {
		if (puissance != 0)
			return ((coef % 1 == 0 ? Math.rint(coef) : coef) + " * X^" + puissance);
		else
			return (coef % 1 == 0 ? Integer.toString((int) Math.rint(coef)) : Double.toString(coef));
	}
}
