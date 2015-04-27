package tests;

import worldOfLanguages.FlatStringSource;
import worldOfLanguages.MyException;
import worldOfLanguages.NaturalLanguageSource;
import worldOfLanguages.PathUnformattedSource;
import worldOfLanguages.Terminal;

/**
 * Demo
 * 
 * Open a stream
 * Parse it with some Grammar
 * 
 * @author Erik Borgers
 */
public class TerminalReadTest {

	/**
	 * @param args
	 */

	private static void readSource( NaturalLanguageSource s ) {

		try {

			s.open();
			s.close();

			s.open();
			new Log( "content=" + s.toString()); // write current content
			s.close();

			s.open();
			Terminal t1 = s.getTerminal( );

			new Log( "First terminal is <" + t1 + ">");
			Terminal t2 = s.getTerminal( );
			new Log( "Second terminal is <" + t2 + ">" );
			s.close();

		} catch ( MyException ex ) {
			ex.printStackTrace();
		}
	}

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

	public static void main(String[] args) {

		new Log( "Start StreamTest" );

		readSource( new FlatStringSource( "Some source to check.") );
		new Log( "Terminalsnrs="+ Terminal.getNrs());
		readAllSource( new FlatStringSource( "Some source to check.") );
		new Log( "Terminalsnrs="+ Terminal.getNrs());
		readAllSource( new FlatStringSource( "More source to check.") );
		new Log( "Terminalsnrs="+ Terminal.getNrs());
		readSource( new PathUnformattedSource( "C:/test.reader.txt" ));
		//		readAllSource( new PathUnformattedSource( "inputs/test.reader.txt" ));  // relative (komt in MyDocuments.MyProjects.SmarT) 
		//		readAllSource( new HTMLSource( "http://users.bart.nl/~borgers/mieke.htm" ));
		//		readAllSource( new HTMLSource( "http://www.familieborgers.com/baby1/" ));
		int nr1 = Terminal.getNrs();
		readSource( new FlatStringSource( "Some source to check.") );
		int nr2 = Terminal.getNrs();
		if ( nr1 != nr2 ) System.err.println( "ERROR in Terminal storage!!!");
		new Log( "done!");
	}
}
