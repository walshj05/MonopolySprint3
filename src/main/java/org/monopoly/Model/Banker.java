package org.monopoly.Model;

import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.GameTiles.PropertySpace;
import org.monopoly.Model.Cards.TitleDeedDeck;
import org.monopoly.Model.Players.Player;

import java.util.*;

/**
 * Represents a Banker which manages the bank in the game of Monopoly.
 *
 * @author shifmans
 */
public class Banker {
    private double bankBalance;
    private TitleDeedDeck deck;
    private int numHouses;
    private int numHotels;
    private static Banker instance;

    /**
     * Constructor to initialize the Banker.
     *
     * Developed by: shifmans
     */
    public Banker() {
        this.bankBalance = Double.POSITIVE_INFINITY;
        this.deck = new TitleDeedDeck();
        this.numHouses = 32;
        this.numHotels = 12;
    }

    /**
     * Singleton pattern to ensure only one instance of Banker is created.
     * @return The instance of Banker.
     *
     * Developed by: shifmans
     */
    public static Banker getInstance() {
        if (instance == null) {
            instance = new Banker();
        }
        return instance;
    }

    /**
     * This method is used to buy a property from the banker.
     * @param propertyName The name of the property to buy.
     * @param player The player who is buying the property.
     *
     * Developed by: shifmans
     */
    public void sellProperty(String propertyName, Player player) {
        if (deck.getTitleDeeds().getProperty(propertyName).isMortgaged()) {
            throw new IllegalStateException("Property is mortgaged and cannot be sold.");
        }

        deck.getTitleDeeds().getProperty(propertyName).setOwner(player.getName());
        player.subtractFromBalance(deck.getTitleDeeds().getProperty(propertyName).getPrice());
        deck.drawCard(propertyName);
    }

    /**
     * This method is used to sell a house to a player.
     * @param propertyName The name of the property to sell a house from.
     * @param player The player who is selling the house.
     *
     * Developed by: shifmans
     */
    public void sellHouse(String propertyName, Player player) {
        if (deck.getTitleDeeds().getProperty(propertyName) instanceof PropertySpace) {
            if (this.numHouses == 0) {
                throw new IllegalStateException("There are no houses left.");
            }
            else {
                this.numHouses -= 1;

                int numPropertyHouses = ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getNumHouses();
                player.subtractFromBalance(((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getHousePrice());
                ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).setNumHouses(numPropertyHouses+1);
            }
        }

        else {
            throw new IllegalArgumentException("This property does not have houses.");
        }
    }

    /**
     * This method is used to receive a house from a player.
     * @param propertyName The name of the property to receive a house from.
     * @param player The player who is receiving the house.
     *
     * Developed by: shifmans
     */
    public void receiveHouse(String propertyName, Player player) {
        if (deck.getTitleDeeds().getProperty(propertyName) instanceof PropertySpace) {
            if (this.numHouses == 0) {
                throw new IllegalStateException("There are no houses left.");
            }
            else {
                this.numHouses += 1;

                int numPropertyHouses = ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getNumHouses();
                player.addToBalance(((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getHousePrice()/2);
                ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).setNumHouses(numPropertyHouses-1);
            }
        }

        else {
            throw new IllegalArgumentException("This property does not have houses.");
        }
    }

    /**
     * This method is used to sell a hotel to a player.
     * @param propertyName The name of the property to sell a hotel from.
     * @param player The player who is selling the hotel.
     *
     * Developed by: shifmans
     */
    public void sellHotel(String propertyName, Player player) {
        if (deck.getTitleDeeds().getProperty(propertyName) instanceof PropertySpace) {
            if (this.numHotels == 0) {
                throw new IllegalStateException("There are no hotels left.");
            }
            else {
                this.numHotels -= 1;

                int numPropertyHotels = ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getNumHotels();
                player.subtractFromBalance(((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getHousePrice());
                ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).setNumHotels(numPropertyHotels+1);
            }
        }

        else {
            throw new IllegalArgumentException("This property does not have hotels.");
        }
    }

    /**
     * This method is used to receive a hotel from a player.
     * @param propertyName The name of the property to receive a hotel from.
     * @param player The player who is receiving the hotel.
     *
     * Developed by: shifmans
     */
    public void receiveHotel(String propertyName, Player player) {
        if (deck.getTitleDeeds().getProperty(propertyName) instanceof PropertySpace) {
            if (this.numHotels == 0) {
                throw new IllegalStateException("There are no hotels left.");
            }
            else {
                this.numHotels += 1;

                int numPropertyHotels = ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getNumHotels();
                player.addToBalance(((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getHotelPrice()/2);
                ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).setNumHotels(numPropertyHotels-1);
            }
        }

        else {
            throw new IllegalArgumentException("This property does not have hotels.");
        }
    }

    /**
     * This method is used to auction a property.
     * @param propertyName The name of the property to auction.
     * @param players The players who are in the game.
     *
     * Developed by: shifmans
     */
    public void auctionProperty(String propertyName, ArrayList<Player> players) {
        int currentBidAmount = 1;
        HashMap<Player, Integer> currentBidding = new HashMap<>();
        Scanner keyboard = new Scanner(System.in);
        Player currentHighestBidder;
        int numRounds = 1;

        while (true) {
            if (numRounds == 1) {
                System.out.println("Starting Bid for " + propertyName + " starting at $" + currentBidAmount);
            } else {
                System.out.println("Round " + numRounds + ", the bid now starts at $" + currentBidAmount);
            }

            ArrayList<Player> bidders = getCurrentBidders(players, currentBidAmount, keyboard);
            ArrayList<Integer> bidAmounts = getBidAmount(currentBidAmount, bidders, keyboard);

            if ((numRounds == 1) && (bidders.isEmpty())) {
                deck.getTitleDeeds().getProperty(propertyName).setOwner("");
                deck.returnCard(propertyName);
                break;
            }

            if (bidders.size() <= 1) {
                endAuction(propertyName, bidders, bidAmounts);
                break;
            }

            for (int i = 0; i < bidders.size(); i++) {
                currentBidding.put(bidders.get(i), bidAmounts.get(i));
            }

            currentHighestBidder = getHighestBidder(currentBidding);
            currentBidAmount = currentBidding.get(currentHighestBidder);

            numRounds++;
        }
    }

    /**
     * Ends the auction for a property.
     * @param propertyName The name of the property.
     * @param bidders The list of bidders.
     * @param bidAmounts The list of bid amounts.
     *
     * Developed by: shifmans
     */
    private void endAuction(String propertyName, ArrayList<Player> bidders, ArrayList<Integer> bidAmounts) {
        if (bidders.isEmpty() || bidAmounts.isEmpty()) {
            System.out.println("No valid bids placed for the property.");
            return;
        }

        System.out.println("The Auction for " + propertyName + " ended!");
        System.out.println("Winner: " + bidders.get(0).getName());
        System.out.println("Bid Amount: $" + bidAmounts.get(0));

        deck.getTitleDeeds().getProperty(propertyName).setOwner(bidders.get(0).getName());
        bidders.get(0).subtractFromBalance(bidAmounts.get(0));
    }

    /**
     * Gets the current bidders for the property.
     * @param players The list of players in game.
     * @param currentBidAmount The current bid amount.
     * @param keyboard The scanner for user input.
     * @return The list of bidders for property.
     *
     * Developed by: shifmans
     */
    private ArrayList<Player> getCurrentBidders(ArrayList<Player> players, int currentBidAmount, Scanner keyboard) {
        ArrayList<Player> bidders = new ArrayList<>();

        for (Player player : players) {
            if (currentBidAmount <= player.getBalance()) {
                System.out.println(player.getName() + ", do you want to bid on this property (Y/N)? ");
                char answer = keyboard.next().charAt(0);

                //Add countdown timer feature later, 3 seconds to bid

                while ((answer != 'Y') && (answer != 'y') && (answer != 'N') && (answer != 'n')) {
                    System.out.println("Invalid response, " + player.getName() + " do you want to bid on this property (Y/N)? ");
                    answer = keyboard.next().charAt(0);
                }

                if ((answer == 'Y') || (answer == 'y')) {
                    bidders.add(player);
                }
            }

            else {
                System.out.println(player.getName() + " does not have enough money to bid.");
            }
        }
        return bidders;
    }

    /**
     * Gets the bid amount from the bidders.
     * @param currentBid The current bid amount.
     * @param bidders The list of bidders.
     * @param keyboard The scanner for user input.
     * @return The list of bid amounts.
     *
     * Developed by: shifmans
     */
    private ArrayList<Integer> getBidAmount(int currentBid, ArrayList<Player> bidders, Scanner keyboard) {
        ArrayList<Integer> bidAmounts = new ArrayList<>();

        for (Player bidder : bidders) {
            System.out.println(bidder.getName() + ", how much do you want to bid? ");
            int answer = keyboard.nextInt();

            while (answer <= currentBid) {
                System.out.println("Invalid response, " + bidder.getName() + " how much do you want to bid? ");
                answer = keyboard.nextInt();
            }

            bidAmounts.add(answer);
        }

        return bidAmounts;
    }

    /**
     * Gets the highest bidder from the current bidding.
     * @param currentBidding The current bidding map.
     * @return The player with the highest bid.
     *
     * Developed by: shifmans
     */
    private Player getHighestBidder(HashMap<Player, Integer> currentBidding) {
        int bid = 0;
        Player highestBidder = null;

        for (Player bidder : currentBidding.keySet()) {
            if (currentBidding.get(bidder) > bid) {
                bid = currentBidding.get(bidder);
                highestBidder = bidder;
            }
        }

        return highestBidder;
    }

    /**
     * This method is used to mortgage a property.
     * @param propertyName The name of the property to mortgage.
     *
     * Developed by: shifmans
     */
    public void mortgageProperty(String propertyName) {
        deck.getTitleDeeds().getProperties().get(propertyName).setMortgagedStatus(true);
    }

    /**
     * This method is used to pay a player for passing Go Space.
     * @param playerName The player who is passing Go Space.
     *
     * Developed by: shifmans
     */
    public void payGoSpace(Player playerName) {
        playerName.addToBalance(200);
        this.bankBalance -= 200;
    }

    /**
     * This method is used when player is paying the bank money.
     * @param playerName The player who is paying the bank.
     * @param money The amount of money the player is paying.
     *
     * Developed by: shifmans
     */
    public void receiveMoney(Player playerName, int money) {
        playerName.subtractFromBalance(money);
        this.bankBalance += money;
    }

    /**
     * Checks if the player has a monopoly.
     * @author walshj05
     */
    public void checkForMonopolies(ArrayList<String> propertiesOwned, ArrayList<Monopoly> monopolies, ArrayList<ColorGroup> colorGroups) {
        if (propertiesOwned.contains("Mediterranean Avenue") && propertiesOwned.contains("Baltic Avenue")) {
            if (!colorGroups.contains(ColorGroup.BROWN)) {
                String[] brownProperties = {"Mediterranean Avenue", "Baltic Avenue"};
                monopolies.add(new Monopoly(brownProperties, ColorGroup.BROWN));
            }
        }
        if (propertiesOwned.contains("Oriental Avenue") && propertiesOwned.contains("Vermont Avenue") && propertiesOwned.contains("Connecticut Avenue")) {
            if (!colorGroups.contains(ColorGroup.LIGHT_BLUE)) {
                String[] lightBlueProperties = {"Oriental Avenue", "Vermont Avenue", "Connecticut Avenue"};
                monopolies.add(new Monopoly(lightBlueProperties, ColorGroup.LIGHT_BLUE));
            }
        }
        if (propertiesOwned.contains("St. Charles Place") && propertiesOwned.contains("States Avenue") && propertiesOwned.contains("Virginia Avenue")) {
            if (!colorGroups.contains(ColorGroup.PINK)) {
                String[] pinkProperties = {"St. Charles Place", "States Avenue", "Virginia Avenue"};
                monopolies.add(new Monopoly(pinkProperties, ColorGroup.PINK));
            }
        }
        if (propertiesOwned.contains("St. James Place") && propertiesOwned.contains("Tennessee Avenue") && propertiesOwned.contains("New York Avenue")) {
            if (!colorGroups.contains(ColorGroup.ORANGE)) {
                String[] orangeProperties = {"St. James Place", "Tennessee Avenue", "New York Avenue"};
                monopolies.add(new Monopoly(orangeProperties, ColorGroup.ORANGE));
            }
        }
        if (propertiesOwned.contains("Kentucky Avenue") && propertiesOwned.contains("Indiana Avenue") && propertiesOwned.contains("Illinois Avenue")) {
            if (!colorGroups.contains(ColorGroup.RED)) {
                String[] redProperties = {"Kentucky Avenue", "Indiana Avenue", "Illinois Avenue"};
                monopolies.add(new Monopoly(redProperties, ColorGroup.RED));
            }
        }
        if (propertiesOwned.contains("Atlantic Avenue") && propertiesOwned.contains("Ventnor Avenue") && propertiesOwned.contains("Marvin Gardens")) {
            if (!colorGroups.contains(ColorGroup.YELLOW)) {
                String[] yellowProperties = {"Atlantic Avenue", "Ventnor Avenue", "Marvin Gardens"};
                monopolies.add(new Monopoly(yellowProperties, ColorGroup.YELLOW));
            }
        }
        if (propertiesOwned.contains("Pacific Avenue") && propertiesOwned.contains("North Carolina Avenue") && propertiesOwned.contains("Pennsylvania Avenue")) {
            if (!colorGroups.contains(ColorGroup.GREEN)) {
                String[] greenProperties = {"Pacific Avenue", "North Carolina Avenue", "Pennsylvania Avenue"};
                monopolies.add(new Monopoly(greenProperties, ColorGroup.GREEN));
            }
        }
        if (propertiesOwned.contains("Park Place") && propertiesOwned.contains("Boardwalk")) {
            if (!colorGroups.contains(ColorGroup.DARK_BLUE)) {
                String[] darkBlueProperties = {"Park Place", "Boardwalk"};
                monopolies.add(new Monopoly(darkBlueProperties, ColorGroup.DARK_BLUE));
            }
        }
    }

    /**
     * This method is used to get the deck of properties.
     * @return The deck of properties.
     *
     * Developed by: shifmans
     */
    public TitleDeedDeck getDeck() {
        return this.deck;
    }

    /**
     * This method is used to get the number of houses.
     * @return The number of houses.
     *
     * Developed by: shifmans
     */
    public int getHouses() {
        return this.numHouses;
    }

    /**
     * This method is used to get the number of hotels.
     * @return The number of hotels.
     *
     * Developed by: shifmans
     */
    public int getHotels() {
        return this.numHotels;
    }

    /**
     * This method is used to get the bank balance.
     * @return The bank balance.
     *
     * Developed by: shifmans
     */
    public double getBalance() {
        return this.bankBalance;
    }

    /*
    NOTE: THE FOLLOWING METHODS ARE MEANT TO BE USED FOR THE NEW IMPLEMENTATION OF MONOPOLY CLASS
    NOTE: WE CAN REFACTOR THE BANKER CLASS OR MONOPOLY CLASS AS NEEDED, BUT THIS IS NEEDED FOR TESTS
    NOTE: TO PASS!
     */

    /**
     * This method is used to buy a house from the banker.
     * @author walshj05
     */
    public void buyHouse() {
        this.numHouses--;
    }

    /**
     * This method is used to buy a hotel from the banker.
     * @author walshj05
     */
    public void buyHotel() {
        this.numHotels--;
    }

    /**
     * This method is used to return a number of houses to the banker.
     * @param numHouses The number of houses to return.
     * @author walshj05
     */
    public void returnHouses(int numHouses) {
        this.numHouses += numHouses;
    }

    /**
     * This method is used to return a hotel to the banker.
     * @author walshj05
     */
    public void returnHotel() {
        this.numHotels++;
        this.numHouses -= 4;
    }
}
