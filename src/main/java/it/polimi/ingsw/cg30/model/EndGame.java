
package it.polimi.ingsw.cg30.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import it.polimi.ingsw.cg30.model.bonus.PointsBonus;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;

public class EndGame implements GameState{
	
	private Game game;
	private Player currentPlayer;
	private List<Player> playersRemained;
	private Player playerWithZeroEmporiums;
	
	
	/**
	 * this constructor create the end game state, to call when a player
	 * has built all the emporiums. This state make the last lap of turns
	 * and at the end calculate the winner of the game.
	 * @param game the game to manage
	 * @param player the player that has biult all the emporiums
	 */
	public EndGame(Game game, Player player) {

		if (game == null)
			throw new NullPointerException();
		this.game = game;
		game.setGameState(this);
		this.playersRemained = new ArrayList<>(game.getPlayers());
		this.playerWithZeroEmporiums = player;
		run();
	}

	
	/**
	 * run the last lap of the game
	 */
	@Override
	public void run() {
		playersRemained.remove(playerWithZeroEmporiums);
		Player firstOfLastLap = null;
		for (Player player : playersRemained) {
			if (player.getPlayerNumber() == playerWithZeroEmporiums.getPlayerNumber()+1) {
				firstOfLastLap=player;
				break;
			}
		}
		if (firstOfLastLap == null){
			firstOfLastLap=playersRemained.get(0);
		}
		playerTurnSetup(firstOfLastLap);
	}

	/**
	 * the method adds to the player his possible actions and notifies the view
	 * that it's his turn
	 * 
	 * @param currentPlayer
	 * @throws NullPointerException
	 *             if the player is null
	 */
	private void playerTurnSetup(Player currentPlayer) {
		
		
		if (currentPlayer == null)
			throw new NullPointerException();
		
		this.currentPlayer = currentPlayer;
		
		if (!this.checkPlayersNotAllOffline()){
			game.getGameBoard().setWinner(calculateTheWinner(game.getPlayers()));
		}
		
		else{
			if (!currentPlayer.isOnline()){
				nextPlayer();
			}
			else {
				currentPlayer.mainActionFiller();
				currentPlayer.fastActionFiller();
				currentPlayer.takePoliticCards(1);
				game.notifyObservers(new PlayerChange(currentPlayer, currentPlayer.getUUID()));
				game.resetTimer();
			}
		}
	}
	/**
	 * @return currentPlayer of the State
	 */
	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * 
	 */
	@Override
	public void nextPlayer() {
		Player p;
		if (!playersRemained.isEmpty()){
			
			playersRemained.remove(currentPlayer);
		}
		if (!playersRemained.isEmpty()){
			p = playersRemained.get(0);
			playerTurnSetup(p);
		}
		else {
			game.getGameBoard().setWinner(calculateTheWinner(game.getPlayers()));
			game.notifyObservers(new GameBoardChange(game.getGameBoard(),game.getPlayers(),game.getUUID(), null));
		}
	}

	/**
	 * updates the player lap checking his possible actions
	 */
	@Override
	public void update() {
		game.notifyObservers(new GameBoardChange(game.getGameBoard(),game.getPlayers(),game.getUUID(), null));
		
		if (currentPlayer.getPossibleActions().isEmpty()) {
			nextPlayer();
		}
		
		for (Player player : game.getPlayers()){
			game.notifyObservers(new PlayerChange(player, player.getUUID()));
		}
	}
	/**
	 * this method calculate the winner.
	 * The player that is first on the nobility track earn 5 points,
	 * the second on the nobility track earn 2 points. If there are more
	 * that one player that is the 'first' on this track, all the firsts
	 * earns 5 points but the seconds earns 0 points. if there is only one
	 * first but a lot of 'second' on the track, all the seconds earns 2 points.
	 * 
	 * 
	 * @param players
	 * @return
	 */
	private Player calculateTheWinner(List<Player> players){
		
		ArrayList<Player> nobilityFirst = new ArrayList<>();
		ArrayList<Player> nobilitySecond = new ArrayList<>();
		ArrayList<Player> winners = new ArrayList<>();
		Player winner;
		
		// ribalta il nobilityTrack
		Collections.reverse(game.getGameBoard().getNobilityTrack());
		// lo scorro dall'inizio cosi primi sono in realta gli ultimi
		for (NobilitySlot nobilitySlot : game.getGameBoard().getNobilityTrack()) {
			// controllo i primi
			if (!nobilitySlot.getDisks().isEmpty() && nobilityFirst.isEmpty()) {

				Iterator<IndicatorDisk> it = nobilitySlot.getDisks().iterator();
				while (it.hasNext()) {
					nobilityFirst.add(it.next().getPlayer());
					it.remove();
				}
			}
			// controllo i secondi
			if (!nobilitySlot.getDisks().isEmpty() && nobilitySecond.isEmpty()) {

				Iterator<IndicatorDisk> it = nobilitySlot.getDisks().iterator();
				while(it.hasNext()) {
					nobilitySecond.add(it.next().getPlayer());
					it.remove();
				}
			}
		}
		// Ora che ho i primi ed i secondi assegno i bonus
		if (nobilityFirst.size() > 1) {
			for (Player player : nobilityFirst) {
				player.addFinalPoints(5);
			}
		}
		else if (nobilityFirst.size() == 1) {
			nobilityFirst.get(0).movePointsDisk(5);
			for (Player player : nobilitySecond) {
				player.addFinalPoints(2);
			}
		}
		// chi ha piu tessere permesso guadagna 3 punti
		ArrayList<Player> hasMorePermissionCards = new ArrayList<>();
		hasMorePermissionCards.add(players.get(0));
		// can i use an iterator instead of the second for here?
		for (Player player : players) {
			if (!(player == hasMorePermissionCards.get(0))) {
				if (player.getPermissionCards().size() == hasMorePermissionCards.get(0).getPermissionCards().size()) {
					hasMorePermissionCards.add(player);
				} 
				else if (player.getPermissionCards().size() > hasMorePermissionCards.get(0).getPermissionCards().size()) {
					hasMorePermissionCards.clear();
					hasMorePermissionCards.add(player);
				}
			}
		}
		// do il bonus dei 3 punti
		for (Player player : hasMorePermissionCards) {
			player.addFinalPoints(3);
		}
		// somma tessere bonus
		for (Player player : players) {
			int sumOfBonusCards = 0;
			for (PointsBonus pointsBonus : player.getBonusCards()) {
				sumOfBonusCards = sumOfBonusCards + pointsBonus.getNumberOfBonus();
			}
			player.addFinalPoints(sumOfBonusCards);
		}
		// determino i vincitori
		winners.add(players.get(0));

		for (Player player : players) {
			if (!(player == winners.get(0))) {
				if (player.getPointsDisk().getSlot().getCoordinate() 
					+ player.getFinalPoints() 
					== 
					winners.get(0).getPointsDisk().getSlot().getCoordinate()
					+ winners.get(0).getFinalPoints()) {
					
					winners.add(player);
				} 
				else if (player.getPointsDisk().getSlot().getCoordinate() +
						 player.getFinalPoints()
						 > 
						 winners.get(0).getPointsDisk().getSlot().getCoordinate() +
						 winners.get(0).getFinalPoints()) {
					winners.clear();
					winners.add(player);
				}
			}
		}
		// tra i vincitori seleziono il vincitore che ha piu assistenti e carte politiche
		winner = winners.get(0);

		for (Player player : winners) {
			if (!(player == winners.get(0))&& ((player.getAssistants().size()) + (player.getPoliticCards().size())) > 
					((winners.get(0).getAssistants().size()) + (winners.get(0).getPoliticCards().size()))) {
					
					winner = player;
			}
		}
		// torno il vincitore
		return winner;
	}
	/**
	 * checks if all the players are offline
	 * @return false if all the players are offline
	 */
	public boolean checkPlayersNotAllOffline(){
		for(Player player: game.getPlayers()){
			if (player.isOnline()) 
				return true;
		}
		return false;
	}

}