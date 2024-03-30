package ui;

import player.PlayerProfile;

public class ResponseManager {
    private static final String INITIALIZATION_MESSAGE = "Initializing...\n"
        + "Enter your name: \n";
    private static final String INDENTATION =
            "===".repeat(20);
    private static final String JOB_SELECT_MSG = "Choose your job type: \n" +
            "for Robotics, enter '/r'\n" +
            "for Semiconductor industry, enter '/s'\n" +
            "for Artificial intelligence, enter '/a'\n";
    private static final String BYE_MSG = "Bye bye adventurer!";
    private static final String HELP_MSG =
            "Enter ur action!\n" +
            "work - to work\n" +
            "rest - to rest\n" +
            "exercise - to exercise\n" +
            "status - to check status\n" +
            "upgrade - to upgrade(!NOTE you need to have at least $100000)\n" +
            "bye - to exit\n";
    private static final String COMPANY_HELP_MSG =
            "Here are the things you can do to manage your company:\n" +
            "hire <number> - to hire employee\n" +
            "fire <number> - to fire employee\n" +
            "raise <number> - to raise salary\n" +
            "lower <number> - to lower salary\n" +
            "bye - to exit\n";

    public static void printBoard(String boardInfo) {
        indentPrint(boardInfo + "\n");
    }

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

    public static void printCurrentRound(int currentRound, int actionsLeft) {
        System.out.println("Current round: " + currentRound + "\n" +
                "you have " + (20 - currentRound) + " rounds left");
        System.out.println(INDENTATION);
        System.out.println("You have " + actionsLeft + " actions left" +
                "\nInput your action! If needed, type 'help' for more info");
    }

    public static void printHelp() {
        indentPrint(HELP_MSG);
    }

    public static void printCompanyProfit(int profit) {
        if (profit > 0) {
            indentPrint("Good job! You have earned " + profit + " assets from your company!\n");
        } else {
            indentPrint("You have lost " + profit + " assets from your company!" +
                    "\nPlease manage your company better next time!\n");
        }
    }
}
