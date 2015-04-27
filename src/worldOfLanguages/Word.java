package worldOfLanguages;


public class Word extends Terminal {

	String string;

	protected Word( String s) {
		super();
		string = s;
	}

	protected static boolean isWordPart( char ch ) {
		if (( ch >= 'A' ) && ( ch <= 'Z' )) return true;
		if (( ch >= 'a' ) && ( ch <= 'z' )) return true;
		if (ch == '-')
			return true; // TODO 9: skip these in future?
		return false;
	}

	@Override
	public String toString() {
		return string;
	}

}
