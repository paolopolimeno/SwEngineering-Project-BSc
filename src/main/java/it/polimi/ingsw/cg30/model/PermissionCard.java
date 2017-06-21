package it.polimi.ingsw.cg30.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.bonus.Bonus;

@XmlRootElement(name = "permission_card")
@XmlAccessorType(XmlAccessType.FIELD)
public class PermissionCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8851312832613559168L;
	@XmlElementRef
	private List<Bonus> bonuses;
	private Region region;
	private List<String> letters;
	private boolean used;
	/**
	 * create a permission card
	 * with used false and void letters
	 */
	public PermissionCard(){
		used = false;
		letters = new ArrayList<>();
	}
	/**
	 * set the region of the card
	 * @param region 
	 * @throws NullPointerException if the region is null
	 */
	public void setRegion(Region region) {
		
		if(region == null)
			throw new NullPointerException();
		this.region = region;
	}
	/**
	 * 
	 * @return an arraylist with all the bonuses of the card
	 */
	public List<Bonus> getBonuses() {
		return bonuses;
	}

	/**
	 * gives to the player all the permissioncard's bonuses
	 * @param player
	 * @throws NullPointerException if the player is null
	 */
	public void useBonuses(Player player) {

		if(player == null)
			throw new NullPointerException();
		
		for (Bonus bonus : bonuses) {
			bonus.giveBonusToPlayer(player);	
		}
	}
	/**
	 * 
	 * @return the region of the card
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @return the used state of the card
	 */
	public Boolean getUsed() {
		return used;
	}

	/**
	 * @param used the state of used
	 * if the state used is false, used can be setted to true
	 * if the state used is already true, you can't set the state used to false
	 * 
	 */
	public void setUsed(boolean used) {
		
		if (!getUsed()) {
			this.used = used;
		}
	}

	/**
	 * @return the letters of the card
	 */
	public List<String> getLetters() {
		return letters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonuses == null) ? 0 : bonuses.hashCode());
		result = prime * result + ((letters == null) ? 0 : letters.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + (used ? 1231 : 1237);
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
		PermissionCard other = (PermissionCard) obj;
		if (bonuses == null) {
			if (other.bonuses != null)
				return false;
		} else if (!bonuses.equals(other.bonuses))
			return false;
		if (letters == null) {
			if (other.letters != null)
				return false;
		} else if (!letters.equals(other.letters))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!(region.getName()).equals(other.region.getName()))
			return false;
		if (used != other.used)
			return false;
		return true;
	}
}