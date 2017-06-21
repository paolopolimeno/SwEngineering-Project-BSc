package it.polimi.ingsw.cg30.model;

import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;

public class TurnLap implements GameState {

	/**
	 * the game to manage
	 */
	private Game game;
	/**
	 * the player that can perfom the actions
	 */
	private Player currentPlayer;
	
	/**
	 * 
	 * @param game
	 * @throws NullPointerException
	 *             if the game is null
	 */
	public TurnLap(Game game) {

		if (game == null)
			throw new NullPointerException();
		this.game = game;
		game.setGameState(this);
		run();
	}
	/**
	 * run a turn lap
	 */
	@Override
	public void run() {

		for (Player player : game.getPlayers()) {

			if (player.getPlayerNumber() == 1) {
				playerTurnSetup(player);
			}
		}
	}
	/**
	 * the method adds to the player his possible actions and notifies the view
	 * that it's his turn. it also starts timer.
	 * if player is offline just calls next turn.
	 * 
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
			game.setGameState(new EndGame(game,currentPlayer));
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
	 * the method manages the players turn if the next player doesn't exist
	 * create the next gamestate
	 */
	@Override
	public void nextPlayer() {
		
		for (Player player : game.getPlayers()) {
			if (player.getPlayerNumber() == currentPlayer.getPlayerNumber() + 1) {
				currentPlayer = player;
				playerTurnSetup(currentPlayer);
				return;
			}
		}
		game.setGameState(new MarketSellLap(game));
	}

	/**
	 * return the current player
	 */
	@Override
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	/**
	 * this method notify to the observers the changes of the game
	 * and if the current player has no actions to perfom call nextPlayer
	 * if the current player has finished the emporiums, according
	 * to the rules of the game, this method call the end game phase.
	 */
	@Override
	public void update() {
		game.notifyObservers(new GameBoardChange(game.getGameBoard(),game.getPlayers(),game.getUUID(), null));
		
		for (Player player : game.getPlayers()){
			game.notifyObservers(new PlayerChange(player, player.getUUID()));
		}
		
		if (currentPlayer.getPossibleActions().isEmpty()) {
			if (!currentPlayer.getEmporiums().isEmpty())
				nextPlayer();
			else {
				currentPlayer.movePointsDisk(3);
				game.setGameState(new EndGame(game, currentPlayer));
			}
		}
	}
	/**
	 * 
	 * @return true if there is at least a player online
	 */
	public boolean checkPlayersNotAllOffline(){
		for(Player player: game.getPlayers()){
			if (player.isOnline()) 
				return true;
		}
		return false;
	}
	
}