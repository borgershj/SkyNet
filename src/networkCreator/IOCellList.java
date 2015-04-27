package networkCreator;

import java.util.ArrayList;

import networkSimulation.IOCell;
import nullSAF.core.ArrayListRandomized;


/**
 * list containing Components in random order
 * 
 * you can select the IOCell with the lowest/highest nr of inputs/outputs
 */

public class IOCellList extends ArrayListRandomized<IOCell> {

	public static IOCellList newArrayListRandomized(ArrayList<IOCell> cells) {
		IOCellList l = new IOCellList();
		for (IOCell n : cells) {
			l.add(n);
		}
		return l;
	}

	/**
	 * return IOCell with the lowest output count
	 * 
	 * @return
	 */
	public IOCell lowestOutputNrs() {
		int min = 1000000000;
		IOCell result = null;
		for (IOCell n : this) {
			if (n.getReceivers().size() < min) {
				min = n.getReceivers().size();
				result = n;
			}
		}
		return result;
	}

	/**
	 * return IOCell with the highest output count
	 * 
	 * @return
	 */
	public IOCell highestOutputNrs() {
		int max = -1;
		IOCell result = null;
		for (IOCell n : this) {
			if (n.getReceivers().size() > max) {
				max = n.getReceivers().size();
				result = n;
			}
		}
		return result;
	}

	public IOCell lowestInputNrs() {
		int min = 1000000000;
		IOCell result = null;
		for (IOCell n : this) {
			if (n.getReceivers().size() < min) {
				min = n.getSenders().size();
				result = n;
			}
		}
		return result;
	}

	public IOCell highestInputNrs() {
		int max = -1;
		IOCell result = null;
		for (IOCell n : this) {
			if (n.getReceivers().size() > max) {
				max = n.getSenders().size();
				result = n;
			}
		}
		return result;
	}

}
