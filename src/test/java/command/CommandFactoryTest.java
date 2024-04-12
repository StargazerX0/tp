package command;

import exception.CommandInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link CommandFactory} class to ensure it correctly creates instances
 * of different {@link Command} implementations based on the provided string input.
 */
class CommandFactoryTest {

    /**
     * Verifies that the CommandFactory creates an instance of WorkCommand when the "work" command is specified.
     *
     * @throws CommandInputException if the input command is invalid.
     */
    @Test
    void createWorkCommandReturnsWorkCommandInstance() throws CommandInputException {
        assertInstanceOf(WorkCommand.class, CommandFactory.create("work"), "Expected WorkCommand instance.");
    }

    /**
     * Verifies that the CommandFactory creates an instance of RestCommand when the "rest" command is specified.
     *
     * @throws CommandInputException if the input command is invalid.
     */
    @Test
    void createRestCommandReturnsRestCommandInstance() throws CommandInputException {
        assertInstanceOf(RestCommand.class, CommandFactory.create("rest"), "Expected RestCommand instance.");
    }

    /**
     * Verifies that the CommandFactory creates an instance of ExerciseCommand when the "exercise" command is specified.
     *
     * @throws CommandInputException if the input command is invalid.
     */
    @Test
    void createExerciseCommandReturnsExerciseCommandInstance() throws CommandInputException {
        assertInstanceOf(ExerciseCommand.class, CommandFactory.create("exercise"),
                "Expected ExerciseCommand instance.");
    }

    /**
     * Verifies that the CommandFactory creates an instance of CheckStatusCommand when the "status" command is specified
     * @throws CommandInputException if the input command is invalid.
     */
    @Test
    void createStatusCommandReturnsCheckStatusCommandInstance() throws CommandInputException {
        assertInstanceOf(CheckStatusCommand.class, CommandFactory.create("status"),
                "Expected CheckStatusCommand instance.");
    }

    /**
     * Verifies that the CommandFactory creates an instance of HireEmployeeCommand when the "hire" command
     * with arguments is specified.
     * This tests the factory's ability to handle commands that require additional arguments.
     *
     * @throws CommandInputException if the input command is invalid.
     */
    @Test
    void createHireCommandWithArgumentsReturnsHireEmployeeCommandInstance() throws CommandInputException {
        Command command = CommandFactory.create("hire 5");
        assertInstanceOf(HireEmployeeCommand.class, command,
                "Expected HireEmployeeCommand instance with argument.");
    }

    /**
     * Verifies that the CommandFactory throws a CommandInputException when an invalid command string is provided.
     * This tests the factory's error handling for unrecognized commands.
     */
    @Test
    void createInvalidInputThrowsCommandInputException() {
        assertThrows(CommandInputException.class, () -> CommandFactory.create("invalidCommand"),
                "Expected CommandInputException for invalid command.");
    }

    /**
     * Verifies that the CommandFactory throws a CommandInputException when an empty command string is provided.
     * This tests the factory's error handling for empty command inputs.
     */
    @Test
    void createEmptyInputThrowsCommandInputException() {
        assertThrows(CommandInputException.class, () -> CommandFactory.create(""),
                "Expected CommandInputException for empty input.");
    }
}


