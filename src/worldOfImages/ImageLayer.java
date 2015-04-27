package worldOfImages;

import java.util.ArrayList;

import networkSimulation.Layer;
import nullSAF.core.NYI;
import nullSAF.renderers.Renderer;


/**
 * Image is encoded into Unit values and vice versa
 * 
 * Image is N x M in size (or mapped onto NxM values)
 * 
 * Unit is bit, greyvalue, red, blue or green value of same dimension
 * 
 * @author Erik Borgers
 * 
 */
public class ImageLayer extends Layer {

	public ImageLayer(String name, int width, int height) {
		super(name);
		new NYI();
	}

	@Override
	public int getDepth() {
		return 2;
	}

	@Override
	protected ArrayList<Renderer> renderers() {
		// TODO Auto-generated method stub
		return null;
	}

}
