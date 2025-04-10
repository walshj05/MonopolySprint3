package org.monopoly.Model;

import org.monopoly.Model.Players.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the turns of the players in the game.
 * Keeps track of the current player and the order of the players.
 * @author walshj05
 */
public class TurnManager {
    private int currentPlayerIndex;
    private final ArrayList<Player> players;
    private int numPlayers;
    private static TurnManager instance;
    private Game game;
    private GameBoard gameBoard;

    public static TurnManager getInstance() {
        if (instance == null) {
            return new TurnManager(0, new ArrayList<>(), null, null);
        }
        return instance;
    }
    /**
     * Constructor for the TurnManager class.
     * @param numPlayers the number of players in the game
     * @param players the list of players in the game
     * @author walshj05
     */
    public TurnManager(int numPlayers, ArrayList<Player> players, Game game, GameBoard gameBoard) {
        this.numPlayers = numPlayers;
        this.currentPlayerIndex = 0;
        this.players = players;
        this.game = game;
        this.gameBoard = gameBoard;
        instance = this;
    }

    /**
     * Continues to the next player in the turn order.
     * @author walshj05
     */
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
    }

    /**
     * Gets the current player
     * @return the current player
     * @author walshj05
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }


    /**
     * Allows the current player to take their turn
     * @author walshj05
     * Modified by: crevelings (4/1/25), (4/9/25)
     * 4/9/25: Moved from game to TurnManager
     */
    public void playerTakeTurn(){
        Dice dice = Dice.getInstance();
        int doublesNeeded = 0;
        Player currentPlayer = getCurrentPlayer();

        // Check to see if a player is in jail
        if (currentPlayer.isInJail()){
            jailTurnLogic(currentPlayer);
            if (currentPlayer.isInJail()){
                doublesNeeded = 1; // passes the base turn logic
            } else {
                dice.resetNumDoubles(); // allows base turn logic
            }
        }

        // Player takes standard turn
        while (dice.getNumDoubles() == doublesNeeded) {
            currentPlayer.takeTurn(dice);
            int currentPosition = currentPlayer.getPosition();
            gameBoard.executeStrategyType(currentPlayer, "tile");

            // Execute strategy of tile in new position if the player moved due to an action
            while (currentPlayer.getPosition() != currentPosition) {
                if (currentPlayer.isInJail()){ // turn ends if player moves to jail
                    return;
                }
                currentPosition = currentPlayer.getPosition();
                gameBoard.executeStrategyType(currentPlayer, "tile");
            }

            if (dice.isDouble()) {
                dice.incrementNumDoubles();
            }
            doublesNeeded++;

            if (dice.getNumDoubles() == 3) {
                currentPlayer.goToJail();
                break;
            }
        }
        dice.resetNumDoubles();
    }


    /**
     * Logic for a player's turn while in jail
     * @param player Player object
     * @author walshj05
     * Modified by: crevelings (4/9/25)
     * Moved from Game to TurnManager
     */
    public void jailTurnLogic(Player player){
        Dice dice = Dice.getInstance();
        if (player.getJailTurns() == 3){
            player.releaseFromJail();
        } else if (player.hasCard("community:Get Out of Jail Free")){
            player.removeCard("community:Get Out of Jail Free");
            gameBoard.executeStrategyType(player, "community:Get Out of Jail Free");
        } else if (player.hasCard("chance:Get Out of Jail Free.")){
            player.removeCard("chance:Get Out of Jail Free.");
            gameBoard.executeStrategyType(player, "chance:Get Out of Jail Free.");
        } else {
            dice.roll();
            if (dice.isDouble()){
                player.releaseFromJail();
            } else {
                player.incrementJailTurns();
            }
        }
    }

    /**
     * Continues to the next players turn (ends the current players turn)
     * @author walshj05
     * Modified by: crevelings (4/1/25), (4/9/25)
     * 4/9/25: Moved from game to TurnManager
     */
    public void nextPlayersTurn(){
        nextPlayer();
    }

    /**
     * Removes a player from the game.
     * @param player the player to be removed from the game
     * @author walshj05
     */
    public void removePlayer(Player player) {
        players.remove(player);
        numPlayers--;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets the TurnManager object for testing purposes
     * @return TurnManager object
     * @author walshj05
     */
    public TurnManager getTurnManager() {
        return instance;
    }
}
