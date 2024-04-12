package command;

import exception.CommandInputException;
import org.junit.jupiter.api.Test;
import ui.CommandType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link CommandType} class.
 * Ensures that CommandType can correctly analyze and match input strings to the appropriate enum values.
 */
class CommandTypeTest {

    /**
     * Tests CommandType's ability to correctly identify valid commands.
     * @throws CommandInputException if an invalid command is analyzed.
     */
    @Test
    void analyseInput_validCommand_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.EXIT, CommandType.analyseInput("bye"),
                "Analyzing 'bye' should return EXIT CommandType.");
        assertEquals(CommandType.WORK, CommandType.analyseInput("work"),
                "Analyzing 'work' should return WORK CommandType.");
        assertEquals(CommandType.REST, CommandType.analyseInput("rest"),
                "Analyzing 'rest' should return REST CommandType.");
        assertEquals(CommandType.EXERCISE, CommandType.analyseInput("exercise"),
                "Analyzing 'exercise' should return EXERCISE CommandType.");
        assertEquals(CommandType.STATUS, CommandType.analyseInput("status"),
                "Analyzing 'status' should return STATUS CommandType.");
    }

    /**
     * Tests CommandType's response to an invalid command string by expecting a CommandInputException.
     */
    @Test
    void analyseInput_invalidCommand_throwsException() {
        assertThrows(CommandInputException.class, () -> CommandType.analyseInput("walk"),
                "An invalid command should throw CommandInputException.");
    }

    /**
     * Verifies that CommandType can correctly identify valid commands, even with trailing spaces.
     * @throws CommandInputException if an invalid command is analyzed.
     */
    @Test
    void analyseInput_validCommandWithSpace_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.REST, CommandType.analyseInput("rest "),
                "Analyzing 'rest ' with space should still return REST CommandType.");
    }

    /**
     * Ensures that CommandType analysis is case insensitive for uppercase inputs.
     * @throws CommandInputException if an invalid command is analyzed.
     */
    @Test
    void analyseInput_validCommandWithUpperCase_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.EXERCISE, CommandType.analyseInput("EXERCISE"),
                "Analyzing 'EXERCISE' in uppercase should return EXERCISE CommandType.");
    }

    /**
     * Checks that CommandType analysis is case insensitive for mixed case inputs.
     * @throws CommandInputException if an invalid command is analyzed.
     */
    @Test
    void analyseInput_validCommandWithMixedCase_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.EXERCISE, CommandType.analyseInput("eXeRcIsE"),
                "Analyzing 'eXeRcIsE' in mixed case should return EXERCISE CommandType.");
    }

    /**
     * Tests CommandType's ability to correctly identify composite commands.
     * @throws CommandInputException if an invalid command is analyzed.
     */
    @Test
    void analyseInput_validCommandComposite_returnsCommandType() throws CommandInputException {
        assertEquals(CommandType.HIRE, CommandType.analyseInput("hire 5"),
                "Analyzing 'hire 5' should return HIRE CommandType.");
    }

    /**
     * Tests that CommandType analysis ignores numerical arguments in the command string.
     * @throws CommandInputException if an invalid command is analyzed.
     */
    @Test
    void analyseInput_commandWithArguments_ignoresArguments() throws CommandInputException {
        assertEquals(CommandType.HIRE, CommandType.analyseInput("hire 10"),
                "Analyzing 'hire 10' should ignore the argument and return HIRE CommandType.");
        assertEquals(CommandType.FIRE, CommandType.analyseInput("fire 2000"),
                "Analyzing 'fire 2000' should ignore the argument and return FIRE CommandType.");
    }
}
