package networkSimulation;

import java.util.ArrayList;

/**
 * an ISender can send ISignal-s to Connector-s
 * 
 * @author Erik
 *
 */
public interface ISender {

	/**
	 * connect to a specific connector
	 * 
	 * @param c
	 */
	public void connectTo(Connector to);

	public ArrayList<Connector> getConnectors();

}
