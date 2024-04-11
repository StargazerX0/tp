package exception;

/**
 * Represents exceptions related to invalid command inputs in the EconoCraft game.
 * This class extends the standard {@code Exception} class, providing more specific context
 * for errors encountered when processing player commands.
 */
public class CommandInputException extends Exception{

    /**
     * Constructs a new {@code CommandInputException} with the specified detail message.
     * The message provides more information about the nature of the command input error.
     *
     * @param message The detail message. This message is saved for later retrieval by the {@code getMessage()} method.
     */

    public CommandInputException(String message) {
        super(message);
    }
}
