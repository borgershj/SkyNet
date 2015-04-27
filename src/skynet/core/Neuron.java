package skynet.core;

import java.util.ArrayList;

/**
 * Basic Neuron: multiple inputs, one output. Synapses, a learning algorithm and a algorithm that calculates the state
 * 
 * @author Erik Borgersje yy pushed
 * 
 */
public class Neuron {

	private static int nrs = 0; // unique id
	private int id;
	private String name; // printable name

	public Position position = null; // position in the brain
	public ArrayList<Neuron> inputs = new ArrayList<Neuron>(); // connected input units
	public ArrayList<Neuron> outputs = new ArrayList<Neuron>(); // connected output units
	private ArrayList<Synaps> synapses = new ArrayList<Synaps>(); // synapses for input units
	private Body body;

	// create Neuron with a certain update time
	public Neuron(String name, BodyFactory bodyFactory) {
		this.name = name;
		nrs++;
		id = nrs; // give Neuron a unique ID

		this.body = bodyFactory.newBody(this); // create a body
	}

	public void setPosition(float x, float y, float z) {
		if (position == null)
			position = new Position(x, y, z);
		else
			position.set(x, y, z);
	}

	public Synaps addInput(Neuron from) {
		Synaps s = body.newSynaps(this);
		synapses.add(s);
		inputs.add(from);
		from.outputs.add(this);
		return s;
	}

	public Synaps addOutput(Neuron to) {
		return to.addInput(this);
	}

	public boolean removeInput(Neuron from) {
		int i = inputs.indexOf(from);
		if (i < 0)
			return false;
		inputs.remove(i);
		synapses.remove(i);
		from.outputs.remove(this);
		return true;
	}

	public boolean removeOutput(Neuron to) {
		return to.removeInput(this);
	}

	public void removeInputs() {
		while (inputs.size() > 1) {
			removeInput(inputs.get(0));
		}
	}

	public void removeOutputs() {
		while (outputs.size() > 1) {
			removeOutput(outputs.get(0));
		}
	}

	public State getState() {
		return body.getState();
	}

	/**
	 * tick the neuron: read all connected input states, calculate the new state value, possibly train too.
	 * we do not allow separate calculate state and train runs. The body should know!
	 * 
	 * p milliseconds have passed since the last update
	 * 
	 */
	public void tick(int pmillis) {
		// update the cell
		body.tick(pmillis);
	}

	public static int getNrs() {
		return nrs;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
