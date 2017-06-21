package it.polimi.ingsw.cg30.view.gui;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class EdgeLayout extends Group {

    private AnchorPane source;
    private AnchorPane target;
    private Line line;

    /**
     * this constructor creates a edge 
     * @param anchorPane city source
     * @param anchorPane2 city target
     */
    public EdgeLayout(AnchorPane anchorPane, AnchorPane anchorPane2) {

        this.source = anchorPane;
        this.target = anchorPane2;

        line = new Line();

        line.startXProperty().bind(anchorPane.layoutXProperty().add(anchorPane.getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind(anchorPane.layoutYProperty().add(anchorPane.getBoundsInParent().getHeight() / 2.0));

        line.endXProperty().bind(anchorPane2.layoutXProperty().add( anchorPane2.getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind(anchorPane2.layoutYProperty().add( anchorPane2.getBoundsInParent().getHeight() / 2.0));

        line.setStyle("-fx-stroke: yellow; -fx-border-color: yellow; -fx-border-width: 10");

        getChildren().add(line);
    }

    /**
     * 
     * @return the source
     */
    public AnchorPane getSource() {
        return source;
    }
    /**
     * 
     * @return the target
     */
    public AnchorPane getTarget() {
        return target;
    }
}