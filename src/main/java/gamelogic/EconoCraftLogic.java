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
    private final PlayerProfile player;

    private EconoCraftLogic(PlayerProfile player) {
        this.player = player;
    }

    public static EconoCraftLogic initializeGame() {
        PlayerProfile player = null;
        try {
            player = Loader.loadProfile();
            indentPrint("Welcome back!\n");
        } catch (LoadProfileException e) {
            indentPrint("You will start a fresh new journey!\n");
        }

        if (player == null) {
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
            player = new PlayerProfile(playerName, jobType);
            ResponseManager.printWelcome(player);
        }

        try {
            Saver.saveProfile(player);
        } catch (SaveProfileException e) {
            indentPrint("Error saving profile: " + e.getMessage());
        }

        return new EconoCraftLogic(player);
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
            inGameReminder(player.getActionCount());
            try {
                Command command = CommandFactory.create(userInput.nextLine());
                command.execute(player);
                exitFlag = command.isExit();

                if (command.canGenerateEvent()) {
                    player.nextAction();
                }
                if (!player.canAct()) {
                    EventGenerator.getRandomEvent()
                            .triggerEvent(player);
                    player.nextRound();
                    exitFlag = player.isFinished();
                }
                Saver.saveProfile(player);
            } catch (CommandInputException | GameException | SaveProfileException error) {
                indentPrint(error.getMessage());
            }
        }
        printEndMessage(player);
        userInput.close();
    }

    private void inGameReminder(int actionCount) {
        if (actionCount == 0) {
            ResponseManager.printCurrentRound(player.getCurrentRound());
        }
        ResponseManager.printActionLeft(player.actionPerRound() - actionCount);
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
