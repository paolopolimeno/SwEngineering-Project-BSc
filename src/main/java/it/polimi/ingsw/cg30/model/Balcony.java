package it.polimi.ingsw.cg30.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Balcony implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8468919088775025625L;

	private transient GameBoard gameBoard;
	private Queue<Counselor> counselors;
	private Region region;

	/**
	 * Balcony is represented by an ArrayBlockingQueue Iterator on the queue
	 * returns elements from head to tail
	 * 
	 * @param gameBoard
	 *            which contains the balcony
	 * @param region
	 *            which contains the balcony
	 * 
	 * @throws NullPointerException
	 *             if the region or the gameboard are null
	 */
	public Balcony(GameBoard gameBoard, Region region) {

		if (gameBoard == null || region == null)
			throw new NullPointerException();

		this.region = region;
		this.gameBoard = gameBoard;
		counselors = new ArrayBlockingQueue<>(4);

	}

	/**
	 * this constructor is just for the king balcony
	 * 
	 * @param gameBoard
	 *            which contains the balcony
	 * @throws NullPointerException
	 *             if the gameboard is null
	 */
	public Balcony(GameBoard gameBoard) {

		if (gameBoard == null)
			throw new NullPointerException();

		this.gameBoard = gameBoard;
		counselors = new ArrayBlockingQueue<>(4);
	}

	/**
	 * initial fill of the balcony one counselor at a time
	 * 
	 * @param counselor
	 *            to add
	 * @throws NullPointerException
	 *             if the counselor is null
	 */
	public void initFillBalcony(Counselor counselor) {

		if (counselor == null)
			throw new NullPointerException();
		counselors.add(counselor);
	}

	/**
	 * a counselor is added as tail of the queue. the head is given to
	 * gameboard.
	 * 
	 * @param counselor
	 *            to add
	 * @throws NullPointerException
	 *             if the counselor is null
	 */
	public void addCounselor(Counselor counselor) {

		if (counselor == null)
			throw new NullPointerException();

		this.gameBoard.getCounselors().remove(counselor);
		Counselor head = this.getCounselors().peek();
		this.getCounselors().remove();
		this.getCounselors().add(counselor);
		gameBoard.getCounselors().add(head);
	}

	/**
	 * 
	 * 
	 * @param cards
	 *            chosen by the player
	 * @return true if all cards match a counselor
	 * 
	 */
	public boolean matchBalconyWithCards(List<PoliticCard> cards) {

		ArrayList<Counselor> copiedCounselors = new ArrayList<>(counselors);
		ArrayList<PoliticCard> copiedCards = new ArrayList<>(cards);

		if (cards.size() > 4)
			return false;

		copiedCards.removeIf(c -> ("JOLLY").equals(c.getColor()));

		for (Counselor counselor : copiedCounselors) {
			for (PoliticCard card : copiedCards) {
				if (card.getColor().equals(counselor.getColor())) {
					copiedCards.remove(card);
					break;
				}
			}
		}
		return copiedCards.isEmpty();
	}

	/**
	 * determines price to pay based on the number of cards passed
	 * and how many of them are jolly
	 * @param cards
	 *            chosen by the player
	 * @return price to pay
	 * 
	 */
	public int councilorsPrice(List<PoliticCard> cards) {
		int numberOfJolly = 0;
		int price = 0;

		for (PoliticCard card : cards) {
			if (("JOLLY").equals(card.getColor()))
				numberOfJolly++;
		}

		if (cards.size() == 4)
			price = 0;
		else if (cards.size() == 3)
			price = 4;
		else if (cards.size() == 2)
			price = 7;
		else if (cards.size() == 1)
			price = 10;

		return price + numberOfJolly;
	}

	/**
	 * @return the counselors
	 */
	public Queue<Counselor> getCounselors() {
		return counselors;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}
}