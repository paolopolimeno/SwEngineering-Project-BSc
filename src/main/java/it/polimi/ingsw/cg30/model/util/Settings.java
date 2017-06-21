package it.polimi.ingsw.cg30.model.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import it.polimi.ingsw.cg30.model.City;

import it.polimi.ingsw.cg30.model.GameBoard;
import it.polimi.ingsw.cg30.model.NobilitySlot;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Region;
import it.polimi.ingsw.cg30.model.Slot;
import it.polimi.ingsw.cg30.model.bonus.AssistantBonus;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.CityTokenBonus;
import it.polimi.ingsw.cg30.model.bonus.FreePermissionCardBonus;
import it.polimi.ingsw.cg30.model.bonus.MainActionBonus;
import it.polimi.ingsw.cg30.model.bonus.NobilityBonus;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;
import it.polimi.ingsw.cg30.model.bonus.PoliticCardBonus;
import it.polimi.ingsw.cg30.model.bonus.ProsperityBonus;
import it.polimi.ingsw.cg30.model.bonus.RepeatPermissionCardBonus;
import it.polimi.ingsw.cg30.model.util.CardColor;
import it.polimi.ingsw.cg30.model.util.Road;
import it.polimi.ingsw.cg30.model.util.Token;

@XmlRootElement(name = "settings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Settings {
	@XmlElements ({ 
		@XmlElement (name = "city", type = City.class),
		@XmlElement (name = "gameboard", type = GameBoard.class),
		@XmlElement (name = "region", type = Region.class),
		@XmlElement (name = "bonus", type = Bonus.class),
		@XmlElement (name = "assistant_bonus", type = AssistantBonus.class),
		@XmlElement (name = "points_bonus", type = PointsBonus.class),
		@XmlElement (name = "prosperity_bonus", type = ProsperityBonus.class),
		@XmlElement (name = "main_action_bonus", type = MainActionBonus.class),
		@XmlElement (name = "politic_card_bonus", type = PoliticCardBonus.class),
		@XmlElement (name = "city_token_bonus", type = CityTokenBonus.class),
		@XmlElement (name = "free_permission_card_bonus", type = FreePermissionCardBonus.class),
		@XmlElement (name = "repeat_permission_card_bonus", type = RepeatPermissionCardBonus.class),
		@XmlElement (name = "nobility_slot", type = NobilitySlot.class),
		@XmlElement (name = "slot", type = Slot.class),
		@XmlElement (name = "nobility_bonus", type = NobilityBonus.class),	
})
	
	private GameBoard gameBoard;
	@XmlElementRef
	private ArrayList<Road> links;
	@XmlElementRef
	private ArrayList<Token> tokens;
	@XmlElementRef
	private ArrayList<CardColor> cardColors;
	@XmlElementRef
	private ArrayList<PermissionCard> permissionCards;
	@XmlElement (name ="city_with_king")
	private String cityWithKing;
	@XmlElement (name ="councilors_per_balcony")
	private int councilorsPerBalcony;
	@XmlElement (name ="prosperity_track_length")
	private int prosperityLength;
	@XmlElement (name ="points_track_length")
	private int pointsLength;
	@XmlElement (name ="iron_bonus_amount")
	private int ironBonusAmount;
	@XmlElement (name ="bronze_bonus_amount")
	private int bronzeBonusAmount;
	@XmlElement (name ="silver_bonus_amount")
	private int silverBonusAmount;
	@XmlElement (name ="gold_bonus_amount")
	private int goldBonusAmount;
	@XmlElement (name ="region_bonus_amount")
	private int regionBonusAmount;
	@XmlElement (name ="first_king_bonus_amount")
	private int firstKingBonusAmount;
	@XmlElement (name ="second_king_bonus_amount")
	private int secondKingBonusAmount;
	@XmlElement (name ="third_king_bonus_amount")
	private int thirdKingBonusAmount;
	@XmlElement (name ="fourth_king_bonus_amount")
	private int fourthKingBonusAmount;
	@XmlElement (name ="fifth_king_bonus_amount")
	private int fifthKingBonusAmount;
	/**
	 * @return the gameboard loaded
	 */
	public GameBoard getGameBoard() {
		return gameBoard;
	}
	/**
	 * @return the list of cardcolors loaded
	 */
	public List<CardColor> getCardColors() {
		return cardColors;
	}
	/**
	 * @return the tokens loaded
	 */
	public List<Token> getTokens() {
		return tokens;
	}
	/**
	 * @return all the permission cards loaded
	 */
	public List<PermissionCard> getPermissionCards() {
		return permissionCards;
	}
	/**
	 * @return all the links loaded
	 */
	public List<Road> getLinks() {
		return links;
	}
	/**
	 * @return the city that has to start with the king
	 */
	public String getCityWithKing() {
		return cityWithKing;
	}
	/**
	 * @return the number of councilors for each balcony
	 */
	public int getCouncilorsPerBalcony() {
		return councilorsPerBalcony;	
	}
	/**
	 * @return the length of the prosperity track as an int
	 */
	public int getProsperityTrackLength() {
		return prosperityLength;
	}
	/**
	 * @return the length of the points track as an int
	 */
	public int getPointsTrackLength() {
		return pointsLength;
	}
	/**
	 * @return the value of the iron bonus
	 */
	public int getIronBonusAmount() {
		return ironBonusAmount;
	}
	/**
	 * @param ironBonusAmount set the amount of the iron bonus
	 */
	public void setIronBonusAmount(int ironBonusAmount) {
		this.ironBonusAmount = ironBonusAmount;
	}
	/**
	 * @return the amount of bronze bonus
	 */
	public int getBronzeBonusAmount() {
		return bronzeBonusAmount;
	}
	/**
	 * set the amount of the bonus
	 * @param bronzeBonusAmount
	 */
	public void setBronzeBonusAmount(int bronzeBonusAmount) {
		this.bronzeBonusAmount = bronzeBonusAmount;
	}
	/**
	 * @return the amount of silver bonus
	 */
	public int getSilverBonusAmount() {
		return silverBonusAmount;
	}
	/**
	 * set the amount of the bonus
	 * @param silverBonusAmount
	 */
	public void setSilverBonusAmount(int silverBonusAmount) {
		this.silverBonusAmount = silverBonusAmount;
	}
	/**
	 * @return the amount of gold bonus
	 */
	public int getGoldBonusAmount() {
		return goldBonusAmount;
	}
	/**
	 * set the amount of the bonus
	 * @param goldBonusAmount
	 */
	public void setGoldBonusAmount(int goldBonusAmount) {
		this.goldBonusAmount = goldBonusAmount;
	}
	/**
	 * @return the amount of region bonus
	 */
	public int getRegionBonusAmount() {
		return regionBonusAmount;
	}
	/**
	 * set the amount of the bonus
	 * @param regionBonusAmount
	 */
	public void setRegionBonusAmount(int regionBonusAmount) {
		this.regionBonusAmount = regionBonusAmount;
	}
	/**
	 * @return the amount of first king bonus
	 */
	public int getFirstKingBonusAmount() {
		return firstKingBonusAmount;
	}
	/**
	 * set the amount of the bonus
	 * @param firstKingBonusAmount
	 */
	public void setFirstKingBonusAmount(int firstKingBonusAmount) {
		this.firstKingBonusAmount = firstKingBonusAmount;
	}
	/**
	 * @return the amount of second king bonus
	 */
	public int getSecondKingBonusAmount() {
		return secondKingBonusAmount;
	}
	/**
	 * set the amount of the bonus
	 * @param secondKingBonusAmount
	 */
	public void setSecondKingBonusAmount(int secondKingBonusAmount) {
		this.secondKingBonusAmount = secondKingBonusAmount;
	}
	/**
	 * @return the amount of third king bonus
	 */
	public int getThirdKingBonusAmount() {
		return thirdKingBonusAmount;
	}
	/**
	 * set the amount of the bonus
	 * @param thirdKingBonusAmount
	 */
	public void setThirdKingBonusAmount(int thirdKingBonusAmount) {
		this.thirdKingBonusAmount = thirdKingBonusAmount;
	}
	/**
	 * @return the amount of fourth king bonus
	 */
	public int getFourthKingBonusAmount() {
		return fourthKingBonusAmount;
	}
	/**
	 * set the amount of the bonus
	 * @param fourthKingBonusAmount
	 */
	public void setFourthKingBonusAmount(int fourthKingBonusAmount) {
		this.fourthKingBonusAmount = fourthKingBonusAmount;
	}
	/**
	 * @return the amount of fifth king bonus
	 */
	public int getFifthKingBonusAmount() {
		return fifthKingBonusAmount;
	}
	/**
	 * set the amount of the bonus
	 * @param fifthKingBonusAmount
	 */
	public void setFifthKingBonusAmount(int fifthKingBonusAmount) {
		this.fifthKingBonusAmount = fifthKingBonusAmount;
	}
}