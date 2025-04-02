package org.monopoly.View;

import org.monopoly.Model.Banker;
import org.monopoly.Model.GameTiles.GameTile;
import org.monopoly.Model.GameTiles.PropertySpace;
import org.monopoly.Model.Players.HumanPlayer;
import org.monopoly.Model.Players.Player;
import org.monopoly.Model.Players.Token;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * This class is a demo of the Banker and TitleDeedCard classes.
 * @author shifmans
 */
public class BankDeedDemo {

    /**
     * This is the main method that demonstrates the functionality of the Banker and TitleDeedCard classes.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Banker banker = new Banker();
        ArrayList<Player> playerList = new ArrayList<>();

        HumanPlayer player1 = new HumanPlayer("Alice", new Token("Thimble", "Thimble.png"));
        HumanPlayer player2 = new HumanPlayer("Bob", new Token("Boot", "Boot.png"));

        playerList.add(player1);
        playerList.add(player2);

        System.out.println("All TitleDeedCards:");
        for (String propertyName: banker.getDeck().getTitleDeeds().getProperties().keySet()) {
            GameTile property = banker.getDeck().getTitleDeeds().getProperties().get(propertyName);
            System.out.println(property.getName());
        }
        System.out.println("Total TitleDeedCards: " + banker.getDeck().getTitleDeeds().getProperties().size());

        System.out.println("Bank Starting Balance: $" + banker.getBalance());
        System.out.println(player1.getName() + " Starting Balance: $" + player1.getBalance());
        System.out.println(player2.getName() + " Starting Balance: $" + player2.getBalance());
        System.out.println("Banker's Title Deed Deck Size: " + banker.getDeck().getSize());

        System.out.println("Banker gives " + player1.getName() + " $200 for passing Go.");
        banker.payGoSpace(player1);
        System.out.println("Bank Balance: $" + banker.getBalance());
        System.out.println(player1.getName() + " Balance: $" + player1.getBalance());
        System.out.println(player2.getName() + " Balance: $" + player2.getBalance());

        System.out.println("Banker gives " + player2.getName() + " paid $100 for landing on Income Tax.");
        banker.receiveMoney(player2, 100);
        System.out.println("Bank Balance: $" + banker.getBalance());
        System.out.println(player1.getName() + " Balance: $" + player1.getBalance());
        System.out.println(player2.getName() + " Balance: $" + player2.getBalance());

        System.out.println(player2.getName() + " buys Mediterranean Avenue for $" + banker.getDeck().getTitleDeeds().getProperties().get("Mediterranean Avenue").getPrice());
        banker.sellProperty("Mediterranean Avenue", player2);
        System.out.println("Bank Balance: $" + banker.getBalance());
        System.out.println(player1.getName() + " Balance: $" + player1.getBalance());
        System.out.println(player2.getName() + " Balance: $" + player2.getBalance());
        System.out.println("Banker's Title Deed Deck Size: " + banker.getDeck().getSize());

        System.out.println(player1.getName() + " buys Boardwalk Avenue for $" + banker.getDeck().getTitleDeeds().getProperties().get("Boardwalk").getPrice());
        banker.sellProperty("Boardwalk", player1);
        System.out.println("Bank Balance: $" + banker.getBalance());
        System.out.println(player1.getName() + " Balance: $" + player1.getBalance());
        System.out.println(player2.getName() + " Balance: $" + player2.getBalance());
        System.out.println("Banker's Title Deed Deck Size: " + banker.getDeck().getSize());

        System.out.println(player2.getName() + " buys Short Line Railroad for $" + banker.getDeck().getTitleDeeds().getProperties().get("Short Line Railroad").getPrice());
        banker.sellProperty("Short Line Railroad", player2);
        System.out.println("Property Mortgage Status: " + banker.getDeck().getTitleDeeds().getProperties().get("Boardwalk").isMortgaged());
        System.out.println("Bank Balance: $" + banker.getBalance());
        System.out.println(player1.getName() + " Balance: $" + player1.getBalance());
        System.out.println(player2.getName() + " Balance: $" + player2.getBalance());
        System.out.println("Banker's Title Deed Deck Size: " + banker.getDeck().getSize());

        System.out.println(player2.getName() + " mortgaged Short Line Railroad.");
        banker.mortgageProperty("Short Line Railroad");
        System.out.println("Property Mortgage Status: " + banker.getDeck().getTitleDeeds().getProperties().get("Short Line Railroad").isMortgaged());

        PropertySpace property = (PropertySpace) banker.getDeck().getTitleDeeds().getProperties().get("Boardwalk");
        System.out.println("Number of Houses on Boardwalk: " + property.getNumHouses());
        System.out.println(player1.getName() + " buys a house on Boardwalk Avenue for $" + property.getHousePrice());
        banker.sellHouse("Boardwalk", player1);
        System.out.println("Number of Houses on Boardwalk: " + property.getNumHouses());
        System.out.println("Bank Balance: $" + banker.getBalance());
        System.out.println(player1.getName() + " Balance: $" + player1.getBalance());
        System.out.println(player2.getName() + " Balance: $" + player2.getBalance());

        System.out.println(player2.getName() + " decides to auction Mediterranean Avenue.");
        String simulatedInput = "Y\nY\n100\n200\nY\nN\n220";  // Y = bid, 100 = Alice's bid, 200 = Bob's bid
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        banker.auctionProperty("Mediterranean Avenue", playerList);

    }
}
