package command;

import exception.CommandInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for verifying the functionality of the CommandFactory.
 */
class CommandFactoryTest {

    @Test
    void create_WorkCommand_ReturnsWorkCommandInstance() throws CommandInputException {
        assertInstanceOf(WorkCommand.class, CommandFactory.create("work"), "Expected WorkCommand instance.");
    }

    @Test
    void create_RestCommand_ReturnsRestCommandInstance() throws CommandInputException {
        assertInstanceOf(RestCommand.class, CommandFactory.create("rest"), "Expected RestCommand instance.");
    }

    // Example test for ExerciseCommand
    @Test
    void create_ExerciseCommand_ReturnsExerciseCommandInstance() throws CommandInputException {
        assertInstanceOf(ExerciseCommand.class, CommandFactory.create("exercise"),
                "Expected ExerciseCommand instance.");
    }

    // Example test for CheckStatusCommand
    @Test
    void create_StatusCommand_ReturnsCheckStatusCommandInstance() throws CommandInputException {
        assertInstanceOf(CheckStatusCommand.class, CommandFactory.create("status"),
                "Expected CheckStatusCommand instance.");
    }

    // Test for HireEmployeeCommand with arguments
    @Test
    void create_HireCommandWithArguments_ReturnsHireEmployeeCommandInstance() throws CommandInputException {
        Command command = CommandFactory.create("hire 5");
        assertInstanceOf(HireEmployeeCommand.class, command,
                "Expected HireEmployeeCommand instance with argument.");
    }

    // Test for invalid input
    @Test
    void create_InvalidInput_ThrowsCommandInputException() {
        assertThrows(CommandInputException.class, () -> CommandFactory.create("invalidCommand"),
                "Expected CommandInputException for invalid command.");
    }

    // Test for empty input
    @Test
    void create_EmptyInput_ThrowsCommandInputException() {
        assertThrows(CommandInputException.class, () -> CommandFactory.create(""),
                "Expected CommandInputException for empty input.");
    }

}

