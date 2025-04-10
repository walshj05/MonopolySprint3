package org.monopoly.View;

import javafx.stage.Stage;
import org.monopoly.Model.Players.ComputerPlayer;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.Players.Token;

import java.io.IOException;
import java.util.ArrayList;

public class GUI {
    private Stage stage;
    private GameScene gameScene;
    private static GUI instance;

    public static GUI getInstance() {
        return instance; // returns null if the GUI hasn't been made yet
    }

    public GUI(Stage stage) throws IOException {
        instance = this;
        this.stage = stage;
        ArrayList<Player> humanPlayers = new ArrayList<>();
        humanPlayers.add(new HumanPlayer("Player 1", new Token("Player 1", "Iron.png")));
        humanPlayers.add(new HumanPlayer("Player 2", new Token("Player 1", "BattleShip.png")));
        ArrayList<Player> computerPlayers = new ArrayList<>();
        computerPlayers.add(new ComputerPlayer("Computer 1", new Token("Computer 1", "TopHat.png")));
        computerPlayers.add(new ComputerPlayer("Computer 2", new Token("Computer 2", "Boot.png")));
        setGameScene(humanPlayers, computerPlayers);
    }


    public void setGameScene(ArrayList<Player> humanPlayers, ArrayList<Player> computerPlayers) throws IOException {
        // Create a new game scene and set it as the current scene
        this.gameScene = new GameScene(humanPlayers, computerPlayers);
        stage.setScene(gameScene.getScene());
        stage.centerOnScreen();
        stage.show();
    }
}
