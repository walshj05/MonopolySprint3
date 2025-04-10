package org.monopoly.View.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.monopoly.Model.Players.Token;
import org.monopoly.monopolygameproject.HelloApplication;

import java.util.ArrayList;

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

    public void initialize() {
        // Initialize the tile if needed
        // For example, set a default image or style
        tile.setVisible(true);
    }
    @Override
    public void updateTokens(ArrayList<Token> tokens) {
        if (tokens.size() > 4) { // assuming max 4 tokens on a tile
            return;
        }
        // Clear existing tokens
//        token1.setImage(null);
//        token2.setImage(null);
//        token3.setImage(null);
//        token4.setImage(null);
        // Set new tokens based on length of tokens
//        if (tokens.size() == 1) {
//            Image image = new Image(String.valueOf(HelloApplication.class.getResource("/" + tokens.getFirst().getIcon())));
//            token4.setImage(image);
//        }
//        if (tokens.size() > 1) {
//            Image image = new Image(String.valueOf(HelloApplication.class.getResource("/" + tokens.get(1).getIcon())));
//            token2.setImage(image);
//        }
//        if (tokens.size() > 2) {
//            Image image = new Image(String.valueOf(HelloApplication.class.getResource("/" + tokens.get(2).getIcon())));
//            token3.setImage(image);
//        }
//        if (tokens.size() > 3) {
//            Image image = new Image(String.valueOf(HelloApplication.class.getResource("/" + tokens.get(3).getIcon())));
//            token4.setImage(image);
//        }
        System.out.println(token4.getImage().getUrl());
    }

    public void rotatePane(int degrees){
        tile.setRotate(tile.getRotate() + degrees);
    }
}
