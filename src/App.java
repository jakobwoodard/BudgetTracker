import java.util.Scanner;

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
            System.out.println("8. Exit");
            System.out.print('\n' + "Enter your choice: ");

            String choice = input.nextLine();
            System.out.println("\n\n\n");
            String description;
            double amount;
            String date;
            String category;
            switch (choice) {
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
                case "2":
                    System.out.print("Expense description: ");
                    description = input.nextLine();
                    System.out.print("Expense amount: ");
                    amount = input.nextDouble();
                    input.nextLine();
                    System.out.print("Expense date (mm-dd-yy): ");
                    date = input.nextLine();
                    System.out.println("Category (Food, Entertainment, Rent, Vehicle, Phone, Other)");
                    category = input.nextLine();
                    Expense e = new Expense(description, amount, date, parseCategory(category));
                    b.addExpense(e);
                    break;
                case "3":
                    System.out.println("\nIncome");
                    System.out.println("----------------------------------------------");
                    b.listIncome();
                    System.out.println("\nExpenses");
                    System.out.println("----------------------------------------------");
                    b.listExpenses();
                    break;
                case "4":
                    b.viewBudgetSummary();
                    break;
                case "5":
                //TODO
                    System.out.println("Not yet implemented");
                    break;
                case "6":
                    System.out.println("Which category would you like to filter by? (Food, Entertainment, Rent, Vehicle, Phone, Other)");
                    category = input.nextLine();
                    b.filterExpensesByCategory(parseCategory(category));
                    break;
                case "7":
                    //TODO
                    System.out.println("Not yet implemented");
                    break;
                case "8":
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice\n\n");
                    break;
            }
        }
        input.close();
    }

    private static Category parseCategory(String category) {
        if (category.equalsIgnoreCase("Food")) {
            return Category.FOOD;
        }
        else if (category.equalsIgnoreCase("Entertainment")) {
            return Category.ENTERTAINMENT;
        }
        else if (category.equalsIgnoreCase("Rent")) {
            return Category.RENT;
        }
        else if (category.equalsIgnoreCase("Vehicle")) {
            return Category.VEHICLE;
        }
        else if (category.equalsIgnoreCase("Phone")) {
            return Category.PHONE;
        }
        else {
            return Category.OTHER;
        }
    }
}
