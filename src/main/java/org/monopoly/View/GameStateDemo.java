package org.monopoly.View;

import org.monopoly.Model.Game;
import org.monopoly.Model.Players.Token;

/**
 * This class is a demo of the Game class
 * @author walshj05
 */
public class GameStateDemo {
    public static void main(String[] args) {
        System.out.println("This is a demo of the Game class");
        System.out.println("This will demonstrate the process of the player turns and game starting process.\n");

        System.out.println("Creating a game with 2 players");
        Token[] tokens = {new Token("Battleship","BattleShip.png"), new Token("Car","Car.png")};
        Game game = new Game(2, tokens);
        System.out.println("Game created successfully...\nThe Players are:");
        System.out.println(game.getTurnManager().getCurrentPlayer());
        game.nextPlayersTurn();
        System.out.println(game.getTurnManager().getCurrentPlayer());
        game.nextPlayersTurn();
        System.out.println();

        System.out.println("Player 1 takes a turn");
        game.playerTakeTurn();
        game.nextPlayersTurn();
        System.out.println();

        System.out.println("Player 2 takes a turn");
        game.playerTakeTurn();
        game.nextPlayersTurn();
        System.out.println();
        System.out.println("\n\n\n");

        System.out.println("Creating a game with 3 players");
        Token[] tokens2 = {new Token("Battleship","BattleShip.png"), new Token("Car","Car.png"), new Token("Dog","Dog.png")};
        Game game2 = new Game(3, tokens2);
        System.out.println("Game created successfully...\nThe Players are:");
        System.out.println(game2.getTurnManager().getCurrentPlayer());
        game2.nextPlayersTurn();
        System.out.println(game2.getTurnManager().getCurrentPlayer());
        game2.nextPlayersTurn();
        System.out.println(game2.getTurnManager().getCurrentPlayer());
        game2.nextPlayersTurn();

        System.out.println("Player 1 takes a turn");
        game2.playerTakeTurn();
        game2.nextPlayersTurn();
        System.out.println();

        System.out.println("Player 2 takes a turn");
        game2.playerTakeTurn();
        game2.nextPlayersTurn();
        System.out.println();

        System.out.println("Player 3 takes a turn");
        game2.playerTakeTurn();
        game2.nextPlayersTurn();
        System.out.println("\n\n\n");
    }
}
