package worldOfLanguages;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * a document on disk or internet using an URL
 * 
 * default implementation just reads from an unformatted file (flat)
 * specialized classes might overrule this (eg recognie html tags)
 * 
 * @author Erik Borgers
 */
public class URLUnformattedSource extends ReaderBasedSource {

	private String location;

	/** create  a stream to a document, to read or write to it */
	public URLUnformattedSource( String url ) {
		super( );
		location = url;
	}

	/* 
	 * open for reading
	 * examples
	 * file://C:/config.sys
	 * http://archive.ncsa.uiuc.edu/SDG/Software/Mosaic/Demo/url-primer.html 
	 * ftp://path...
	 * ftp://user:password@host/path...
	 * http://user:password@host/path...
	 */
	@Override
	protected Reader getReader() throws Exception {

		URL document_url;

		// check the URL for its form
		try {
			document_url = new URL(location);
		}
		catch (MalformedURLException e) {
			throw new MyException( "Illegal url format:  " + location, e );
		}

		// try to open for reading, to store in buffer
		URLConnection uc = document_url.openConnection();
		Reader reader = new InputStreamReader(uc.getInputStream());

		return reader;
	}

	public String getLocation() {
		return location;
	}

	/**
	 * open a connection to a writable url
	 * if it does not exist, create it now
	 *
	 */
	/*	@Override
	protected Writer getWriter() throws Exception {

		// try to open for reading, to store in buffer
		URL document_url = new URL(location);
		URLConnection uc = document_url.openConnection();
		// getOutputStream should also create the url when not existent
		Writer writer = new OutputStreamWriter(uc.getOutputStream());

		return writer;
	}
	 */
	@Override
	public String getSourceName() {
		return this.location;
	}

}