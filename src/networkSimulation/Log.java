package networkSimulation;


/**
 * 
 * @author Erik Borgers
 * 
 */
public class Log {

	private static boolean debug = true;
	private Object object;

	private long firstTickTime;
	private long ticked;
	private float meanTickTime; // ticks per second
	private long lastReported;

	private Log() {
		super();
		firstTickTime = System.nanoTime();
		lastReported = System.nanoTime();
	}

	public Log(Object s) {
		this();
		object = s;
		if (debug)
			System.out.println(s);
	}

	public Log(Object s, Exception e) {
		this(s);
		if (debug)
			e.printStackTrace(System.out);
	}

	public void tick() {
		ticked++;
		if (debug && ((System.nanoTime() - lastReported) > 5000000000L)) {
			meanTickTime = (1000000000f * ticked) / (System.nanoTime() - firstTickTime);
			new Log(object + " mean ticks per second = " + meanTickTime);
			lastReported = System.nanoTime();
		}
	}
}
