package org.monopoly.Model.Players;

import org.junit.jupiter.api.Test;
import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Exceptions.NoSuchPropertyException;
import org.monopoly.Model.Dice;

import static org.junit.jupiter.api.Assertions.*;

public class HumanPlayerTest {

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
        assertTrue(humanPlayer.hasMonopoly("darkBlue"));
    }
}
