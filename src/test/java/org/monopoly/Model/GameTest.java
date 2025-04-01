package org.monopoly.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.Players.Token;

import java.util.ArrayList;

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
        TurnManager tm = game.getTurnManager();
        Player currentPlayer = tm.getCurrentPlayer();
        assertEquals(0, currentPlayer.getPosition());
        while (currentPlayer.getPosition() == 0) {
            game.playerTakeTurn();
        }
        assertNotEquals(0, currentPlayer.getPosition());
    }

    @Test
    void playerOneAndPlayerTwoCanTakeTurns() {
        Token[] tokens = {new Token("John Doe","BattleShip.png"), new Token("Jane Doe","Car.png")};
        Game game = new Game(2, tokens);
        TurnManager tm = game.getTurnManager();
        Player currentPlayer = tm.getCurrentPlayer();
        while (currentPlayer.getPosition() == 0) {
            game.playerTakeTurn();
        }
        assertNotEquals(0, currentPlayer.getPosition());
        game.nextPlayersTurn();
        currentPlayer = tm.getCurrentPlayer();
        while (currentPlayer.getPosition() == 0) {
            game.playerTakeTurn();
        }
        assertNotEquals(0, currentPlayer.getPosition());
    }

    @Test
    void afterPlayer2TurnGoBackToPlayer1() {
        Token[] tokens = {new Token("John Doe","BattleShip.png"), new Token("Jane Doe","Car.png")};
        Game game = new Game(2, tokens);
        TurnManager tm = game.getTurnManager();
        game.nextPlayersTurn();
        Player currentPlayer = tm.getCurrentPlayer();
        while (currentPlayer.getPosition() == 0) {
            game.playerTakeTurn();
        }
        assertNotEquals(0, currentPlayer.getPosition());
        game.nextPlayersTurn();
        currentPlayer = tm.getCurrentPlayer();
        assertEquals(0, currentPlayer.getPosition());
        while (currentPlayer.getPosition() == 0) {
            game.playerTakeTurn();
        }
        assertNotEquals(0, currentPlayer.getPosition());
    }

    @Test
    void playerInJailGoesThroughInJailProcess(){
        Token[] tokens = {new Token("John Doe","BattleShip.png"), new Token("Jane Doe","Car.png")};
        Game game = new Game(2, tokens);
        TurnManager tm = game.getTurnManager();
        Player currentPlayer = tm.getCurrentPlayer();
        currentPlayer.goToJail();
        game.jailTurnLogic(currentPlayer);
        assertEquals(10, currentPlayer.getPosition());
        currentPlayer.releaseFromJail();
        game.playerTakeTurn();
    }

    @Test
    void removingAPlayerFromGameChancesTurnManagerQueue(){
        Token[] tokens = {new Token("John Doe","BattleShip.png"), new Token("Jane Doe","Car.png"), new Token("Jack Doe","Dog.png")};
        Game game = new Game(3, tokens);
        TurnManager tm = game.getTurnManager();
        Player currentPlayer = tm.getCurrentPlayer();
        game.nextPlayersTurn();
        game.removePlayer(currentPlayer);
        ArrayList<Player> players = new ArrayList<>();
        boolean newPlayerAdded = true;
        while (newPlayerAdded) {
            game.nextPlayersTurn();
            if (players.contains(tm.getCurrentPlayer())) {
                newPlayerAdded = false;
            } else {
            players.add(tm.getCurrentPlayer());
            }
        }
        assertEquals(2, players.size());
    }
}