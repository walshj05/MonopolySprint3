package org.monopoly.Model.GameTiles;

import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Players.ComputerPlayer;
import org.monopoly.Model.Players.Player;

import java.util.ArrayList;

/**
 * Represents the Property Space element on the Game Board's Tiles.
 *
 * @author shifmans
 */
public class PropertySpace extends GameTile {
    private int price;
    private ArrayList<Integer> rentPrices;
    private ColorGroup colorGroup;
    private int housePrice;
    private int hotelPrice;
    private int mortgageValue;
    private int unmortgageValue;
    private boolean isMortgaged;
    private int numHouses;
    private int numHotels;
    private String owner;

    /**
     * Constructor to initialize a PropertySpace with all information.
     * @param name Name of a PropertySpace.
     * @param actions Actions for a PropertySpace.
     * @param price Price for a PropertySpace.
     * @param rentPrices Rent Prices for a PropertySpace.
     * @param colorGroup Color Group for a PropertySpace.
     * @param housePrice House Price for a PropertySpace.
     * @param hotelPrice Hotel Price for a PropertySpace.
     * @param mortgageValue Mortgage Value for a PropertySpace.
     *
     * Developed by: shifmans
     */
    public PropertySpace(String name, String actions, int price, ArrayList<Integer> rentPrices, ColorGroup colorGroup, int housePrice, int hotelPrice, int mortgageValue) {
        super(name, actions);
        this.price = price;
        this.rentPrices = rentPrices;
        this.colorGroup = colorGroup;
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
        this.mortgageValue = mortgageValue;
        this.unmortgageValue = mortgageValue + (int) (mortgageValue * 0.1); //Mortgage value plus 10% interest
        this.isMortgaged = false;
        this.numHouses = 0;
        this.numHotels = 0;
        this.owner = "";
    }

    /**
     * Shows the actions that occur after a player lands on element space.
     * @return Displays the actions for landing on element space.
     *
     * Developed by: shifmans
     */
    @Override
    public String landOn() {
        actions += displayPropertyInfo();
        return actions;
    }

    /**
     * Player is shown information about Property after landing on element space.
     * @return Information on Property Space.
     *
     * Developed by: shifmans
     */
    private String displayPropertyInfo() {
        return "Property Name: " + getName() + "\n" +
                "Color Set: " + getColorGroup() + "\n" +
                "Purchase Price: $" + getPrice() + "\n" +
                "Rent (without houses/hotels): $" + getRentPrices().get(0) + "\n" +
                "Rent with 1 House: $" + getRentPrices().get(1) + "\n" +
                "Rent with 2 Houses: $" + getRentPrices().get(2) + "\n" +
                "Rent with 3 Houses: $" + getRentPrices().get(3) + "\n" +
                "Rent with 4 Houses: $" + getRentPrices().get(4) + "\n" +
                "Rent with Hotel: $" + getRentPrices().get(5) + "\n" +
                "Mortgage Value: $" + getMortgageValue() + "\n" +
                "House Price: $" + getHousePrice() + "\n" +
                "Hotel Price: $" + getHotelPrice();
    }

    /**
     * Gets the price of a PropertySpace.
     * @return The price of a PropertySpace.
     *
     * Developed by: shifmans
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gets the rent price based on the number of buildings built on property.
     * @param numBuildingsBuilt The number of buildings built on property.
     * @return The rent price based on the number of buildings built on property.
     *
     * Developed by: shifmans
     */
    @Override
    public int getRentPrice(int numBuildingsBuilt) {
        return rentPrices.get(numBuildingsBuilt);
    }

    /**
     * Gets the rent prices of a PropertySpace.
     * @return The list of rent prices of a PropertySpace.
     *
     * Developed by: shifmans
     */
    public ArrayList<Integer> getRentPrices() {
        return rentPrices;
    }

    /**
     * Gets the color group of a PropertySpace.
     * @return The color group of a PropertySpace.
     *
     * Developed by: shifmans
     */
    public ColorGroup getColorGroup() {
        return colorGroup;
    }

    /**
     * Gets the house price of a PropertySpace.
     * @return The house price of a PropertySpace.
     *
     * Developed by: shifmans
     */
    public int getHousePrice() {
        return housePrice;
    }

    /**
     * Gets the hotel price of a PropertySpace.
     * @return The hotel price of a PropertySpace.
     *
     * Developed by: shifmans
     */
    public int getHotelPrice() {
        return hotelPrice;
    }

    /**
     * Gets the mortgage value of a PropertySpace.
     * @return The mortgage value of a PropertySpace.
     *
     * Developed by: shifmans
     */
    public int getMortgageValue() {
        return mortgageValue;
    }

    /**
     * Gets the unmortgage value of a PropertySpace.
     * @return The unmortgage value of a PropertySpace.
     */
    public int getUnmortgageValue() {
        return unmortgageValue;
    }

    /**
     * Gets the mortgaged status of a PropertySpace.
     * @return The mortgaged status of a PropertySpace.
     *
     * Developed by: shifmans
     */
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /**
     * Sets the mortgaged status of a PropertySpace.
     * @param isMortgaged The mortgaged status of a PropertySpace.
     *
     * Developed by: shifmans
     */
    public void setMortgagedStatus(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
    }

    /**
     * Gets the number of houses on a PropertySpace.
     * @return The number of houses on a PropertySpace.
     *
     * Developed by: shifmans
     */
    public int getNumHouses() {
        return numHouses;
    }

    /**
     * Sets the number of houses on a PropertySpace.
     * @param numHouses The number of houses on a PropertySpace.
     *
     * Developed by: shifmans
     */
    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    /**
     * Gets the number of hotels on a PropertySpace.
     * @return The number of hotels on a PropertySpace.
     *
     * Developed by: shifmans
     */
    public int getNumHotels() {
        return numHotels;
    }

    /**
     * Sets the number of hotels on a PropertySpace.
     * @param numHotels The number of hotels on a PropertySpace.
     *
     * Developed by: shifmans
     */
    public void setNumHotels(int numHotels) {
        this.numHotels = numHotels;
    }

    /**
     * Gets the owner of a PropertySpace.
     * @return The owner of a PropertySpace.
     *
     * Developed by: shifmans
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of a PropertySpace.
     * @param owner The owner of a PropertySpace.
     *
     * Developed by: shifmans
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Executes the strategy for the PropertySpace.
     * @author crevelings
     * Modified by: crevelings (4/8/25) Configured for CPU
     */
    @Override
    public void executeStrategy(Player player) {
        if (player.hasProperty(getName())) {
            System.out.println("You already own the " + getName() + "!");
        } else {
            if (owner == null || owner.isEmpty()) { // Proper null check
                System.out.println("You can buy the " + getName() + " for $" + price);
                System.out.println("Or property can be auctioned");
            } else {
                System.out.println(getOwner() + " already owns the " + getName() + "!");
                if (player.getClass() == ComputerPlayer.class){
                    ((ComputerPlayer) player).handleLanding(this.rentPrices);
                }
            }
            try {
                player.purchaseProperty(getName(), price);
                System.out.println("You bought the " + getName());
            } catch (InsufficientFundsException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
