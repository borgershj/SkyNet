package worldOfLanguages;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * a document on a clasic file path
 * examples "C:/test.reader.txt" or  "inputs/test.reader.txt"
 * 
 * @author Erik Borgers
 */
public class PathUnformattedSource extends ReaderBasedSource {

	private String path;

	/** create  a stream to a document, to read or write to it */
	public PathUnformattedSource( String ppath ) {
		super( );
		path = ppath;
	}

	/* 
	 * open for reading using a path
	 *
	 */
	@Override
	protected Reader getReader() throws Exception {
		File file = new File( path);
		Reader reader = new InputStreamReader( new FileInputStream( file ));
		return reader;
	}


	/**
	 * open a connection to a writable path
	 * if it does not exist, create it now
	 *
	 */
	/*
	@Override
	protected Writer getWriter() throws Exception {
		File file = new File( path);
		Writer writer = new OutputStreamWriter( new FileOutputStream( file ));
		return writer;
	}
	 */

	@Override
	protected String getSourceName() {
		return this.path;
	}

}