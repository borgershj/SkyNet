package networkSimulation;

import java.util.ArrayList;

/**
 * 
 * a Unit is the mother of all types like Neurons, States, etc.
 * 
 * * FIXME implement different ways of ticking (atonomous triggered by time, or triggered by matrix)
 * 
 * @author Erik Borgers
 * 
 */

public abstract class Unit extends Entity implements ISender, IReceiver {
	// FIXME : implements/extends iets zodat je hem in de game engine wel kan drawen (hoeft niet de rekenslag te zijn)!  {

	/** Connector-s of this Cell */
	private ArrayList<Connector> connectors;

	/** a single SignalLink, the output SignalLink, connected to 0 or more other unique Unit-s (double connections are not allowed), with a delay */
	private SignalLink signalLink;

	public Unit(String name, XYPoint pos, int updateTimeInMillis, boolean randomize, int delayInMillis) {
		super(name, pos);
		this.connectors = new ArrayList<Connector>();
		String linkName = name + "-signalLink";
		this.signalLink = new SignalLink(linkName, this, delayInMillis);
		this.kickAlive(updateTimeInMillis, randomize);
	}

	/**
	 * connect to Unit, creating a new Connection on the fly
	 * 
	 * @param c
	 */
	public final void connectTo(IReceiver to) {
		connectTo(newConnector());
	}

	/**
	 * connect to a specific connector
	 * 
	 * @param c
	 */
	public final void connectTo(Connector to) {
		signalLink.connectTo(to);
	}

	/**
	 * return the units state (the last broadcasted ISignal)
	 * @return
	 */
	public Signal getState() {
		return this.signalLink.getRootSignal();
	}

	public ArrayList<IReceiver> getReceivingCells() {
		return this.signalLink.getReceivers();
	}

	public ArrayList<Connector> getConnectors() {
		return this.connectors;
	}

	public void broadCast(Signal signal) {
		this.signalLink.setRootSignal(signal);
	}

	public SignalLink getSignalLink() {
		return this.signalLink;
	}
}
