package it.polimi.ingsw.cg30.view.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import it.polimi.ingsw.cg30.connection.client.Client;
import it.polimi.ingsw.cg30.connection.client.ClientNetworkInterface;
import it.polimi.ingsw.cg30.connection.client.Graphic;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.AnotherMainAction;
import it.polimi.ingsw.cg30.model.actions.AssistantHiring;
import it.polimi.ingsw.cg30.model.actions.BuildEmporium;
import it.polimi.ingsw.cg30.model.actions.BuildEmporiumKing;
import it.polimi.ingsw.cg30.model.actions.BuyPermissionCard;
import it.polimi.ingsw.cg30.model.actions.ChangePermissionCards;
import it.polimi.ingsw.cg30.model.actions.CityTokenAction;
import it.polimi.ingsw.cg30.model.actions.ElectCounselor;
import it.polimi.ingsw.cg30.model.actions.ElectCounselorAssistant;
import it.polimi.ingsw.cg30.model.actions.FreePermissionCardAction;
import it.polimi.ingsw.cg30.model.actions.NoFastAction;
import it.polimi.ingsw.cg30.model.actions.RepeatPermissionCardAction;
import it.polimi.ingsw.cg30.model.actions.market.BuyAction;
import it.polimi.ingsw.cg30.model.actions.market.SellAction;
import it.polimi.ingsw.cg30.model.actions.market.Thing;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;
import it.polimi.ingsw.cg30.model.util.Util;

public class CLIGraphic implements Graphic {
	/**
	 * scanner used for cli input by user
	 */
	private Scanner input = new Scanner(System.in);
	/**
	 * the printer object that print the game model
	 */
	private Printer printer = new Printer();
	/**
	 * prompt to select the city and calls insertThings
	 */
	@Override
	public String selectCity() {

		Util.println("Seleziona la citt√†: [1..N - per selezionare]");
		return insertThing();
	}
/**
 * prompt to select the counselors and calls insertThing
 */
	@Override
	public String selectCounselor() {

		Util.println("Seleziona il consigliere: [1..N - per selezionare]");
		return insertThing();
	}
/**
 * prompt to select the balconies and calls insertThing
 */
	@Override
	public String selectBalcony() {

		Util.println("Seleziona il balcone: [1..N - per selezionare]");
		return insertThing();
	}
	/**
	 * prompt to select the balconies and calls insertThing
	 */

	@Override
	public String selectBalconyFromAll() {

		Util.println("Seleziona il balcone: [1..N (4 for the king balcony) - per selezionare]");
		return insertThing();
	}
/**
 * prompt to select politic cards and calls insertThings
 */
	@Override
	public List<String> selectPoliticCards() {

		Util.println("Seleziona le carte politiche:  [END - per terminare] [1..N - per selezionare]");
		return insertThings();
	}

	/**
	 * prompt to select PermissionCard and calls insertThing
	 */
	@Override
	public String selectPermissionCard() {

		Util.println("Seleziona la Tessera Permesso:  [1..N - per selezionare]");
		return insertThing();
	}
	/**
	 * prompt to select Region and calls insertThing
	 */

	@Override
	public String selectRegion() {

		Util.println("Seleziona la regione: [1..N - per selezionare]");
		return insertThing();
	}
	/**
	 * lets you insert at most four strings,
	 * including END
	 * @return array of string inserted
	 */

	@Override
	public List<String> insertThings() {

		ArrayList<String> choice = new ArrayList<>();

		for (int i = 0; i < 4; i++) {

			String temp = input.nextLine();

			if (("END").equals(temp)) {
				choice.add(temp);
				return choice;
			}
			choice.add(temp);
		}
		return choice;
	}
/**
 * lets you insert one string
 * @return string inserted
 */
	@Override
	public String insertThing() {

		String choice;
		choice = input.nextLine();
		return choice;
	}
	
/**
 * calls Printer's printGame
 */
	@Override
	public void printGameBoard(GameBoardChange gameBoard) {
		printer.printGame(gameBoard);
	}

	/**
	 * calls Printer's printPlayer and printInstructions
	 */
	@Override
	public void printPlayer(PlayerChange player) {
		printer.printPlayer(player);
		printInstructions(player);
	}
	/**
	 *print possible actions 
	 */
	@Override
	public void printPossibleAction(PlayerChange player) {
			
		Util.println("Actions: ");

		for (int i = 0; i < player.getPossibleActions().size(); i++) {
			Util.println(player.getPossibleActions().get(i).getActionName());
		}
		
	}
	/**
	 * print prompts for what is to be  inserted
	 * @param player
	 */
	public void printInstructions(PlayerChange player){
		Util.println("Type 'exit' to exit, 'msg'+message for a chat message!");
		try{
			if (Client.getInstance().getPlayer() != null &&
			    !(Client.getInstance().getPlayer().getPossibleActions().isEmpty())){
				
				Util.println(
					"Select the action: [1F..4F] for fast actions \n"
					+ "[1M...4M] for main actions \n"
					+ "[buy or sell] for market actions \n"
					+ "[0] to skip fast action! \n"
					+ "[1B..3B] for bonus actions");
				printPossibleAction(player);
			}
		} catch (NullPointerException e){
			Util.exception(e);
		}
	}
/**
 * gets input and gives prompts for marketBuyLap
 */
	@Override
	public void buyItems(List<Thing> things, Action action) {

		String choice = "";

		Util.println("Seleziona gli oggetti da comprare [1...n END per terminare]: ");

		while (!("END").equals(choice)) {
			choice = input.nextLine();
			if (action.isLegitChoice(choice, things.size())) {
				((BuyAction) action).addThingToBuy(things.get(Integer.parseInt(choice) - 1));
			} else
				Util.println("Selezione non valida! Ritenta");
		} 
	}
	/**
	 * gets input and gives prompts for marketSellLap
	 */

	@Override
	public void sellItems(Action action) {

		String choice;
		boolean selection = true;
		while (selection) {
			Util.println("quale aiutante vuoi vendere? [1...n END per terminare]: ");
			choice = input.nextLine();
			if (!"END".equals(choice)) {
				if (action.isLegitChoice(choice, Client.getInstance().getPlayer().getAssistants().size())) {
					int price = selectPrice();
					Thing thing = new Thing(price, Client.getInstance().getPlayer().getAssistants().get(Integer.parseInt(choice) - 1),
									Client.getInstance().getPlayer().getPlayerNumber());
					((SellAction) action).addThingforSale(thing);
				}
			} else if ("END".equals(choice))
				selection = false;
		}

		selection = true;
		while (selection) {
			Util.println("quale carta politica vuoi vendere? [1...n END per terminare]: ");
			choice = input.nextLine();
			if (!"END".equals(choice)) {
				if (action.isLegitChoice(choice, Client.getInstance().getPlayer().getPoliticCards().size())) {
					int price = selectPrice();
					Thing thing = new Thing(price,
							Client.getInstance().getPlayer().getPoliticCards().get(Integer.parseInt(choice) - 1), 
							Client.getInstance().getPlayer().getPlayerNumber());
					((SellAction) action).addThingforSale(thing);
				}
			} else if ("END".equals(choice))
				selection = false;
		}

		selection = true;
		while (selection) {
			Util.println("quale carta permesso vuoi vendere? [1...n END per terminare]: ");
			choice = input.nextLine();
			if (!"END".equals(choice)) {
				if (action.isLegitChoice(choice, Client.getInstance().getPlayer().getPermissionCards().size())) {
					int price = selectPrice();
					Thing thing = new Thing(price,
							Client.getInstance().getPlayer().getPermissionCards().get(Integer.parseInt(choice) - 1),
							Client.getInstance().getPlayer().getPlayerNumber());
					((SellAction) action).addThingforSale(thing);
				}
			} else if ("END".equals(choice))
				selection = false;
		}
	}
/**
 * lets you choose a price
 * @return priceSelected
 */
	public int selectPrice() {
		Util.println("A che prezzo?");
		String choice;
		choice = input.nextLine();
		return Integer.parseInt(choice);
	}
	/**
	 * lets you choose bonuses
	 * @return List of strings selected
	 */
	public List<String> selectBonuses() {

		Util.println("per favore inserisci  bonus richiesti \n"+
					 "1B: citt‡ [END - per terminare] [1..N - per selezionare] \n"+
					 "2B: prima la regione, poi la carta [END - per terminare] [1..N - per selezionare]\n"+
					 "3B: carta permesso [END - per terminare] [1..N - per selezionare]");
		ArrayList<String> choice = new ArrayList<>();
		String temp=null;
		
		while (!("END").equals(temp)) {

			temp = input.nextLine();
			if(!"END".equals(temp))
						choice.add(temp);
		}
		return choice;
	}
	
/**
 * prints rooms list
 */
	@Override
	public void updateRooms(){
		Util.println("ROOMS:");
		for (String string : Client.getInstance().getRoomsList()) {
			Util.println(string);
		}
	}
	/**
	 * lets you select rooms
	 */
	@Override
	public void roomSelection(UUID uuid, ClientNetworkInterface connection) {
		String selection;

		Util.println(Client.getInstance().getUsername() + " select a room below or type 'new' to create a new room");
		

		selection = input.nextLine();


		if (("new").equals(selection)) {

			selection = "";
			while (!(selection.equals(Util.CHOICE_TWO) || selection.equals(Util.CHOICE_THREE) || selection.equals(Util.CHOICE_FOUR))) {
				Util.println(Client.getInstance().getUsername() + " select number of players for your room between 2-4");
				selection = input.nextLine();
			}	
			
			Util.println("Select a name for the room!");
			String roomName = input.nextLine();
			Util.println("Select a map where to play!");
			for (String string : Client.getInstance().getMapsList()) {
				Util.println(string);
			}
			int mapNumber = input.nextInt();
			String mapName = Client.getInstance().getMapsList().get(mapNumber-1);
			Util.println("Select the number of emporiums of the game!");
			int numEmporium = input.nextInt();

			connection.createRoom(uuid, roomName, mapName, Integer.parseInt(selection), numEmporium);
		}
		else if (Util.isLegitChoice(selection, Client.getInstance().getRoomsList().size())) {
			connection.joinRoom(uuid, Client.getInstance().getRoomsList().get(Integer.parseInt(selection)-1));
		}
	}
	
/**
 * print a message from chat
 */
	@Override
	public void printChatMessage(String username, String message) {
		Util.println(username+": "+message);
	}
/**
 * listens to player commands inserted
 */
	@Override
	public void commandListener() {
		Action action;
		ArrayList<String> choice;
		String commandIn="";
		while(!("exit").equals(commandIn)){
			printInstructions(Client.getInstance().getPlayer());
			commandIn = input.nextLine();
			
			if (commandIn.startsWith("msg")){
				commandIn.replaceFirst("msg", "");
				Client.getInstance().sendMessage(commandIn);
			}
			else if (("exit").equals(commandIn)){
				Client.getInstance().getConnection().disconnect();
			}
			else if (("0").equals(commandIn)){
				action = new NoFastAction();
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("1M").equals(commandIn)){
				choice = new ArrayList<>();
				choice.add(selectPermissionCard());
				choice.add(selectCity());
				action = new BuildEmporium(choice);
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("2M").equals(commandIn)){
				choice = new ArrayList<>();
				choice.addAll(selectPoliticCards());
				choice.add(selectCity());
				action = new BuildEmporiumKing(choice);
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("3M").equals(commandIn)){
				choice = new ArrayList<>();
				choice.addAll(selectPoliticCards());
				choice.add(selectBalcony());
				choice.add(selectPermissionCard());
				action = new BuyPermissionCard(choice);
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("4M").equals(commandIn)){
				choice = new ArrayList<>();
				choice.add(selectCounselor());
				choice.add(selectBalconyFromAll());
				action = new ElectCounselor(choice);
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("1F").equals(commandIn)){
				action = new AnotherMainAction();
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("2F").equals(commandIn)){
				action = new AssistantHiring();
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("3F").equals(commandIn)){
				choice = new ArrayList<>();
				choice.add(selectCounselor());
				choice.add(selectBalconyFromAll());
				action = new ElectCounselorAssistant(choice);
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("4F").equals(commandIn)){
				choice = new ArrayList<>();
				choice.add(selectRegion());
				action = new ChangePermissionCards(choice);
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("sell").equals(commandIn)){
				action = new SellAction();
				sellItems(action);
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("buy").equals(commandIn)){
				action = new BuyAction();
				buyItems(Client.getInstance().getGameBoard().getAllThingsForSale(), action);
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("1B").equals(commandIn)){
				choice = new ArrayList<>();
				choice.addAll(selectBonuses());
				action=new CityTokenAction(choice.size(),choice);
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("2B").equals(commandIn)){
				choice = new ArrayList<>();
				choice.addAll(selectBonuses());
				action=new FreePermissionCardAction(choice.size()/2,choice);
				Client.getInstance().getConnection().sendAction(action);
			}
			else if (("3B").equals(commandIn)){
				choice = new ArrayList<>();
				choice.addAll(selectBonuses());
				action=new RepeatPermissionCardAction(choice.size(),choice);
				Client.getInstance().getConnection().sendAction(action);
			}
		}
	}
}