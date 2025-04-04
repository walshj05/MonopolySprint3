package org.monopoly.Model.Players;

import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Exceptions.NoSuchPropertyException;
import org.monopoly.Model.Banker;
import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Dice;
import org.monopoly.Model.Monopoly;

import java.util.ArrayList;

/**
 * A class representing a player in the Monopoly game.
 * More functionality will be added
 * @author walshj05, crevelings
 */
public class HumanPlayer extends Player {
    private final String name;
    private int position;
    private int balance;
    private boolean inJail;
    private final Token token;
    private final ArrayList<String> propertiesOwned;
    private final ArrayList<String> propertiesMortgaged;
    private final ArrayList<Monopoly> monopolies;
    private final ArrayList<ColorGroup> colorGroups;
    private final ArrayList<String> cards;
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
        this.propertiesOwned = new ArrayList<>();
        this.propertiesMortgaged = new ArrayList<>();
        this.monopolies = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.colorGroups = new ArrayList<>();
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
        jailTurns = 0;
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
            updateMonopolies();
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
            updateMonopolies();
        } else {
            throw new NoSuchPropertyException("You do not own " + property);
        }
    }

    /**
     * Checks if the player has a monopoly
     * @return boolean
     * @author walshj05
     */
    public boolean hasMonopoly(ColorGroup colorGroup) {
        for (Monopoly monopoly : monopolies) {
            if (monopoly.getColorGroup() == colorGroup) {
                return true;
            }
        }
        return false;
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

    public void buyHouse(String propertyName, ColorGroup colorGroup, int price) throws InsufficientFundsException {
        if (balance - price < 0) {
            throw new InsufficientFundsException("Insufficient funds to buy a house");
        }
        if (!propertiesOwned.contains(propertyName) || !colorGroups.contains(colorGroup)) {
            throw new RuntimeException("Property not registered to player.");
        }

        int index = colorGroups.indexOf(colorGroup);
        try {
            monopolies.get(index).buildHouse(propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void sellHouse(String propertyName, ColorGroup colorGroup) throws InsufficientFundsException {
        if (!propertiesOwned.contains(propertyName) || !colorGroups.contains(colorGroup)) {
            throw new RuntimeException("Property not registered to player.");
        }

        int index = colorGroups.indexOf(colorGroup);
        try {
            monopolies.get(index).sellHouse(propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void buyHotel(String propertyName, ColorGroup colorGroup, int price) throws InsufficientFundsException {
        if (balance - price < 0) {
            throw new InsufficientFundsException("Insufficient funds to buy a hotel");
        }
        if (!propertiesOwned.contains(propertyName) || !colorGroups.contains(colorGroup)) {
            throw new RuntimeException("Property not registered to player.");
        }

        int index = colorGroups.indexOf(colorGroup);
        try {
            monopolies.get(index).buildHotel(propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void sellHotel(String propertyName, ColorGroup colorGroup) throws InsufficientFundsException {
        if (!propertiesOwned.contains(propertyName) || !colorGroups.contains(colorGroup)) {
            throw new RuntimeException("Property not registered to player.");
        }

        int index = colorGroups.indexOf(colorGroup);
        try {
            monopolies.get(index).sellHotel(propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
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
     */
    private void updateMonopolies() {
        Banker banker = Banker.getInstance();
        for (Monopoly monopoly : monopolies) {
            if (!colorGroups.contains(monopoly.getColorGroup())) {
                colorGroups.add(monopoly.getColorGroup());
            }
        }
        banker.checkForMonopolies(propertiesOwned, monopolies, colorGroups);
    }
}
