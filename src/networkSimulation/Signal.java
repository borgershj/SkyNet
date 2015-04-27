package networkSimulation;

/**
 * value is from -1 to +1
 * 
 * @author Erik Borgers
 * 
 */

public class Signal {

	private float state;

	public Signal(float defaultValue) {
		this.state = defaultValue;
	}

	public float get() {
		return this.state;
	}

	void set(float value) {
		this.state = value;
		if (state > 1.0f)
			state = 1.0f;
		if (state < -1.0f)
			state = -1.0f;
	}

	public static float random() {
		return -1.0f + (2.0f * (float) Math.random());
	}

	@Override
	public String toString() {
		return new Float(this.state).toString();
	}

}
