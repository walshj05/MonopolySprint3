package org.monopoly.Model.Players;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Exceptions.NoSuchPropertyException;
import org.monopoly.Model.Banker;
import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Cards.TitleDeedCards;
import org.monopoly.Model.Dice;
import org.monopoly.Model.GameBoard;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HumanPlayerTest {

    /**
     * Developed by: shifmans
     */
    @BeforeEach
    public void resetSingletons() {
        Banker.resetInstance();
        TitleDeedCards.resetInstance();
        GameBoard.resetInstance();
    }

    @Test
    public void testPlayerCreation() {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals("John Doe", humanPlayer.getName());
        assertEquals(1500, humanPlayer.getBalance());
        assertEquals(0, humanPlayer.getPosition());
    }

    @Test
    public void testPlayerBalance() {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.addToBalance(2000);
        assertEquals(3500, humanPlayer.getBalance());
    }

    @Test
    public void testPlayerName() {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals("John Doe", humanPlayer.getName());
    }

    @Test
    public void testPlayerSetPosition() {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.setPosition(5);
        assertEquals(5, humanPlayer.getPosition());
    }

    @Test
    void playerMoveWorksWhenNotInJail() {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.move(5);
        assertEquals(5, humanPlayer.getPosition());
    }

    @Test
    void playerDoesNotMoveWhenInJail() {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.goToJail(); // new pos in jail (10)
        assertEquals(10, humanPlayer.getPosition());
        humanPlayer.move(5);
        assertEquals(10, humanPlayer.getPosition());
    }

    @Test
    void playerCanGetOutOfJail() {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.goToJail(); // new pos in jail (10)
        assertEquals(10, humanPlayer.getPosition());
        humanPlayer.releaseFromJail();
        assertFalse(humanPlayer.isInJail());
        humanPlayer.move(5);
        assertEquals(15, humanPlayer.getPosition());
    }

    @Test
    void playerHasAGameToken () {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertNotNull(humanPlayer.getToken());
    }

    @Test
    void playerCanPurchaseProperty() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.purchaseProperty("Park Place", 350);
        assertTrue(humanPlayer.hasProperty("Park Place"));
        assertEquals(1150, humanPlayer.getBalance());
    }

    @Test
    void playerCannotPurchasePropertyIfInsufficientFunds() {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.subtractFromBalance(1400); // Set balance to 100
        try {
            humanPlayer.purchaseProperty("Park Place", 350);
        } catch (InsufficientFundsException e) {
            assertEquals("Insufficient funds to purchase Park Place", e.getMessage());
        }
        assertFalse(humanPlayer.hasProperty("Park Place"));
    }

    @Test
    void playerTaxedForMoreThanTheyCanAffordGoesBankrupt(){
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.subtractFromBalance(1400); // Set balance to 100
        humanPlayer.subtractFromBalance(2000); // Taxed 2000
        assertTrue(humanPlayer.isBankrupt());
    }

    @Test
    void playerCanAddAndRemoveCardsFromTheirHand(){
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.addCard("Get Out of Jail Free");
        assertTrue(humanPlayer.hasCard("Get Out of Jail Free"));
        humanPlayer.removeCard("Get Out of Jail Free");
        assertFalse(humanPlayer.hasCard("Get Out of Jail Free"));
    }

    @Test
    void playerToStringMethodWorks(){
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals("John Doe (Token: John Doe)", humanPlayer.toString());
    }

    @Test
    void testPlayerCanSellProperty() throws InsufficientFundsException, NoSuchPropertyException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.purchaseProperty("Park Place", 350);
        humanPlayer.sellProperty("Park Place", 350);
        assertFalse(humanPlayer.hasProperty("Park Place"));
        assertEquals(1500, humanPlayer.getBalance());
    }

    @Test
    void mortgagePropertyWorks() throws InsufficientFundsException, NoSuchPropertyException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.purchaseProperty("Park Place", 350);
        humanPlayer.mortgageProperty("Park Place", 175);
        assertFalse(humanPlayer.hasProperty("Park Place"));
        assertEquals(1325, humanPlayer.getBalance());
    }

    @Test
    void playerTakingTurnMovesPlayer() {
        Dice dice = new Dice();
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(0, humanPlayer.getPosition());
        humanPlayer.takeTurn(dice);
        assertNotEquals(0, humanPlayer.getPosition());
    }

    @Test
    void testPlayerInJailCannotMove(){
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.goToJail();
        assertEquals(10, humanPlayer.getPosition());
        humanPlayer.takeTurn(new Dice());
        assertEquals(10, humanPlayer.getPosition());
    }

    @Test
    void playerCanBeInJailForMultipleTurns(){
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.goToJail();
        assertEquals(10, humanPlayer.getPosition());
        humanPlayer.takeTurn(new Dice());
        humanPlayer.incrementJailTurns();
        assertEquals(10, humanPlayer.getPosition());
        humanPlayer.incrementJailTurns();
        assertEquals(10, humanPlayer.getPosition());
        assertEquals(2, humanPlayer.getJailTurns());
        humanPlayer.resetJailTurns();
        assertEquals(0, humanPlayer.getJailTurns());
    }

    /*
    Add a test for hasMonopoly
     */
    @Test
    void testPlayerHasMonopoly() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.purchaseProperty("Park Place", 350);
        humanPlayer.purchaseProperty("Boardwalk", 400);
        assertTrue(humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerBuyOneHouseNoMonopoly() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        assertEquals(1150, humanPlayer.getBalance());
        assertTrue(humanPlayer.hasProperty("Park Place"));
        assertFalse(humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE));
        assertThrows(RuntimeException.class, () -> humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200));
        assertEquals(1150, humanPlayer.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerBuyOneHouseNoMoney() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        assertEquals(1150, humanPlayer.getBalance());

        // Take player money for testing purposes
        humanPlayer.subtractFromBalance(1000);

        assertThrows(InsufficientFundsException.class, () -> humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerBuyHouseUnownedProperty() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(List.of(), humanPlayer.getPropertiesOwned());
        assertThrows(RuntimeException.class, () -> humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200));

        humanPlayer.purchaseProperty("Park Place", 350);
        assertEquals(1150, humanPlayer.getBalance());
        assertThrows(RuntimeException.class, () -> humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerBuyOneHouse() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        assertEquals(1150, humanPlayer.getBalance());
        assertTrue(humanPlayer.hasProperty("Park Place"));
        assertEquals(List.of("Park Place"), humanPlayer.getPropertiesOwned());
        assertFalse(humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE));
        assertEquals(0, humanPlayer.getMonopolies().size());

        humanPlayer.purchaseProperty("Boardwalk", 400);
        assertEquals(750, humanPlayer.getBalance());
        assertTrue(humanPlayer.hasProperty("Boardwalk"));
        assertEquals(Arrays.asList("Park Place", "Boardwalk"), humanPlayer.getPropertiesOwned());
        assertTrue(humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE));
        assertEquals(1, humanPlayer.getMonopolies().size());

        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        assertEquals(550, humanPlayer.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testCheckEvenBuildRule() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        assertEquals(1150, humanPlayer.getBalance());
        assertTrue(humanPlayer.hasProperty("Park Place"));
        assertEquals(List.of("Park Place"), humanPlayer.getPropertiesOwned());
        assertFalse(humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE));
        assertEquals(0, humanPlayer.getMonopolies().size());

        humanPlayer.purchaseProperty("Boardwalk", 400);
        assertEquals(750, humanPlayer.getBalance());
        assertTrue(humanPlayer.hasProperty("Boardwalk"));
        assertEquals(Arrays.asList("Park Place", "Boardwalk"), humanPlayer.getPropertiesOwned());
        assertTrue(humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE));
        assertEquals(1, humanPlayer.getMonopolies().size());

        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        assertEquals(550, humanPlayer.getBalance());

        assertThrows(RuntimeException.class, () -> humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerOverBuyHouses() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        // Give player money for testing purposes
        humanPlayer.addToBalance(10000);
        assertEquals(11500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        humanPlayer.purchaseProperty("Boardwalk", 400);
        humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(10750, humanPlayer.getBalance());

        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        assertEquals(9150, humanPlayer.getBalance());

        assertThrows(RuntimeException.class, () -> humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200));
        assertThrows(RuntimeException.class, () -> humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200));
    }

    /**
     * Developed by: shifmans
     */
    @Test void testPlayerSellOneHouse() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        assertTrue(humanPlayer.hasProperty("Park Place"));
        assertFalse(humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE));

        humanPlayer.purchaseProperty("Boardwalk", 400);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        assertEquals(550, humanPlayer.getBalance());

        humanPlayer.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        assertEquals(650, humanPlayer.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testCheckEvenSellRule() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        assertTrue(humanPlayer.hasProperty("Park Place"));
        assertFalse(humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE));

        humanPlayer.purchaseProperty("Boardwalk", 400);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        assertEquals(550, humanPlayer.getBalance());

        humanPlayer.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        assertEquals(650, humanPlayer.getBalance());

        assertThrows(RuntimeException.class, () -> humanPlayer.sellHouse("Park Place", ColorGroup.DARK_BLUE));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerOverSellHouses() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        // Give player money for testing purposes
        humanPlayer.addToBalance(10000);
        assertEquals(11500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        humanPlayer.purchaseProperty("Boardwalk", 400);
        humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(10750, humanPlayer.getBalance());

        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        assertEquals(9150, humanPlayer.getBalance());

        humanPlayer.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        humanPlayer.sellHouse("Boardwalk", ColorGroup.DARK_BLUE);
        humanPlayer.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        humanPlayer.sellHouse("Boardwalk", ColorGroup.DARK_BLUE);
        humanPlayer.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        humanPlayer.sellHouse("Boardwalk", ColorGroup.DARK_BLUE);
        humanPlayer.sellHouse("Park Place", ColorGroup.DARK_BLUE);
        humanPlayer.sellHouse("Boardwalk", ColorGroup.DARK_BLUE);

        assertEquals(9950, humanPlayer.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerSellHouseUnownedProperty() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(List.of(), humanPlayer.getPropertiesOwned());
        assertThrows(RuntimeException.class, () -> humanPlayer.sellHouse("Park Place", ColorGroup.DARK_BLUE));

        humanPlayer.purchaseProperty("Park Place", 350);
        assertEquals(1150, humanPlayer.getBalance());
        assertThrows(RuntimeException.class, () -> humanPlayer.sellHouse("Boardwalk", ColorGroup.DARK_BLUE));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerBuyHotel() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        // Give player money for testing purposes
        humanPlayer.addToBalance(10000);
        assertEquals(11500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        humanPlayer.purchaseProperty("Boardwalk", 400);
        humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(10750, humanPlayer.getBalance());

        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        assertEquals(9150, humanPlayer.getBalance());

        humanPlayer.buyHotel("Park Place", ColorGroup.DARK_BLUE, 200);
        assertEquals(8950, humanPlayer.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerBuyHotelNoMoney() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        // Give player money for testing purposes
        humanPlayer.addToBalance(900);
        assertEquals(2400, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        humanPlayer.purchaseProperty("Boardwalk", 400);
        humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(1650, humanPlayer.getBalance());

        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        assertEquals(50, humanPlayer.getBalance());

        assertThrows(InsufficientFundsException.class, () -> humanPlayer.buyHotel("Park Place", ColorGroup.DARK_BLUE, 200));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerBuyHotelUnownedProperty() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        // Give player money for testing purposes
        humanPlayer.addToBalance(1500);
        assertEquals(3000, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        humanPlayer.purchaseProperty("Boardwalk", 400);
        humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(2250, humanPlayer.getBalance());

        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        assertEquals(650, humanPlayer.getBalance());

        assertThrows(RuntimeException.class, () -> humanPlayer.buyHotel("Short Line Railroad", ColorGroup.DARK_BLUE, 200));
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerSellHotel() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        // Give player money for testing purposes
        humanPlayer.addToBalance(10000);
        assertEquals(11500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        humanPlayer.purchaseProperty("Boardwalk", 400);
        humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(10750, humanPlayer.getBalance());

        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        assertEquals(9150, humanPlayer.getBalance());

        humanPlayer.buyHotel("Park Place", ColorGroup.DARK_BLUE, 200);
        assertEquals(8950, humanPlayer.getBalance());

        humanPlayer.sellHotel("Park Place", ColorGroup.DARK_BLUE);
        assertEquals(9050, humanPlayer.getBalance());
    }

    /**
     * Developed by: shifmans
     */
    @Test
    public void testPlayerSellHotelUnownedProperty() throws InsufficientFundsException {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        assertEquals(1500, humanPlayer.getBalance());

        // Give player money for testing purposes
        humanPlayer.addToBalance(10000);
        assertEquals(11500, humanPlayer.getBalance());

        humanPlayer.purchaseProperty("Park Place", 350);
        humanPlayer.purchaseProperty("Boardwalk", 400);
        humanPlayer.hasMonopoly(ColorGroup.DARK_BLUE);
        assertEquals(10750, humanPlayer.getBalance());

        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Park Place", ColorGroup.DARK_BLUE, 200);
        humanPlayer.buyHouse("Boardwalk", ColorGroup.DARK_BLUE, 200);
        assertEquals(9150, humanPlayer.getBalance());

        humanPlayer.buyHotel("Park Place", ColorGroup.DARK_BLUE, 200);
        assertEquals(8950, humanPlayer.getBalance());

        assertThrows(RuntimeException.class, () -> humanPlayer.sellHotel("Short Line Railroad", ColorGroup.DARK_BLUE));
    }

    /**
     * Test to see if the player's position rolls over once it reaches 39
     */
    @Test
    public void testPlayerPositionRollOver() {
        HumanPlayer humanPlayer = new HumanPlayer("John Doe", new Token( "John Doe","BattleShip.png"));
        humanPlayer.setPosition(39);
        humanPlayer.move(2);
        assertEquals(1, humanPlayer.getPosition());

        humanPlayer.setPosition(38);
        humanPlayer.move(10);
        assertEquals(8, humanPlayer.getPosition());
    }

}
