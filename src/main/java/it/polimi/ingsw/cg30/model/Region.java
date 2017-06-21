package it.polimi.ingsw.cg30.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.common.base.CharMatcher;

import it.polimi.ingsw.cg30.model.bonus.PointsBonus;

@XmlRootElement(name = "region")
@XmlAccessorType(XmlAccessType.FIELD)
public class Region implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4168676138314438866L;
	@XmlAttribute(name = "name")
	private String name;
	private List<City> cityList = new ArrayList<>();
	private List<PermissionCard> deckPermissionCards = new ArrayList<>();
	private List<PermissionCard> showedPermissionCards = new ArrayList<>();
	@XmlAttribute(name = "first_letter")
	private String firstLetter;
	@XmlAttribute(name = "last_letter")
	private String lastLetter;
	private PointsBonus regionBonus;


	/**
	 * method used in initialization phase in order to match cities with region,
	 * according to the name of the city
	 * 
	 * @param string
	 *            represents the name of a city
	 * @return
	 */
	public boolean matchCity(String string) {
		return CharMatcher.inRange(firstLetter.charAt(0), lastLetter.charAt(0)).matches(string.toLowerCase().charAt(0));
	}
	/**
	 * method used in initialization phase in order to match permission cards
	 * with the regions according to the letters of the cards
	 * All the letters of a given card have to match the letters of the region
	 * @param card to match
	 * @return true if all the letters of the card match the region
	 */
	public boolean matchPermissionCard(PermissionCard card) {
		int numberOfLetters = card.getLetters().size();
		int count = 0;
		
		for (int i = 0; i<numberOfLetters; i++) {
			if (CharMatcher.inRange(firstLetter.charAt(0), lastLetter.charAt(0)).matches(card.getLetters().get(i).toLowerCase().charAt(0))) {
				count++;
			}
		}
		return count == numberOfLetters;
	}

	/**
	 * add a city into the region
	 * @param city
	 */
	public void addCity(City city) {
		cityList.add(city);
	}
	/**
	 * add a permission card in the region permission cards deck
	 * @param card
	 */
	public void addPermissionCard(PermissionCard card) {
		deckPermissionCards.add(card);
	}
	/**
	 * return the city in the region
	 * @return
	 */
	public List<City> getCityList() {
		return this.cityList;
	}
	/**
	 * delete the permission card from the showed permission cards and return the card
	 * @param selectedPermissionCard the card to pick and to remove from the showed permission cards
	 * @return
	 */
	public PermissionCard pickPermissionCard(PermissionCard selectedPermissionCard) {
		
		showedPermissionCards.remove(selectedPermissionCard);
		showPermissionCard();
		return selectedPermissionCard;
	}
	/**
	 * replace the showed permission cards with fresh cards and put the oldest under the deck
	 */
	public void changePermissionCards() {

			deckPermissionCards.addAll(showedPermissionCards);
			showedPermissionCards.clear();
			showPermissionCard();
	}
	/**
	 * shuffle the permission deck
	 */
	public void shufflePermissionCards() {
		Collections.shuffle(deckPermissionCards);
	}
	/**
	 * fill the show permission cards space
	 */
	public void showPermissionCard() {

		for (int i = 0; i < 2; i++) {

			if (this.showedPermissionCards.size() <2) {
				showedPermissionCards.add(deckPermissionCards.get(0));
				deckPermissionCards.remove(0);
			}
		}
	}

	/**
	 * @return the deckPermissionCards
	 */
	public List<PermissionCard> getDeckPermissionCards() {
		return deckPermissionCards;
	}

	
	/**
	 * @return the name of the region
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @return showedPermissionCards, 
	 * which are the ones a player can pick
	 */
	public List<PermissionCard> getShowedPermissionCards() {
		return showedPermissionCards;
	}
	
	/**
	 * set regionBonus
	 * @param bonus
	 */
	public void setRegionBonus(PointsBonus bonus){
		this.regionBonus=bonus;
	}
	
	
	/**
	 * 
	 * @return regionBonus
	 */
	public PointsBonus getRegionBonus(){
		return regionBonus;
	}
	
	/**
	 * set regionBonus to null
	 */
	public void removeRegionBonus(){
		this.regionBonus=null;
	}
	/**
	 * check if passed player has right to 
	 * the region bonus
	 * @param player
	 * @return true if he has the right
	 */
	public boolean checkRegionBonus(Player player) {
		
		int count = 0;
		
		for (City city : cityList) {
			if (city.getEmporiumsSpace().isEmpty())
				return false;
			for (Emporium emporium : city.getEmporiumsSpace()) {
				if (emporium.getColor().equals(player.getColor()))
					count++;
			}
		}
		return count==cityList.size();
	}
}