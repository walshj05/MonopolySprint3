package org.monopoly.Model.Players;

import org.monopoly.Exceptions.HotelCannotBeBuiltException;
import org.monopoly.Exceptions.HouseCannotBeBuiltException;
import org.monopoly.Exceptions.InsufficientFundsException;
import org.monopoly.Exceptions.NoSuchPropertyException;
import org.monopoly.Model.Dice;

/**
 * Abstract class for the different types of players in the Monopoly game.
 * @author walshj05
 */
public abstract class Player {

    public abstract String getName();

    public abstract Token getToken();

    public abstract int getPosition();

    public abstract int getBalance();

    public abstract int getNumHotels();

    public abstract int getNumHouses();

    public abstract void setPosition(int position);

    public abstract void move(int spaces);

    public abstract void goToJail();

    public abstract boolean isInJail();

    public abstract void releaseFromJail();

    public abstract void takeTurn(Dice dice);

    public abstract void purchaseProperty(String property, int price) throws InsufficientFundsException;

    public abstract void mortgageProperty(String property, int mortgageCost) throws NoSuchPropertyException;

    public abstract void sellProperty(String property, int propertyCost) throws NoSuchPropertyException;

    public abstract boolean hasMonopoly(String colorGroup);

    public abstract void addHouse(String property, String colorGroup) throws HouseCannotBeBuiltException;

    public abstract void addHotel(String property) throws HotelCannotBeBuiltException;

    public abstract void addToBalance(int amount);

    public abstract void subtractFromBalance(int amount);

    public abstract boolean hasProperty(String property);

    public abstract void addCard(String card);

    public abstract void removeCard(String card);

    public abstract boolean hasCard(String card);

    public abstract boolean isBankrupt();

    public abstract void resetJailTurns();
    public abstract void incrementJailTurns();
    public abstract int getJailTurns();
}