package org.monopoly.Model.Cards;

import org.junit.jupiter.api.Test;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.Players.Token;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author walshj05
 * Tests for the CommunityChestDeck class
 */
class CommunityChestDeckTest {
    @Test
    void testEnvironmentWorks() {
        assertTrue(true);
    }

    @Test
    void testCommunityChestDeckCreation() {
        CommunityChestDeck communityChestDeck = new CommunityChestDeck();
        assertNotNull(communityChestDeck, "CommunityChestDeck object should be created");
    }

    @Test
    void testDrawCard() {
        CommunityChestDeck communityChestDeck = new CommunityChestDeck();
        String card = communityChestDeck.drawCard();
        assertNotNull(card, "Card should be drawn");
    }

    @Test
    void testDrawCardCanBeReturnedToDiscardPile() {
        CommunityChestDeck communityChestDeck = new CommunityChestDeck();
        String card = communityChestDeck.drawCard();
        communityChestDeck.returnCardToDeck(card);
        assertEquals(1, communityChestDeck.discardPile.size(), "Discard pile should contain 1 card after returning a card");
        assertEquals(15, communityChestDeck.drawPile.size(), "Draw pile should contain 15 cards after returning a card");
    }

    @Test
    void testDrawDoesNotReturnCardWhenEmpty(){
        CommunityChestDeck communityChestDeck = new CommunityChestDeck();
        for (int i = 0; i < 16; i++) {
            communityChestDeck.drawCard();
        }
        String card = communityChestDeck.drawCard();
        assertEquals("No cards left in deck",card, "Card should not be drawn when deck is empty");
    }

    @Test
    void testPlayingDrawAfterReturningTwoCardsSetsDeckSizeTo2(){
        CommunityChestDeck communityChestDeck = new CommunityChestDeck();
        String card1 = "";
        String card2 = "";
        for (int i = 0; i < 8; i++) {
            card1 =communityChestDeck.drawCard();
            card2 =communityChestDeck.drawCard();
        }
        communityChestDeck.returnCardToDeck(card1);
        communityChestDeck.returnCardToDeck(card2);
        assertEquals(2, communityChestDeck.discardPile.size(), "Discard pile should contain 2 cards after returning 2 cards");
        communityChestDeck.drawCard();
        assertEquals(1, communityChestDeck.drawPile.size(), "Draw pile should contain 1 cards after returning 2 cards and drawing 1");
    }

    @Test
    void testReturningAnExtraOfACardDoesNotWork(){
        CommunityChestDeck communityChestDeck = new CommunityChestDeck();
        String card = communityChestDeck.drawCard();
        communityChestDeck.returnCardToDeck(card);
        communityChestDeck.returnCardToDeck(card);
        assertEquals(1, communityChestDeck.discardPile.size(), "Discard pile should contain 1 card after returning a card");
        assertEquals(15, communityChestDeck.drawPile.size(), "Draw pile should contain 15 cards after returning a card");
    }

    @Test
    void testDrawCardReturnsDifferentCards(){
        CommunityChestDeck communityChestDeck = new CommunityChestDeck();
        String card1 = communityChestDeck.drawCard();
        String card2 = communityChestDeck.drawCard();
        assertNotEquals(card1, card2, "Two cards drawn should not be the same");
    }

    @Test
    void testExecuteStrategyWorks(){
        CommunityChestDeck communityChestDeck = new CommunityChestDeck();
        Player player = new HumanPlayer("Player1", new Token("Dog", "ScottieDog.png"));
        communityChestDeck.executeStrategy(player);
        assertEquals(0, player.getPosition(), "Player position should not change");

        String card = communityChestDeck.drawCard();
        communityChestDeck.executeStrategy(player, card);
        assertNotNull(player);
    }
}