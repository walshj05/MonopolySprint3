package org.monopoly.Model.Players;

/**
 * Created token class that has the icons for and sets the icon to be with a player.
 * @author Sean Creveling
 */
public class Token {
    private final String name;
    private final String icon;
    private Player owner;

    /**
     * Constructor for the Token class.
     * @param name The name of the token.
     * @param icon The icon representing the token.
     */
    public Token(String name, String icon) {
        this.name = name;
        this.icon = icon;
        this.owner = null;
    }

    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return name + " (Owner: " + (owner != null ? getName() : "None") + ")";
    }
}
