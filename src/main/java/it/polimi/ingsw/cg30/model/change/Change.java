package it.polimi.ingsw.cg30.model.change;

import java.io.Serializable;
import java.util.UUID;

import it.polimi.ingsw.cg30.model.Player;

public abstract class Change implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 373331154233699735L;
	private transient Player receiver;
	private final transient UUID gameId;
	
	/**
	 * 
	 * @param receiver
	 */
	public  Change(UUID gameId ,Player receiver){
		
		this.gameId=gameId;
		this.receiver=receiver;
	}

	/**
	 * 
	 * @return the receiver
	 */
	public Player getReceiver() {
		return receiver;
	}
	public UUID getUUID(){
		return gameId;
	}

	/**
	 * the method sets the receiver
	 * @param receiver
	 * @throws NullPointerException if the receiver is null
	 */
	public void setReceiver(Player receiver) {
		
		if(receiver == null)
			throw new NullPointerException();
		
		this.receiver = receiver;
	}
}