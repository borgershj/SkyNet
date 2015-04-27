package networkSimulation;

import nullSAF.core.BaseGameObject;
import nullSAF.core.Entity;
import nullSAF.core.GameEngineObjectManager;
import nullSAF.maps.Map;
import nullSAF.maps.Overlay;

/**
 * this renders a MapLayer with all Unit-s in their current states
 * 
 * @author Erik Borgers
 * 
 */
public class NodeOverlay extends Overlay {

	public NodeOverlay(int millis) {
		super(millis);
	}

	/**
	 * paint entities
	 */
	@Override
	public void renderOverlay(Map map) {

		// draw alive objects that are entities in current state
		for (BaseGameObject e : GameEngineObjectManager.getObjects()) {
			if (e instanceof Unit)
				((Entity)e).renderEntity(map);
		}

		// also render corpse objects to be drawn as gosts
		for (BaseGameObject e : GameEngineObjectManager.getCorpses()) {
			if (e instanceof Unit)
				((Entity) e).renderEntity(map);
		}
	}

}
