package tests;


import java.net.URL;
import java.util.ArrayList;

import worldOfLanguages.HTMLSource;
import worldOfLanguages.MyException;
import worldOfLanguages.NaturalLanguageSource;
import worldOfLanguages.Terminal;




/**
 * Demo
 * 
 * Open a stream
 * Parse it with some Grammar
 * 
 * @author Erik Borgers
 */
public class URLTest {

	/**
	 * @param args
	 */


	private static void readAllSource( NaturalLanguageSource s ) {

		try {

			s.open();
			Terminal t1;

			do {
				t1 = s.getTerminal( );
				System.out.print( t1 + "-");
			} while ( t1 != Terminal.readTerminal( "" ) ); // EndOfStream 
			System.out.println();

			s.close();

		} catch ( MyException ex ) {
			ex.printStackTrace();
		}
	}

	private static ArrayList<URL> readURL( HTMLSource s ) {

		ArrayList<URL> results = null;

		try {

			s.open();
			results = s.getHyperLinks();
			s.close();
		} catch ( MyException ex ) {
			ex.printStackTrace();
		}

		return results;

	}

	private static void printURLS( ArrayList<URL> list ) {
		for ( URL u : list ) {
			new Log( "URL = " + u.toString() );
		}
	}

	public static void main(String[] args) {

		new Log( "Start StreamTest" );

		ArrayList<URL> results = readURL( new HTMLSource( "file:inputs/kinderVerhalenLink.html" ));
		printURLS( results );


		ArrayList<URL> results2 = readURL( new HTMLSource( "http://www.kinderverhalen.nl/sprookjes.htm" ));
		printURLS( results2 );

		readAllSource( new HTMLSource( "http://www.kinderverhalen.nl/sprookjes.htm" ));

		new Log( "done!");
	}
}
