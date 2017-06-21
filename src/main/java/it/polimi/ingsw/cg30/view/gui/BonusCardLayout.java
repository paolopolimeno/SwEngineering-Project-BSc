package it.polimi.ingsw.cg30.view.gui;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class BonusCardLayout extends AnchorPane {
	
	private Label numberOfBonus;
	private ImageView card;
	
	/**
	 * this constructor creates a new bonus card
	 * @param numberOfBonus
	 * @param rootPane
	 */
	public BonusCardLayout(int numberOfBonus, Pane rootPane){
		
		this.setPrefHeight(rootPane.getPrefHeight()*0.06);
		this.setPrefWidth(rootPane.getPrefWidth()*0.1);
		
		card = new ImageView("file:src/main/resources/images/regionTile.png");
		
		card.setFitHeight(this.getPrefHeight());
		card.setFitWidth(this.getPrefWidth());		

		card.setLayoutX(0);
		card.setLayoutY(0);

		this.getChildren().add(card);
		
		this.numberOfBonus = new Label();
		this.numberOfBonus.setText(String.valueOf(numberOfBonus));
		
		this.numberOfBonus.setLayoutX(card.getFitWidth()*0.3);
		this.numberOfBonus.setLayoutY(card.getFitHeight()*0.2);
		
		this.getChildren().add(this.numberOfBonus);
	}
	

}
