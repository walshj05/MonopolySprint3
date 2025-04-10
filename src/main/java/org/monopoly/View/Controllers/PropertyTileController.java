package org.monopoly.View.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.monopoly.Model.Players.Token;

import java.util.ArrayList;

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

    @Override
    public void updateTokens(ArrayList<Token> tokens) {
        if (tokens.size() > 4) { // assuming max 4 tokens on a tile
            return;
        }
        // Clear existing tokens
        token1.setImage(null);
        token2.setImage(null);
        token3.setImage(null);
        token4.setImage(null);
        // Set new tokens based on length of tokens
//        if (tokens.size() == 1) {
//            token1.setImage(new Image(String.valueOf(HelloApplication.class.getResource(tokens.getFirst().getIcon()))));
//        }
//        if (tokens.size() > 1) {
//            token2.setImage(new Image(String.valueOf(HelloApplication.class.getResource(tokens.get(1).getIcon()))));
//
//        if (tokens.size() > 2) {
//            token3.setImage(new Image(String.valueOf(HelloApplication.class.getResource(tokens.get(2).getIcon()))));
//        }
//        if (tokens.size() > 3) {
//            token4.setImage(new Image(String.valueOf(HelloApplication.class.getResource(tokens.get(3).getIcon()))));
//        }
    }
    public void rotatePane(int degrees){
        tile.setRotate(tile.getRotate() + degrees);
    }
}
