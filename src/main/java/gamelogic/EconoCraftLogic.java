package gamelogic;

import command.Command;
import command.CommandFactory;
import exception.CommandInputException;
import exception.GameException;
import exception.JobSelectException;
import exception.LoadProfileException;
import exception.NameInputException;
import exception.SaveProfileException;
import file.Loader;
import file.Saver;
import player.PlayerProfile;
import randomevent.EventGenerator;
import ui.Parser;
import ui.ResponseManager;

import static ui.Parser.isAccept;
import static ui.ResponseManager.indentPrint;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents the game logic of EconoCraft.
 */
public class EconoCraftLogic {
    private static final Scanner userInput = new Scanner(System.in);
    private final PlayerProfile playerProfile;

    private EconoCraftLogic(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    /**
     * Initializes the game by loading the player's profile if it exists,
     * or creating a new profile if it does not exist or is corrupted.
     *
     * @return the game logic object to start the game.
     */
    public static EconoCraftLogic initializeGame() {
        PlayerProfile playerProfile = null;
        playerProfile = loadProgress(playerProfile);

        if (playerProfile == null) {
            ResponseManager.printGameInit();
            String playerName = "";
            String jobType = "";
            
            try {
                playerName = getName();
                ResponseManager.printJobSelect();
                jobType = getJob();
            } catch (NoSuchElementException e) {
                ResponseManager.printGoodbye();
                System.exit(0);
            }
            playerProfile = new PlayerProfile(playerName, jobType);
            ResponseManager.printWelcome(playerProfile);
        }
        saveProgress(playerProfile);

        return new EconoCraftLogic(playerProfile);
    }

    /**
     * Saves the player's progress.
     *
     * @param playerProfile the player's profile to save.
     */
    private static void saveProgress(PlayerProfile playerProfile) {
        try {
            Saver.saveProfile(playerProfile);
        } catch (SaveProfileException e) {
            indentPrint("Error saving profile: " + e.getMessage());
        }
    }

    /**
     * Loads the player's progress.
     *
     * @param playerProfile the player's profile to load.
     * @return the player's profile.
     */
    private static PlayerProfile loadProgress(PlayerProfile playerProfile) {
        try {
            playerProfile = Loader.loadProfile();
            indentPrint("Welcome back!\n");
        } catch (LoadProfileException e) {
            indentPrint("Seems like there is no saved profile or profile corrupted.\n" +
                    "You will start a fresh new journey!\n");
        }
        return playerProfile;
    }

    /**
     * Gets the job type from the user.
     *
     * @return the job type selected by the user.
     */
    private static String getJob() {
        String jobType = "";
        while (jobType.isEmpty()) {
            try {
                jobType = Parser.parseCareer(userInput.nextLine());
            } catch (JobSelectException e) {
                indentPrint(e.getMessage());
            }
        }
        return jobType;
    }

    /**
     * Gets the player's name from the user.
     *
     * @return the player's name.
     */
    private static String getName() {
        String playerName = "";
        while (playerName.isEmpty()) {
            try {
                playerName = Parser.parseName(userInput.nextLine());
            } catch (NameInputException e) {
                indentPrint(e.getMessage());
            }
        }
        return playerName;
    }

    /**
     * Starts the game loop.
     */
    public void startGame() {
        ResponseManager.printHelp();
        boolean exitFlag = false;

        while (!exitFlag) {
            inGameReminder(playerProfile.getActionCount());
            try {
                Command command = CommandFactory.create(userInput.nextLine());
                command.execute(playerProfile);
                exitFlag = command.isExit();

                if (command.canGenerateEvent()) {
                    playerProfile.nextAction();
                }
                if (!playerProfile.canAct()) {
                    EventGenerator.getRandomEvent()
                            .triggerEvent(playerProfile);
                    playerProfile.nextRound();
                    exitFlag = playerProfile.isFinished();
                }
                Saver.saveProfile(playerProfile);
            } catch (CommandInputException | GameException | SaveProfileException error) {
                indentPrint(error.getMessage());
            }
        }
        showEndMessage(playerProfile);
        userInput.close();
    }

    /**
     * Prints the action left for the player to perform and
     * prints the current round the player is in at the start of the round.
     *
     * @param actionCount the number of actions performed.
     */
    private void inGameReminder(int actionCount) {
        if (actionCount == 0) {
            ResponseManager.printCurrentRound(playerProfile.getCurrentRound());
        }
        ResponseManager.printActionLeft(playerProfile.actionPerRound() - actionCount);
    }

    /**
     * Shows the end message of the game based on the player's condition.
     * If the player wins or loses, the game will be restarted.
     *
     * @param playerProfile the player's profile.
     */
    private void showEndMessage(PlayerProfile playerProfile) {
        switch (playerProfile.checkWin()) {
        case 1:
            indentPrint("Congratulations! You have won the game!\n");
            Saver.deleteProfile();
            promptRestart();
            break;

        case -1:
            indentPrint("You have lost the game. Better luck next time!\n");
            Saver.deleteProfile();
            promptRestart();
            break;

        default:
            indentPrint("Game has been saved.\n");
            break;
        }
    }

    /**
     * Prompts the player to restart the game.
     * If the player accepts, the game will be restarted.
     * If the player declines, the game will exit.
     */
    private void promptRestart() {
        ResponseManager.promptRestart();
        if (isAccept()) {
            EconoCraftLogic.initializeGame().startGame();
        } else {
            ResponseManager.printGoodbye();
            System.exit(0);
        }
    }
}
