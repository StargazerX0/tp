package command;

import exception.CommandInputException;
import ui.CommandType;
import ui.Parser;

public class CommandFactory {
    public static Command create(String userInput) throws CommandInputException {
        CommandType commandType = Parser.parseCommand(userInput);
        switch (commandType) {
        case WORK:
            return new WorkCommand();

        case REST:
            return new RestCommand();

        case EXERCISE:
            return new ExerciseCommand();

        case STATUS:
            return new CheckStatusCommand();

        case HELP:
            return new HelpCommand();

        case UPGRADE:
            return new UpgradeCommand();

        case STOCK:
            return new StockCommand();

        case HIRE:
            String hireNum = Parser.separateCommand(userInput)[1];
            return new HireEmployeeCommand(Integer.parseInt(hireNum));

        case FIRE:
            String fireNum = Parser.separateCommand(userInput)[1];
            return new FireEmployeeCommand(Integer.parseInt(fireNum));

        case ADJUST_SALARY:
            String[] commandParts = Parser.separateCommand(userInput);
            String changeType = commandParts[0].toLowerCase();
            String amount= commandParts[1];
            return new AdjustSalaryCommand(changeType, Integer.parseInt(amount));

        default:
            return new ExitCommand();
        }
    }
}
