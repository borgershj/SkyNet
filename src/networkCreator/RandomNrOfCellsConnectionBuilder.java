package networkCreator;

/**
 * connects to a random nr of output cells (can vary per cell) to every input
 * 
 * when percentage is true: random nr of connections = between 1 and 2*pN percentage of output cells (mean is pN %)
 * 
 * when percentage is false : random number of connections is between 0 and 2*pN 
 * 
 * 
 */

import networkSimulation.IReceiver;
import networkSimulation.ISender;
import networkSimulation.Layer;

public class RandomNrOfCellsConnectionBuilder extends ConnectionBuilder {

	private int N;
	private boolean percentage;
	private int delayInMillis;

	public RandomNrOfCellsConnectionBuilder(int pN, boolean ppercentage) {
		this(pN, ppercentage, 0);
	}

	/**
	 * connect to N percentage number of the output cells (percentage = true) or exact N
	 * 
	 * @param N
	 */
	public RandomNrOfCellsConnectionBuilder(int pN, boolean ppercentage, int pdelayInMillis) {
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
	 * connect the source to mean N randomly choosen different outputs (or N% of the total outputs)
	 * 
	 * connect to at least one, to prevent cells without output
	 */
	public void connect(ISender source, Layer lto) {
		double connectionProbability;
		IOCellList tos = IOCellList.newArrayListRandomized(lto.getReceivers());
		if (this.percentage == true) {
			connectionProbability = this.N / 100f;
		} else {
			if (this.N > lto.getReceivers().size())
				throw new RuntimeException("N larger then output layer size");
			if (lto.getReceivers().size() == 0)
				throw new RuntimeException("output layer size is 0");
			connectionProbability = this.N / lto.getReceivers().size();
		}
		for (IReceiver to : tos) {
			if (Math.random() <= connectionProbability) {
				source.connectToCell(to, this.delayInMillis);
			}
		}
		// check: if nothing was choosen, still connect one
		if (source.getReceivers().size() == 0) {
			source.connectToCell(tos.get(0), this.delayInMillis);
		}
	}

	@Override
	/**
	 * connect the receiver with mean N randomly choosen inputs (or N% of the total inputs)
	 * 
	 * connect to at least one, to prevent cells without inputs
	 */
	public void connect(Layer lfrom, IReceiver receiver) {
		double connectionProbability;
		IOCellList froms = IOCellList.newArrayListRandomized(lfrom.getSenders());
		if (this.percentage == true) {
			connectionProbability = this.N / 100f;
		} else {
			if (this.N > lfrom.getSenders().size())
				throw new RuntimeException("N larger then input layer size");
			if (lfrom.getSenders().size() == 0)
				throw new RuntimeException("input layer size is 0");
			connectionProbability = this.N / lfrom.getSenders().size();
		}
		for (ISender from : froms) {
			if (Math.random() <= connectionProbability) {
				from.connectToCell(receiver, this.delayInMillis);
			}
		}
		// check: if nothing was choosen, still connect one
		if (receiver.getSenders().size() == 0) {
			froms.get(0).connectToCell(receiver, this.delayInMillis);
		}
	}
}
