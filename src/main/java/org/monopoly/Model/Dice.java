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
     */
    public int[] roll() {
        int die1 = random.nextInt(SIDES) + 1; // Random number between 1 and 6
        int die2 = random.nextInt(SIDES) + 1;
        isDouble = (die1 == die2); // Check if it's a double
        return new int[]{die1, die2};
    }

    /**
     * Checks if doubles have been rolled
     */
    public boolean isDouble() {
        return isDouble;
    }

    public int getNumDoubles() {
        return numDoubles;
    }

    public void incrementNumDoubles() {
        numDoubles++;
    }

    public void resetNumDoubles() {
        numDoubles = 0;
    }
}