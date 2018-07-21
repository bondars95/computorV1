

public class ParseException extends Exception{
	private String str;

	public String toString() {
		return ("java.Computor.ParseException: " + this.getMessage());
	}

	ParseException(String text) {
		str = text;
	}
	
	public String getMessage() {
		return (str);
	}
}
