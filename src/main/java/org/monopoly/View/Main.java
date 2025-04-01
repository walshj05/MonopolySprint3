package org.monopoly.View;

import org.monopoly.Exceptions.InsufficientFundsException;

import java.util.Scanner;

/**
 * Main class to run the Monopoly game
 * @author walshj05
 */
public class Main {
    public static void main(String[] args) throws InsufficientFundsException {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Monopoly!");
        input.nextLine();
        System.out.println("Banker Demo:");
        BankDeedDemo.main(args);
        input.nextLine();
        System.out.println("Tile Demo:");
        TilesMain.main(args);
        input.nextLine();
        System.out.println("GameState Demo:");
        GameStateDemo.main(args);
    }
}
