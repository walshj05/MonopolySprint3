package org.monopoly.Model;

import org.monopoly.Model.GameTiles.PropertySpace;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Cards.TitleDeedDeck;

import java.util.*;

public class Banker {
    private double bankBalance;
    private TitleDeedDeck deck;
    private int numHouses;
    private int numHotels;
    private static Banker instance;

    public Banker() {
        this.bankBalance = Double.POSITIVE_INFINITY;
        this.deck = new TitleDeedDeck();
        this.numHouses = 32;
        this.numHotels = 32;
    }


    public static Banker getInstance() {
        if (instance == null) {
            instance = new Banker();
        }
        return instance;
    }

    public void sellProperty(String propertyName, HumanPlayer humanPlayer) {
        if (deck.getTitleDeeds().getProperty(propertyName).isMortgaged()) {
            throw new IllegalStateException("Property is mortgaged and cannot be sold.");
        }

        deck.getTitleDeeds().getProperty(propertyName).setOwner(humanPlayer.getName());
        humanPlayer.subtractFromBalance(deck.getTitleDeeds().getProperty(propertyName).getPrice());
        deck.drawCard(propertyName);
    }

    public void sellHouse(String propertyName, HumanPlayer humanPlayer) {
        if (deck.getTitleDeeds().getProperty(propertyName) instanceof PropertySpace) {
            if (this.numHouses == 0) {
                throw new IllegalStateException("There are no houses left.");
            }
            else {
                this.numHouses -= 1;

                int numPropertyHouses = ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getNumHouses();
                humanPlayer.subtractFromBalance(((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getHousePrice());
                ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).setNumHouses(numPropertyHouses+1);
            }
        }

        else {
            throw new IllegalArgumentException("This property does not have houses.");
        }
    }

    public void receiveHouse(String propertyName, HumanPlayer humanPlayer) {
        if (deck.getTitleDeeds().getProperty(propertyName) instanceof PropertySpace) {
            if (this.numHouses == 0) {
                throw new IllegalStateException("There are no houses left.");
            }
            else {
                this.numHouses += 1;

                int numPropertyHouses = ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getNumHouses();
                humanPlayer.addToBalance(((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getHousePrice()/2);
                ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).setNumHouses(numPropertyHouses-1);
            }
        }

        else {
            throw new IllegalArgumentException("This property does not have houses.");
        }
    }

    public void sellHotel(String propertyName, HumanPlayer humanPlayer) {
        if (deck.getTitleDeeds().getProperty(propertyName) instanceof PropertySpace) {
            if (this.numHotels == 0) {
                throw new IllegalStateException("There are no hotels left.");
            }
            else {
                this.numHotels -= 1;

                int numPropertyHotels = ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getNumHotels();
                humanPlayer.subtractFromBalance(((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getHousePrice());
                ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).setNumHotels(numPropertyHotels+1);
            }
        }

        else {
            throw new IllegalArgumentException("This property does not have hotels.");
        }
    }

    public void receiveHotel(String propertyName, HumanPlayer humanPlayer) {
        if (deck.getTitleDeeds().getProperty(propertyName) instanceof PropertySpace) {
            if (this.numHotels == 0) {
                throw new IllegalStateException("There are no hotels left.");
            }
            else {
                this.numHotels += 1;

                int numPropertyHotels = ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getNumHotels();
                humanPlayer.addToBalance(((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).getHotelPrice()/2);
                ((PropertySpace) deck.getTitleDeeds().getProperty(propertyName)).setNumHotels(numPropertyHotels-1);
            }
        }

        else {
            throw new IllegalArgumentException("This property does not have hotels.");
        }
    }

    public void auctionProperty(String propertyName, ArrayList<HumanPlayer> players) {
        int currentBidAmount = 1;
        HashMap<HumanPlayer, Integer> currentBidding = new HashMap<>();
        Scanner keyboard = new Scanner(System.in);
        HumanPlayer currentHighestBidder;
        int numRounds = 1;

        while (true) {
            if (numRounds == 1) {
                System.out.println("Starting Bid for " + propertyName + " starting at $" + currentBidAmount);
            } else {
                System.out.println("Round " + numRounds + ", the bid now starts at $" + currentBidAmount);
            }

            ArrayList<HumanPlayer> bidders = getCurrentBidders(players, currentBidAmount, keyboard);
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

    private void endAuction(String propertyName, ArrayList<HumanPlayer> bidders, ArrayList<Integer> bidAmounts) {
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

    private ArrayList<HumanPlayer> getCurrentBidders(ArrayList<HumanPlayer> players, int currentBidAmount, Scanner keyboard) {
        ArrayList<HumanPlayer> bidders = new ArrayList<>();

        for (HumanPlayer player : players) {
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

    private ArrayList<Integer> getBidAmount(int currentBid, ArrayList<HumanPlayer> bidders, Scanner keyboard) {
        ArrayList<Integer> bidAmounts = new ArrayList<>();

        for (HumanPlayer bidder : bidders) {
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

    private HumanPlayer getHighestBidder(HashMap<HumanPlayer, Integer> currentBidding) {
        int bid = 0;
        HumanPlayer highestBidder = null;

        for (HumanPlayer bidder : currentBidding.keySet()) {
            if (currentBidding.get(bidder) > bid) {
                bid = currentBidding.get(bidder);
                highestBidder = bidder;
            }
        }

        return highestBidder;
    }

    public void mortgageProperty(String propertyName) {
        deck.getTitleDeeds().getProperties().get(propertyName).setMortgagedStatus(true);
    }

    public void payGoSpace(HumanPlayer humanPlayerName) {
        humanPlayerName.addToBalance(200);
        this.bankBalance -= 200;
    }

    public void receiveMoney(HumanPlayer humanPlayerName, int money) {
        humanPlayerName.subtractFromBalance(money);
        this.bankBalance += money;
    }

    /**
     * Checks if the player has a monopoly.
     * @author walshj05
     */
    public ArrayList<String> checkForMonopolies(ArrayList<String> propertiesOwned) {
        ArrayList<String> currMonopolies = new ArrayList<>();
        if (propertiesOwned.contains("Mediterranean Avenue") && propertiesOwned.contains("Baltic Avenue")) {
            currMonopolies.add("brown");
        }
        if (propertiesOwned.contains("Oriental Avenue") && propertiesOwned.contains("Vermont Avenue") && propertiesOwned.contains("Connecticut Avenue")) {
            currMonopolies.add("lightBlue");
        }
        if (propertiesOwned.contains("St. Charles Place") && propertiesOwned.contains("States Avenue") && propertiesOwned.contains("Virginia Avenue")) {
            currMonopolies.add("pink");
        }
        if (propertiesOwned.contains("St. James Place") && propertiesOwned.contains("Tennessee Avenue") && propertiesOwned.contains("New York Avenue")) {
            currMonopolies.add("orange");
        }
        if (propertiesOwned.contains("Kentucky Avenue") && propertiesOwned.contains("Indiana Avenue") && propertiesOwned.contains("Illinois Avenue")) {
            currMonopolies.add("red");
        }
        if (propertiesOwned.contains("Atlantic Avenue") && propertiesOwned.contains("Ventnor Avenue") && propertiesOwned.contains("Marvin Gardens")) {
            currMonopolies.add("yellow");
        }
        if (propertiesOwned.contains("Pacific Avenue") && propertiesOwned.contains("North Carolina Avenue") && propertiesOwned.contains("Pennsylvania Avenue")) {
            currMonopolies.add("green");
        }
        if (propertiesOwned.contains("Park Place") && propertiesOwned.contains("Boardwalk")) {
            currMonopolies.add("darkBlue");
        }
        return currMonopolies;
    }

    public TitleDeedDeck getDeck() {
        return this.deck;
    }

    public int getHouses() {
        return this.numHouses;
    }

    public int getHotels() {
        return this.numHotels;
    }

    public double getBalance() {
        return this.bankBalance;
    }
}
