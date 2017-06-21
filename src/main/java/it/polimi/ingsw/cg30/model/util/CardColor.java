package it.polimi.ingsw.cg30.model.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "card_color")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardColor {
	@XmlAttribute(name = "color")
	private String color;
	@XmlAttribute(name = "councilorsNumber")
	private int councilorsNumber;
	@XmlAttribute(name = "politicCardsNumber")
	private int politicCardsNumber;
	/**
	 * @return the color of the card color object
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @return the councilors number corresponding
	 * to the color
	 */
	public int getCouncilorsNumber() {
		return councilorsNumber;
	}
	/**
	 * @return the politic cards number corresponding
	 * to the color
	 */
	public int getPoliticCardsNumber() {
		return politicCardsNumber;
	}
}