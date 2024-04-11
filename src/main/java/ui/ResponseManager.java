package ui;

import player.PlayerProfile;

/**
 * Manages the responses and messages displayed to the player throughout the game.
 * Includes utilities for printing various types of messages with optional color coding.
 */
public class ResponseManager {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    private static final String INITIALIZATION_MESSAGE = "Initializing...\n"
        + "Enter your name:\n";
    private static final String INDENTATION =
            "===".repeat(20);
    private static final String JOB_SELECT_MSG = "Choose your job type:\n" +
            "for Robotics, enter '/r'\n" +
            "for Semi-conductor industry, enter '/s'\n" +
            "for Artificial intelligence, enter '/a'\n";
    private static final String BYE_MSG = "Bye bye adventurer!";
    private static final String HELP_MSG =
            "Enter ur action!\n" +
            "work - to work\n" +
            "rest - to rest\n" +
            "exercise - to exercise\n" +
            "status - to check status\n" +
            "upgrade - to upgrade(!NOTE you need to have at least $10000)\n" +
            "bye - to exit\n" +
            INDENTATION +
            "\nCommands below are only available for advanced players:\n" +
            INDENTATION +
            "\nstock - to purchase stocks from the stock market \n" +
            "sellstock - to sell all of your stocks \n" +
            "company - to check company status\n" +
            "hire <number> - to hire employee (each employee cost $1000 to hire)\n" +
            "fire <number> - to fire employee\n" +
            "raise <number> - to raise salary\n" +
            "lower <number> - to lower salary\n";

    public static void printBoard(String boardInfo) {
        indentPrint(boardInfo + "\n");
    }

    /**
     * Prints a message with decorative indentation on both sides.
     *
     * @param message The message to print.
     */
    public static void indentPrint(String message) {
        System.out.println(INDENTATION + "\n" + message + INDENTATION);
    }

    public static void printGameInit() {
        indentPrint(INITIALIZATION_MESSAGE);
    }

    public static void printJobSelect() {
        indentPrint(JOB_SELECT_MSG);
    }

    public static void printWelcome(PlayerProfile playerProfile) {
        System.out.println("Welcome, " + playerProfile.toString());
    }

    public static void echoChosenIndustry(String input) {
        System.out.println("You have chosen " + input);
    }

    public static void printGoodbye() {
        System.out.println(BYE_MSG);
    }

    public static void printCurrentRound(int currentRound) {
        System.out.println("Current round: " + currentRound + "\n" +
                "you have " + (20 - currentRound) + " rounds left");
        System.out.println(INDENTATION);
    }

    public static void printActionLeft(int actionsLeft) {
        String actionLeftStr = actionsLeft == 1 ? " action left" : " actions left";
        System.out.println("You have " + actionsLeft + actionLeftStr +
                "\nInput your action! If needed, type 'help' for more info");
        System.out.println(INDENTATION);
    }

    public static void printHelp() {
        indentPrint(HELP_MSG);
    }

    public static void printIndentation() {
        System.out.println(INDENTATION);
    }

    public static void printCompanyProfit(int profit) {
        if (profit > 0) {
            indentPrint("Good job! You have earned " + profit + " assets from your company!\n");
        } else {
            indentPrint("You have lost " + profit + " assets from your company!" +
                    "\nPlease manage your company better next time!\n");
        }
    }

    public static void promptRestart() {
        indentPrint("Do you want to restart the game? (yes/no)");
    }
}
