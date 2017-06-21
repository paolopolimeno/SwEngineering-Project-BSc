package it.polimi.ingsw.cg30.model;

import java.io.Serializable;

import it.polimi.ingsw.cg30.model.Player;

public class IndicatorDisk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7418983964489474077L;
	private transient Player player;
	private Slot slot;

	/**
	 * 
	 * @param player
	 * @throws NullPointerException if the player is null
	 */
	public IndicatorDisk(Player player) {
		
		if(player == null)
			throw new NullPointerException();
		this.player = player;
	}
	/**
	 * set the slot of the indicator disk
	 * @param slot
	 * @throws NullPointerException if the slot is null
	 */
	public void setSlot(Slot slot) {
		
		if(slot == null)
			throw new NullPointerException();
		
		this.slot = slot;
	}
	/**
	 *
	 * @return the slot of indicator disk
	 */
	public Slot getSlot() {
		return slot;
	}
	/**
	 * 
	 * @return the player who owns the indicator disk
	 */
	public Player getPlayer() {
		return player;
	}
}