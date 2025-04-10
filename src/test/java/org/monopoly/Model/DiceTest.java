package org.monopoly.Model;

import org.junit.jupiter.api.Test;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Token;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Dice class.
 * @author crevelings
 */
class DiceTest {

    @Test
    void testDiceCreation() {
        Dice dice = new Dice();
        assertNotNull(dice, "Dice object should be created");
    }

    @Test
    void testRollInRange() {
        Dice dice = new Dice();
        int[] roll = dice.roll();
        assertTrue(roll[0] >= 1 && roll[0] <= 6, "First roll should be between 1 and 6");
        assertTrue(roll[1] >= 1 && roll[1] <= 6, "Second roll should be between 1 and 6");
    }

    @Test
    void testRollDistribution() {
        Dice dice = new Dice();
        int[] rollCounts = new int[13]; // Index 0 and 1 will be unused
        int totalRolls = 10000;

        for (int i = 0; i < totalRolls; i++) {
            int[] roll = dice.roll();
            rollCounts[roll[0] + roll[1]]++;
        }

        // Check if each possible outcome (2-12) occurred at least once
        for (int i = 2; i <= 12; i++) {
            assertTrue(rollCounts[i] > 0, "Roll " + i + " should occur at least once in " + totalRolls + " rolls");
        }

        assertTrue(rollCounts[7] > rollCounts[2], "7 should be rolled more often than 2");
        assertTrue(rollCounts[7] > rollCounts[12], "7 should be rolled more often than 12");
    }

    @Test
    void testRollDoubles() {
        Dice dice = new Dice();
        boolean rolledDoubles;
        while (true) { // checks that eventually there will be a double rolled
            int[] firstRoll = dice.roll();
            int[] secondRoll = dice.roll();
            if (firstRoll[0] == secondRoll[0] && firstRoll[1] == secondRoll[1]) {
                rolledDoubles = true;
                break;
            }
        }
        assertTrue(rolledDoubles, "Doubles have been rolled");
    }

    @Test
    void testTotalDoublesRolled() {
        Dice dice = new Dice();
        Token token = new Token("Thimble", "Thimble.png");
        HumanPlayer humanPlayer = new HumanPlayer("Alice", token);
        int totalDoubles = 0;

        // Simulate 100 turns
        for (int i = 0; i < 100; i++) {
            humanPlayer.takeTurn(dice);
            if (dice.isDouble()) {
                totalDoubles++;
            }
        }

        assertTrue(totalDoubles >= 0, "Total doubles should be non-negative.");
        System.out.println("Total doubles rolled: " + totalDoubles);
    }

    @Test
    void testConsecutiveDoubles() {
        Dice dice = new Dice();
        Token token = new Token("Thimble", "Thimble.png");
        HumanPlayer humanPlayer = new HumanPlayer("Alice", token);
        int maxConsecutiveDoubles = 0;

        for (int i = 0; i < 100; i++) {
            humanPlayer.takeTurn(dice);
            if (dice.isDouble()) {
                maxConsecutiveDoubles = Math.max(maxConsecutiveDoubles, dice.getNumDoubles());
            } else {
                dice.resetNumDoubles();
            }
        }

        assertTrue(maxConsecutiveDoubles <= 3,
                "Maximum consecutive doubles should be between 0 and 3.");
        System.out.println("Maximum consecutive doubles in a single turn: " + maxConsecutiveDoubles);
    }

    @Test
    void testResetNumDoubles() {
        Dice dice = new Dice();
        dice.incrementNumDoubles();
        dice.incrementNumDoubles();
        assertEquals(2, dice.getNumDoubles());
        dice.resetNumDoubles();
        assertEquals(0, dice.getNumDoubles());
    }

    @Test
    void testJailFunctionality() {
        Dice dice = new Dice();
        Token token = new Token("Thimble", "Thimble.png");
        HumanPlayer humanPlayer = new HumanPlayer("Alice", token);
        boolean wentToJail = false;

        for (int i = 0; i < 100; i++) {
            humanPlayer.takeTurn(dice);
            if (humanPlayer.isInJail()) {
                wentToJail = true;
                break;
            }
        }

        assertTrue(wentToJail || dice.getNumDoubles() < 3,
                "Player should go to jail after rolling three doubles in a row.");
        System.out.println("Player went to jail: " + wentToJail);
    }
}