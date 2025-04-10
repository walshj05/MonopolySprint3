package org.monopoly.View.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.monopoly.Model.Players.Player;

public class ComputerPlayerController {
    @FXML
    private AnchorPane playerPane;
    @FXML
    private Label name;
    @FXML
    private ImageView token;
    @FXML
    private Label balance;
    @FXML
    private VBox properties;
    private Player player;
    public void setPlayer(Player player) {
        this.player = player;
        name.setText(player.getName());
        balance.setText("Balance: $" + player.getBalance());
        // Update properties list if needed
    }
}
