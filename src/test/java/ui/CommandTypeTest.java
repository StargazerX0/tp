package ui;

import exception.CommandInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandTypeTest {

    @Test
    void analyseInput_validInput_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.HELP, CommandType.analyseInput("help"));
        assertEquals(CommandType.EXERCISE, CommandType.analyseInput("exercise"));
        assertEquals(CommandType.WORK, CommandType.analyseInput("work"));
        assertEquals(CommandType.REST, CommandType.analyseInput("rest"));
        assertEquals(CommandType.STATUS, CommandType.analyseInput("status"));
        assertEquals(CommandType.UPGRADE, CommandType.analyseInput("upgrade"));
        assertEquals(CommandType.STOCK, CommandType.analyseInput("stock"));
        assertEquals(CommandType.ADJUST_SALARY, CommandType.analyseInput("raise 1000"));
        assertEquals(CommandType.ADJUST_SALARY, CommandType.analyseInput("lower 1000"));
        assertEquals(CommandType.COMPANY, CommandType.analyseInput("company"));
        assertEquals(CommandType.HIRE, CommandType.analyseInput("hire 1"));
        assertEquals(CommandType.FIRE, CommandType.analyseInput("fire 1"));
        assertEquals(CommandType.SELLSTOCK, CommandType.analyseInput("sellstock"));
        assertEquals(CommandType.BOND, CommandType.analyseInput("bond"));
        assertEquals(CommandType.CRYPTO, CommandType.analyseInput("crypto"));
        assertEquals(CommandType.EXIT, CommandType.analyseInput("bye"));
        assertEquals(CommandType.EXIT, CommandType.analyseInput("exit"));
    }

    @Test
    void analyseInput_invalidInput_throwsCommandInputException() {
        assertThrows(CommandInputException.class, () -> CommandType.analyseInput("invalid"));
    }

    @Test
    void analyseInput_validInputMixedCase_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.HELP, CommandType.analyseInput("HELP"));
        assertEquals(CommandType.EXERCISE, CommandType.analyseInput("ExERCiSE"));
        assertEquals(CommandType.WORK, CommandType.analyseInput("WOrK"));
        assertEquals(CommandType.REST, CommandType.analyseInput("ReST"));
        assertEquals(CommandType.STATUS, CommandType.analyseInput("STATUS"));
        assertEquals(CommandType.UPGRADE, CommandType.analyseInput("UpGRADE"));
        assertEquals(CommandType.STOCK, CommandType.analyseInput("STocK"));
        assertEquals(CommandType.ADJUST_SALARY, CommandType.analyseInput("RAisE 1000"));
        assertEquals(CommandType.ADJUST_SALARY, CommandType.analyseInput("LoWER 1000"));
        assertEquals(CommandType.COMPANY, CommandType.analyseInput("cOMPANy"));
        assertEquals(CommandType.HIRE, CommandType.analyseInput("hiRE 1"));
        assertEquals(CommandType.FIRE, CommandType.analyseInput("FIre 1"));
        assertEquals(CommandType.SELLSTOCK, CommandType.analyseInput("SeLLsTOCK"));
        assertEquals(CommandType.BOND, CommandType.analyseInput("BoND"));
        assertEquals(CommandType.CRYPTO, CommandType.analyseInput("CrYPTO"));
        assertEquals(CommandType.EXIT, CommandType.analyseInput("Bye"));
        assertEquals(CommandType.EXIT, CommandType.analyseInput("EXiT"));
    }

    @Test
    void analyseInput_validInputWithSpace_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.HELP, CommandType.analyseInput("help "));
        assertEquals(CommandType.EXERCISE, CommandType.analyseInput(" exercise "));
        assertEquals(CommandType.WORK, CommandType.analyseInput("   work "));
        assertEquals(CommandType.REST, CommandType.analyseInput("rest "));
        assertEquals(CommandType.STATUS, CommandType.analyseInput(" status "));
        assertEquals(CommandType.UPGRADE, CommandType.analyseInput(" upgrade "));
        assertEquals(CommandType.STOCK, CommandType.analyseInput(" stock "));
        assertEquals(CommandType.ADJUST_SALARY, CommandType.analyseInput("raise 1000 "));
        assertEquals(CommandType.ADJUST_SALARY, CommandType.analyseInput(" lower 1000 "));
        assertEquals(CommandType.COMPANY, CommandType.analyseInput("company "));
        assertEquals(CommandType.HIRE, CommandType.analyseInput("hire   1 "));
        assertEquals(CommandType.FIRE, CommandType.analyseInput("fire  1 "));
        assertEquals(CommandType.SELLSTOCK, CommandType.analyseInput("sellstock  "));
        assertEquals(CommandType.BOND, CommandType.analyseInput("bond "));
        assertEquals(CommandType.CRYPTO, CommandType.analyseInput("    crypto "));
        assertEquals(CommandType.EXIT, CommandType.analyseInput("   bye "));
        assertEquals(CommandType.EXIT, CommandType.analyseInput("  exit "));
    }
}