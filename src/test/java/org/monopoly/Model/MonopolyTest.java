package org.monopoly.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Monopoly game.
 * @author walshj05
 */
public class MonopolyTest {
    /**
     * Test the buildHouse method for one monopoly.
     * It should allow building a house on each property.
     * @author walshj05
     */
    @Test
    void testBuildHouseWorksForOneMonopoly() {
        String[] properties = {"A", "B", "C"};
        Monopoly monopoly = new Monopoly(properties, null);

        // Build a house on property A
        monopoly.buildHouse("A");
        assertEquals(1, monopoly.getNumberOfHouses("A"));

        // Build a house on property B
        monopoly.buildHouse("B");
        assertEquals(1, monopoly.getNumberOfHouses("B"));

        // Build a house on property C
        monopoly.buildHouse("C");
        assertEquals(1, monopoly.getNumberOfHouses("C"));
    }

    /**
     * Test the buildHouse method for three variations of the same monopoly.
     * It should allow building a house on each property, but not on the same property in different variations.
     * @author walshj05
     */
    @Test
    void testBuildHouseWorksForThreeVariationsOfTheSameMonopoly() {
        int[] variation1 = {0, 0, 1};
        int[] variation2 = {0, 1, 0};
        int[] variation3 = {1, 0, 0};
        String[] properties = {"A", "B", "C"};
        Monopoly monopoly = new Monopoly(properties, variation1, null);
        Monopoly monopoly2 = new Monopoly(properties, variation2, null);
        Monopoly monopoly3 = new Monopoly(properties, variation3, null);

        // Build a house on property A
        monopoly.buildHouse("A");
        assertEquals(1, monopoly.getNumberOfHouses("A"));

        // Build a house on property B
        assertThrows(IllegalArgumentException.class, () -> monopoly2.buildHouse("B"));
        assertEquals(1, monopoly2.getNumberOfHouses("B"));

        // Build a house on property C
        monopoly3.buildHouse("C");
        assertEquals(1, monopoly3.getNumberOfHouses("C"));
    }

    /**
     * Tests that a given state allows building in specific configurations.
     * This test checks that the player cannot build on property C without first building on A, B
     * @author walshj05
     */
    @Test
    void test002ConfigurationNotAllowed() {
        int[] variation1 = {0, 0, 1};
        String[] properties = {"A", "B", "C"};
        Monopoly monopoly = new Monopoly(properties, variation1, null);
        System.out.println("Before alterations:\n" + monopoly);

        // Build a house on property C
        assertThrows(IllegalArgumentException.class, () -> monopoly.buildHouse("C"));
        assertEquals(1, monopoly.getNumberOfHouses("C"));
        System.out.println("Attempted to build on C, but did not follow rule:\n" + monopoly);
        // Build a house on property B
        monopoly.buildHouse("B");
        assertEquals(1, monopoly.getNumberOfHouses("B"));
        System.out.println("Built house on B:\n" + monopoly);
        // Build a house on property A
        monopoly.buildHouse("A");
        assertEquals(1, monopoly.getNumberOfHouses("A"));
        System.out.println("Built house on A:\n" + monopoly);
        System.out.println(monopoly);
    }

    /**
     * Tests house can only be built on B, C if first built on A
     * @author walshj05
     */
    @Test
    void test012ConfigurationNotAllowed() {
        int[] variation1 = {0, 1, 1};
        String[] properties = {"A", "B", "C"};
        Monopoly monopoly = new Monopoly(properties, variation1, null);
        System.out.println("Before alterations:\n" + monopoly);

        // Build a house on property C
        assertThrows(IllegalArgumentException.class, () -> monopoly.buildHouse("C"));
        assertEquals(1, monopoly.getNumberOfHouses("C"));
        System.out.println("Attempted to build on C, but did not follow rule:\n" + monopoly);

        // Build a house on property B
        assertThrows(IllegalArgumentException.class, () -> monopoly.buildHouse("B"));
        assertEquals(1, monopoly.getNumberOfHouses("B"));
        System.out.println("Attempted to build on B but failed:\n" + monopoly);

        // Build a house on property A
        monopoly.buildHouse("A");
        assertEquals(1, monopoly.getNumberOfHouses("A"));
        System.out.println("Built house on A:\n" + monopoly);

        // Build a house on property C
        monopoly.buildHouse("C");
        assertEquals(2, monopoly.getNumberOfHouses("C"));
        System.out.println("Built house on C:\n" + monopoly);
    }

    /**
     * Tests houses cannot be built on A, B, C
     * @author walshj05
     */
    @Test
    void test445ConfigNotAllowed() {
        int[] variation1 = {4, 4, 4};
        String[] properties = {"A", "B", "C"};
        Monopoly monopoly = new Monopoly(properties, variation1, null);

        System.out.println("Before alterations:\n" + monopoly);
        // Build a house on property C
        assertThrows(IllegalArgumentException.class, () -> monopoly.buildHouse("C"));
        assertEquals(4, monopoly.getNumberOfHouses("C"));
        System.out.println("Attempted to build on C, but did not follow rule:\n" + monopoly);
    }

    /**
     * Tests that a given state allows building in specific configurations.
     * This test checks that the player cannot build on property C without first building on A, B
     * @author walshj05
     */
    @Test
    void testPlayerCanBuyHotelWith444Configuration() {
        int[] variation1 = {4, 4, 4};
        String[] properties = {"A", "B", "C"};
        Monopoly monopoly = new Monopoly(properties, variation1, null);
        System.out.println("Before alterations:\n" + monopoly);

        // Build a hotel on property C
        monopoly.buildHotel("C");
        assertEquals(5, monopoly.getNumberOfHouses("C"));
        System.out.println("Attempted to build on C:\n" + monopoly);

        // Build a hotel on property B
        monopoly.buildHotel("B");
        assertEquals(5, monopoly.getNumberOfHouses("B"));
        System.out.println("Attempted to build on B\n" + monopoly);

        // Build a hotel on property A
        monopoly.buildHotel("A");
        assertEquals(5, monopoly.getNumberOfHouses("A"));
        System.out.println("Built hotel on A:\n" + monopoly);
    }

    /**
     * Tests that a given state allows building in specific configurations.
     * This test checks that the player cannot build on property C without first building on A, B
     * @author walshj05
     */
    @Test
    void testPlayerCannotSellHouseWith000Configuration() {
        String[] properties = {"A", "B", "C"};
        Monopoly monopoly = new Monopoly(properties, null);
        System.out.println("Before alterations:\n" + monopoly);

        // Build a hotel on property C
        assertThrows(IllegalArgumentException.class, () -> monopoly.sellHouse("C"));
        assertEquals(0, monopoly.getNumberOfHouses("C"));
        System.out.println("Attempted to sell house on C, but did not have any houses:\n" + monopoly);
    }

    /**
     * Tests that a given state allows building in specific configurations.
     * This test checks that the player cannot build on property C without first building on A, B
     * @author walshj05
     */
    @Test
    void testPlayerCannotSellHotelWith000Configuration() {
        String[] properties = {"A", "B", "C"};
        Monopoly monopoly = new Monopoly(properties, null);
        System.out.println("Before alterations:\n" + monopoly);

        // Build a hotel on property C
        assertThrows(IllegalArgumentException.class, () -> monopoly.sellHotel("C"));
        assertEquals(0, monopoly.getNumberOfHouses("C"));
        System.out.println("Attempted to sell hotel on C, but did not have any hotels:\n" + monopoly);
    }
}

