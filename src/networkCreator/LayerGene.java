
package networkCreator;

import networkSimulation.Layer;

/**
 * @author Erik Borgers
 *
 */
public class LayerGene {

	/**
	 * 
	 */
	public LayerGene() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * How to store information for connecting to other layers?
	 * 
	 * In case of one output layer
	 * 
	 * - name of layer or "position" (upper, lower, left, right?) - how much (fully, sparse, random?) - what
	 * type (delay=X millis)
	 */
	public Layer getConnectTo() {
		// TODO
		return null;
	}

	public RandomlySelectedCellsConnectionBuilder getConnector() {
		// TODO
		return null;

	}

}
