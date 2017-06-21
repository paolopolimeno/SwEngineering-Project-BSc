package it.polimi.ingsw.cg30.model.util;

import java.io.File;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.cg30.model.Balcony;
import it.polimi.ingsw.cg30.model.City;
import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.Game;
import it.polimi.ingsw.cg30.model.GameBoard;
import it.polimi.ingsw.cg30.model.King;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.Region;
import it.polimi.ingsw.cg30.model.Slot;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;

public class GameLoader {
	/**
	 * all the game settings
	 */
	private static Settings settings;
	/**
	 * the path of the xml settings file
	 */
	private static final String settingsPath = "src"+File.separator+"main"+File.separator+"resources"+File.separator+"settings.xml";
	/**
	 * the path of the maps folder
	 */
	public static final String mapsPath = "src"+File.separator+"main"+File.separator+"resources"+File.separator+"maps";
	/**
	 * this constructor receive a game to load and load
	 * into it all the settings retreived from the xml file.
	 * At the end return the game passed with all the
	 * settings loaded into it.
	 * @param trueGame
	 * @return the game fully loaded
	 */
	public static Game run(Game trueGame) {
		settings = (Settings) Unmarshaller.load(settingsPath);
		if (trueGame.getMapName() == null){
			initGameBoard(settings.getGameBoard(), Util.getFilesInPath(mapsPath).get(0));
		}
		else initGameBoard(settings.getGameBoard(), trueGame.getMapName());
		trueGame.setGameBoard(settings.getGameBoard());
		trueGame.getGameBoard().setGame(trueGame);
		return trueGame;
	}
	/**
	 * this method initialize the gameboard passed with the map corresponding
	 * to the mapname
	 * @param gameBoard
	 * @param mapName
	 */
	private static void initGameBoard(GameBoard gameBoard, String mapName) {

		linkCities(gameBoard, mapName);
		tokensOnCities(gameBoard);
		citiesIntoRegions(gameBoard);
		permissionCardsIntoRegions(gameBoard);
		politicCardAndCouncilorsCreator(gameBoard);
		pointsTrackCreator(gameBoard);
		prosperityTrackCreator(gameBoard);
		balconiesCreator(gameBoard);
		politicCardShuffler(gameBoard);
		bonusCardsCreator(gameBoard);
	}
	/**
	 * this method create all the bonus cards from the settings class
	 * loaded from xml file
	 * @param gameBoard
	 */
	private static void bonusCardsCreator(GameBoard gameBoard) {
		gameBoard.setIronBonus(new PointsBonus(settings.getIronBonusAmount()));
		gameBoard.setBronzeBonus(new PointsBonus(settings.getBronzeBonusAmount()));
		gameBoard.setSilverBonus(new PointsBonus(settings.getSilverBonusAmount()));
		gameBoard.setGoldBonus(new PointsBonus(settings.getGoldBonusAmount()));
		for (Region region : gameBoard.getRegions()){
			region.setRegionBonus(new PointsBonus(settings.getRegionBonusAmount()));
		}
		gameBoard.getKingBonusCards().add(new PointsBonus(settings.getFirstKingBonusAmount()));
		gameBoard.getKingBonusCards().add(new PointsBonus(settings.getSecondKingBonusAmount()));
		gameBoard.getKingBonusCards().add(new PointsBonus(settings.getThirdKingBonusAmount()));
		gameBoard.getKingBonusCards().add(new PointsBonus(settings.getFourthKingBonusAmount()));
		gameBoard.getKingBonusCards().add(new PointsBonus(settings.getFifthKingBonusAmount()));
	}
	/**
	 * this method create the four balconies of the game
	 * @param gameBoard
	 */
	private static void balconiesCreator(GameBoard gameBoard) {
		Collections.shuffle(gameBoard.getCounselors());
		
		for (Region region : gameBoard.getRegions()) {
			Balcony balcony = new Balcony(gameBoard, region);
			for (int i = 0; i < settings.getCouncilorsPerBalcony(); i++) {
				balcony.initFillBalcony(gameBoard.getCounselors().get(0));
				gameBoard.getCounselors().remove(0);
			}
			gameBoard.getBalconies().add(balcony);
		}
		Balcony kingBalcony = new Balcony(gameBoard);
		
		for (int i = 0; i < settings.getCouncilorsPerBalcony(); i++) {
			kingBalcony.initFillBalcony(gameBoard.getCounselors().get(0));
			gameBoard.getCounselors().remove(0);
		}
		gameBoard.setKingBalcony(kingBalcony);
		gameBoard.getBalconies().add(kingBalcony);
	}
	/**
	 * this method create the prosperity track into the gameboard
	 * @param gameBoard
	 */
	private static void prosperityTrackCreator(GameBoard gameBoard) {
		for (int i = 0; i < settings.getProsperityTrackLength(); i++) {
			gameBoard.getProsperityTrack().add(i, new Slot(i));
		}		
	}
	/**
	 * this methos create the points track into the gameboard
	 * @param gameBoard
	 */
	private static void pointsTrackCreator(GameBoard gameBoard) {
		for (int i = 0; i < settings.getPointsTrackLength(); i++) {
			gameBoard.getPointsTrack().add(i, new Slot(i));
		}
	}
	/**
	 * this method create all the councilors and the politic cards
	 * based on the number of councilors and politic cards per color
	 * loaded from the settings
	 * @param gameBoard the gameboard in which load councilors and cards
	 */
	private static void politicCardAndCouncilorsCreator(GameBoard gameBoard) {
		for (CardColor color : settings.getCardColors()) {
			for (int i = 0; i < color.getPoliticCardsNumber(); i++) {
				gameBoard.getPoliticCards().add(new PoliticCard(color.getColor()));
			}
			for (int i = 0; i < color.getCouncilorsNumber(); i++) {
				gameBoard.getCounselors().add(new Counselor(color.getColor()));
			}
		}
	}
	/**
	 * this method shuffle the politic cards of the gameboard
	 * @param gameBoard
	 */
	private static void politicCardShuffler(GameBoard gameBoard) {
		List<PoliticCard> pc=gameBoard.getPoliticCards();
		Collections.shuffle(pc);
		
	}
	/**
	 * this method send the permission cards into the regions
	 * @param gameBoard
	 */
	private static void permissionCardsIntoRegions(GameBoard gameBoard) {
		for (PermissionCard card : settings.getPermissionCards()) {
			for (Region region : gameBoard.getRegions()) {
				if (region.matchPermissionCard(card)) {
					card.setRegion(region);
					region.addPermissionCard(card);
					break;
				}
			}
		}
		
		for (Region region : gameBoard.getRegions()) {
			region.shufflePermissionCards();
			region.showPermissionCard();
		}
	}
	/**
	 * this method send the cities into the regions
	 * @param gameBoard
	 */
	private static void citiesIntoRegions(GameBoard gameBoard) {
		for (City city : gameBoard.getCities()) {
			for (Region region : gameBoard.getRegions()) {
				if (region.matchCity(city.getName())) {
					region.addCity(city);
					break;
				}
			}
		}
	}
	/**
	 * this method put all the tokens into the cities
	 * @param gameBoard
	 */
	private static void tokensOnCities(GameBoard gameBoard) {
		Collections.shuffle(settings.getTokens());

		for (City city : gameBoard.getCities()) {
			if (!city.getName().equals(settings.getCityWithKing())) {
				for (Bonus bonus : settings.getTokens().get(0).getBonuses()) {
					city.getCityBonuses().add(bonus);
				}
				settings.getTokens().remove(0);
			}
			else {
				King king = new King(city);
				gameBoard.setKing(king);
			}
		}
	}
	/**
	 * this method link the cities from the map
	 * @param gameBoard
	 * @param mapName
	 */
	private static void linkCities(GameBoard gameBoard, String mapName) {
		
		List<Road> roads = mapLoader(mapName);
		
		for (City city1 : gameBoard.getCities()) {
			for (City city2 : gameBoard.getCities()) {
				for (Road link : roads) {
					if (city1.getName().equals(link.getCity1()) && 
						city2.getName().equals(link.getCity2())) {

						city1.addLinkedCity(city2);
						city2.addLinkedCity(city1);
						gameBoard.getEdges().add(new Edge(city1, city2));
					}
				}
			}
		}
		gameBoard.setGraphModel(new GraphModel(gameBoard.getCities(), gameBoard.getEdges()));
	}
	/**
	 * this method load the map corrisponding to the number passed in.
	 * @param mapName the name of the map that you want load.
	 * @return  an arraylist of Road loaded from the selected map
	 */
	private static List<Road> mapLoader(String mapName){

		Settings mapSettings = (Settings) Unmarshaller.load(mapsPath+File.separator+mapName);
		return mapSettings.getLinks();
	}

}