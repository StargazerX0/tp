package minigame;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import ui.ResponseManager;

public class TypingGame implements MiniGame {
    private static final double TIME_RATIO = 1000.0;
    private static final int PERCENTAGE = 100;
    private static final int TIME_LIMIT = 20;
    private static final String[] TEXTS_TO_TYPE = {
        "The quick brown fox jumps over the lazy dog.",
        "Pack my box with five dozen liquor jugs.",
        "How vexingly quick daft zebras jump!",
        "The five boxing wizards jump quickly.",
        "Bright vixens jump; dozy fowl quack.",
        "Quick zephyrs blow, vexing daft Jim.",
        "Sphinx of black quartz, judge my vow.",
        "Jinxed wizards pluck ivy from the big quilt.",
        "Cozy lummox gives smart squid who asks for job pen.",
        "The jay, pig, fox, zebra, and my wolves quack!",
        "Blowzy night-frumps vex'd Jack Q.",
        "Lazy movers quit hard-packing of paper jewelry boxes.",
        "Five or six big jet planes zoomed quickly by the tower.",
        "Expect skilled signwriters to use many jazzy, quaint old alphabets effectively.",
        "Heavy boxes perform quick waltzes and jigs.",
        "The quick onyx goblin jumps over the lazy dwarf.",
        "Crazy Fredericka bought many very exquisite opal jewels.",
        "Sixty zippers were quickly picked from the woven jute bag.",
        "Amazingly few discotheques provide jukeboxes.",
        "Hark! Toxic jungle water vipers quietly drop on zebras for meals!"
    };

    private static final String START_MSG = "Welcome to the Typing Game!\n"
            + "Type the following text as fast as you can:\n";
    private static final String GREEN_COLOR = "\033[0;32m";
    private static final String RED_COLOR = "\033[0;31m";
    private static final String RESET = "\033[0m";
    private static final Logger TG_LOGGER = Logger.getLogger(TypingGame.class.getName());
    private int accuracy;
    private double timeSpent;

    private String textToType;

    private final String[] userInput;

    public TypingGame() {
        this.accuracy = 0;
        this.timeSpent = 0;
        this.textToType = TEXTS_TO_TYPE[new Random().nextInt(TEXTS_TO_TYPE.length)];
        this.userInput = new String[] {""};
    }

    private static void setupLogger() {
        LogManager.getLogManager().reset();
        TG_LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        TG_LOGGER.addHandler(consoleHandler);
    }

    public void startGame() {
        setupLogger();
        CompletableFuture<String> finalScore = CompletableFuture.supplyAsync(() -> {
            Scanner scanner = new Scanner(System.in);
            ResponseManager.indentPrint(START_MSG);
            ResponseManager.indentPrint(GREEN_COLOR + textToType + RESET + "\n");
            ResponseManager.indentPrint(
                    "Press" + RED_COLOR + " ENTER " + RESET + "to" + RED_COLOR + " start " + RESET + "\n");
            // Wait for user to press enter
            scanner.nextLine();

            typingGameLogic(scanner);
            TG_LOGGER.info("User has completed the game");
            return "Good job! You finished within the time limit!\n";
        });

        try {
            ResponseManager.indentPrint(finalScore.get(TIME_LIMIT, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            this.timeSpent = TIME_LIMIT;
            finalScore.cancel(true);
            System.out.println("\nTime's up!!!! Your input is not captured TAT\n");
            TG_LOGGER.info("User did not complete the game in time");
        } catch (InterruptedException | ExecutionException e) {
            ResponseManager.indentPrint("An error occurred while calculating your score.\n");
            TG_LOGGER.log(Level.SEVERE, "An error occurred while calculating the score", e);
        }
    }

    private void typingGameLogic(Scanner scanner) {
        long startTime = System.currentTimeMillis();
        System.out.print("Type here: ");
        userInput[0] = scanner.nextLine();
        this.timeSpent = (System.currentTimeMillis() - startTime) / TIME_RATIO;
        this.accuracy = calculateAccuracy();
    }

    private int calculateAccuracy() {
        int correctCharacters = 0;
        int typedLength = Math.min(textToType.length(), userInput[0].length());
        for (int i = 0; i < typedLength; i++) {
            if (textToType.charAt(i) == userInput[0].charAt(i)) {
                correctCharacters++;
            }
        }
        assert correctCharacters <= textToType.length() :
                "Correct characters should not exceed the length of the text";
        return (correctCharacters * PERCENTAGE / textToType.length());
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public void outputResult() {
        String response = (this.accuracy >= 75) ? "Great job!" :
                (this.accuracy >= 50) ? "Good effort!" : "Keep practicing!";
        ResponseManager.indentPrint(
                String.format("You typed at %d%% accuracy in %.2f seconds!\n" +
                        "%s\n", this.accuracy, this.timeSpent, response));
    }
}
