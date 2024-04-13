package command;

import exception.InvalidMarkException;
import minigame.Hangman;
import minigame.TicTacToe;
import player.PlayerProfile;
import ui.ResponseManager;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Command class responsible for handling exercise options within a command-line interface,
 * allowing players to choose and play Tic Tac Toe or Hangman.
 * The outcomes of these games affect the player's health within their profile.
 */
public class ExerciseCommand implements Command {

    /**
     * Executes the main logic for choosing and playing games.
     * The method prompts the user to choose between Tic Tac Toe and Hangman and launches the selected game.
     * Based on the game's outcome, the player's health is updated.
     *
     * @param playerProfile The profile of the player, which will be updated based on the game outcomes.
     */
    public void execute(PlayerProfile playerProfile) {
        ResponseManager.indentPrint("Choose your game:\n1. Tic Tac Toe\n2. Hangman\n");
        int gameChoice = getGameChoice();

        if (gameChoice == 1) {
            playTicTacToe(playerProfile);
        } else if (gameChoice == 2) {
            playHangman(playerProfile);
        }
    }

    /**
     * Retrieves the user's choice of game using input from the console.
     * It ensures that the choice is either 1 (Tic Tac Toe) or 2 (Hangman).
     * If invalid input is provided, it prompts the user again.
     *
     * @return The user's game choice as an integer (1 or 2).
     */
    protected int getGameChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (true) {
            try {
                ResponseManager.indentPrint("Enter your choice (1 for Tic Tac Toe, 2 for Hangman):\n");
                choice = scanner.nextInt();

                if (choice == 1 || choice == 2) {
                    return choice;
                } else {
                    ResponseManager.indentPrint("Invalid choice, please select 1 for Tic Tac Toe or 2 for Hangman\n");
                }
            } catch (InputMismatchException ime) {
                ResponseManager.indentPrint("Please enter a valid integer.\n");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * Initiates a game of Tic Tac Toe. Prompts the user to choose a mark ('X' or 'O').
     * After the game, updates the player profile based on the outcome (win or lose).
     *
     * @param playerProfile The profile of the player, which is updated based on the game outcome.
     */
    private void playTicTacToe(PlayerProfile playerProfile) {
        ResponseManager.indentPrint("Please choose your mark: X or O\n");
        char playerMark = '-';
        while ((playerMark != 'X') && (playerMark != 'O')) {
            try {
                playerMark = getMark();
            } catch (InvalidMarkException e) {
                ResponseManager.indentPrint(e.getMessage());
            }
        }
        TicTacToe game = new TicTacToe(playerMark);
        game.startGame();
        if (game.getStatus() == 1) {
            playerProfile.addHealth(10);
        } else if (game.getStatus() == -1) {
            playerProfile.loseHealth(5);
        }
    }

    /**
     * Initiates a game of Hangman. After the game, updates the player profile based on the outcome (win or lose).
     *
     * @param playerProfile The profile of the player, which is updated based on the game outcome.
     */
    public void playHangman(PlayerProfile playerProfile) {
        Hangman game = new Hangman();
        game.startGame();
        if (game.getStatus() == 1) {
            playerProfile.addHealth(10);
        } else if (game.getStatus() == -1) {
            playerProfile.loseHealth(5);
        }
    }

    /**
     * Gets a valid mark ('X' or 'O') from the player's input for the Tic Tac Toe game.
     * Validates the input to ensure it is correct and throws an exception if not.
     *
     * @return The valid mark chosen by the player.
     * @throws InvalidMarkException if the mark is not 'X' or 'O'.
     */
    public char getMark() throws InvalidMarkException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (!input.equals("X") && !input.equals("O")) {
            throw new InvalidMarkException("Mark can only be X or O\n");
        }
        return input.charAt(0);
    }

    /**
     * Indicates if this command should trigger an exit from the program. Always returns false.
     *
     * @return false, indicating the command does not cause the program to exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Determines if this command can trigger other events. Always returns true.
     *
     * @return true, indicating that this command can generate events.
     */
    public boolean canGenerateEvent() {
        return true;
    }
}
