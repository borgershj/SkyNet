package worldOfLanguages;


/**
 * @author erik borgers
 * 
 */
public class FlatStringSource extends NaturalLanguageSource {

	private FlatStringSource() {
		super();
		current = 0;
		buffer = new StringBuffer();
	}

	public FlatStringSource( String initialContent ) {
		this();
		buffer.append( initialContent );
	}

	@Override
	public void open( ) throws MyException {
		super.open();
		current = 0;
	}

	@Override
	public Terminal getTerminal( ) throws MyException  {

		if ( !isOpen() ) {
			throw new MyException( "Stream not open" );
		};

		// try to get a terminal from the string (possibly EOS)
		Terminal t = Terminal.getReadable( buffer, current );
		if ( t == null ) {
			// new Log( "Skipped <" + buffer.charAt( current ) + ">" );
			current++; // skip the unreadable stuff and try again (could be EOS)
			return getTerminal();
		}
		// go forwards for the read chars
		current += t.length();
		return t;
	}

	@Override
	public void close() throws MyException {
		super.close();
	}


}

