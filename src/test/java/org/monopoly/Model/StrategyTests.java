package org.monopoly.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.monopoly.Model.Cards.ColorGroup.BROWN;
import static org.monopoly.Model.Cards.ColorGroup.RAILROAD;

import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Model.Cards.ChanceDeck;
import org.monopoly.Model.Cards.CommunityChestDeck;
import org.monopoly.Model.GameTiles.*;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.Players.Token;

import java.util.ArrayList;

/**
 * This test Class focuses on and tests that every single possible strategy and process for every single tile
 */
public class StrategyTests {

    @Test
    public void testStrategy() {
        Strategy strategy = new Strategy() {
            @Override
            public void executeStrategy(Player humanPlayer) {

            }
        };
    }

    @Test
    public void testExecuteStrategy() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy strategy = new Strategy() {
            @Override
            public void executeStrategy(Player player) {
            }
        };
        HumanPlayer player = new HumanPlayer("Test Player", token);
        strategy.executeStrategy(player);
    }

    @Test
    public void testGoSpaceSalary() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy strategy = new GoSpace();
        HumanPlayer player = new HumanPlayer("Test Player", token);
        int initialBalance = player.getBalance();
        strategy.executeStrategy(player);
        int finalBalance = player.getBalance();
        assertEquals(initialBalance + 200, player.getBalance(), "Player's salary should be 1700.");
        System.out.println("Player's final balance: " + finalBalance);
    }

    @Test
    public void testGoSpaceSalaryMultipleTimes() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy strategy = new GoSpace();
        HumanPlayer player = new HumanPlayer("Test Player", token);
        int initialBalance = player.getBalance();
        strategy.executeStrategy(player);
        strategy.executeStrategy(player);
        int finalBalance = player.getBalance();
        assertEquals(finalBalance, player.getBalance(), "Player's salary should be 1900.");
        System.out.println("Player's final balance: " + finalBalance);
    }

    @Test
    public void testFreeParkingSpace() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy strategy = new FreeParkingSpace();
        HumanPlayer player = new HumanPlayer("Test Player", token);
        strategy.executeStrategy(player);
        assertEquals(1500, player.getBalance(), "Player's balance should be the same after landing on free parking space.");
    }

    @Test
    public void testGoToJailSpace() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy strategy = new GoToJailSpace();
        HumanPlayer player = new HumanPlayer("Test Player", token);
        strategy.executeStrategy(player);
        assertEquals(10, player.getPosition(), "Player should be in jail.");
    }

    @Test
    public void testJustVisitingJail() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy strategy = new JailSpace();
        HumanPlayer player = new HumanPlayer("Test Player", token);
        player.move(10);
        strategy.executeStrategy(player);
        assertEquals(10, player.getPosition(), "Player should be at jail space.");
        assertFalse(player.isInJail(), "Player should just be visiting jail.");
    }

    @Test
    public void testBeingInJail() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy goToJail = new GoToJailSpace();
        Strategy jail = new JailSpace();
        HumanPlayer player = new HumanPlayer("Test Player", token);
        goToJail.executeStrategy(player);
        jail.executeStrategy(player);
        assertEquals(10, player.getPosition(), "Player should be at jail space.");
        assertTrue(player.isInJail(), "Player should be in jail.");
    }

    @Test
    public void testIncomeTaxSpace() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy strategy = new IncomeTaxSpace();
        HumanPlayer player = new HumanPlayer("Test Player", token);
        int initialBalance = player.getBalance();
        strategy.executeStrategy(player);
        int finalBalance = player.getBalance();
        assertEquals(initialBalance - 200, player.getBalance(), "Player should pay $200 in income tax.");
        System.out.println("Player's final balance: " + finalBalance);
    }

    @Test
    public void testLuxuryTaxSpace() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy strategy = new LuxuryTaxSpace();
        HumanPlayer player = new HumanPlayer("Test Player", token);
        int initialBalance = player.getBalance();
        strategy.executeStrategy(player);
        int finalBalance = player.getBalance();
        assertEquals(initialBalance - 100, player.getBalance(), "Player should pay $100 in income tax.");
        System.out.println("Player's final balance: " + finalBalance);
    }

    @Test
    public void testChanceSpace() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy strategy = new ChanceSpace(new ChanceDeck());
        HumanPlayer player = new HumanPlayer("Test Player", token);
        strategy.executeStrategy(player);
        assertEquals(1500, player.getBalance(), "Player's balance should be the same after landing on chance space.");
    }

    @Test
    public void testCommunityChestSpace() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        Strategy strategy = new CommunityChestSpace(new CommunityChestDeck());
        HumanPlayer player = new HumanPlayer("Test Player", token);
        strategy.executeStrategy(player);
        assertEquals(1500, player.getBalance(), "Player's balance should be the same after landing on chance space.");
    }

    @Test
    public void testPlayerHasElectricCompany() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        ElectricCompanySpace strategy = new ElectricCompanySpace("Electric Company", 150, new ArrayList<>(), null, 0);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        try {
            player.purchaseProperty(strategy.getName(), strategy.getPrice());
        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        }
        strategy.executeStrategy(player);
        assertEquals(1350, player.getBalance(), "Player's balance should be at $150 due to buying property.");
    }

    @Test
    public void testPlayerDoesNotOwnElectricCompanyAndNoOwner() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        ElectricCompanySpace strategy = new ElectricCompanySpace("Electric Company", 150, new ArrayList<>(), null, 0);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        strategy.executeStrategy(player);
    }

    @Test
    public void testPlayerDoesNotOwnElectricCompanyButSomeoneElseDoes() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        ElectricCompanySpace strategy = new ElectricCompanySpace("Electric Company", 150, new ArrayList<>(), null, 0);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        HumanPlayer owner = new HumanPlayer("Someone Else", token);

        strategy.setOwner(String.valueOf(owner));
        strategy.executeStrategy(player);
    }

    @Test
    public void testPlayerSuccessfullyBuysElectricCompany() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        ElectricCompanySpace strategy = new ElectricCompanySpace("Electric Company", 150, new ArrayList<>(), null, 0);
        HumanPlayer player = new HumanPlayer("Test Player", token);

        strategy.executeStrategy(player);
        assertEquals(1350, player.getBalance(), "Player's balance should be at $150 due to buying property.");
        System.out.println("Player Balance: $" + player.getBalance());
    }

    @Test
    public void testNotEnoughMoneyForElectricCompany() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        ElectricCompanySpace strategy = new ElectricCompanySpace("Electric Company", 150, new ArrayList<>(), null, 0);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        player.subtractFromBalance(1400);
        try {
            player.purchaseProperty(strategy.getName(), strategy.getPrice());
        } catch (InsufficientFundsException e) {
            assertEquals("Insufficient funds to purchase Electric Company", e.getMessage(), "Exception should print if not enough money");
        }
    }

    @Test
    public void testPlayerHasWaterWorks() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        WaterWorksSpace strategy = new WaterWorksSpace("Water Works", 150, new ArrayList<>(), null, 0);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        try {
            player.purchaseProperty(strategy.getName(), strategy.getPrice());
        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        }
        strategy.executeStrategy(player);
        assertEquals(1350, player.getBalance(), "Player's balance should be at $150 due to buying property.");
    }
    @Test
    public void testPlayerDoesNotOwnWaterWorksAndNoOwner() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        WaterWorksSpace strategy = new WaterWorksSpace("Water Works", 150, new ArrayList<>(), null, 0);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        strategy.executeStrategy(player);
    }

    @Test
    public void testPlayerDoesNotOwnWaterWorksButSomeoneElseDoes() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        WaterWorksSpace strategy = new WaterWorksSpace("Water Works", 150, new ArrayList<>(), null, 0);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        HumanPlayer owner = new HumanPlayer("Someone Else", token);

        strategy.setOwner(String.valueOf(owner));
        strategy.executeStrategy(player);
    }

    @Test
    public void testPlayerSuccessfullyBuysWaterWorks() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        WaterWorksSpace strategy = new WaterWorksSpace("Water Works", 150, new ArrayList<>(), null, 0);
        HumanPlayer player = new HumanPlayer("Test Player", token);

        strategy.executeStrategy(player);
        assertEquals(1350, player.getBalance(), "Player's balance should be at $150 due to buying property.");
        System.out.println("Player Balance: $" + player.getBalance());
    }

    @Test
    public void testNotEnoughMoneyForWaterWorks() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        WaterWorksSpace strategy = new WaterWorksSpace("Water Works", 150, new ArrayList<>(), null, 0);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        player.subtractFromBalance(1400);
        try {
            player.purchaseProperty(strategy.getName(), strategy.getPrice());
        } catch (InsufficientFundsException e) {
            assertEquals("Insufficient funds to purchase Water Works", e.getMessage(), "Exception should print if not enough money");
        }
    }

    @Test
    public void testPlayerHasRailRoad() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        RailroadSpace strategy = new RailroadSpace("B&O Railroad", "", 200, new ArrayList<>(), RAILROAD, 100);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        try {
            player.purchaseProperty(strategy.getName(), strategy.getPrice());
        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        }
        strategy.executeStrategy(player);
        assertEquals(1300, player.getBalance(), "Player's balance should be at $150 due to buying property.");
    }

    @Test
    public void testPlayerDoesNotOwnRailRoadAndNoOwner() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        RailroadSpace strategy = new RailroadSpace("B&O Railroad", "", 200, new ArrayList<>(), RAILROAD, 100);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        strategy.executeStrategy(player);
    }

    @Test
    public void testPlayerDoesNotOwnRailRoadButSomeoneElseDoes() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        RailroadSpace strategy = new RailroadSpace("B&O Railroad", "", 200, new ArrayList<>(), RAILROAD, 100);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        HumanPlayer owner = new HumanPlayer("Someone Else", token);

        strategy.setOwner(String.valueOf(owner));
        strategy.executeStrategy(player);
    }

    @Test
    public void testPlayerSuccessfullyBuysRailRoad() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        RailroadSpace strategy = new RailroadSpace("B&O Railroad", "", 200, new ArrayList<>(), RAILROAD, 100);
        HumanPlayer player = new HumanPlayer("Test Player", token);

        strategy.executeStrategy(player);
        assertEquals(1300, player.getBalance(), "Player's balance should be at $150 due to buying property.");
        System.out.println("Player Balance: $" + player.getBalance());
    }

    @Test
    public void testNotEnoughMoneyForRailRoad() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        RailroadSpace strategy = new RailroadSpace("B&O Railroad", "", 200, new ArrayList<>(), RAILROAD, 100);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        player.subtractFromBalance(1400);
        try {
            player.purchaseProperty(strategy.getName(), strategy.getPrice());
        } catch (InsufficientFundsException e) {
            assertEquals("Insufficient funds to purchase B&O Railroad", e.getMessage(), "Exception should print if not enough money");
        }
    }

    @Test
    public void testPlayerHasPropertySpace() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        PropertySpace strategy = new PropertySpace("Mediterranean Avenue", "", 60, new ArrayList<>(), BROWN, 50,50,30);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        try {
            player.purchaseProperty(strategy.getName(), strategy.getPrice());
        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1440, player.getBalance(), "Player's balance should be at $150 due to buying property.");
    }

    @Test
    public void testPlayerDoesNotPropertySpaceAndNoOwner() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        PropertySpace strategy = new PropertySpace("Mediterranean Avenue", "", 60, new ArrayList<>(), BROWN, 50,50,30);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        strategy.executeStrategy(player);
    }

    @Test
    public void testPlayerDoesNotOwnPropertySpaceButSomeoneElseDoes() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        PropertySpace strategy = new PropertySpace("Mediterranean Avenue", "", 60, new ArrayList<>(), BROWN, 50,50,30);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        HumanPlayer owner = new HumanPlayer("Someone Else", token);

        strategy.setOwner(String.valueOf(owner));
        strategy.executeStrategy(player);
    }

    @Test
    public void testPlayerSuccessfullyBuysProperty() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        PropertySpace strategy = new PropertySpace("Mediterranean Avenue", "", 60, new ArrayList<>(), BROWN, 50,50,30);
        HumanPlayer player = new HumanPlayer("Test Player", token);

        strategy.executeStrategy(player);
        assertEquals(1440, player.getBalance(), "Player's balance should be at $150 due to buying property.");
        System.out.println("Player Balance: $" + player.getBalance());
    }

    @Test
    public void testNotEnoughMoneyForProperty() {
        Token token = new Token("Thimble", "TokensPNGs/Thimble.png");
        RailroadSpace strategy = new RailroadSpace("Mediterranean Avenue", "", 200, new ArrayList<>(), RAILROAD, 100);
        HumanPlayer player = new HumanPlayer("Test Player", token);
        player.subtractFromBalance(1400);
        try {
            player.purchaseProperty(strategy.getName(), strategy.getPrice());
        } catch (InsufficientFundsException e) {
            assertEquals("Insufficient funds to purchase Mediterranean Avenue", e.getMessage(), "Exception should print if not enough money");
        }
    }
}