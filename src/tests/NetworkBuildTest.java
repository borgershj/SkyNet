package tests;

import Neural.Neuron;
import networkCreator.ConnectionBuilder;
import networkCreator.RandomlySelectedCellsConnectionBuilder;
import nullSAF.core.GameEngine;
import nullSAF.core.Log;
import nullSAF.core.XYPoint;

/**
 * 
 * do a test.reader with some tanks, without AI (!)
 * 
 * @author Erik
 * 
 */
public class NetworkBuildTest {

	public NetworkBuildTest() {

		int framerate = 50;
		int neuronrate = framerate / 4; // nr of mean updates per second per neuron must be lower then tickrate for randomness
		int neuronUpdateTime = 1000 / neuronrate;
		ConnectionBuilder c = new RandomlySelectedCellsConnectionBuilder(30, true);
		GameEngine gameEngine = new CluelessGameEngine(framerate);

		new Log("creating units");

		// FIXME create line piece generator

		// create input layer

		// create line counter

		// create output layer

		// create hidden layers

		// /// one networkSimulation created

		gameEngine.run();

		// FIXME test-train networkSimulation with 100 examples

		// done

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
		new NetworkBuildTest();
	}
}
