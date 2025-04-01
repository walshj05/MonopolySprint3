package org.monopoly.Model;

import org.monopoly.Model.Players.Player;

/**
 * Strategy interface for implementing different player strategies in the Monopoly game.
 * @author walshj05
 */
public interface Strategy {
    /**
     * Executes the strategy for the given player.
     * @param player The player to execute the strategy for.
     * @author walshj05
     */
    void executeStrategy(Player player);
}
