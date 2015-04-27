package networkSimulation;

import java.util.ArrayList;

/**
 * this queue contains values in time
 * 
 * when values are added, all values shift in time for millis
 * 
 * you can retreive a value with some delay
 * 
 * @author Erik Borgers
 * 
 * TODO 8 this class can be made better performant 
 */

public class DelayQueue<T> extends SignalQueue<T> {

	private long totalDelay;
	private long lastUpdate;

	private ArrayList<T> values; // the value queue
	private ArrayList<Long> delayedTimes; // times when to retreive the value (inserted time + delay)

	public DelayQueue(int pTotalDelay) {
		values = new ArrayList<T>();
		delayedTimes = new ArrayList<Long>();
		totalDelay = pTotalDelay;
		lastUpdate = -1;
		if (pTotalDelay <= 0)
			throw new RuntimeException("Delay must be larger then 0 millis");
	}

	@Override
	public void setEntrance(T object, long pNowInMillis) {
		if (pNowInMillis < lastUpdate)
			throw new RuntimeException("Cannot set a new value in the past (newer value was set already)");
		if (pNowInMillis <= lastUpdate)
			throw new RuntimeException("Cannot set a new value at same time");
		values.add(0, object);
		delayedTimes.add(0, new Long(pNowInMillis + totalDelay));
		if (delayedTimes.size() > 1) {
			// clean up	
			// entrees older (with a higer index) then the last one past pNow can be removed
			int n = delayedTimes.size();
			for (int i = n; i > 0; i--) {
				if (delayedTimes.get(i - 1).longValue() < pNowInMillis) { // value i-1 already in the past
					delayedTimes.remove(i);
					values.remove(i);
				}
			}
		}
	}

	@Override
	public T getExit(long pNowInMillis) {
		if (pNowInMillis < 0)
			throw new RuntimeException("Cannot get a value before start time");
		int i = 0;
		for (Long time : delayedTimes) {
			if (time < pNowInMillis)
				return values.get(i);
			i++;
		}
		// otherwise there is no value known yet with this delay. In this case, we return null
		return null;
	}

	@Override
	public long getDelayInMillis() {
		return totalDelay;
	}

	@Override
	public T getEntrance() {
		if (values.size() > 0)
			return values.get(0);
		return null;
	}

}
