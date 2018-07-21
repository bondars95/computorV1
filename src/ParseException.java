

public class ParseException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String str;

	public String toString() {
		return ("java.computor.ParseException: " + this.getMessage());
	}

	ParseException(String text) {
		str = text;
	}
	
	public String getMessage() {
		return (str);
	}
}
