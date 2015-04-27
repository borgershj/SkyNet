package worldOfLanguages;

/** 
 *
 */
public class QuestionMark extends EndOfSentence {

	// you cannot create directly, to prevent multiple instances
	protected QuestionMark() {
		super();
	}


	@Override
	public String toString() {
		return "?";
	}
}
