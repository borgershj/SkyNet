package networkSimulation;

import java.util.ArrayList;

import nullSAF.core.Angle;
import nullSAF.core.Entity;
import nullSAF.core.GameEngine;
import nullSAF.core.RendererList;
import nullSAF.steerers.Steerer;

/**
 * an SignalLink sends signals from a sending Unit to one or more Connection-s
 * 
 * an SignalLink can introduce a delay (equal for all receivers) 
 * 
 * @author Erik Borgers
 * 
 */
public class SignalLink extends Entity {

	/** the Unit or Sensor that sends signals into this SignalLink */
	private ISender sender;

	/** Connector-s that SignalLink feeds into */
	private ArrayList<Connector> connectors;

	/** Unit-s or Actuator-s that SignalLink feeds into */
	private ArrayList<IReceiver> receivers;

	/** delay queue of sent values */
	private SignalQueue<Signal> queue;

	/**
	 * create SignalLink on a sending Cell
	 * 
	 * @param sender
	 */
	public SignalLink(String name, Unit pbody, int delayInMillis) {
		super(name, null);
		this.sender = pbody;
		this.connectors = new ArrayList<Connector>();
		this.receivers = new ArrayList<IReceiver>();
		if (delayInMillis > 0) {
			this.queue = new DelayQueue<Signal>(delayInMillis);
		} else {
			this.queue = new NoDelayQueue<Signal>();
		}
		this.kickAlive(-1, false);
	}

	/**
	 * Connect the SignalLink to a receiving Unit or Actuator
	 * 
	 * @param n
	 */
	public void connectTo(IReceiver receiver) {
		Connector s = receiver.newConnector();
		connectTo(s);
	}

	/**
	 * Connect the SignalLink of a sender to an existing new Connector on the Receiver Unit
	 * 
	 * @param n
	 */
	public void connectTo(Connector connector) {
		this.connectors.add(connector);
		if (connector.getUnit() == null)
			throw new RuntimeException("Connector must be attached");
		if (!(receivers.contains(connector.getUnit()))) {
			receivers.add(connector.getUnit());
		}
	}

	/**
	 * return all connections where this SignalLink feeds in to
	 * 
	 * @return
	 */
	public ArrayList<Connector> getConnectors() {
		return this.connectors;
	}

	/**
	 * return all receivers that this SignalLink feeds in to
	 * 
	 * @return
	 */
	public ArrayList<IReceiver> getReceivers() {
		return this.receivers;
	}

	public ISender getSender() {
		return this.sender;
	}

	/**
	 * give the current SignalLink value at the end of the SignalLink (given the delay)
	 * 
	 * @param function
	 * @param millis
	 */
	public Signal getTipState() {
		// find the connector position in the queue
		// look in the noDelayQueue which value to communicate
		int now = GameEngine.getSimulationTimeInMillis();
		Signal signal = this.queue.getExit(now);
		return signal;
	}

	/**
	 * set the value at the root of the SignalLink (the tip might have some delay) at the current time
	 * 
	 * @param signal
	 * @param pmillis
	 */
	public void setRootSignal(Signal signal) {
		int now = GameEngine.getSimulationTimeInMillis();
		this.queue.setEntrance(signal, now);
	}

	/**
	 * get the value at the root of the SignalLink (the tip might have some delay) at the current time
	 * 
	 * @param signal
	 * @param pmillis
	 */
	public Signal getRootSignal() {
		return this.queue.getEntrance();
	}

	@Override
	protected float bodyHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected float bodyWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Angle getHeadingAngle() {
		return null;
	}

	@Override
	protected int ghostTime() {
		return -1;
	}

	@Override
	protected RendererList renderers() {
		if (this.renderers == null) {
			this.renderers = new RendererList();
			this.renderers.add(new LinkRenderer());
		}
		return this.renderers;
	}

	@Override
	protected Steerer steererStrategy() {
		return null;
	}

	@Override
	public int getDepth() {
		return 1; // above Layer
	}

	@Override
	public void tickNow(int millis) {
		// do nothing
	}

}
