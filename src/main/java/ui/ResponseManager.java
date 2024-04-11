package ui;

import player.PlayerProfile;

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
            "bond - to purchase bonds from the bond market \n" +
            "sellbond - to sell all of your bonds \n" +
            "cryptocurrency - to purchase cryptocurrency from the cryptocurrency market \n" +
            "sellcryptocurrency - to sell all of your cryptocurrency \n" +
            "company - to check company status\n" +
            "hire <number> - to hire employee (each employee cost $1000 to hire)\n" +
            "fire <number> - to fire employee\n" +
            "raise <number> - to raise salary\n" +
            "lower <number> - to lower salary\n";

    public static void printBoard(String boardInfo) {
        indentPrint(boardInfo + "\n");
    }

    /**
     * Prints a message with indentation.
     * @param message The message to print.
     */
    public static void indentPrint(String message) {
        System.out.println(INDENTATION + "\n" + message + INDENTATION);
    }

    /**
     * Prints the game initialization message.
     */
    public static void printGameInit() {
        indentPrint(INITIALIZATION_MESSAGE);
    }

    /**
     * Prints the job selection message.
     */
    public static void printJobSelect() {
        indentPrint(JOB_SELECT_MSG);
    }

    /**
     * Prints the welcome message.
     *
     * @param playerProfile The player's profile.
     */
    public static void printWelcome(PlayerProfile playerProfile) {
        System.out.println("Welcome, " + playerProfile.toString());
    }

    /**
     * Prints the player's status.
     *
     * @param industry The industry the player has chosen.
     */
    public static void echoChosenIndustry(String industry) {
        System.out.println("You have chosen " + industry);
    }

    /**
     * Prints the goodbye message.
     */
    public static void printGoodbye() {
        System.out.println(BYE_MSG);
    }
    
    /**
     * Prints the which round the player is currently in.
     * @param currentRound The current round the player is in.
     */
    public static void printCurrentRound(int currentRound) {
        System.out.println("Current round: " + currentRound + "\n" +
                "you have " + (20 - currentRound) + " rounds left");
        System.out.println(INDENTATION);
    }

    /**
     * Prints the number of actions of the player left.
     * @param actionsLeft The number of actions left.
     */
    public static void printActionLeft(int actionsLeft) {
        String actionLeftStr = actionsLeft == 1 ? " action left" : " actions left";
        System.out.println("You have " + actionsLeft + actionLeftStr +
                "\nInput your action! If needed, type 'help' for more info");
        System.out.println(INDENTATION);
    }

    /**
     * Prints the help message for new players.
     */
    public static void printHelp() {
        indentPrint(HELP_MSG);
    }

    /**
     * Prints the indentation line.
     */
    public static void printIndentation() {
        System.out.println(INDENTATION);
    }

    /**
     * Prints the amount of money the company has earned or lost each round.
     * @param profit
     */
    public static void printCompanyProfit(int profit) {
        if (profit > 0) {
            indentPrint("Good job! You have earned " + profit + " assets from your company!\n");
        } else {
            indentPrint("You have lost " + profit + " assets from your company!" +
                    "\nPlease manage your company better next time!\n");
        }
    }

    /**
     * Asks the player if they want to restart the game.
     */
    public static void promptRestart() {
        indentPrint("Do you want to restart the game? (yes/no)");
    }
}
