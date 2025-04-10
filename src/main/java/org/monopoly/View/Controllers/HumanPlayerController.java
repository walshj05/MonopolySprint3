package org.monopoly.View.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.monopoly.Model.Players.Player;

/**
 * Controller for the human player interface.
 * This class handles the player's actions and updates the UI accordingly.
 * @author walshj05
 */
public class HumanPlayerController {
    @FXML
    public Button mortProp;
    @FXML
    public Button getOutOfJail;
    @FXML
    public Button trade;
    @FXML
    public Button auction;
    @FXML
    public Button drawCard;
    @FXML
    private AnchorPane playerPane;
    @FXML
    private Button rollDice;
    @FXML
    private Button sell;
    @FXML
    private Button buyProp;
    @FXML
    private Button buyHouse;
    @FXML
    private Button buyHotel;
    @FXML
    private Button endTurn;
    @FXML
    private Label name;
    @FXML
    private ImageView token;
    @FXML
    private Label money;
    @FXML
    private VBox properties;
    private Player player;

    /**
     * Initializes the player interface with the given player.
     * @param player The player to initialize the interface for.
     * @author walshj05
     */
    public void setPlayer(Player player) {
        this.player = player;
        name.setText(player.getName());
        money.setText("Balance: $" + player.getBalance());
    }

    /**
     * Sets the token image for the player.
     * @author walshj05
     */
    public void updateProperties(){}

    /**
     * Updates the players information
     * @author walshj05
     */
    public void updatePlayerInfo(){
        money.setText("Balance: $" + player.getBalance());

    }
}
