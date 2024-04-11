package minigame;

import ui.ResponseManager;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Hangman implements MiniGame {
    private static final String[] WORDS = {
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

    public Hangman() {
        round = 0;
        totalWrongGuesses = 0;
    }

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

    public int getStatus() {
        if (totalWrongGuesses >= 3) {
            return -1;
        } else if (round == 5) {
            return 1;
        }
        return 0;
    }

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

    public void outputResult() {
        if (totalWrongGuesses >= 3) {
            ResponseManager.indentPrint("Game over! Too many wrong guesses.\n");
        } else if (round == 5) {
            ResponseManager.indentPrint("Game completed! You finished all rounds.\n");
        }
    }
}
