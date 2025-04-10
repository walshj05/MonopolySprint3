package org.monopoly.Model.Cards;

import org.monopoly.Model.Strategy;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author walshj05
 * This class is responsible for creating a deck of cards and
 * has abstract methods for the basic functions of a deck of cards.
 */
public abstract class CardDeck implements Strategy {
    protected ArrayList<String> drawPile;
    protected ArrayList<String> discardPile;
    protected ArrayList<String> unavailableCards;

    /**
     * Creates a deck of cards from an arraylist of cards
     * Shuffles the draw deck
     * @author walshj05
     * @param drawPile ArrayList of cards
     */
    public CardDeck(ArrayList<String> drawPile) {
        this.drawPile = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.unavailableCards = new ArrayList<>();
        this.drawPile.addAll(drawPile);
        Collections.shuffle(this.drawPile);
    }

    /**
     * Draws a card from the draw pile
     * @author walshj05
     * @return String of the card drawn
     */
    public abstract String drawCard();

    /**
     * Reshuffles the discard pile into the draw pile
     * @author walshj05
     */
    protected void reshuffleDrawPile(){
        Collections.shuffle(this.discardPile);
        this.drawPile.addAll(this.discardPile);
        this.discardPile.clear();
    }

}
