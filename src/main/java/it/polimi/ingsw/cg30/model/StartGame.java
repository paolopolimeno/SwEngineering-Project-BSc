 package it.polimi.ingsw.cg30.model;

import java.util.Random;

import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;
import it.polimi.ingsw.cg30.model.util.GameLoader;

public class StartGame implements GameState {
	/**
	 * the game to start
	 */
	private Game game;
	/**
	 * 
	 * @param game
	 * @throws NullPointerException if the game is null
	 */
	public StartGame(Game game) {
		
		if(game == null)
			throw new NullPointerException();
		
		game = GameLoader.run(game);
		game.setGameState(this);
		this.game=game;
	}
	/**
	 * run the game notifying the view and start the first turn lap
	 * if the number of players is 2 call twoPlayerinit() method
	 */
	@Override
	public void run() {
		for (Player player : game.getPlayers()) {
			player.init();
		}
		if (game.getPlayers().size() == 2){
			twoPlayersInit();
		}
		game.notifyObservers(new GameBoardChange(game.getGameBoard(),game.getPlayers(),game.getUUID(),null));
		for (Player player : game.getPlayers()){
			game.notifyObservers(new PlayerChange(player, player.getUUID()));
		}
		game.setGameState(new TurnLap(game));
	}
	/**
	 * this method according to the rules of the game,
	 * pick a permission card for each region in the game
	 * and build an emporium of an unused player color in
	 * all the city of the permission card picked.
	 * The permission card selected is a random card from the
	 * two initially showed up.
	 * At the end of the method cards are shuffled again.
	 */
	private void twoPlayersInit(){
		for (Region region : game.getGameBoard().getRegions()){
			//genero a caso un numero tra 0 e 1 e pesco la carta corrispondente
			int rnd = new Random().nextInt(game.getPlayers().size());
			PermissionCard card = region.getShowedPermissionCards().get(rnd);
			//per tutte le lettere costruisco
			for (String s : card.getLetters()){
				for (City city : region.getCityList()){
					if (city.getInitLetter().equals(s)){
						city.buildEmporium(new Emporium(game.getGameBoard().getColors().get(3), 3));
					}
				}
			}
			//rimescolo la carta nel mazzo
			region.changePermissionCards();
		}
	}
	/**
	 * return the current player
	 * in this phase of the game the current player
	 * doesn't exist so this method returns null
	 */
	@Override
	public Player getCurrentPlayer() {
		return null;
	}
	/**
	 * next player method void in this state
	 */
	@Override
	public void nextPlayer() {
	}
	/**
	 * the update method void in this state
	 */
	@Override
	public void update() {
	}
}