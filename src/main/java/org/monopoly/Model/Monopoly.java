package org.monopoly.Model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Monopoly class represents a monopoly with properties and houses.
 * It allows for building houses on properties and checking the number of houses on each property.
 */
public class Monopoly {
    private final ArrayList<String> properties;
    private final int[] buildings;
    //todo add a field for color group.

    /**
     * Initializes the properties array with the number of houses on each property
     * @param properties The properties to be initialized
     */
    public Monopoly(String[] properties) {
        this.properties = new ArrayList<>();
        Collections.addAll(this.properties, properties);
        buildings = new int[properties.length];
        for (int i = 0; i < properties.length; i++) {
            buildings[i] = 0;
        }
    }

    /**
     *
     * Initialize the properties array with the number of houses on each property
     * Designed for testing purposes.
     * @param properties The properties to be initialized
     * @param houses The number of houses on each property
     */
    public Monopoly(String[] properties, int[] houses) {
        this.properties = new ArrayList<>();
        Collections.addAll(this.properties, properties);
        this.buildings = houses;
    }

    /**
     * Builds a house on the property with the given name.
     * @param propertyName The name of the property to build a house on.
     */
    public void buildHouse(String propertyName) throws IllegalArgumentException {
        // Check if the property exists
        if (!properties.contains(propertyName)) {
            throw new IllegalArgumentException("Property not a part of this monopoly.");
        }

        int index = properties.indexOf(propertyName);
        Banker banker = Banker.getInstance();

        try
        {checkValidHouseBuild(banker, index);}
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid build: " + e.getMessage());
        }
        // Build the house
        buildings[index]++;
        banker.buyHouse();
    }

    private void checkValidHouseBuild(Banker banker, int index) {
        // Check if there are houses available
        if (banker.getHouses() <= 0) {
            throw new IllegalArgumentException("No houses available.");
        }

        // Check if the property already has 4 houses
        if (buildings[index] >= 4) {
            throw new IllegalArgumentException("Property already has 4 houses.");
        }

        // Check if the building rule is being followed
        if (violatesEvenBuildingRule(index, true)){
            throw new IllegalArgumentException("Even building rule not being followed.");
        }
    }

    /**
     * Returns the number of houses on the property with the given name.
     * @param propertyName The name of the property to get the number of houses on.
     * @return The number of houses on the property.
     */
    public int getNumberOfHouses(String propertyName) throws IllegalArgumentException {
        // Check if the property exists
        if (!properties.contains(propertyName)) {
            throw new IllegalArgumentException("Property not a part of this monopoly.");
        }
        int index = properties.indexOf(propertyName);
        return buildings[index];
    }

    public void buildHotel(String propertyName) throws IllegalArgumentException {
        // Check if the property exists
        if (!properties.contains(propertyName)) {
            throw new IllegalArgumentException("Property not a part of this monopoly.");
        }

        int index = properties.indexOf(propertyName);
        Banker banker = Banker.getInstance();

        try
        {checkValidHotelBuild(banker, index);}
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid build: " + e.getMessage());
        }

        // Build the hotel
        buildings[index] = 5;
        banker.buyHotel();

        // Return the houses to the bank
        banker.returnHouses(4);
    }

    private void checkValidHotelBuild(Banker banker, int index) {
        // Check if there are hotels available
        if (banker.getHotels() <= 0) {
            throw new IllegalArgumentException("No hotels available.");
        }

        // Check if the property already has a hotel
        if (buildings[index] != 4) {
            throw new IllegalArgumentException("Property does not have 4 houses.");
        }

        // Check if the building rule is being followed
        if (violatesEvenBuildingRule(index, true)){
            throw new IllegalArgumentException("Even building rule not being followed.");
        }
    }

    private boolean violatesEvenBuildingRule(int index, boolean purchase){
        if (purchase){
            for (int i = 0; i < properties.size(); i++) {
                if (i != index) {
                    if ((buildings[index] + 1) - buildings[i] > 1) {
                        return true;
                    }
                }
            }
        } else {
            for (int i = 0; i < properties.size(); i++) {
                if (i != index) {
                    if ((buildings[index] - 1) - buildings[i] < -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void sellHouse(String propertyName) throws IllegalArgumentException {
        // Check if the property exists
        if (!properties.contains(propertyName)) {
            throw new IllegalArgumentException("Property not a part of this monopoly.");
        }

        int index = properties.indexOf(propertyName);
        Banker banker = Banker.getInstance();

        // Check if the property has houses
        if (buildings[index] <= 0) {
            throw new IllegalArgumentException("Property does not have any houses.");
        }
        // Check if the building rule is being followed
        if (violatesEvenBuildingRule(index, false)){
            throw new IllegalArgumentException("Even building rule not being followed.");
        }

        // Sell the house
        buildings[index]--;
        banker.returnHouses(1);
    }

    public void sellHotel(String propertyName) throws IllegalArgumentException {
        // Check if the property exists
        if (!properties.contains(propertyName)) {
            throw new IllegalArgumentException("Property not a part of this monopoly.");
        }

        int index = properties.indexOf(propertyName);
        Banker banker = Banker.getInstance();

        // Check if the property has a hotel
        if (buildings[index] != 5) {
            throw new IllegalArgumentException("Property does not have a hotel.");
        }

        // Check if bank has at least four houses available
        if (banker.getHouses() < 4) {
            throw new IllegalArgumentException("Not enough houses available in the bank.");
        }

        // Check if the building rule is being followed
        if (violatesEvenBuildingRule(index, false)){
            throw new IllegalArgumentException("Even building rule not being followed.");
        }

        // Sell the hotel
        buildings[index] = 4;
        banker.returnHotel();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monopoly Properties:\n");
        for (int i = 0; i < properties.size(); i++) {
            sb.append(properties.get(i)).append(": ").append(buildings[i]).append(" houses\n");
        }
        return sb.toString();
    }
}