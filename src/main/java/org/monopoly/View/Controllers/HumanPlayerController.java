package org.monopoly.View.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.monopoly.Model.Players.Player;

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

    public void setPlayer(Player player) {
        this.player = player;
        name.setText(player.getName());
        money.setText("Balance: $" + player.getBalance());
    }

    public void updateProperties(){}

    public void updatePlayerInfo(){
        money.setText("Balance: $" + player.getBalance());

    }
}
