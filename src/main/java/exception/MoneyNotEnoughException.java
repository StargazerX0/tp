package exception;

public class MoneyNotEnoughException extends GameException {
    public MoneyNotEnoughException() {
        super("You do not have enough money for this service!"
                + " Please try again after you have earned more money!\n");
    }
}
