package org.monopoly.Model.GameTiles;

import org.monopoly.Model.Cards.CommunityChestDeck;
import org.monopoly.Model.Players.Player;

/**
 * Represents the Community Chest Space element on the Game Board's Tiles.
 *
 * @author shifmans
 */
public class CommunityChestSpace extends GameTile {

    /**
     * Constructor to initialize a CommunityChestSpace.
     *
     * Developed by: shifmans
     */
    public CommunityChestSpace(CommunityChestDeck deck) {
        super("Community Chest Space", "");
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
     * Executes the strategy for the CommunityChestSpace.
     * @author crevelings
     */
    @Override
    public void executeStrategy(Player player) {
    }
}
