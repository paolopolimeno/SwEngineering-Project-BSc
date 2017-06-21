package it.polimi.ingsw.cg30.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.bonus.*;

@XmlRootElement(name = "nobility_slot")
@XmlAccessorType (XmlAccessType.FIELD)
public class NobilitySlot extends Slot {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7008216507487933635L;
	@XmlElementRef
	private ArrayList<Bonus> bonuses;
	/**
	 * this constructor is used by JAXB library
	 * to load a nobility slot directly from file
	 */
	public NobilitySlot() {	
	}

	/**
	 * 
	 * @return an arraylist of all the bonuses of the slot
	 */
	public ArrayList<Bonus> getBonuses() {
		return this.bonuses;
	}
}