package main.java.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Class that represents a user's budget. Keeps track of all income and expenses. Also keeps track of individual budgets and expenses per category.
 */
public class Budget {

    private ArrayList<Income> income;
    private double totalIncome;
    private ArrayList<Expense> expenses;
    private double totalExpenses;
    private HashMap<Category, Double> categoryBudgets;
    private HashMap<Category, Double> categoryExpenses;



    public ArrayList<Income> getIncome() {
        return income;
    }

    private void setIncome(ArrayList<Income> income) {
        this.income = income;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    private void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    private void setTotalIncome(double income) {
        this.totalIncome = income;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    private void setTotalExpenses(double expenses) {
        this.totalExpenses = expenses;
    }

    public HashMap<Category, Double> getCategoryBudgets() {
        return categoryBudgets;
    }

    public HashMap<Category, Double> getCategoryExpenses() {
        return categoryExpenses;
    }

    /**
     * Returns the total balance that a user has, represented by totalIncome - totalExpenses
     * @return the total balance a user has
     */
    public double getBalance() {
        return totalIncome - totalExpenses;
    }

    

    public Budget() {
        setIncome(new ArrayList<Income>());
        setExpenses(new ArrayList<Expense>());
        setTotalIncome(0);
        setTotalExpenses(0); 
        this.categoryBudgets = new HashMap<Category, Double>();
        this.categoryExpenses = new HashMap<Category, Double>();

    }

    /**
     * Lists all of the incomes in a formatted manner
     */
    public void listIncome() {
        System.out.printf("%-15s%-10s%-10s\n","Description", "Amount", "Date");
        System.out.println("----------------------------------------------");
        income.sort(null);
        for (Income i : income) {
            System.out.printf("%-15.11s%-10.2f%-10s\n", i.getDescription(), i.getAmount(), i.getDate());
        }
    }

    /**
     * Lists all of the expenses in a formatted manner
     */
    public void listExpenses() {
        System.out.printf("%-15s%-10s%-10s%-15s\n","Description", "Amount", "Date", "Category");
        System.out.println("----------------------------------------------");
        expenses.sort(null);
        for (Expense e : expenses) {
            System.out.printf("%-15.11s%-10.2f%-10s%-15s\n", e.getDescription(), e.getAmount(), e.getDate(), e.getCategory());
        }
    }

    /**
     * Lists all of the expenses for the given Cateogry c
     * @param c the Category by which all expenses will be listed for
     */
    public void filterExpensesByCategory(Category c) {
        System.out.printf("%-15s%-10s%-10s%-15s\n","Description", "Amount", "Date", "Category");
        System.out.println("----------------------------------------------");
        expenses.sort(null);
        for (Expense e: expenses) {
            if (e.getCategory().equals(c)) {
                System.out.printf("%-15.11s%-10.2f%-10s%-15s\n", e.getDescription(), e.getAmount(), e.getDate(), e.getCategory());
            }
        }
        System.out.printf("\nTotal spent on %s: $%.2f", c.toString(), categoryExpenses.get(c));
    }

    /**
     * Sets the budget for a given Category
     * @param category the Category whose budget is being updated
     * @param budget the desired budget for the category (represented as a double)
     */
    public void setCategoryBudget(Category category, double budget) {
        categoryBudgets.put(category, budget);
    }


    /**
     * Displays the total income, expenses, and remaining balance
     */
    public void viewBudgetSummary() {
        System.out.printf("%-20s$%.2f\n", "Total Income: ", getTotalIncome());
        System.out.printf("%-20s$%.2f\n", "Total Expenses: ", getTotalExpenses());
        System.out.printf("%-20s$%.2f\n", "Remaining Balance: ", (getTotalIncome() - getTotalExpenses()));
    }

    /**
     * Displays the user's expenses grouped in Categories. Also displays the total expenses at the end
     */
    public void viewExpensesByCateogory() {
        System.out.printf("%-10s%-10s\n","Category", "Amount");
        System.out.println("----------------------------------------------");
        for (Entry<Category, Double> entry : categoryExpenses.entrySet()) {
            System.out.printf("%-10%-10.2f\n", entry.getKey().toString(), entry.getValue());
        }
        System.out.printf("Total Expenses: %.2f", totalExpenses);
    }

    /**
     * Adds income to a user's budget
     * @param i the income being added, with a description, amount, and date
     */
    public void addIncome(Income i) {
        income.add(i);
        setTotalIncome(totalIncome + i.getAmount());
    }

    /**
     * Adds an expense. If the Category of the added expense has a budget associated with it, notifies the user if the budget has been exceeded.
     * @param e the expense beign added, with a description, amount, date, and category
     */
    public void addExpense(Expense e) {
        expenses.add(e);
        setTotalExpenses(totalExpenses + e.getAmount());


        if (categoryExpenses.containsKey(e.getCategory())) {
            categoryExpenses.put(e.getCategory(), categoryExpenses.get(e.getCategory()) + e.getAmount());
        }
        else {
            categoryExpenses.put(e.getCategory(), e.getAmount());
        }

        if (categoryBudgets.containsKey(e.getCategory()) && categoryExpenses.get(e.getCategory()) > categoryBudgets.get(e.getCategory())) {
            System.out.println("ALERT: " + e.getCategory().toString() + " is over budget!");
        }
    }
}
