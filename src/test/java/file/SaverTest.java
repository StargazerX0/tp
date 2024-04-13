package file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import player.PlayerProfile;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the functionality of the {@link Saver} class, focusing on the ability to save and delete player profiles
 * from the filesystem in a controlled testing environment using a temporary directory.
 */
class SaverTest {
    @TempDir
    Path tempDir; // JUnit 5 feature to create a temporary directory

    private PlayerProfile playerProfile;

    /**
     * Sets up the test environment before each test. This includes initializing a new PlayerProfile
     * and configuring the Saver to use a temporary directory for file operations, ensuring tests
     * do not affect the actual filesystem outside of the test context.
     */
    @BeforeEach
    void setup() {
        playerProfile = new PlayerProfile("John Doe", "Adventurer");
        // Direct Saver to use the temporary directory for tests
        Saver.setFilePath(tempDir.resolve("PlayerProfile.json").toString());
    }

    /**
     * Tests that the Saver class can successfully save a player profile to the filesystem.
     * Asserts that the file indeed exists after the save operation.
     * @throws Exception if there are errors during the save operation.
     */
    @Test
    void testSaveProfile_successful() throws Exception {
        Saver.saveProfile(playerProfile);
        File savedFile = tempDir.resolve("PlayerProfile.json").toFile(); // Correctly converting Path to File
        assertTrue(savedFile.exists(), "The file should have been created.");
    }

    /**
     * Tests that the Saver class can successfully delete a player profile from the filesystem.
     * First ensures a file is saved and exists, then tests its deletion, asserting that the file
     * no longer exists after the delete operation.
     * @throws Exception if there are errors during the file operations.
     */
    @Test
    void testDeleteProfile_profileExists() throws Exception {
        Saver.saveProfile(playerProfile);
        File savedFile = tempDir.resolve("PlayerProfile.json").toFile(); // Correctly converting Path to File
        assertTrue(savedFile.exists(), "The file should exist before deletion.");
        Saver.deleteProfile();
        assertTrue(!savedFile.exists(), "The file should have been deleted.");
    }
}
