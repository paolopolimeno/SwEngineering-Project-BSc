package it.polimi.ingsw.cg30.view.gui;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg30.model.City;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CityLayout extends AnchorPane {

	private ImageView kingImg = new ImageView("file:src/main/resources/images/king.png");
	private ArrayList<BonusLayout> bonuses;
	private ImageView cityImg;
	private Label cityName;
	private Rectangle emporium;
	private int playerNumberCity;

	/**
	 * this constructor creates a new city
	 * @param image
	 * @param city
	 * @param rootPane
	 * @param playerNumber
	 * @param bonus
	 */
	public CityLayout(Image image, City city, Pane rootPane, int playerNumber, List<Bonus> bonus) {

		this.setPrefHeight(rootPane.getPrefHeight() * 0.17);
		this.setPrefWidth(rootPane.getPrefWidth() * 0.17);

		this.playerNumberCity = playerNumber;

		this.cityImg = new ImageView(image);
		this.cityImg.setPreserveRatio(true);

		this.cityImg.setLayoutX(0);
		this.cityImg.setLayoutY(0);

		this.cityImg.setFitHeight(this.getPrefHeight() * 0.8);
		this.cityImg.setFitWidth(this.getPrefWidth() * 0.8);

		this.getChildren().add(cityImg);

		if(playerNumber == 2)
			playerNumberCity++;
		
		double j = 0;

		for (int i = 0; i < playerNumberCity; i++) {

			this.emporium = new Rectangle();

			if (city.getEmporiumsSpace().size()>i) {
					
					if (("YELLOW").equalsIgnoreCase(city.getEmporiumsSpace().get(i).getColor())) {
						this.emporium.setFill(Color.YELLOW);
					}
					if (("GREEN").equalsIgnoreCase(city.getEmporiumsSpace().get(i).getColor())) {
						this.emporium.setFill(Color.GREEN);
					}
					if (("RED").equalsIgnoreCase(city.getEmporiumsSpace().get(i).getColor())) {
						this.emporium.setFill(Color.RED);
					}
					if (("BLUE").equalsIgnoreCase(city.getEmporiumsSpace().get(i).getColor())) {
						this.emporium.setFill(Color.BLUE);
					}
			} 
			else {
				this.emporium.setFill(Color.TRANSPARENT);
			}
	
			this.emporium.setStroke(Color.BLACK);
			this.emporium.setHeight(this.getPrefHeight() * 0.1);
			this.emporium.setWidth(this.getPrefWidth() * 0.1);

			this.emporium.setLayoutY(cityImg.getLayoutY() + cityImg.getFitHeight());
			this.emporium.setLayoutX(cityImg.getLayoutX() + j);

			this.getChildren().add(emporium);

			j = j + this.emporium.getWidth();
		}

		this.cityName = new Label(city.getName());
		this.cityName.setLayoutY(cityImg.getLayoutY() + cityImg.getFitHeight() + emporium.getHeight());
		this.cityName.setLayoutX(cityImg.getLayoutX());
		
		this.getChildren().add(cityName);

		bonuses = new ArrayList<>();

		if (bonus.size() == 1) {

			bonuses.add(new BonusLayout(bonus.get(0), rootPane));

			bonuses.get(0).setLayoutX(this.getPrefWidth() * 0.25);
			bonuses.get(0).setLayoutY(0);

			this.getChildren().add(bonuses.get(0));

		} else if (bonus.size() == 2) {

			bonuses.add(new BonusLayout(bonus.get(0), rootPane));

			bonuses.get(0).setLayoutX(this.getPrefWidth() * 0.15);
			bonuses.get(0).setLayoutY(0);

			this.getChildren().add(bonuses.get(0));

			bonuses.add(new BonusLayout(bonus.get(1), rootPane));

			bonuses.get(1).setLayoutX(bonuses.get(1).getLayoutX() + bonuses.get(1).getPrefWidth());
			bonuses.get(1).setLayoutY(0);

			this.getChildren().add(bonuses.get(1));
		}
	}

	/**
	 * @return the cityImg
	 */
	public ImageView getCityImg() {
		return cityImg;
	}

	/**
	 * this method adds the king to the city
	 */
	public void addKing() {

		kingImg.setFitHeight(this.getPrefHeight() * 0.35);
		kingImg.setPreserveRatio(true);

		kingImg.setEffect(new Glow(0.7));

		kingImg.setLayoutX(this.getPrefWidth() * 0.15);
		kingImg.setLayoutY(this.getPrefHeight() * 0.4);

		this.getChildren().add(kingImg);
	}

	/**
	 * @param cityImg
	 *            the cityImg to set
	 */
	public void setCityImg(ImageView cityImg) {
		this.cityImg = cityImg;
	}

	/**
	 * @return the cityName
	 */
	public Label getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(Label cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the playerNumber
	 */
	public int getPlayerNumber() {
		return playerNumberCity;
	}

	/**
	 * @param playerNumber
	 *            the playerNumber to set
	 */
	public void setPlayerNumber(int playerNumber) {
		this.playerNumberCity = playerNumber;
	}

}
