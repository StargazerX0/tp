package ui;

import exception.CommandInputException;

/**
 * Represents the enumeration of all possible commands that the player can input.
 */
public enum CommandType {
    HELP("\\s*(?i)help\\s*"),
    EXERCISE("\\s*(?i)exercise\\s*"),
    WORK("\\s*(?i)work\\s*"),
    REST("\\s*(?i)rest\\s*"),
    STATUS("\\s*(?i)status\\s*"),
    UPGRADE("\\s*(?i)upgrade\\s*"),
    STOCK("\\s*(?i)stock\\s*"),
    ADJUST_SALARY("\\s*(?i)(raise|lower)\\s+\\d+\\s*"),
    COMPANY("\\s*(?i)company\\s*"),
    HIRE("\\s*(?i)hire\\s+\\d+\\s*"),
    FIRE("\\s*(?i)fire\\s+\\d+\\s*"),
    SELLSTOCK("\\s*(?i)sellstock\\s*"),
    BOND("\\s*(?i)bond\\s*"),
    CRYPTO("\\s*(?i)crypto\\s*"),
    EXIT("\\s*(?i)(bye|exit)\\s*");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    /**
     * Analyses the user input and return the corresponding CommandType.
     *
     * @param userInput the user input
     * @return the corresponding CommandType
     * @throws CommandInputException if the user input is invalid
     */
    public static CommandType analyseInput(String userInput) throws CommandInputException {
        for (CommandType commandType : CommandType.values()) {
            if (userInput.matches(commandType.command)) {
                return commandType;
            }
        }
        throw new CommandInputException("Invalid command, please try again!\n");
    }
}
