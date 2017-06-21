package it.polimi.ingsw.cg30.view.gui;

import java.util.ArrayList;

import it.polimi.ingsw.cg30.model.PermissionCard;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PermissionCardLayout extends Pane {

	private Label cities;
	private ArrayList<BonusLayout> bonus;
	private String letters = " ";
	private ImageView card = new ImageView("file:src/main/resources/images/tile.png");

	/**
	 * this constructor creates a permission card
	 * @param permissionCard
	 * @param rootPane
	 */
	public PermissionCardLayout(PermissionCard permissionCard, Pane rootPane) {

		if (permissionCard.getUsed())
			this.setOpacity(0.5);

		this.setPrefHeight(rootPane.getPrefHeight() * 0.1);
		this.setPrefWidth(rootPane.getPrefWidth() * 0.065);

		card.setFitHeight(this.getPrefHeight());
		card.setFitWidth(this.getPrefWidth());

		card.setLayoutX(0);
		card.setLayoutY(0);

		this.getChildren().add(card);

		cities = new Label();

		for (int i = 0; i < permissionCard.getLetters().size(); i++) {

			letters = letters + permissionCard.getLetters().get(i).toUpperCase() + " ";
		}

		cities.setText(letters);

		cities.setLayoutX(card.getFitWidth() * 0.02);
		cities.setLayoutY(card.getFitHeight() * 0.05);

		this.getChildren().add(cities);

		bonus = new ArrayList<>();

		if (permissionCard.getBonuses().size() == 1) {

			bonus.add(new BonusLayout(permissionCard.getBonuses().get(0), rootPane));

			bonus.get(0).setLayoutX(card.getFitWidth() * 0.15);
			bonus.get(0).setLayoutY(card.getFitHeight() * 0.35);

			this.getChildren().add(bonus.get(0));

		} else if (permissionCard.getBonuses().size() == 2) {

			bonus.add(new BonusLayout(permissionCard.getBonuses().get(0), rootPane));

			bonus.get(0).setLayoutX(0);
			bonus.get(0).setLayoutY(card.getFitHeight() * 0.4);
			bonus.get(0).setPrefHeight(card.getFitHeight() * 0.02);
			bonus.get(0).setPrefWidth(card.getFitWidth() * 0.02);

			this.getChildren().add(bonus.get(0));

			bonus.add(new BonusLayout(permissionCard.getBonuses().get(1), rootPane));

			bonus.get(1).setLayoutX(card.getFitWidth() * 0.4);
			bonus.get(1).setLayoutY(card.getFitHeight() * 0.4);
			bonus.get(1).setPrefHeight(card.getFitHeight() * 0.02);
			bonus.get(1).setPrefWidth(card.getFitWidth() * 0.02);

			this.getChildren().add(bonus.get(1));

		}

	}

}
