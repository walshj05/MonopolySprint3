package org.monopoly.Model.Players;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {
    private Token token;
    private HumanPlayer humanPlayer;

    @BeforeEach
    void setUp() {
        token = new Token("Top hat", "org/monopoly/monopolygameproject/TokenPNGs/TopHat.png");
        humanPlayer = new HumanPlayer("Alice", token);
    }

    @Test
    void testTokenCreation() {
        assertEquals("Top hat", token.getName(), "Token name should be 'Top hat'.");
        assertEquals("org/monopoly/monopolygameproject/TokenPNGs/TopHat.png", token.getIcon(), "Token icon should match.");
    }

    @Test
    void testTokenOwner() {
        assertEquals(humanPlayer, token.getOwner(), "Token owner should be Alice.");
        assertEquals("Alice", token.getOwner().getName(), "Token owner's name should be Alice.");
    }

    @Test
    void testPlayerToken() {
        System.out.println("Player: " + humanPlayer);
        System.out.println("Player's token: " + humanPlayer.getToken());
        System.out.println("Token: " + token);
        assertEquals(token, humanPlayer.getToken(), "Player's token should be the Top hat.");
    }
}