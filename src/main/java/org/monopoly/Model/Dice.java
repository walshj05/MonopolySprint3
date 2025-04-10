package org.monopoly.Model;

import java.util.Random;


/**
 * @author crevelings
 * This class represents a pair of six-sided dice for the game
 * Implemented as a Singleton
 */
public class Dice {
    private static final int SIDES = 6;
    private Random random;
    protected boolean isDouble;
    private int numDoubles; // Tracks consecutive doubles in the current turn

    private static Dice instance;
    public Dice() {
        random = new Random(System.nanoTime());
        isDouble = false;
        numDoubles = 0;
    }

    /**
     * Get the singleton instance of the Dice class
     * @return the singleton instance of Dice
     * @author crevelings
     */
    public static Dice getInstance() {
        if (instance == null) {
            instance = new Dice();
        }
        return instance;
    }

    /**
     * Rolls the dice and returns the 2 dice that are stored in an array
     * Also tracks if doubles have been rolled
     * @return an array of 2 integers representing the rolled dice
     * @author crevelings
     */
    public int[] roll() {
        int die1 = random.nextInt(SIDES) + 1; // Random number between 1 and 6
        int die2 = random.nextInt(SIDES) + 1;
        isDouble = (die1 == die2); // Check if it's a double
        return new int[]{die1, die2};
    }

    /**
     * Checks if doubles have been rolled
     * @return true if doubles have been rolled, false otherwise
     * @author crevelings
     */
    public boolean isDouble() {
        return isDouble;
    }

    /**
     * Returns the number of doubles rolled in the current turn
     * @return the number of doubles rolled
     * @author crevelings
     */
    public int getNumDoubles() {
        return numDoubles;
    }

    /**
     * Increments the number of doubles rolled in the current turn
     * @author crevelings
     */
    public void incrementNumDoubles() {
        numDoubles++;
    }

    /**
     * Resets the number of doubles rolled in the current turn
     * @author crevelings
     */
    public void resetNumDoubles() {
        numDoubles = 0;
    }
}