package player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests for the {@link PlayerProfile} class.
 * Verifies the functionality related to player attributes, health, assets,
 * and company ownership.
 */
class PlayerProfileTest {

    private PlayerProfile player;

    /**
     * Setup method to initialize common objects used across tests.
     */
    @BeforeEach
    void setUp() {
        player = new PlayerProfile("Jack", "Robotics", 100, 5000,
                1, false);
    }

    /**
     * Test to verify the correct output of the player's name.
     */
    @Test
    void outputName() {
        assertEquals("Jack", player.getName(),
                "The getName method should return the correct player's name.");
    }

    /**
     * Test to verify if health is correctly deducted.
     */
    @Test
    void loseHealth() {
        player.loseHealth(10);
        assertEquals(90, player.getHealth(), "The loseHealth method should correctly deduct health.");
    }

    @Test
    void addHealth() {
        PlayerProfile player = new PlayerProfile("Jack", "Robotics", 90, 5000,
                1, false);
        player.addHealth(10);
        assertEquals(100, player.getHealth(),
                "The addHealth method should correctly add health and not exceed 100.");
    }


    /**
     * Test to verify if assets can be correctly added.
     */
    @Test
    void addAsset() {
        player.addAsset(1000);
        assertEquals(6000, player.getAsset().getAsset(),
                "The addAsset method should correctly add assets.");
    }

    /**
     * Test to verify if the player can be correctly marked as an advanced player.
     */
    @Test
    void upgrade() {
        assertFalse(player.isAdvancedPlayer(), "Initially, player should not be an advanced player.");
        player.upgrade();
        assertTrue(player.isAdvancedPlayer(), "After upgrade, player should be marked as an advanced player.");
    }

    /**
     * Test to verify the functionality of hiring employees and updating the number of employees.
     */
    @Test
    void hireEmployee() {
        assertEquals(0, player.getNumberOfEmployees(), "Initially, player should have no employees.");
        player.hireEmployee(3);
        assertEquals(3, player.getNumberOfEmployees(),
                "After hiring, player should have the correct number of employees.");
    }

    /**
     * Test to verify the functionality of firing employees and updating the number of employees.
     */
    @Test
    void fireEmployee() {
        player.hireEmployee(5); // Assuming there are enough employees to fire
        player.fireEmployee(2);
        assertEquals(3, player.getNumberOfEmployees(),
                "After firing, player should have the correct number of employees left.");
    }

    /**
     * Test to verify if the game is correctly identified as finished under various conditions.
     */
    @Test
    void isFinished() {
        assertFalse(player.isFinished(), "The game should not be finished initially.");
        player.nextRound();
        for (int i = 2; i <= PlayerProfile.ROUND_LIMIT; i++) {
            player.nextRound();
        }
        assertTrue(player.isFinished(), "The game should be finished after reaching the round limit.");
    }


}
