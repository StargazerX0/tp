package tracker;

import java.util.ArrayList;
import java.util.List;

//managing and displaying all financial activity records

public class Tracker {
    private List<FinancialActivity> activities = new ArrayList<>();

    public void recordActivity(String type, String description, double amount) {
        activities.add(new FinancialActivity(type, description, amount));
    }

    public void showActivities() {
        if (activities.isEmpty()) {
            System.out.println("No financial activities recorded.");
            return;
        }
        System.out.println("Financial Activities:");
        activities.forEach(activity -> System.out.println(activity.toString()));
    }
}
