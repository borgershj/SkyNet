package networkCreator;

import networkSimulation.IReceiver;
import networkSimulation.ISender;
import networkSimulation.Layer;

/**
 * 
 * Fully connects neurons or layers Axon-s with 0 delay
 * 
 * @author Erik Borgers
 * 
 */

public class FullyConnectionBuilder extends ConnectionBuilder {

	private int delayInMillis;

	/**
	 * connect with zero delay
	 */
	public FullyConnectionBuilder() {
		this(0);
	}

	/**
	 * connect with constant delay
	 * 
	 * @param pdelayInMillis
	 */
	public FullyConnectionBuilder(int pdelayInMillis) {
		this.delayInMillis = pdelayInMillis;
	}

	@Override
	public void connect(Layer in, IReceiver receiver) {
		for (ISender from : in.getSenders()) {
			from.connectToCell(receiver, this.delayInMillis);
		}
	}

	@Override
	public void connect(ISender source, Layer out) {
		for (IReceiver to : out.getReceivers()) {
			source.connectToCell(to, this.delayInMillis);
		}
	}
}
