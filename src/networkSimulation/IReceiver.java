package networkSimulation;


/**
 * an IReceiver can contain Connector-s
 * 
 * @author Erik
 *
 */

public interface IReceiver {

	// create a Connector
	public abstract Connector newConnector();

}
