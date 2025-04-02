package org.monopoly.Model.GameTiles;

import org.monopoly.Model.Cards.ChanceDeck;
import org.monopoly.Model.Players.Player;

/**
 * Represents the Chance Space element on the Game Board's Tiles.
 *
 * @author shifmans
 */
public class ChanceSpace extends GameTile {
    /**
     * Constructor to initialize a ChanceSpace.
     *
     * Developed by: shifmans
     */
    public ChanceSpace(ChanceDeck deck) {
        super("Chance Space", "");
    }

    /**
     * Shows the actions that occur after a player lands on element space.
     * @return Displays the actions for landing on element space.
     *
     * Developed by: shifmans
     */
    @Override
    public String landOn() {
        actions += drawCard();
        return actions;
    }

    /**
     * Player draws a card from Chance Card Deck after landing on element space.
     * @return Instructions for drawing a card.
     *
     * Developed by: shifmans
     */
    private String drawCard() {
        return "Draw a card from the deck";
    }

    /**
     * Executes the strategy for the ChanceSpace.
     * @author crevelings
     */
    @Override
    public void executeStrategy(Player player) {
    }
}
