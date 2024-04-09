package ui;

import exception.CommandInputException;

public enum CommandType {
    ADJUST_SALARY("(?i)(raise|lower)\\s+\\d+\\s*"),
    BOND("(?i)bond\\s*"),
    COMPANY("(?i)company\\s*"),
    CRYPTOCURRENCY("(?i)cryptocurrency\\s*"),
    EXERCISE("(?i)exercise\\s*"),
    EXIT("(?i)bye\\s*"),
    FIRE("(?i)fire\\s+\\d+\\s*"),
    HELP("(?i)help\\s*"),
    HIRE("(?i)hire\\s+\\d+\\s*"),
    REST("(?i)rest\\s*"),
    SELLBOND("(?i)sellbond\\s*"),
    SELLSTOCK("(?i)sellstock\\s*"),
    STATUS("(?i)status\\s*"),
    STOCK("(?i)stock\\s*"),
    UPGRADE("(?i)upgrade\\s*"),
    WORK("(?i)work\\s*"),
    SELLCRYPTOCURRENCY("(?i)sellcryptocurrency\\s*"),
    INFLOW("(?i)inflow\\s+\\d+\\s*(.+)?"),
    OUTFLOW("(?i)outflow\\s+\\d+\\s*(.+)?");

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
