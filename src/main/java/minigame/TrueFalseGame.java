package minigame;

import ui.ResponseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Implements a Multiple Choice Question (MCQ) game with a focus on True or False questions,
 * covering a variety of topics. The game challenges players to answer correctly and tracks the
 * number of correct responses. It's designed to be engaging and informative, providing a fun
 * way to test knowledge on different subjects.
 */
public class TrueFalseGame implements MiniGame {
    private static final Logger logger = Logger.getLogger("MCQLog");
    private static final String INSTRUCTION_MESSAGE = "Type T for true and F for false\n";
    private static final String QUESTION_1 =
            "As per the textbook, brown-field projects are usually \n" +
                    "harder than green-field projects. True or False?\n";
    private static final String Q1_ANS = "F";
    private static final String QUESTION_2 =
            "Non-functional requirements specify the constraints under which system \n" +
                    "is developed and operated. True or False? \n";
    private static final String Q2_ANS = "T";
    private static final String QUESTION_3 = "One may have to spend an extra effort in digging NFRs \n" +
            "out as early as possible because they are easier to miss. True or False?\n";
    private static final String Q3_ANS = "T";
    private static final String QUESTION_4 = "Brainstorming aims to generate ideas; \n" +
            "not to validate them. True or False?\n";
    private static final String Q4_ANS = "T";

    private static final String QUESTION_5 = "Manufacturing is the entrepreneurial business " +
            "actually produce the products they sell \n";
    private static final String Q5_ANS = "T";
    private static final String QUESTION_6 = "Retailing business sells products to other " +
            "businesses rather than the final customer? \n";
    private static final String Q6_ANS = "F";
    private static final String QUESTION_7 = "Total sales-expenses is a calculation of profits \n";
    private static final String Q7_ANS = "T";
    private static final String QUESTION_8 = "Command economy allows the government to determine what, " +
            "how, and whom products and services are produced \n";
    private static final String Q8_ANS = "T";

    private static final String START_MSG = "Welcome to the MCQ Game!\n"
            + "Answer the following questions:\n";
    private static final List<String> questionList = new ArrayList<>();
    private static final List<String> answerList = new ArrayList<>();
    private static final String CORRECT_MESSAGE = "Correct!\n";
    private static final String WRONG_MESSAGE = "Incorrect!\n";

    private int correctCount;

    /**
     * Sets up the game environment, including initializing question and answer lists,
     * and configuring logging settings. This method is called before the game starts
     * to ensure all necessary components are ready for the gameplay.
     */
    private void gameSetUp() {
        questionList.add(QUESTION_1);
        questionList.add(QUESTION_2);
        questionList.add(QUESTION_3);
        questionList.add(QUESTION_4);
        questionList.add(QUESTION_5);
        questionList.add(QUESTION_6);
        questionList.add(QUESTION_7);
        questionList.add(QUESTION_8);
        answerList.add(Q1_ANS);
        answerList.add(Q2_ANS);
        answerList.add(Q3_ANS);
        answerList.add(Q4_ANS);
        answerList.add(Q5_ANS);
        answerList.add(Q6_ANS);
        answerList.add(Q7_ANS);
        answerList.add(Q8_ANS);

        LogManager.getLogManager().reset();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.SEVERE);
        logger.addHandler(consoleHandler);
    }

    /**
     * Starts the MCQ game session. It presents a series of True or False questions to the player,
     * collects their responses, and evaluates the correctness of each answer. The game tracks the
     * number of correct answers and provides immediate feedback for each response.
     */
    public void startGame() {
        correctCount = 0;
        gameSetUp();
        Scanner scanner = new Scanner(System.in);
        ResponseManager.indentPrint(START_MSG);
        for (int i = 0; i < 2; i++) {
            int index = getRandomNumber(0, questionList.size() - 1);
            assert index <= 3 : "Should not have index larger three!!!";
            ResponseManager.indentPrint(questionList.get(index));
            ResponseManager.indentPrint(INSTRUCTION_MESSAGE);
            String response = scanner.nextLine();
            correctnessCheck(index, response);
        }
    }

    /**
     * Evaluates the correctness of the player's response to a given question. It compares the player's
     * input against the correct answer and updates the game state accordingly. This method also handles
     * invalid inputs by providing appropriate feedback.
     *
     * @param index The index of the current question in the question list.
     * @param input The player's response to the current question.
     */
    public void correctnessCheck(int index, String input) {
        String answer = answerList.get(index);
        if (!input.trim().matches("T") && !input.trim().matches("F")) {
            System.out.println("You did not input T or F, which is what you should be inputting. \n" +
                    "Should I give you another chance to input again? Hell nah, get used to it!!!");
        }
        if (input.trim().matches(answer)) {
            ResponseManager.indentPrint(CORRECT_MESSAGE);
            correctCount += 1;
        } else {
            ResponseManager.indentPrint(WRONG_MESSAGE);
        }
    }

    /**
     * Returns the total number of questions the player has answered correctly during the game session.
     *
     * @return The count of correctly answered questions.
     */
    public int getCorrectCount() {
        return correctCount;
    }

    /**
     * Outputs the game results, displaying the total number of questions correctly answered by the player.
     * This method concludes the game session and provides a summary of the player's performance.
     */
    public void outputResult() {
        String question = correctCount > 1 ? " questions" : " question";
        System.out.println("You answered " + correctCount
                + question + " correctly.\n");

        logger.info("reached the end of rest action");
    }

    /**
     * Generates a random integer within a specified range. This utility method is used to select
     * questions randomly from the question list for the game session.
     *
     * @param min The minimum value (inclusive) of the range.
     * @param max The maximum value (exclusive) of the range.
     * @return A random integer within the specified range.
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
