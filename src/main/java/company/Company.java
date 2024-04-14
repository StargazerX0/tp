package company;

public class Company {
    private final String name;
    private int numberOfEmployees;
    private int employeeSalary;
    private int revenuePerEmployee;

    public Company() {
        this.name = "ECONO CROP";
        this.numberOfEmployees = 0;
        this.employeeSalary = 800;
        this.revenuePerEmployee = 1000;
    }
    public Company(String name, int numberOfEmployees, int employeeSalary, int revenuePerEmployee) {
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.employeeSalary = employeeSalary;
        this.revenuePerEmployee = revenuePerEmployee;
    }

    public void hireEmployee(int number) {
        numberOfEmployees += number;
    }

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

    public void updateRevenue(int amount) {
        revenuePerEmployee += amount;
    }

    public void updateSalary(int amount) {
        employeeSalary += amount;
    }

    public int profitPerRound() {
        return receiveRevenue() - paySalaries();
    }

    public String toString() {
        return "Company: " + name + "\n" +
                "Number of Employees: " + numberOfEmployees + "\n" +
                "Employee Salary: " + employeeSalary + "\n" +
                "Revenue Per Employee: " + revenuePerEmployee + "\n" +
                "Profit per Round: " + profitPerRound() + "\n";
    }
}
