package worldOfLanguages;


/**
 * A source of linear ordered tokens in a natural language
 * 
 * string
 * flat file
 * html file
 * chat-site
 * e-mail
 * word file
 * rtf file
 * pdf file
 * 
 * @author Erik Borgers
 */
public abstract class NaturalLanguageSource {

	// buffer to store read content
	protected StringBuffer buffer;

	// next to read position in buffer
	protected int current;

	// state of the connection
	protected EnumSourceState state;

	// the constructor will create a stream object
	protected NaturalLanguageSource() {
		state = EnumSourceState.CLOSED;
		buffer = null;
	}

	public boolean isOpen() {
		if (state == EnumSourceState.OPEN) return true;
		return false;
	}

	/**
	 * open the source for reading
	 */
	public void open( ) throws MyException {
		if ( state == EnumSourceState.OPEN ) {
			throw new MyException( "Stream already open" );
		}
		state = EnumSourceState.OPEN;
	}

	/** 
	 * return the next Terminal from the current position
	 */
	public abstract Terminal getTerminal( ) throws MyException;

	/**
	 * close the the stream
	 */
	public void close() throws MyException {
		if (state != EnumSourceState.OPEN) {
			throw new MyException( "Stream not open" );
		}
		state = EnumSourceState.CLOSED;
	}

	/**
	 * print string contents from the current read position for max 50 chars
	 */
	@Override
	public String toString() {
		if ( !isOpen()) return null;
		return this.buffer.substring( current );
	}

}
