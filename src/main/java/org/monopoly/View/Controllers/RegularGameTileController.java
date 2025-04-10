package org.monopoly.View.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.monopoly.Model.Players.Token;
import org.monopoly.monopolygameproject.HelloApplication;

import java.util.ArrayList;

/**
 * Controller for the regular game tile.
 * This class handles the display of tokens on the tile.
 * @author walshj05
 */
public class RegularGameTileController implements TileController {
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

    /**
     * Initializes the tile with default settings.
     * @author walshj05
     */
    public void initialize() {
        // Initialize the tile if needed
        // For example, set a default image or style
        tile.setVisible(true);
    }
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
     */
    public void rotatePane(int degrees){
        tile.setRotate(tile.getRotate() + degrees);
    }
}
