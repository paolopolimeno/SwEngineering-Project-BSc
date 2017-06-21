package it.polimi.ingsw.cg30.model.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.bonus.Bonus;
@XmlRootElement(name = "token")
@XmlAccessorType(XmlAccessType.FIELD)
public class Token {
	
	@XmlElementRef
	ArrayList<Bonus> bonuses;
	/**
	 * @return all the bonuses contained in the token
	 */
	public List<Bonus> getBonuses() {
		return this.bonuses;
	}
}