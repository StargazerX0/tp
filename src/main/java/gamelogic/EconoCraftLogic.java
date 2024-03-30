package gamelogic;

import command.Command;
import command.CommandFactory;
import exception.CommandInputException;
import exception.GameException;
import exception.JobSelectException;
import exception.NameInputException;
import file.Loader;
import file.Saver;
import player.PlayerProfile;
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
        PlayerProfile playerProfile;
        playerProfile = Loader.loadProfile();

        if (playerProfile == null) {
            ResponseManager.printGameInit();
            String playerName = "";
            String jobType = "";
            try {
                playerName = getName();
            } catch (NoSuchElementException e) {
                ResponseManager.printGoodbye();
                System.exit(0);
            }

            ResponseManager.printJobSelect();
            try {
                jobType = getJob();
            } catch (NoSuchElementException e) {
                ResponseManager.printGoodbye();
                System.exit(0);
            }

            playerProfile = new PlayerProfile(playerName, jobType);
        }

        Saver.saveProfile(playerProfile);
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
                actionCount++;
                if (actionCount >= playerProfile.actionPerRound()) {
                    playerProfile.nextRound();
                    actionCount = 0;
                }
                playerProfile.updatePlayer();
                exitFlag = command.isExit() || playerProfile.isFinished();
            } catch (CommandInputException | GameException error) {
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
