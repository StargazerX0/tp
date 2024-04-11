package file;

import exception.LoadProfileException;
import player.PlayerProfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Provides functionality to load a player's profile from a JSON file.
 * This class handles reading and parsing the JSON file to reconstruct the player's game state.
 */
public class Loader {
    private static final String FILE_PATH = "data/PlayerProfile.json";

    public static PlayerProfile loadProfile() throws LoadProfileException {
        ensureDirectoryExists();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            throw new LoadProfileException("Profile file not found.\n");
        }

        String json = readJsonFromFile(file);
        return Decoder.decodePlayerProfile(json);
    }

    private static void ensureDirectoryExists() {
        File directory = new File(FILE_PATH).getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("Failed to create data directory.\n");
        }
    }

    private static String readJsonFromFile(File file) throws LoadProfileException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            throw new LoadProfileException("Error reading profile from file.\n");
        }
        return content.toString();
    }
}
