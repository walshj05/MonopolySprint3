package org.monopoly.Model.Cards;

import org.monopoly.Model.GameTiles.GameTile;
import java.util.*;

/**
 * TitleDeedDeck class represents a deck of title deed cards in the game.
 *
 * Developed by: shifmans
 */
public class TitleDeedDeck {
    private ArrayList<GameTile> drawPile;
    private TitleDeedCards properties;

    /**
     * Constructor for TitleDeedDeck.
     * Initializes the draw pile with properties from TitleDeedCards.
     *
     * Developed by: shifmans
     */
    public TitleDeedDeck() {
        this.drawPile = new ArrayList<>();
        this.properties = new TitleDeedCards();
        initializeDeck();
    }

    /**
     * Initializes the draw pile with properties from TitleDeedCards.
     *
     * Developed by: shifmans
     */
    private void initializeDeck() {
        for (String propertyName : properties.getProperties().keySet()) {
            drawPile.add(properties.getProperties().get(propertyName));
        }
    }

    /**
     * Draws a card from the draw pile.
     * @param currentProperty The name of the property to be drawn.
     * @return The drawn GameTile property.
     *
     * Developed by: shifmans
     */
    public GameTile drawCard(String currentProperty) {
        GameTile property = properties.getProperty(currentProperty);

        if (!drawPile.contains(property)) {
            throw new NoSuchElementException("Property has already been purchased.");
        }

        this.drawPile.remove(property);

        return property;
    }

    /**
     * Returns a card to the draw pile.
     * @param currentProperty The name of the property to be returned.
     *
     * Developed by: shifmans
     */
    public void returnCard(String currentProperty) {
        GameTile property = properties.getProperty(currentProperty);

        if (drawPile.contains(property)) {
            throw new IllegalArgumentException("Property is already in deck.");
        }

        this.drawPile.add(property);
    }

    /**
     * Returns the properties of the TitleDeedDeck.
     * @return TitleDeedCards object containing the properties.
     *
     * Developed by: shifmans
     */
    public TitleDeedCards getTitleDeeds() {
        return this.properties;
    }

    /**
     * Returns the size of the draw pile.
     * @return The size of the draw pile.
     *
     * Developed by: shifmans
     */
    public int getSize() {
        return this.drawPile.size();
    }
}
