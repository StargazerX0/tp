package command;

import exception.CommandInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class CommandFactoryTest {

    @Test
    void create_validInput_returnsCommand() throws CommandInputException {
        assertInstanceOf(WorkCommand.class, CommandFactory.create("work"));
        assertInstanceOf(RestCommand.class, CommandFactory.create("rest"));
        assertInstanceOf(ExerciseCommand.class, CommandFactory.create("exercise"));
        assertInstanceOf(UpgradeCommand.class, CommandFactory.create("upgrade"));
        assertInstanceOf(ExitCommand.class, CommandFactory.create("exit"));
        assertInstanceOf(AdjustSalaryCommand.class, CommandFactory.create("raise 100"));
        assertInstanceOf(AdjustSalaryCommand.class, CommandFactory.create("lower 100"));
        assertInstanceOf(HireEmployeeCommand.class, CommandFactory.create("hire 1"));
        assertInstanceOf(FireEmployeeCommand.class, CommandFactory.create("fire 1"));
        assertInstanceOf(CompanyStatsCommand.class, CommandFactory.create("company"));
        assertInstanceOf(StockCommand.class, CommandFactory.create("stock"));
        assertInstanceOf(SellStockCommand.class, CommandFactory.create("sellstock"));
        assertInstanceOf(ExitCommand.class, CommandFactory.create("exit"));
        assertInstanceOf(HelpCommand.class, CommandFactory.create("help"));
        assertInstanceOf(CheckStatusCommand.class, CommandFactory.create("status"));
    }
}
