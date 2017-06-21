package it.polimi.ingsw.cg30.model;

import java.util.ArrayList;

import java.util.Random;

import it.polimi.ingsw.cg30.model.actions.market.BuyAction;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;

public class MarketBuyLap implements GameState {

	private Game game;
	private Player currentPlayer;
	private ArrayList<Player> players;

	/**
	 * the constructor set the game state as a buy market lap
	 * @param game
	 */
	public MarketBuyLap(Game game) {
		this.game = game;
		game.setGameState(this);
		run();
	}

	/**
	 * this method run a new buy market lap
	 */
	@Override
	public void run() {
		players = new ArrayList<>(game.getPlayers());

		int rnd = new Random().nextInt(players.size());
		playerTurnSetup(game.getPlayers().get(rnd));
	}

	/**
	 * set the passed player as currentPlayer and reset timer, if he is online.
	 * If offline calls nextplayer.
	 * 
	 * @param currentPlayer
	 */
	public void playerTurnSetup(Player currentPlayer) {

		this.currentPlayer = currentPlayer;
		if (!currentPlayer.isOnline()) {
			players.remove(currentPlayer);
			nextPlayer();
		} else {
			currentPlayer.getPossibleActions().add(new BuyAction());
			game.notifyObservers(new PlayerChange(currentPlayer, currentPlayer.getUUID()));
			players.remove(currentPlayer);
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
	 * chooses next player and pass it to playerTurnSetup
	 */
	@Override
	public void nextPlayer() {
		if (!players.isEmpty()) {
			int rnd = new Random().nextInt(players.size());
			Player tmp = players.get(rnd);

			for (Player player : game.getPlayers()) {
				if (player.equals(tmp)) {
					currentPlayer = player;
					playerTurnSetup(currentPlayer);
					return;
				}
			}
		} else {
			game.getGameBoard().getAllThingsForSale().clear();
			game.setGameState(new TurnLap(game));
		}
	}

	/**
	 * calls game.notifyObservers in order to send changes. calls nextPlayer
	 */
	@Override
	public void update() {
		game.notifyObservers(new GameBoardChange(game.getGameBoard(), game.getPlayers(), game.getUUID(), null));

		for (Player player : game.getPlayers()) {
			game.notifyObservers(new PlayerChange(player, player.getUUID()));
		}

		if (currentPlayer.getPossibleActions().isEmpty()) {
			nextPlayer();
		}
	}
}