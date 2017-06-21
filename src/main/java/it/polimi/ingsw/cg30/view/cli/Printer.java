package it.polimi.ingsw.cg30.view.cli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import it.polimi.ingsw.cg30.connection.client.Client;
import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.City;
import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.Region;
import it.polimi.ingsw.cg30.model.actions.market.Thing;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;
import it.polimi.ingsw.cg30.model.util.Util;

public class Printer {
	private ArrayList<ArrayList<CityModel>> citiesToPrint = new ArrayList<>();
	private List<PrintableModel> permissionCardsToPrint = new ArrayList<>();
	private List<PrintableModel> playerPermissionCards = new ArrayList<>();
	private List<NobilitySlotModel> nobilityTrackToPrint = new ArrayList<>();
	private List<ArrayList<String>> nobilityTrackIcon = new ArrayList<>();
	private List<BonusCardModel> bonusCardsToPrint = new ArrayList<>();
	private List<String> regionsToPrint = new ArrayList<>();
	private List<String> balconyToPrint = new ArrayList<>();
	private List<String> availableCounselorsToPrint = new ArrayList<>();
	private List<String> prosperitySquare = new ArrayList<>();
	private List<String> nobilitySquare = new ArrayList<>();
	private List<String> pointsSquare = new ArrayList<>();
	private List<String> bonusCardTitle = new ArrayList<>();
	private List<ArrayList<String>> prosperityTrackToPrint = new ArrayList<>();
	private List<ArrayList<String>> pointsTrackToPrint = new ArrayList<>();
	private List<CityModel> cityModels = new ArrayList<>();
	private List<Integer> offsets = new ArrayList<>();
	private List<String> playerView = new ArrayList<>();
	private List<PoliticCardModel> playerPoliticCards = new ArrayList<>();
	private List<PrintableModel> sellableThings = new ArrayList<>();
	private boolean thisNumberIsCity;
	private int i;
	private City prev;
	private City next;
	private String seaBonus = "";
	private String hillBonus = "";
	private String mountainBonus = "";
	boolean kingHere;
	/**
	 * this method print the gameboard 
	 * @param gameBoard the gameboard to print
	 */
	public void printGame(GameBoardChange gameBoard) {
		if (gameBoard.getWinnerPlayerNumber()!=0){
			if (gameBoard.getWinnerPlayerNumber()==Client.getInstance().getPlayer().getPlayerNumber())
				Util.println("Hai vinto");
			else Util.println("Hai perso, ha vinto il giocatore "+gameBoard.getWinnerPlayerNumber());
		}
		else {
			printRegions(gameBoard);
			printMap(gameBoard);
			printPermissionCards(gameBoard);
			printBalconies(gameBoard);
			printProsperityTrack(gameBoard);
			printNobilityTrack(gameBoard);
			printPointsTrack(gameBoard);
			printThingsForSale(gameBoard);
		}
	}	
	/**
	 * this method print the things that are in sale in the market
	 * @param gameBoard the gameboard from which keep the things in sale
	 */
	private void printThingsForSale(GameBoardChange gameBoard) {
		if (!gameBoard.getAllThingsForSale().isEmpty()){
			
			Util.println(" ___________________ ");
			Util.println("|       MARKET      |");
			Util.println("|___________________|");
			
			for (Thing thing : gameBoard.getAllThingsForSale()){
				if (thing.getThing() instanceof PermissionCard){
					PrintableModel model = new PermissionCardModel((PermissionCard) thing.getThing());
					model.addRow("  PRICE:  "+thing.getPrice());
					model.addRow("  OWNER:  "+thing.getOwnerNumber());
					sellableThings.add(model); 
				}
				else if (thing.getThing() instanceof PoliticCard){
					PrintableModel model = new PoliticCardModel((PoliticCard) thing.getThing());
					model.addRow("  PRICE:  "+thing.getPrice());
					model.addRow("  OWNER:  "+thing.getOwnerNumber());
					sellableThings.add(model); 
				}
				else if (thing.getThing() instanceof Assistant){
					PrintableModel asssistantModel = new PrintableModel();
					
					asssistantModel.dataToModelize.add("Assistant");
					asssistantModel.dataToModelize.add("PRICE: "+thing.getPrice());
					asssistantModel.dataToModelize.add("OWNER: "+thing.getOwnerNumber());

					asssistantModel.model = new Model(asssistantModel.dataToModelize, 0, 9, false);
					
					sellableThings.add(asssistantModel);
				}
			}
			// retrieving the max height of the objects in the sellablething list
			int maxHeight = sellableThings.get(0).getHeight();
			for (PrintableModel printable : sellableThings){
				if (printable.getHeight() > maxHeight){
					maxHeight = printable.getHeight();
				}
			}
			//printing
			for (i=0; i<maxHeight; i++) {
				for (PrintableModel thing : sellableThings) {
					if (thing.getHeight() < i+1){
						StringBuilder spaceRow = new StringBuilder();
						for (int z = 0; z < thing.getWidth(); z++){
							spaceRow.append(" ");
						}
						thing.addRow(spaceRow.toString());
					}
					Util.print(thing.print(i));
				}
				Util.println("");
			}
			sellableThings.clear();
		}
	}
	/**
	 * this method print all the information about the player
	 * @param player the player to print
	 */
	public void printPlayer(PlayerChange player) {
		
		printPlayerInfo(player);
		printPlayerPoliticCards(player);
		printPlayerPermissionCards(player);
	}
	/**
	 * this method print all the map. All the cities here are
	 * printed with streets and bonus in the right eay
	 * @param gameBoard
	 */
	private void printMap(GameBoardChange gameBoard) {
		
		City city = new City();
		
			for (i = 0; i<17; i++) {
				thisNumberIsCity = false;
			
				for (City citytemp : gameBoard.getCities()) {
					if (i+1 == citytemp.getNumber()) {
						city=citytemp;
						thisNumberIsCity = true;
						break;
					}
				}
			
			if (thisNumberIsCity) {
				//calculate offsets and initialize a new citymodel
				for (City nearcity : city.getLinkedCities()) {
					offsets.add(nearcity.getNumber() - city.getNumber());
				}
				if (gameBoard.getKing().getLocationCity().equals(city)) {
					kingHere = true;
				}
				else kingHere = false;
				
				if (city.getCityBonuses().isEmpty()) {
					cityModels.add(new CityModel(city.getName(), city.getCityColor(), i+1, null, city.getEmporiumsSpace(), offsets, kingHere));
				}
				
				else cityModels.add(new CityModel(city.getName(), city.getCityColor(), i+1, city.getCityBonuses(), city.getEmporiumsSpace(), offsets, kingHere));
				
				offsets.clear();
			}
			
			else {
				//initialize a new blank citymodel
				boolean previousAndThisLinked = false;
				boolean thisAndNextLinked = false;
				
				prev = new City();
				next = new City();
				
				// previous city
				for (City previousCity : gameBoard.getCities()) {
					if (previousCity.getNumber() == i) {
						prev=previousCity;
					}
				}
				//next city
				for (City nextCity : gameBoard.getCities()) {
					if (nextCity.getNumber() == i+2) {
						next=nextCity;
					}
				}
				
				for (City linked : prev.getLinkedCities()) {
				
						if (linked.getName().equals(next.getName())) {
							previousAndThisLinked = true;
							thisAndNextLinked = true;
						}
				}
				
				if (previousAndThisLinked && thisAndNextLinked) {
					cityModels.add(new CityModel(i+1, previousAndThisLinked, thisAndNextLinked));
				}
				else cityModels.add(new CityModel(i+1));
			}
		}
		
		//sort citymodel by number
		Collections.sort(cityModels, new Comparator<CityModel>() {
			@Override
			public int compare(CityModel o1, CityModel o2) {
				return o1.getNumber() - o2.getNumber();
			}
		});
		
		cityModels.sort((o1, o2)->o1.getNumber()-o2.getNumber());
		
		
		//splitting in lines
		ArrayList<CityModel> line = new ArrayList<>();
		
		for (CityModel citymodel : cityModels) {
			
			line.add(citymodel);
			
			if (line.size() == 6) {
				citiesToPrint.add(new ArrayList<CityModel>(line));
				line.clear();
			}
		}
		citiesToPrint.add(line);
		//fine roba per le citta
		
		//stampa mappa
		for (ArrayList<CityModel> lineToPrint : citiesToPrint) {
			for (i=0; i<lineToPrint.get(0).getDraw().size(); i++) {
				for (CityModel cityModelToPrint : lineToPrint) {
					Util.print(cityModelToPrint.print(i));
				}
				Util.println("");
			}
		}
		citiesToPrint.clear();
		cityModels.clear();
	}
	/**
	 * this method print all the regions bonuses in the regions
	 * @param gameBoard
	 */
	private void printRegions(GameBoardChange gameBoard) {

			if (gameBoard.getRegions().get(0).getRegionBonus()!=null){
				seaBonus = gameBoard.getRegions().get(0).getRegionBonus().getClass().getSimpleName() + gameBoard.getRegions().get(0).getRegionBonus().getNumberOfBonus();
			}
			if (gameBoard.getRegions().get(1).getRegionBonus()!=null){
				hillBonus = gameBoard.getRegions().get(1).getRegionBonus().getClass().getSimpleName()+ gameBoard.getRegions().get(1).getRegionBonus().getNumberOfBonus();
			}
			if (gameBoard.getRegions().get(2).getRegionBonus()!=null){
				mountainBonus = gameBoard.getRegions().get(2).getRegionBonus().getClass().getSimpleName()+ gameBoard.getRegions().get(2).getRegionBonus().getNumberOfBonus();
			}
			regionsToPrint.add("    ____________________________________________________________________________________________________________________________");
			regionsToPrint.add("   |               Region: Sea              |               Region: Hill               |               Region: Mountain         |");
			regionsToPrint.add("   |           Bonus: "+seaBonus+"          |           Bonus: "+hillBonus+"            |           Bonus: "+mountainBonus+"          |");
			regionsToPrint.add("   |____________________________________________________________________________________________________________________________|");

			for (String string : regionsToPrint) {
				Util.println(string);
			}
			regionsToPrint.clear();
	}
	/**
	 * this method print the permission cards showed in the regions
	 * @param gameBoard
	 */
	private void printPermissionCards(GameBoardChange gameBoard) {
		
		for (Region region : gameBoard.getRegions()){
			permissionCardsToPrint.add(new DeckModel(region.getDeckPermissionCards().size(), "DECK"));
			for (PermissionCard card : region.getShowedPermissionCards()){
				permissionCardsToPrint.add(new PermissionCardModel(card));
			}
			permissionCardsToPrint.add(new PermissionCardModel(null));
		}

		for (i=0; i<permissionCardsToPrint.get(0).getHeight(); i++) {
			for (PrintableModel permissionmodel : permissionCardsToPrint) {
				Util.print(permissionmodel.print(i));
			}
			Util.println("");
		}		
		permissionCardsToPrint.clear();
	}
	/**
	 * this method print all the balconies with all the counselors inside
	 * @param gameBoard
	 */
	private void printBalconies(GameBoardChange gameBoard) {
	
		StringBuilder seaCounselors = new StringBuilder();
		Iterator<Counselor> seait = gameBoard.getBalconies().get(0).getCounselors().iterator();
	
		ArrayList<String> councilors = new ArrayList<>();
		
		while(seait.hasNext()){
			councilors.add(seait.next().getColor());
		}
		Collections.reverse(councilors);
		councilors.forEach(s -> seaCounselors.append(s+" "));
		councilors.clear();
			      
		StringBuilder hillCounselors = new StringBuilder();
		Iterator<Counselor> hillit = gameBoard.getBalconies().get(1).getCounselors().iterator();
		while(hillit.hasNext()){
			councilors.add(hillit.next().getColor());
		}
		Collections.reverse(councilors);
		councilors.forEach(s -> hillCounselors.append(s+" "));
		councilors.clear();
				  
		StringBuilder kingCounselors = new StringBuilder();
		Iterator<Counselor> kingit = gameBoard.getKingBalcony().getCounselors().iterator();
		while(kingit.hasNext()){
			councilors.add(kingit.next().getColor());
		}
		Collections.reverse(councilors);
		councilors.forEach(s -> kingCounselors.append(s+" "));
		councilors.clear();
				  
		StringBuilder mountainCounselors = new StringBuilder();
		Iterator<Counselor> mountainit = gameBoard.getBalconies().get(2).getCounselors().iterator();
		while(mountainit.hasNext()){
			councilors.add(mountainit.next().getColor());
		}
		Collections.reverse(councilors);
		councilors.forEach(s -> mountainCounselors.append(s+" "));
		councilors.clear();
			
		balconyToPrint.add(" __________________________________________________________________________________________________________________________________");
		balconyToPrint.add("|                Sea                            Hill                                King                          Mountain         |");
		balconyToPrint.add("| -> "+seaCounselors+"    |     "+hillCounselors+ "   |    "+kingCounselors+"    |     "+mountainCounselors);
		balconyToPrint.add("|__________________________________________________________________________________________________________________________________|");
		
		StringBuilder availableCounselors = new StringBuilder();
				
		Iterator<Counselor> availableit = gameBoard.getCounselors().iterator();
			while(availableit.hasNext()){
				availableCounselors.append(availableit.next().getColor()+" ");
			}
				
		availableCounselorsToPrint.add("  ____________________________________________________ ");
		availableCounselorsToPrint.add("|                Available Counselors                |");
		availableCounselorsToPrint.add("        "+availableCounselors);
		availableCounselorsToPrint.add("|____________________________________________________|");
				
		for (i = 0; i <balconyToPrint.size(); i++) {
			Util.print(balconyToPrint.get(i));
			Util.println(availableCounselorsToPrint.get(i));
		}
		balconyToPrint.clear();
		availableCounselorsToPrint.clear();
	}
	/**
	 * this method print the prosperity track
	 * @param gameBoard
	 */
	private void printProsperityTrack(GameBoardChange gameBoard) {
	
		prosperitySquare.add("");
		prosperitySquare.add("Prosperity");
		prosperitySquare.add("");
		prosperitySquare.add("Track");	
		
		Model prosperityTitleModel = new Model(prosperitySquare, 0, 14, true);
				
		prosperityTrackToPrint.add(prosperityTitleModel.getDraw());
		prosperitySquare.clear();		
				
		for (i = 0; i<gameBoard.getProsperityTrack().size(); i++) {
	
			prosperitySquare.add(String.valueOf(gameBoard.getProsperityTrack().get(i).getCoordinate()));
			prosperitySquare.add("SEPARATOR");
			//creazione null replace dei numeri giocatore
			String player1 = " ";
			String player2 = " ";
			String player3 = " ";
			String player4 = " ";
					
			if (0<gameBoard.getProsperityTrackCoordinate().size() && 
				i == gameBoard.getProsperityTrackCoordinate().get(1)) {
				player1 = "1";
			}
			if (1<gameBoard.getProsperityTrackCoordinate().size() &&
				i == gameBoard.getProsperityTrackCoordinate().get(2)) {
				player2 = "2";
			}
			prosperitySquare.add(player1+player2);
					
			if (2<gameBoard.getProsperityTrackCoordinate().size() &&
				i == gameBoard.getProsperityTrackCoordinate().get(3)) {
				player3 = "3";
			}
			if (3<gameBoard.getProsperityTrackCoordinate().size() &&
				i == gameBoard.getProsperityTrackCoordinate().get(4)) {
				player4 = "4";
			}
			prosperitySquare.add(player3+player4);	
			
			Model prosperitySquareModel = new Model(prosperitySquare, 0, 2, true);
					
			prosperityTrackToPrint.add(prosperitySquareModel.getDraw());
			prosperitySquare.clear();
		}
		
		//inizio bonus card citta
		if (!gameBoard.getKingBonusCards().isEmpty()) 
			bonusCardsToPrint.add(new BonusCardModel("King", gameBoard.getKingBonusCards().get(0)));
				
		if (gameBoard.getIronBonus() != null) 
			bonusCardsToPrint.add(new BonusCardModel("Iron", gameBoard.getIronBonus()));
				
		if (gameBoard.getBronzeBonus() != null) 
			bonusCardsToPrint.add(new BonusCardModel("Bronze", gameBoard.getBronzeBonus()));
				
		if (gameBoard.getSilverBonus() != null) 
			bonusCardsToPrint.add(new BonusCardModel("Silver", gameBoard.getSilverBonus()));
				
		if (gameBoard.getGoldBonus() != null) 
			bonusCardsToPrint.add(new BonusCardModel("Gold", gameBoard.getGoldBonus()));
				
		bonusCardTitle.add(" ______________________________________ ");
		bonusCardTitle.add("|              Bonus Cards             |");

		//inizio stampa percorso ricchezza
		for (i=0; i<prosperityTrackToPrint.get(0).size(); i++) {
			for (ArrayList<String> prosperityModel : prosperityTrackToPrint) {
					Util.print(prosperityModel.get(i));
			}
			// stampa bonus card
			if (i < 2) {
				Util.print(bonusCardTitle.get(i));
			}
			else {
				for (BonusCardModel bonusCard : bonusCardsToPrint) {
					Util.print(bonusCard.print(i-2));
				}
			}
			Util.println("");
		}
		prosperityTrackToPrint.clear();	
		bonusCardsToPrint.clear();
	}
	/**
	 * this method print the nobility track
	 * @param gameBoard
	 */
	private void printNobilityTrack(GameBoardChange gameBoard) {
		
		nobilitySquare.add("");
		nobilitySquare.add("");
		nobilitySquare.add("");
		nobilitySquare.add("Nobility");
		nobilitySquare.add("");
		nobilitySquare.add("Track");
		nobilitySquare.add("");
		nobilitySquare.add("");
		nobilitySquare.add("");
		
		Model nobilityTrackModel = new Model(nobilitySquare, 0, 10, true);
		
		nobilityTrackIcon.add(nobilityTrackModel.getDraw());
		
		for (i = 0; i < gameBoard.getNobilityTrack().size(); i++) {
			nobilityTrackToPrint.add(new NobilitySlotModel(gameBoard, i));	
		}

		for (i=0; i<nobilityTrackToPrint.get(0).getHeight(); i++) {
			Util.print(nobilityTrackIcon.get(0).get(i));
			
			for (NobilitySlotModel nobilityslotmodel : nobilityTrackToPrint) {
					Util.print(nobilityslotmodel.print(i));
			}
			Util.println("");
		}
		nobilityTrackToPrint.clear();
		nobilitySquare.clear();
		nobilityTrackIcon.clear();
	}
	/**
	 * this method prints the points track
	 * @param gameBoard
	 */
	private void printPointsTrack(GameBoardChange gameBoard) {
		
		pointsSquare.add("");
		pointsSquare.add("Points");
		pointsSquare.add("");
		pointsSquare.add("Track");
		
		Model pointsSquareTitleModel = new Model(pointsSquare, 0, 8, true);
		
		pointsTrackToPrint.add((ArrayList<String>) pointsSquareTitleModel.getDraw());
		pointsSquare.clear();
		
		for (i = 0; i<gameBoard.getPointsTrack().size(); i++) {
	
			pointsSquare.add(String.valueOf(gameBoard.getPointsTrack().get(i).getCoordinate()));
			pointsSquare.add("SEPARATOR");
			//creazione null replace dei numeri giocatore
			String player1 = " ";
			String player2 = " ";
			String player3 = " ";
			String player4 = " ";
			if (0<gameBoard.getPointsTrackCoordinate().size() &&
				i == gameBoard.getPointsTrackCoordinate().get(1)) {
				player1 = "1";
			}
			if (1<gameBoard.getPointsTrackCoordinate().size() &&
				i == gameBoard.getPointsTrackCoordinate().get(2)) {
				player2 = "2";
			}
			pointsSquare.add(player1+player2);
			
			if (2<gameBoard.getPointsTrackCoordinate().size() &&
				i == gameBoard.getPointsTrackCoordinate().get(3)) {
				player3 = "3";
			}
			if (3<gameBoard.getPointsTrackCoordinate().size() &&
				i == gameBoard.getPointsTrackCoordinate().get(4)) {
				player4 = "4";
			}
			pointsSquare.add(player3+player4);
			
			Model pointsSquareModel = new Model(pointsSquare, 0, 2, true);

			pointsTrackToPrint.add(pointsSquareModel.getDraw());
			pointsSquare.clear();
		}

		for (i=0; i<pointsTrackToPrint.get(0).size(); i++) {
			for (ArrayList<String> pointsModel : pointsTrackToPrint) {
				Util.print(pointsModel.get(i));
			}
			Util.println("");
		}
		pointsTrackToPrint.clear();
	}
	/**
	 * this method print all the player info
	 * @param player
	 */
	private void printPlayerInfo(PlayerChange player) {
	
		if (!player.getPossibleActions().isEmpty()){
			playerView.add(" ____________________________________________________________ ");
			playerView.add("|                       TURN OF PLAYER "+player.getPlayerNumber()+"                     |");
		}
		playerView.add("|____________________________________________________________|");
		playerView.add("|           YOU HAVE "+player.getAssistants().size()+" ASSISTANTS AND "+player.getEmporiums().size()+" EMPORIUMS           |");
		playerView.add("|____________________________________________________________!");

		for (String string : playerView) {
			Util.println(string);
		}
		playerView.clear();
	}
	/**
	 * this method print all the politic cards owned by the player
	 * @param player
	 */
	private void printPlayerPoliticCards(PlayerChange player) {
		
		Util.println("YOUR POLITIC CARDS:");
		if (player.getPoliticCards()!=null){
			for (PoliticCard politicCard : player.getPoliticCards()) {
				playerPoliticCards.add(new PoliticCardModel(politicCard));
			}
		//inizio stampa carte politiche giocatore
			for (i=0; i<playerPoliticCards.get(0).getHeight(); i++) {
				for (PoliticCardModel playerPoliticCard : playerPoliticCards) {
						Util.print(playerPoliticCard.print(i));
				}
				Util.println("");
			}
		}
		playerPoliticCards.clear();
	}
	/**
	 * this method print all the player permissioncards owned by the player
	 * @param player
	 */
	private void printPlayerPermissionCards(PlayerChange player) {

		Util.println("\n YOUR PERMISSION CARDS:");
		
		int count = 0;
		if (player.getPermissionCards()!=null){		
			for (PermissionCard playerPermissionCard : player.getPermissionCards()) {
				if (playerPermissionCard.getUsed()) {
					count++;
				}
				else playerPermissionCards.add(new PermissionCardModel(playerPermissionCard));
			}
			playerPermissionCards.add(new DeckModel(count, "Used"));
		//inizio stampa carte permesso giocatore
			for (i=0; i<playerPermissionCards.get(0).getHeight(); i++) {
				for (PrintableModel playerPermissionCard : playerPermissionCards) {
					Util.print(playerPermissionCard.print(i));
				}
				Util.println("");
			}
		}
		playerPermissionCards.clear();
	}
}