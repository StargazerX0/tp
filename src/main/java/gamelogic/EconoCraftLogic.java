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
            indentPrint("Welcome back!\n");
        } catch (LoadProfileException e) {
            indentPrint("You will start a fresh new journey!\n");
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
            ResponseManager.printWelcome(playerProfile);
        }

        try {
            Saver.saveProfile(playerProfile);
        } catch (SaveProfileException e) {
            indentPrint("Error saving profile: " + e.getMessage());
        }

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
        printEndMessage(playerProfile);
        userInput.close();
    }

    private void inGameReminder(int actionCount) {
        if (actionCount == 0) {
            ResponseManager.printCurrentRound(playerProfile.getCurrentRound());
        }
        ResponseManager.printActionLeft(playerProfile.actionPerRound() - actionCount);
    }

    private void printEndMessage(PlayerProfile playerProfile) {
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

    private void promptRestart() {
        ResponseManager.promptRestart();
        if (isAccept()) {
            EconoCraftLogic.initializeGame().startEcono();
        } else {
            ResponseManager.printGoodbye();
            System.exit(0);
        }
    }
}
