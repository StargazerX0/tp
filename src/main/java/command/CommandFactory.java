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
        case SELLSTOCK:
            return new SellStockCommand();

        case SELLBOND:
            return new SellBondCommand();

        case STOCK:
            return new StockCommand();

        case SELLCRYPTOCURRENCY:
            return new SellCryptoCommand();

        case BOND:
            return new BuyBondCommand();

        case CRYPTOCURRENCY:
            return new BuyCryptoCommand();

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

        case COMPANY:
            return new CompanyStatsCommand();

        case INFLOW:
            String[] inflowParams = Parser.separateCommand(userInput);
            String inflowDescription = inflowParams[1];
            int inflowAmount = Integer.parseInt(inflowParams[2]);
            return new InFlowCommand(inflowDescription, inflowAmount);

        case OUTFLOW:
            String[] outflowParams = Parser.separateCommand(userInput);
            String outflowDescription = outflowParams[1];
            int outflowAmount = Integer.parseInt(outflowParams[2]);
            return new OutFlowCommand(outflowDescription, outflowAmount);


        default:
            return new ExitCommand();
        }
    }
}
