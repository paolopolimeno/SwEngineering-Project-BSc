package it.polimi.ingsw.cg30.model.actions.market;

import java.io.Serializable;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.Player;

public class Thing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4099540118799228475L;
	
	private int price;
	private Object object;
	private int playerNumber;
	
	/**
	 * 
	 * @param price of the thing to sale
	 * @param thing assistant, permission card or politic card
	 * @param owner of the thing
	 * @throws NullPointerException if the owner or thing are null
	 */
	public Thing(int price, Object object, int playerNumber){
		
		if(object == null)
			throw new NullPointerException();
		
		this.price = price;
		this.object = object;
		this.playerNumber = playerNumber;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerNumber;
		result = prime * result + price;
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thing other = (Thing) obj;
		if (playerNumber != other.playerNumber)
			return false;
		if (price != other.price)
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		}else if (object.getClass()==Assistant.class){
			return true; 
		}else if (!object.equals(other.object)){
						return false;
		}
		return true;
	}
	/**
	 * 
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * set the price of the thing
	 * @param p
	 */
	public void setPrice(int p){
		this.price=p;
	}

	/**
	 * 
	 * @return the thing
	 */
	public Object getThing() {
		return object;
	}

	/**
	 * 
	 * @return the owner
	 */
	public int getOwnerNumber() {
		return playerNumber;
	}	
	/**
	 * set the player
	 * 
	 * @param player
	 * @throws NullPointerException if the player is null
	 */
	public void setPlayer(Player player) {
		
		if(player == null)
			throw new NullPointerException();
		
		this.playerNumber=player.getPlayerNumber();
	}
}