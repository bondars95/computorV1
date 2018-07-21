
import java.util.ArrayList;
import java.util.Collections;

public class Polynome {

	private ArrayList<Monome> pol;

	Polynome() {
		pol = new ArrayList<Monome>();
	}

	Polynome (String arg) throws Exception {
		this();
		
		int 	signe;
		double	coefficient;
		double	puissance;
		int		i;
		int		len;

		i = 0;
			while (i < arg.length()) {
				while (arg.charAt(i) == ' ')
					i++;
				if (arg.charAt(i) == '-')
				{
					signe = -1;
					i++;
				}
				else
				{
					signe = 1;
					if (arg.charAt(i) == '+')
						i++;
				}
				len = 0;
				while (arg.charAt(i) == ' ')
					i++;
				while (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.') {
					len++;
				}
				coefficient = Double.parseDouble(arg.substring(i, i + len)) * signe;
				i += len;
				if (arg.charAt(i +  1) == '*') {
					i += 4;
					if (i > arg.length()) {
						throw new ParseException("Unexpected end");
					}
					if (arg.charAt(i) != '^') {
						puissance = 1;
					}
					else {
						i++;
						len = 0;
						if (arg.charAt(i) == '-')
						{
							signe = -1;
							i++;
						}
						else
							signe = 1;
						len = 0;
						while (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.') {
							len++;
						}
						puissance = Double.parseDouble(arg.substring(i, i + len)) * signe;
						i += len;
					}
					if (puissance % 1 != 0 || puissance < 0) {
						throw new ParseException("It seems you gave a not natural power value (" + puissance + ")");
					}
				}
				else {
					puissance = 0;
				}
				add_monome(new Monome((int)puissance, coefficient));
				while (arg.charAt(i) == ' ')
					i++;
				if (arg.charAt(i) == '=')
					break;
			}
			i++;
			while (i < arg.length()) {
				while (arg.charAt(i) == ' ')
					i++;
				if (arg.charAt(i) == '-')
				{
					signe = -1;
					i++;
				}
				else
				{
					signe = 1;
					if (arg.charAt(i) == '+')
						i++;
				}
				len = 0;
				while (arg.charAt(i) == ' ')
					i++;
				while (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.') {
					len++;
				}
				coefficient = Double.parseDouble(arg.substring(i, i + len)) * signe;
				i += len;
				if (arg.charAt(i +  1) == '*') {
					i += 4;
					if (i > arg.length()) {
						throw new ParseException("Unexpected end");
					}
					if (arg.charAt(i) != '^') {
						puissance = 1;
					}
					else {
						i++;
						len = 0;
						if (arg.charAt(i) == '-')
						{
							signe = -1;
							i++;
						}
						else
							signe = 1;
						len = 0;
						while (i + len < arg.length() && (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.')) {
							len++;
						}
						puissance = Double.parseDouble(arg.substring(i, i + len)) * signe;
						i += len;
					}
					if (puissance % 1 != 0 || puissance < 0) {
						throw new ParseException("It seems you gave a not natural power value (" + puissance + ")");
					}
				}
				else {
					puissance = 0;
				}
				add_monome(new Monome((int)puissance, coefficient * -1));
				while (i + len < arg.length() && arg.charAt(i) == ' ')
					i++;
			}
			if (pol.isEmpty())
				pol.add(new Monome(0, 0));
		}
	
	public void sort() {
		Collections.sort(pol);
	}

	public void add_monome(Monome to_add) {
		Monome	i;
		int		n;
		
		n = 0;
		if (to_add.getCoef() == 0 && to_add.getPuissance() != 0)
			return ;
		while (n < pol.size()) {
			i = pol.get(n);
			if (i.getPuissance() == to_add.getPuissance()) {
				if (i.getCoef() + to_add.getCoef() == 0 && i.getPuissance() != 0)
					pol.remove(n);
				else
					i.add(to_add);
				return ;
			}
			n++;
		}
		pol.add(to_add);
	}
	
	public String toString()
	{
		String ret = new String();
		
		int j;
		ArrayList<Monome> pol2;
		
		pol2 = new ArrayList<Monome>(pol);
		Collections.reverse(pol2);
		j = 0;
		for (Monome i : pol2) {
			if (i.getCoef() >= 0 && j > 0) {
				ret += " + ";
				if (i.getCoef() % 1 == 0)
					ret += Integer.toString((int)(Math.rint(i.getCoef())));
				else
					ret += Double.toString(i.getCoef());
				ret += " * X^" + Integer.toString(i.getPuissance());
			}
			else if (i.getCoef() >= 0 && j == 0) {
				if (i.getCoef() % 1 == 0)
					ret += Integer.toString((int)(Math.rint(i.getCoef())));
				else
					ret += Double.toString(i.getCoef());
				ret += " * X^" + Integer.toString(i.getPuissance());
			}
			else if (i.getCoef() < 0) {
				if (j == 0)
					ret += "-";
				else
					ret += " - ";
				if (i.getCoef() * -1 % 1 == 0)
					ret += Integer.toString((int)(Math.rint(i.getCoef()) * -1));
				else
					ret += Double.toString(i.getCoef() * -1);
				ret += " * X^" + Integer.toString(i.getPuissance());
			}
			j++;
		}
		return ret;
	}
	
	public Monome get_monome_deg(int pdegre) {
		for (Monome i : pol) {
			if (i.getPuissance() == pdegre)
				return (i);
		}
		return null;
	}
	
	public int getDegre() {
		return (pol.get(0).getPuissance());
	}
	
	public boolean polEmpty() {
		return pol.isEmpty();
	}
}
