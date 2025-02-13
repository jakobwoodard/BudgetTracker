package main.java;
import java.util.Scanner;

import main.java.IO.BudgetReader;
import main.java.IO.BudgetWriter;
import main.java.models.Budget;
import main.java.models.Income;
import main.java.models.Expense;


/**
 * This program is meant to demonstrate a wide variety of coding concepts and my ability to produce a functioning, robust Java program.
 * By following the CLI, a user will be prompted to manipulate data pertaining to their "budget". A "budget" in this program is a set of 
 * income(s) and expense(s) with given descriptors, amounts, dates, and for expenses - categories. A user is able to set spending limits for 
 * specific categories BUT, similar to real-life, there is no preventing the user from overspending on their budget. Instead, they are sent an "alert"
 * that they have overspent. 
 * 
 * At any time during the program, the user can see what his or her past incomes and expenses are. They can see this either through listing the entirety
 * of their budget, through a summary of just total dollar amounts, or by viewing their expenses by a specific category.
 * 
 * A user can also generate reports about their budget. These reports include a full expense report, sorted by time; a report on a single or multiple spending
 * categories; a report about expenses over a specific time frame; or a report combining a single or multiple spending categories within a single time frame.
 * These reports will be generated in a csv format to a file specified by the user.
 * 
 * The user can also save the entirety of their budget to a specified file at any point. This is meant to allow the user to save their budget and return back
 * later to add on/generate reports. The user can return to editing their budget by reading in a budget file. A budget file has a specific format and keeps 
 * track of all income, expenses, and any budgets for individual categories that may have been added by the user.
 */
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
                    try {
                        Income i = new Income(description, amount, date);
                        b.addIncome(i);
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("Error adding income.");
                        System.out.println(e.getMessage());
                    }
                    
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
                    Expense e = new Expense(description, amount, date, BudgetReader.parseCategory(category));
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
                    System.out.printf("A budget of $%.2f has been set for %s\n", amount, BudgetReader.parseCategory(category).toString());
                    break;
                // Viewing expenses by Category
                case "6":
                    System.out.print("Which category would you like to filter by? (Food, Entertainment, Rent, Vehicle, Phone, Other): ");
                    category = input.nextLine();
                    b.filterExpensesByCategory(BudgetReader.parseCategory(category));
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
