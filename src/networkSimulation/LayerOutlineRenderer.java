package networkSimulation;

import java.awt.Color;

import nullSAF.maps.Map;
import nullSAF.renderers.Renderer;

/**
 * draws a color around all Unit-s in it
 */

public class LayerOutlineRenderer extends Renderer<Layer> {

	@Override
	public void renderAlive(Map map, Layer l) {
		map.setColor(Color.BLUE);
		map.drawRect(l.getPosition(), l.getBodyWidth(), l.getBodyHeight());
		// do not draw the Unit-s themselves, because these are drawn by there own renderer
	}

	@Override
	public void renderDead(Map map, Layer l) {
		map.setColor(Color.GREEN);
		map.drawRect(l.getPosition(), l.getBodyWidth(), l.getBodyHeight());
		// nothing
	}
}
