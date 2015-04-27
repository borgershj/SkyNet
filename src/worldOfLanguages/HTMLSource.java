package worldOfLanguages;

import java.net.URL;
import java.util.ArrayList;


public class HTMLSource extends URLUnformattedSource {

	public HTMLSource( String url ) {
		super( url );
	}

	public boolean scannedHyperlinkStart = false;

	@Override
	public Terminal getTerminal( ) throws MyException {

		if ( !isOpen() ) {
			throw new MyException( "Stream not open" );
		};

		// try to get a terminal from the string (possibly EOS)
		Terminal t = Terminal.readTerminal( buffer.substring( current ) );

		if ( t == null ) {
			// if it is a hyperlink, create an URL
			if (( buffer.length()-current >= 7 ) && (buffer.substring( current, current+7 ).equals( "<a href" ))) {
				while ( buffer.charAt( current ) != '\"' ) {
					current++;
					if ( current >= buffer.length() ) return Terminal.readTerminal(""); // EOS
				}
				current++; // skip the opening " 
				StringBuffer urlBuffer = new StringBuffer();
				while ( buffer.charAt( current ) != '\"' ) {
					urlBuffer.append( buffer.charAt( current ) );
					current++;
					if ( current >= buffer.length() ) return Terminal.readTerminal(""); // EOS
				}
				current++; // skip de closing "
				while ( buffer.charAt( current ) != '>' ) {
					current++;
					if ( current >= buffer.length() ) return Terminal.readTerminal(""); // EOS
				}				
				t = Terminal.getURL( getLocation(), urlBuffer.toString() );
				scannedHyperlinkStart = true;
				return t;
			}

			// if it is the end of a hyperlink, create an EOURL
			if ( scannedHyperlinkStart == true ) {
				if ( ( buffer.length()-current >= 3 ) && (buffer.substring( current, current+3 ).equals( "</a" ))) {
					while ( buffer.charAt( current ) != '>' ) {
						current++;
						if ( current >= buffer.length() ) return Terminal.readTerminal(""); // EOS
					}
					current++; // skip the > 
					scannedHyperlinkStart = false;
					t = Terminal.getEOURL( );
					return t;
				}
			}

			// if on a start of any other tag, skip everything until the end of the tag ">"
			if ( buffer.charAt( current ) == '<' ) {
				//	skipping the html tag
				while ( buffer.charAt( current ) != '>' ) {
					current++;
					if ( current >= buffer.length() ) return Terminal.readTerminal(""); // EOS
				}
				current++; // skip the '>' 
				return getTerminal();
			}
			// if something else, just skip it and try again
			current++;  
			return getTerminal();
		}
		// go forwards for the read chars
		current += t.length();
		return t;
	}

	/** retreive all hyperlinks from current position till end of file */
	public ArrayList<URL> getHyperLinks() throws MyException {
		if (!isOpen()) throw new MyException( "Source not open for reading" );

		Terminal t;
		ArrayList<URL> list = new ArrayList<URL>();

		do {
			t = getTerminal( );
			if ( t instanceof URLTerminal ) {
				URL u = ((URLTerminal)t).getURL( );
				list.add( u );
			}
		} while (!( t instanceof EndOfStream ));

		return list;
	}
}
