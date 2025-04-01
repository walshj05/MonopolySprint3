package org.monopoly.Model.GameTiles;

import org.monopoly.Model.Players.Player;

/**
 * Represents the Jail Space element on the Game Board's Tiles.
 *
 * @author shifmans
 */
public class JailSpace extends GameTile {

    /**
     * Constructor to initialize a JailSpace.
     *
     * Developed by: shifmans
     */
    public JailSpace() {
        super("Jail Space", "");
    }

    /**
     * Shows the actions that occur after a player lands on element space.
     * @return Displays the actions for landing on element space.
     *
     * Developed by: shifmans
     */
    @Override
    public String landOn() {
        actions += displayJailInfo();
        return actions;
    }

    /**
     * Player is shown information about Jail after landing on element space.
     * @return Information on Jail Space.
     *
     * Developed by: shifmans
     */
    private String displayJailInfo() {
        return "Pay $50 or roll doubles to get out of jail";
    }

    /**
     * Executes the strategy for the JailSpace.
     * @author crevelings
     */
    @Override
    public void executeStrategy(Player player) {
        if (player.isInJail()) {
            System.out.println(player.getName() + " is in jail.");
        }
        else {
            System.out.println(player.getName() + " is just visiting jail.");
        }
    }
}
