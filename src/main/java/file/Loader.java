package file;

import exception.LoadProfileException;
import player.PlayerProfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Provides functionality to load player profiles from the filesystem in JSON format.
 * It handles file reading operations, including checks for file existence and directory validation.
 */
public class Loader {
    private static String filePath = "data/PlayerProfile.json";

    /**
     * Loads a player profile from a JSON file. Ensures that the directory and file exist
     * before attempting to read. It uses the {@link Decoder} class to parse the JSON data into a PlayerProfile object.
     *
     * @return PlayerProfile The loaded player profile object.
     * @throws LoadProfileException if the file does not exist or an error occurs during reading.
     */
    public static PlayerProfile loadProfile() throws LoadProfileException {
        ensureDirectoryExists();

        File file = new File(filePath);
        if (!file.exists()) {
            throw new LoadProfileException("Profile file not found.\n");
        }

        String json = readJsonFromFile(file);
        return Decoder.decodePlayerProfile(json);
    }

    /**
     * Sets the file path used for saving and deleting the player profile.
     * This is primarily used for testing to redirect operations to a temporary directory.
     *
     * @param newPath The new file path to be used.
     */
    public static void setFilePath(String newPath) {
        filePath = newPath;
    }

    /**
     * Ensures that the necessary directory for storing the player profile file exists.
     * If the directory does not exist, it attempts to create it.
     *
     * @throws RuntimeException if creating the directory fails.
     */
    private static void ensureDirectoryExists() {
        File directory = new File(filePath).getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("Failed to create data directory.\n");
        }
    }

    /**
     * Reads the content of a file into a single string. It iterates through each line of the file,
     * appending it to a StringBuilder.
     *
     * @param file The file to read from.
     * @return String The content of the file.
     * @throws LoadProfileException if an error occurs during the file reading process.
     */
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
