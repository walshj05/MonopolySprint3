package org.monopoly.Model.Cards;

import org.monopoly.Model.Players.Player;

import java.util.ArrayList;

/**
 * @author walshj05
 * This class is responsible for creating a deck of Community Chest cards
 * and has methods for drawing a card from the deck.
 */
public class CommunityChestDeck extends CardDeck {
    /**
     * Creates a deck of Community Chest cards
     * Shuffles the draw deck
     * @author walshj05
     */
    public CommunityChestDeck() {
        super(CommunityChestDeck.initializeCards());
    }

    /**
     * Initializes the Community Chest deck with the cards
     * @return ArrayList of Community Chest cards
     * @author walshj05
     */
    private static ArrayList<String> initializeCards(){
        ArrayList<String> communityChestCards = new ArrayList<>();
        communityChestCards.add("Advance to Go (Collect $200)");
        communityChestCards.add("Bank error in your favor. Collect $200");
        communityChestCards.add("Doctor’s fee. Pay $50");
        communityChestCards.add("From sale of stock you get $50");
        communityChestCards.add("Get Out of Jail Free");
        communityChestCards.add("Go to Jail. Go directly to jail, do not pass Go, do not collect $200");
        communityChestCards.add("Holiday fund matures. Receive $100");
        communityChestCards.add("Income tax refund. Collect $20");
        communityChestCards.add("It is your birthday. Collect $10 from every player");
        communityChestCards.add("Life insurance matures. Collect $100");
        communityChestCards.add("Pay hospital fees of $100");
        communityChestCards.add("Pay school fees of $50");
        communityChestCards.add("Receive $25 consultancy fee");
        communityChestCards.add("You are assessed for street repair. $40 per house. $115 per hotel");
        communityChestCards.add("You have won second prize in a beauty contest. Collect $10");
        communityChestCards.add("You inherit $100");
        return communityChestCards;
    }

    /**
     * Draws a card from the top of the deck
     * - If the draw pile is empty, reshuffles the discard pile into the draw pile
     * - If both piles are empty, returns a message that there are no cards left
     * @return String of the card drawn
     * @author walshj05
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
     * Executes the strategy for the Community Chest cards
     * @param player Player
     * @param card String card
     * @author walshj05
     */
    public void executeStrategy(Player player, String card) {
        switch (card) {
            case "Advance to Go (Collect $200)":
                player.setPosition(0);
                player.addToBalance(200);
                break;
            case "Bank error in your favor. Collect $200":
                player.addToBalance(200);
                break;
            case "Doctor’s fee. Pay $50", "Pay school fees of $50":
                player.subtractFromBalance(50);
                break;
            case "From sale of stock you get $50":
                player.addToBalance(50);
                break;
            case "Get Out of Jail Free":
                if (!player.isInJail()) {
                    player.addCard("community:" + card);
                    return;
                } else {
                    player.releaseFromJail();
                    returnCardToDeck(card);
                }
                break;
            case "Go to Jail. Go directly to jail, do not pass Go, do not addToBalance $200":
                player.goToJail();
                break;
            case "Holiday fund matures. Receive $100", "You inherit $100", "Life insurance matures. Collect $100":
                player.addToBalance(100);
                break;
            case "Income tax refund. Collect $20":
                player.addToBalance(20);
                break;
            case "It is your birthday. Collect $10 from every player":
//                player.addToBalance(0); // todo add logic to collect $10 from every player -> gameState
                break;
            case "Pay hospital fees of $100":
                player.subtractFromBalance(100);
                break;
            case "Receive $25 consultancy fee":
                player.addToBalance(25);
                break;
            case "You are assessed for street repair. $40 per house. $115 per hotel":
//                int totalCharge = (115 * player.getNumHotels()) + (40 * player.getNumHotels());
                player.subtractFromBalance(0); // todo add logic to charge player for street repair -> gameState
                break;
            case "You have won second prize in a beauty contest. Collect $10":
                player.addToBalance(10);
                break;
            default:
                break;
        }
        returnCardToDeck(card);
    }

    /**
     * Executes the strategy for the Community Chest cards
     * @param player The player to execute the strategy for.
     * @author walshj05
     */
    @Override
    public void executeStrategy(Player player) {
    }
}
