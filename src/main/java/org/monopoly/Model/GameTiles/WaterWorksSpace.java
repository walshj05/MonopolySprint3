package org.monopoly.Model.GameTiles;

import org.monopoly.Exceptions.BankruptcyException;
import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Model.Banker;
import org.monopoly.Model.Cards.ColorGroup;
import org.monopoly.Model.Dice;
import org.monopoly.Model.Players.ComputerPlayer;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.TurnManager;

import java.util.ArrayList;

/**
 * Represents the Water Works Space element on the Game Board's Tiles.
 *
 * @author shifmans
 */
public class WaterWorksSpace extends GameTile {
    private int price;
    private ArrayList<Integer> rentPriceMultiplier;
    private ColorGroup colorGroup;
    private int mortgageValue;
    private int unmortgageValue;
    private boolean isMortgaged;
    private String owner;

    /**
     * Constructor to initialize a WaterWorksSpace with all information.
     * @param actions Actions for a WaterWorksSpace.
     * @param price Price for a WaterWorksSpace.
     * @param rentPriceMultiplier Rent Price Multiplier for a WaterWorksSpace.
     * @param colorGroup Color Group for a WaterWorksSpace.
     * @param mortgageValue Mortgage Value for a WaterWorksSpace.
     *
     * Developed by: shifmans
     */
    public WaterWorksSpace(String actions, int price, ArrayList<Integer> rentPriceMultiplier, ColorGroup colorGroup, int mortgageValue) {
        super("Water Works", actions);
        this.price = price;
        this.rentPriceMultiplier = rentPriceMultiplier;
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
        actions += displayWaterWorksInfo();
        return actions;
    }

    /**
     * Player is shown information about Water Works after landing on element space.
     * @return Information on Water Works Space.
     *
     * Developed by: shifmans
     */
    private String displayWaterWorksInfo() {
        return "Property Name: " + getName() + "\n" +
                "Color Set: " + getColorGroup() + "\n" +
                "Purchase Price: $" + getPrice() + "\n" +
                "Rent (without houses/hotels): Depends on dice roll\n" +
                "If you own 1 Utility: Rent is 4 times the amount rolled on the dice.\n" +
                "If you own 2 Utilities: Rent is 10 times the amount rolled on the dice.\n" +
                "Mortgage Value: $" + getMortgageValue();
    }

    /**
     * Gets the price of a WaterWorksSpace.
     * @return The price of a WaterWorksSpace.
     *
     * Developed by: shifmans
     */
    public int getPrice() {
        return price;
    }

    /**
     * Calculates the rent price based on the number of utilities owned and the dice roll.
     * @param numUtilitiesOwned The number of utility properties owned by the player.
     * @return The rent price based on the number of utilities owned and the dice roll.
     *
     * Developed by: shifmans
     */
    @Override
    public int getRentPrice(int numUtilitiesOwned) {
        Dice dice = new Dice();
        int[] diceRoll = dice.roll();


        if (numUtilitiesOwned == 1) { // If the player owns 1 utility
            return (diceRoll[0] + diceRoll[1]) * getRentPriceMultiplier().get(0);
        }


        return (diceRoll[0] + diceRoll[1]) * getRentPriceMultiplier().get(1);
    }

    /**
     * Gets the rent prices of a WaterWorksSpace.
     * @return The list of rent prices of a WaterWorksSpace.
     *
     * Developed by: shifmans
     */
    private ArrayList<Integer> getRentPriceMultiplier() {
        return rentPriceMultiplier;
    }

    /**
     * Gets the color group of a WaterWorksSpace.
     * @return The color group of a WaterWorksSpace.
     *
     * Developed by: shifmans
     */
    public ColorGroup getColorGroup() {
        return colorGroup;
    }

    /**
     * Gets the mortgage value of a WaterWorksSpace.
     * @return The mortgage value of a WaterWorksSpace.
     *
     * Developed by: shifmans
     */
    public int getMortgageValue() {
        return mortgageValue;
    }

    /**
     * Gets the unmortgage value of a WaterWorksSpace.
     * @return The unmortgage value of a WaterWorksSpace.
     */
    public int getUnmortgageValue() {
        return unmortgageValue;
    }

    /**
     * Gets the mortgaged status of a WaterWorksSpace.
     * @return The mortgaged status of a WaterWorksSpace.
     *
     * Developed by: shifmans
     */
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /**
     * Sets the mortgaged status of a WaterWorksSpace.
     * @param isMortgaged The mortgaged status of a WaterWorksSpace.
     *
     * Developed by: shifmans
     */
    public void setMortgagedStatus(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
    }

    /**
     * Gets the owner of a WaterWorksSpace.
     * @return The owner of a WaterWorksSpace.
     *
     * Developed by: shifmans
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of a WaterWorksSpace.
     * @param owner The owner of a WaterWorksSpace.
     *
     * Developed by: shifmans
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Executes the strategy for the WaterWorksSpace.
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
                ((ComputerPlayer) player).handleLanding(rentPriceMultiplier);
            }

        } else {
            if (isMortgaged) {
                System.out.println("Property is mortgaged. No rent due.");
                return;
            }

            int rent = getRentPrice(rentPriceMultiplier.size());
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
