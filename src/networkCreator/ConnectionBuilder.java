package networkCreator;

import java.util.ArrayList;

import networkSimulation.IReceiver;
import networkSimulation.ISender;
import networkSimulation.Layer;


/**
 * 
 * Connects Components to other Components given some policy
 * 
 * The Components must of course have the same of ISignal to communicate
 * 
 * @author Erik Borgers
 * 
 */

public abstract class ConnectionBuilder {

	/**
	 * connects outputs of in matrix to inputs of out matrix.
	 * 
	 * @param in
	 * @param out
	 */
	public void connect(Layer in, Layer out) {
		ArrayList<ISender> senders = in.getSenders();
		for (ISender from : senders) {
			connect(from, out);
		}
	}

	/**
	 * connect sender to a matrix
	 * 
	 * @param source
	 * @param to
	 */
	public abstract void connect(ISender source, Layer out);

	/**
	 * connect matrix to receiver
	 * 
	 * @param from
	 * @param receiver
	 */
	public abstract void connect(Layer in, IReceiver receiver);
}
