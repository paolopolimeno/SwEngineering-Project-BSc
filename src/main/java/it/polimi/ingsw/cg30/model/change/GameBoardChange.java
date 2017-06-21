package it.polimi.ingsw.cg30.model.change;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.City;
import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.GameBoard;
import it.polimi.ingsw.cg30.model.King;
import it.polimi.ingsw.cg30.model.NobilitySlot;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.Player;
import it.polimi.ingsw.cg30.model.Region;
import it.polimi.ingsw.cg30.model.Slot;
import it.polimi.ingsw.cg30.model.actions.market.Thing;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;

public class GameBoardChange extends Change implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7427640094881356721L;

	private final List<Region> regions;
	private final List<Slot> prosperityTrack;
	private final List<Slot> pointsTrack;
	private final List<Counselor> counselors;
	private final List<Balcony> balconies;
	private final Balcony kingBalcony;
	private final List<City> cities;
	private final List<NobilitySlot> nobilityTrack;
	private final King king;
	private final PointsBonus ironBonus;
	private final PointsBonus bronzeBonus;
	private final PointsBonus silverBonus;
	private final PointsBonus goldBonus;
	private final List<PointsBonus> kingBonusCards;
	private final List<Thing> allThingsForSale;
	private int winnerPlayerNumber;

	/**
	 * this map contains in the first integer the number of the players, in the
	 * second the number of his assistants
	 */
	private Map<Integer, Integer> assistantsNumber = new HashMap<>();
	private Map<Integer, List<PermissionCard>> permissionCards = new HashMap<>();
	private Map<Integer, Integer> emporiumsNumber = new HashMap<>();
	private Map<Integer, Integer> prosperityTrackCoordinate = new HashMap<>();
	private Map<Integer, Integer> nobilityTrackCoordinate = new HashMap<>();
	private Map<Integer, Integer> pointsTrackCoordinate = new HashMap<>();
	private Map<Integer, String> playersColor = new HashMap<>();
	private Map<Integer, List<PointsBonus>> playersBonusCards = new HashMap<>();

	/**
	 * see {@link Change}
	 * 
	 * @param gameBoard
	 * @param receiver
	 */
	public GameBoardChange(GameBoard gameBoard, List<Player> players, UUID gameId, Player receiver) {

		super(gameId, receiver);

		this.regions = gameBoard.getRegions();
		this.prosperityTrack = gameBoard.getProsperityTrack();
		this.pointsTrack = gameBoard.getPointsTrack();
		this.counselors = gameBoard.getCounselors();
		this.balconies = gameBoard.getBalconies();
		this.kingBalcony = gameBoard.getKingBalcony();
		this.cities = gameBoard.getCities();
		this.nobilityTrack = gameBoard.getNobilityTrack();
		this.king = gameBoard.getKing();
		this.ironBonus = gameBoard.getIronBonus();
		this.bronzeBonus = gameBoard.getBronzeBonus();
		this.silverBonus = gameBoard.getSilverBonus();
		this.goldBonus = gameBoard.getGoldBonus();
		this.kingBonusCards = gameBoard.getKingBonusCards();
		this.allThingsForSale = gameBoard.getAllThingsForSale();
		if (gameBoard.getWinner() != null)
			this.winnerPlayerNumber = gameBoard.getWinner().getPlayerNumber();

		for (Player player : players) {
			assistantsNumber.put(Integer.valueOf(player.getPlayerNumber()),
					Integer.valueOf(player.getAssistants().size()));

			permissionCards.put(Integer.valueOf(player.getPlayerNumber()), player.getPermissionCards());

			emporiumsNumber.put(Integer.valueOf(player.getPlayerNumber()),
					Integer.valueOf(player.getEmporiums().size()));
			prosperityTrackCoordinate.put(Integer.valueOf(player.getPlayerNumber()),
					Integer.valueOf(player.getProsperityDisk().getSlot().getCoordinate()));
			nobilityTrackCoordinate.put(Integer.valueOf(player.getPlayerNumber()),
					Integer.valueOf(player.getNobilityDisk().getSlot().getCoordinate()));
			pointsTrackCoordinate.put(Integer.valueOf(player.getPlayerNumber()),
					Integer.valueOf(player.getPointsDisk().getSlot().getCoordinate()));
			playersColor.put(Integer.valueOf(player.getPlayerNumber()), player.getColor());
			playersBonusCards.put(Integer.valueOf(player.getPlayerNumber()), player.getBonusCards());
		}
	}

	/**
	 * 
	 * @return assistantsNumber
	 */
	public Map<Integer, Integer> getAssistantsNumber() {
		return assistantsNumber;
	}

	/**
	 * set assistantsNumber
	 * 
	 * @param assistantsNumber
	 */

	public void setAssistantsNumber(Map<Integer, Integer> assistantsNumber) {
		this.assistantsNumber = assistantsNumber;
	}

	/**
	 * 
	 * @return permissionCards
	 */
	public Map<Integer, List<PermissionCard>> getPermissionCards() {
		return permissionCards;
	}

	/**
	 * 
	 * @param permissionCards
	 */
	public void setPermissionCards(Map<Integer, List<PermissionCard>> permissionCards) {
		this.permissionCards = permissionCards;
	}

	/**
	 * 
	 * @return the regions
	 */
	public List<Region> getRegions() {
		return regions;
	}

	/**
	 * 
	 * @return the prosperity track
	 */
	public List<Slot> getProsperityTrack() {
		return prosperityTrack;
	}

	/**
	 * 
	 * @return the points track
	 */
	public List<Slot> getPointsTrack() {
		return pointsTrack;
	}

	/**
	 * 
	 * @return the counselors
	 */
	public List<Counselor> getCounselors() {
		return counselors;
	}

	/**
	 * 
	 * @return the balconies
	 */
	public List<Balcony> getBalconies() {
		return balconies;
	}

	/**
	 * 
	 * @return teh king balcony
	 */
	public Balcony getKingBalcony() {
		return kingBalcony;
	}

	/**
	 * 
	 * @return the cities
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * 
	 * @return the nobility track
	 */
	public List<NobilitySlot> getNobilityTrack() {
		return nobilityTrack;
	}

	/**
	 * 
	 * @return the king
	 */
	public King getKing() {
		return king;
	}

	/**
	 * 
	 * @return the iron bonus
	 */
	public PointsBonus getIronBonus() {
		return ironBonus;
	}

	/**
	 * 
	 * @return the bronze bonus
	 */
	public PointsBonus getBronzeBonus() {
		return bronzeBonus;
	}

	/**
	 * 
	 * @return the silver bonus
	 */
	public PointsBonus getSilverBonus() {
		return silverBonus;
	}

	/**
	 * 
	 * @return the gold bonus
	 */
	public PointsBonus getGoldBonus() {
		return goldBonus;
	}

	/**
	 * 
	 * @return the king bonus
	 */
	public List<PointsBonus> getKingBonusCards() {
		return kingBonusCards;
	}

	/**
	 * 
	 * @return all things to sale
	 */
	public List<Thing> getAllThingsForSale() {
		return allThingsForSale;
	}

	/**
	 * 
	 * @return the number of emporiums
	 */
	public Map<Integer, Integer> getEmporiumsNumber() {
		return emporiumsNumber;
	}

	/**
	 * 
	 * @return the coordinates of players on the prosperity track
	 */
	public Map<Integer, Integer> getProsperityTrackCoordinate() {
		return prosperityTrackCoordinate;
	}

	/**
	 * 
	 * @return the coordinates of players on the nobility track
	 */
	public Map<Integer, Integer> getNobilityTrackCoordinate() {
		return nobilityTrackCoordinate;
	}

	/**
	 * 
	 * @return the coordinates of players on the points track
	 */
	public Map<Integer, Integer> getPointsTrackCoordinate() {
		return pointsTrackCoordinate;
	}

	/**
	 * 
	 * @return the winner
	 */
	public int getWinnerPlayerNumber() {
		return winnerPlayerNumber;
	}

	/**
	 * 
	 * @param winnerPlayerNumber
	 */
	public void setWinnerPlayerNumber(int winnerPlayerNumber) {
		this.winnerPlayerNumber = winnerPlayerNumber;
	}

	/**
	 * 
	 * @return the colors of the players
	 */
	public Map<Integer, String> getPlayersColor() {
		return playersColor;
	}

	/**
	 * 
	 * @return the bonus cards owned by the players
	 */
	public Map<Integer, List<PointsBonus>> getPlayersBonusCards() {
		return playersBonusCards;
	}

}