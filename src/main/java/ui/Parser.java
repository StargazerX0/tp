package ui;

import java.util.Scanner;

import exception.CommandInputException;
import exception.JobSelectException;
import exception.NameInputException;
import static ui.ResponseManager.indentPrint;

public class Parser {
    private static final String NAME = "^[a-zA-Z ]{1,15}$";
    private static final int NAME_LENGTH_LIMIT = 15;
    private static final String YES_REGEX = "(?i)(y|yes)";
    private static final String NO_REGEX = "(?i)(n|no)";
    private static final String ROBOTIC = "/r";
    private static final String SEMI_CONDUCTOR = "/s";
    private static final String AI = "/a";

    /**
     * Parses the name input for player profile initialization.
     * 
     * @param input The name input.
     * @return The parsed name.
     * @throws NameInputException If the name input is invalid or more than 15 characters.
     */
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

    /**
     * Parses the career input for player profile initialization.
     * 
     * @param input The career that user has chosen.
     * @return The parsed career.
     * @throws JobSelectException If the career input is invalid.
     */
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

    /**
     * Parses the command input.
     * 
     * @param input The user command input.
     * @return The parsed command type.
     * @throws CommandInputException If the command input is invalid.
     */
    public static CommandType parseCommand(String input) throws CommandInputException {
        return CommandType.analyseInput(input.trim().toLowerCase());
    }

    /**
     * Speperate command from the user input as a string array, 
     * with the command at index 0 and the information at index 1.
     * 
     * @param input The user input.
     * @return The command and the information of the command as a string array.
     */
    public static String[] separateCommand(String input) {
        return input.split("\\s", 2);
    }

    /**
     * Decode the information from the user input about the command.
     * 
     * @param input The information about the command. 
     * @return The decoded information.
     * @throws CommandInputException
     */
    public static int decodeInfo(String input) throws CommandInputException {
        try {
            return Integer.parseInt(input.trim());
        } catch (IllegalArgumentException e) {
            throw new CommandInputException("Invalid number, please try again\n");
        }
    }

    /**
     * Check if the user input yes or no.
     * 
     * @return True if the user input yes, false if the user input no.
     */
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
