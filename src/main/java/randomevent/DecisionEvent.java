package randomevent;

import exception.CommandInputException;
import player.PlayerProfile;

import java.util.Random;
import java.util.Scanner;

import static ui.Parser.isAccept;

public class DecisionEvent extends RandomEvent {
    private static final String[] DECISIONS = {
        "Boss want you to present a project to the board of directors.\n " +
                "You have to work overtime to prepare the presentation.\n" +
                "(Yes/No)?\n",
        "Do you want to improve your skills by taking a course? it costs $1000\n(Yes/No)?\n",
        "You found a wallet on the street. Do you want to return it to the police?\n(Yes/No)?\n",
        "There is a food challenge in town.\n" +
                "You need to eat 1kg of A5 Wagyu in 15min\n" +
                "If you win, U will receive $500\n" +
                "Otherwise, you will need to pay $200\n" +
                "Do you want to participate?\n(Yes/No)?\n",
        "Do you want to give a donation to the charity?\n(Yes/No)?\n",
        "Do you want to improve your company's employee benefits?\n(Yes/No)?\n",
        "Do you want to raise the salary of your employees?\n(Yes/No)?\n"
    };

    public DecisionEvent(double probability) {
        super(probability);
    }

    @Override
    public void triggerEvent(PlayerProfile playerProfile) throws CommandInputException {
        int range = playerProfile.isAdvancedPlayer() ?
                DECISIONS.length : DECISIONS.length - 3;
        int index = new Random().nextInt(range);

        switch (index) {
        case 0:
            presentProject(playerProfile);
            break;
        case 1:
            takeCourse(playerProfile);
            break;
        case 2:
            returnWallet(playerProfile);
            break;
        case 3:
            foodChallenge(playerProfile);
            break;
        case 4:
            donateCharity(playerProfile);
            break;
        case 5:
            improveBenefits(playerProfile);
            break;
        case 6:
            raiseSalary(playerProfile);
            break;
        default:
            System.out.println("A peaceful round!");
        }
    }

    private void presentProject(PlayerProfile playerProfile) throws CommandInputException {
        System.out.println(DECISIONS[0]);
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        if (isAccept(response)) {
            switch (new Random().nextInt(2)) {
            case 0:
                System.out.println("You have successfully presented the project to the board of directors!\n" +
                        "The boss is very satisfied with your work!");
                playerProfile.addAsset(500);
                break;

            case 1:
                System.out.println("You did a terrible job to present the project to the board of directors!\n" +
                        "The boss is very disappointed with your work!");
                playerProfile.loseAsset(200);
                break;

            default:
                break;
            }
        } else {
            System.out.println("You have rejected the offer.");
        }
    }

    private void takeCourse(PlayerProfile playerProfile) throws CommandInputException {
        System.out.println(DECISIONS[1]);
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        if (isAccept(response)) {
            playerProfile.loseAsset(1000);
            System.out.println("You have successfully taken the course!\n" +
                    "Your skills have been improved!\n" +
                    "Your money received has been increased by 50% for the next round! :)");
            playerProfile.adjustAssetMultiplier(1.5);
        } else {
            System.out.println("You have rejected the offer.");
        }
    }

    private void returnWallet(PlayerProfile playerProfile) throws CommandInputException {
        System.out.println(DECISIONS[2]);
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        if (isAccept(response)) {
            System.out.println("You have returned the wallet to the police.\n" +
                    "The owner of the wallet is very grateful to you!");
            playerProfile.addAsset(200);
        } else {
            System.out.println("You have kept the wallet for yourself.");
            switch (new Random().nextInt(2)) {
            case 0:
                System.out.println("The owner has found you and reported to the police!\n" +
                        "You have been fined $500 for stealing!");
                playerProfile.loseAsset(500);
                break;

            case 1:
                System.out.println("The owner has given up on finding the wallet.\n" +
                        "You have gained $200 from the wallet!");
                playerProfile.addAsset(200);
                break;

            default:
                break;
            }

        }
    }

    private void foodChallenge(PlayerProfile playerProfile) throws CommandInputException {
        System.out.println(DECISIONS[3]);
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        if (isAccept(response)) {
            switch (new Random().nextInt(3)) {
            case 0:
                System.out.println("You have successfully completed the food challenge!\n");
                playerProfile.addAsset(500);
                System.out.println("You have received $500 as a reward!");
                break;

            case 1:
                System.out.println("You have failed to complete the food challenge!\n");
                playerProfile.loseAsset(200);
                System.out.println("You have lost $200 as a penalty!");
                break;

            case 2:
                System.out.println("You have completed the food challenge, but your body cannot handle!\n" +
                        "You have received $200 as a consolation prize!\n" +
                        "You have lost $300 to pay the medical bill!");
                playerProfile.loseAsset(100);
                System.out.println("You have lost $100 TAT");
                break;

            default:
                break;
            }
        } else {
            System.out.println("You have rejected the offer.");
        }
    }

    private void donateCharity(PlayerProfile playerProfile) throws CommandInputException {
        System.out.println(DECISIONS[4]);
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        if (isAccept(response)) {
            playerProfile.loseAsset(500);
            System.out.println("You have donated $500 to the charity!\n" +
                    "The charity is very grateful to you!");
            switch (new Random().nextInt(2)) {
            case 0:
                System.out.println("You become famous for your donation!\n" +
                        "Now you can earn 20% faster for the next round!");
                playerProfile.adjustAssetMultiplier(1.2);
                break;

            case 1:
                System.out.println("You have received a thank you letter from the charity!");
                playerProfile.addAsset(200);
                System.out.println("You have received $200 as a reward!");
                break;

            default:
                break;
            }
        } else {
            System.out.println("You have rejected the offer.");
        }
    }

    private void improveBenefits(PlayerProfile playerProfile) throws CommandInputException {
        System.out.println(DECISIONS[5]);
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        if (isAccept(response)) {
            playerProfile.loseAsset(10000);
            System.out.println("You have successfully improved the company's employee benefits!\n" +
                    "The employees are very satisfied with the new benefits!");
            playerProfile.adjustAssetMultiplier(1.2);
            System.out.println("Your money received has been increased by 20% for the next round!");
        } else {
            System.out.println("You have rejected the offer.");
        }
    }

    private void raiseSalary(PlayerProfile playerProfile) throws CommandInputException {
        System.out.println(DECISIONS[6]);
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        if (isAccept(response)) {
            playerProfile.updateSalary(200);
            System.out.println("You have successfully raised $200 to the salary of your employees!\n" +
                    "The employees are very satisfied with the new salary!");
            playerProfile.updateRevenue(250);
            System.out.println("Your employees are more productive now!\n" +
                    "The revenue per employee has been increased by $250!");
        } else {
            System.out.println("You have rejected the offer.");
        }
    }
}
