package org.monopoly.Model;

import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.Players.Token;

import java.util.ArrayList;

/**
 * Represents the game logic and state for a game of Monopoly
 * @author walshj05
 * Modified by: crevelings (4/1/25)
 */
public class Game {
    private GameBoard gameBoard;
    private TurnManager turnManager;

    /**
     * Constructor for the Game class
     * @param numHumanPlayers Number of human players in the game
     * @param playerTokens Array of Tokens for the players
     * @author walshj05
     */
    public Game(int numHumanPlayers, Token[] playerTokens) {
        if (numHumanPlayers < 2 || numHumanPlayers > 4) {
            throw new IllegalArgumentException("Invalid number of players");
        }
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numHumanPlayers; i++) {
            players.add(new HumanPlayer("Player" + (i + 1), playerTokens[i]));
        }

        this.gameBoard = GameBoard.getInstance();
        this.turnManager = new TurnManager(numHumanPlayers, players, this, gameBoard); // todo implement players rolling for order
    }
}
