package skynet.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import skynet.core.Neuron;
import skynet.test.mockups.MyBodyFactory;

/**
 * 
 * @author Erik Borgers zzz
 *
 */
public class Test1 {

	public MyBodyFactory bf;

	@Before
	public void createFactory() throws Exception {
		bf = new MyBodyFactory();
	}

	@After
	public void clearFactory() throws Exception {
		bf = null;
	}

	@Test
	public void CreateTest() {
		Neuron n1 = new Neuron("a", bf);
		Neuron n2 = new Neuron("b", bf);
		assertEquals("id nr is wrong", 2, n2.getId());
		assertEquals("units nrs is wrong", 2, Neuron.getNrs());
		assertEquals("name is wrong", "b", n2.getName());
	}

	@Test
	public void ConnectTest() {
		Neuron n1 = new Neuron("a", bf);
		Neuron n2 = new Neuron("b", bf);
		Neuron n3 = new Neuron("c", bf);
		Neuron o1 = new Neuron("output", bf);
		o1.addInput(n1);
		o1.addInput(n2);
		n3.addOutput(o1);
		n1.addInput(o1);
		assertEquals("wrong number of inputs", o1.inputs.size(), 3);
		assertEquals("wrong number of outputs", o1.outputs.size(), 1);
		assertEquals("wrong number of inputs", n1.inputs.size(), 1);
		o1.removeInput(n2);
		assertEquals("wrong number of inputs", o1.inputs.size(), 2);
		o1.removeInput(n2); // already removed
		assertEquals("wrong number of inputs", o1.inputs.size(), 2);
	}

}
