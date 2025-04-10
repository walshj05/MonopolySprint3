package org.monopoly.View.Controllers;

import org.monopoly.Model.Players.Token;

import java.util.ArrayList;

public interface TileController {
    void updateTokens(ArrayList<Token> tokens);
}
