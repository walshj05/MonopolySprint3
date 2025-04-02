package org.monopoly.Model.Cards;

import org.monopoly.Model.Players.Player;

import java.util.ArrayList;

/**
 * @author walshj05
 * This class is responsible for creating a deck of chance cards, and retrieving each card's header.
 */
public class ChanceDeck extends CardDeck {
    /**
     * Creates a deck of chance cards
     * @author walshj05
     */
    public ChanceDeck() {
        super(ChanceDeck.initializeCards());
    }

    /**
     * Initializes the chance cards
     * Later on this will be replaced by a more efficient initialization method
     * @author walshj05
     * @return ArrayList of chance cards
     */
    private static ArrayList<String> initializeCards(){
        ArrayList<String> chanceCards = new ArrayList<>();
        chanceCards.add("Advance to Boardwalk.");
        chanceCards.add("Advance to Go (Collect $200).");
        chanceCards.add("Advance to Illinois Avenue. If you pass Go, collect $200.");
        chanceCards.add("Advance to St. Charles Place. If you pass Go, collect $200.");
        chanceCards.add("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled.");
        chanceCards.add("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.");
        chanceCards.add("Bank pays you dividend of $50.");
        chanceCards.add("Get Out of Jail Free.");
        chanceCards.add("Go Back 3 Spaces.");
        chanceCards.add("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200.");
        chanceCards.add("Make general repairs on all your property. For each house pay $25. For each hotel pay $100.");
        chanceCards.add("Speeding fine $15.");
        chanceCards.add("Take a trip to Reading Railroad. If you pass Go, collect $200.");
        chanceCards.add("You have been elected Chairman of the Board. Pay each player $50.");
        chanceCards.add("Your building loan matures. Collect $150");
        return chanceCards;
    }

    /**
     * Draws a card from the top of the deck, and places it in the discard pile
     * @author walshj05
     * @return String header of the card drawn
     */
    @Override
    public String drawCard() {
        if (drawPile.isEmpty() && discardPile.isEmpty()) {
            return "No cards left in deck";
        } else if (drawPile.isEmpty()) {
            reshuffleDrawPile();
        }
        String card = drawPile.removeLast();
        unavailableCards.add(card);
        return card;
    }

    /**
     * Returns the card to the discard pile
     * @author walshj05
     */
    public void returnCardToDeck(String card) {
        if (unavailableCards.contains(card)) {
            unavailableCards.remove(card);
            discardPile.add(card);
        }
    }

    /**
     * Executes the strategy for the player
     * @param player The player to execute the strategy for.
     * @author walshj05
     */
    @Override
    public void executeStrategy(Player player) {
    }

    /**
     * Executes the strategy for the player and uses a specific strategy for a specific card
     * @param player The player to execute the strategy for.
     * @param card The card to execute the strategy for.
     * @author walshj05
     */
    public void executeStrategy(Player player, String card) {
        switch (card) {
            case "Advance to Boardwalk.":
                player.setPosition(39);
                break;
            case "Advance to Go (Collect $200).":
                player.setPosition(0);
                player.addToBalance(200);
                break;
            case "Advance to Illinois Avenue. If you pass Go, collect $200.":
                if (player.getPosition() > 24) {
                    player.addToBalance(200);
                }
                player.setPosition(24);
                break;
            case "Advance to St. Charles Place. If you pass Go, collect $200.":
                if (player.getPosition() > 11) {
                    player.addToBalance(200);
                }
                player.setPosition(11);
                break;
            case "Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled.":
                int position = player.getPosition();
                if (position == 7) {
                    player.setPosition(15);
                } else if (position == 22) {
                    player.setPosition(25);
                } else if (position == 36) {
                    player.setPosition(5);
                }
                break;
            case "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.":
                int pos = player.getPosition();
                if (pos == 7 || pos == 36) {
                    player.setPosition(12);
                } else if (pos == 22) {
                    player.setPosition(28);
                }
                break;
            case "Bank pays you dividend of $50.":
                player.addToBalance(50);
                break;
            case "Get Out of Jail Free.":
                if (!player.isInJail()) {
                    player.addCard("chance:" + card);
                    return;
                } else {
                    returnCardToDeck(card);
                    player.releaseFromJail();
                }
                break;
            case "Go Back 3 Spaces.":
                if (player.getPosition() < 3) {
                    player.setPosition(40 - (3 - player.getPosition()));
                } else {
                    player.setPosition(player.getPosition() - 3);
                }
                break;
            case "Go to Jail. Go directly to Jail, do not pass Go, do not collect $200.":
                player.setPosition(10);
                player.goToJail();
                break;
            case "Make general repairs on all your property. For each house pay $25. For each hotel pay $100.":
//                int total = (player.getNumHotels() * 100) + (player.getNumHouses() * 25);
                player.subtractFromBalance(0); // todo add logic to charge player for repairs -> gameState
                break;
            case "Speeding fine $15.":
                player.subtractFromBalance(15);
                break;
            case "Take a trip to Reading Railroad. If you pass Go, collect $200.":
                if (player.getPosition() > 5) {
                    player.addToBalance(200);
                }
                player.setPosition(5);
                break;
            case "You have been elected Chairman of the Board. Pay each player $50.":
                // todo find way to pay all
                break;
            case "Your building loan matures. Collect $150":
                player.addToBalance(150);
            default:
                break;
        }
        returnCardToDeck(card);
    }
}
