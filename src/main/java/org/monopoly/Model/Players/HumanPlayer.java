package org.monopoly.Model.Players;

import org.monopoly.Exceptions.*;
import org.monopoly.Model.*;

import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Cards.TitleDeedCards;
import org.monopoly.Model.GameTiles.PropertySpace;

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
     * Gets the properties owned by the player.
     * @return Properties owned by the player.
     *
     * Developed by: shifmans
     */
    public ArrayList<String> getPropertiesOwned() {
        return propertiesOwned;
    }

    /**
     * Gets the monopolies a player has.
     * @return Monopolies a player has.
     *
     * Developed by: shifmans
     */
    public ArrayList<Monopoly> getMonopolies() {
        return monopolies;
    }


    /**
     * Moves player a certain number of spaces
     * Also checks if they are in jail or not
     * @param spaces num spaces moved
     * @author walshj05
     */
    public void move(int spaces) {
        GameBoard.getInstance().removeToken(this.token, position);
        if (!inJail) {
            position = (position + spaces) % 40; // Move the player
            System.out.println(name + " moved " + spaces + " spaces to position " + position);
        } else {
            System.out.println(name + " is in jail and cannot move.");
        }
        GameBoard.getInstance().addToken(this.token, position);
    }

    /**
     * Puts player in jail
     * @author walshj05
     */
    public void goToJail() {
        inJail = true;
        position = 10;
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

    public void unmortgageProperty(String property, int mortgageValue) throws NoSuchPropertyException {
        if (propertiesMortgaged.contains(property)) {
            propertiesMortgaged.remove(property);
            propertiesOwned.add(property);
            balance -= mortgageValue; // Deduct the mortgage value from the player's balance
            System.out.println(name + " unmortgaged " + property + " for $" + mortgageValue);
        } else {
            throw new NoSuchPropertyException("You do not have this property mortgaged.");
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

    @Override
    public void buyHouse(String propertyName, ColorGroup colorGroup, int price) throws InsufficientFundsException, RuntimeException {
        if (balance - price < 0) {
            throw new InsufficientFundsException("Insufficient funds to buy a house");
        }
        if (!propertiesOwned.contains(propertyName)) {
            throw new RuntimeException("Property not registered to player.");
        }

        int index = colorGroups.indexOf(colorGroup);
        try {
            monopolies.get(index).buildHouse(propertyName);
        } catch (Exception e) {
            if (e.getMessage().equals("Index -1 out of bounds for length 0")) {
                throw new RuntimeException("Player does not have all properties in this monopoly.");
            }
            throw new RuntimeException(e.getMessage());
        }

        balance -= price;
    }

    @Override
    public void sellHouse(String propertyName, ColorGroup colorGroup) {
        if (!propertiesOwned.contains(propertyName) || !colorGroups.contains(colorGroup)) {
            throw new RuntimeException("Property not registered to player.");
        }

        int index = colorGroups.indexOf(colorGroup);
        try {
            monopolies.get(index).sellHouse(propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        TitleDeedCards cards = TitleDeedCards.getInstance();
        PropertySpace property = (PropertySpace) cards.getProperty(propertyName);
        balance += property.getHousePrice()/2;
    }

    @Override
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

        balance -= price;
    }

    @Override
    public void sellHotel(String propertyName, ColorGroup colorGroup) {
        if (!propertiesOwned.contains(propertyName) || !colorGroups.contains(colorGroup)) {
            throw new RuntimeException("Property not registered to player.");
        }

        int index = colorGroups.indexOf(colorGroup);
        try {
            monopolies.get(index).sellHotel(propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        TitleDeedCards cards = TitleDeedCards.getInstance();
        PropertySpace property = (PropertySpace) cards.getProperty(propertyName);
        balance += property.getHotelPrice()/2;
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
        banker.checkForMonopolies(propertiesOwned, monopolies, colorGroups);
        for (Monopoly monopoly : monopolies) {
            if (!colorGroups.contains(monopoly.getColorGroup())) {
                colorGroups.add(monopoly.getColorGroup());
            }
        }
    }

    @Override
    public int getNumHotels() {
        int numHotels = 0;
        for (Monopoly monopoly : monopolies) {
            int[] buildings = monopoly.getBuildings();
            for (int i = 0; i < buildings.length; i++) {
                if (buildings[i] == 5) {
                    numHotels++;
                }
            }
        }
        return numHotels;
    }

    @Override
    public int getNumHouses() {
        int numHouses = 0;
        for (Monopoly monopoly : monopolies) {
            int[] buildings = monopoly.getBuildings();
            for (int i = 0; i < buildings.length; i++) {
                if (buildings[i] > 0 && buildings[i] < 5) {
                    numHouses += buildings[i];
                }
            }
        }
        return numHouses;
    }

    @Override
    public void mortgageAssetsToRaiseFunds(int amount) throws BankruptcyException {
        for (String propertyName : propertiesOwned) {
            PropertySpace property = (PropertySpace) TitleDeedCards.getInstance().getProperty(propertyName);
            if (!property.isMortgaged()) {
                try {
                    System.out.println("Mortgaging " + property.getName());
                    mortgageProperty(property.getName(), property.getMortgageValue()); // defined in subclass
                    if (getBalance() >= amount) return;
                } catch (Exception e) {
                    System.out.println("Error mortgaging property: " + e.getMessage());
                }
            }
        }

        if (getBalance() < amount) {
            throw new BankruptcyException(getName() + " could not raise enough funds by mortgaging.");
        }
    }

    @Override
    public void sellBuildingsToRaiseFunds(int amount) throws BankruptcyException {
        for (String propertyName : propertiesOwned) {
            PropertySpace property = (PropertySpace) TitleDeedCards.getInstance().getProperty(propertyName);

            while (getBalance() < amount && property.getNumHotels() > 0) {
                try {
                    System.out.println("Selling hotel on " + property.getName());
                    sellHotel(property.getName(), property.getColorGroup()); // defined in subclass
                } catch (Exception e) {
                    System.out.println("Error selling hotel: " + e.getMessage());
                }
            }

            while (getBalance() < amount && property.getNumHouses() > 0) {
                try {
                    System.out.println("Selling house on " + property.getName());
                    sellHouse(property.getName(), property.getColorGroup()); // defined in subclass
                } catch (Exception e) {
                    System.out.println("Error selling house: " + e.getMessage());
                }
            }

            if (getBalance() >= amount) return;
        }

        if (getBalance() < amount) {
            throw new BankruptcyException(getName() + " could not raise enough funds by selling buildings.");
        }
    }

    @Override
    public void attemptToRaiseFunds(int amount) throws BankruptcyException {
        sellBuildingsToRaiseFunds(amount);
        mortgageAssetsToRaiseFunds(amount);
    }
}
