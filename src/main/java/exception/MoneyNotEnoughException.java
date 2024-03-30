package exception;

public class MoneyNotEnoughException extends GameException {
    public MoneyNotEnoughException(String message) {
        super("You do not have enough money\n" + message);
    }
}
