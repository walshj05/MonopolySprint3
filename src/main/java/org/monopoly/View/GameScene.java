package org.monopoly.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.Players.Token;
import org.monopoly.View.Controllers.BoardController;
import org.monopoly.View.Controllers.ComputerPlayerController;
import org.monopoly.View.Controllers.HumanPlayerController;
import org.monopoly.monopolygameproject.HelloApplication;

import java.io.IOException;
import java.util.ArrayList;

public class GameScene {
    private final Scene scene;
    private BoardController boardController;
    private static GameScene instance;
    public static GameScene getInstance() {
        return  instance; // returns null if the GUI hasn't been made yet
    }
    public GameScene(ArrayList<Player> humanPlayers, ArrayList<Player> computerPlayers) throws IOException {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(1190, 740);
        instance = this;

        // Load the board
        FXMLLoader boardLoader = new FXMLLoader(HelloApplication.class.getResource("BoardPane.fxml"));
        Parent boardRoot = boardLoader.load();
        this.boardController = boardLoader.getController();
        root.getChildren().add(boardRoot);
        for (Player player : humanPlayers) {
            player.move(0);
        }
        for (Player player : computerPlayers) {
            player.move(0);
        }
        initializePlayerInterfaces(humanPlayers, computerPlayers, root);
        this.scene = new Scene(root, 1190, 740);
    }

    private void initializePlayerInterfaces(ArrayList<Player> humanPlayers, ArrayList<Player> computerPlayers, AnchorPane root) throws IOException {
        // Initialize the board with players
        int yStart = 0;
        for (Player player : humanPlayers) {
            FXMLLoader playerLoader = new FXMLLoader(HelloApplication.class.getResource("HumanPlayerInterface.fxml"));
            Parent playerRoot = playerLoader.load();
            HumanPlayerController playerController = playerLoader.getController();
            // todo add controller to players observer list
            playerController.setPlayer(player);
            playerRoot.setLayoutY(yStart);
            playerRoot.setLayoutX(740);
            yStart += 185;
            root.getChildren().add(playerRoot);
        }

        for (Player player : computerPlayers) {
            FXMLLoader playerLoader = new FXMLLoader(HelloApplication.class.getResource("ComputerPlayerInterface.fxml"));
            Parent playerRoot = playerLoader.load();
            ComputerPlayerController playerController = playerLoader.getController();
            // todo add controller to players observer list
            playerController.setPlayer(player);
            playerRoot.setLayoutY(yStart);
            playerRoot.setLayoutX(740);
            root.getChildren().add(playerRoot);
            yStart += 185;
        }
    }


    public void updateTokens(ArrayList<Token> tokens, int tileIndex) {
        boardController.updateTokens(tokens, tileIndex);
    }
    public Scene getScene() {
        return scene;
    }
}
