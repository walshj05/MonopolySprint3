package org.monopoly.Model.GameTiles;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.Players.Token;

/**
 * Represents a class to test the Tiles on the Game Board created by GameTile.
 *
 * @author shifmans
 */
class TestGameTile extends GameTile {

    /**
     * Developed by: shifmans
     * */
    public TestGameTile(String name, String actions) {
        super(name, actions);
    }

    /**
     * Developed by: shifmans
     * */
    @Override
    public String landOn() {
        return super.landOn();
    }

    @Override
    public void executeStrategy(Player player) {

    }
}

/**
 * Tests to check that all methods within the GameTile class work properly.
 *
 * @author shifmans
 */
class GameTileTest {

    /**
     * Developed by: shifmans
     * */
    @Test
    void testGameTileConstructor() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        assertEquals("Tile", tile.getName());
        assertEquals("Test Action", tile.landOn());
        assertEquals(new ArrayList<String>(), tile.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileNameCorrectLabel() {
        GameTile tile = new TestGameTile("Tile", "Test Action");

        assertEquals("Tile", tile.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileNameIncorrectLabel() {
        GameTile tile = new TestGameTile("Tile", "Test Action");

        assertNotEquals("Go Space", tile.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileLandOnCorrectActions() {
        GameTile tile = new TestGameTile("Tile", "Test Action");

        assertEquals("Test Action", tile.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileLandOnIncorrectActions() {
        GameTile tile = new TestGameTile("Tile", "Test Action");

        assertNotEquals("Collect $200", tile.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileTokensNoTokens() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, tile.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileTokensAddOneToken() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        ArrayList<String> tokens = new ArrayList<>();

        tile.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, tile.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileTokensAddTwoToken() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        ArrayList<String> tokens = new ArrayList<>();

        tile.addToken("Dog");
        tile.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, tile.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileTokensAddOneTokenUnderCapacity() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        tile.addToken("Dog");
        tile.addToken("Hat");
        tile.addToken("Iron");
        tile.addToken("Thimble");
        tile.addToken("Ship");
        tile.addToken("Boot");
        tile.addToken("Cannon");
        tile.addToken("Car");

        assertFalse(tile.hasReachedCapacity());
        assertEquals(tile.MAX_CAPACITY-1, tile.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileTokensAddTokenToCapacity() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        tile.addToken("Dog");
        tile.addToken("Hat");
        tile.addToken("Iron");
        tile.addToken("Thimble");
        tile.addToken("Ship");
        tile.addToken("Boot");
        tile.addToken("Cannon");
        tile.addToken("Car");
        tile.addToken("Wheelbarrow");

        assertTrue(tile.hasReachedCapacity());
        assertEquals(tile.MAX_CAPACITY, tile.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileTokensAddOneTokenOverCapacity() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        tile.addToken("Dog");
        tile.addToken("Hat");
        tile.addToken("Iron");
        tile.addToken("Thimble");
        tile.addToken("Ship");
        tile.addToken("Boot");
        tile.addToken("Cannon");
        tile.addToken("Car");
        tile.addToken("Wheelbarrow");
        tile.addToken("Dog");

        assertTrue(tile.hasReachedCapacity());
        assertEquals(tile.MAX_CAPACITY, tile.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileTokensAddOneRemoveOneToken() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        ArrayList<String> tokens = new ArrayList<>();

        tile.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, tile.getTokens());

        tile.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, tile.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileTokensAddTwoRemoveOneToken() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        ArrayList<String> tokens = new ArrayList<>();

        tile.addToken("Dog");
        tile.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, tile.getTokens());

        tile.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, tile.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileTokensNoTokensRemoveOneToken() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        ArrayList<String> tokens = new ArrayList<>();

        tile.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, tile.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTilePrice() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        assertEquals(0, tile.getPrice());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileGetColorGroup() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        assertNull(tile.getColorGroup());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileGetMortgageValue() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        assertEquals(0, tile.getMortgageValue());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileGetUnmortgageValue() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        assertEquals(0, tile.getUnmortgageValue());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileIsMortgaged() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        assertFalse(tile.isMortgaged());

        tile.setMortgagedStatus(true);
        assertTrue(tile.isMortgaged());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGameTileGetOwner() {
        GameTile tile = new TestGameTile("Tile", "Test Action");
        assertEquals(null, tile.getOwner());

        HumanPlayer humanPlayer = new HumanPlayer("Bob", new Token("Thimble", "Thimble.png"));
        tile.setOwner(humanPlayer.getName());
        assertEquals("Bob", tile.getOwner());
    }
}
