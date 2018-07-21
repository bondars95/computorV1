

public class PolDeg1 implements Comparable<Monome> {
	private int power;
	private double cof;
	
	PolDeg1(int pdegre, double pnumber) {
		power = pdegre;
		if (pnumber == -0.0)
			pnumber = 0.0;
		cof = pnumber;
	}

	public int getPower() {
		return power;
	}

	public double getCof() {
		return cof;
	}
	
	public void add(Monome to_add) {
		this.cof =  cof + to_add.getCof();
	}

	@Override
	public int compareTo(Monome o) {
		Monome m;
		if (o instanceof Monome) {
			m = (Monome) o;
			if (m.getPower() > power)
				return (1);
			else if (m.getPower() < power)
				return (-1);
			else
				return (0);
		}
		else
			return 0;
	}
	
	public String toString() {
		if (power != 0)
			return ((cof % 1 == 0 ? Math.rint(cof) : cof) + " * X^" + power);
		else
			return (cof % 1 == 0 ? Integer.toString((int) Math.rint(cof)) : Double.toString(cof));
	}
}
