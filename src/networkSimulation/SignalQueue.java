package networkSimulation;

/**
 * this queue contains values in time
 * 
 * when values are added, all values shift in time for millis
 * 
 * you can retreive a value with some delay
 * 
 * @author Erik Borgers
 * 
 */

public abstract class SignalQueue<T> {

	/**
	 * add a value to the beginning of the delay queue, at the given time
	 * 
	 * @param object
	 * @param pmillis
	 */
	public abstract void setEntrance(T object, long pNowInMillis);

	/**
	 * return the Object with the correct delay at end of the queue
	 * 
	 * if no object was found for this delay, return null
	 * 
	 * @param integer
	 * @return
	 */
	public abstract T getExit(long pNowInMillis);

	/**
	 * get the total delay for this queue
	 * @return
	 */
	public abstract long getDelayInMillis();

	/**
	 * return the Object at the start of the queue (latest set)
	 * 
	 * if no object was found for this delay, return null
	 * 
	 * @param integer
	 * @return
	 */
	public abstract T getEntrance();
}
