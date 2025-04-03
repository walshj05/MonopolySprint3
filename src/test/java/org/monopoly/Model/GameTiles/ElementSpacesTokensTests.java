package org.monopoly.Model.GameTiles;

import org.junit.jupiter.api.Test;
import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Cards.ChanceDeck;
import org.monopoly.Model.Cards.CommunityChestDeck;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to check whether collection of tokens currently on each element spaces are correct.
 * Tests whether the collection of tokens are properly added, removed, and stored.
 *
 * @author shifmans
 */
public class ElementSpacesTokensTests {

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceTokensNoTokens() {
        GoSpace space = new GoSpace();
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceTokensAddOneToken() {
        GoSpace space = new GoSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceTokensAddTwoToken() {
        GoSpace space = new GoSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceTokensAddOneTokenUnderCapacity() {
        GoSpace space = new GoSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceTokensAddTokenToCapacity() {
        GoSpace space = new GoSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceTokensAddOneTokenOverCapacity() {
        GoSpace space = new GoSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceTokensAddOneRemoveOneToken() {
        GoSpace space = new GoSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceTokensAddTwoRemoveOneToken() {
        GoSpace space = new GoSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceTokensNoTokensRemoveOneToken() {
        GoSpace space = new GoSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceTokensNoTokens() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceTokensAddOneToken() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceTokensAddTwoToken() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceTokensAddOneTokenUnderCapacity() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceTokensAddTokenToCapacity() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceTokensAddOneTokenOverCapacity() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceTokensAddOneRemoveOneToken() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceTokensAddTwoRemoveOneToken() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceTokensNoTokensRemoveOneToken() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceTokensNoTokens() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceTokensAddOneToken() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceTokensAddTwoToken() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceTokensAddOneTokenUnderCapacity() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceTokensAddTokenToCapacity() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceTokensAddOneTokenOverCapacity() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceTokensAddOneRemoveOneToken() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceTokensAddTwoRemoveOneToken() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceTokensNoTokensRemoveOneToken() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceTokensNoTokens() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceTokensAddOneToken() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceTokensAddTwoToken() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceTokensAddOneTokenUnderCapacity() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceTokensAddTokenToCapacity() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceTokensAddOneTokenOverCapacity() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceTokensAddOneRemoveOneToken() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceTokensAddTwoRemoveOneToken() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceTokensNoTokensRemoveOneToken() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceTokensNoTokens() {
        JailSpace space = new JailSpace();
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceTokensAddOneToken() {
        JailSpace space = new JailSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceTokensAddTwoToken() {
        JailSpace space = new JailSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceTokensAddOneTokenUnderCapacity() {
        JailSpace space = new JailSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceTokensAddTokenToCapacity() {
        JailSpace space = new JailSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceTokensAddOneTokenOverCapacity() {
        JailSpace space = new JailSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceTokensAddOneRemoveOneToken() {
        JailSpace space = new JailSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceTokensAddTwoRemoveOneToken() {
        JailSpace space = new JailSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceTokensNoTokensRemoveOneToken() {
        JailSpace space = new JailSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceTokensNoTokens() {
        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, new ArrayList<>(), ColorGroup.RAILROAD, 100);
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceTokensAddOneToken() {
        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, new ArrayList<>(), ColorGroup.RAILROAD, 100);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceTokensAddTwoToken() {
        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, new ArrayList<>(), ColorGroup.RAILROAD, 100);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceTokensAddOneTokenUnderCapacity() {
        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, new ArrayList<>(), ColorGroup.RAILROAD, 100);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceTokensAddTokenToCapacity() {
        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, new ArrayList<>(), ColorGroup.RAILROAD, 100);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceTokensAddOneTokenOverCapacity() {
        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, new ArrayList<>(), ColorGroup.RAILROAD, 100);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceTokensAddOneRemoveOneToken() {
        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, new ArrayList<>(), ColorGroup.RAILROAD, 100);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceTokensAddTwoRemoveOneToken() {
        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, new ArrayList<>(), ColorGroup.RAILROAD, 100);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceTokensNoTokensRemoveOneToken() {
        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, new ArrayList<>(), ColorGroup.RAILROAD, 100);
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceTokensNoTokens() {
        FreeParkingSpace space = new FreeParkingSpace();
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceTokensAddOneToken() {
        FreeParkingSpace space = new FreeParkingSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceTokensAddTwoToken() {
        FreeParkingSpace space = new FreeParkingSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceTokensAddOneTokenUnderCapacity() {
        FreeParkingSpace space = new FreeParkingSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceTokensAddTokenToCapacity() {
        FreeParkingSpace space = new FreeParkingSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceTokensAddOneTokenOverCapacity() {
        FreeParkingSpace space = new FreeParkingSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceTokensAddOneRemoveOneToken() {
        FreeParkingSpace space = new FreeParkingSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceTokensAddTwoRemoveOneToken() {
        FreeParkingSpace space = new FreeParkingSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceTokensNoTokensRemoveOneToken() {
        FreeParkingSpace space = new FreeParkingSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceTokensNoTokens() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceTokensAddOneToken() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceTokensAddTwoToken() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceTokensAddOneTokenUnderCapacity() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceTokensAddTokenToCapacity() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceTokensAddOneTokenOverCapacity() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceTokensAddOneRemoveOneToken() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceTokensAddTwoRemoveOneToken() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceTokensNoTokensRemoveOneToken() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceTokensNoTokens() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceTokensAddOneToken() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceTokensAddTwoToken() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceTokensAddOneTokenUnderCapacity() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceTokensAddTokenToCapacity() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceTokensAddOneTokenOverCapacity() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceTokensAddOneRemoveOneToken() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceTokensAddTwoRemoveOneToken() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceTokensNoTokensRemoveOneToken() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceTokensNoTokens() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceTokensAddOneToken() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceTokensAddTwoToken() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceTokensAddOneTokenUnderCapacity() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceTokensAddTokenToCapacity() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceTokensAddOneTokenOverCapacity() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceTokensAddOneRemoveOneToken() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceTokensAddTwoRemoveOneToken() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceTokensNoTokensRemoveOneToken() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceTokensNoTokens() {
        IncomeTaxSpace space = new IncomeTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceTokensAddOneToken() {
        IncomeTaxSpace space = new IncomeTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceTokensAddTwoToken() {
        IncomeTaxSpace space = new IncomeTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceTokensAddOneTokenUnderCapacity() {
        IncomeTaxSpace space = new IncomeTaxSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceTokensAddTokenToCapacity() {
        IncomeTaxSpace space = new IncomeTaxSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceTokensAddOneTokenOverCapacity() {
        IncomeTaxSpace space = new IncomeTaxSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceTokensAddOneRemoveOneToken() {
        IncomeTaxSpace space = new IncomeTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceTokensAddTwoRemoveOneToken() {
        IncomeTaxSpace space = new IncomeTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceTokensNoTokensRemoveOneToken() {
        IncomeTaxSpace space = new IncomeTaxSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceTokensNoTokens() {
        GoToJailSpace space = new GoToJailSpace();
        ArrayList<String> tokens = new ArrayList<>();
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceTokensAddOneToken() {
        GoToJailSpace space = new GoToJailSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceTokensAddTwoToken() {
        GoToJailSpace space = new GoToJailSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceTokensAddOneTokenUnderCapacity() {
        GoToJailSpace space = new GoToJailSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");

        assertFalse(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY-1, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceTokensAddTokenToCapacity() {
        GoToJailSpace space = new GoToJailSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceTokensAddOneTokenOverCapacity() {
        GoToJailSpace space = new GoToJailSpace();
        space.addToken("Dog");
        space.addToken("Hat");
        space.addToken("Iron");
        space.addToken("Thimble");
        space.addToken("Ship");
        space.addToken("Boot");
        space.addToken("Cannon");
        space.addToken("Car");
        space.addToken("Wheelbarrow");
        space.addToken("Dog");

        assertTrue(space.hasReachedCapacity());
        assertEquals(space.MAX_CAPACITY, space.getTokens().size());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceTokensAddOneRemoveOneToken() {
        GoToJailSpace space = new GoToJailSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        tokens.add("Dog");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceTokensAddTwoRemoveOneToken() {
        GoToJailSpace space = new GoToJailSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.addToken("Dog");
        space.addToken("Hat");
        tokens.add("Dog");
        tokens.add("Hat");
        assertEquals(tokens, space.getTokens());

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceTokensNoTokensRemoveOneToken() {
        GoToJailSpace space = new GoToJailSpace();
        ArrayList<String> tokens = new ArrayList<>();

        space.removeToken("Dog");
        tokens.remove("Dog");
        assertEquals(tokens, space.getTokens());
    }
}
