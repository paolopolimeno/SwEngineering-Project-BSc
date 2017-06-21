package it.polimi.ingsw.cg30.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType (XmlAccessType.FIELD)
public class Slot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7795161073160280541L;
	@XmlElement (name="coordinate")
	private int coordinate;
	private List<IndicatorDisk> disks = new ArrayList<>();
	/**
	 * this constructor is used by JAXB library
	 * to load a slot directly from file
	 */
	public Slot() {
		//used by jax b
	}

	/**
	 * 
	 * @param coordinate of the slot
	 * @throws IllegalArgumentException if the coordinate is negative
	 */
	public Slot(int coordinate) {
		
		if(coordinate < 0)
			throw new IllegalArgumentException();
		
		this.coordinate = coordinate;
	}
	/**
	 * 
	 * @return the coordinate of the slot
	 */
	public int getCoordinate() {
		return coordinate;
	}
	/**
	 * 
	 * @return the disks on the slot
	 */
	public List<IndicatorDisk> getDisks() {
		return disks;
	}
}