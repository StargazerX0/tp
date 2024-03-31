package ui;

import exception.CommandInputException;

public enum CommandType {
    WORK("(?i)work\\s*"),
    REST("(?i)rest\\s*"),
    EXIT("(?i)bye\\s*"),
    EXERCISE("(?i)exercise\\s*"),
    STATUS("(?i)status\\s*"),
    STOCK("(?i)stock\\s*"),
    SELLSTOCK("(?i)sellstock\\s*"),
    UPGRADE("(?i)upgrade\\s*"),
    HIRE("(?i)hire\\s+\\d+\\s*"),
    FIRE("(?i)fire\\s+\\d+\\s*"),
    ADJUST_SALARY("(?i)(raise|lower)\\s+\\d+\\s*"),
    HELP("(?i)help\\s*");

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
