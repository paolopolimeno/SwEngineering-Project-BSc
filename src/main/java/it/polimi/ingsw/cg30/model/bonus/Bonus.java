package it.polimi.ingsw.cg30.model.bonus;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.Player;


@XmlRootElement(name = "bonus")
@XmlAccessorType (XmlAccessType.FIELD)
public abstract class Bonus implements Serializable {

	/**UID
	 * 
	 */
	private static final long serialVersionUID = 2245768125278927416L;
	
	@XmlAttribute(name = "amount")
	private int amount;

	/**
	 * 
	 * @param numberOfBonus
	 * @throws IllegalArgumentException if the number is negative or 0
	 */
	public Bonus(int numberOfBonus) {
		
		if(numberOfBonus <= 0 )
			throw new IllegalArgumentException();
		this.amount = numberOfBonus;
	}
	/**
	 * void constructor used by JAXB
	 */
	public Bonus() {
	}
  
	/**
	 * 
	 * @param selectedPlayer
	 * @throws NullPointerException if the selectedPlayer is null
	 */
	public void giveBonusToPlayer(Player selectedPlayer) {
		
		if(selectedPlayer == null)
			throw new NullPointerException();
		
	}

	/**
	 * @return the numberOfBonus
	 */
	public int getNumberOfBonus() {
		return amount;
	}
/**
 * @return hashcode
 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		return result;
	}
	/**
	 * @return true if bonus equals parameter
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bonus other = (Bonus) obj;
		if (amount != other.amount)
			return false;
		return true;
	}
}