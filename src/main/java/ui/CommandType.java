package ui;

import exception.CommandInputException;

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
    CRYPTO("(?i)crypto\\s*"),
    EXIT("(?i)(bye|exit)\\s*");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType analyseInput(String userInput) throws CommandInputException {
        for (CommandType commandType : CommandType.values()) {
            if (userInput.matches(commandType.command)) {
                return commandType;
            }
        }
        throw new CommandInputException("Invalid command, please try again!\n");
    }
}
