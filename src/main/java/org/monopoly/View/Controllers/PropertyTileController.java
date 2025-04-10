package org.monopoly.View.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.monopoly.Model.Players.Token;

import java.util.ArrayList;

/**
 * Controller for the property tile.
 * This class handles the display of tokens on the property tile.
 * @author walshj05
 */
public class PropertyTileController implements TileController {

    @FXML
    private AnchorPane tile;
    @FXML
    private ImageView token1;
    @FXML
    private ImageView token2;
    @FXML
    private ImageView token3;
    @FXML
    private ImageView token4;
    @FXML
    private ImageView houseToken;


    /**
     * Updates the tokens to display on the tile
     * @param tokens The tokens to display on the tile.
     * @author walshj05
     */
    @Override
    public void updateTokens(ArrayList<Token> tokens) {
        if (tokens.size() > 4) { // assuming max 4 tokens on a tile
            return;
        }
    }

    /**
     * Rotates the tile by the specified degrees.
     * @param degrees The degrees to rotate the tile.
     * @author walshj05
     */
    public void rotatePane(int degrees){
        tile.setRotate(tile.getRotate() + degrees);
        if (degrees == 270 || degrees == 90) {
            tile.translateXProperty().set(20);
        }
    }
}
