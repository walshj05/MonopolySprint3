package org.monopoly.Model.GameTiles;

import org.monopoly.Exceptions.BankruptcyException;
import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Model.Banker;
import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Players.ComputerPlayer;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.TurnManager;

import java.util.ArrayList;

/**
 * Represents the Railroad Space element on the Game Board's Tiles.
 *
 * @author shifmans
 */
public class RailroadSpace extends GameTile {
    private int price;
    private ArrayList<Integer> rentPrices;
    private ColorGroup colorGroup;
    private int mortgageValue;
    private int unmortgageValue;
    private boolean isMortgaged;
    private String owner;

    /**
     * Constructor to initialize a RailroadSpace with the following parameters.
     * @param name Name for a RailroadSpace.
     * @param actions Actions for a RailroadSpace.
     * @param price Price for a RailroadSpace.
     * @param rentPrices Rent Price for a RailroadSpace.
     * @param colorGroup Color Group for a RailroadSpace.
     * @param mortgageValue Mortgage Value for a RailroadSpace.
     *
     * Developed by: shifmans
     */
    public RailroadSpace(String name, String actions, int price, ArrayList<Integer> rentPrices, ColorGroup colorGroup, int mortgageValue) {
        super(name, actions);
        this.price = price;
        this.rentPrices = rentPrices;
        this.colorGroup = colorGroup;
        this.mortgageValue = mortgageValue;
        this.unmortgageValue = mortgageValue + (int) (mortgageValue * 0.1); //Mortgage value plus 10% interest
        this.isMortgaged = false;
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
        actions += displayRailroadInfo();
        return actions;
    }

    /**
     * Player is shown information about Railroad after landing on element space.
     * @return Information on Railroad Space.
     *
     * Developed by: shifmans
     */
    private String displayRailroadInfo() {
        return "Property Name: " + getName() + "\n" +
                "Color Set: " + getColorGroup() + "\n" +
                "Purchase Price: $" + getPrice() + "\n" +
                "Rent with 1 Railroad Owned: $" + getRentPrices().get(0) + "\n" +
                "Rent with 2 Railroads Owned: $" + getRentPrices().get(1) + "\n" +
                "Rent with 3 Railroads Owned: $" + getRentPrices().get(2) + "\n" +
                "Rent with 4 Railroads Owned: $" + getRentPrices().get(3) + "\n" +
                "Mortgage Value: $" + getMortgageValue();
    }

    /**
     * Gets the price of a RailroadSpace.
     * @return The price of a RailroadSpace.
     *
     * Developed by: shifmans
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gets the rent price based on the number of railroads owned.
     * @param numRailroadsOwned The number of Railroads owned.
     * @return The rent price based on the number of railroads owned.
     *
     * Developed by: shifmans
     */
    @Override
    public int getRentPrice(int numRailroadsOwned) {
        return rentPrices.get(numRailroadsOwned-1);
    }

    /**
     * Gets the rent prices of a RailroadSpace.
     * @return The list of rent prices of a RailroadSpace.
     *
     * Developed by: shifmans
     */
    public ArrayList<Integer> getRentPrices() {
        return rentPrices;
    }

    /**
     * Gets the color group of a RailroadSpace.
     * @return The color group of a RailroadSpace.
     *
     * Developed by: shifmans
     */
    public ColorGroup getColorGroup() {
        return colorGroup;
    }

    /**
     * Gets the mortgage value of a RailroadSpace.
     * @return The mortgage value of a RailroadSpace.
     *
     * Developed by: shifmans
     */
    @Override
    public int getMortgageValue() {
        return mortgageValue;
    }

    /**
     * Gets the unmortgage value of a RailroadSpace.
     * @return The unmortgage value of a RailroadSpace.
     */
    public int getUnmortgageValue() {
        return unmortgageValue;
    }

    /**
     * Gets the mortgaged status of a RailroadSpace.
     * @return The mortgaged status of a RailroadSpace.
     *
     * Developed by: shifmans
     */
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /**
     * Sets the mortgaged status of a RailroadSpace.
     * @param isMortgaged The mortgaged status of a RailroadSpace.
     *
     * Developed by: shifmans
     */
    public void setMortgagedStatus(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
    }

    /**
     * Gets the owner of a RailroadSpace.
     * @return The owner of a RailroadSpace.
     *
     * Developed by: shifmans
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of a RailroadSpace.
     * @param owner The owner of a RailroadSpace.
     *
     * Developed by: shifmans
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Executes the strategy for the RailroadSpace.
     * @author crevelings
     * Modified by: crevelings (4/8/25), (4/9/25)
     * 4/8/25: Configured for CPU
     * 4/9/25: Added full implementation for strategy
     */
    @Override
    public void executeStrategy(Player player) {
        System.out.println(player.getName() + " landed on " + getName());

        if (player.hasProperty(getName())) {
            System.out.println("You already own " + getName() + "!");
            return;
        }

        if (owner == null || owner.isEmpty()) {
            System.out.println(getName() + " is unowned.");

            if (player instanceof HumanPlayer) {
                try {
                    player.purchaseProperty(getName(), price);
                    setOwner(player.getName());
                    System.out.println("You bought " + getName() + "!");
                } catch (InsufficientFundsException e) {
                    System.out.println("Not enough money to purchase. Starting auction...");
                    Banker banker = Banker.getInstance();
                    TurnManager turnManager = TurnManager.getInstance();
                    banker.auctionProperty(getName(), turnManager.getPlayers());
                }
            } else if (player instanceof ComputerPlayer) {
                ((ComputerPlayer) player).handleLanding(rentPrices);
            }

        } else {
            if (isMortgaged) {
                System.out.println("Property is mortgaged. No rent due.");
                return;
            }

            int rent = getRentPrice(rentPrices.size());
            System.out.println("Owned by " + owner + ". Rent is $" + rent);

            try {
                if (!(player.getBalance() >= rent)) {
                    System.out.println("Not enough funds. Attempting to raise money...");
                    player.attemptToRaiseFunds(rent);
                }

                player.subtractFromBalance(rent);
            } catch (BankruptcyException e) {
                System.out.println(player.getName() + " is bankrupt!");
            }
        }
    }
}
