package worldOfLanguages;

import java.util.HashMap;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;


/**
 * Generic Terminal that occurs in sources
 * Terminals have just 1 occurence
 * 
 * Word
 * Punctuations
 * Number
 * EOS 
 * 
 * 
 * @author Erik Borgers
 */
public abstract class Terminal  {

	// remember the unique instances, retrievable by name string 
	// private static Hashtable terminalCache = new Hashtable();
	private static HashMap<String,Terminal> terminalCache = new HashMap<String,Terminal>();

	/**
	 * get a unique instance of a Terminal for this string starting at position current
	 * "" will return an EOS
	 * unscannable chars will return null
	 *
	 * @return
	 */
	static public Terminal getReadable( StringBuffer s, int current ) {

		if ( s== null) throw new RuntimeException( "No string present to scan terminal from" );

		Terminal t;

		// try for an end of stream terminal
		if ( current >= s.length() ) {
			t = terminalCache.get(""); 
			if ( t== null ) {
				t = new EndOfStream();
				terminalCache.put("", t);
			}
			return t;
		}

		// try a sentence end terminal
		if ( s.charAt( current ) == '.' )  {
			t = terminalCache.get("."); 
			if ( t== null ) {
				t = new Dot();
				terminalCache.put(".", t);
			}
			return t;
		}

		if ( s.charAt( current ) == '!' ) {
			t = terminalCache.get("!"); 
			if ( t== null ) {
				t = new ExclamationMark();
				terminalCache.put("!", t);
			}
			return t;
		}

		if ( s.charAt( current ) == '?' ) {
			t = terminalCache.get("?"); 
			if ( t== null ) {
				t = new QuestionMark();
				terminalCache.put("?", t);
			}
			return t;
		}

		// try a word terminal
		StringBuffer wordChars = new StringBuffer();

		int i=current; 
		while ( i< s.length() ) {
			if ( Word.isWordPart( s.charAt( i ) ) ) {
				wordChars.append( s.charAt( i ) );
				i++;
			} else {
				break;
			}
		}
		// terminals are in lowercase only
		String word = wordChars.toString().toLowerCase();
		if ( word.length() > 0 ) {
			t = terminalCache.get(word); 
			if ( t== null ) {
				t = new Word( word );
				terminalCache.put(word, t);
			}
			return t;
		}

		// nothing did match a potential terminal
		return null;
	}

	/**
	 * get a terminal from a string 
	 * @param s
	 * @return
	 */
	static public Terminal readTerminal( String s ) {
		StringBuffer b = new StringBuffer( s );
		return getReadable( b, 0 );
	}

	/**
	 * return an end-of-URL type of terminal 
	 * @param s
	 * @return
	 */
	static public EndOfURLTerminal getEOURL( ) {

		EndOfURLTerminal t;

		// try for an end of stream terminal
		t = (EndOfURLTerminal)terminalCache.get( "<EOURL>" ); 
		if ( t== null ) {
			t = new EndOfURLTerminal( );
			terminalCache.put("<EOURL>", t);
		}
		return t;
	}

	/**
	 * return a URL type of terminal from a string like http://www.hello.nl
	 * @param s
	 * @return
	 */
	static public URLTerminal getURL( String fileLocation, String url ) throws MyException {

		if ( url== null) throw new RuntimeException( "No string present to scan URL from" );

		StringBuffer absoluteURL; 

		// if the url is relative, insert the location
		if ( !url.contains( ":" )) {
			if ( fileLocation== null) throw new RuntimeException( "No location present to scan URL from" );
			absoluteURL = new StringBuffer( fileLocation );
			absoluteURL.append( "/" );
			absoluteURL.append( url );
		} else
			absoluteURL = new StringBuffer(url);

		URLTerminal t;

		// try to get the URL from cache, otherwise save it
		t = (URLTerminal)terminalCache.get( absoluteURL ); 
		if ( t== null ) {
			try {
				t = new URLTerminal( absoluteURL.toString() );
			} catch ( Exception e ) {
				throw new MyException( "malformed URL expession: " + absoluteURL ); // TODO or silently accept
			}
			terminalCache.put(absoluteURL.toString(), t);
		}
		return t;
	}

	/**
	 * return nr of terminals stored
	 */
	static public int getNrs() {
		return terminalCache.size();
	}

	/**
	 * create a string
	 */
	@Override
	public abstract String toString();

	/**
	 * the length of the terminal
	 * @return
	 */
	public int length() {
		return toString().length();
	}

}