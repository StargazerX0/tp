package seedu.duke;

import seedu.duke.ui.ResponseManager;

import java.util.Scanner;


public class InitializeCommand {

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        ResponseManager.printInitializationMessage();

        String playerName = scanner.nextLine();


        ResponseManager.printJobSelectionMessage();
        String jobType = scanner.nextLine();

        // verify user input
        while (!jobType.equals("Robotics") && !jobType.equals("Semiconductor industry")
                && !jobType.equals("Artificial intelligence")) {
            ResponseManager.printJobSelectionErrorMessage();
            jobType = scanner.nextLine();
        }

        System.out.println("Welcome, " + playerName + "! You have chosen a career in " + jobType + ".");

        scanner.close();
    }
}
