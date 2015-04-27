package worldOfLanguages;

/** 
 *
 */
public class ExclamationMark extends EndOfSentence {

	// you cannot create directly, to prevent multiple instances
	protected ExclamationMark() {
		super();
	}


	@Override
	public String toString() {
		return "!";
	}
}
