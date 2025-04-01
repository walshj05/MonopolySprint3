package org.monopoly.Model.Cards;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TitleDeedDeckTests {

    @Test
    public void testInitializingDeck() {
        TitleDeedDeck deck = new TitleDeedDeck();

        assertEquals(28, deck.getSize());
    }

    @Test
    public void testDrawCard() {
        TitleDeedDeck deck = new TitleDeedDeck();
        deck.drawCard("Mediterranean Avenue");

        assertEquals(27, deck.getSize());
    }

    @Test
    public void testTitleDeedCardAlreadyBought() {
        TitleDeedDeck deck = new TitleDeedDeck();
        deck.drawCard("Mediterranean Avenue");

        assertThrows(NoSuchElementException.class, () -> deck.drawCard("Mediterranean Avenue"));

        assertEquals(27, deck.getSize());
    }

    @Test
    public void testNoTitleDeedCardsLeft() {
        TitleDeedDeck deck = new TitleDeedDeck();

        deck.drawCard("Mediterranean Avenue");
        deck.drawCard("Baltic Avenue");
        deck.drawCard("Oriental Avenue");
        deck.drawCard("Vermont Avenue");
        deck.drawCard("Connecticut Avenue");
        deck.drawCard("St. Charles Place");
        deck.drawCard("States Avenue");
        deck.drawCard("Virginia Avenue");
        deck.drawCard("St. James Place");
        deck.drawCard("Tennessee Avenue");
        deck.drawCard("New York Avenue");
        deck.drawCard("Kentucky Avenue");
        deck.drawCard("Indiana Avenue");
        deck.drawCard("Illinois Avenue");
        deck.drawCard("Atlantic Avenue");
        deck.drawCard("Ventnor Avenue");
        deck.drawCard("Marvin Gardens");
        deck.drawCard("Pacific Avenue");
        deck.drawCard("North Carolina Avenue");
        deck.drawCard("Pennsylvania Avenue");
        deck.drawCard("Park Place");
        deck.drawCard("Boardwalk");
        deck.drawCard("Reading Railroad");
        deck.drawCard("Pennsylvania Railroad");
        deck.drawCard("B&O Railroad");
        deck.drawCard("Short Line Railroad");
        deck.drawCard("Electric Company");
        deck.drawCard("Water Works");

        assertThrows(NoSuchElementException.class, () -> deck.drawCard("Mediterranean Avenue"));
        assertEquals(0, deck.getSize());
    }

    @Test
    public void testReturnCard() {
        TitleDeedDeck deck = new TitleDeedDeck();
        deck.drawCard("Mediterranean Avenue");

        assertEquals(27, deck.getSize());

        deck.returnCard("Mediterranean Avenue");
        assertEquals(28, deck.getSize());
    }

    @Test
    public void testReturnCardAlreadyInDeck() {
        TitleDeedDeck deck = new TitleDeedDeck();
        assertEquals(28, deck.getSize());

        assertThrows(IllegalArgumentException.class, () -> deck.returnCard("Mediterranean Avenue"));
        assertEquals(28, deck.getSize());
    }
}
