package it.polimi.ingsw.cg30.view.cli;

public class Link {
	/**
	 * if the link is true or false
	 */
	private boolean state;
	/**
	 * the symbol representing the link
	 */
	private String linkSymbol;
	/**
	 * this constructor create a link that is the string if the 
	 * state is true or is an empty space if the state is false
	 * @param state the state of the link (active or non active)
	 * @param link the symbol that represent the link
	 */
	public Link (boolean state, String link) {
		this.linkSymbol = link;
		this.state = state;
	}
	/**
	 *this method print the link according to the boolean state
	 * @return the string value of the link
	 */
	public String print() {
		if (!this.state) {
			return " ";
		}
		else return linkSymbol;
	}
}