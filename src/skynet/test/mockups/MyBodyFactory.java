package skynet.test.mockups;

import skynet.core.Body;
import skynet.core.BodyFactory;
import skynet.core.Neuron;

public class MyBodyFactory extends BodyFactory {

	public MyBodyFactory() {
		super();
	}

	@Override
	public Body newBody(Neuron neuron) {
		return new MyBody(neuron);
	}

}
