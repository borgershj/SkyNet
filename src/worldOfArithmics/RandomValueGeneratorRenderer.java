package worldOfArithmics;

import java.awt.Color;

import networkSimulation.Signal;
import networkSimulation.ISignal;
import nullSAF.core.Log;
import nullSAF.maps.Map;
import nullSAF.renderers.Renderer;


public class RandomValueGeneratorRenderer extends Renderer<RandomValueGenerator> {

	@Override
	public void renderAlive(Map map, RandomValueGenerator n) {

		ISignal s = n.getState();
		float v = ((Signal) s).get(); // FIXME van dit vieze gecast wil ik nog af!

		new Log("Render random:" + n.getName() + " value: " + v);

		if (v > 0f) {
			map.setColor(Color.BLUE);
			map.fillCircle(n.getPosition(), v * n.getBodyWidth());
		} else {
			map.setColor(Color.CYAN);
			map.fillCircle(n.getPosition(), -v * n.getBodyWidth());
		}
	}

	@Override
	public void renderDead(Map map, RandomValueGenerator n) {
		map.setColor(Color.black);
		map.fillCircle(n.getPosition(), n.getBodyWidth());
	}

}
