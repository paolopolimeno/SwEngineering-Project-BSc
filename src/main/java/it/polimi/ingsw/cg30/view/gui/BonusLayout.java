package it.polimi.ingsw.cg30.view.gui;

import it.polimi.ingsw.cg30.model.bonus.AssistantBonus;
import it.polimi.ingsw.cg30.model.bonus.Bonus;
import it.polimi.ingsw.cg30.model.bonus.MainActionBonus;
import it.polimi.ingsw.cg30.model.bonus.NobilityBonus;
import it.polimi.ingsw.cg30.model.bonus.PointsBonus;
import it.polimi.ingsw.cg30.model.bonus.PoliticCardBonus;
import it.polimi.ingsw.cg30.model.bonus.ProsperityBonus;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BonusLayout extends AnchorPane {

	private Label numberOfBonus;
	private ImageView bonus;

	private Image assistantBonus = new Image("file:src/main/resources/images/Assistant.png");
	private Image pointsBonus = new Image("file:src/main/resources/images/Point.png");
	private Image moneyBonus = new Image("file:src/main/resources/images/Coins.png");
	private Image cardBonus = new Image("file:src/main/resources/images/back.png");
	private Image mainActionBonus = new Image("file:src/main/resources/images/MainAction.png");
	private Image nobilityBonus = new Image("file:src/main/resources/images/Nobility.png");

	/**
	 * this constructor creates a new bonus
	 * @param bonus
	 * @param rootPane
	 */
	public BonusLayout(Bonus bonus, Pane rootPane) {


		this.setPrefHeight(rootPane.getPrefHeight() * 0.06);
		this.setPrefWidth(rootPane.getPrefWidth() * 0.06);

		numberOfBonus = new Label(String.valueOf(bonus.getNumberOfBonus()));

		if (bonus instanceof AssistantBonus) {

			this.bonus = new ImageView(assistantBonus);
			this.numberOfBonus.setTextFill(Color.WHITE);
			
			numberOfBonus.setLayoutX(this.getPrefWidth() * 0.1);
			numberOfBonus.setLayoutY(this.getPrefHeight() * 0.3);
		}

		if (bonus instanceof PointsBonus) {

			this.bonus = new ImageView(pointsBonus);
			this.numberOfBonus.setTextFill(Color.YELLOW);
			
			numberOfBonus.setLayoutX(this.getPrefWidth() * 0.2);
			numberOfBonus.setLayoutY(this.getPrefHeight() * 0.25);

		}

		if (bonus instanceof ProsperityBonus) {

			this.bonus = new ImageView(moneyBonus);
			this.numberOfBonus.setTextFill(Color.BLACK);
			
			numberOfBonus.setLayoutX(this.getPrefWidth() * 0.28);
			numberOfBonus.setLayoutY(this.getPrefHeight() * 0.3);

		}

		if (bonus instanceof PoliticCardBonus) {

			this.bonus = new ImageView(cardBonus);
			this.numberOfBonus.setTextFill(Color.YELLOW);
			
			numberOfBonus.setLayoutX(this.getPrefWidth() * 0.17);
			numberOfBonus.setLayoutY(this.getPrefHeight() * 0.28);

		}
		if (bonus instanceof MainActionBonus) {

			this.bonus = new ImageView(mainActionBonus);
			this.numberOfBonus.setTextFill(Color.BLACK);
			
			numberOfBonus.setLayoutX(this.getPrefWidth() * 0.25);
			numberOfBonus.setLayoutY(this.getPrefHeight() * 0.3);

		}
		
		if (bonus instanceof NobilityBonus) {

			this.bonus = new ImageView(nobilityBonus);
			this.numberOfBonus.setTextFill(Color.YELLOW);
			
			numberOfBonus.setLayoutX(this.getPrefWidth() * 0.28);
			numberOfBonus.setLayoutY(this.getPrefHeight() * 0.28);
		}

		this.bonus.setPreserveRatio(true);
		this.bonus.setLayoutX(0);
		this.bonus.setLayoutY(0);
		this.bonus.setFitHeight(this.getPrefHeight());
		
		this.getChildren().add(this.bonus);
		this.getChildren().add(numberOfBonus);

	}

}
