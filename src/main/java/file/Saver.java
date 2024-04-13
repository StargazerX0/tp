package file;

import exception.SaveProfileException;

import player.PlayerProfile;
import ui.ResponseManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Manages the persistence of player profiles by saving and deleting them in JSON format on the filesystem.
 * Handles necessary file and directory operations, including error management.
 */
public class Saver {
    private static String FILE_PATH = "data/PlayerProfile.json";

    /**
     * Saves the specified player profile to a JSON file at a predefined location.
     * If the file does not exist, it creates a new file; otherwise, it overwrites the existing file.
     * It ensures that the directory exists before attempting to write the file.
     *
     * @param playerProfile The player profile object to be serialized and saved.
     * @throws SaveProfileException if an I/O error occurs during file creation or write operations.
     */
    public static void saveProfile(PlayerProfile playerProfile) throws SaveProfileException {
        ensureDirectoryExists();

        try {
            File file = new File(FILE_PATH);

            if (file.createNewFile()) {
                ResponseManager.indentPrint("File created: " + file.getName() + "\n");
            }

            writeJsonToFile(file, Serializer.constructJson(playerProfile));
        } catch (IOException e) {
            throw new SaveProfileException("Error accessing the file: " + FILE_PATH + "\n");
        }
    }

    /**
     * Sets the file path used for saving and deleting the player profile.
     * This is primarily used for testing to redirect operations to a temporary directory.
     *
     * @param newPath The new file path to be used.
     */
    public static void setFilePath(String newPath) {
        FILE_PATH = newPath;
    }

    /**
     * Ensures the directory for storing the profile exists. If it does not, attempts to create it.
     */
    private static void ensureDirectoryExists() {
        File directory = new File(FILE_PATH).getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("Failed to create data directory.\n");
        }
    }

    /**
     * Writes the given JSON string to the specified file. This method uses a FileWriter
     * to write to the file, ensuring that the contents are flushed to the disk after writing.
     *
     * @param file The file object representing the file to write to.
     * @param json The JSON string to be written to the file.
     * @throws SaveProfileException if there is a failure during the file writing process.
     */
    private static void writeJsonToFile(File file, String json) throws SaveProfileException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            throw new SaveProfileException("Error writing to file: " + FILE_PATH + "\n");
        }
    }

    /**
     * Deletes the player profile file if it exists.
     */
    public static void deleteProfile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
