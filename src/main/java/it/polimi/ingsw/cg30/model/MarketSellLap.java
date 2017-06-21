package it.polimi.ingsw.cg30.model;


import it.polimi.ingsw.cg30.model.actions.market.SellAction;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;

public class MarketSellLap implements GameState {

	private Game game;
	private Player currentPlayer;

	/**
	 * the constructor set the game state as a sell market lap
	 * @param game
	 */
	public MarketSellLap(Game game) {
		this.game = game;
		game.setGameState(this);
		run();
	}

	/**
	 * this method run a new sell market lap
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
	 * set the passed player as currentPlayer and 
	 * reset timer, if he is online.
	 * If offline calls nextplayer.
	 * @param currentPlayer
	 */
	public void playerTurnSetup(Player currentPlayer) {

		this.currentPlayer = currentPlayer;
		if (!currentPlayer.isOnline()){
		
			nextPlayer();
		}
		else {
			currentPlayer.getPossibleActions().add(new SellAction());
			game.notifyObservers(new PlayerChange(currentPlayer, currentPlayer.getUUID()));
			game.resetTimer();
		}
	}

	/**
	 * @return currentPlayer
	 */
	@Override
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * chooses next player and pass it 
	 * to
	 * playerTurnSetup
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
		game.setGameState(new MarketBuyLap(game));
	}
	/**
	 * calls game.notifyObservers
	 * in order to send changes.
	 * calls nextPlayer
	 */
	@Override
	public void update() {
		game.notifyObservers(new GameBoardChange(game.getGameBoard(),game.getPlayers(),game.getUUID(), null));
		
		for (Player player : game.getPlayers()){
			game.notifyObservers(new PlayerChange(player, player.getUUID()));
		}
		
		if (currentPlayer.getPossibleActions().isEmpty()) {
			nextPlayer();
		}
	}
}