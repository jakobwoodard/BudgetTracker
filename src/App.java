import java.util.Scanner;

import IO.BudgetReader;
import IO.BudgetWriter;
import models.Budget;
import models.Category;
import models.Expense;
import models.Income;

public class App {
    public static void main(String[] args) throws Exception {
        boolean running = true;
        Scanner input = new Scanner(System.in);
        Budget b = new Budget();

        while (running) {
            System.out.println("\n\n");
            System.out.println("Budget Tracker Menu: \n");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Income & Expenses");
            System.out.println("4. View Budget Summary");
            System.out.println("5. Set Category Budget");
            System.out.println("6. View Expenses by Category");
            System.out.println("7. Generate Report");
            System.out.println("8. Save to File");
            System.out.println("9. Load from File");
            System.out.println("10. Exit");
            System.out.print('\n' + "Enter your choice: ");

            String choice = input.nextLine();
            System.out.println("\n\n\n");
            String description;
            double amount;
            String date;
            String category;
            switch (choice) {
                // Adding Income
                case "1":
                    System.out.print("Income description: ");
                    description = input.nextLine();
                    System.out.print("Income amount: ");
                    amount = input.nextDouble();
                    input.nextLine();
                    System.out.print("Income date (mm-dd-yy): ");
                    date = input.nextLine();
                    Income i = new Income(description, amount, date);
                    b.addIncome(i);
                    break;
                // Adding Expense
                case "2":
                    System.out.print("Expense description: ");
                    description = input.nextLine();
                    System.out.print("Expense amount: ");
                    amount = input.nextDouble();
                    input.nextLine();
                    System.out.print("Expense date (mm-dd-yy): ");
                    date = input.nextLine();
                    System.out.print("Category (Food, Entertainment, Rent, Vehicle, Phone, Other): ");
                    category = input.nextLine();
                    Expense e = new Expense(description, amount, date, parseCategory(category));
                    b.addExpense(e);
                    break;
                // Viewing Income and Expenses
                case "3":
                    System.out.println("\nIncome");
                    System.out.println("----------------------------------------------");
                    b.listIncome();
                    System.out.println("\nExpenses");
                    System.out.println("----------------------------------------------");
                    b.listExpenses();
                    break;
                // Viewing Budget Summary
                case "4":
                    b.viewBudgetSummary();
                    break;
                // Setting Budget for given Category
                case "5":
                    System.out.print("Which category would you like to add a budget to? (Food, Entertainment, Rent, Vehicle, Phone, Other): ");
                    category = input.nextLine();
                    System.out.print("Budget amount: ");
                    amount = input.nextDouble();
                    input.nextLine();
                    b.setCategoryBudget(BudgetReader.parseCategory(category), amount);
                    System.out.printf("A budget of $%.2f has been set for %s\n", amount, parseCategory(category).toString());
                    break;
                // Viewing expenses by Category
                case "6":
                    System.out.print("Which category would you like to filter by? (Food, Entertainment, Rent, Vehicle, Phone, Other): ");
                    category = input.nextLine();
                    b.filterExpensesByCategory(parseCategory(category));
                    break;
                // Generating Report
                case "7":
                    //TODO
                    System.out.println("Not yet implemented");
                    break;
                // Save to file
                case "8":
                    System.out.print("Enter the file name to save to: ");
                    String filename = input.nextLine();
                    BudgetWriter.saveBudget(b, filename);
                    break;
                // Load from file
                case "9":
                    System.out.print("WARNING: This action will overwrite the current budget. It is advised you save the current budget before continuting.\nDo you wish to continue? (y/n) ");
                    String option = input.nextLine();
                    if (option.equalsIgnoreCase("y")) {
                        System.out.print("Enter the file name to read from: ");
                        filename = input.nextLine();
                        b = BudgetReader.readBudget(filename);
                    }
                    break;
                // Exit
                case "10":
                    System.out.println("Exiting...");
                    running = false;
                    break;
                // Invalid value
                default:
                    System.out.println("Invalid choice\n\n");
                    break;
            }
        }
        input.close();
    }
}
