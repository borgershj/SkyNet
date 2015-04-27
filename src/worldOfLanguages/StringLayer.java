package worldOfLanguages;

import java.util.ArrayList;

import networkSimulation.Layer;
import nullSAF.core.NYI;
import nullSAF.renderers.Renderer;


/**
 * String is encoded into Unit values and vice versa
 * 
 * String to chars -> every char is set of neurons? Every char is a row (N), length == M a-z + ' ' + '.' + '?', others are deleted (A->a +
 * seperate CapsLock?)
 * 
 * String to di-grams
 * 
 * String to tri-grams
 * 
 * String to syllables
 * 
 * TODO how to encode? Make use of frequencies? Kohonen mapping?
 * 
 * @author Erik Borgers
 * 
 */
public class StringLayer extends Layer {

	public StringLayer(String name, int length) {
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
