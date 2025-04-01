package org.monopoly.Model.GameTiles;

import org.monopoly.Model.Players.Player;

/**
 * Represents the Free Parking Space element on the Game Board's Tiles.
 *
 * @author shifmans
 */
public class FreeParkingSpace extends GameTile {

    /**
     * Constructor to initialize a FreeParkingSpace.
     *
     * Developed by: shifmans
     */
    public FreeParkingSpace() {
        super("Free Parking Space", "");
    }

    /**
     * Shows the actions that occur after a player lands on element space.
     * @return Displays the actions for landing on element space.
     *
     * Developed by: shifmans
     */
    @Override
    public String landOn() {
        actions += doNothing();
        return actions;
    }

    /**
     * Player doesn't have to do anything after landing on element space.
     * @return Information on Free Parking Space.
     *
     * Developed by: shifmans
     */
    private String doNothing() {
        return "Take a rest, you don't have to do anything";
    }

    /**
     * Executes the strategy for the FreeParkingSpace.
     * @author crevelings
     */
    @Override
    public void executeStrategy(Player player) {
        System.out.println(doNothing());
    }
}
