package org.monopoly.View;

import javafx.stage.Stage;
import org.monopoly.Model.Players.ComputerPlayer;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.Players.Token;

import java.io.IOException;
import java.util.ArrayList;

/**
 * GUI class for the Monopoly game.
 * This class is responsible for creating and managing the GUI scenes
 * as well as their respective components.
 * @author walshj05
 */
public class GUI {
    private Stage stage;
    private GameScene gameScene;
    private static GUI instance;

    /**
     * Returns the singleton instance of the GUI class.
     * @return The GUI instance, or null if it hasn't been created yet.
     */
    public static GUI getInstance() {
        return instance; // returns null if the GUI hasn't been made yet
    }

    /**
     * Constructor for the GUI class.
     * Initializes the GUI with a given stage and sets up the game scene.
     * @param stage The stage to be used for the GUI.
     * @throws IOException If an error occurs while loading the FXML files.
     */
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


    /**
     * Sets the game scene for the GUI.
     * This method creates a new GameScene instance and sets it as the current scene.
     * @param humanPlayers The list of human players in the game.
     * @param computerPlayers The list of computer players in the game.
     * @throws IOException If an error occurs while loading the FXML files.
     */
    public void setGameScene(ArrayList<Player> humanPlayers, ArrayList<Player> computerPlayers) throws IOException {
        // Create a new game scene and set it as the current scene
        this.gameScene = new GameScene(humanPlayers, computerPlayers);
        stage.setScene(gameScene.getScene());
        stage.centerOnScreen();
        stage.show();
    }
}
