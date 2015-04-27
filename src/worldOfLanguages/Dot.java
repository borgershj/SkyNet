package worldOfLanguages;

public class Dot extends EndOfSentence {

	// you cannot create directly, to prevent multiple instances
	protected Dot() {
		super();
	}


	@Override
	public String toString() {
		return ".";
	}
}
