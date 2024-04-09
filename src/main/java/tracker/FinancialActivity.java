package tracker;

//Represents a single record of financial activity

public class FinancialActivity {
    private String type;
    private String description;
    private double amount;

    public FinancialActivity(String type, String description, double amount) {
        this.type = type;
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Description: %s, Amount: %.2f", type, description, amount);
    }
}
