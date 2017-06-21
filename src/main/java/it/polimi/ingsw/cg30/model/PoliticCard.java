package it.polimi.ingsw.cg30.model;

import java.io.Serializable;

public class PoliticCard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -292028317368344285L;
	private String color;
	
	/**
	 * @param color
	 * @throws NullPointerException if the color is null
	 */
	public PoliticCard(String color) {
		
		if(color == null)
			throw new NullPointerException();
		
		this.color = color;
	}
	
	/**
	 * @return the color of the card as string
	 */
	public String getColor() {
		return color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		PoliticCard other = (PoliticCard) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}
}