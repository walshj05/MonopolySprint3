package org.monopoly.Model.GameTiles;

import org.junit.jupiter.api.Test;
import org.monopoly.Model.Cards.ColorGroup;
import java.util.ArrayList;
import org.monopoly.Model.Cards.ChanceDeck;
import org.monopoly.Model.Cards.CommunityChestDeck;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to check whether the names for each element space corresponds correctly.
 *
 * @author shifmans
 */
public class ElementSpacesNameTests {

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceNameCorrectLabel() {
        GoSpace space = new GoSpace();

        assertEquals("Go Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceNameIncorrectLabel() {
        GoSpace space = new GoSpace();

        assertNotEquals("Property Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceNameCorrectLabel() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);

        assertEquals("Property Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceNameIncorrectLabel() {
        PropertySpace space = new PropertySpace("Property Space", "", 0,new ArrayList<>(), ColorGroup.BROWN, 0, 0, 0);

        assertNotEquals("Community Chest Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceNameCorrectLabel() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());

        assertEquals("Community Chest Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceNameIncorrectLabel() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());

        assertNotEquals("Chance Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceNameCorrectLabel() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());

        assertEquals("Chance Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceNameIncorrectLabel() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());

        assertNotEquals("Jail Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceNameCorrectLabel() {
        JailSpace space = new JailSpace();

        assertEquals("Jail Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceNameIncorrectLabel() {
        JailSpace space = new JailSpace();

        assertNotEquals("Railroad Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceNameCorrectLabel() {
        RailroadSpace space = new RailroadSpace("Railroad Space", "", 0, new ArrayList<>(), ColorGroup.RAILROAD, 0);


            assertEquals("Railroad Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceNameIncorrectLabel() {
        RailroadSpace space = new RailroadSpace("Railroad Space", "", 0, new ArrayList<>(), ColorGroup.RAILROAD, 0);

        assertNotEquals("Go To Jail Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceNameCorrectLabel() {
        FreeParkingSpace space = new FreeParkingSpace();

        assertEquals("Free Parking Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceNameIncorrectLabel() {
        FreeParkingSpace space = new FreeParkingSpace();

        assertNotEquals("Electric Company", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceNameCorrectLabel() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);

        assertEquals("Electric Company", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceNameIncorrectLabel() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);

        assertNotEquals("Water Works Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceNameCorrectLabel() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);

            assertEquals("Water Works", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceNameIncorrectLabel() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);

        assertNotEquals("Luxury Tax Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceNameCorrectLabel() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();

        assertEquals("Luxury Tax Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceNameIncorrectLabel() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();

        assertNotEquals("Income Tax Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceNameCorrectLabel() {
        IncomeTaxSpace space = new IncomeTaxSpace();

        assertEquals("Income Tax Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceNameIncorrectLabel() {
        IncomeTaxSpace space = new IncomeTaxSpace();

        assertNotEquals("Go Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceNameCorrectLabel() {
        GoToJailSpace space = new GoToJailSpace();

        assertEquals("Go To Jail Space", space.getName());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceNameIncorrectLabel() {
        GoToJailSpace space = new GoToJailSpace();

        assertNotEquals("Free Parking Space", space.getName());
    }
}
