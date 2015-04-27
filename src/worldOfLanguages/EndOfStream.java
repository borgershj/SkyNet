package worldOfLanguages;


/** 
 * try to read an end of stream terminal for any Stream
 *
 */
public class EndOfStream extends Terminal {

	// you cannot create directly, to prevent multiple instances
	protected EndOfStream() {
		super();
	}


	@Override
	public String toString() {
		return "<EOS>";
	}
}
