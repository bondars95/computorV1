
import java.util.ArrayList;
import java.util.Collections;

public class Polynom {
    private static ArrayList<Exponent> exponents = new ArrayList<>();

    private static void parseExpression(String exp, boolean leftPart) {
        int sign;
        double cof;
        double power;
        int i;
        int len;

        i = 0;
        while (i < exp.length()) {
            while (exp.charAt(i) == ' ') {
                i++;
            }
            if (exp.charAt(i) == '-') {
                sign = -1;
                i++;
            } else {
                sign = 1;
                if (exp.charAt(i) == '+') {
                    i++;
                }
            }
            len = 0;
            while (exp.charAt(i) == ' ')
                i++;
            while (Character.isDigit(exp.charAt(i + len)) || exp.charAt(i + len) == '.') {
                len++;
            }
            cof = Double.parseDouble(exp.substring(i, i + len)) * sign;
            i += len;
            if (exp.charAt(i + 1) == '*') {
                i += 4;
                if (i > exp.length()) {
                    throw new ParseException("Unexpected end");
                }
                if (exp.charAt(i) != '^') {
                    power = 1;
                } else {
                    i++;
                    if (exp.charAt(i) == '-') {
                        sign = -1;
                        i++;
                    } else
                        sign = 1;
                    len = 0;
                    while (i + len < exp.length() && (Character.isDigit(exp.charAt(i + len)) || exp.charAt(i + len) == '.')) {
                        len++;
                    }
                    power = Double.parseDouble(exp.substring(i, i + len)) * sign;
                    i += len;
                }
                if (power % 1 != 0 || power < 0) {
                    throw new ParseException("It seems you gave a not natural power value (" + power + ")");
                }
            } else {
                power = 0;
            }
            addExponent(new Exponent((int) power, leftPart ? cof : -cof));
            while (i + len < exp.length() && exp.charAt(i) == ' ') {
                i++;
            }
            if (leftPart && exp.charAt(i) == '=') {
                // parse right part
                parseExpression(exp.substring(i + 1), false);
                return;
            }
        }
    }

    Polynom(String arg) {
        parseExpression(arg, true);
        if (exponents.isEmpty()) {
            exponents.add(new Exponent(0, 0));
        }
    }

    void sort() {
        Collections.sort(exponents);
    }

    private static void addExponent(Exponent toAdd) {
        if (toAdd.getCof() == 0 && toAdd.getPower() != 0)
            return;
        for (Exponent e : exponents) {
            if (e.getPower() == toAdd.getPower()) {
                if ((e.getCof() + toAdd.getCof()) == 0 && e.getPower() != 0) {
                    exponents.remove(e);
                } else {
                    e.add(toAdd);
                }
                return;
            }
        }
        exponents.add(toAdd);
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();

        int j;
        ArrayList<Exponent> exponents;

        exponents = new ArrayList<>(Polynom.exponents);
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
