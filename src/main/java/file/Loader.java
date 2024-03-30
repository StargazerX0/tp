package file;

import company.Company;
import player.PlayerProfile;
import ui.ResponseManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Loader {
    private static final String FILE_PATH = "data/PlayerProfile.json";

    public static PlayerProfile loadProfile() {
        String json = readJsonFromFile(FILE_PATH);
        if (json == null) {
            ResponseManager.indentPrint("No existing profile found or an error o" +
                "ccurred during reading. Starting new.\n");
            return null;
        }
        return parseJson(json);
    }

    private static String readJsonFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().forEach(line -> content.append(line).append("\n"));
        } catch (IOException e) {
            ResponseManager.indentPrint("Error reading profile: " + e.getMessage() + "\n");
            return null;
        }
        return content.toString();
    }

    private static PlayerProfile parseJson(String json) {
        String name = extractValue(json, "name");
        String occupation = extractValue(json, "occupation");
        int asset = Integer.parseInt(extractValue(json, "asset"));
        int health = Integer.parseInt(extractValue(json, "health"));
        int currentRound = Integer.parseInt(extractValue(json, "currentRound"));
        boolean isAdvancedPlayer = Boolean.parseBoolean(extractValue(json, "isAdvancedPlayer"));

        Company company = parseCompany(json);

        return new PlayerProfile(name, occupation, health, asset, currentRound, isAdvancedPlayer, company);
    }

    private static Company parseCompany(String json) {
        String companyJson = extractJsonObject(json, "company");
        String name = extractValue(companyJson, "name");
        int numberOfEmployees = Integer.parseInt(extractValue(companyJson, "numberOfEmployees"));
        int employeeSalary = Integer.parseInt(extractValue(companyJson, "employeeSalary"));
        int revenuePerEmployee = Integer.parseInt(extractValue(companyJson, "revenuePerEmployee"));

        return new Company(name, numberOfEmployees, employeeSalary, revenuePerEmployee);
    }

    private static String extractJsonObject(String json, String key) {
        int startIndex = json.indexOf("\"" + key + "\": {") + key.length() + 4;
        int endIndex = json.indexOf("}", startIndex) + 1;
        return json.substring(startIndex, endIndex);
    }

    private static String extractValue(String json, String key) {
        String pattern = "\"" + key + "\":";
        int startIndex = json.indexOf(pattern) + pattern.length();
        startIndex = json.charAt(startIndex) == '"' ? startIndex + 1 : startIndex;
        int endIndex = json.charAt(startIndex) == '"' ?
            json.indexOf("\"", startIndex) : json.indexOf(",", startIndex);
        endIndex = endIndex == -1 ? json.indexOf("}", startIndex) : endIndex;
        String value = json.substring(startIndex, endIndex).trim();

        return value.replace("\"", "");
    }
}
