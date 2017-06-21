package it.polimi.ingsw.cg30.view.gui;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class IndicatorDiskLayout extends AnchorPane {

	private Circle disk;

	/**
	 * this method creates an indicator disk
	 * @param color
	 * @param rootPane
	 */
	public IndicatorDiskLayout(String color, Pane rootPane) {

		this.setPrefHeight(rootPane.getPrefHeight() * 0.01);
		this.setPrefWidth(rootPane.getPrefWidth() * 0.01);

		disk = new Circle();
		
		if(("YELLOW").equals(color)){
			disk.setFill(Color.YELLOW);
		}
		if(("GREEN").equals(color)){
			disk.setFill(Color.GREEN);
		}
		if(("BLUE").equals(color)){
			disk.setFill(Color.BLUE);
		}
		if(("RED").equals(color)){
			disk.setFill(Color.RED);
		}
		disk.setRadius(this.getPrefHeight());

		this.getChildren().add(disk);
	}

	/**
	 * this method moves the points disk in the given position 4:3
	 * @param position
	 */
	public void movePointsDisk(int position) {

		double sizeOfACell = (4.65) * disk.getRadius();

		if (position > 27 && position < 50) {

			disk.setLayoutX(sizeOfACell * 27 + disk.getLayoutX());
			disk.setLayoutY(disk.getLayoutY() + sizeOfACell * (position - 27));

		} else if (position > 50 && position < 77) {

			disk.setLayoutY(disk.getLayoutY() + sizeOfACell * 23);
			disk.setLayoutX(disk.getLayoutX() + sizeOfACell * 27 - sizeOfACell * (position - 50));

		} else if (position > 77 && position < 100) {

			disk.setLayoutY(disk.getLayoutY() + sizeOfACell * 23 - sizeOfACell * (position - 77));
			disk.setLayoutX(disk.getLayoutX());
		} else {

			disk.setLayoutX(sizeOfACell * position + disk.getLayoutX());
		}
	}
	
	/**
	 * this method moves the points disk in the given position 16:9
	 * @param position
	 */
	public void movePointsDiskPc(int position) {

		double sizeOfACellHorizontal = (6.22) * disk.getRadius();
		double sizeOfACellVertical = (4.7)*disk.getRadius();

		if (position > 27 && position < 50) {

			disk.setLayoutX(sizeOfACellHorizontal * 27 + disk.getLayoutX());
			disk.setLayoutY(disk.getLayoutY() + sizeOfACellVertical * (position - 27));

		} else if (position > 50 && position < 77) {

			disk.setLayoutY(disk.getLayoutY() + sizeOfACellVertical * 23);
			disk.setLayoutX(disk.getLayoutX() + sizeOfACellHorizontal * 27 - sizeOfACellHorizontal * (position - 50));

		} else if (position > 77 && position < 100) {

			disk.setLayoutY(disk.getLayoutY() + sizeOfACellVertical * 23 - sizeOfACellVertical * (position - 77));
			disk.setLayoutX(disk.getLayoutX());
		} else {

			disk.setLayoutX(sizeOfACellHorizontal * position + disk.getLayoutX());
		}
	}

	/**
	 * this method moves the money disk in the given position 4:3
	 * @param position
	 */
	public void moveMoneyDisk(int position) {

		double sizeOfACell = (4.6) * disk.getRadius();
		disk.setLayoutX(sizeOfACell * position + disk.getLayoutX());
	}
	
	/**
	 * this method moves the money disk in the given position 16:9
	 * @param position
	 */
	public void moveMoneyDiskPc(int position) {

		double sizeOfACell = (6.07) * disk.getRadius();
		disk.setLayoutX(sizeOfACell * position + disk.getLayoutX());
	}

	/**
	 * this method moves the nobility disk in the given position 4:3
	 * @param position
	 */
	public void moveNobilityDisk(int position) {

		double sizeOfACell = (4.4) * disk.getRadius();
		disk.setLayoutX(sizeOfACell * position + disk.getLayoutX());
	}
	
	/**
	 * this method moves the nobility disk in the given position 16:9
	 * @param position
	 */
	public void moveNobilityDiskPc(int position) {

		double sizeOfACell = (5.85) * disk.getRadius();
		disk.setLayoutX(sizeOfACell * position + disk.getLayoutX());
	}
}