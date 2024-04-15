package minigame;

import ui.ResponseManager;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**
 * Implements a Hangman game with a focus on software engineering terminology.
 * Manages game logic, including initialization of game states, handling guesses, and determining game outcomes.
 */
public class Hangman implements MiniGame {

    /**
     * A collection of words used in the Hangman game. These words are related to software engineering concepts in
     * CS2113.
     */
    protected static final String[] WORDS = {
        "java", "computer", "program", "keyboard", "mouse",
        "algorithm", "software", "hardware", "network", "database",
        "security", "coding", "development", "design", "architecture",
        "interface", "application", "system", "technology", "internet",
        "server", "client", "cloud", "virtual", "binary",
        "function", "variable", "class", "object", "method",
        "loop", "condition", "array", "list", "string",
        "integer", "boolean", "char", "exception", "thread",
        "cs2113"
    };

    private String chosenWord;
    private Set<Character> guessedLetters;
    private int round;
    private int totalWrongGuesses;

    /**
     * Initializes a new game of Hangman with default settings, setting the stage for multiple rounds with a focus on
     * software engineering terms.
     */
    public Hangman() {
        round = 0;
        totalWrongGuesses = 0;
    }

    /**
     * Prepares a new round by selecting a random word from the word list and revealing all letters except one.
     */
    private void initializeGame() {
        int randomIndex = (int) (Math.random() * WORDS.length);
        chosenWord = WORDS[randomIndex];
        assert chosenWord != null && !chosenWord.isEmpty() : "Chosen word must not be null or empty";

        guessedLetters = new HashSet<>();
        char letterToHide = chosenWord.charAt((int) (Math.random() * chosenWord.length()));
        for (char c : chosenWord.toCharArray()) {
            if (c != letterToHide) {
                guessedLetters.add(c);
            }
        }
    }

    /**
     * Displays the current state of the word, showing guessed letters and underscores for unguessed letters.
     */
    private void printWordState() {
        StringBuilder display = new StringBuilder();
        for (char letter : chosenWord.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                display.append(letter).append(" ");
            } else {
                display.append("_ ");
            }
        }
        ResponseManager.indentPrint(display.toString() + "\n");
    }

    /**
     * Determines the current status of the game, indicating whether the game is ongoing, won, or lost.
     * @return -1 if the game is lost, 1 if won, and 0 if still ongoing.
     */
    public int getStatus() {
        if (totalWrongGuesses >= 3) {
            return -1;
        } else if (round == 5) {
            return 1;
        }
        return 0;
    }

    /**
     * Manages the Hangman game loop, guiding players through up to 5 rounds or until three incorrect guesses occur.
     *
     * Gameplay steps:
     * - Welcome the player and display game instructions.
     * - For each round:
     *   - Select and display a new word with one letter hidden.
     *   - Prompt for a guess and validate it as a single alphabetic character.
     *   - Check the guess, update the game state, and show the updated word.
     * - End the game by showing the result (win, loss, or draw).
     *
     * This method handles game dynamics and player interactions, ensuring the gameplay follows the defined rules
     * and progresses effectively.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        ResponseManager.indentPrint("Welcome to the Hangman Game!\n");
        ResponseManager.indentPrint("This game's words are related to concepts in Software Engineering.\n");
        ResponseManager.indentPrint("Please enter only one character at a time as your guess.\n");

        while (round < 5 && totalWrongGuesses < 3) {
            initializeGame();
            ResponseManager.indentPrint("Round " + (round + 1) + ":\n");
            printWordState();

            ResponseManager.indentPrint("Guess the missing letter:\n");
            String input = scanner.nextLine().toLowerCase();

            while (input.length() != 1) {
                ResponseManager.indentPrint("Invalid input. Please guess exactly one letter at a time:\n");
                input = scanner.nextLine().toLowerCase();
            }

            char guessedChar = input.charAt(0);
            if (chosenWord.indexOf(guessedChar) >= 0 && !guessedLetters.contains(guessedChar)) {
                guessedLetters.add(guessedChar);
                printWordState();
                ResponseManager.indentPrint("Correct! Moving to next round.\n");
            } else {
                totalWrongGuesses++;
                ResponseManager.indentPrint("Wrong guess!\n");
            }
            round++;
        }

        outputResult();
    }

    /**
     * Outputs the result of the game, indicating whether the player won or lost and concluding the game interaction.
     */
    public void outputResult() {
        if (totalWrongGuesses >= 3) {
            ResponseManager.indentPrint("Game over! Too many wrong guesses.\n");
        } else if (round == 5) {
            ResponseManager.indentPrint("Game completed! You finished all rounds.\n");
        }
    }

    public String getChosenWord() {
        return chosenWord;
    }
}
