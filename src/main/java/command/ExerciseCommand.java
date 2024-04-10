package command;

import exception.InvalidMarkException;
import minigame.Hangman;
import minigame.TicTacToe;
import player.PlayerProfile;
import ui.ResponseManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExerciseCommand implements Command {

    public void execute(PlayerProfile playerProfile) {
        ResponseManager.indentPrint("Choose your game:\n1. Tic Tac Toe\n2. Hangman\n");
        int gameChoice = getGameChoice();

        if (gameChoice == 1) {
            playTicTacToe(playerProfile);
        } else if (gameChoice == 2) {
            playHangman(playerProfile);
        }
    }

    private int getGameChoice() {
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

    public void playHangman(PlayerProfile playerProfile) {
        Hangman game = new Hangman();
        game.startGame();
        if (game.getStatus() == 1) {
            playerProfile.addHealth(10);
        } else if (game.getStatus() == -1) {
            playerProfile.loseHealth(5);
        }
    }

    public char getMark() throws InvalidMarkException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (!input.equals("X") && !input.equals("O")) {
            throw new InvalidMarkException("Mark can only be X or O\n");
        }
        return input.charAt(0);
    }

    public boolean isExit() {
        return false;
    }

    public boolean canGenerateEvent() {
        return true;
    }
}
