package org.monopoly.Model.GameTiles;

import org.junit.jupiter.api.Test;
import org.monopoly.Model.Cards.ChanceDeck;
import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Cards.CommunityChestDeck;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to check whether the proper actions show for each element space when landed on.
 *
 * @author shifmans
 */
public class ElementSpacesLandOnTests {

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceLandOnCorrectActions() {
        GoSpace space = new GoSpace();

        assertEquals("Collect $200 in salary for Passing Go!", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoSpaceLandOnIncorrectActions() {
        GoSpace space = new GoSpace();

        assertNotEquals("Property Name: Boardwalk\n" +
                "Color Set: Dark Blue\n" +
                "Purchase Price: $400\n" +
                "Rent (without houses/hotels): $50\n" +
                "Rent with 1 House: $200\n" +
                "Rent with 2 Houses: $600\n" +
                "Rent with 3 Houses: $1,400\n" +
                "Rent with 4 Houses: $1,800\n" +
                "Rent with Hotel: $2,000\n" +
                "Mortgage Value: $200\n" +
                "Houses: Can build 4 houses\n" +
                "Hotel: Can build 1 hotel", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceLandOnCorrectActions() {
        ArrayList<Integer> rentPrices = new ArrayList<>();
        rentPrices.add(2);
        rentPrices.add(10);
        rentPrices.add(30);
        rentPrices.add(90);
        rentPrices.add(160);
        rentPrices.add(250);

        PropertySpace space = new PropertySpace("Mediterranean Avenue", "", 60, rentPrices, ColorGroup.BROWN, 50, 50, 30);

            assertEquals("Property Name: Mediterranean Avenue" + "\n" +
                    "Color Set: BROWN" + "\n" +
                    "Purchase Price: $60" + "\n" +
                    "Rent (without houses/hotels): $2" + "\n" +
                    "Rent with 1 House: $10" + "\n" +
                    "Rent with 2 Houses: $30" + "\n" +
                    "Rent with 3 Houses: $90" + "\n" +
                    "Rent with 4 Houses: $160" + "\n" +
                    "Rent with Hotel: $250" + "\n" +
                    "Mortgage Value: $30" + "\n" +
                    "House Price: $50" + "\n" +
                    "Hotel Price: $50", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testPropertySpaceLandOnIncorrectActions() {
        ArrayList<Integer> rentPrices = new ArrayList<>();
        rentPrices.add(2);
        rentPrices.add(10);
        rentPrices.add(30);
        rentPrices.add(90);
        rentPrices.add(160);
        rentPrices.add(250);

        PropertySpace space = new PropertySpace("Property Space", "", 0, rentPrices, ColorGroup.BROWN, 0, 0, 0);

            assertNotEquals("Draw a card from the deck", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceLandOnCorrectActions() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());

        assertEquals("Draw a card from the deck", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testCommunityChestSpaceLandOnIncorrectActions() {
        CommunityChestSpace space = new CommunityChestSpace(new CommunityChestDeck());

        assertNotEquals("Return a card to the deck", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceLandOnCorrectActions() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());

        assertEquals("Draw a card from the deck", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testChanceSpaceLandOnIncorrectActions() {
        ChanceSpace space = new ChanceSpace(new ChanceDeck());

        assertNotEquals("Pay $50 or roll doubles to get out of jail", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceLandOnCorrectActions() {
        JailSpace space = new JailSpace();

        assertEquals("Pay $50 or roll doubles to get out of jail", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testJailSpaceLandOnIncorrectActions() {
        JailSpace space = new JailSpace();

        assertNotEquals("Property Name: Reading Railroad\n" +
                "Color Set: None (Railroad)\n" +
                "Purchase Price: $200\n" +
                "Rent (without houses/hotels): $25\n" +
                "Rent with 1 Railroad Owned: $25\n" +
                "Rent with 2 Railroads Owned: $50\n" +
                "Rent with 3 Railroads Owned: $100\n" +
                "Rent with 4 Railroads Owned: $200\n" +
                "Mortgage Value: $100\n" +
                "Houses: Cannot build houses or hotels\n" +
                "Hotel: Cannot build hotels", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceLandOnCorrectActions() {
        ArrayList<Integer> rentPrices = new ArrayList<>();
        rentPrices.add(25);
        rentPrices.add(50);
        rentPrices.add(100);
        rentPrices.add(200);

        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, rentPrices, ColorGroup.RAILROAD, 100);

        assertEquals("Property Name: Reading Railroad\n" +
                "Color Set: RAILROAD\n" +
                "Purchase Price: $200\n" +
                "Rent with 1 Railroad Owned: $25\n" +
                "Rent with 2 Railroads Owned: $50\n" +
                "Rent with 3 Railroads Owned: $100\n" +
                "Rent with 4 Railroads Owned: $200\n" +
                "Mortgage Value: $100", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testRailroadSpaceLandOnIncorrectActions() {
        ArrayList<Integer> rentPrices = new ArrayList<>();
        rentPrices.add(25);
        rentPrices.add(50);
        rentPrices.add(100);
        rentPrices.add(200);

        RailroadSpace space = new RailroadSpace("Reading Railroad", "", 200, rentPrices, ColorGroup.RAILROAD, 100);

        assertNotEquals("Take a rest, you don't have to do anything", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceLandOnCorrectActions() {
        FreeParkingSpace space = new FreeParkingSpace();

        assertEquals("Take a rest, you don't have to do anything", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testFreeParkingSpaceLandOnIncorrectActions() {
        FreeParkingSpace space = new FreeParkingSpace();

        assertNotEquals("Property Name: Electric Company\n" +
                "Color Set: None (Utility)\n" +
                "Purchase Price: $150\n" +
                "Rent (without houses/hotels): Depends on dice roll\n" +
                "If you own 1 Utility: Rent is 4 times the amount rolled on the dice.\n" +
                "If you own 2 Utilities: Rent is 10 times the amount rolled on the dice.\n" +
                "Mortgage Value: $75\n" +
                "Houses: Cannot build houses or hotels\n" +
                "Hotel: Cannot build hotels", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceLandOnCorrectActions() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);

            assertEquals("Property Name: Electric Company\n" +
                "Color Set: UTILITY\n" +
                "Purchase Price: $150\n" +
                "Rent (without houses/hotels): Depends on dice roll\n" +
                "If you own 1 Utility: Rent is 4 times the amount rolled on the dice.\n" +
                "If you own 2 Utilities: Rent is 10 times the amount rolled on the dice.\n" +
                "Mortgage Value: $75", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testElectricCompanySpaceLandOnIncorrectActions() {
        ElectricCompanySpace space = new ElectricCompanySpace("", 150, new ArrayList<>(), ColorGroup.UTILITY, 75);

        assertNotEquals("Property Name: Water Works\n" +
                "Color Set: None (Utility)\n" +
                "Purchase Price: $150\n" +
                "Rent (without houses/hotels): Depends on dice roll\n" +
                "If you own 1 Utility: Rent is 4 times the amount rolled on the dice.\n" +
                "If you own 2 Utilities: Rent is 10 times the amount rolled on the dice.\n" +
                "Mortgage Value: $75\n" +
                "Houses: Cannot build houses or hotels\n" +
                "Hotel: Cannot build hotels", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceLandOnCorrectActions() {
        ArrayList<Integer> rentPriceMultiplier = new ArrayList<>();
        rentPriceMultiplier.add(4);
        rentPriceMultiplier.add(10);

        WaterWorksSpace space = new WaterWorksSpace("", 150, rentPriceMultiplier, ColorGroup.UTILITY, 75);

            assertEquals("Property Name: Water Works" + "\n" +
                "Color Set: UTILITY" + "\n" +
                "Purchase Price: $150" + "\n" +
                "Rent (without houses/hotels): Depends on dice roll\n" +
                "If you own 1 Utility: Rent is 4 times the amount rolled on the dice.\n" +
                "If you own 2 Utilities: Rent is 10 times the amount rolled on the dice.\n" +
                "Mortgage Value: $75", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testWaterWorksSpaceLandOnIncorrectActions() {
        WaterWorksSpace space = new WaterWorksSpace("", 0, new ArrayList<>(), ColorGroup.UTILITY, 0);

        assertNotEquals("Pay $100", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceLandOnCorrectActions() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();

        assertEquals("Pay $100 in Luxury Tax!", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testLuxuryTaxSpaceLandOnIncorrectActions() {
        LuxuryTaxSpace space = new LuxuryTaxSpace();

        assertNotEquals("Pay $200 in Luxury Tax!", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceLandOnCorrectActions() {
        IncomeTaxSpace space = new IncomeTaxSpace();

        assertEquals("Pay $200 in Income Tax!", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testIncomeTaxSpaceLandOnIncorrectActions() {
        IncomeTaxSpace space = new IncomeTaxSpace();

        assertNotEquals("Go directly to Jail", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceLandOnCorrectActions() {
        GoToJailSpace space = new GoToJailSpace();

        assertEquals("Go directly to Jail", space.landOn());
    }

    /**
     * Developed by: shifmans
     * */
    @Test
    public void testGoToJailSpaceLandOnIncorrectActions() {
        GoToJailSpace space = new GoToJailSpace();

        assertNotEquals("Collect $200", space.landOn());
    }
}
