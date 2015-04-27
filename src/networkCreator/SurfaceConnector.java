package networkCreator;

import networkSimulation.IReceiver;
import networkSimulation.ISender;
import networkSimulation.Layer;
import nullSAF.core.NYI;


/**
 * 
 * TODO: connect with a probability on distance (for example for a matrix or a receptive field)
 * 
 * TODO: delay could vary with distance too
 * 
 * @author Erik
 * 
 */

public class SurfaceConnector extends ConnectionBuilder {

	private int delayInMillis;

	/**
	 * connect with zero delay to cells with same XY, within the radius
	 * 
	 * radius
	 */
	public SurfaceConnector(float radius) {
		this(0);
	}

	/**
	 * connect with zero delay to the closest N cells (with similar XY)
	 * 
	 * radius
	 */
	public SurfaceConnector(Integer N) {
		this(0);
	}

	/**
	 * connect with constant delay
	 * 
	 * @param pdelayInMillis
	 */
	public SurfaceConnector(int pdelayInMillis) {
		this.delayInMillis = pdelayInMillis;
		new NYI();
	}

	@Override
	public void connect(ISender source, Layer to) {
		new NYI();
	}

	@Override
	public void connect(Layer from, IReceiver receiver) {
		new NYI();
	}

}
