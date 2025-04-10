package org.monopoly.View.Controllers;

import org.monopoly.Model.Players.Token;

import java.util.ArrayList;

/**
 * Interface for the TileController.
 * This interface defines the methods that a TileController must implement.
 * @author walshj05
 */
public interface TileController {
    /**
     * Updates the tokens to display on the tile.
     * @param tokens The tokens to display on the tile.
     * @author walshj05
     */
    void updateTokens(ArrayList<Token> tokens);
}
