package file;

import company.Company;
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
        return parseJson(json);
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
            reader.lines().forEach(line -> content.append(line).append("\n"));
        } catch (IOException e) {
            throw new LoadProfileException("Error reading profile from file.\n");
        }
        return content.toString();
    }

    private static PlayerProfile parseJson(String json) throws LoadProfileException {
        try {
            String name = extractValue(json, "name");
            String occupation = extractValue(json, "occupation");
            int asset = Integer.parseInt(extractValue(json, "asset"));
            int health = Integer.parseInt(extractValue(json, "health"));
            int currentRound = Integer.parseInt(extractValue(json, "currentRound"));
            boolean isAdvancedPlayer = Boolean.parseBoolean(extractValue(json, "isAdvancedPlayer"));

            Company company = parseCompany(json);

            return new PlayerProfile(name, occupation, health, asset, currentRound, isAdvancedPlayer, company);
        } catch (Exception e) {
            throw new LoadProfileException("Error parsing profile data.\n");
        }
    }

    private static Company parseCompany(String json) throws LoadProfileException {
        try {
            String companyJson = extractJsonObject(json, "company");
            String name = extractValue(companyJson, "name");
            int numberOfEmployees = Integer.parseInt(extractValue(companyJson, "numberOfEmployees"));
            int employeeSalary = Integer.parseInt(extractValue(companyJson, "employeeSalary"));
            int revenuePerEmployee = Integer.parseInt(extractValue(companyJson, "revenuePerEmployee"));

            return new Company(name, numberOfEmployees, employeeSalary, revenuePerEmployee);
        } catch (Exception e) {
            throw new LoadProfileException("Error parsing company data.\n");
        }
    }

    private static String extractJsonObject(String json, String key) throws LoadProfileException {
        int startIndex = json.indexOf("\"" + key + "\": {") + key.length() + 4;
        int endIndex = json.indexOf("}", startIndex) + 1;
        if (startIndex < 0 || endIndex < 0) {
            throw new LoadProfileException("Error extracting JSON object for key: " + key + "\n");
        }
        return json.substring(startIndex, endIndex);
    }

    private static String extractValue(String json, String key) throws LoadProfileException {
        String pattern = "\"" + key + "\":";
        int startIndex = json.indexOf(pattern) + pattern.length();
        if (startIndex == -1) {
            throw new LoadProfileException("Key not found in JSON: " + key);
        }
        startIndex = json.charAt(startIndex) == '"' ? startIndex + 1 : startIndex;
        int endIndex = json.charAt(startIndex) == '"' ?
            json.indexOf("\"", startIndex) : json.indexOf(",", startIndex);
        endIndex = endIndex == -1 ? json.indexOf("}", startIndex) : endIndex;
        if (endIndex == -1) {
            throw new LoadProfileException("Error finding end of value for key: " + key + "\n");
        }
        String value = json.substring(startIndex, endIndex).trim();

        return value.replace("\"", "");
    }
}
