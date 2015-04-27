package networkSimulation;

import java.awt.Color;

import nullSAF.core.BaseGameObject;
import nullSAF.core.GameEngineObjectManager;
import nullSAF.core.XYPoint;
import nullSAF.maps.Map;
import nullSAF.maps.Overlay;

/**
 * this Overlay renders all the SignalLink-s in a Brain at once
 * 
 * @author Erik Borgers
 * 
 */
public class CellLinkOverlay extends Overlay {

	public CellLinkOverlay(int millis) {
		super(millis);
	}

	/**
	 * paint entities
	 */
	@Override
	public void renderOverlay(Map map) {
		// (re)draw all connections in the layer
		// this could be done most efficiently only after after a reconfiguration
		new Log("Rerendering the layer connections");
		map.setColor(Color.red);
		for (BaseGameObject e : GameEngineObjectManager.getObjects()) {
			if (e instanceof SignalLink) {
				ISender sender = (ISender) e;
				for (IReceiver s : sender.getReceivers()) {
					XYPoint from = ((Unit) sender).getPosition();
					XYPoint to = ((Unit) s).getPosition();
					map.drawArrow(from, to); ?? gaan we dat zo doen, of maken we renderers in Link
				}
			}
		}
	}
}
