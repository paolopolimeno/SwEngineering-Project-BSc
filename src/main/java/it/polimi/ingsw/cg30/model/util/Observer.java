package it.polimi.ingsw.cg30.model.util;

public interface Observer<T> {
	/**
	 * this is the default method of the interface
	 * observer for update the observer with a change
	 * @param change
	 */
	public void update(T change);
}