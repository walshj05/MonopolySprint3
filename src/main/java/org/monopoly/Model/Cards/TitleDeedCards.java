package org.monopoly.Model.Cards;

import org.monopoly.Model.GameTiles.*;
import java.util.*;

/**
 * TitleDeedCards class represents the title deed cards in the game of Monopoly.
 * It initializes the properties with their respective details such as name, price, rent prices, color group, etc.
 *
 * Developed by: shifmans
 */
public class TitleDeedCards {
    private final HashMap<String, GameTile> properties;
    private static TitleDeedCards instance;

    /**
     * Constructor for TitleDeedCards.
     * Initializes the properties with their respective details.
     *
     * Developed by: shifmans
     */
    public TitleDeedCards() {
        this.properties = new HashMap<>();
        initializeCards();
    }

    public static TitleDeedCards getInstance() {
        if (instance == null) {
            instance = new TitleDeedCards();
        }
        return instance;
    }
    /**
     * Initializes the properties with their respective details.
     *
     * Developed by: shifmans
     */
    private void initializeCards() {
        ArrayList<Integer> rentPrices;

        rentPrices = new ArrayList<>(Arrays.asList(2, 10, 30, 90, 160, 250));
        properties.put("Mediterranean Avenue", new PropertySpace("Mediterranean Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 60, rentPrices, ColorGroup.BROWN, 50, 50, 30));
        rentPrices = new ArrayList<>(Arrays.asList(4, 20, 60, 180, 320, 450));
        properties.put("Baltic Avenue", new PropertySpace("Baltic Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 60, rentPrices, ColorGroup.BROWN, 50, 50, 30));

        rentPrices = new ArrayList<>(Arrays.asList(6, 30, 90, 270, 400, 550));
        properties.put("Oriental Avenue", new PropertySpace("Oriental Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 100, rentPrices, ColorGroup.LIGHT_BLUE, 50, 50, 50));
        rentPrices = new ArrayList<>(Arrays.asList(6, 30, 90, 270, 400, 550));
        properties.put("Vermont Avenue", new PropertySpace("Vermont Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 100, rentPrices, ColorGroup.LIGHT_BLUE, 50, 50, 50));
        rentPrices = new ArrayList<>(Arrays.asList(8, 40, 100, 300, 450, 600));
        properties.put("Connecticut Avenue", new PropertySpace("Connecticut Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 120, rentPrices, ColorGroup.LIGHT_BLUE, 50, 50, 60));

        rentPrices = new ArrayList<>(Arrays.asList(10, 50, 150, 450, 625, 750));
        properties.put("St. Charles Place", new PropertySpace("St. Charles Place", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 140, rentPrices, ColorGroup.PINK, 100, 100, 70));
        rentPrices = new ArrayList<>(Arrays.asList(10, 50, 150, 450, 625, 750));
        properties.put("States Avenue", new PropertySpace("States Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 140, rentPrices, ColorGroup.PINK, 100, 100, 70));
        rentPrices = new ArrayList<>(Arrays.asList(12, 60, 180, 500, 700, 900));
        properties.put("Virginia Avenue", new PropertySpace("Virginia Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 160, rentPrices, ColorGroup.PINK, 100, 100, 80));

        rentPrices = new ArrayList<>(Arrays.asList(14, 70, 200, 550, 750, 950));
        properties.put("St. James Place", new PropertySpace("St. James Place", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 180, rentPrices, ColorGroup.ORANGE, 100, 100, 90));
        rentPrices = new ArrayList<>(Arrays.asList(14, 70, 200, 550, 750, 950));
        properties.put("Tennessee Avenue", new PropertySpace("Tennessee Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 180, rentPrices, ColorGroup.ORANGE, 100, 100, 90));
        rentPrices = new ArrayList<>(Arrays.asList(16, 80, 220, 600, 800, 1000));
        properties.put("New York Avenue", new PropertySpace("New York Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 200, rentPrices, ColorGroup.ORANGE, 100, 100, 100));

        rentPrices = new ArrayList<>(Arrays.asList(18, 90, 250, 700, 875, 1050));
        properties.put("Kentucky Avenue", new PropertySpace("Kentucky Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 220, rentPrices, ColorGroup.RED, 150, 150, 110));
        rentPrices = new ArrayList<>(Arrays.asList(18, 90, 250, 700, 875, 1050));
        properties.put("Indiana Avenue", new PropertySpace("Indiana Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 220, rentPrices, ColorGroup.RED, 150, 150, 110));
        rentPrices = new ArrayList<>(Arrays.asList(20, 100, 300, 750, 925, 1100));
        properties.put("Illinois Avenue", new PropertySpace("Illinois Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 240, rentPrices, ColorGroup.RED, 150, 150, 120));

        rentPrices = new ArrayList<>(Arrays.asList(22, 110, 330, 800, 975, 1150));
        properties.put("Atlantic Avenue", new PropertySpace("Atlantic Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 260, rentPrices, ColorGroup.YELLOW, 150, 150, 130));
        rentPrices = new ArrayList<>(Arrays.asList(22, 110, 330, 800, 975, 1150));
        properties.put("Ventnor Avenue", new PropertySpace("Ventnor Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 260, rentPrices, ColorGroup.YELLOW, 150, 150, 130));
        rentPrices = new ArrayList<>(Arrays.asList(24, 120, 360, 850, 1025, 1200));
        properties.put("Marvin Gardens", new PropertySpace("Marvin Gardens", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 280, rentPrices, ColorGroup.YELLOW, 150, 150, 140));

        rentPrices = new ArrayList<>(Arrays.asList(26, 130, 390, 900, 1100, 1275));
        properties.put("Pacific Avenue", new PropertySpace("Pacific Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 300, rentPrices, ColorGroup.GREEN, 200, 200, 150));
        rentPrices = new ArrayList<>(Arrays.asList(26, 130, 390, 900, 1100, 1275));
        properties.put("North Carolina Avenue", new PropertySpace("North Carolina Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 300, rentPrices, ColorGroup.GREEN, 200, 200, 150));
        rentPrices = new ArrayList<>(Arrays.asList(28, 150, 450, 1000, 1200, 1400));
        properties.put("Pennsylvania Avenue", new PropertySpace("Pennsylvania Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 320, rentPrices, ColorGroup.GREEN, 200, 200, 160));

        rentPrices = new ArrayList<>(Arrays.asList(35, 175, 500, 1100, 1300, 1500));
        properties.put("Park Place", new PropertySpace("Park Place", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 350, rentPrices, ColorGroup.DARK_BLUE, 200, 200, 175));
        rentPrices = new ArrayList<>(Arrays.asList(50, 200, 600, 1400, 1700, 2000));
        properties.put("Boardwalk", new PropertySpace("Boardwalk", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 400, rentPrices, ColorGroup.DARK_BLUE, 200, 200, 200));

        rentPrices = new ArrayList<>(Arrays.asList(25, 50, 100, 200));
        properties.put("Reading Railroad", new RailroadSpace("Reading Railroad", "Buy Property, Pay Rent, Mortgage", 200, rentPrices, ColorGroup.RAILROAD, 100));
        rentPrices = new ArrayList<>(Arrays.asList(25, 50, 100, 200));
        properties.put("Pennsylvania Railroad", new RailroadSpace("Pennsylvania Railroad", "Buy Property, Pay Rent, Mortgage", 200, rentPrices, ColorGroup.RAILROAD, 100));
        rentPrices = new ArrayList<>(Arrays.asList(25, 50, 100, 200));
        properties.put("B&O Railroad", new RailroadSpace("B&O Railroad", "Buy Property, Pay Rent, Mortgage", 200, rentPrices, ColorGroup.RAILROAD, 100));
        rentPrices = new ArrayList<>(Arrays.asList(25, 50, 100, 200));
        properties.put("Short Line Railroad", new RailroadSpace("Short Line Railroad", "Buy Property, Pay Rent, Mortgage", 200, rentPrices, ColorGroup.RAILROAD, 100));

        rentPrices = new ArrayList<>(Arrays.asList(4, 10));
        properties.put("Electric Company", new ElectricCompanySpace("Buy Property, Pay Rent, Mortgage", 150, rentPrices, ColorGroup.UTILITY, 75));
        rentPrices = new ArrayList<>(Arrays.asList(4, 10));
        properties.put("Water Works", new WaterWorksSpace("Buy Property, Pay Rent, Mortgage", 150, rentPrices, ColorGroup.UTILITY, 75));
    }

    /**
     * Returns the properties of the TitleDeedCards.
     * @return HashMap of properties.
     *
     * Developed by: shifmans
     */
    public HashMap<String, GameTile> getProperties() {
        return this.properties;
    }

    /**
     * Returns a specific property based on its name.
     * @param propertyName Name of the property.
     * @return GameTile object representing the property.
     *
     * Developed by: shifmans
     */
    public GameTile getProperty(String propertyName) {
        return this.properties.get(propertyName);
    }
}
