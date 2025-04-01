package org.monopoly.Model.GameTiles;

import org.monopoly.Model.Players.Player;

/**
 * Represents the Go-To Jail Space element on the Game Board's Tiles.
 *
 * @author shifmans
 */
public class GoToJailSpace extends GameTile {

    /**
     * Constructor to initialize a GoToJailSpace.
     *
     * Developed by: shifmans
     */
    public GoToJailSpace() {
        super("Go To Jail Space", "");
    }

    /**
     * Shows the actions that occur after a player lands on element space.
     * @return Displays the actions for landing on element space.
     *
     * Developed by: shifmans
     */
    @Override
    public String landOn() {
        actions += goToJail();
        return actions;
    }

    /**
     * Executes the strategy for the GoToJailSpace.
     * @author crevelings
     */
    /**
     * Player goes directly to Jail after landing on element space.
     * @return Instructions for going directly to Jail.
     *
     * Developed by: shifmans
     */
    private String goToJail() {
        return "Go directly to Jail";
    }

    @Override
    public void executeStrategy(Player player) {
        player.goToJail();
    }
}
