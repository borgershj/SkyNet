package networkSimulation;

import java.util.ArrayList;

import nullSAF.core.Angle;
import nullSAF.core.Entity;
import nullSAF.core.RendererList;
import nullSAF.core.XYPoint;
import nullSAF.steerers.Steerer;

/**
 * a Layer contains Unit-s (in whatever configuration)
 * 
 * The position and dimensions of a Layer is determined by the Unit-s in it (mean)
 * 
 * Layer-s can be drawn
 * 
 * Layer-s can be grown and interconnected to other Unit-s or Layer-s with strategies (see other package)
 * 
 * @author Erik Borgers
 * 
 */

public class Layer extends Entity {

	private ArrayList<Unit> units;
	private float height;
	private float width;

	public Layer(String name, int updateTimeInMillis) {
		super(name, null);
		// a layer has no initial position, this is determined by the Unit-s inside it
		this.units = new ArrayList<Unit>();
		this.kickAlive(-1, false); // never tick. This is for drawing in overlays only
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	/**
	 * add a Unit to the Layer. This will not change interconnectiveness.
	 * 
	 */
	public final void add(Unit n) {
		this.units.add(n);
		// recalculate dimensions
		float minx = 10000, miny = 10000, maxx = -10000, maxy = -10000;
		for (Unit c : this.units) {
			float x = c.getPosition().getX();
			float y = c.getPosition().getY();
			if (x < minx) {
				minx = x;
			}
			if (y < miny) {
				miny = y;
			}
			if (x > maxx) {
				maxx = x;
			}
			if (y < maxy) {
				maxy = y;
			}
		}
		this.setPosition(new XYPoint((maxx + minx) / 2f, (miny + maxy) / 2f));
		this.height = maxy - miny;
		this.width = maxx - minx;
	}

	/**
	 * see if this Unit is in the layer
	 * 
	 * @param n
	 * @return
	 */
	public final boolean containsUnit(Unit n) {
		return this.units.contains(n);
	}

	@Override
	protected final float bodyHeight() {
		return this.height;
	}

	@Override
	protected final float bodyWidth() {
		return this.width;
	}

	@Override
	protected final RendererList renderers() { // TODO 3: for now, draw all Layer-s in the same way
		RendererList l = new RendererList();
		l.add(new LayerOutlineRenderer());
		return null;
	}

	@Override
	public Angle getHeadingAngle() {
		return null;
	}

	@Override
	protected int ghostTime() {
		return -1;
	}

	@Override
	protected Steerer steererStrategy() {
		return null;
	}

	@Override
	public int getDepth() {
		return 0;
	}

	@Override
	public void tickNow(int millis) {
		// do nothing, layers do not tick
	}
}
