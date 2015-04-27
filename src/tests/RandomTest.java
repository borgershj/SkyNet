package tests;

import Neural.Neuron;
import networkCreator.ConnectionBuilder;
import networkCreator.FullyConnectionBuilder;
import networkSimulation.Layer;
import nullSAF.core.GameEngine;
import nullSAF.core.Log;
import nullSAF.core.XYPoint;
import worldOfArithmics.RandomValueGenerator;

/**
 * 
 * do a test.reader with some tanks, without AI (!)
 * 
 * @author Erik
 * 
 */
public class RandomTest {

	public RandomTest() {
		int tickrate = 100;
		int neuronrate = 25; // nr of mean updates per second per neuron must be lower then tickrate for randomness
		int neuronUpdateTime = 1000 / neuronrate;
		ConnectionBuilder c = new FullyConnectionBuilder();
		GameEngine gameEngine = new CluelessGameEngine(neuronrate);

		new Log("creating units");

		RandomValueGenerator s = new RandomValueGenerator("myRandom1", new XYPoint(10, 80), neuronUpdateTime);

		Neuron n1 = new MyNeuron("layer1neuron1", new XYPoint(10, 40), neuronUpdateTime);
		Layer l1 = new Layer("layer 1");
		l1.add(n1);

		Neuron n2 = new MyNeuron("layer2neuron1", new XYPoint(10, 10), neuronUpdateTime);
		Layer l2 = new Layer("layer 2");
		l2.add(n2);

		c.connect(l1, l2);

		c.connect(s, l1);

		gameEngine.run();
	}

	class MyNeuron extends Neuron {

		public MyNeuron(String string, XYPoint point, int updateTimeInMillis) {
			super(string, point, 0, updateTimeInMillis);
		}

		@Override
		protected float bodyHeight() {
			return 1f;
		}

		@Override
		protected float bodyWidth() {
			return 1f;
		}

	}

	public static void main(String[] args) {
		new RandomTest();
	}
}
