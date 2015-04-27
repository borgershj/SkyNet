package networkSimulation;

/**
 * a NoDelayQueue with no delay
 * 
 * @author Erik Borgers
 * 
 * @param <T>
 */

public class NoDelayQueue<T> extends SignalQueue<T> {

	private T value;
	private long lastUpdate;

	public NoDelayQueue() {
		value = null;
		lastUpdate = -1;
	}

	@Override
	public void setEntrance(T object, long pNowInMillis) {
		// there is no delay, just overwrite the value
		value = object;
		lastUpdate = pNowInMillis;
	}

	@Override
	public T getExit(long pNowInMillis) {
		if (pNowInMillis < lastUpdate)
			throw new RuntimeException("Cannot get values from the past");
		// return latest added value or null if no value set
		return value;
	}

	@Override
	public long getDelayInMillis() {
		return 0;
	}

	@Override
	public T getEntrance() {
		return value;
	}

}
