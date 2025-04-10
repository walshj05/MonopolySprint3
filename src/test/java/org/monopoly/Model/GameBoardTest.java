package org.monopoly.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.monopoly.Model.GameTiles.GameTile;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Token;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = GameBoard.getInstance();
    }

    @Test
    void testGetTile() {
        GameTile tile = gameBoard.getTile(0);
        assertNotNull(tile, "Tile at position 0 should not be null.");
        assertEquals("Go Space", tile.getName(), "Tile at position 0 should be a Go Space.");
    }

    @Test
    void testDrawCommunityChestCard() {
        String card = gameBoard.drawCommunityChestCard();
        assertNotNull(card, "Drawn Community Chest card should not be null.");
        System.out.println("Drawn Community Chest card: " + card);
    }

    @Test
    void testDrawChanceCard() {
        String card = gameBoard.drawChanceCard();
        assertNotNull(card, "Drawn Chance card should not be null.");
        System.out.println("Drawn Chance card: " + card);
    }

    @Test
    void testNumberOfTiles() {
        int numberOfTiles = gameBoard.getNumberOfTiles();
        assertTrue(numberOfTiles > 0, "Number of tiles should be greater than 0.");
        System.out.println("Number of tiles: " + numberOfTiles);
    }

    @Test
    void testChanceSpacePositions() {
        assertEquals("Chance Space", gameBoard.getTile(7).getName(), "Position 7 should be Chance Space.");
        assertEquals("Chance Space", gameBoard.getTile(22).getName(), "Position 22 should be Chance Space.");
        assertEquals("Chance Space", gameBoard.getTile(36).getName(), "Position 36 should be Chance Space.");
    }

    @Test
    void testCommunityChestSpacePositions() {
        assertEquals("Community Chest Space", gameBoard.getTile(2).getName(), "Position 2 should be Community Chest Space.");
        assertEquals("Community Chest Space", gameBoard.getTile(17).getName(), "Position 17 should be Community Chest Space.");
        assertEquals("Community Chest Space", gameBoard.getTile(33).getName(), "Position 33 should be Community Chest Space.");
    }

    @Test
    void testGetInstance() {
        GameBoard anotherGameBoard = GameBoard.getInstance();
        assertSame(gameBoard, anotherGameBoard, "getInstance should return the same instance.");
    }

    @Test
    void testTokensArrayListsAreEmpty() {
        HumanPlayer humanPlayer = new HumanPlayer("Test Player", new Token("Test Token", "BattleShip.png"));
        assertTrue(gameBoard.getTokens(0).contains(humanPlayer.getToken()));
        humanPlayer.move(10);
        assertTrue(gameBoard.getTokens(10).contains(humanPlayer.getToken()));
        assertFalse(gameBoard.getTokens(0).contains(humanPlayer.getToken()), "Token should not be in the original position.");

    }
}
