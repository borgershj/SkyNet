package networkCreator;

import networkSimulation.IReceiver;
import networkSimulation.ISender;
import networkSimulation.Layer;

/**
 * builds connections between two layers, nr of outputs is constant
 * 
 * connects to a certain percentage of the output cells (when percentage = true) or exact N randomly choosen
 * 
 * connections are spread, so that all input/output cell will have about the same number of connections
 * 
 * note: it is not really random because:
 * 
 * - we want every output and every input connected at least once.
 * 
 * - we do not want 2 connections between exactly the same neurons.
 * 
 * - outputs are connected about the same amount of time
 * 
 * TODO: now only a fixed delay
 * 
 * @author Erik Borgers
 * 
 */

public class RandomlySelectedCellsConnectionBuilder extends ConnectionBuilder {

	private int N;
	private boolean percentage;
	private int delayInMillis;

	/**
	 * connect to N % of the output cells (percentage = true) or exact N randomly choosen
	 * 
	 * @param ppercentage
	 */
	public RandomlySelectedCellsConnectionBuilder(int pN, boolean ppercentage) {
		this(pN, ppercentage, 0);
	}

	/**
	 * connect to N percentage number of the output cells (percentage = true) or exact N
	 * 
	 * @param N
	 */
	public RandomlySelectedCellsConnectionBuilder(int pN, boolean ppercentage, int pdelayInMillis) {
		this.N = pN;
		this.percentage = ppercentage;
		this.delayInMillis = pdelayInMillis;
		if (this.N <= 0)
			throw new RuntimeException("percentage/N should be positive: " + this.percentage);
		if ((this.percentage == true) && (this.N > 100))
			throw new RuntimeException("percentage should not be more then 100: " + this.percentage);
	}

	@Override
	/**
	 * connect the source to N randomly choosen outputs (or N% of the total outputs)
	 */
	public void connect(ISender source, Layer lto) {
		int nrOfConnectionsToMake;
		IOCellList tos = IOCellList.newArrayListRandomized(lto.getReceivers());
		if (this.percentage == true) {
			nrOfConnectionsToMake = (int) (tos.size() * (this.N / 100f));
			if (nrOfConnectionsToMake == 0) {
				nrOfConnectionsToMake = 1;
			}
		} else {
			nrOfConnectionsToMake = this.N;
			if (this.N > lto.getReceivers().size())
				throw new RuntimeException("N larger then output layer size");
		}
		int connectionsMade = 0;
		while (connectionsMade < nrOfConnectionsToMake) {
			// select neurons, ordered on lowest nr of connections
			IReceiver to = tos.lowestInputNrs();
			if (source.connectsToCell(to))
				// already connected, but I think this should not happen in the algorithm
				throw new RuntimeException("neurons already connected, this is unexpected!");
			source.connectToCell(to, this.delayInMillis);
			connectionsMade++;
			tos.reorder();
		}
	}

	@Override
	/**
	 * connect the receiver from N randomly choosen inputs (or N% of the total inputs)
	 */
	public void connect(Layer lfrom, IReceiver receiver) {
		int nrOfConnectionsToMake;
		IOCellList froms = IOCellList.newArrayListRandomized(lfrom.getSenders());
		if (this.percentage == true) {
			nrOfConnectionsToMake = (int) (froms.size() * (this.N / 100f));
			if (nrOfConnectionsToMake == 0) {
				nrOfConnectionsToMake = 1;
			}
		} else {
			nrOfConnectionsToMake = this.N;
			if (this.N > lfrom.getSenders().size())
				throw new RuntimeException("N larger then input layer size");
		}
		int connectionsMade = 0;
		while (connectionsMade < nrOfConnectionsToMake) {
			// select neurons, ordered on lowest nr of connections
			ISender from = froms.lowestOutputNrs();
			if (from.connectsToCell(receiver))
				// already connected, but I think this should not happen in the algorithm
				throw new RuntimeException("neurons already connected, this is unexpected!");
			from.connectToCell(receiver, this.delayInMillis);
			connectionsMade++;
			froms.reorder();
		}
	}

}
