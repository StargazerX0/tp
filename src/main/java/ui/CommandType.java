package ui;

import exception.CommandInputException;

/**
 * Defines the types of commands that can be input by the player in the game.
 * Each enum constant represents a specific command, along with a regex pattern to match user input.
 */
public enum CommandType {
    HELP("(?i)help\\s*"),
    EXERCISE("(?i)exercise\\s*"),
    WORK("(?i)work\\s*"),
    REST("(?i)rest\\s*"),
    STATUS("(?i)status\\s*"),
    UPGRADE("(?i)upgrade\\s*"),
    STOCK("(?i)stock\\s*"),
    ADJUST_SALARY("(?i)(raise|lower)\\s+\\d+\\s*"),
    COMPANY("(?i)company\\s*"),
    HIRE("(?i)hire\\s+\\d+\\s*"),
    FIRE("(?i)fire\\s+\\d+\\s*"),
    SELLSTOCK("(?i)sellstock\\s*"),
    BOND("(?i)bond\\s*"),
    SELLBOND("(?i)sellbond\\s*"),
    SELLCRYPTOCURRENCY("(?i)sellcrypto\\s*"),
    CRYPTOCURRENCY("(?i)crypto\\s*"),
    EXIT("(?i)(bye|exit)\\s*");


    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    /**
     * Analyses user input and determines the corresponding CommandType.
     *
     * @param userInput The raw input from the user.
     * @return The matching CommandType.
     * @throws CommandInputException If no matching command is found.
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
