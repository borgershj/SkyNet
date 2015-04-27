package skynet.test.mockups;

import skynet.core.Body;
import skynet.core.Neuron;
import skynet.core.Synaps;

public class MyBody extends Body {

	public MyBody(Neuron neuron) {
		super(neuron);
	}

	@Override
	public void tick(int pmillis) {
		// TODO Auto-generated method stub

	}

	@Override
	public Synaps newSynaps(Neuron neuron) {
		return new MySynaps(neuron);
	}

	@Override
	public void initState() {
		state = new MyState(0.0f);
	}

}
