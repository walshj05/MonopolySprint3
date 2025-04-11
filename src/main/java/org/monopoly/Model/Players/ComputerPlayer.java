package org.monopoly.Model.Players;

import org.monopoly.Exceptions.BankruptcyException;
import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Exceptions.NoSuchPropertyException;
import org.monopoly.Model.*;
import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Cards.TitleDeedCards;
import org.monopoly.Model.GameTiles.GameTile;
import org.monopoly.Model.GameTiles.PropertySpace;

import java.util.*;

/**
 * A class that will represent a computer player in the game of Monopoly.
 * This class has a random number generator that will be used to determine the moves of the player.
 * @author walshj05, crevelings
 *
 */
public class ComputerPlayer extends Player {
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
     *
     * @param name  String
     * @param token Token
     * @author walshj05
     */
    public ComputerPlayer(String name, Token token) {
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
     *
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
     *
     * Developed by: shifmans
     */
    public ArrayList<String> getPropertiesOwned() {
        return propertiesOwned;
    }

    /**
     * Gets the properties that are mortgaged.
     * @return Properties that are mortgaged.
     *
     * Developed by: shifmans
     */
    public ArrayList<String> getPropertiesMortgaged() {
        return propertiesMortgaged;
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
     *
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
     *
     * @author walshj05
     */
    public void goToJail() {
        inJail = true;
        position = 10;
        System.out.println(name + " has been sent to jail!");
    }

    /**
     * Checks to see if the player is in jail
     *
     * @return boolean
     * @author walshj05
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Releases player from jail
     *
     * @author walshj05
     */
    public void releaseFromJail() {
        inJail = false;
        jailTurns = 0;
        System.out.println(name + " has been released from jail!");
    }

    /**
     * A method for a player to take a turn in the game
     *
     * @param dice Dice object
     * @author walshj05
     */
    public void takeTurn(Dice dice) {
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
     *
     * @param property String
     * @throws InsufficientFundsException exception
     * @author walshj05
     *
     * Modified by: shifmans
     */
    public void purchaseProperty(String property, int price) throws InsufficientFundsException {
        boolean decision;

        if (balance > 500) {
            decision = runOdds(0.85);
        } else {
            decision = runOdds(0.50);
        }

        if (!decision) {
            System.out.println("Player has decided not to purchase " + property);
            return;
        }

        if (balance >= price) {
            propertiesOwned.add(property);
            balance -= price;
            updateMonopolies();
        } else {
            throw new InsufficientFundsException("Insufficient funds to purchase " + property);
        }
    }

    /**
     * Player mortgages a property
     *
     * @param property String
     * @throws NoSuchPropertyException exception
     * @author crevelings
     * Modified by: shifmans
     */
    public void mortgageProperty(String property, int mortgageCost) throws NoSuchPropertyException {
        boolean decision;

        if (balance > 500) {
            decision = runOdds(0.35);
        } else {
            decision = runOdds(0.65);
        }

        if (!decision) {
            System.out.println("Player has decided not to mortgage " + property);
            return;
        }

        if (propertiesOwned.contains(property)) {
            propertiesOwned.remove(property);
            propertiesMortgaged.add(property);
            balance += mortgageCost;
        } else {
            throw new NoSuchPropertyException("You do not own " + property);
        }
    }

    /**
     * Unmortgages a property for the player.
     * @param property The name of the property.
     * @param mortgageValue The value to unmortgage the property.
     * @throws NoSuchPropertyException If the property is not owned or has not been mortgaged.
     * @author crevelings
     * Modified by: shifmans
     */
    public void unmortgageProperty(String property, int mortgageValue) throws NoSuchPropertyException {
        boolean decision;

        if (balance > 500) {
            decision = runOdds(0.65);
        } else {
            decision = runOdds(0.35);
        }

        if (!decision) {
            System.out.println("Player has decided not to unmortgage " + property);
            return;
        }

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
     *
     * @param property String
     * @throws NoSuchPropertyException exception
     * @author crevelings
     * Modified by: shifmans
     */
    public void sellProperty(String property, int propertyCost) throws NoSuchPropertyException {
        boolean decision;

        if (balance > 500) {
            decision = runOdds(0.35);
        } else {
            decision = runOdds(0.65);
        }

        if (!decision) {
            System.out.println("Player has decided not to sell " + property);
            return;
        }

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
     *
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
     *
     * @param amount int amount
     * @author walshj05
     */
    public void addToBalance(int amount) {
        this.balance += amount;
    }

    /**
     * Subtracts a certain amount from the player's balance
     *
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
     *
     * @param property String
     * @return boolean
     * @author walshj05
     */
    public boolean hasProperty(String property) {
        return propertiesOwned.contains(property);
    }

    /**
     * Adds a card to the player's hand
     *
     * @param card String
     * @author walshj05
     */
    public void addCard(String card) {
        cards.add(card);
    }

    /**
     * Removes a card from the player's hand
     *
     * @param card String
     * @author walshj05
     */
    public void removeCard(String card) {
        cards.remove(card);
    }

    /**
     * Checks if the player has a certain community chest card
     *
     * @param card String
     * @return boolean
     * @author walshj05
     */
    public boolean hasCard(String card) {
        return cards.contains(card);
    }

    /**
     * Checks if the player is bankrupt
     *
     * @return boolean
     * @author walshj05
     */
    public boolean isBankrupt() {
        return balance == 0;
    }

    /**
     * Modified by: shifmans
     */
    @Override
    public void buyHouse(String propertyName, ColorGroup colorGroup, int price) throws InsufficientFundsException {
        boolean decision;

        if (balance > 500) {
            decision = runOdds(0.75);
        } else {
            decision = runOdds(0.25);
        }

        if (!decision) {
            System.out.println("Player has decided not to buy a house on " + propertyName);
            return;
        }

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

    /**
     * Modified by: shifmans
     */
    @Override
    public void sellHouse(String propertyName, ColorGroup colorGroup) {
        boolean decision;

        if (balance > 500) {
            decision = runOdds(0.35);
        } else {
            decision = runOdds(0.65);
        }

        if (!decision) {
            System.out.println("Player has decided not to sell a house on " + propertyName);
            return;
        }

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

    /**
     * Modified by: shifmans
     */
    @Override
    public void buyHotel(String propertyName, ColorGroup colorGroup, int price) throws InsufficientFundsException {
        boolean decision;

        if (balance > 750) {
            decision = runOdds(0.75);
        } else {
            decision = runOdds(0.25);
        }

        if (!decision) {
            System.out.println("Player has decided not to buy a hotel on " + propertyName);
            return;
        }

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

    /**
     * Modified by: shifmans
     */
    @Override
    public void sellHotel(String propertyName, ColorGroup colorGroup) {
        boolean decision;

        if (balance > 750) {
            decision = runOdds(0.35);
        } else {
            decision = runOdds(0.65);
        }

        if (!decision) {
            System.out.println("Player has decided not to sell a hotel on " + propertyName);
            return;
        }

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
     *
     * @return int
     * @author walshj05
     */
    @Override
    public int getJailTurns() {
        return jailTurns;
    }

    /**
     * Resets the number of turns the player has been in jail
     *
     * @author walshj05
     */
    @Override
    public void resetJailTurns() {
        this.jailTurns = 0;
    }

    /**
     * Increments the number of turns the player has been in jail
     *
     * @author walshj05
     */
    @Override
    public void incrementJailTurns() {
        this.jailTurns++;
    }

    /**
     * Updates the monopolies of the player
     *
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

    /**
     * Determines if the player will run a certain odd
     *
     * @param odd Likelihood of the event occurring
     * @return Whether the event will likely occur
     *
     * Developed by: shifmans
     */
    public boolean runOdds(double odd) {
        Random rand = new Random(System.nanoTime());

        return rand.nextDouble() <= odd;
    }

    /**
     * Handles the computer player landing on a property space
     * @param rentPrices list of prices for rent of a space
     * @author crevelings (4/7/25)
     */
    public void handleLanding( ArrayList<Integer> rentPrices) {
        GameTile space = GameBoard.getInstance().getTile(position);
        Banker banker = Banker.getInstance();
        TurnManager turnManager = TurnManager.getInstance();
        System.out.println(name + " landed on " + space.getName());

        // Check if property is owned
        if (Objects.equals(space.getOwner(), "")) {
            try {
                purchaseProperty(space.getName(), space.getPrice());
                System.out.println(name + " bought " + space.getName() + " for $" + space.getPrice());
                space.setOwner(name);
            } catch (InsufficientFundsException e) {
                System.out.println(name + " could not afford " + space.getName() + ". Property will be auctioned.");
                banker.auctionProperty(space.getName(), turnManager.getPlayers());
            }
        } else if (!space.getOwner().equals(name)) {
            int baseRent = rentPrices.getFirst();
            System.out.println(name + " pays rent of $" + baseRent + " to " + space.getOwner());
            subtractFromBalance(baseRent);
        } else {
            System.out.println(name + " already owns " + space.getName());
        }
    }

    /**
     * Handles the chances of players at the end of their turn and the processes that they can do and the chances of CPU doing these processes
     * @throws InsufficientFundsException No money
     * @throws NoSuchPropertyException Property isn't there anymore
     */
    /*
    public void handleEndOfTurn() throws InsufficientFundsException, NoSuchPropertyException {
        Random random = new Random(System.nanoTime());
        int index = random.nextInt(propertiesOwned.size());
        PropertySpace space = (PropertySpace) TitleDeedCards.getInstance().getProperty(propertiesOwned.get(index));
        String spaceName = space.getName();
        ColorGroup spaceColor = space.getColorGroup();

        if (runOdds(0.9)){
            buyHouse(spaceName, spaceColor, space.getHousePrice());
        } else {
            sellHouse(spaceName, spaceColor);
        }

        if (runOdds(0.9)){
            buyHotel(spaceName, spaceColor, space.getHotelPrice());
        } else {
            sellHotel(spaceName, spaceColor);
        }

        if (runOdds(0.5)){
            sellProperty(spaceName, space.getPrice());
        } else {
            mortgageProperty(spaceName, space.getMortgageValue());
        }
    }
     */

    /**
     * Gives the number of hotels the player owns
     * @return numHotels
     * @author crevelings and walshj05
     */
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

    /**
     * Gives the number of houses the player owns
     * @return numHouses
     * @author crevelings and walshj05
     */
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

    /**
     * Process for having players mortgage their assets off when they are in debt
     * @param amount
     * @throws BankruptcyException
     * @author crevelings
     */
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

    /**
     * Process for having players sell their buildings off when they are in debt
     * @param amount
     * @throws BankruptcyException
     * @author crevelings
     */
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

    /**
     * Calls both debt helper methods
     * @param amount
     * @throws BankruptcyException
     * @author crevelings
     */
    @Override
    public void attemptToRaiseFunds(int amount) throws BankruptcyException {
        sellBuildingsToRaiseFunds(amount);
        mortgageAssetsToRaiseFunds(amount);
    }
}