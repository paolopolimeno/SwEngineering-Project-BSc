package it.polimi.ingsw.cg30.model.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "road")
@XmlAccessorType(XmlAccessType.FIELD)
public class Road {
	@XmlAttribute(name = "city1")
	private String city1;
	@XmlAttribute(name = "city2")
	private String city2;
	/**
	 * return the first city of the road
	 * @return
	 */
	public String getCity1() {
		return city1;
	}
	/**
	 * return the second city of the road
	 * @return
	 */
	public String getCity2() {
		return city2;
	}	
}