package skynet.core;

/** body of a Neuron */

public abstract class Body {

	protected State state = null; // state of the Neuron (output)
	private Neuron neuron;

	public Body(Neuron neuron) {
		this.neuron = neuron;
	}

	/**
	 * create a new (unattached) Synaps for a neuron
	 * 
	 * @param neuron
	 * @return
	 */
	public abstract Synaps newSynaps(Neuron neuron);

	/**
	 * calculate a new state and/or adjust synapses (learn)
	 * 
	 * @param pmillis
	 */
	public abstract void tick(int pmillis);

	/**
	 * create and set the State
	 */
	public abstract void initState();

	public State getState() {
		return state;
	}
}
