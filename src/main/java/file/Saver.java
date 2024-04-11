package file;

import exception.SaveProfileException;
import player.PlayerProfile;
import ui.ResponseManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles saving of the player's profile to a file in JSON format.
 * This class encapsulates all the functionality required to serialize the player profile into JSON
 * and write it to a persistent storage medium.
 */
public class Saver {
    private static final String FILE_PATH = "data/PlayerProfile.json";

    public static void saveProfile(PlayerProfile playerProfile) throws SaveProfileException {
        ensureDirectoryExists();

        try {
            File file = new File(FILE_PATH);

            if (file.createNewFile()) {
                ResponseManager.indentPrint("File created: " + file.getName() + "\n");
            }

            writeJsonToFile(file, constructJson(playerProfile));
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

    private static String constructJson(PlayerProfile playerProfile) {
        // Construct JSON method remains the same
        return "{\n" +
            " \"name\": \"" + playerProfile.getName() + "\", \n" +
            " \"occupation\": \"" + playerProfile.getOccupation() + "\", \n" +
            " \"asset\": \"" + playerProfile.getAsset().outputMoney() + "\", \n"  +
            " \"health\": \"" + playerProfile.getHealth() + "\", \n" +
            " \"currentRound\": \"" + playerProfile.getCurrentRound() + "\", \n" +
            " \"isAdvancedPlayer\": \"" + playerProfile.isAdvancedPlayer() + "\", \n" +
            " \"company\": {\n" +
            "   \"name\": \"" + playerProfile.getCompany().getName() + "\", \n" +
            "   \"numberOfEmployees\": " + playerProfile.getCompany().getNumberOfEmployees() + ", \n" +
            "   \"employeeSalary\": " + playerProfile.getCompany().getEmployeeSalary() + ", \n" +
            "   \"revenuePerEmployee\": " + playerProfile.getCompany().getRevenuePerEmployee() + "\n" +
            " }\n" +
            " \"asset\": {\n" +
            "   \"name\": \"" + playerProfile.getCompany().getName() + "\", \n" +
            "   \"numberOfEmployees\": " + playerProfile.getCompany().getNumberOfEmployees() + ", \n" +
            "   \"employeeSalary\": " + playerProfile.getCompany().getEmployeeSalary() + ", \n" +
            "   \"revenuePerEmployee\": " + playerProfile.getCompany().getRevenuePerEmployee() + "\n" +
            " }\n" +
            "}";
    }

    public static void deleteProfile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
