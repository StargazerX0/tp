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
import static ui.ResponseManager.indentPrint;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class EconoCraftLogic {
    private static final Scanner userInput = new Scanner(System.in);
    private final PlayerProfile playerProfile;

    private EconoCraftLogic(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    public static EconoCraftLogic initializeGame() {
        PlayerProfile playerProfile = null;

        try {
            playerProfile = Loader.loadProfile();
        } catch (LoadProfileException e) {
            indentPrint("No previous record, creating new profile:\n");
        }

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
        }

        try {
            Saver.saveProfile(playerProfile);
        } catch (SaveProfileException e) {
            indentPrint("Error saving profile: " + e.getMessage());
        }

        ResponseManager.printWelcome(playerProfile);
        return new EconoCraftLogic(playerProfile);
    }

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

    public void startEcono() {
        ResponseManager.printHelp();
        boolean exitFlag = false;
        int actionCount = 0;

        while (!exitFlag) {
            inGameReminder(actionCount);
            try {
                Command command = CommandFactory.create(userInput.nextLine());
                command.execute(playerProfile);
                Saver.saveProfile(playerProfile);

                exitFlag = command.isExit();
                if (command.canGenerateEvent()) {
                    actionCount++;
                }
                if (actionCount >= playerProfile.actionPerRound()) {
                    actionCount = 0;
                    EventGenerator.getRandomEvent()
                            .triggerEvent(playerProfile);
                    playerProfile.nextRound();
                    exitFlag = playerProfile.isFinished();
                }
            } catch (CommandInputException | GameException | SaveProfileException error) {
                indentPrint(error.getMessage());
            }
        }
        printEndMessage(playerProfile);
        userInput.close();
    }

    private void inGameReminder(int actionCount) {
        if (actionCount == 0) {
            indentPrint("Current Status:\n" + playerProfile.toString() + "\n");
            ResponseManager.printCurrentRound(playerProfile.getCurrentRound());
        }
        ResponseManager.printActionLeft(playerProfile.actionPerRound() - actionCount);
    }

    private void printEndMessage(PlayerProfile playerProfile) {
        switch (playerProfile.checkWin()) {
        case 1:
            indentPrint("Congratulations! You have won the game!\n");
            break;

        case -1:
            indentPrint("You have lost the game. Better luck next time!\n");
            break;

        default:
            indentPrint("Game has been saved.\n");
            break;
        }
    }
}
