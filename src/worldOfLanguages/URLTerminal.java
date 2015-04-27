package worldOfLanguages;

import java.net.MalformedURLException;
import java.net.URL;


public class URLTerminal extends Terminal {

	private URL url = null;

	public URLTerminal( String s ) throws MalformedURLException {
		super();
		url = new URL( s );
	}

	/**
	 * return the URL
	 */
	public URL getURL() {
		return url;
	}

	@Override
	public String toString() {
		return url.toString();
	}

}
