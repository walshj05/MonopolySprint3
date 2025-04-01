package org.monopoly.Model.Players;

import org.monopoly.Exceptions.HouseCannotBeBuiltException;
import org.monopoly.Exceptions.HotelCannotBeBuiltException;
import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Exceptions.NoSuchPropertyException;
import org.monopoly.Model.Dice;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing a player in the Monopoly game.
 * More functionality will be added
 * @author walshj05, crevelings
 */
public class HumanPlayer extends Player {
    private String name;
    private int position;
    private int balance;
    private boolean inJail;
    private Token token;
    private int numHouses;
    private int numHotels;
    private ArrayList<String> propertiesOwned;
    private ArrayList<String> propertiesMortgaged;
    private HashMap<String, Integer> monopolies;
    private ArrayList<String> cards;
    private int jailTurns;

    /**
     * Constructor for a HumanPlayer
     * @param name String
     * @param token Token
     * @author walshj05
     */
    public HumanPlayer(String name, Token token) {
        this.name = name;
        this.token = token;
        this.balance = 1500; // Starting balance
        this.inJail = false;
        this.jailTurns = 0;
        token.setOwner(this);
        this.position = 0;
        this.numHouses = 0;
        this.numHotels = 0;
        this.propertiesOwned = new ArrayList<>();
        this.propertiesMortgaged = new ArrayList<>();
        this.monopolies = new HashMap<>();
        this.cards = new ArrayList<>();
    }

    /**
     * Getters and Setters
     * @author walshj05
     */
    public String getName() {
        return name;
    }

    public Token getToken() {
        return token;
    }

    public int getPosition() {
        return position;
    }

    public int getBalance() {
        return balance;
    }

    public int getNumHotels() {
        return numHotels;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    /**
     * Moves player a certain number of spaces
     * Also checks if they are in jail or not
     * @param spaces num spaces moved
     * @author walshj05
     */
    public void move(int spaces) {
        if (!inJail) {
            position += spaces; // Move the player
            System.out.println(name + " moved " + spaces + " spaces to position " + position);
        } else {
            System.out.println(name + " is in jail and cannot move.");
        }
    }

    /**
     * Puts player in jail
     * @author walshj05
     */
    public void goToJail() {
        inJail = true;
        position = 10; // Assuming position 10 is Jail
        System.out.println(name + " has been sent to jail!");
    }

    /**
     * Checks to see if the player is in jail
     * @return boolean
     * @author walshj05
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Releases player from jail
     * @author walshj05
     */
    public void releaseFromJail() {
        inJail = false;
        System.out.println(name + " has been released from jail!");
    }

    /**
     * A method for a player to take a turn in the game
     * @param dice Dice object
     * @author walshj05
     */
    public void takeTurn (Dice dice) {
        if (inJail) {
            System.out.println(name + " is in jail and cannot roll.");
        }

        int[] rollResult = dice.roll();
        int die1 = rollResult[0];
        int die2 = rollResult[1];
        int total = die1 + die2;

        System.out.println(name + " rolled a " + die1 + " and a " + die2 + " (Total: " + total + ")");

        move(total);
    }

    /**
     * Player buys a property
     * @param property String
     * @throws InsufficientFundsException exception
     * @author walshj05
     */
    public void purchaseProperty(String property, int price) throws InsufficientFundsException {
        if (balance >= price) {
            propertiesOwned.add(property);
            balance -= price;
            checkForMonopoly();
        } else {
            throw new InsufficientFundsException("Insufficient funds to purchase " + property);
        }
    }

    /**
     * Player sells a property
     * @param property String
     * @throws NoSuchPropertyException exception
     * @author walshj05
     */
    public void mortgageProperty(String property, int mortgageCost) throws NoSuchPropertyException {
        if (propertiesOwned.contains(property)) {
            propertiesOwned.remove(property);
            propertiesMortgaged.add(property);
            balance += mortgageCost;
        } else {
            throw new NoSuchPropertyException("You do not own " + property);
        }
    }

    /**
     * Player sells a property
     * @param property String
     * @throws NoSuchPropertyException exception
     * @author walshj05
     */
    public void sellProperty(String property, int propertyCost) throws NoSuchPropertyException {
        if (propertiesOwned.contains(property)) {
            propertiesOwned.remove(property);
            balance += propertyCost;
            checkForMonopoly();
        } else {
            throw new NoSuchPropertyException("You do not own " + property);
        }
    }

    /**
     * Checks if the player has a monopoly
     * @return boolean
     * @author walshj05
     */
    public boolean hasMonopoly(String colorGroup) {
        return monopolies.containsKey(colorGroup);
    }

    /**
     * Adds a house to a property
     * @param property String
     * @throws HouseCannotBeBuiltException exception
     * @author walshj05
     */
    public void addHouse(String property, String colorGroup) throws HouseCannotBeBuiltException {
        if (hasMonopoly(colorGroup)) {
            numHouses++;
        } else {
            throw new HouseCannotBeBuiltException("You do not have a monopoly on " + colorGroup);
        }
    }

    /**
     * Adds a hotel to a property
     * @param property String
     * @throws HotelCannotBeBuiltException exception
     * @author walshj05
     */
    public void addHotel(String property) throws HotelCannotBeBuiltException {
        if (numHouses == 4) {
            numHouses = 0;
            numHotels++;
        } else {
            throw new HotelCannotBeBuiltException("You do not have 4 houses on " + property);
        }
    }

    /**
     * Adds a certain amount to the player's balance
     * @param amount int
     * @author walshj05
     */
    public void addToBalance(int amount) {
        this.balance += amount;
    }

    /**
     * Subtracts a certain amount from the player's balance
     * @param amount int
     * @author walshj05
     */
    public void subtractFromBalance(int amount) {
        if (this.balance - amount < 0) {
            this.balance = 0;
            return;
        }
        this.balance -= amount;
    }

    /**
     * Checks if the player has a certain property
     * @param property String
     * @return boolean
     * @author walshj05
     */
    public boolean hasProperty(String property) {
        return propertiesOwned.contains(property);
    }

    /**
     * Adds a community chest card to the player's hand
     * @param card String
     * @author walshj05
     */
    public void addCard(String card) {
        cards.add(card);
    }

    /**
     * Removes a community chest card from the player's hand
     * @param card String
     * @author walshj05
     */
    public void removeCard(String card) {
        cards.remove(card);
    }

    /**
     * Checks if the player has a certain community chest card
     * @param card String
     * @return boolean
     * @author walshj05
     */
    public boolean hasCard(String card) {
        return cards.contains(card);
    }

    /**
     * Checks if the player is bankrupt
     * @return boolean
     * @author walshj05
     */
    public boolean isBankrupt() {
        return balance == 0;
    }

    /**
     * Returns a string representation of the player
     * @return String
     * @author walshj05
     */
    @Override
    public String toString() {
        return name + " (Token: " + token.getName() + ")";
    }

    /**
     * Checks if the player has a monopoly.
     * @author walshj05
     */
    private void checkForMonopoly() {
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
        updateMonopolies(currMonopolies);
    }

    /**
     * Gets the number of turns the player has been in jail
     * @return int
     * @author walshj05
     */
    @Override
    public int getJailTurns() {
        return jailTurns;
    }

    /**
     * Resets the number of turns the player has been in jail
     * @author walshj05
     */
    @Override
    public void resetJailTurns(){
        this.jailTurns = 0;
    }

    /**
     * Increments the number of turns the player has been in jail
     * @author walshj05
     */
    @Override
    public void incrementJailTurns(){
        this.jailTurns++;
    }

    /**
     * Updates the monopolies of the player
     * @author walshj05
     * @param monopolies ArrayList of monopolies
     */
    private void updateMonopolies(ArrayList<String> monopolies) {
        for (String monopoly : monopolies) {
            if (!this.monopolies.containsKey(monopoly)) {
                this.monopolies.put(monopoly, 0);
            }
        }
    }
}
