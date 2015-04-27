package worldOfLanguages;

// TODO 9 : define subclasses for more specific errors

public class MyException extends Exception {

	public MyException( String message ) {
		super( message );
	}

	public MyException( String message, Throwable cause ) {
		super( message, cause );
	}
}
