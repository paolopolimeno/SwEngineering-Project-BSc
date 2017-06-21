package it.polimi.ingsw.cg30.model.change;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.Emporium;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;

public class PlayerChange extends Change implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3063749666958132697L;
	
	private final int playerNumber;
	private final String color;
	private final List<PermissionCard> permissionCards;
	private final List<Assistant> assistants;
	private final List<Action> possibleActions;
	private final List<PointsBonus> bonusCards;
	private final List<Emporium> emporiums;
	private final List<PoliticCard> politicCards;
	private final UUID playerId;
	/**
	 * see {@link Change}
	 * @param player
	 */
	public PlayerChange(Player player, UUID gameId) {
		
		super(gameId, player);
		this.playerNumber=player.getPlayerNumber();
		this.color=player.getColor();
		this.permissionCards=player.getPermissionCards();
		this.assistants=player.getAssistants();
		this.possibleActions=player.getPossibleActions();
		this.emporiums=player.getEmporiums();
		this.politicCards=player.getPoliticCards();
		this.bonusCards=player.getBonusCards();
		this.playerId=player.getUUID();
		
	}
	
	/**
	 *  
	 * @return the player number
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}
	/**
	 * 
	 * @return the player color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 
	 * @return the player permission cards
	 */
	public List<PermissionCard> getPermissionCards() {
		return permissionCards;
	}
	/**
	 * 
	 * @return the player assistants
	 */
	public List<Assistant> getAssistants() {
		return assistants;
	}
	/**
	 * 
	 * @return the player possible actions
	 */
	public List<Action> getPossibleActions() {
		return possibleActions;
	}
	/**
	 * 
	 * @return the player bonus cards
	 */
	public List<PointsBonus> getBonusCards() {
		return bonusCards;
	}
	/**
	 * 
	 * @return the player emporiums
	 */
	public List<Emporium> getEmporiums() {
		return emporiums;
	}
	/**
	 * 
	 * @return the player politic cards
	 */
	public List<PoliticCard> getPoliticCards() {
		return politicCards;
	}
	/**
	 * 
	 * @return the player id
	 */
	public UUID getPlayerId() {
		return playerId;
	}
}