package org.monopoly.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.Players.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GameTest {
    @Test
    public void testGameCreation() {
        Token[] tokens = {new Token("John Doe","BattleShip.png"), new Token("Jane Doe","Car.png")};
        Game game = new Game(2, tokens);
        assertNotNull(game);
    }

    @Test
    void testGameCannotBeCreatedWithOnePlayer() {
        assertThrows(IllegalArgumentException.class, () -> new Game(1, null));
    }

    @Test
    void playerCanTakeTurn() {
        Token[] tokens = {new Token("John Doe","BattleShip.png"), new Token("Jane Doe","Car.png")};
        Game game = new Game(2, tokens);
        TurnManager tm = TurnManager.getInstance().getTurnManager();
        Player currentPlayer = tm.getCurrentPlayer();
        assertEquals(0, currentPlayer.getPosition());
        while (currentPlayer.getPosition() == 0) {
            tm.playerTakeTurn();
        }
        assertNotEquals(0, currentPlayer.getPosition());
    }

    @Test
    void playerOneAndPlayerTwoCanTakeTurns() {
        Token[] tokens = {new Token("John Doe","BattleShip.png"), new Token("Jane Doe","Car.png")};
        Game game = new Game(2, tokens);
        TurnManager tm = TurnManager.getInstance().getTurnManager();
        Player currentPlayer = tm.getCurrentPlayer();
        while (currentPlayer.getPosition() == 0) {
            tm.playerTakeTurn();
        }
        assertNotEquals(0, currentPlayer.getPosition());
        tm.nextPlayersTurn();
        currentPlayer = tm.getCurrentPlayer();
        while (currentPlayer.getPosition() == 0) {
            tm.playerTakeTurn();
        }
        assertNotEquals(0, currentPlayer.getPosition());
    }

    @Test
    void afterPlayer2TurnGoBackToPlayer1() {
        Token[] tokens = {new Token("John Doe","BattleShip.png"), new Token("Jane Doe","Car.png")};
        Game game = new Game(2, tokens);
        TurnManager tm = TurnManager.getInstance().getTurnManager();
        tm.nextPlayersTurn();
        Player currentPlayer = tm.getCurrentPlayer();
        while (currentPlayer.getPosition() == 0) {
            tm.playerTakeTurn();
        }
        assertNotEquals(0, currentPlayer.getPosition());
        tm.nextPlayersTurn();
        currentPlayer = tm.getCurrentPlayer();
        assertEquals(0, currentPlayer.getPosition());
        while (currentPlayer.getPosition() == 0) {
            tm.playerTakeTurn();
        }
        assertNotEquals(0, currentPlayer.getPosition());
    }

    @Test
    void playerInJailGoesThroughInJailProcess(){
        Token[] tokens = {new Token("John Doe","BattleShip.png"), new Token("Jane Doe","Car.png")};
        Game game = new Game(2, tokens);
        TurnManager tm = TurnManager.getInstance().getTurnManager();
        Player currentPlayer = tm.getCurrentPlayer();
        currentPlayer.goToJail();
        tm.jailTurnLogic(currentPlayer);
        currentPlayer.releaseFromJail();
        tm.playerTakeTurn();
        assertNotEquals(10, currentPlayer.getPosition());
    }

    @Test
    void removingAPlayerFromGameChancesTurnManagerQueue(){
        Token[] tokens = {new Token("John Doe","BattleShip.png"), new Token("Jane Doe","Car.png"), new Token("Jack Doe","Dog.png")};
        Game game = new Game(3, tokens);
        TurnManager tm = TurnManager.getInstance().getTurnManager();
        Player currentPlayer = tm.getCurrentPlayer();
        tm.nextPlayersTurn();
        tm.removePlayer(currentPlayer);
        ArrayList<Player> players = new ArrayList<>();
        boolean newPlayerAdded = true;
        while (newPlayerAdded) {
            tm.nextPlayersTurn();
            if (players.contains(tm.getCurrentPlayer())) {
                newPlayerAdded = false;
            } else {
            players.add(tm.getCurrentPlayer());
            }
        }
        assertEquals(2, players.size());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testGetPlayers() {
        Token token1 = new Token("Player1", "BattleShip.png");
        Token token2 = new Token("Player2", "Car.png");

        Player player1 = new HumanPlayer("Player1", token1);
        Player player2 = new HumanPlayer("Player2", token2);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        TurnManager turnManager = new TurnManager(players.size(), players, null, null);

        ArrayList<Player> retrievedPlayers = turnManager.getPlayers();
        assertEquals(players.size(), retrievedPlayers.size());
        assertTrue(retrievedPlayers.contains(player1));
        assertTrue(retrievedPlayers.contains(player2));
    }
}