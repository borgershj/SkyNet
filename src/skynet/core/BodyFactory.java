package skynet.core;

public abstract class BodyFactory {

	/**
	 * create a body for a Neuron
	 * 
	 * @param neuron
	 * @return
	 */
	public abstract Body newBody(Neuron neuron);

}
