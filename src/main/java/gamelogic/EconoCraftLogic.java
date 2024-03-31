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
            ResponseManager.indentPrint("No previous record, creating new profile: \n");
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
            ResponseManager.indentPrint("Error saving profile: " + e.getMessage());
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
                ResponseManager.indentPrint(e.getMessage());
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
                ResponseManager.indentPrint(e.getMessage());
            }
        }
        return playerName;
    }

    public void startEcono() {
        ResponseManager.printHelp();
        boolean exitFlag = false;
        int actionCount = 0;

        while (!exitFlag) {
            ResponseManager.printCurrentRound(playerProfile.getCurrentRound(),
                playerProfile.actionPerRound() - actionCount);
            try {
                Command command = CommandFactory.create(userInput.nextLine());
                command.execute(playerProfile);
                Saver.saveProfile(playerProfile);

                playerProfile.updatePlayer();
                exitFlag = command.isExit() || playerProfile.isFinished();
                if (command.canGenerateEvent()) {
                    actionCount++;
                    EventGenerator.getRandomEvent()
                            .triggerEvent(playerProfile);
                }
                if (actionCount >= playerProfile.actionPerRound()) {
                    playerProfile.nextRound();
                    actionCount = 0;
                }
            } catch (CommandInputException | GameException | SaveProfileException error) {
                ResponseManager.indentPrint(error.getMessage());
            }
        }
        printEndMessage(playerProfile);
        userInput.close();
    }

    private void printEndMessage(PlayerProfile playerProfile) {
        switch (playerProfile.checkWin()) {
        case 1:
            ResponseManager.indentPrint("Congratulations! You have won the game!\n");
            break;

        case -1:
            ResponseManager.indentPrint("You have lost the game. Better luck next time!\n");
            break;

        default:
            ResponseManager.indentPrint("Game has been saved.\n");
            break;
        }

    }
}
