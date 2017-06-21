package it.polimi.ingsw.cg30.model.util;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
	/**
	 * the list of all the observers
	 */
	private List<Observer<T>> observers;
	/**
	 * this constructor create a new observable
	 * with is empty list of observers
	 */
	public Observable() {
		observers = new ArrayList<>();
	}
	/**
	 * this method register an observer to this
	 * observable object
	 * @param o the observer
	 */
	public void registerObserver(Observer<T> o) {	
		observers.add(o);
	}
	/**
	 * this method unregister an observer from this
	 * observable object
	 * @param o the observer
	 */
	public void unregisterObserver(Observer<T> o) {
		this.observers.remove(o);
	}
	/**
	 * this method notify all the observers with a change
	 * @param change
	 */
	public void notifyObservers(T change) {
		for (Observer<T> o : this.observers) {
			o.update(change);
		}
	}
}
