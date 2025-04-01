package org.monopoly.Model.GameTiles;

import org.monopoly.Model.Players.Player;

/**
 * Represents the Luxury Tax Space element on the Game Board's Tiles.
 *
 * @author shifmans
 */
public class LuxuryTaxSpace extends GameTile {

    /**
     * Constructor to initialize a LuxuryTaxSpace.
     *
     * Developed by: shifmans
     */
    public LuxuryTaxSpace() {
        super("Luxury Tax Space", "");
    }

    /**
     * Shows the actions that occur after a player lands on element space.
     * @return Displays the actions for landing on element space.
     *
     * Developed by: shifmans
     */
    @Override
    public String landOn() {
        actions += payTax();
        return actions;
    }

    /**
     * Player pays $100 tax after landing on element space.
     * @return Instructions for paying tax.
     *
     * Developed by: shifmans
     */
    private String payTax() {
        return "Pay $100 in Luxury Tax!";
    }

    /**
     * Executes the strategy for the Luxury Tax Space.
     * @author crevelings
     */
    @Override
    public void executeStrategy(Player player) {
        player.subtractFromBalance(100);
        System.out.println(payTax());
    }
}
