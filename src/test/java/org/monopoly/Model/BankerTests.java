package org.monopoly.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.monopoly.Model.Cards.TitleDeedDeck;
import org.monopoly.Model.GameTiles.ElectricCompanySpace;
import org.monopoly.Model.GameTiles.WaterWorksSpace;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Token;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class BankerTests {

    @Test
    public void testBankerConstructor() {
        Banker banker = new Banker();
        TitleDeedDeck deck = banker.getDeck();

        assertEquals(28, deck.getSize());
        assertEquals(32, banker.getHouses());
        assertEquals(32, banker.getHotels());
        assertEquals(Double.POSITIVE_INFINITY, banker.getBalance());

        String[] properties = {"Boardwalk", "Park Place", "Baltic Avenue", "Mediterranean Avenue",
                "Reading Railroad", "Pennsylvania Railroad", "B&O Railroad", "Short Line Railroad",
                "Oriental Avenue", "Vermont Avenue", "Connecticut Avenue", "St. Charles Place",
                "States Avenue", "Virginia Avenue", "St. James Place", "Tennessee Avenue",
                "New York Avenue", "Kentucky Avenue", "Indiana Avenue", "Illinois Avenue",
                "Atlantic Avenue", "Ventnor Avenue", "Marvin Gardens", "Pacific Avenue",
                "North Carolina Avenue", "Pennsylvania Avenue", "Electric Company", "Water Works"};

        for (String property : properties) {
            assertTrue(deck.getTitleDeeds().getProperties().containsKey(property));
        }
    }

    @Test
    public void testSellProperty() {
        Banker banker = new Banker();
        TitleDeedDeck deck = banker.getDeck();
        int deckSize = deck.getSize();

        HumanPlayer player = new HumanPlayer("Bob", new Token("Thimble", "Thimble.png"));
        banker.sellProperty("Boardwalk", player);

        assertEquals(deckSize - 1, deck.getSize());
        assertEquals("Bob", deck.getTitleDeeds().getProperty("Boardwalk").getOwner());
        assertEquals(1100, player.getBalance());
    }

    @Test
    public void testSellHouse() {
        Banker banker = new Banker();
        int houses = banker.getHouses();

        HumanPlayer player = new HumanPlayer("Bob", new Token("Thimble", "Thimble.png"));
        banker.sellHouse("Boardwalk", player);

        assertEquals(houses - 1, banker.getHouses());
        assertEquals(1300, player.getBalance());

    }

    @Test
    public void testReceiveHouses() {
        Banker banker = new Banker();
        int houses = banker.getHouses();

        HumanPlayer player = new HumanPlayer("Bob", new Token("Thimble", "Thimble.png"));
        banker.receiveHouse("Boardwalk", player);

        assertEquals(houses + 1, banker.getHouses());
        assertEquals(1600, player.getBalance());

    }

    @Test
    public void testSellHotel() {
        Banker banker = new Banker();
        int hotels = banker.getHotels();

        HumanPlayer player = new HumanPlayer("Bob", new Token("Thimble", "Thimble.png"));
        banker.sellHotel("Boardwalk", player);

        assertEquals(hotels - 1, banker.getHotels());
        assertEquals(1300, player.getBalance());

    }

    @Test
    public void testReceiveHotels() {
        Banker banker = new Banker();
        int hotels = banker.getHotels();
        HumanPlayer player = new HumanPlayer("Jim", new Token("Thimble", "Thimble.png"));

        banker.receiveHotel("Boardwalk", player);
        assertEquals(hotels + 1, banker.getHotels());
        assertEquals(1600, player.getBalance());

    }

    @Test
    public void testAuctionProperty() {
        Banker banker = new Banker();

        ArrayList<HumanPlayer> players = new ArrayList<>();
        players.add(new HumanPlayer("Jim", new Token("Thimble", "Thimble.png")));
        players.add(new HumanPlayer("Pat", new Token("Top Hat", "TopHat.png")));

        // Simulate user input using System.setIn()
        String simulatedInput = "Y\nY\n10\n20\nY\nN\n21";  // Y = bid, 10 = Jim's bid, 20 = Pat's bid
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        banker.auctionProperty("Boardwalk", players);
        assertEquals("Jim", banker.getDeck().getTitleDeeds().getProperty("Boardwalk").getOwner());  // Pat should win
    }

    @Test
    public void testMortgageProperty() {
        Banker banker = new Banker();
        TitleDeedDeck deck = banker.getDeck();
        assertFalse(deck.getTitleDeeds().getProperty("Boardwalk").isMortgaged());

        banker.mortgageProperty("Boardwalk");
        assertTrue(deck.getTitleDeeds().getProperty("Boardwalk").isMortgaged());
    }

    @Test
    public void testMortgageRailroad() {
        Banker banker = new Banker();
        TitleDeedDeck deck = banker.getDeck();
        assertFalse(deck.getTitleDeeds().getProperty("Short Line Railroad").isMortgaged());

        banker.mortgageProperty("Short Line Railroad");
        assertTrue(deck.getTitleDeeds().getProperty("Short Line Railroad").isMortgaged());
    }

    @Test
    public void testMortgageElectricCompany() {
        Banker banker = new Banker();
        TitleDeedDeck deck = banker.getDeck();
        assertFalse(deck.getTitleDeeds().getProperty("Electric Company").isMortgaged());

        banker.mortgageProperty("Electric Company");
        assertTrue(deck.getTitleDeeds().getProperty("Electric Company").isMortgaged());
    }

    @Test
    public void testMortgageWaterWorks() {
        Banker banker = new Banker();
        TitleDeedDeck deck = banker.getDeck();
        assertFalse(deck.getTitleDeeds().getProperty("Water Works").isMortgaged());

        banker.mortgageProperty("Water Works");
        assertTrue(deck.getTitleDeeds().getProperty("Water Works").isMortgaged());
    }

    @Test
    public void testPayGoSpace() {
        Banker banker = new Banker();
        HumanPlayer humanPlayer = new HumanPlayer("Bob", new Token("Thimble", "Thimble.png"));

        assertEquals(1500, humanPlayer.getBalance());
        assertEquals(Double.POSITIVE_INFINITY, banker.getBalance());

        banker.payGoSpace(humanPlayer);
        assertEquals(1700, humanPlayer.getBalance());
        assertEquals(Double.POSITIVE_INFINITY, banker.getBalance());
    }

    @Test
    public void testReceiveMoney() {
        Banker banker = new Banker();
        HumanPlayer humanPlayer = new HumanPlayer("Bob", new Token("Thimble", "Thimble.png"));

        assertEquals(1500, humanPlayer.getBalance());
        assertEquals(Double.POSITIVE_INFINITY, banker.getBalance());

        banker.receiveMoney(humanPlayer, 200);
        assertEquals(1300, humanPlayer.getBalance());
        assertEquals(Double.POSITIVE_INFINITY, banker.getBalance());
    }

    @Test
    public void testElectricCompanyGetRent() {
        Banker banker = new Banker();
        ElectricCompanySpace electricCompany = (ElectricCompanySpace) banker.getDeck().getTitleDeeds().getProperty("Electric Company");

        assertTrue((electricCompany.getRentPrice(1) >= 8) && (electricCompany.getRentPrice(1) <= 48));
        assertTrue((electricCompany.getRentPrice(2) >= 20) && (electricCompany.getRentPrice(2) <= 120));
    }

    @Test
    public void testWaterWorksGetRent() {
        Banker banker = new Banker();
        WaterWorksSpace waterWorks = (WaterWorksSpace) banker.getDeck().getTitleDeeds().getProperty("Water Works");

        assertTrue((waterWorks.getRentPrice(1) >= 8) && (waterWorks.getRentPrice(1) <= 48));
        assertTrue((waterWorks.getRentPrice(2) >= 20) && (waterWorks.getRentPrice(2) <= 120));
    }

    @Test
    public void testUnmortgagedValue() {
        Banker banker = new Banker();
        TitleDeedDeck deck = banker.getDeck();

        assertEquals(33, deck.getTitleDeeds().getProperty("Mediterranean Avenue").getUnmortgageValue());
        assertEquals(110, deck.getTitleDeeds().getProperty("Short Line Railroad").getUnmortgageValue());
        assertEquals(82, deck.getTitleDeeds().getProperty("Electric Company").getUnmortgageValue());
        assertEquals(82, deck.getTitleDeeds().getProperty("Water Works").getUnmortgageValue());
    }
}
