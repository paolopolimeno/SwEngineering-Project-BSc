package it.polimi.ingsw.cg30.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import it.polimi.ingsw.cg30.model.actions.market.Thing;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;
import it.polimi.ingsw.cg30.model.util.Edge;
import it.polimi.ingsw.cg30.model.util.GraphModel;

@XmlRootElement(name = "gameboard")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameBoard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8560108381355308476L; 
	
	private transient Game game;
	@XmlElementRef
	private List<Region> regions;
	private List<Slot> prosperityTrack = new ArrayList<>();
	private List<Slot> pointsTrack = new ArrayList<>();
	private List<Counselor> counselors = new ArrayList<>();
	private List<Balcony> balconies = new ArrayList<>();
	private Balcony kingBalcony;
	private transient List<PoliticCard> politicCards = new ArrayList<>();
	@XmlElementRef
	private List<City> cities;
	@XmlElementRef
	private List<NobilitySlot> nobilityTrack;
	private transient List<Edge> edges = new ArrayList<>();
	private transient GraphModel graph;
	private King king;
	private PointsBonus ironBonus;
	private PointsBonus bronzeBonus;
	private PointsBonus silverBonus;
	private PointsBonus goldBonus;
	private List<PointsBonus> kingBonusCards;
	private List<Thing> allThingsForSale = new ArrayList<>();
	private Player winner;
	private Map<Integer, String> colors = new HashMap<>();
	
	
	/**
	 * the constructor creates the color associated with the players and the bonus cards
	 */
	public GameBoard() {
		kingBonusCards = new ArrayList<>();
		colors.put(1, "RED");
		colors.put(2, "YELLOW");
		colors.put(3, "BLUE");
		colors.put(4, "GREEN");
	}
	/**
	 * @return the kingBalcony
	 */
	public Balcony getKingBalcony() {
		return kingBalcony;
	}

	/**
	 * @return pick a politicCard
	 */
	public PoliticCard pickPoliticCard() {

		PoliticCard politicCardSelected = politicCards.get(0);
		politicCards.remove(0);
		return politicCardSelected;
	}

	/**
	 * 
	 * @return prosperityTrack
	 */
	public List<Slot> getProsperityTrack() {
		return prosperityTrack;
	}
	
	/**
	 * 
	 * @return pointsTrack
	 */
	public List<Slot> getPointsTrack() {
		return pointsTrack;
	}
	/**
	 * 
	 * @return nobilityTrack
	 */
	public List<NobilitySlot> getNobilityTrack() {
		return nobilityTrack;
	}

	/**
	 * @return the counselors
	 */
	public List<Counselor> getCounselors() {
		return counselors;
	}
	/**
	 * set the king
	 * @param king
	 */
	public void setKing(King king) {
		this.king = king;
	}

	/**
	 * @return the balconies
	 */
	public List<Balcony> getBalconies() {
		return balconies;
	}

	/**
	 * @return the politicCards
	 */
	public List<PoliticCard> getPoliticCards() {
		return politicCards;
	}

	/**
	 * @return the sea
	 */
	public Region getSea() {
		return regions.get(0);
	}

	/**
	 * @return the hill
	 */
	public Region getHill() {
		return regions.get(1);
	}

	/**
	 * @return the mountain
	 */
	public Region getMountain() {
		return regions.get(2);
	}

	/**
	 * check if player has gained a special bonus
	 * If he has and the bonus is still present he gets it
	 * @param player
	 */
	public void citiesBuildBonus(Player player) {

		boolean iron = false; 
		boolean bronze = false; 
		boolean silver = false; 
		boolean	gold = false; 
		boolean sea = false;
		boolean hill = false; 
		boolean mountain = false;

		ArrayList<City> cityNotBuilded;
		cityNotBuilded = new ArrayList<>(cities);

		for (City city : cities) {
			for (Emporium emporium : city.getEmporiumsSpace()) {
				if (emporium.getColor().equals(player.getColor()))
					cityNotBuilded.remove(city);
			}
		}

		for (City city : cityNotBuilded) {
			if (("Iron").equals(city.getCityColor()))
				iron = true;
			if (("Bronze").equals(city.getCityColor()))
				bronze = true;
			if (("Silver").equals(city.getCityColor()))
				silver = true;
			if (("Gold").equals(city.getCityColor()))
				gold = true;
		}
		
		if (regions.get(0).checkRegionBonus(player)){
			sea = true;
		}
		if (regions.get(1).checkRegionBonus(player)){
			hill = true;
		}
		if (regions.get(2).checkRegionBonus(player)){
			mountain = true;
		}
		
		if (!iron && ironBonus != null) {
			player.getBonusCards().add(ironBonus);
			ironBonus = null;
			giveKingBonusCard(player);
		}

		if (!bronze && bronzeBonus!=null) {
			player.getBonusCards().add(bronzeBonus);
			bronzeBonus = null;
			giveKingBonusCard(player);
		}
		if (!silver && silverBonus!=null) {
			player.getBonusCards().add(silverBonus);
			silverBonus = null;
			giveKingBonusCard(player);
		}
		if (!gold && goldBonus!=null) {
			player.getBonusCards().add(goldBonus);
			goldBonus = null;
			giveKingBonusCard(player);
		}

		if (sea && getSea().getRegionBonus()!=null) {
			player.getBonusCards().add(getSea().getRegionBonus());
			getSea().removeRegionBonus();
			giveKingBonusCard(player);
		}
		if (hill && getHill().getRegionBonus()!=null) {
			player.getBonusCards().add(getHill().getRegionBonus());
			getHill().removeRegionBonus();
			giveKingBonusCard(player);
		}
		if (mountain && getMountain().getRegionBonus()!=null) {
			player.getBonusCards().add(getMountain().getRegionBonus());
			getMountain().removeRegionBonus();
			giveKingBonusCard(player);
		}
	}
	
	
	/**
	 * takes first kingbonuscard and gives it to the player
	 * If kingcards are finished it does nothing
	 * @param player
	 */
	private void giveKingBonusCard(Player player){
		if (!kingBonusCards.isEmpty()){
			player.getBonusCards().add(kingBonusCards.get(0));
			kingBonusCards.remove(0);
		}
	}

	/**
	 * @return the cities
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * @return the edges
	 */
	public List<Edge> getEdges() {
		return edges;
	}

	/**
	 * @return the graph
	 */
	public GraphModel getGraph() {
		return graph;
	}

	/**
	 * @return the king
	 */
	public King getKing() {
		return king;
	}

	/**
	 * @return the kingBonusCards
	 */
	public List<PointsBonus> getKingBonusCards() {
		return kingBonusCards;
	}
	/**
	 * @return the regions
	 */
	public List<Region> getRegions() {
		return regions;
	}
	/**
	 * set current game
	 * @param game
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the allThingsForSale
	 */
	public List<Thing> getAllThingsForSale() {
		return allThingsForSale;
	}
	
	/**
	 * add all Thing object of the passed ArrayList
	 * to allThingsForSale 
	 * @param things
	 */
	
	public void addThingsForSale(List<Thing> things){
		allThingsForSale.addAll(things);
	}

	/**
	 * set kingBalcony
	 * @param kingBalcony
	 */
	public void setKingBalcony(Balcony kingBalcony) {
		this.kingBalcony=kingBalcony;
	}

	/**
	 * set graphModel
	 * @param graphModel
	 */
	public void setGraphModel(GraphModel graphModel) {
		this.graph=graphModel;
	}
	/**
	 * set ironBonus
	 * @param ironBonus
	 */
	public void setIronBonus(PointsBonus ironBonus) {
		this.ironBonus = ironBonus;
	}
	/**
	 * set bronzeBonus
	 * @param bronzeBonus
	 */
	public void setBronzeBonus(PointsBonus bronzeBonus) {
		this.bronzeBonus = bronzeBonus;
	}
	/**
	 * set silverBonus
	 * @param silverBonus
	 */
	public void setSilverBonus(PointsBonus silverBonus) {
		this.silverBonus = silverBonus;
	}
	/**
	 * set goldBonus
	 * @param goldBonus
	 */
	
	public void setGoldBonus(PointsBonus goldBonus) {
		this.goldBonus = goldBonus;
	}
	
	/**
	 * set kingCardBonus
	 * @param kingBonusCards
	 */
	public void setKingBonusCards(List<PointsBonus> kingBonusCards) {
		this.kingBonusCards = kingBonusCards;
	}
	/**
	 * 
	 * @return ironBonus
	 */
	public PointsBonus getIronBonus() {
		return ironBonus;
	}
	/**
	 * 
	 * @return bronzeBonus
	 */
	public PointsBonus getBronzeBonus() {
		return bronzeBonus;
	}
	/**
	 * 
	 * @return silverBonus
	 */
	
	public PointsBonus getSilverBonus() {
		return silverBonus;
	}
	/**
	 * 
	 * @return goldBonus
	 */
	public PointsBonus getGoldBonus() {
		return goldBonus;
	}
	/**
	 * 
	 * @return winner
	 */
	public Player getWinner() {
		return winner;
	}
	/**
	 * set match winner
	 * @param winner
	 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	public Map<Integer, String> getColors() {
		return colors;
	}
}