package company;

import player.Asset;
import ui.ResponseManager;

public class Company {
    private final String name;
    private int numberOfEmployees;
    private int employeeSalary;
    private int revenuePerEmployee;

    public Company() {
        this.name = "SIU";
        this.numberOfEmployees = 0;
        this.employeeSalary = 800;
        this.revenuePerEmployee = 1000;
    }

    public void hireEmployee(int number) {
        numberOfEmployees += number;
    }

    public void removeEmployee(int number) {
        numberOfEmployees -= number;
    }

    private int paySalaries() {
        return numberOfEmployees * employeeSalary;
    }

    private int receiveRevenue() {
        return numberOfEmployees * revenuePerEmployee;
    }

    public String getName() {
        return name;
    }

    public void updateRevenue(int amount) {
        revenuePerEmployee += amount;
    }

    public void updateSalary(int amount) {
        employeeSalary += amount;
    }

    public void updatePlayer(Asset asset) {
        int profit = receiveRevenue() - paySalaries();
        asset.addAsset(profit);
        ResponseManager.printCompanyProfit(profit);
    }
}
