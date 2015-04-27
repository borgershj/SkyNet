package networkSimulation;

import java.awt.Color;

import nullSAF.core.Log;
import nullSAF.maps.Map;
import nullSAF.renderers.Renderer;

/**
 * Draw a neuron
 * 
 * @author Erik
 *
 */
public class UnitRenderer extends Renderer<Unit> {

	@Override
	public void renderAlive(Map map, Unit n) {
		Signal v = n.getState();
		float f = ((Signal) v).get();

		new Log("Render neuron:" + n.getName() + " value: " + v);

		if (f > 0f) {
			map.setColor(Color.GREEN);
			map.fillCircle(n.getPosition(), f * n.getBodyWidth());
		} else {
			map.setColor(Color.RED);
			map.fillCircle(n.getPosition(), -f * n.getBodyWidth());
		}
	}

	@Override
	public void renderDead(Map map, Unit n) {
		map.setColor(Color.black);
		map.fillCircle(n.getPosition(), n.getBodyWidth());
	}

}
