package file;

import exception.SaveProfileException;

import player.PlayerProfile;
import ui.ResponseManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Saver {

    private static final String FILE_PATH = "data/PlayerProfile.json";

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

    private static void ensureDirectoryExists() {
        File directory = new File(FILE_PATH).getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("Failed to create data directory.\n");
        }
    }

    private static void writeJsonToFile(File file, String json) throws SaveProfileException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            throw new SaveProfileException("Error writing to file: " + FILE_PATH + "\n");
        }
    }

}
