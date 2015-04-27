package worldOfArithmics;

import java.util.ArrayList;

import networkSimulation.Signal;
import networkSimulation.IReceiver;
import networkSimulation.ISender;
import networkSimulation.ISignal;
import networkSimulation.SignalLink;
import networkSimulation.Unit;
import nullSAF.core.XYPoint;
import nullSAF.renderers.Renderer;

/**
 * 
 * generates random Signal values
 * 
 * @author Erik Borgers
 * 
 */

// FIXME make this inherit from World

public class RandomValueGenerator extends Unit implements ISender {

	private SignalLink signalLink;
	Signal signal;

	public RandomValueGenerator(String name, XYPoint p, int updateTimeInMillis) {
		super(name, p, updateTimeInMillis);
		this.signalLink = new SignalLink(this);
		updateNow(0); // call once to set signal
	}

	@Override
	public void connectToCell(IReceiver receiver, int millisDelay) {
		this.signalLink.connectLink(receiver, millisDelay);
	}

	@Override
	public boolean connectsToCell(IReceiver receiver) {
		if (this.signalLink.getReceivers().contains(receiver))
			return true;
		return false;
	}

	@Override
	public void updateNow(int pmillis) {
		// update the cell and feed the axon
		this.signal = new Signal(Signal.random());
		this.signalLink.setSignal(this.signal, pmillis);
		// new Log(getName() + " was ticked for " + pmillis);
	}

	@Override
	public ArrayList<IReceiver> getReceivers() {
		return this.signalLink.getReceivers();
	}

	@Override
	protected float bodyHeight() {
		return 5f;
	}

	@Override
	protected float bodyWidth() {
		return 5f;
	}

	@Override
	public final int getDepth() {
		return 10; // draw on top of matrixes/layers
	}

	@Override
	protected ArrayList<Renderer> renderers() {
		if (this.renderers == null) {
			this.renderers = new ArrayList<Renderer>();
			this.renderers.add(new RandomValueGeneratorRenderer());
		}
		return this.renderers;
	}

	@Override
	public SignalLink getSignalLink() {
		return this.signalLink;
	}

	@Override
	public ISignal getState() {
		return this.signal;
	}

}
