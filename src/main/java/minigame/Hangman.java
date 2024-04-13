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
     * Initiates and manages the gameplay loop for Hangman, processing player guesses and advancing game rounds until
     * completion.
     *
     * Key functionalities include:
     * 1. Displaying game instructions and welcoming the player.
     * 2. Executing a game loop that continues through a maximum of 5 rounds or until the player accumulates three
     * wrong guesses.
     * 3. Each round involves:
     *    - Initializing the game state with a new word, hiding one letter.
     *    - Displaying the current word state and prompting for a guess.
     *    - Validating the player's input to ensure it is a single alphabetic character.
     *    - Checking the guess against the chosen word, updating the game state accordingly.
     * 4. Concludes the game by displaying the final result based on the game's outcome (win, loss, or draw).
     *
     * This method is central to the player interaction and game logic, ensuring that gameplay progresses smoothly
     * and rules are enforced.
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
