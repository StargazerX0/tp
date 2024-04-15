package file;

import exception.LoadProfileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import player.PlayerProfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoaderTest {

    @TempDir
    Path tempDir;
    private File profileFile;

    /**
     * Setup for testing: creates a sample player profile JSON file in a temporary directory
     * which will be used for testing the load functionality.
     */
    @BeforeEach
    void setUp() throws IOException {
        profileFile = tempDir.resolve("PlayerProfile.json").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(profileFile))) {
            writer.write("{\n" +
                "\"name\": \"John Doe\",\n" +
                "\"occupation\": \"Robotics\",\n" +
                "\"health\": 100,\n" +
                "\"currentRound\": 5,\n" +
                "\"actionCount\": 2,\n" +
                "\"isAdvancedPlayer\": false,\n" +
                "\"company\": {\"name\": \"Adventures Inc.\", \"numberOfEmployees\": 50, \"employeeSalary\": 3000, " +
                "\"revenuePerEmployee\": 150},\n" +
                "\"asset\": {\"money\": 1000, \"stocks\": [], \"bonds\": [], \"cryptos\": []}\n" +
                "}");
        }
        Loader.setFilePath(profileFile.getAbsolutePath());  // Assuming we add a method to set the file path in Loader
    }

    /**
     * Tests the ability of the Loader to correctly load a player profile from a JSON file.
     * Ensures that all properties are correctly parsed and the PlayerProfile object is accurately created.
     */
    @Test
    void testLoadProfile_successful() throws LoadProfileException {
        PlayerProfile loadedProfile = Loader.loadProfile();

        assertNotNull(loadedProfile, "Loaded profile should not be null.");
        assertEquals("John Doe", loadedProfile.getName(), "Names should match.");
        assertEquals("Robotics", loadedProfile.getOccupation(), "Occupations should match.");
    }

    /**
     * Tests Loader's response to a missing file scenario.
     * Expects a LoadProfileException to be thrown to indicate that the file could not be found.
     */
    @Test
    void testLoadProfile_fileNotFound() {
        File nonExistentFile = new File(profileFile.getParent(), "nonexistent.json");
        Loader.setFilePath(nonExistentFile.getAbsolutePath());  // Directing Loader to a non-existent file

        assertThrows(LoadProfileException.class, Loader::loadProfile,
            "Loading from a non-existent file should throw a LoadProfileException.");
    }
}
