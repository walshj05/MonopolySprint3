package org.monopoly.Model.Players;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.monopoly.Exceptions.BankruptcyException;
import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Exceptions.NoSuchPropertyException;
import org.monopoly.Model.Banker;
import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Cards.TitleDeedCards;
import org.monopoly.Model.Dice;
import org.monopoly.Model.GameBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ComputerPlayer class.
 *
 * @author shifmans
 */
public class ComputerPlayerTests {

    /**
     * Developed by: shifmans
     */
    @BeforeEach
    public void resetSingletons() {
        Banker.resetInstance();
        TitleDeedCards.resetInstance();
        GameBoard.resetInstance();
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerConstructor() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));

        assertEquals("CPU", cpu.getName());
        assertEquals(1500, cpu.getBalance());
        assertEquals(0, cpu.getPosition());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerHasAGameToken () {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertNotNull(cpu.getToken());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerBalance() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        cpu.addToBalance(2000);
        assertEquals(3500, cpu.getBalance());

        cpu.subtractFromBalance(1000);
        assertEquals(2500, cpu.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerSetPosition() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        cpu.setPosition(5);
        assertEquals(5, cpu.getPosition());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerMoveWorksWhenNotInJail() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        cpu.move(5);
        assertEquals(5, cpu.getPosition());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerDoesNotMoveWhenInJail() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        cpu.goToJail();
        assertEquals(10, cpu.getPosition());
        assertTrue(cpu.isInJail());

        cpu.move(5);
        assertEquals(10, cpu.getPosition());
        assertTrue(cpu.isInJail());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerGetsOutOfJail() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        cpu.goToJail();
        assertEquals(10, cpu.getPosition());
        assertTrue(cpu.isInJail());

        cpu.releaseFromJail();
        assertEquals(10, cpu.getPosition());
        assertFalse(cpu.isInJail());

        cpu.move(5);
        assertEquals(15, cpu.getPosition());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerTakeTurn() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals(0, cpu.getPosition());
        cpu.takeTurn(new Dice());
        assertTrue((cpu.getPosition() > 0) && (cpu.getPosition() <= 12));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerPurchaseProperty() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals(1500, cpu.getBalance());
        assertEquals(0, cpu.getPropertiesOwned().size());
        assertEquals(List.of(), cpu.getPropertiesOwned());
        assertFalse(cpu.hasProperty("Park Place"));

        while (cpu.getPropertiesOwned().size() != 1) {
            cpu.purchaseProperty("Park Place", 100);
        }

        assertEquals(1400, cpu.getBalance());
        assertEquals(1, cpu.getPropertiesOwned().size());
        assertEquals(List.of("Park Place"), cpu.getPropertiesOwned());
        assertTrue(cpu.hasProperty("Park Place"));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerHasMonopoly() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals(1500, cpu.getBalance());
        assertFalse(cpu.hasMonopoly(ColorGroup.DARK_BLUE));
        assertEquals(0, cpu.getPropertiesOwned().size());
        assertEquals(List.of(), cpu.getPropertiesOwned());
        assertFalse(cpu.hasProperty("Park Place"));
        assertFalse(cpu.hasProperty("Boardwalk"));

        while (cpu.getPropertiesOwned().size() != 1) {
            cpu.purchaseProperty("Park Place", 350);
        }

        while (cpu.getPropertiesOwned().size() != 2) {
            cpu.purchaseProperty("Boardwalk", 400);
        }

        assertEquals(750, cpu.getBalance());
        assertEquals(2, cpu.getPropertiesOwned().size());
        assertEquals(List.of("Park Place", "Boardwalk"), cpu.getPropertiesOwned());
        assertTrue(cpu.hasMonopoly(ColorGroup.DARK_BLUE));
        assertTrue(cpu.hasProperty("Park Place"));
        assertTrue(cpu.hasProperty("Boardwalk"));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerMortgageProperty() throws InsufficientFundsException, NoSuchPropertyException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals(1500, cpu.getBalance());
        assertEquals(List.of(), cpu.getPropertiesOwned());
        assertEquals(List.of(), cpu.getPropertiesMortgaged());

        while (cpu.getPropertiesOwned().size() != 1) {
            cpu.purchaseProperty("Park Place", 100);
        }

        assertEquals(1400, cpu.getBalance());
        assertEquals(1, cpu.getPropertiesOwned().size());
        assertEquals(List.of("Park Place"), cpu.getPropertiesOwned());

        while (cpu.getPropertiesMortgaged().size() != 1) {
            cpu.mortgageProperty("Park Place", 50);
        }

        assertEquals(1450, cpu.getBalance());
        assertEquals(0, cpu.getPropertiesOwned().size());
        assertEquals(List.of("Park Place"), cpu.getPropertiesMortgaged());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerMortgagePropertyUnknown() throws InsufficientFundsException, NoSuchPropertyException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals(1500, cpu.getBalance());
        assertEquals(List.of(), cpu.getPropertiesOwned());
        assertEquals(List.of(), cpu.getPropertiesMortgaged());

        assertThrows(NoSuchPropertyException.class, () -> {
            while (cpu.getPropertiesMortgaged().size() != 1) {
                cpu.mortgageProperty("Park Place", 50);
            }
        });
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerUnmortgageProperty() throws InsufficientFundsException, NoSuchPropertyException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals(1500, cpu.getBalance());
        assertEquals(List.of(), cpu.getPropertiesOwned());
        assertEquals(List.of(), cpu.getPropertiesMortgaged());

        while (cpu.getPropertiesOwned().size() != 1) {
            cpu.purchaseProperty("Park Place", 100);
        }

        assertEquals(1400, cpu.getBalance());
        assertEquals(1, cpu.getPropertiesOwned().size());
        assertEquals(List.of("Park Place"), cpu.getPropertiesOwned());

        while (cpu.getPropertiesMortgaged().size() != 1) {
            cpu.mortgageProperty("Park Place", 50);
        }

        assertEquals(1450, cpu.getBalance());
        assertEquals(0, cpu.getPropertiesOwned().size());
        assertEquals(List.of("Park Place"), cpu.getPropertiesMortgaged());

        while (cpu.getPropertiesMortgaged().size() == 1) {
            cpu.unmortgageProperty("Park Place", 50);
        }

        assertEquals(1400, cpu.getBalance());
        assertEquals(List.of("Park Place"), cpu.getPropertiesOwned());
        assertEquals(0, cpu.getPropertiesMortgaged().size());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerSellProperty() throws InsufficientFundsException, NoSuchPropertyException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals(1500, cpu.getBalance());
        assertEquals(0, cpu.getPropertiesOwned().size());
        assertEquals(List.of(), cpu.getPropertiesOwned());

        while (cpu.getPropertiesOwned().size() != 1) {
            cpu.purchaseProperty("Park Place", 350);
        }

        assertEquals(1150, cpu.getBalance());
        assertEquals(1, cpu.getPropertiesOwned().size());
        assertEquals(List.of("Park Place"), cpu.getPropertiesOwned());

        while (cpu.getPropertiesOwned().size() == 1) {
            cpu.sellProperty("Park Place", 350);
        }

        assertEquals(1500, cpu.getBalance());
        assertEquals(0, cpu.getPropertiesOwned().size());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerCard() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertFalse(cpu.hasCard("Get Out of Jail Free"));

        cpu.addCard("Get Out of Jail Free");
        assertTrue(cpu.hasCard("Get Out of Jail Free"));

        cpu.removeCard("Get Out of Jail Free");
        assertFalse(cpu.hasCard("Get Out of Jail Free"));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerIsBankrupt() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals(1500, cpu.getBalance());
        assertFalse(cpu.isBankrupt());

        cpu.subtractFromBalance(1500);
        assertEquals(0, cpu.getBalance());
        assertTrue(cpu.isBankrupt());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerBuyOneHouseNoMonopoly() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        while (cpu.getPropertiesOwned().size() != 1) {
            cpu.purchaseProperty("Park Place", 350);
        }

        assertEquals(1150, cpu.getBalance());
        assertTrue(cpu.hasProperty("Park Place"));
        assertFalse(cpu.hasMonopoly(ColorGroup.DARK_BLUE));
        assertThrows(RuntimeException.class, () -> {
            while (cpu.getPropertiesOwned().size() == 1) {
                cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
            }
        });
        assertEquals(1150, cpu.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerBuyOneHouseNoMoney() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        while (cpu.getPropertiesOwned().size() != 1) {
            cpu.purchaseProperty("Park Place", 350);
        }

        assertEquals(1150, cpu.getBalance());

        // Take player money for testing purposes
        cpu.subtractFromBalance(1000);

        assertThrows(InsufficientFundsException.class, () -> {
            while (cpu.getPropertiesOwned().size() == 1) {
                cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
            }
        });

        assertEquals(150, cpu.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerBuyHouseUnownedProperty() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(List.of(), cpu.getPropertiesOwned());
        assertThrows(RuntimeException.class, () -> {
            while (cpu.getPropertiesOwned().size() != 1) {
                cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
            }
        });

        while (cpu.getPropertiesOwned().size() != 1) {
            cpu.purchaseProperty("Park Place", 350);
        }

        assertEquals(1150, cpu.getBalance());
        assertThrows(RuntimeException.class, () -> {
            while (cpu.getPropertiesOwned().size() == 1) {
                cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 400);
            }
        });
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerBuyOneHouse() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        assertEquals(1150, cpu.getBalance());
        assertTrue(cpu.hasProperty("Park Place"));
        assertEquals(List.of("Park Place"), cpu.getPropertiesOwned());
        assertFalse(cpu.hasMonopoly(ColorGroup.DARK_BLUE));
        assertEquals(0, cpu.getMonopolies().size());

        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        assertEquals(750, cpu.getBalance());
        assertTrue(cpu.hasProperty("Boardwalk"));
        assertEquals(List.of("Park Place", "Boardwalk"), cpu.getPropertiesOwned());
        assertTrue(cpu.hasMonopoly(ColorGroup.DARK_BLUE));
        assertEquals(1, cpu.getMonopolies().size());

        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(550, cpu.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerCheckEvenBuildRule() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        assertEquals(1150, cpu.getBalance());
        assertTrue(cpu.hasProperty("Park Place"));
        assertEquals(List.of("Park Place"), cpu.getPropertiesOwned());
        assertFalse(cpu.hasMonopoly(ColorGroup.DARK_BLUE));
        assertEquals(0, cpu.getMonopolies().size());

        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        assertEquals(750, cpu.getBalance());
        assertTrue(cpu.hasProperty("Boardwalk"));
        assertEquals(List.of("Park Place", "Boardwalk"), cpu.getPropertiesOwned());
        assertTrue(cpu.hasMonopoly(ColorGroup.DARK_BLUE));
        assertEquals(1, cpu.getMonopolies().size());

        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(550, cpu.getBalance());

        assertThrows(RuntimeException.class, () -> {
            while (cpu.getNumHouses() == 1) {
                cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
            }
        });
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerOverBuyHouses() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        // Give player money for testing purposes
        cpu.addToBalance(10000);
        assertEquals(11500, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        cpu.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(10750, cpu.getBalance());

        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 2) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 3) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 4) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 5) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 6) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 7) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 8) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(8, cpu.getNumHouses());
        assertEquals(9150, cpu.getBalance());

        assertThrows(RuntimeException.class, () -> {
            while (cpu.getNumHouses() == 8) {
                cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
            }
        });
        assertThrows(RuntimeException.class, () -> {
            while (cpu.getNumHouses() == 8) {
                cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
            }
        });
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerSellOneHouse() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        assertTrue(cpu.hasProperty("Park Place"));
        assertFalse(cpu.hasMonopoly(ColorGroup.DARK_BLUE));

        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(550, cpu.getBalance());

        while (cpu.getNumHouses() == 1) {
            cpu.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        }
        assertEquals(650, cpu.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerCheckEvenSellRule() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        assertTrue(cpu.hasProperty("Park Place"));
        assertFalse(cpu.hasMonopoly(ColorGroup.DARK_BLUE));

        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(550, cpu.getBalance());

        while (cpu.getNumHouses() == 1) {
            cpu.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        }
        assertEquals(650, cpu.getBalance());

        assertThrows(RuntimeException.class, () -> {
            while (cpu.getNumHouses() == 0) {
                cpu.sellHouse("Park Place", ColorGroup.DARK_BLUE);
            }
        });
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerOverSellHouses() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        cpu.addToBalance(10000);
        assertEquals(11500, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        cpu.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(10750, cpu.getBalance());

        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 2) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 3) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 4) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 5) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 6) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 7) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 8) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(9150, cpu.getBalance());
        assertEquals(8, cpu.getNumHouses());

        while (cpu.getNumHouses() == 8) {
            cpu.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        }
        while (cpu.getNumHouses() == 7) {
            cpu.sellHouse("Boardwalk", ColorGroup.DARK_BLUE);
        }
        while (cpu.getNumHouses() == 6) {
            cpu.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        }
        while (cpu.getNumHouses() == 5) {
            cpu.sellHouse("Boardwalk", ColorGroup.DARK_BLUE);
        }
        while (cpu.getNumHouses() == 4) {
            cpu.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        }
        while (cpu.getNumHouses() == 3) {
            cpu.sellHouse("Boardwalk", ColorGroup.DARK_BLUE);
        }
        while (cpu.getNumHouses() == 2) {
            cpu.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        }
        while (cpu.getNumHouses() == 1) {
            cpu.sellHouse("Boardwalk", ColorGroup.DARK_BLUE);
        }
        assertEquals(9950, cpu.getBalance());

        assertThrows(RuntimeException.class, () -> {
            while (cpu.getNumHouses() == 0) {
                cpu.sellHouse("Park Place", ColorGroup.DARK_BLUE);
            }
        });
        assertThrows(RuntimeException.class, () -> {
            while (cpu.getNumHouses() == 0) {
                cpu.sellHouse("Boardwalk", ColorGroup.DARK_BLUE);
            }
        });
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerSellHouseUnownedProperty() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(List.of(), cpu.getPropertiesOwned());
        assertThrows(RuntimeException.class, () -> {
            while (cpu.getNumHouses() == 0) {
                cpu.sellHouse("Park Place", ColorGroup.DARK_BLUE);
            }
        });

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        assertEquals(1150, cpu.getBalance());
        assertThrows(RuntimeException.class, () -> {
            while (cpu.getNumHouses() == 0) {
                cpu.sellHouse("Boardwalk", ColorGroup.DARK_BLUE);
            }
        });
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerBuyHotel() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        // Give player money for testing purposes
        cpu.addToBalance(10000);
        assertEquals(11500, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        cpu.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(10750, cpu.getBalance());

        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 2) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 3) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 4) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 5) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 6) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 7) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 8) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(9150, cpu.getBalance());
        assertEquals(8, cpu.getNumHouses());

        while (cpu.getNumHotels() != 1) {
            cpu.buyHotel("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(8950, cpu.getBalance());
        assertEquals(1, cpu.getNumHotels());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerBuyHotelNoMoney() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        // Give player money for testing purposes
        cpu.addToBalance(900);
        assertEquals(2400, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        cpu.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(1650, cpu.getBalance());

        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 2) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 3) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 4) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 5) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 6) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 7) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 8) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(50, cpu.getBalance());

        assertThrows(InsufficientFundsException.class, () -> {
            while (cpu.getNumHotels() != 1) {
                cpu.buyHotel("Park Place", ColorGroup.DARK_BLUE, 200);
            }});
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerBuyHotelUnownedProperty() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        // Give player money for testing purposes
        cpu.addToBalance(1500);
        assertEquals(3000, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        cpu.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(2250, cpu.getBalance());

        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 2) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 3) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 4) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 5) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 6) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 7) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 8) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(650, cpu.getBalance());

        assertThrows(RuntimeException.class, () -> {
            while (cpu.getNumHotels() != 1) {
                cpu.buyHotel("Short Line Railroad", ColorGroup.DARK_BLUE, 200);
            }});
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerSellHotel() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        // Give player money for testing purposes
        cpu.addToBalance(10000);
        assertEquals(11500, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        cpu.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(10750, cpu.getBalance());

        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 2) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 3) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 4) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 5) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 6) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 7) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 8) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(9150, cpu.getBalance());

        while (cpu.getNumHotels() != 1) {
            cpu.buyHotel("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(8950, cpu.getBalance());

        while (cpu.getNumHotels() == 1) {
            cpu.sellHotel("Park Place", ColorGroup.DARK_BLUE);
        }
        assertEquals(9050, cpu.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerSellHotelUnownedProperty() throws InsufficientFundsException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        // Give player money for testing purposes
        cpu.addToBalance(10000);
        assertEquals(11500, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        while (!cpu.hasProperty("Boardwalk")) {
            cpu.purchaseProperty("Boardwalk", 400);
        }
        cpu.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(10750, cpu.getBalance());

        while (cpu.getNumHouses() != 1) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 2) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 3) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 4) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 5) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 6) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 7) {
            cpu.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        while (cpu.getNumHouses() != 8) {
            cpu.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(9150, cpu.getBalance());

        while (cpu.getNumHotels() != 1) {
            cpu.buyHotel("Park Place", ColorGroup.DARK_BLUE, 200);
        }
        assertEquals(8950, cpu.getBalance());

        assertThrows(RuntimeException.class, () -> {
            while (cpu.getNumHotels() == 1) {
                cpu.sellHotel("Short Line Railroad", ColorGroup.DARK_BLUE);
            }});
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerToString() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals("CPU (Token: CPU)", cpu.toString());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerJailTurns() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));
        assertEquals(0, cpu.getJailTurns());

        cpu.incrementJailTurns();
        assertEquals(1, cpu.getJailTurns());

        cpu.resetJailTurns();
        assertEquals(0, cpu.getJailTurns());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerRunOdds() {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","BattleShip.png"));

        assertTrue(cpu.runOdds(1));
        assertFalse(cpu.runOdds(0));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testComputerPlayerHandleLandingPayRent() {
        HumanPlayer humanPlayer = new HumanPlayer("Player 1", new Token( "Player 1","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());
        assertFalse(humanPlayer.hasProperty("Mediterranean Avenue"));

        humanPlayer.move(1);
        GameBoard.getInstance().executeStrategyType(humanPlayer, "tile");

        assertEquals(1440, humanPlayer.getBalance());
        assertTrue(humanPlayer.hasProperty("Mediterranean Avenue"));

        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token( "CPU","TopHat.png"));
        assertEquals(1500, cpu.getBalance());
        assertEquals(0, cpu.getPosition());
        assertFalse(cpu.hasProperty("Mediterranean Avenue"));

        cpu.move(1);
        assertEquals(1, cpu.getPosition());

        TitleDeedCards tiles = TitleDeedCards.getInstance();
        System.out.println(tiles.getProperty("Mediterranean Avenue").getOwner());

        ArrayList<Integer> rentPrices = new ArrayList<>(Arrays.asList(2, 10, 30, 90, 160, 250));
        cpu.handleLanding(rentPrices);

        assertFalse(cpu.hasProperty("Mediterranean Avenue"));
        assertEquals(1498, cpu.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testMortgageAssetsToRaiseFunds() throws InsufficientFundsException, BankruptcyException {
        ComputerPlayer cpu = new ComputerPlayer("CPU", new Token("CPU", "BattleShip.png"));
        assertEquals(1500, cpu.getBalance());

        while (!cpu.hasProperty("Park Place")) {
            cpu.purchaseProperty("Park Place", 350);
        }
        assertEquals(1150, cpu.getBalance());
        assertEquals(List.of(), cpu.getPropertiesMortgaged());
        assertEquals(List.of("Park Place"), cpu.getPropertiesOwned());


        while (cpu.hasProperty("Park Place")) {
            cpu.mortgageAssetsToRaiseFunds(100);
        }
        // Mortgage value is 175
        assertEquals(1325, cpu.getBalance());
        assertEquals(List.of("Park Place"), cpu.getPropertiesMortgaged());
        assertEquals(List.of(), cpu.getPropertiesOwned());
    }
}
