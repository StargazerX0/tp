package minigame;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Stream;

import ui.ResponseManager;
import static ui.ResponseManager.RED;
import static ui.ResponseManager.GREEN;
import static ui.ResponseManager.YELLOW;
import static ui.ResponseManager.RESET;

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

    private static final String START_MSG = "Welcome to the Typing Game!\n" +
            "Try to finish typing the given text within 20 seconds.\n" +
            "Type the following as fast as you can:\n";
    private static final Logger TG_LOGGER = Logger.getLogger(TypingGame.class.getName());
    private int accuracy;
    private double timeSpent;
    private final String textToType;
    private final String[] userInput;

    public TypingGame() {
        this.accuracy = 0;
        this.timeSpent = 0;
        this.textToType = TEXTS_TO_TYPE[new Random().nextInt(TEXTS_TO_TYPE.length)];
        this.userInput = new String[] {""};
    }

    public TypingGame(String textToType) {
        this.accuracy = 0;
        this.timeSpent = 0;
        this.textToType = textToType;
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
        Scanner scanner = new Scanner(System.in);
        ResponseManager.indentPrint(START_MSG);
        ResponseManager.indentPrint(GREEN + textToType + RESET + "\n");
        ResponseManager.indentPrint(
                "Press" + RED + " ENTER " + RESET + "to" + RED + " start " + RESET + "\n");
        // Wait for user to press enter
        scanner.nextLine();

        typingGameLogic(scanner);
        TG_LOGGER.info("User has completed the game");
    }

    private void typingGameLogic(Scanner scanner) {
        long startTime = System.currentTimeMillis();
        ResponseManager.printIndentation();
        System.out.print(YELLOW + "Type here: " + RESET);
        userInput[0] = scanner.nextLine();
        this.timeSpent = (System.currentTimeMillis() - startTime) / TIME_RATIO;
        this.accuracy = calculateAccuracy();
    }

    private int calculateAccuracy() {
        String textToCheck = userInput[0].trim();
        int normalAccuracy = compareOneToOne(textToCheck);
        int userAgainstText = compareUserToActual(textToCheck);
        int textAgainstUser = compareActualToUser(textToCheck);
        int correctCharacters =
                Stream.of(normalAccuracy, userAgainstText, textAgainstUser)
                        .reduce(0, (x, y) -> Math.max(x, y));

        assert correctCharacters <= textToType.length() :
                "Correct characters should not exceed the length of the text";
        correctCharacters = Math.max(correctCharacters, 0);
        return (correctCharacters * PERCENTAGE / textToType.length());
    }

    private int compareUserToActual(String textToCheck) {
        int correctCharacters = 0;
        int checkPos = 0;

        for (int i = 0; checkPos < textToType.length() && i < textToCheck.length(); i++) {
            if (textToType.charAt(checkPos) == textToCheck.charAt(i)) {
                correctCharacters++;
                checkPos++;
            } else {
                correctCharacters--;
            }
        }
        return correctCharacters;
    }

    private int compareActualToUser(String textToCheck) {
        int correctCharacters = 0;
        int checkPos = 0;

        for (int i = 0; i < textToType.length() && checkPos < textToCheck.length(); i++) {
            if (textToType.charAt(i) == textToCheck.charAt(checkPos)) {
                correctCharacters++;
                checkPos++;
            } else {
                correctCharacters--;
            }
        }
        return correctCharacters;
    }

    private int compareOneToOne(String textToCheck) {
        int correctCharacters = 0;
        int checkLength = Math.min(textToType.length(), textToCheck.length());

        for (int i = 0; i < checkLength; i++) {
            if (textToType.charAt(i) == textToCheck.charAt(i)) {
                correctCharacters++;
            }
        }
        return correctCharacters;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public boolean isOverTime() {
        return this.timeSpent > TIME_LIMIT;
    }

    public void outputResult() {
        String response = (this.accuracy >= 75) ? "Great job!" :
                (this.accuracy >= 50) ? "Good effort!" : "Keep practicing!";
        ResponseManager.indentPrint(
                String.format("You typed at %d%% accuracy in %.2f seconds!\n" +
                        "%s\n", this.accuracy, this.timeSpent, response));
        if (this.timeSpent > TIME_LIMIT) {
            ResponseManager.indentPrint(
                    String.format("You have exceeded the time limit by %.2f sec!\n", (timeSpent - TIME_LIMIT)) +
                    "The money earned will be reduced by 50%.\n");
        }
        TG_LOGGER.info("User has completed the game");
    }
}
