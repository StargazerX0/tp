package ui;

import exception.CommandInputException;
import exception.JobSelectException;
import exception.NameInputException;

import java.util.Scanner;

import static ui.ResponseManager.indentPrint;

public class Parser {
    private static final String NAME = "^[a-zA-Z ]{1,15}$";
    private static final int NAME_LENGTH_LIMIT = 15;
    private static final String YES_REGEX = "(?i)(y|yes)";
    private static final String NO_REGEX = "(?i)(n|no)";
    private static final String ROBOTIC = "/r";
    private static final String SEMI_CONDUCTOR = "/s";
    private static final String AI = "/a";

    public static String parseName(String input) throws NameInputException {
        if (input.matches(NAME)) {
            return input;
        }
        if (input.length() <= NAME_LENGTH_LIMIT) {
            throw new NameInputException("please enter a valid name, try again!\n");
        }
        throw new NameInputException(
                "Oops! Your name is too long! Please enter a name with less than 15 characters.\n");
    }

    public static String parseCareer(String input) throws JobSelectException {
        switch (input.toLowerCase().trim()) {
        case ROBOTIC:
            ResponseManager.echoChosenIndustry("Robotics");
            return "Robotics";

        case SEMI_CONDUCTOR:
            ResponseManager.echoChosenIndustry("Semi-conductor");
            return "Semi-conductor";

        case AI:
            ResponseManager.echoChosenIndustry("Artificial Intelligence");
            return "Artificial intelligence";

        default:
            throw new JobSelectException("Please enter a valid job type, try again!\n");
        }
    }

    public static CommandType parseCommand(String input) throws CommandInputException {
        return CommandType.analyseInput(input.trim().toLowerCase());
    }

    public static String[] separateCommand(String input) {
        return input.split("\\s", 2);
    }

    public static int decodeInfo(String input) throws CommandInputException {
        try {
            return Integer.parseInt(input.trim());
        } catch (IllegalArgumentException e) {
            throw new CommandInputException("Invalid number, please try again\n");
        }
    }

    public static boolean isAccept() {
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String response = userInput.nextLine();
            try {
                if (response.matches(YES_REGEX)) {
                    return true;
                } else if (response.matches(NO_REGEX)) {
                    return false;
                } else {
                    throw new CommandInputException("Invalid input. Please try again.\n");
                }
            } catch (CommandInputException e) {
                indentPrint(e.getMessage());
            }
        }
    }

}
