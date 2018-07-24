
import java.util.ArrayList;
import java.util.Collections;

public class Polynom {
    private ArrayList<Exponent> exponents = new ArrayList<>();

    Polynom(String arg) {
        int sign;
        double cof;
        double power;
        int i;
        int len;

        i = 0;
        while (i < arg.length()) {
            while (arg.charAt(i) == ' ') {
                i++;
            }
            if (arg.charAt(i) == '-') {
                sign = -1;
                i++;
            } else {
                sign = 1;
                if (arg.charAt(i) == '+') {
                    i++;
                }
            }
            len = 0;
            while (arg.charAt(i) == ' ')
                i++;
            while (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.') {
                len++;
            }
            cof = Double.parseDouble(arg.substring(i, i + len)) * sign;
            i += len;
            if (arg.charAt(i + 1) == '*') {
                i += 4;
                if (i > arg.length()) {
                    throw new ParseException("Unexpected end");
                }
                if (arg.charAt(i) != '^') {
                    power = 1;
                } else {
                    i++;
                    if (arg.charAt(i) == '-') {
                        sign = -1;
                        i++;
                    } else
                        sign = 1;
                    len = 0;
                    while (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.') {
                        len++;
                    }
                    power = Double.parseDouble(arg.substring(i, i + len)) * sign;
                    i += len;
                }
                if (power % 1 != 0 || power < 0) {
                    throw new ParseException("It seems you gave a not natural power value (" + power + ")");
                }
            } else {
                power = 0;
            }
            addExponent(new Exponent((int) power, cof));
            while (arg.charAt(i) == ' ')
                i++;
            if (arg.charAt(i) == '=')
                break;
        }
        i++;
        while (i < arg.length()) {
            while (arg.charAt(i) == ' ')
                i++;
            if (arg.charAt(i) == '-') {
                sign = -1;
                i++;
            } else {
                sign = 1;
                if (arg.charAt(i) == '+')
                    i++;
            }
            len = 0;
            while (arg.charAt(i) == ' ')
                i++;
            while (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.') {
                len++;
            }
            cof = Double.parseDouble(arg.substring(i, i + len)) * sign;
            i += len;
            if (arg.charAt(i + 1) == '*') {
                i += 4;
                if (i > arg.length()) {
                    throw new ParseException("Unexpected end");
                }
                if (arg.charAt(i) != '^') {
                    power = 1;
                } else {
                    i++;
                    if (arg.charAt(i) == '-') {
                        sign = -1;
                        i++;
                    } else
                        sign = 1;
                    len = 0;
                    while (i + len < arg.length() && (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.')) {
                        len++;
                    }
                    power = Double.parseDouble(arg.substring(i, i + len)) * sign;
                    i += len;
                }
                if (power % 1 != 0 || power < 0) {
                    throw new ParseException("It seems you gave a not natural power value (" + power + ")");
                }
            } else {
                power = 0;
            }
            addExponent(new Exponent((int) power, cof * -1));
            while (i + len < arg.length() && arg.charAt(i) == ' ')
                i++;
        }
        if (exponents.isEmpty())
            exponents.add(new Exponent(0, 0));
    }

    void sort() {
        Collections.sort(exponents);
    }

    private void addExponent(Exponent toAdd) {
        Exponent i;
        int n;

        n = 0;
        if (toAdd.getCof() == 0 && toAdd.getPower() != 0)
            return;
        while (n < exponents.size()) {
            i = exponents.get(n);
            if (i.getPower() == toAdd.getPower()) {
                if (i.getCof() + toAdd.getCof() == 0 && i.getPower() != 0)
                    exponents.remove(n);
                else
                    i.add(toAdd);
                return;
            }
            n++;
        }
        exponents.add(toAdd);
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();

        int j;
        ArrayList<Exponent> exponents;

        exponents = new ArrayList<>(this.exponents);
        Collections.reverse(exponents);
        j = 0;
        for (Exponent i : exponents) {
            if (i.getCof() >= 0 && j > 0) {
                ret.append(" + ");
                if (i.getCof() % 1 == 0)
                    ret.append(Integer.toString((int) (Math.rint(i.getCof()))));
                else
                    ret.append(Double.toString(i.getCof()));
                ret.append(" * X^").append(Integer.toString(i.getPower()));
            } else if (i.getCof() >= 0 && j == 0) {
                if (i.getCof() % 1 == 0)
                    ret.append(Integer.toString((int) (Math.rint(i.getCof()))));
                else
                    ret.append(Double.toString(i.getCof()));
                ret.append(" * X^").append(Integer.toString(i.getPower()));
            } else if (i.getCof() < 0) {
                if (j == 0)
                    ret.append("-");
                else
                    ret.append(" - ");
                if (i.getCof() * -1 % 1 == 0)
                    ret.append(Integer.toString((int) (Math.rint(i.getCof()) * -1)));
                else
                    ret.append(Double.toString(i.getCof() * -1));
                ret.append(" * X^").append(Integer.toString(i.getPower()));
            }
            j++;
        }
        return ret.toString();
    }

    Exponent getPow(int power) {
        for (Exponent i : exponents) {
            if (i.getPower() == power)
                return (i);
        }
        return null;
    }

    int getPower() {
        return (exponents.get(0).getPower());
    }

    private static final class ParseException extends RuntimeException {
        private String str;

        public String toString() {
            return ("ParseException: " + this.getMessage());
        }

        ParseException(String text) {
            str = text;
        }

        public String getMessage() {
            return (str);
        }
    }
}
