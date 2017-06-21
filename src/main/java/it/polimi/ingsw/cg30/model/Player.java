package it.polimi.ingsw.cg30.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.AnotherMainAction;
import it.polimi.ingsw.cg30.model.actions.AssistantHiring;
import it.polimi.ingsw.cg30.model.actions.BonusAction;
import it.polimi.ingsw.cg30.model.actions.BuildEmporium;
import it.polimi.ingsw.cg30.model.actions.BuildEmporiumKing;
import it.polimi.ingsw.cg30.model.actions.BuyPermissionCard;
import it.polimi.ingsw.cg30.model.actions.ChangePermissionCards;
import it.polimi.ingsw.cg30.model.actions.ElectCounselor;
import it.polimi.ingsw.cg30.model.actions.ElectCounselorAssistant;
import it.polimi.ingsw.cg30.model.actions.FastAction;
import it.polimi.ingsw.cg30.model.actions.MainAction;
import it.polimi.ingsw.cg30.model.actions.NoFastAction;
import it.polimi.ingsw.cg30.model.actions.market.BuyAction;
import it.polimi.ingsw.cg30.model.actions.market.SellAction;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1668813092903675953L;

	private final int number;
	private int finalPoints;
	private final String color;
	private List<PermissionCard> permissionCards;
	private List<Assistant> assistants;
	private List<Action> possibleActions;
	private List<PointsBonus> bonusCards;
	private List<Emporium> emporiums;
	private List<PoliticCard> politicCards;
	private IndicatorDisk prosperityDisk;
	private IndicatorDisk pointsDisk;
	private IndicatorDisk nobilityDisk;
	private transient Game game;
	private transient List<Action> actionsBackup;
	private final UUID playerId;
	private boolean isOnline;
	
	/**
	 * @method Player
	 * @param playerNumber
	 * @param color
	 * 
	 * @throws IllegalArgumentException if the number of the player is negative or 0
	 * @throws NullPointerException if the color or the game are null
	 */
	public Player(int playerNumber, String color, Game game, UUID playerId){

		if(playerNumber <=0)
			throw new IllegalArgumentException();
		if(color == null || game == null)
			throw new NullPointerException();
		
		permissionCards = new ArrayList<>();
		assistants = new ArrayList<>();
		emporiums = new ArrayList<>();
		politicCards = new ArrayList<>();
		prosperityDisk = new IndicatorDisk(this);
		pointsDisk = new IndicatorDisk(this);
		nobilityDisk = new IndicatorDisk(this);
		possibleActions = new ArrayList<>();
		bonusCards = new ArrayList<>();
		actionsBackup= new ArrayList<>();

		this.game = game;
		this.number = playerNumber;
		this.color = color;
		this.playerId=playerId;
		
		isOnline=true;
	}
	/**
	 * initialize player indicator disks, assistants,
	 * politic cards and emporiums
	 * 
	 */
	public void init() {
		this.initPoints();
		this.createInitialAssistant(this.number);
		this.initMoney(this.number);
		this.takePoliticCards(6);
		this.createEmporiums();
		this.initNobility();
	}

	/**
	 * the method gives the initial assistants to the player
	 * 
	 * @param playerNumber
	 *            number of the player
	 * @throws IllegalArgumentException
	 *             if player number is negative or equal to 0
	 */
	public void createInitialAssistant(int playerNumber)  {

		if (playerNumber <= 0)
			throw new IllegalArgumentException();

		for (int i = 0; i < playerNumber; i++) {

			this.assistants.add(new Assistant());
		}
	}

	/**
	 * the method creates a new assistant and gives it to the player
	 * 
	 */
	public void takeAssistant() {
		assistants.add(new Assistant());
	}

	/**
	 * the method adds the choosen politic cards of the player to the deck of
	 * politic cards and remove them from the hand of the player
	 * 
	 * @param toDeletePoliticCards
	 */
	public void returnPoliticCards(List<PoliticCard> toDeletePoliticCards){

		if(toDeletePoliticCards != null){
			game.getGameBoard().getPoliticCards().addAll(toDeletePoliticCards);
			for (PoliticCard card : toDeletePoliticCards){
				politicCards.remove(card);
			}
		}
	}

	/**
	 * the method pick the selected permission card and gives it to the player
	 * 
	 * @param selectedPermissionCard
	 *            picked by the player after electing a counselor
	 * @throws NullPointerException
	 *             when the selectedPermissionCard is null
	 */
	public void takePermissionCard(PermissionCard selectedPermissionCard) {

		if (selectedPermissionCard == null)
			throw new NullPointerException();

		permissionCards.add(selectedPermissionCard.getRegion().pickPermissionCard(selectedPermissionCard));
	}

	/**
	 * 
	 * the method gives to the player a politic card
	 * 
	 * @param amount
	 *            of cards picked by the player
	 * @throws IllegalArgumentException
	 *             if the the amount is negative
	 */
	public void takePoliticCards(int amount)  {

		if (amount < 0)
			throw new IllegalArgumentException("negative amount");

		for (int i = 0; i < amount; i++) {
			politicCards.add(game.getGameBoard().pickPoliticCard());
		}
	}

	/**
	 * the method gives the starting money to the player
	 * 
	 * @param playerNumber
	 *            number of the player
	 */
	public void initMoney(int playerNumber) {

		this.game.getGameBoard().getProsperityTrack().get(9 + playerNumber).getDisks().add(this.prosperityDisk);
		this.getProsperityDisk().setSlot(this.game.getGameBoard().getProsperityTrack().get(9 + playerNumber));
	}

	/**
	 * sets initial nobility points to 0
	 */
	public void initNobility() {

		this.game.getGameBoard().getNobilityTrack().get(0).getDisks().add(this.nobilityDisk);
		this.getNobilityDisk().setSlot(this.game.getGameBoard().getNobilityTrack().get(0));
	}

	/**
	 * the method sets the initial points of the player to 0
	 * 
	 */
	public void initPoints() {

		this.game.getGameBoard().getPointsTrack().get(0).getDisks().add(this.pointsDisk);
		this.getPointsDisk().setSlot(this.game.getGameBoard().getPointsTrack().get(0));
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}
	/**
	 * gives player the numer of emporium setted in the game
	 */
	public void createEmporiums() {

		for (int i=0; i < game.getNumEmporium(); i++) {
			emporiums.add(new Emporium(this.color, this.number));
		}
	}

	/**
	 * 
	 * @return player's prosperityDisk
	 */
	public IndicatorDisk getProsperityDisk() {
		return prosperityDisk;
	}

	/**
	 * if the movement goes out of bounds, the method moves the disk on the last
	 * slot of the track
	 * 
	 * @param offset
	 *            of money gained or lost
	 * @throws IllegalArgumentException
	 *             if the player hasn't enough money
	 */
	public void moveProsperityDisk(int offset)  {

		int currentPosition = this.getProsperityDisk().getSlot().getCoordinate();

		if ((currentPosition + offset) < 0)
			throw new IllegalArgumentException("not enough money");

		if (currentPosition + offset > this.game.getGameBoard().getProsperityTrack().size() - 1) {
			this.game.getGameBoard().getProsperityTrack().get(this.game.getGameBoard().getProsperityTrack().size() - 1)
					.getDisks().add(this.prosperityDisk);
			this.game.getGameBoard().getProsperityTrack().get(currentPosition).getDisks().remove(this.prosperityDisk);
			this.getProsperityDisk().setSlot(this.game.getGameBoard().getProsperityTrack()
					.get(this.game.getGameBoard().getProsperityTrack().size() - 1));
			return;
		}
		this.game.getGameBoard().getProsperityTrack().get(currentPosition + offset).getDisks().add(this.prosperityDisk);
		this.game.getGameBoard().getProsperityTrack().get(currentPosition).getDisks().remove(this.prosperityDisk);
		this.getProsperityDisk().setSlot(this.game.getGameBoard().getProsperityTrack().get(currentPosition + offset));
	}

	/**
	 * if the movement goes out of bounds, the method moves the disk on the last
	 * slot of the track
	 * 
	 * @param offset
	 *            of points gained or lost
	 * @throws IllegalArgumentException
	 *             if the player hasn't enough points
	 */
	public void movePointsDisk(int offset)  {

		int currentPosition = this.getPointsDisk().getSlot().getCoordinate();

		if ((currentPosition + offset) < 0)
			throw new IllegalArgumentException("not enough points");

		if (currentPosition + offset > this.game.getGameBoard().getPointsTrack().size() - 1) {
			this.game.getGameBoard().getPointsTrack().get(this.game.getGameBoard().getPointsTrack().size() - 1)
					.getDisks().add(this.pointsDisk);
			this.game.getGameBoard().getPointsTrack().get(currentPosition).getDisks().remove(this.pointsDisk);
			this.getPointsDisk().setSlot(this.game.getGameBoard().getPointsTrack()
					.get(this.game.getGameBoard().getPointsTrack().size() - 1));
			return;
		}
		this.game.getGameBoard().getPointsTrack().get(currentPosition + offset).getDisks().add(this.pointsDisk);
		this.game.getGameBoard().getPointsTrack().get(currentPosition).getDisks().remove(this.pointsDisk);
		this.getPointsDisk().setSlot(this.game.getGameBoard().getPointsTrack().get(currentPosition + offset));
	}

	/**
	 * if the movement goes out of bounds, the method moves the disk on the last
	 * slot of the track
	 * 
	 * @param offset
	 *            of slots gained or lost
	 * @throws IllegalArgumentException
	 *             if the player hasn't enough slots
	 */
	public void moveNobilityDisk(int offset){
		int currentPosition = this.getNobilityDisk().getSlot().getCoordinate();

		if ((currentPosition + offset) < 0)
			throw new IllegalArgumentException("not enough points");

		else if (currentPosition + offset > this.game.getGameBoard().getNobilityTrack().size() - 1) {
			this.game.getGameBoard().getNobilityTrack().get(this.game.getGameBoard().getNobilityTrack().size()-1)
					.getDisks().add(this.nobilityDisk);
			this.game.getGameBoard().getNobilityTrack().get(currentPosition).getDisks().remove(this.nobilityDisk);
			this.getNobilityDisk().setSlot(this.game.getGameBoard().getNobilityTrack()
					.get(this.game.getGameBoard().getNobilityTrack().size() - 1));
		}

		else {
			this.game.getGameBoard().getNobilityTrack().get(currentPosition + offset).getDisks().add(this.nobilityDisk);
			this.game.getGameBoard().getNobilityTrack().get(currentPosition).getDisks().remove(this.nobilityDisk);
			this.getNobilityDisk().setSlot(this.game.getGameBoard().getNobilityTrack().get(currentPosition + offset));
		}
	}

	/**
	 * 
	 * @return the nobility disk
	 */
	public IndicatorDisk getNobilityDisk() {
		return nobilityDisk;
	}

	/**
	 * @return the playerNumber
	 */
	public int getPlayerNumber() {
		return number;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * 
	 * @return the permission cards owned by the player
	 */
	public List<PermissionCard> getPermissionCards() {
		return permissionCards;
	}

	/**
	 * @return the ownedEmporiums
	 */
	public List<Emporium> getEmporiums() {
		return emporiums;
	}

	/**
	 * @return the ownedPoliticCards
	 */
	public List<PoliticCard> getPoliticCards() {
		return politicCards;
	}

	/**
	 * 
	 * @return the point disk
	 */
	public IndicatorDisk getPointsDisk() {
		return pointsDisk;
	}

	/**
	 * 
	 * @return the possible actions of the player
	 */
	public List<Action> getPossibleActions() {
		return possibleActions;
	}

	/**
	 * the method add an action to the array of possible actions
	 * 
	 * @param possibleActions
	 * @throws NullPointerException if the possible action is null
	 */
	public void addPossibleActions(Action possibleActions) {

		if (possibleActions == null)
			throw new NullPointerException();

		this.possibleActions.add(possibleActions);
	}
	/**
	 * 
	 * @return the bonus cards
	 */
	public List<PointsBonus> getBonusCards() {
		return bonusCards;
	}

	/**
	 * the method adds the four fast actions to the possible actions of the
	 * player
	 */
	public void mainActionFiller() {
		this.addPossibleActions(new BuildEmporium(null));
		this.addPossibleActions(new BuildEmporiumKing(null));
		this.addPossibleActions(new BuyPermissionCard(null));
		this.addPossibleActions(new ElectCounselor(null));
	}

	/**
	 * the method adds the four fast actions and the "special" action No Fast
	 * Action to the possible actions of the player
	 */
	public void fastActionFiller() {
		this.addPossibleActions(new AnotherMainAction());
		this.addPossibleActions(new AssistantHiring());
		this.addPossibleActions(new ElectCounselorAssistant(null));
		this.addPossibleActions(new ChangePermissionCards(null));
		this.addPossibleActions(new NoFastAction());
	}

	/**
	 * the method removes the fast actions of the player
	 */
	public void fastActionRemover() {
		ArrayList<Action> toRemove = new ArrayList<>();

		for (Action action : this.getPossibleActions()) {
			if (action.getClass().getSuperclass() == FastAction.class)
				toRemove.add(action);
		}

		for (Action action : toRemove) {
			this.getPossibleActions().remove(action);
		}
	}

	/**
	 * the method removes the main actions of the player
	 */
	public void mainActionRemover() {
		ArrayList<Action> toRemove = new ArrayList<>();

		for (Action action : this.getPossibleActions()) {
			if (action.getClass().getSuperclass() == MainAction.class)
				toRemove.add(action);
			if (toRemove.size()==4) 
				break;
		}

		for (Action action : toRemove) {
			this.getPossibleActions().remove(action);
		}
	}
	/**
	 * the method removes the bonus action of the player
	 */
	public void bonusActionRemover(Action actionToRemove) {
		
		ArrayList<Action> toRemove = new ArrayList<>();
		
		for (Action action : actionToRemove.getPlayer().getPossibleActions()){
			if (action.getClass().getSimpleName().equals(actionToRemove.getClass().getSimpleName())){
				toRemove.add(action);
			}
		}
		actionToRemove.getPlayer().getPossibleActions().removeAll(toRemove);
	}
	/**
	 * removes actions of class SellAction
	 * from player's possibleActions
	 */
	public void sellActionRemover(){
		ArrayList<Action> toRemove = new ArrayList<>();
		
		for (Action action : this.getPossibleActions()) {
			if (action.getClass() == SellAction.class)
				toRemove.add(action);
		}

		for (Action action : toRemove) {
			this.getPossibleActions().remove(action);
		}
	}
	/**
	 * removes actions of class BuyAction
	 * from player's possibleActions
	 * 
	 */
	public void buyActionRemover(){
		ArrayList<Action> toRemove = new ArrayList<>();
		
		for (Action action : this.getPossibleActions()) {
			if (action.getClass() == BuyAction.class)
				toRemove.add(action);
		}

		for (Action action : toRemove) {
			this.getPossibleActions().remove(action);
		}
	}
	
	/**
	 * 
	 * @return true if player's possibleActions
	 * contains at least 1 Action of class BonusAction
	 */
	public boolean checkIfHasBonus(){

		for (Action action : this.getPossibleActions()) {
			if (action.getClass().getSuperclass() == BonusAction.class)
				return true;
		}
		return false;
	}
	/**
	 * makes a backup of all possibleActions
	 * which are not of BonusAction class.
	 * puts the backup made in  player's actionsBackup.
	 * removes all actions which are not 
	 * of BonusAction class
	 * from possibleActions
	 * 
	 * 
	 * 	 */
	public void makeBackUpAndRemove(){
		
		ArrayList<Action> actionCopy=new ArrayList<>(this.getPossibleActions());
		ArrayList<Action> toRemove = new ArrayList<>();

		for (Action action : actionCopy) {
			if (action.getClass().getSuperclass() == BonusAction.class)
				toRemove.add(action);
		}

		for (Action action : toRemove) {
			actionCopy.remove(action);
		}
		this.setActionsBackup(new ArrayList<>(actionCopy));
		this.getPossibleActions().removeAll(actionCopy);
	}
		
	
	/**
	 * the method
	 * 
	 * @param amount
	 *            of assistants to remove from the player
	 *            CAN be 0, example: when removing assistants in BuildEmporium
	 * @throws IllegalArgumentException
	 *             if the amount is negative 
		
	 */
	public void removeAssistants(int amount)  {

		if (amount < 0)
			throw new IllegalArgumentException();
		
		if (amount > assistants.size()){
			assistants.clear();
		}

		else {
			for (int i = 0; i < amount; i++) {
				assistants.remove(0);
			}
		}
	}

	/**
	 * the method removes the first emporium
	 * 
	 */
	public void removeEmporium() {
		emporiums.remove(0);
	}

	/**
	 * @param assistants
	 *            the assistants to set
	 */
	public void setAssistants(List<Assistant> assistants) {
		this.assistants = assistants;
	}
	/**
	 * 
	 * @return the assistants owned by the player
	 */
	public List<Assistant> getAssistants() {
		return this.assistants;
	}
/**
 * 
 * @return actionsBackup list
 */
	public List<Action> getActionsBackup() {
		return actionsBackup;
	}
	/**
	 * set possibleActions list
	 * @param possibleActions
	 */
	public void setPossibleActions(List<Action> possibleActions) {
		this.possibleActions = possibleActions;
	}
	/**
	 * set actionsBackup list
	 * @param actionsBackup
	 */
	public void setActionsBackup(List<Action> actionsBackup) {
		this.actionsBackup = actionsBackup;
	}
	/**
	 * 
	 * @return playerId
	 */
	public UUID getUUID(){
		return playerId;
	}
	/**
	 * set the player offline
	 */
	public void setOffline() {
		isOnline = false;	
	}
	/**
	 * 
	 * @return true if the player is online
	 */
	public boolean isOnline(){
		return isOnline;
	}
	/**
	 * 
	 * @return the final points of the player
	 */
	public int getFinalPoints(){
		return finalPoints;
	}
	/**
	 * 
	 * @param p points to add 
	 */
	public void addFinalPoints(int p){
		this.finalPoints+=p;
	}
}