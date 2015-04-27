package worldOfLanguages;

import java.io.FileNotFoundException;
import java.io.Reader;


/**
 * reads a document on disk or internet
 * 
 * 
 * @author Erik Borgers
 */
public abstract class ReaderBasedSource extends NaturalLanguageSource {

	// contains the chars of the read document, possibly appended with write statements
	private Reader reader;

	/** create  a link to a document, to read or write to it */
	protected ReaderBasedSource( ) {
		super();
		reader=null;
	}

	protected abstract Reader getReader() throws Exception;
	protected abstract String getSourceName();

	@Override
	public void open( ) throws MyException {
		super.open();
		try {
			reader = getReader();
			buffer = new StringBuffer();
			int ch;
			while ( (ch = reader.read()) != -1 ) {
				buffer.append( (char)ch );
			}
			reader.close();
			current=0;
		}
		catch (FileNotFoundException readException) {
			// this is not a problem, if we only do writing this can still be ok
			// we return an empty buffer
			state = EnumSourceState.CLOSED;
			throw new MyException( "could not find file or url:" + getSourceName());
		}
		catch (Exception otherException) {
			buffer = null;
			state = EnumSourceState.CLOSED;
			throw new MyException( "Error while reading from:" + getSourceName(), otherException );
		}			
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
		buffer = null; // garbage collect the buffer
		reader=null;
		super.close();
	}

	@Override
	public String toString() {
		if (!isOpen()) return null;
		return buffer.substring( current );
	}
}