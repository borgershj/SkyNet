package skynet.core;

/**
 * position in the brain (absolute or relative)
 * 
 * @author Erik
 *
 */

public class Position {

	float x;
	float y;
	float z;

	public Position(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
