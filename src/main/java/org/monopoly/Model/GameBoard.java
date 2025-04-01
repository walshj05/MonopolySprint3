package org.monopoly.Model;

import org.monopoly.Model.Cards.ChanceDeck;
import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Cards.CommunityChestDeck;
import org.monopoly.Model.GameTiles.*;
import org.monopoly.Model.Players.Player;

import java.util.*;


/**
 * @author crevelings
 * This class represents the gameboard for the game.
 *
 */
public class GameBoard {
    private List<GameTile> tiles;
    private CommunityChestDeck communityChestDeck;
    private ChanceDeck chanceDeck;

    /**
     * Constructs a GameSpace object
     */
    public GameBoard() {
        this.tiles = new ArrayList<>();
        this.communityChestDeck = new CommunityChestDeck();
        this.chanceDeck = new ChanceDeck();
        initializeBoard();
    }

    /**
     * Initializes the Monopoly GameBoard with all 40 spaces (including Chance and Community Chest Spaces)
     */
    private void initializeBoard() {
        ArrayList<Integer> rentPrices = new ArrayList<>();

        tiles.add(new GoSpace());
        setPropertyRentPrices(rentPrices, 2, 10, 30, 90, 160, 250);
        tiles.add(new PropertySpace("Mediterranean Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 60, rentPrices, ColorGroup.BROWN, 50, 50, 30));
        tiles.add(new CommunityChestSpace(communityChestDeck));
        setPropertyRentPrices(rentPrices, 4, 20, 60, 180, 320, 450);
        tiles.add(new PropertySpace("Baltic Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 60, rentPrices, ColorGroup.BROWN, 50, 50, 30));
        tiles.add(new IncomeTaxSpace());
        setRailroadRentPrices(rentPrices);
        tiles.add(new RailroadSpace("Reading Railroad", "Buy Property, Pay Rent, Mortgage", 200, rentPrices, ColorGroup.RAILROAD, 100));
        setPropertyRentPrices(rentPrices, 6, 30, 90, 270, 400, 550);
        tiles.add(new PropertySpace("Oriental Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 100, rentPrices, ColorGroup.LIGHT_BLUE, 50, 50, 50));
        tiles.add(new ChanceSpace(chanceDeck));
        setPropertyRentPrices(rentPrices, 6, 30, 90, 270, 400, 550);
        tiles.add(new PropertySpace("Vermont Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 100, rentPrices, ColorGroup.LIGHT_BLUE, 50, 50, 50));
        setPropertyRentPrices(rentPrices, 8, 40, 100, 300, 450, 600);
        tiles.add(new PropertySpace("Connecticut Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 120, rentPrices, ColorGroup.LIGHT_BLUE, 50, 50, 60));
        tiles.add(new JailSpace());
        setPropertyRentPrices(rentPrices, 10, 50, 150, 450, 625, 750);
        tiles.add(new PropertySpace("St. Charles Place", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 140, rentPrices, ColorGroup.PINK, 100, 100, 70));
        setUtilitiesRentPriceMultiplier(rentPrices);
        tiles.add(new ElectricCompanySpace("Buy Property, Pay Rent, Mortgage", 150, rentPrices, ColorGroup.UTILITY, 75));
        setPropertyRentPrices(rentPrices, 10, 50, 150, 450, 625, 750);
        tiles.add(new PropertySpace("States Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 140, rentPrices, ColorGroup.PINK, 100, 100, 70));
        setPropertyRentPrices(rentPrices, 12, 60, 180, 500, 700, 900);
        tiles.add(new PropertySpace("Virginia Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 160, rentPrices, ColorGroup.PINK, 100, 100, 80));
        setRailroadRentPrices(rentPrices);
        tiles.add(new RailroadSpace("Pennsylvania Railroad", "Buy Property, Pay Rent, Mortgage", 200, rentPrices, ColorGroup.RAILROAD, 100));
        setPropertyRentPrices(rentPrices, 14, 70, 200, 550, 750, 950);
        tiles.add(new PropertySpace("St. James Place", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 180, rentPrices, ColorGroup.ORANGE, 100, 100, 90));
        tiles.add(new CommunityChestSpace(communityChestDeck));
        setPropertyRentPrices(rentPrices, 14, 70, 200, 550, 750, 950);
        tiles.add(new PropertySpace("Tennessee Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 180, rentPrices, ColorGroup.ORANGE, 100, 100, 90));
        setPropertyRentPrices(rentPrices, 16, 80, 220, 600, 800, 1000);
        tiles.add(new PropertySpace("New York Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 200, rentPrices, ColorGroup.ORANGE, 100, 100, 100));
        tiles.add(new FreeParkingSpace());
        setPropertyRentPrices(rentPrices, 18, 90, 250, 700, 875, 1050);
        tiles.add(new PropertySpace("Kentucky Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 220, rentPrices, ColorGroup.RED, 150, 150, 110));
        tiles.add(new ChanceSpace(chanceDeck));
        setPropertyRentPrices(rentPrices, 18, 90, 250, 700, 875, 1050);
        tiles.add(new PropertySpace("Indiana Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 220, rentPrices, ColorGroup.RED, 150, 150, 110));
        setPropertyRentPrices(rentPrices, 20, 100, 300, 750, 925, 1100);
        tiles.add(new PropertySpace("Illinois Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 240, rentPrices, ColorGroup.RED, 150, 150, 120));
        setRailroadRentPrices(rentPrices);
        tiles.add(new RailroadSpace("B&O Railroad", "Buy Property, Pay Rent, Mortgage", 200, rentPrices, ColorGroup.RAILROAD, 100));
        setPropertyRentPrices(rentPrices, 22, 110, 330, 800, 975, 1150);
        tiles.add(new PropertySpace("Atlantic Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 260, rentPrices, ColorGroup.YELLOW, 150, 150, 130));
        setPropertyRentPrices(rentPrices, 22, 110, 330, 800, 975, 1150);
        tiles.add(new PropertySpace("Ventnor Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 260, rentPrices, ColorGroup.YELLOW, 150, 150, 130));
        setUtilitiesRentPriceMultiplier(rentPrices);
        tiles.add(new WaterWorksSpace("Buy Property, Pay Rent, Mortgage", 150, rentPrices, ColorGroup.UTILITY, 75));
        setPropertyRentPrices(rentPrices, 24, 120, 360, 850, 1025, 1200);
        tiles.add(new PropertySpace("Marvin Gardens", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 280, rentPrices, ColorGroup.YELLOW, 150, 150, 140));
        tiles.add(new GoToJailSpace());
        setPropertyRentPrices(rentPrices, 26, 130, 390, 900, 1100, 1275);
        tiles.add(new PropertySpace("Pacific Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 300, rentPrices, ColorGroup.GREEN, 200, 200, 150));
        setPropertyRentPrices(rentPrices, 26, 130, 390, 900, 1100, 1275);
        tiles.add(new PropertySpace("North Carolina Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 300, rentPrices, ColorGroup.GREEN, 200, 200, 150));
        tiles.add(new CommunityChestSpace(communityChestDeck));
        setPropertyRentPrices(rentPrices, 28, 150, 450, 1000, 1200, 1400);
        tiles.add(new PropertySpace("Pennsylvania Avenue", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 320, rentPrices, ColorGroup.GREEN, 200, 200, 160));
        setRailroadRentPrices(rentPrices);
        tiles.add(new RailroadSpace("Short Line Railroad", "Buy Property, Pay Rent, Mortgage", 200, rentPrices, ColorGroup.RAILROAD, 100));
        tiles.add(new ChanceSpace(chanceDeck));
        setPropertyRentPrices(rentPrices, 35, 175, 500, 1100, 1300, 1500);
        tiles.add(new PropertySpace("Park Place", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 350, rentPrices, ColorGroup.DARK_BLUE, 200, 200, 175));
        tiles.add(new LuxuryTaxSpace());
        setPropertyRentPrices(rentPrices, 50, 200, 600, 1400, 1700, 2000);
        tiles.add(new PropertySpace("Boardwalk", "Buy Property, Pay Rent, Buy Houses/Hotel, Mortgage", 400, rentPrices, ColorGroup.DARK_BLUE, 200, 200, 200));
    }

    private void setPropertyRentPrices(ArrayList<Integer> rentPrices, int basicRent, int oneHouseRent, int twoHouseRent, int threeHouseRent, int fourHouseRent, int hotelRent) {
        rentPrices.clear();


        rentPrices.add(basicRent);
        rentPrices.add(oneHouseRent);
        rentPrices.add(twoHouseRent);
        rentPrices.add(threeHouseRent);
        rentPrices.add(fourHouseRent);
        rentPrices.add(hotelRent);
    }

    private void setRailroadRentPrices(ArrayList<Integer> rentPrices) {
        rentPrices.clear();

        rentPrices.add(25); // Rent for 1 Railroad
        rentPrices.add(50);
        rentPrices.add(100);
        rentPrices.add(200);
    }

    private void setUtilitiesRentPriceMultiplier(ArrayList<Integer> rentPrices) {
        rentPrices.clear();

        rentPrices.add(4); // Rent multiplier for 1 owned utility
        rentPrices.add(10);
    }

    /**
     * Gets the game tile at the specified position.
     * @param position The position of the tile.
     * @return The game tile at the specified position.
     */
    public GameTile getTile(int position) {
        if (position >= 0 && position < tiles.size()) {
            return tiles.get(position);
        }
        throw new IllegalArgumentException("Invalid tile position: " + position);
    }

    /**
     * Draws a card from the Community Chest deck.
     * @return The card drawn from the Community Chest deck.
     */
    public String drawCommunityChestCard() {
        return communityChestDeck.drawCard();
    }

    /**
     * Draws a card from the Chance deck.
     * @return The card drawn from the Chance deck.
     */
    public String drawChanceCard() {
        return chanceDeck.drawCard();
    }

    public int getNumberOfTiles() {
        return tiles.size();
    }

    /**
     * Executes a strategy of specified type for a player.
     * @param player The player to execute the strategy for.
     * @param type The type of strategy to execute. (i.e. "tile", "community:card", "chance:card")
     * @author walshj05
     */
    public void executeStrategyType(Player player, String type) {
        if (Objects.equals(type, "tile")) {
            GameTile currTile = tiles.get(player.getPosition());
            if (currTile instanceof ChanceSpace) {
                String card = drawChanceCard();
                System.out.println(card);
                chanceDeck.executeStrategy(player, card);
            } else if (currTile instanceof CommunityChestSpace) {
                String card = drawCommunityChestCard();
                System.out.println(card);
                communityChestDeck.executeStrategy(player, card);
            } else {
            currTile.executeStrategy(player);
            }
        } else if (type.contains("community:")){ // player uses get out of jail card
            String card = type.split(":")[1];
            communityChestDeck.executeStrategy(player, card);
        } else if (type.contains("chance:")) { // player uses get out of jail card
            String card = type.split(":")[1];
            chanceDeck.executeStrategy(player, card);
        }
    }
}