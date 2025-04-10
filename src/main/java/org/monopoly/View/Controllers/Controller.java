package org.monopoly.View.Controllers;

/**
 * Controller interface for the Monopoly game.
 * @author walshj05
 */
public interface Controller{
    /**
     * Initializes the controller.
     * @author walshj05
     */
    void initialize();
    /**
     * Sets the controller for the view.
     * @param controller The controller to set.
     * @author walshj05
     */
    void setController(Controller controller);
    /**
     * Gets the controller for the view.
     * @return The controller.
     * @author walshj05
     */
    Controller getController();
}
