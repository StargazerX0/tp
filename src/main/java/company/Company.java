package company;

import player.Asset;
import ui.ResponseManager;

/**
 * Represents a company within the EconoCraft game, handling operations such as hiring, firing employees,
 * and financial management including salaries and revenue.
 */
public class Company {
    private final String name;
    private int numberOfEmployees;
    private int employeeSalary;
    private int revenuePerEmployee;

    /**
     * Default constructor initializing a new Company with default values.
     */
    public Company() {
        this.name = "SIU";
        this.numberOfEmployees = 0;
        this.employeeSalary = 800;
        this.revenuePerEmployee = 1000;
    }

    /**
     * Constructs a new Company with specified attributes.
     *
     * @param name The name of the company.
     * @param numberOfEmployees Initial number of employees.
     * @param employeeSalary Initial salary per employee.
     * @param revenuePerEmployee Initial revenue per employee.
     */
    public Company(String name, int numberOfEmployees, int employeeSalary, int revenuePerEmployee) {
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.employeeSalary = employeeSalary;
        this.revenuePerEmployee = revenuePerEmployee;
    }

    /**
     * Hires a specified number of employees.
     *
     * @param number The number of employees to hire.
     */
    public void hireEmployee(int number) {
        numberOfEmployees += number;
    }

    /**
     * Removes a specified number of employees.
     *
     * @param number The number of employees to remove.
     */
    public void removeEmployee(int number) {
        numberOfEmployees -= number;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public int getRevenuePerEmployee() {
        return revenuePerEmployee;
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


    /**
     * Updates the company's revenue per employee by a specified amount.
     *
     * @param amount The amount to add to the revenue per employee.
     */
    public void updateRevenue(int amount) {
        revenuePerEmployee += amount;
    }

    /**
     * Updates the salary for employees by a specified amount.
     *
     * @param amount The amount to add to the employee salary.
     */
    public void updateSalary(int amount) {
        employeeSalary += amount;
    }

    /**
     * Updates the player's asset based on the company's profit per round.
     *
     * @param asset The player's asset to be updated.
     */
    public void updatePlayer(Asset asset) {
        ResponseManager.printCompanyProfit(profitPerRound());
        asset.addAsset(profitPerRound());
    }

    private int profitPerRound() {
        return receiveRevenue() - paySalaries();
    }

    /**
     * Returns a string representation of the company including various statistics.
     *
     * @return A string summary of the company.
     */
    public String toString() {
        return "Company: " + name + "\n" +
                "Number of Employees: " + numberOfEmployees + "\n" +
                "Employee Salary: " + employeeSalary + "\n" +
                "Revenue Per Employee: " + revenuePerEmployee + "\n" +
                "Profit per Round: " + profitPerRound() + "\n";
    }

}
